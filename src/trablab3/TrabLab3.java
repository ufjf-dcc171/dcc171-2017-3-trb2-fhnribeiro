/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author fhnri
 */
public class TrabLab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JanelaMesas janela = new JanelaMesas(getSampleMesa());
        janela.setSize(650,350);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    
    private static List<Mesa> getSampleMesa() {
        List<Mesa> mesas = new ArrayList<Mesa>() {
            {
                add(new Mesa("Mesa 1"));
                add(new Mesa("Mesa 2"));
                add(new Mesa("Mesa 3"));
                
            }
        };
        return mesas;
    }
    
    public static List<Produto> getSampleProduto() {
        List<Produto> produtos = new ArrayList<Produto>() {
            {
                add(new Produto("Prato 1", "Descrição do prato 1",10.50f,1));
                add(new Produto("Prato 2", "Descrição do prato 2",5f,2));
                add(new Produto("Prato 3", "Descrição do prato 3",2.50f,3));
            }
        };
        return produtos;
    }
}
