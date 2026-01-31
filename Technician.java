package com.etmaintain.models;
import java.util.ArrayList;
import java.util.List;
import com.etmaintain.models.WorkOrder;

public class Technician {
    private String name;
    private String skill;
    private List<WorkOrder> assignedOrders;
    public Technician(String name, String skill) {
        this.name = name;
        this.skill = skill;
        this.assignedOrders = new ArrayList<>();
    }
    public String getName() { 
    	return name;
    }
    public void setName(String name) { 
	    this.name = name;
    }
    public String getSkill() {
 	    return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public List<WorkOrder> getAssignedOrders() {
 	    return new ArrayList<>(assignedOrders);
    }

    public void assignOrder(WorkOrder order) {
        assignedOrders.add(order);
    }

    public void removeOrder(WorkOrder order) {
        assignedOrders.remove(order);
    }

    @Override
    public String toString() {
        return "Technician: " + name + " | Skill: " + skill + " | Orders: " + assignedOrders.size();
    }
 }



