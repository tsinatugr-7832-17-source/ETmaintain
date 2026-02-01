package facilitymaintenance;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class Dashboard extends JFrame {
    public Dashboard(List<WorkOrder> orders) {
        setTitle("Work Orders Dashboard");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Description", "Technician", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (WorkOrder o : orders) {
            model.addRow(new Object[]{o.getId(), o.getDescription(), o.getTechnician(), o.getStatus()});
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }
}
