/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author fhnri
 */
public class Pedido {
    public static int pedidos_id=1;
    protected int id;
    protected Date data;
    protected Date dataFim;
    protected List<Produto> produtos;
    protected boolean aberto;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat formatoSql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Pedido(){
        this.id=pedidos_id;
        this.data=new Date();
        Pedido.pedidos_id=pedidos_id+1;
        produtos=new ArrayList<Produto>();
        aberto=true;
    }
    
    public Pedido(Date data, List<Produto> produtos) {
        this.id = pedidos_id;
        this.data = data;
        this.produtos = produtos;
        Pedido.pedidos_id+=1;
    }
    
    public Pedido(int id,Date data,Date dataFim,boolean aberto) {
        
        this.id = id;
        this.data = data;
        this.dataFim = dataFim;
        produtos=new ArrayList<Produto>();
        this.aberto = aberto;
        
        if(id>=Pedido.pedidos_id){
            
            Pedido.pedidos_id=id+1;
        
        }
    }

    public Date getData() {
        return data;
    }
    
    public String getDataSql() {
        return formatoSql.format(data);
        
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void addProduto(Produto p){
        this.produtos.add(p);
    }

    public int getId() {
        return id;
    }

    public boolean isAberto() {
        return aberto;
    }

    public Date getDataFim() {
        return dataFim;
    }
    
    public String getDataFimSql() {
        return formatoSql.format(dataFim);
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
        if(!aberto){
            dataFim=new Date();
        }
    }
    
    public float totalPedido(){
        float total = 0f;
        ArrayList<Produto> produtosList = (ArrayList<Produto>) this.produtos;
        for (Iterator<Produto> it = produtosList.iterator(); it.hasNext();) {
            Produto produto = it.next();
            total+=produto.preco;
        }
        return total;
    }
    
    @Override
    public String toString(){
        return this.id+" ("+formato.format(this.data)+") - "+(this.aberto?"Aberto":"Fechado")+" - Total: "+totalPedido();
    }

    void removeProduto(Produto p) {
        this.produtos.remove(p);
    }
    
}
