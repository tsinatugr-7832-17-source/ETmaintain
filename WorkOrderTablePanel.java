package com.etmaintain.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.etmaintain.managers.WorkOrderManager;
import com.etmaintain.models.WorkOrder;
import com.etmaintain.models.Technician;

public class WorkOrderTablePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private WorkOrderManager workOrderManager;

    public WorkOrderTablePanel(WorkOrderManager workOrderManager) {
        this.workOrderManager = workOrderManager;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Issue", "Status", "Assigned Technician"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // table is read-only
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(25);

        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);

        List<WorkOrder> orders = workOrderManager.getAllWorkOrders();
        for (WorkOrder order : orders) {
            Technician tech = order.getAssignedTechnician();
            tableModel.addRow(new Object[]{
                    order.getId(),
                    order.getIssue(),
                    order.getStatus(),
                    tech != null ? tech.getName() : "Not Assigned"
            });
        }
    }

    public JTable getTable() {
        return table;
    }
}
