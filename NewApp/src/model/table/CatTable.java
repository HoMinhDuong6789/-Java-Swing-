/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.bean.Category;
import model.dao.CategoryDAO;

/**
 *
 * @author Minh_PC
 */
public class CatTable extends AbstractTableModel {

    private List<Category> allItem;
    private String[] arrCols = {"ID Danh Mục", "Tên Danh Mục"};
    private JTable table;
    private CategoryDAO catDAO;

    public CatTable(JTable tbMain) {
        this.table = tbMain;
        catDAO = new CategoryDAO();
        allItem = catDAO.getList();

    }

    public List<Category> getEmployee() {
        return allItem;
    }

    public void loadTable() {
        table.setModel(this);
        cssForTable();
    }

    private void cssForTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(800);
        table.getTableHeader().setPreferredSize(new Dimension(0, 32));
        table.setRowHeight(28);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
    }

    @Override
    public int getColumnCount() {
        return arrCols.length;
    }

    @Override
    public int getRowCount() {
        return allItem.size();
    }

    @Override
    public String getColumnName(int column) {
        return arrCols[column];

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Category objCat = allItem.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = objCat.getIdCat();
                break;

            case 1:
                value = objCat.getName();
                break;
        }

        return value;
    }

    /*
    co hai loai model:
        DefaultTableModel= Abstrac table model  
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    public int addItem(Category objCat) {
        int last_id = 1;
        last_id = catDAO.addItem(objCat);
        objCat.setIdCat(last_id);
        allItem.add(objCat);
        this.fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
        return last_id;
    }

    public int delItem(int row, Category ojbCategory) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        catDAO.delItem(ojbCategory);
        allItem.remove(rowModel);
        fireTableDataChanged();
        return result;
    }

    public int EditItem(int row, Category ojbCategory) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        catDAO.EditItem(ojbCategory);
        allItem.set(rowModel, ojbCategory);
        fireTableRowsUpdated(rowModel, rowModel);

        // chon lai dong moi sua
        table.setRowSelectionInterval(rowModel, rowModel);
        return result;
    }

}
