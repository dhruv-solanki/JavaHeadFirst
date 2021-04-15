import javax.swing.*;
import java.rmi.*;
import java.awt.*;
import java.awt.event.*;

public class ServiceBrowser {
    JPanel mainPanel;
    JComboBox serviceList;
    ServiceServer server;

    public void buildGUI() {
        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        Object[] services = getServicesList();
        serviceList = new JComboBox(services);
        frame.getContentPane().add(BorderLayout.NORTH, serviceList);
        serviceList.addActionListener(new MyListListener());

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    void loadService(Object serviceSelection) {
        try {
            Service service = server.getService(serviceSelection);

            mainPanel.removeAll();
            mainPanel.add(svc.getGUIPanel());
            mainPanel.validate();
            mainPanel.repaint();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    Object[] getServicesList() {
        Object obj = null;

        Object[] services = null;

        try {
            obj = Naming.lookup("rmi://127.0.0.1/ServiceServer");
        } catch(Exception e) {
            e.printStackTrace();
        }

        server = (ServiceServer) obj;

        try  {
            services = server.getServicesList();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return services;
    }

    class MyListListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object selection = serviceList.getSelectedItem();

            loadService(selection);
        }
    }

    public static void main(String[] args) {
        new ServiceBrowser().buildGUI();
    }
}