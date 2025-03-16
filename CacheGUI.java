// CacheGUI.java
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class CacheGUI extends JFrame {
    private Cache cache;
    private JTextArea logArea;
    private JLabel cacheStatus;
    private static final int MEMORY_SIZE = 1024;
    private boolean animatedTracing = true;
    
    public CacheGUI() {
        cache = new Cache(8, 4);
        setTitle("Cache Simulator");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);
        
        cacheStatus = new JLabel("Mode: Select Access Type");
        cacheStatus.setFont(new Font("Arial", Font.BOLD, 16));
        add(cacheStatus, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));

        JButton stepByStepButton = new JButton("Step-by-Step Tracing");
        stepByStepButton.setPreferredSize(new Dimension(150, 30));
        stepByStepButton.addActionListener((ActionEvent e) -> {
            int address = (int) (Math.random() * MEMORY_SIZE) * 16;
            animateMemoryAccess(address);
        });
        buttonPanel.add(stepByStepButton);
        
        JButton finalSnapshotButton = new JButton("Final Snapshot");
        finalSnapshotButton.setPreferredSize(new Dimension(150, 30));
        finalSnapshotButton.addActionListener((ActionEvent e) -> {
            int address = (int) (Math.random() * MEMORY_SIZE) * 16;
            accessMemoryInstantly(address);
        });
        buttonPanel.add(finalSnapshotButton);
        
        JButton seqButton = new JButton("Sequential Test");
        seqButton.setPreferredSize(new Dimension(150, 30));
        seqButton.addActionListener((ActionEvent e) -> runSequentialTest());
        buttonPanel.add(seqButton);
        
        JButton randButton = new JButton("Random Test");
        randButton.setPreferredSize(new Dimension(150, 30));
        randButton.addActionListener((ActionEvent e) -> runRandomTest());
        buttonPanel.add(randButton);
        
        JButton midRepeatButton = new JButton("Mid-Repeat Test");
        midRepeatButton.setPreferredSize(new Dimension(150, 30));
        midRepeatButton.addActionListener((ActionEvent e) -> runMidRepeatTest());
        buttonPanel.add(midRepeatButton);
        
        JButton saveLogButton = new JButton("Save Log");
        saveLogButton.setPreferredSize(new Dimension(150, 30));
        saveLogButton.addActionListener(e -> saveLog());
        buttonPanel.add(saveLogButton);
        
        add(buttonPanel, BorderLayout.EAST);
    }
    
    private void animateMemoryAccess(int address) {
        new Thread(() -> {
            SwingUtilities.invokeLater(() -> cacheStatus.setText("Accessing Address: " + address));
            boolean hit = cache.accessMemory(address);
    
            try {
                Thread.sleep(500); // Simulate delay for animation
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    
            SwingUtilities.invokeLater(() -> {
                logTestResult(address, hit);
                cacheStatus.setText("Cache Status: Ready");
            });
        }).start();
    }
    
    private void accessMemoryInstantly(int address) {
        boolean hit = cache.accessMemory(address);
        logTestResult(address, hit);
        cacheStatus.setText("Cache Status: Ready");
    }
    
    private void runSequentialTest() {
        logArea.append("\nRunning Sequential Test...\n");
        for (int i = 0; i < MEMORY_SIZE * 2; i++) {
            boolean hit = cache.accessMemory(i * 16);
            logTestResult(i * 16, hit);
        }
    }
    
    private void runRandomTest() {
        logArea.append("\nRunning Random Test...\n");
        Random rand = new Random();
        for (int i = 0; i < MEMORY_SIZE * 4; i++) {
            int address = rand.nextInt(MEMORY_SIZE * 16);
            boolean hit = cache.accessMemory(address);
            logTestResult(address, hit);
        }
    }
    
    private void runMidRepeatTest() {
        logArea.append("\nRunning Mid-Repeat Test...\n");
    
        for (int repeat = 0; repeat < 4; repeat++) { // Repeat the entire pattern 4 times
            // Access first n blocks
            for (int i = 0; i < MEMORY_SIZE / 2; i++) {
                boolean hit = cache.accessMemory(i * 16);
                logTestResult(i * 16, hit);
            }
            //Repeat the middle n-1 blocks **twice**
            for (int r = 0; r < 2; r++) { // Repeat twice
                for (int i = 1; i < MEMORY_SIZE / 2; i++) { // Start from 1, not 0
                    boolean hit = cache.accessMemory(i * 16);
                    logTestResult(i * 16, hit);
                }
            }
            //Continue up to 2n blocks
            for (int i = MEMORY_SIZE / 2; i < MEMORY_SIZE; i++) {
                boolean hit = cache.accessMemory(i * 16);
                logTestResult(i * 16, hit);
            }
        }
    }
    
    
    private void logTestResult(int address, boolean hit) {
        logArea.append("\nAccessed Address: " + address + "\n");
        logArea.append(hit ? "Cache Hit!\n" : "Cache Miss!\n");
        logArea.append("Hits: " + cache.getHits() + ", Misses: " + cache.getMisses() + "\n");
        logArea.append("Total Access Time: " + cache.getTotalAccessTime() + " ns\n");
        logArea.append("Average Access Time: " + cache.getAverageAccessTime() + " ns\n\n");
    }
    
    private void saveLog() {
        try (FileWriter writer = new FileWriter("cache_log.txt")) {
            writer.write(logArea.getText());
            JOptionPane.showMessageDialog(this, "Log saved to cache_log.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving log");
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CacheGUI gui = new CacheGUI();
            gui.setVisible(true);
        });
    }
}
