/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author 11944413600
 */
public class ItemsPedidoListModel implements ListModel<Produto> {
    
    List<Produto> produtos;
    private final List<ListDataListener> dataListeners;
    
    public ItemsPedidoListModel(List<Produto> produtos) {
        this.produtos=produtos;
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return produtos.size();
    }

    @Override
    public Produto getElementAt(int index) {
        return produtos.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        dataListeners.remove(l);
    }
    
}
