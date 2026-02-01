package facilitymaintenance;

import java.util.List;

public class Technician extends User {

    public Technician(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        String technician = "Technician";
        return "Technician";
    }

    public void markMyWorkDone(List<WorkOrder> orders) {
        for (WorkOrder order : orders) {
            if (order.getTechnician().equals(name)
                    && order.getStatus() == WorkOrder.Status.PENDING) {
                order.markDone();
            }
        }
    }
}
