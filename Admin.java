package facilitymaintenance;

import java.util.List;

public class Admin extends User {

    public Admin(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public void addWorkOrder(List<WorkOrder> orders, WorkOrder order) {
        orders.add(order);
    }

    public void assignTechnician(WorkOrder order, String technician) {
        order.assignTechnician(technician);
    }
}
