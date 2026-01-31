package com.etmaintain.models;

public class WorkOrder {

    private int id;
    private String issue;
    private String facility;
    private WorkOrderStatus status;
    private WorkOrderPriority priority;
    private int technicianId;          // Optional: can keep numeric ID
    private Technician assignedTechnician; // New: actual Technician object

    // Constructor for new work orders (unassigned)
    public WorkOrder(int id, String issue, String facility, WorkOrderPriority priority) {
        this.id = id;
        this.issue = issue;
        this.facility = facility;
        this.priority = priority;
        this.status = WorkOrderStatus.PENDING;
        this.technicianId = -1;  // not assigned
        this.assignedTechnician = null;
    }

    // Full constructor (assigned work order)
    public WorkOrder(int id, String issue, String facility,
                     WorkOrderStatus status,
                     WorkOrderPriority priority,
                     Technician assignedTechnician) {
        this.id = id;
        this.issue = issue;
        this.facility = facility;
        this.status = status;
        this.priority = priority;
        setAssignedTechnician(assignedTechnician); // ensures technicianId and status are consistent
    }
//getters retrieve the values private variables

    public int getId() {
        return id;
    }

    public String getIssue() {
        return issue;
    }

    public String getFacility() {
        return facility;
    }

    public WorkOrderStatus getStatus() {
        return status;
    }

    public WorkOrderPriority getPriority() {
        return priority;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public Technician getAssignedTechnician() {
        return assignedTechnician;
    }

// setters to update the values and add validation
    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public void setStatus(WorkOrderStatus status) {
        this.status = status;
    }

    public void setPriority(WorkOrderPriority priority) {
        this.priority = priority;
    }

    /**
     * Assign a technician to this work order.
     * Automatically updates technicianId and status.
     */
    public void setAssignedTechnician(Technician technician) {
        this.assignedTechnician = technician;
        if (technician != null) {
            this.technicianId = technician.hashCode(); // or use a proper ID field in Technician
            this.status = WorkOrderStatus.ASSIGNED;
        } else {
            this.technicianId = -1;
            this.status = WorkOrderStatus.PENDING;
        }
    }

    /**
     * Unassign the technician from this work order.
     */
    public void unassignTechnician() {
        this.assignedTechnician = null;
        this.technicianId = -1;
        this.status = WorkOrderStatus.PENDING;
    }

    /**
     * Check if this work order is completed.
     */
    public boolean isCompleted() {
        return status == WorkOrderStatus.COMPLETED;
    }

    @Override
    public String toString() {
        return "WorkOrder ID: " + id +
                ", Issue: " + issue +
                ", Facility: " + facility +
                ", Priority: " + priority +
                ", Status: " + status +
                ", Technician ID: " + technicianId +
                ", Assigned Technician: " + (assignedTechnician != null ? assignedTechnician.getName() : "None");
    }
}
