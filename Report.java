package facilitymaintenance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Report {
    public void generateReport(List<WorkOrder> orders, String filename) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("Work Order Report\n\n");
            for (WorkOrder o : orders) {
                fw.write("ID: " + o.getId() + ", Desc: " + o.getDescription() +
                        ", Technician: " + o.getTechnician() + ", Status: " + o.getStatus() + "\n");
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
