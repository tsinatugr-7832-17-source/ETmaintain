package com.ETmaintain;

import java.util.ArrayList;
import java.util.List;

// Report class to store and display reports
public class Report {
    private final List<String> workOrderReports;

    public Report() {
        this.workOrderReports = new ArrayList<>();
    }

    // Add a new report
    public void addReport(String report) {
        workOrderReports.add(report);
    }

    // Display all reports
    public void showReports() {
        System.out.println("=== Work Order Reports ===");
        if (workOrderReports.isEmpty()) {
            System.out.println("No reports available.");
        } else {
            for (String r : workOrderReports) {
                System.out.println("- " + r);
            }
        }
    }

    // Get the number of reports
    public int getReportCount() {
        return workOrderReports.size();
    }
}
