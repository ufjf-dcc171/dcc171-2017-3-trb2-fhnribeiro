/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author fhnri
 */
public class JanelaPedido extends JFrame{
    JanelaMesas mesa;
    Pedido pedido;
    private final JList<Produto> lstProdutos = new JList<>(new DefaultListModel<>());;
    private final JList<Produto> lstNovosProdutos = new JList<>(new DefaultListModel<>());;
    
    private final JButton addProduto = new JButton("Adicionar");
    private final JButton removeProduto = new JButton("Remover");
    
    public JanelaPedido(){
        super("Pedido");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JScrollPane paneNovosProdutos = new JScrollPane(lstNovosProdutos);
        add(paneNovosProdutos,BorderLayout.WEST);
        JScrollPane paneProdutos = new JScrollPane(lstProdutos);
        add(paneProdutos,BorderLayout.CENTER);
        JPanel botoes = new JPanel(new GridLayout(1,2));
        botoes.add(addProduto);
        botoes.add(removeProduto);        
        add(botoes,BorderLayout.SOUTH);
        
        addProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto p=lstNovosProdutos.getSelectedValue();
                if(p!=null){
                    pedido.addProduto(p);
                    lstProdutos.updateUI();
                    mesa.lstPedido.updateUI();
                    mesa.savePedido();
                }
            }
        });
        
        removeProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto p=lstNovosProdutos.getSelectedValue();
                if(p!=null){
                    pedido.removeProduto(p);
                    lstProdutos.clearSelection();
                    lstProdutos.updateUI();
                    mesa.lstPedido.updateUI();
                    mesa.savePedido();
                }
            }
        });
    }
    
    public void viewPedido(JanelaMesas mesa){
        this.mesa=mesa;
        this.pedido=mesa.lstPedido.getSelectedValue();
        
        if(this.pedido!=null){
            if(pedido.isAberto()){
                lstNovosProdutos.setModel(new ItemsPedidoListModel(TrabLab3.getSampleProduto()));
            }
            lstProdutos.setModel(new ItemsPedidoListModel(this.pedido.getProdutos()));
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            mesa.lstPedido.updateUI();
        }
        
    }
    
    public void closePedido(JanelaMesas mesa){
        this.mesa=mesa;
        this.pedido=mesa.lstPedido.getSelectedValue();
        if(pedido!=null){
            if(pedido.isAberto()){
                pedido.setAberto(false);
            }
            lstProdutos.setModel(new ItemsPedidoListModel(this.pedido.getProdutos()));
            mesa.lstPedido.updateUI();
            int tempIndex=mesa.lstPedido.getSelectedIndex();
            mesa.viewNF();
            mesa.savePedido();
            mesa.lstPedido.clearSelection();

        }
    }
    
}
