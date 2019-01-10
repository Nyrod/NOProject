package no.gui;

import no.algorithm.HalsteadMetrics;
import no.algorithm.RunCCSM;
import no.utlis.FileReader;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;

public class AppFrame extends  JFrame{

    private String absoluteFilePath;
    private HalsteadMetrics halsteadMetrics;

    private JButton openFileChooserButton;
    private JTextPane codeTextPanel;
    private JLabel filePath;
    private JPanel mainPanel;
    private JTextPane resultTextPanel;
    private JFileChooser fileChooser;

    public AppFrame() {
        halsteadMetrics = new HalsteadMetrics();
        setTitle("Program wyznaczający miary Halsteada");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        initEvents();
    }

    private void initComponents() {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }

    private void initEvents() {
        openFileChooserButton.addActionListener(e -> openFile());
    }


    private void openFileChooser() {
        int retValue = fileChooser.showOpenDialog(this);

        if(retValue == JFileChooser.APPROVE_OPTION) {
            absoluteFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath.setText(fileChooser.getSelectedFile().getName());
        } else {
            absoluteFilePath = null;
        }
    }

    private void openFile() {
        halsteadMetrics = new HalsteadMetrics();
        openFileChooser();
        if(absoluteFilePath != null) {
            try {
                codeTextPanel.setText(FileReader.readFile(absoluteFilePath));
                new Thread(() -> {
                    try {
                        halsteadMetrics.calculateHalsteadMetrics(absoluteFilePath);
                    resultTextPanel.setText(halsteadMetrics.toString());
                    System.out.println(halsteadMetrics);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                RunCCSM.run(absoluteFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
