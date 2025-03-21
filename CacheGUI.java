// CacheGUI.java updated for Fully Associative + MRU
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.*;

public class CacheGUI extends JFrame {
    private Cache cache;
    private JTextArea logArea;
    private JLabel cacheStatus;
    private int memorySize;
    private boolean animatedTracing = true;

    public CacheGUI() {
        String input = JOptionPane.showInputDialog(this, "Enter number of memory blocks (minimum 1024):", "Memory Size", JOptionPane.PLAIN_MESSAGE);

        try {
            int sizeInput = Integer.parseInt(input);
            if (sizeInput < 1024) {
                memorySize = 1024;
                JOptionPane.showMessageDialog(this, "Input less than 1024. Defaulting to 1024 memory blocks.");
            } else {
                memorySize = sizeInput;
            }
        } catch (NumberFormatException e) {
            memorySize = 1024;
            JOptionPane.showMessageDialog(this, "Invalid input. Defaulting to 1024 memory blocks.");
        }

        // 🟢 Fully Associative Cache with 32 blocks
        cache = new Cache(32);

        setTitle("Cache Simulator (FA + MRU)");
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
            int address = (int) (Math.random() * memorySize) * 16;
            animateMemoryAccess(address);
        });
        buttonPanel.add(stepByStepButton);

        JButton finalSnapshotButton = new JButton("Final Snapshot");
        finalSnapshotButton.setPreferredSize(new Dimension(150, 30));
        finalSnapshotButton.addActionListener((ActionEvent e) -> {
            int address = (int) (Math.random() * memorySize) * 16;
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
                Thread.sleep(500);
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
        for (int i = 0; i < memorySize * 2; i++) {
            boolean hit = cache.accessMemory(i * 16);
            logTestResult(i * 16, hit);
        }
    }

    private void runRandomTest() {
        logArea.append("\nRunning Random Test...\n");
        Random rand = new Random();
        for (int i = 0; i < memorySize * 4; i++) {
            int address = rand.nextInt(memorySize * 16);
            boolean hit = cache.accessMemory(address);
            logTestResult(address, hit);
        }
    }

    private void runMidRepeatTest() {
        logArea.append("\nRunning Mid-Repeat Test...\n");

        for (int repeat = 0; repeat < 4; repeat++) { // Repeat pattern 4 times
            // First n/2 blocks
            for (int i = 0; i < memorySize / 2; i++) {
                boolean hit = cache.accessMemory(i * 16);
                logTestResult(i * 16, hit);
            }
            // Middle repeat
            for (int r = 0; r < 2; r++) {
                for (int i = 1; i < memorySize / 2; i++) {
                    boolean hit = cache.accessMemory(i * 16);
                    logTestResult(i * 16, hit);
                }
            }
            // Continue up to 2n blocks
            for (int i = memorySize / 2; i < memorySize; i++) {
                boolean hit = cache.accessMemory(i * 16);
                logTestResult(i * 16, hit);
            }
        }
    }

    private void logTestResult(int address, boolean hit) {
        DecimalFormat df = new DecimalFormat("0.00");

        logArea.append("\nAccessed Address: " + address + "\n");
        logArea.append(hit ? "Cache Hit!\n" : "Cache Miss!\n");
        logArea.append("Memory Access Count: " + cache.getMemoryAccessCount() + "\n");
        logArea.append("Hits: " + cache.getHits() + ", Misses: " + cache.getMisses() + "\n");
        logArea.append("Hit Rate: " + df.format(cache.getHitRate() * 100) + "%\n");
        logArea.append("Miss Rate: " + df.format(cache.getMissRate() * 100) + "%\n");
        logArea.append("Total Access Time: " + cache.calculateTotalMemoryAccessTime() + " ns\n");
        logArea.append("Average Access Time: " + df.format(cache.calculateAverageMemoryAccessTime()) + " ns\n\n");
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
