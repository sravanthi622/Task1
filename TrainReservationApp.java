import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrainReservationApp extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;

    JTextField usernameField, passengerField, trainField, cancelNameField;
    JPasswordField passwordField;
    JLabel loginMessage;

    public TrainReservationApp() {
        setTitle("🚆 Train Reservation Portal");
        setSize(550, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(loginPanel(), "Login");
        mainPanel.add(reservationPanel(), "Reservation");
        mainPanel.add(cancellationPanel(), "Cancellation");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    JPanel loginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(236, 240, 241));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel title = new JLabel("Login to Continue", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4, 1, 10, 10));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        loginMessage = new JLabel("", SwingConstants.CENTER);
        loginMessage.setForeground(Color.RED);
        loginMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        form.add(usernameField);
        form.add(passwordField);
        form.add(loginMessage);

        JPanel buttons = new JPanel();
        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Cancel");

        loginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginBtn.setPreferredSize(new Dimension(100, 35));

        exitBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        exitBtn.setPreferredSize(new Dimension(100, 35));

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            if (user.equals("admin") && pass.equals("admin")) {
                cardLayout.show(mainPanel, "Reservation");
            } else {
                loginMessage.setText("❌ Invalid username or password");
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        buttons.add(loginBtn);
        buttons.add(exitBtn);
        form.add(buttons);

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    JPanel reservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 249, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel title = new JLabel("🚆 Train Reservation Form", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 1, 15, 15));
        passengerField = new JTextField();
        passengerField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passengerField.setBorder(BorderFactory.createTitledBorder("Passenger Name"));

        trainField = new JTextField();
        trainField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        trainField.setBorder(BorderFactory.createTitledBorder("Train Name"));

        JPanel buttons = new JPanel();
        JButton reserveBtn = new JButton("Reserve");
        JButton goToCancel = new JButton("Go to Cancellation");

        reserveBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        reserveBtn.setPreferredSize(new Dimension(140, 35));

        goToCancel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        goToCancel.setPreferredSize(new Dimension(160, 35));

        reserveBtn.addActionListener(e -> {
            String name = passengerField.getText();
            String train = trainField.getText();
            if (!name.isEmpty() && !train.isEmpty()) {
                JOptionPane.showMessageDialog(this, "✅ Reserved for " + name + " in " + train);
            } else {
                JOptionPane.showMessageDialog(this, "❗ Please fill in all fields");
            }
        });

        goToCancel.addActionListener(e -> cardLayout.show(mainPanel, "Cancellation"));

        buttons.add(reserveBtn);
        buttons.add(goToCancel);

        form.add(passengerField);
        form.add(trainField);
        form.add(buttons);

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    JPanel cancellationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(253, 242, 233));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel title = new JLabel("❌ Cancellation Form", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 1, 15, 15));
        cancelNameField = new JTextField();
        cancelNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cancelNameField.setBorder(BorderFactory.createTitledBorder("Passenger Name"));

        JPanel buttons = new JPanel();
        JButton cancelBtn = new JButton("Cancel Reservation");
        JButton backBtn = new JButton("Back to Reservation");

        cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cancelBtn.setPreferredSize(new Dimension(170, 35));

        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backBtn.setPreferredSize(new Dimension(170, 35));

        cancelBtn.addActionListener(e -> {
            String name = cancelNameField.getText();
            if (!name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Reservation for " + name + " has been cancelled.");
            } else {
                JOptionPane.showMessageDialog(this, "❗ Please enter the passenger name");
            }
        });

        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "Reservation"));

        buttons.add(cancelBtn);
        buttons.add(backBtn);

        form.add(cancelNameField);
        form.add(buttons);

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        new TrainReservationApp();
    }
}
