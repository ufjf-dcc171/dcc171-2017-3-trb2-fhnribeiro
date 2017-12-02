/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

/**
 *
 * @author fhnri
 */
public class Produto {
    protected String nome;
    protected String descricao;
    protected float preco;

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    protected int id;

    public Produto(String nome, String descricao,float preco,int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco=preco;
        this.id=id;
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    
    public String toString(){
        return this.nome+" - "+Float.toString(preco);
    }
    
}
