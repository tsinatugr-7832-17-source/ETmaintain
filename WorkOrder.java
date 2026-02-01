package facilitymaintenance;

import java.io.Serializable;

public class WorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------- ENCAPSULATED FIELDS ----------
    private int id;
    private String description;
    private String technician;
    private Status status;

    // ---------- ENUM (Better than String) ----------
    public enum Status {
        PENDING,
        DONE
    }

    // ---------- CONSTRUCTOR ----------
    public WorkOrder(int id, String description) {
        this.id = id;
        this.description = description;
        this.technician = "Unassigned";
        this.status = Status.PENDING;
    }

    // ---------- GETTERS (NO DIRECT FIELD ACCESS) ----------
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnician() {
        return technician;
    }

    public Status getStatus() {
        return status;
    }

    // ---------- BEHAVIOR METHODS ----------
    // Encapsulation: state changes only via methods

    public void assignTechnician(String technician) {
        if (technician != null && !technician.isBlank()) {
            this.technician = technician;
        }
    }

    public void markDone() {
        this.status = Status.DONE;
    }

    // ---------- OPTIONAL (Nice for Dashboard / Report) ----------
    @Override
    public String toString() {
        return "ID: " + id +
                ", Description: " + description +
                ", Technician: " + technician +
                ", Status: " + status;
    }
}
