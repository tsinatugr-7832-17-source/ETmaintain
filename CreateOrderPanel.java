package com.etmaintain.ui;

import javax.swing.*;
import java.awt.*;

import com.etmaintain.managers.WorkOrderManager;
import com.etmaintain.models.WorkOrder;
import com.etmaintain.models.WorkOrderStatus;

public class CreateOrderPanel extends JPanel {

    private JTextField issueField;
    private JButton createButton;

    private WorkOrderManager workOrderManager;
    private WorkOrderTablePanel tablePanel;

    public CreateOrderPanel(WorkOrderManager manager, WorkOrderTablePanel tablePanel) {
        this.workOrderManager = manager;
        this.tablePanel = tablePanel;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel issueLabel = new JLabel("Issue:");
        issueField = new JTextField(15);
        createButton = new JButton("Create Work Order");

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(issueLabel, gbc);

        gbc.gridx = 1;
        add(issueField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(createButton, gbc);

        createButton.addActionListener(e -> createWorkOrder());
    }

    private void createWorkOrder() {
        String issue = issueField.getText().trim();

        if (issue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Issue cannot be empty");
            return;
        }

        WorkOrder order = new WorkOrder(issue);
        order.setStatus(WorkOrderStatus.PENDING);

        workOrderManager.addWorkOrder(order);
        tablePanel.refreshTable();

        issueField.setText("");
    }
}
