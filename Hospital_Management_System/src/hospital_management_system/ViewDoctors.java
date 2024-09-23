package hospital_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewDoctors extends JFrame implements ActionListener {

    JTable table;
    JButton backButton, exitButton;
    String[] columns = {"Doctor ID", "Name", "Age", "Gender", "Specialization", "Experience", "Languages", "Mobile", "Email", "Monday Schedule", "Wednesday Schedule", "Friday Schedule", "Image Path"};
    String[][] data;

    public ViewDoctors() {
        // Frame settings
        setTitle("View Doctors");
        setLayout(new BorderLayout());

        // Fetch data from the database
        try (Conn conn = new Conn()) {
            String query = "SELECT doctor_id, name, age, gender, specialization, experience, languages, mobile, email, monday_schedule, wednesday_schedule, friday_schedule, image_path FROM doctors";
            PreparedStatement ps = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();

            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();

            data = new String[rows][columns.length];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("doctor_id");
                data[i][1] = rs.getString("name");
                data[i][2] = rs.getString("age");
                data[i][3] = rs.getString("gender");
                data[i][4] = rs.getString("specialization");
                data[i][5] = rs.getString("experience");
                data[i][6] = rs.getString("languages");
                data[i][7] = rs.getString("mobile");
                data[i][8] = rs.getString("email");
                data[i][9] = rs.getString("monday_schedule");
                data[i][10] = rs.getString("wednesday_schedule");
                data[i][11] = rs.getString("friday_schedule");
                data[i][12] = rs.getString("image_path");
                i++;
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from the database.");
        }

        // Create JTable and add to frame
        table = new JTable(data, columns);
        
        // Customizing table header
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(0, 102, 204));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Customizing table cell rendering
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);
        
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Customizing Back button
        backButton = new JButton("Back");
        backButton.setBackground(new Color(0, 153, 76)); // Green color
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        // Customizing Exit button
        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(204, 0, 0)); // Red color
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFocusPainted(false);
        exitButton.setPreferredSize(new Dimension(100, 30));
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(1000, 600);
        setLocation(500, 250);
        getContentPane().setBackground(new Color(240, 240, 240)); // Frame background color
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new AdminDashboard().setVisible(true);
        } else if (ae.getSource() == exitButton) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewDoctors::new);
    }
}
