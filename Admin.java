package facilitymaintenance;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    public Admin(String name) { super(name); }

    @Override
    public String getRole() { return "Admin"; }

    public void addWorkOrder(List<WorkOrder> orders, WorkOrder order) {
        orders.add(order);
    }

    public void assignTechnician(WorkOrder order, String techName) {
        order.assignTechnician(techName);
    }

    public static class Main {

        public static void main(String[] args) {
            List<WorkOrder> workOrders = loadData();

            // Role selection
            String[] roles = {"Admin","Technician"};
            String role = (String) JOptionPane.showInputDialog(null,"Select Role","Login",
                    JOptionPane.PLAIN_MESSAGE,null,roles,roles[0]);
            if(role==null) return;

            String name = JOptionPane.showInputDialog("Enter Name:");
            if(name==null||name.isBlank()) return;

            Admin admin=null;
            Technician tech=null;
            User user;

            if(role.equals("Admin")) {
                admin = new Admin(name);
                user = admin;
            } else {
                tech = new Technician(name);
                user = tech;
            }

            JOptionPane.showMessageDialog(null,"Welcome "+user.getRole()+": "+name);

            // Sample data
            if(workOrders.isEmpty() && admin!=null) {
                admin.addWorkOrder(workOrders,new WorkOrder("Fix AC","Room 101"));
                admin.addWorkOrder(workOrders,new WorkOrder("Repair Light","Lab 3"));
                admin.addWorkOrder(workOrders,new WorkOrder("Water Leak","Bathroom A"));
            }

            // Make final for lambda
            final Admin finalAdmin=admin;
            final Technician finalTech=tech;
            final List<WorkOrder> finalOrders=workOrders;

            SwingUtilities.invokeLater(()->{
                JFrame frame=new JFrame("ETmaintain");
                frame.setSize(550,450);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                JPanel panel=new JPanel(new GridLayout(6,1,15,15));
                panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                frame.add(panel);

                JButton addBtn=createButton("Add Work Order",Color.BLUE);
                JButton assignBtn=createButton("Assign Technician",Color.MAGENTA);
                JButton doneBtn=createButton("Mark My Work Done",Color.GREEN);
                JButton dashBtn=createButton("View Dashboard",Color.ORANGE);
                JButton reportBtn=createButton("Generate Report",Color.CYAN);
                JButton exitBtn=createButton("Exit",Color.RED);

                panel.add(addBtn);
                panel.add(assignBtn);
                panel.add(doneBtn);
                panel.add(dashBtn);
                panel.add(reportBtn);
                panel.add(exitBtn);

                addBtn.addActionListener(e->{
                    if(finalAdmin==null){ JOptionPane.showMessageDialog(frame,"Only Admin can add work orders!"); return; }
                    String title=JOptionPane.showInputDialog(frame,"Work Order Title:");
                    String loc=JOptionPane.showInputDialog(frame,"Location:");
                    if(title!=null&&!title.isBlank() && loc!=null&&!loc.isBlank()){
                        finalAdmin.addWorkOrder(finalOrders,new WorkOrder(title,loc));
                        JOptionPane.showMessageDialog(frame,"Work Order Added!");
                    }
                });

                assignBtn.addActionListener(e->{
                    if(finalAdmin==null){ JOptionPane.showMessageDialog(frame,"Only Admin can assign technicians!"); return; }
                    if(finalOrders.isEmpty()){ JOptionPane.showMessageDialog(frame,"No Work Orders available."); return; }
                    String[] ids= finalOrders.stream().map(w->w.getId()+"").toArray(String[]::new);
                    String selected=(String)JOptionPane.showInputDialog(frame,"Select Work Order ID:","Assign Technician",JOptionPane.PLAIN_MESSAGE,null,ids,ids[0]);
                    if(selected==null) return;
                    int id=Integer.parseInt(selected);
                    WorkOrder wo= finalOrders.stream().filter(w->w.getId()==id).findFirst().orElse(null);
                    if(wo==null) return;
                    String techName=JOptionPane.showInputDialog(frame,"Enter Technician Name:");
                    if(techName!=null&&!techName.isBlank()){
                        finalAdmin.assignTechnician(wo,techName);
                        JOptionPane.showMessageDialog(frame,"Technician Assigned!");
                    }
                });

                doneBtn.addActionListener(e->{
                    if(finalTech==null){ JOptionPane.showMessageDialog(frame,"Only Technicians can mark work done!"); return; }
                    finalTech.markMyWorkDone(finalOrders);
                    JOptionPane.showMessageDialog(frame,"Work Orders Updated!");
                });

                dashBtn.addActionListener(e->new Dashboard(finalOrders));

                reportBtn.addActionListener(e->{
                    ReportGenerator.generate(finalOrders);
                    JOptionPane.showMessageDialog(frame,"Report Generated: report.txt");
                });

                exitBtn.addActionListener(e->{
                    saveData(finalOrders);
                    frame.dispose();
                });

                frame.setVisible(true);
            });
        }

        private static JButton createButton(String text, Color color){
            JButton btn=new JButton(text);
            btn.setFont(new Font("Arial",Font.BOLD,16));
            btn.setBackground(color);
            btn.setForeground(Color.WHITE);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            return btn;
        }

        private static List<WorkOrder> loadData(){
            try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("workorders.dat"))){
                return (List<WorkOrder>) ois.readObject();
            } catch (Exception e){ return new ArrayList<>(); }
        }

        private static void saveData(List<WorkOrder> orders){
            try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("workorders.dat"))){
                oos.writeObject(orders);
            } catch (Exception e){ e.printStackTrace(); }
        }
    }
}
