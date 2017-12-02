/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fhnri
 */
public class JanelaNotaFiscal extends JFrame{
    private List<Produto> produtos = new ArrayList<>();
    private JTable tabela;
    protected JLabel lblTotal = new JLabel("Total: ");
    protected JLabel lblValueTotal = new JLabel("");
    protected JLabel lblStart = new JLabel("Aberto em: ");
    protected JLabel lblEnd = new JLabel("Fechado em: ");
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    JanelaNotaFiscal(JanelaMesas mesa){
        super("Nota fiscal");
        Pedido pedido=mesa.lstPedido.getSelectedValue();
        tabela = new JTable(new ProdutoTableModel(pedido.getProdutos()));
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(2, 2));
        lblStart.setText(lblStart.getText()+formato.format(pedido.getData()));
        lblEnd.setText(lblEnd.getText()+formato.format(pedido.getDataFim()));
        totalPanel.add(lblStart);
        totalPanel.add(lblEnd);
        totalPanel.add(lblTotal);
        totalPanel.add(lblValueTotal);
        add(totalPanel,BorderLayout.SOUTH);
        setSize(480,500);
        lblValueTotal.setText("R$ "+Float.toString(pedido.totalPedido()));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
}
