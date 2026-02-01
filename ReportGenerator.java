package facilitymaintenance;

import java.io.PrintWriter;
import java.util.List;

public class ReportGenerator {

    public static void generate(List<WorkOrder> orders) {
        try (PrintWriter pw = new PrintWriter("report.txt")) {
            pw.println("===== FACILITY MAINTENANCE REPORT =====");
            for (WorkOrder w : orders) {
                pw.println("ID: " + w.getId());
                pw.println("Title: " + w.getTitle());
                pw.println("Location: " + w.getLocation());
                pw.println("Technician: " + w.getTechnician());
                pw.println("Status: " + w.getStatus());
                pw.println("---------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
