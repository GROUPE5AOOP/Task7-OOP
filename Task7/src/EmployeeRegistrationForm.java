import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class EmployeeRegistrationForm extends JFrame {
    private JTextField txtFullName, txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbDepartment;
    private JDateChooser dateChooser;
    private JTree orgTree;
    private JButton btnSubmit, btnClear;

    public EmployeeRegistrationForm() {
        setTitle("Employee Registration System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(600, 550);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("EMPLOYEE REGISTRATION FORM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitle, gbc);
        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;

        // Full Name
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Full Name:"), gbc);
        txtFullName = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtFullName, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Email Address:"), gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Password:"), gbc);
        txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Department:"), gbc);
        String[] departments = {"IT", "Finance", "HR", "Marketing"};
        cmbDepartment = new JComboBox<>(departments);
        gbc.gridx = 1;
        panel.add(cmbDepartment, gbc);

        // Date of Birth
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Date of Birth:"), gbc);
        dateChooser = new JDateChooser();
        gbc.gridx = 1;
        panel.add(dateChooser, gbc);

        // Organization Structure (JTree)
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Organization Structure:"), gbc);
        orgTree = new JTree(createOrgStructure());
        JScrollPane treeScroll = new JScrollPane(orgTree);
        treeScroll.setPreferredSize(new Dimension(250, 100));
        gbc.gridx = 1;
        panel.add(treeScroll, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        btnSubmit = new JButton("Submit");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel);
        setVisible(true);

        // Button actions
        btnSubmit.addActionListener(e -> handleSubmit());
        btnClear.addActionListener(e -> clearForm());
    }

    private DefaultMutableTreeNode createOrgStructure() {
        DefaultMutableTreeNode company = new DefaultMutableTreeNode("Company");
        DefaultMutableTreeNode it = new DefaultMutableTreeNode("IT Department");
        it.add(new DefaultMutableTreeNode("Developers"));
        it.add(new DefaultMutableTreeNode("Support"));

        DefaultMutableTreeNode finance = new DefaultMutableTreeNode("Finance Department");
        finance.add(new DefaultMutableTreeNode("Accounts"));
        finance.add(new DefaultMutableTreeNode("Auditors"));

        DefaultMutableTreeNode hr = new DefaultMutableTreeNode("HR Department");
        hr.add(new DefaultMutableTreeNode("Recruitment"));
        hr.add(new DefaultMutableTreeNode("Training"));

        DefaultMutableTreeNode marketing = new DefaultMutableTreeNode("Marketing Department");
        marketing.add(new DefaultMutableTreeNode("Digital Marketing"));
        marketing.add(new DefaultMutableTreeNode("Field Agents"));

        company.add(it);
        company.add(finance);
        company.add(hr);
        company.add(marketing);
        return company;
    }

    private void handleSubmit() {
        String name = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
        String dept = (String) cmbDepartment.getSelectedItem();
        java.util.Date dob = dateChooser.getDate();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || dob == null) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(name, email, dept, dob);

        JOptionPane.showMessageDialog(this,
                "Employee Registered Successfully!\n\n" +
                "Full Name: " + employee.getFullName() +
                "\nEmail: " + employee.getEmail() +
                "\nDepartment: " + employee.getDepartment() +
                "\nDate of Birth: " + employee.getFormattedDob() +
                "\nPassword: ******",
                "Registration Summary",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        txtFullName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        cmbDepartment.setSelectedIndex(0);
        dateChooser.setDate(null);
    }
}
