package com.etmaintain.managers;

import com.etmaintain.models.WorkOrder;
import com.etmaintain.models.WorkOrderStatus;
import java.util.List;
import java.util.Optional;

public interface WorkOrderOperations {
    void addWorkOrder(WorkOrder order);
    Optional<WorkOrder> getWorkOrderById(int id);
    List<WorkOrder> getWorkOrdersByStatus(WorkOrderStatus status);
    List<WorkOrder> getAllWorkOrders();
}