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
public class MesasListModel implements ListModel<Mesa> {
    
    List<Mesa> mesas;
    private final List<ListDataListener> dataListeners;
    
    public MesasListModel(List<Mesa> mesas) {
        this.mesas=mesas;
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return mesas.size();
    }

    @Override
    public Mesa getElementAt(int index) {
        return mesas.get(index);
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
