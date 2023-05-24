package guipackage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(String name) {
        super(name);
    }

    public void initializeFrame(JPanel nextPanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400,750);
        JButton start = new JButton("Begin");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFrame(nextPanel);
            }
        });
        
        JLabel welcomLabel = new JLabel("Welcome to Travel Estimator", SwingConstants.CENTER);
        welcomLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        ImageIcon img1 = new ImageIcon("./Pictures/logo.png");
        JLabel bgImg = new JLabel(img1);
        
        add(start,BorderLayout.PAGE_END);
        add(bgImg,BorderLayout.CENTER);
        add(welcomLabel,BorderLayout.PAGE_START);
        setVisible(true);
    }

    public void resetFrame(JPanel newPanel) {
        getContentPane().removeAll();
        repaint();
        add(newPanel);
        setVisible(true);
    }
}