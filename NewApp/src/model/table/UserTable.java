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
import model.bean.User;
import model.dao.UserDAO;

/**
 *
 * @author Minh_PC
 */
public class UserTable extends AbstractTableModel {

    private List<User> allItem;
    private final String[] arrCols = {"ID", "User Name", "Full Name", "Active  "};
    private final JTable table;
    private UserDAO userDAO = new UserDAO();

    public UserTable(JTable tbMain) {
        this.table = tbMain;
        allItem = new ArrayList<>();
        /*allItem.add(new User(1, "Admin", "123456", "Adminstrator", true));
        allItem.add(new User(2, "NguoiDung", "123456", "Người Dùng", false));
        allItem.add(new User(3, "LeNa", "123456", "Lê Na", true));
        allItem.add(new User(4, "KhacHang", "123456", "Khách Hàng", false));
        allItem.add(new User(22, "NguyenA", "123456", "Nguyến A", false));
         */
        allItem = userDAO.getList();
    }

    public List<User> getEmployee() {
        return allItem;
    }

    public void loadTable() {
        table.setModel(this);
        cssForTable();
    }

    private void cssForTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(270);
        table.getColumnModel().getColumn(3).setPreferredWidth(180);
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
        User objUser = allItem.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = objUser.getIdUser();
                break;

            case 1:
                value = objUser.getUserName();
                break;

            case 2:
                value = objUser.getFullName();
                break;

            case 3:
                value = objUser.isKichHoat();
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
        if (columnIndex == 3) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    public int addItem(User objCat) {
        int last_id = 1;
        last_id = userDAO.addItem(objCat);
        objCat.setIdUser(last_id);
        allItem.add(objCat);
        //userDAO.addItem(objCat);
        this.fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
        return last_id;
    }

    public int delItem(int row, User ojbUser) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        //Category ojbCategory;
        userDAO.delItem(ojbUser);
        allItem.remove(rowModel);
        fireTableDataChanged();
        return result;
    }

    public int EditItem(int row, User ojbUser) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        userDAO.EditItem(ojbUser);
        allItem.set(rowModel, ojbUser);
        System.out.println(rowModel);
        fireTableRowsUpdated(rowModel, rowModel);

        // chon lai dong moi sua
        table.setRowSelectionInterval(rowModel, rowModel);
        return result;
    }

    public User checklogin(String username, String pass) {
        return userDAO.checklogin(username, pass);
    }

    
}
