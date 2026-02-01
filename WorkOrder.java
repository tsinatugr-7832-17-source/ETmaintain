package facilitymaintenance;

import java.io.Serializable;

public class WorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private static int counter = 1; // auto-ID generator

    private int id;
    private String title;
    private String location;
    private String technician;
    private Status status;

    public enum Status { PENDING, ASSIGNED, DONE }

    public WorkOrder(String title, String location) {
        this.id = counter++;
        this.title = title;
        this.location = location;
        this.technician = "Unassigned";
        this.status = Status.PENDING;
    }

    // GETTERS
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getTechnician() { return technician; }
    public Status getStatus() { return status; }

    // METHODS
    public void assignTechnician(String tech) {
        if (tech != null && !tech.isBlank()) {
            this.technician = tech;
            this.status = Status.ASSIGNED;
        }
    }

    public void markDone() { this.status = Status.DONE; }

    @Override
    public String toString() {
        return id + ": " + title + " - " + location + " - " + status;
    }
}
