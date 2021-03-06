/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        List<Mesa> mesas = new ArrayList<Mesa>();
        
        BufferedReader bufferMesas= null;
        
        try {
            
            bufferMesas = new BufferedReader(new FileReader("mesas.txt"));
            
            String line = bufferMesas.readLine();
            
            while (line != null) {
                
                String[] dados = line.split("\t");
                
                mesas.add(new Mesa(Integer.parseInt(dados[0]), dados[1]));
                
                line = bufferMesas.readLine();
                
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo:\n"+ex.getMessage(),"Arquivo não encontrado",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TrabLab3.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally {
 
            try {
                bufferMesas.close();
            } catch (IOException ex) {
                Logger.getLogger(TrabLab3.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
            
            
            
        }
        return mesas;
    }
    
    public static List<Produto> getSampleProduto() {
        
        List<Produto> produtos = new ArrayList<Produto>();
        
        BufferedReader bufferProdutos= null;
        
        try {
            
            bufferProdutos = new BufferedReader(new FileReader("produtos.txt"));
            
            String line = bufferProdutos.readLine();
                
            while (line != null) {

                String[] dados = line.split("\t");
                
                produtos.add(new Produto(Integer.parseInt(dados[0]), dados[1], Float.parseFloat(dados[2])));

                line = bufferProdutos.readLine();

            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo:\n"+ex.getMessage(),"Arquivo não encontrado",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TrabLab3.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
 
            try {
                bufferProdutos.close();
            } catch (IOException ex) {
                Logger.getLogger(TrabLab3.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
                
            }
            
            
            
        }
        return produtos;
    }
}
