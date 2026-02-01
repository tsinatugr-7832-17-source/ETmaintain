package facilitymaintenance;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<WorkOrder> orders = new ArrayList<>();
    private static Admin admin = new Admin("System Admin");

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // ---------- LOAD DATA (IO) ----------
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("workorders.dat"))) {
            orders = (List<WorkOrder>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No saved data found. Starting new.");
        }

        // ---------- PRELOAD SAMPLE DATA ----------
        if (orders.isEmpty()) {
            admin.addWorkOrder(orders, new WorkOrder(1, "Fix broken AC"));
            admin.addWorkOrder(orders, new WorkOrder(2, "Repair leaking pipe"));
            admin.addWorkOrder(orders, new WorkOrder(3, "Replace light bulbs"));

            admin.assignTechnician(orders.get(0), "Alice");
            admin.assignTechnician(orders.get(1), "Bob");
            admin.assignTechnician(orders.get(2), "Charlie");
        }

        // ---------- SWING UI ----------
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Facility Maintenance System");
            frame.setSize(550, 450);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(6, 1, 15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            frame.add(panel);

            JButton addBtn = createButton("Add Work Order");
            JButton assignBtn = createButton("Assign Technician");
            JButton doneBtn = createButton("Technician Mark Done");
            JButton dashboardBtn = createButton("View Dashboard");
            JButton reportBtn = createButton("Generate Report");
            JButton exitBtn = createButton("Exit");

            panel.add(addBtn);
            panel.add(assignBtn);
            panel.add(doneBtn);
            panel.add(dashboardBtn);
            panel.add(reportBtn);
            panel.add(exitBtn);

            // ---------- BUTTON ACTIONS ----------

            // Add Work Order
            addBtn.addActionListener(e -> {
                String desc = JOptionPane.showInputDialog(frame,
                        "Enter Work Order Description:");
                if (desc != null && !desc.isBlank()) {
                    int id = orders.size() + 1;
                    admin.addWorkOrder(orders, new WorkOrder(id, desc));
                    JOptionPane.showMessageDialog(frame, "Work Order Added");
                }
            });

            // Assign Technician
            assignBtn.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(
                            JOptionPane.showInputDialog(frame, "Enter Work Order ID"));
                    String tech =
                            JOptionPane.showInputDialog(frame, "Enter Technician Name");

                    WorkOrder order = orders.stream()
                            .filter(o -> o.getId() == id)
                            .findFirst()
                            .orElse(null);

                    if (order != null) {
                        admin.assignTechnician(order, tech);
                        JOptionPane.showMessageDialog(frame, "Technician Assigned");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Work Order Not Found");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Input");
                }
            });

            // Technician Mark Done (POLYMORPHISM)
            doneBtn.addActionListener(e -> {
                String techName =
                        JOptionPane.showInputDialog(frame, "Enter Technician Name");

                if (techName != null && !techName.isBlank()) {
                    User user = new Technician(techName); // polymorphism
                    ((Technician) user).markMyWorkDone(orders);
                    JOptionPane.showMessageDialog(frame, "Work Orders Updated");
                }
            });

            // Dashboard
            dashboardBtn.addActionListener(e ->
                    new StyledDashboard(orders));

            // Report
            reportBtn.addActionListener(e -> {
                Report report = new Report();
                report.generateReport(orders, "reports/report.txt");
                JOptionPane.showMessageDialog(frame,
                        "Report Generated in reports/report.txt");
            });

            // Exit + Save
            exitBtn.addActionListener(e -> {
                try (ObjectOutputStream oos =
                             new ObjectOutputStream(
                                     new FileOutputStream("workorders.dat"))) {
                    oos.writeObject(orders);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            });

            frame.setVisible(true);
        });
    }

    // ---------- BUTTON STYLE ----------
    private static JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setFocusPainted(false);
        return btn;
    }
}
