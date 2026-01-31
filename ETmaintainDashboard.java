package com.ETmaintain;

import java.util.ArrayList;
import java.util.List;

// Dashboard class to manage system overview
class Dashboard {
    private final List<String> notifications;
    private int totalWorkOrders;
    private int completedWorkOrders;

    public Dashboard() {
        this.notifications = new ArrayList<>();
        this.totalWorkOrders = 0;
        this.completedWorkOrders = 0;
    }

    // Add a notification
    public void addNotification(String message) {
        notifications.add(message);
    }

    // Display all notifications
    public void showNotifications() {
        System.out.println("=== Dashboard Notifications ===");
        if (notifications.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            for (String note : notifications) {
                System.out.println("- " + note);
            }
        }
    }

    // Update work order stats
    public void updateWorkOrders(int total, int completed) {
        this.totalWorkOrders = total;
        this.completedWorkOrders = completed;
    }

    // Show dashboard summary
    public void showSummary() {
        System.out.println("=== Dashboard Summary ===");
        System.out.println("Total Work Orders: " + totalWorkOrders);
        System.out.println("Completed Work Orders: " + completedWorkOrders);
        System.out.println("Pending Work Orders: " + (totalWorkOrders - completedWorkOrders));
    }
}
