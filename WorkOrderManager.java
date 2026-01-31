package com.etmaintain.managers;

import com.etmaintain.models.WorkOrder;
import com.etmaintain.models.WorkOrderStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkOrderManager implements WorkOrderOperations {
    private List<WorkOrder> workOrders = new ArrayList<>();

    @Override
    public void addWorkOrder(WorkOrder order) {
        workOrders.add(order);
    }

    @Override
    public Optional<WorkOrder> getWorkOrderById(int id) {
        for (WorkOrder wo : workOrders) {
            if (wo.getId() == id) {
                return Optional.of(wo);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<WorkOrder> getWorkOrdersByStatus(WorkOrderStatus status) {
        List<WorkOrder> filteredList = new ArrayList<>();
        for (WorkOrder wo : workOrders) {
            if (wo.getStatus() == status) {
                filteredList.add(wo);
            }
        }
        return filteredList;
    }

    @Override
    public List<WorkOrder> getAllWorkOrders() {
        return new ArrayList<>(workOrders);
    }
}