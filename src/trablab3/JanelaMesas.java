/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author fhnri
 */
public class JanelaMesas extends JFrame{
    
    private JanelaPedido janelaPedidos = new JanelaPedido();
    
    protected List<Mesa> mesas;
    public final JList<Mesa> lstMesas= new JList<>(new DefaultListModel<>());
    
    public final JList<Pedido> lstPedido= new JList<>(new DefaultListModel<>());
    
    public ArrayList<Produto> produtos = (ArrayList<Produto>) TrabLab3.getSampleProduto();
    
    private JButton fecharPedido = new JButton("Fechar pedido");
    private JButton addItem = new JButton("Gerenciar pedido");
    private JButton novoPedido = new JButton("Novo pedido");
    private JButton removerPedido = new JButton("Remover pedido");

    public JanelaMesas(List<Mesa> mesasSample) throws HeadlessException {
        
        super("Mesas");
        
        JanelaMesas janela=this;
        
        this.mesas = mesasSample;
        
        
        lstMesas.setModel(new MesasListModel(this.mesas));
        
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane paneMesas = new JScrollPane(lstMesas);
        Dimension d = lstMesas.getPreferredSize();
        d.width = 150;
        paneMesas.setPreferredSize(d);
        
        add(paneMesas,BorderLayout.WEST);
         
         add(new JScrollPane(lstPedido),BorderLayout.CENTER);
        
        JPanel botoes = new JPanel(new GridLayout(1,4));
        
        fecharPedido.setEnabled(false);
        addItem.setEnabled(false);
        removerPedido.setEnabled(false);
        
        botoes.add(fecharPedido);
        botoes.add(addItem);
        botoes.add(novoPedido);
        botoes.add(removerPedido);
        
        readPedido();
        
        add(botoes,BorderLayout.SOUTH);
        lstMesas.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if(selecionada != null){
                    lstPedido.setModel( new PedidosListModel(selecionada.getPedidos() ) );
                }else{
                    lstPedido.setModel( new DefaultListModel<>());
                }
            }
            
        });
        lstPedido.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    fecharPedido.setEnabled(true);
                    if(pedido.isAberto()){
                        addItem.setEnabled(true);
                        removerPedido.setEnabled(true);
                        fecharPedido.setText("Fechar pedido");
                    }else{
                        addItem.setEnabled(false);
                        removerPedido.setEnabled(false);
                        fecharPedido.setText("Visualizar pedido");
                    }
                }else{
                    fecharPedido.setEnabled(false);
                    addItem.setEnabled(false);
                    removerPedido.setEnabled(false);
                }
            }
        });
        
        
        novoPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if(selecionada != null){
                    selecionada.addPedido(new Pedido());
                    lstPedido.updateUI();
                    lstMesas.updateUI();
                    savePedido();
                }
            }
        });
        
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    
                    janelaPedidos.viewPedido(janela);
                    
                }
                
            }
        });
        
        fecharPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    
                    janelaPedidos.closePedido(janela);
                    savePedido();
                }
                
            }
        });
        
        removerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    
                    lstPedido.clearSelection();
                    Mesa selecionada = lstMesas.getSelectedValue();
                    selecionada.getPedidos().remove(pedido);
                    lstPedido.updateUI();
                    lstMesas.updateUI();
                    savePedido();
                }
            }
        });
        
    }

    void viewNF() {
        if(lstPedido.getSelectedValue()!=null){
            JanelaNotaFiscal NF=new JanelaNotaFiscal(this);
        }
    }
    
    public void readPedido(){
        BufferedReader bufferPedido = null;
        BufferedReader bufferProdutos = null;
        try {
            
            bufferPedido = new BufferedReader(new FileReader("pedidos.txt"));
            bufferProdutos = new BufferedReader(new FileReader("produtosPedido.txt"));
            
            try {
                
                String line = bufferPedido.readLine();
                
                while (line != null) {
                    
                    String[] dados = line.split("\t");
                    
                    Mesa mesa = mesas.get(Integer.parseInt(dados[0])-1);
                    
                    boolean aberto = Integer.parseInt(dados[4])==1?true:false;
                    
                    long dateFim = Long.parseLong(dados[3]);
                    
                    mesa.addPedido(new Pedido(Integer.parseInt(dados[1]),new Date(Long.parseLong(dados[2])),dateFim>0 ? new Date(dateFim) : null, aberto ) );
                    
                    line = bufferPedido.readLine();
                
                }
                
                line = bufferProdutos.readLine();
                
                while (line != null) {
                    
                    String[] dados = line.split("\t");
                    
                    Mesa mesa = mesas.get(Integer.parseInt(dados[0])-1);
                    
                    int idPedido=Integer.parseInt(dados[1]);
                    
                    for(Pedido pedido:mesa.getPedidos()){
                        
                        if(pedido.getId()==idPedido){
                            
                            int idProduto=Integer.parseInt(dados[2]);
                            
                            //Aqui eu passo por todos os produtos para ter certeza que o produto existe, 
                            //poderia ter pego pela posição
                            for(Produto p:produtos){
                                
                                if(p.getId()==idProduto){

                                    pedido.addProduto(p);

                                }
                            }
                            
                        }
                    }
                    
                    line = bufferProdutos.readLine();
                
                }
            
            } finally {
                
                bufferPedido.close();
                bufferProdutos.close();
                
            }
            
        } catch (Exception ex) {
            try {
                new Formatter("pedidos.txt");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(JanelaMesas.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo:\n"+ex.getMessage()+"\n Foi criado um novo arquivo.","Arquivo não encontrado",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JanelaMesas.class.getName()).log(Level.SEVERE, null, ex);
            
            
        } finally {
            try {
                bufferPedido.close();
                bufferProdutos.close();
            } catch (IOException ex) {
                
                Logger.getLogger(JanelaMesas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void savePedido(){
        
        Formatter arqPedido;
        Formatter arqProdutos;
        
        try {
            
            arqPedido = new Formatter("pedidos.txt");
            
            arqProdutos = new Formatter("produtosPedido.txt");
            
            for(Mesa mesa:this.mesas){

                ArrayList<Pedido> pedidos = (ArrayList<Pedido>) mesa.getPedidos();
                
                for(Pedido pedido:pedidos){
                    
                    arqPedido.format("%d\t%d\t%d\t%d\t%d\n", mesa.getId(),pedido.getId(),pedido.getData().getTime(),!pedido.isAberto() ? pedido.getDataFim().getTime() : 0,pedido.isAberto()?1:0);
                    
                    ArrayList<Produto> produtos = (ArrayList<Produto>) pedido.getProdutos();
                    
                    for(Produto p:produtos){
                        arqProdutos.format("%d\t%d\t%d\n", mesa.getId(), pedido.getId(),p.getId());
                    }
                    
                }
                
                
            }
            arqProdutos.close();
            arqPedido.close();
        
        } catch (FileNotFoundException ex) {
        
            Logger.getLogger(JanelaPedido.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Não foi possível salvar o arquivo.","Erro",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    
}
