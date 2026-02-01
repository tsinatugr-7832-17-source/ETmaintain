package facilitymaintenance;

import java.util.List;

public class Technician extends User {

    public Technician(String name) { super(name); }

    @Override
    public String getRole() { return "Technician"; }

    public void markMyWorkDone(List<WorkOrder> orders) {
        for (WorkOrder w : orders) {
            if (w.getTechnician().equals(getName()) && w.getStatus() != WorkOrder.Status.DONE) {
                w.markDone();
            }
        }
    }
}
