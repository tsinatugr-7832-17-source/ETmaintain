package facilitymaintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Dashboard extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public Dashboard(List<WorkOrder> workOrders) {
        setTitle("Facility Dashboard");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(
                new String[]{"ID","Title","Location","Technician","Status"},0
        );

        table = new JTable(model);
        loadData(workOrders);

        // Row coloring
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, 4).toString();
                switch (status) {
                    case "PENDING" -> c.setBackground(Color.ORANGE);
                    case "ASSIGNED" -> c.setBackground(Color.CYAN);
                    case "DONE" -> c.setBackground(Color.GREEN);
                    default -> c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton viewBtn = new JButton("View Selected Work Order");
        viewBtn.addActionListener(e -> viewSelected());
        add(viewBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadData(List<WorkOrder> orders) {
        model.setRowCount(0);
        for (WorkOrder w : orders) {
            model.addRow(new Object[]{w.getId(), w.getTitle(), w.getLocation(), w.getTechnician(), w.getStatus()});
        }
    }

    private void viewSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a work order!");
            return;
        }
        JOptionPane.showMessageDialog(this,
                "ID: "+table.getValueAt(row,0)+
                        "\nTitle: "+table.getValueAt(row,1)+
                        "\nLocation: "+table.getValueAt(row,2)+
                        "\nTechnician: "+table.getValueAt(row,3)+
                        "\nStatus: "+table.getValueAt(row,4)
        );
    }
}
