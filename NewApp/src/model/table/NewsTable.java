/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.bean.News;
import model.dao.NewsDAO;

/**
 *
 * @author Minh_PC
 */
public class NewsTable extends AbstractTableModel {

    private List<News> allItem;
    private  String[] arrCols = {"ID Tin", "Tên Tin", "Danh Mục", "Mô Tả", "Ngày Đăng"};
    private  JTable table;
    private NewsDAO newsDAO = new NewsDAO();

    public NewsTable(JTable tbMain) {
        this.table = tbMain;
        allItem = new ArrayList<>();
        /*allItem.add(new User(1, "Admin", "123456", "Adminstrator", true));
        allItem.add(new User(2, "NguoiDung", "123456", "Người Dùng", false));
        allItem.add(new User(3, "LeNa", "123456", "Lê Na", true));
        allItem.add(new User(4, "KhacHang", "123456", "Khách Hàng", false));
        allItem.add(new User(22, "NguyenA", "123456", "Nguyến A", false));
         */
        allItem = newsDAO.getList();
    }

    public List<News> getEmployee() {
        return allItem;
    }

    public void loadTable() {
        table.setModel(this);
        cssForTable();
    }

    private void cssForTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(210);
        table.getColumnModel().getColumn(4).setPreferredWidth(180);
        table.getTableHeader().setPreferredSize(new Dimension(0, 32));
        table.setRowHeight(28);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        //sset backgroud  cho kieu boolea
        ((JComponent) table.getDefaultRenderer(Boolean.class)).setOpaque(true);

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
        News objUser = allItem.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = objUser.getId();
                break;

            case 1:
                value = objUser.getName();
                break;

            case 2:
                value = objUser.getCname();
                break;

            case 3:
                value = objUser.getDetail_text();
                break;
            case 4:
                value = objUser.getDate_create();
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
        /*if (columnIndex == 3) {
            return Boolean.class;
        }
         */

        return super.getColumnClass(columnIndex);
    }

    public int delItem(int row, News ojbUser) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        //Category ojbCategory;
        newsDAO.delItem(ojbUser);
        allItem.remove(rowModel);
        fireTableDataChanged();
        return result;
    }

    public int EditItem(int row, News ojbNews) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        
       
        //ojbUser.setId();
        
        allItem.set(rowModel, ojbNews);
        newsDAO.EditItem(ojbNews);
       // System.out.println(rowModel);
        fireTableRowsUpdated(rowModel, rowModel);
        // chon lai dong moi sua
        table.setRowSelectionInterval(rowModel, rowModel);
        return result;
    }

    public News getNewFormRow(int row) {
        int rowModel = table.convertRowIndexToModel(row);
        return allItem.get(rowModel);
    }

    public int addItem(News objnews) {
        int last_id = 1;
        last_id = newsDAO.addItem(objnews);
        objnews.setId(last_id);
        allItem.add(objnews);
        // newsDAO.addItem(objCat);
        this.fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
        return last_id;
    }

}
