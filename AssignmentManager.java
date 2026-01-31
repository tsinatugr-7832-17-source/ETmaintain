package com.etmaintain.models;
import java.util.List;
import java.util.ArrayList;
import com.etmaintain.models.Technician;
import com.etmaintain.models.WorkOrder;
public class AssignmentManager {
    private List<Technician> technicians;

    public AssignmentManager(List<Technician> technicians) {
        this.technicians = technicians;
    }
    public Technician assignTechnician(WorkOrder order) {
        for (Technician tech : technicians) {
            if (tech.getSkill().equalsIgnoreCase(order.getIssue())) {
                tech.assignOrder(order);
                order.setAssignedTechnician(tech);
                return tech;
            }
        }
        Technician fallback = technicians.stream().min((t1, t2) -> Integer.compare(t1.getAssignedOrders().size(), t2.getAssignedOrders().size()))
                .orElse(null);

        if (fallback != null) {
            fallback.assignOrder(order);
            order.setAssignedTechnician(fallback);
        }
        return fallback;
    }
    public void reassignTechnician(WorkOrder order, Technician newTech) {
        Technician oldTech = order.getAssignedTechnician();
        if (oldTech != null) oldTech.removeOrder(order);
	    newTech.assignOrder(order);
        order.setAssignedTechnician(newTech);
    }

}



