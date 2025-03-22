import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CacheHome extends JFrame {

    public CacheHome() {
        setTitle("Cache Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Homepage Colors
        Color background = Color.decode("#e3e0d7"); // Main Background
        Color buttonColor = Color.decode("#BE5985"); // Buttons
        Color buttonHoverColor = Color.decode("#EC7FA9"); // Button Hover
        Color textColor = Color.decode("#7D1C4A"); // Text Color

        // Background Color
        getContentPane().setBackground(background);

        JLabel titleLabel = new JLabel("Cache Simulator (Full Associative | MRU)", JLabel.CENTER);
        titleLabel.setFont(new Font("Gill Sans", Font.BOLD, 25));
        titleLabel.setForeground(textColor); // Title text color
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(background);

        JButton startButton = new JButton("Start Simulation");
        styleButton(startButton, buttonColor, buttonHoverColor);

        startButton.addActionListener((ActionEvent e) -> {
            new CacheGUI().setVisible(true);
            dispose();
        });

        JButton instructionButton = new JButton("Instructions");
        styleButton(instructionButton, buttonColor, buttonHoverColor);

        instructionButton.addActionListener((ActionEvent e) -> showInstructions());

        buttonPanel.add(startButton);
        buttonPanel.add(instructionButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button, Color backgroundColor, Color hoverColor) {

        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Gill Sans", Font.BOLD, 20));
        button.setBorder(BorderFactory.createLineBorder(hoverColor));
        button.setFocusPainted(false);
        
        button.setOpaque(true);
        button.setContentAreaFilled(true);
    
        button.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

                button.setBackground(backgroundColor);
            }
        });
    }    

    private void showInstructions() {

        String instructions = "1. Click 'Start Simulation' to begin\n\n"
                + "2. Enter the number of memory blocks when prompted. This will define the memory size for the simulation\n\n"
                + "3. Choose one of the test types: \n"
                + "   - Sequential Test: Simulates accessing memory blocks in increasing order\n"
                + "   - Random Test: Simulates accessing memory blocks in a random order\n"
                + "   - Mid-Repeat Test: Simulates accessing half of the memory, repeating some blocks in the middle, and continuing access to the end\n\n"
                + "4. The application will show the simulation log, displaying whether each memory access resulted in a Cache Hit or Cache Miss\n\n"
                + "5. Analyze Performance Metrics: \n"
                + "   - Hit Rate: Percentage of accesses that were found in cache\n"
                + "   - Miss Rate: Percentage of accesses that were not found in cache\n"
                + "   - Total Access Time: Time taken for all memory accesses\n"
                + "   - Average Access Time: Average time per memory access\n\n"
                + "6. Save Logs: Click 'Save Log' to store the simulation results in a text file\n\n";
        JOptionPane.showMessageDialog(this, instructions, "Instruction", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CacheHome home = new CacheHome();
            home.setVisible(true);
        });
    }
}
