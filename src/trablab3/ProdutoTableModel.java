package trablab3;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;

    public ProdutoTableModel(List<Produto> dados) {
        this.produtos = dados;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return produtos.get(rowIndex).getNome();
            case 1:
                return produtos.get(rowIndex).getPreco();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "Preco";
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void add(Produto p) {
       produtos.add(p);
       //this.fireTableDataChanged();
       this.fireTableRowsInserted(produtos.size()-1, produtos.size()-1);
    }
    
    public void remove(int l){
        produtos.remove(l);
        this.fireTableRowsDeleted(l, l);
    }

    
}
