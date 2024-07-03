import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

class WelcomeGUI extends JFrame {
    JLabel welcome;
    JButton userButton, adminButton, exitButton;

    public WelcomeGUI(String title) {
        super(title);
        setLayout(null);
        setComponents();
        setSize(850, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminButton.addActionListener(new ButtonActionListener());
        userButton.addActionListener(new ButtonActionListener());
        exitButton.addActionListener(new ExitButtonListener());
    }

    private void setComponents() {
        // Define the buttons
        userButton = new JButton("USER");
        adminButton = new JButton("ADMIN");
        exitButton = new JButton("Exit");

        // Set bounds and font for the buttons
        userButton.setBounds(250, 150, 150, 100);
        userButton.setFont(new Font("", Font.BOLD, 24));
        add(userButton);

        adminButton.setBounds(450, 150, 150, 100);
        adminButton.setFont(new Font("", Font.BOLD, 24));
        add(adminButton);

        exitButton.setBounds(350, 280, 150, 50);
        exitButton.setFont(new Font("", Font.BOLD, 18));
        add(exitButton);

        // Set button background and foreground colors
        userButton.setBackground(Color.DARK_GRAY);
        adminButton.setBackground(Color.DARK_GRAY);
        exitButton.setBackground(Color.DARK_GRAY);

        userButton.setForeground(Color.WHITE);
        adminButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        // Create and position the label for "BANKING SYSTEM"
        welcome = new JLabel("BANKING SYSTEM", SwingConstants.CENTER);
        welcome.setForeground(Color.BLACK);
        welcome.setFont(new Font("Arial", Font.BOLD, 50));
        welcome.setBounds(100, 50, 650, 50);
        add(welcome);

        // Set the background image
        ImageIcon bImage = new ImageIcon(ClassLoader.getSystemResource("etienne-martin-2_K82gx9Uk8-unsplash.jpg"));
        Image bImage2 = bImage.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon bImage3 = new ImageIcon(bImage2);
        JLabel backgroundImage = new JLabel(bImage3);
        backgroundImage.setBounds(0, 0, 850, 480);
        add(backgroundImage);
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == adminButton) {
                    new GUIAdminLogin();
                    dispose();
                } else if (e.getSource() == userButton) {
                    new UserLogin();
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame aboutUs = new JFrame("About Us");
            JPanel panel = new JPanel(new GridLayout(0, 1));

            JLabel heading = new JLabel("About Us");
            heading.setFont(new Font("Arial", Font.BOLD, 18));
            heading.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(heading);

            JLabel label1 = new JLabel("Talal Atiq - FA22-BDS-041");
            JLabel label2 = new JLabel("Shahab Hassan - FA22-BDS-052");
            JLabel label3 = new JLabel("Hamza Naeem - FA22-BDS-025");
            JLabel label4 = new JLabel("Essa Zeeshan - FA22-BDS-023");

            label1.setFont(new Font("Arial", Font.BOLD, 18));
            label1.setBounds(100, 50, 650, 50);

            label2.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setBounds(100, 50, 650, 50);

            label2.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setBounds(100, 50, 650, 50);

            label3.setFont(new Font("Arial", Font.BOLD, 18));
            label3.setBounds(100, 50, 650, 50);

            label4.setFont(new Font("Arial", Font.BOLD, 18));
            label4.setBounds(100, 50, 650, 50);

            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);

            aboutUs.add(panel);
            aboutUs.setSize(300, 200);
            aboutUs.setLocationRelativeTo(null);
            aboutUs.setVisible(true);

            Timer timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    aboutUs.dispose();
                    dispose();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public static void main(String[] args) {
        new WelcomeGUI("Welcome Screen");
    }
}
