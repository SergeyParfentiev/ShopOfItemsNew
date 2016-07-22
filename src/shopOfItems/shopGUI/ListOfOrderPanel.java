package shopOfItems.shopGUI;

import shopOfItems.logic.InterfaceShopOfItems;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ListOfOrderPanel {

    private InterfaceShopOfItems shopOfItems;
    
    public ListOfOrderPanel(InterfaceShopOfItems shopOfItems) {
        this.shopOfItems = shopOfItems;
    }

    public JPanel list() {
        JPanel list = new JPanel();
        String[] columnNames = {"id", "Name", "Cost", "Sold", "Total cost", "Customer", "Date"};
        scrollPane(list, shopOfItems.listOfOrders(), columnNames);
        return list;
    }

    private void scrollPane(JPanel jPanel, Object[][] list, String[] columnNames) {
        jPanel.setLayout(new GridBagLayout());
        JTable getAll = new JTable(list, columnNames);

        getAll.getColumnModel().getColumn(0).setMaxWidth(30);

        for(int i = 1; i < getAll.getColumnCount(); i++) {
            pack(getAll, i);
        }
        DefaultTableCellRenderer r = (DefaultTableCellRenderer) getAll.getDefaultRenderer(String.class);
        r.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane jScrollPane = new JScrollPane(getAll);
        jScrollPane.setPreferredSize(new Dimension(780, 535));

        jPanel.add(jScrollPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private void pack(JTable jTable, int columnIdx) {
        int width = 0;
        for (int row = 0; row < jTable.getRowCount(); row++) {
            TableCellRenderer renderer = jTable.getCellRenderer(row, columnIdx);
            Component comp = jTable.prepareRenderer(renderer, row, columnIdx);
            width = Math.max(comp.getPreferredSize().width, width);
        }
        jTable.getColumnModel().getColumn(columnIdx).setMinWidth(width);
    }
}
