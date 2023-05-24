package travelpackage;
import javax.swing.*;

import java.awt.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.*;
import guipackage.*;

public class Group {
    private JPanel groupInfoScreen;
    private double totalCash;
    private int size;
    private Garage garage;

    public Group(String imgDirectory, MainFrame mainFrame, JPanel nextPanel) {
        ImageIcon img = new ImageIcon(imgDirectory);
        JLabel imgPanel = new JLabel(img,SwingConstants.CENTER);
        imgPanel.setBounds(0, 0, 300, 300);

        JPanel walletInfo = new JPanel();
        JLabel walletPromt = new JLabel("How much money do you have? $");
        walletPromt.setFont(new Font("Arial", Font.ITALIC, 30));
        JTextField walletField = new JTextField(10);
        walletField.setBounds(0, 0, 100, 50);
        walletInfo.add(walletPromt);
        walletInfo.add(walletField);

        JPanel groupInfo = new JPanel();
        JLabel groupPrompt = new JLabel("How many people will travel? ");
        groupPrompt.setFont(new Font("Arial", Font.ITALIC, 30));
        JTextField groupField = new JTextField(10);
        groupInfo.add(groupPrompt);
        groupInfo.add(groupField);

        JPanel combineBoth = new JPanel();
        combineBoth.setLayout(new BoxLayout(combineBoth, BoxLayout.Y_AXIS));
        combineBoth.add(walletInfo);
        combineBoth.add(groupInfo);

        JButton submit = new JButton("Submit");
        submit.setPreferredSize(new DimensionUIResource(100, 50));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    totalCash = Double.parseDouble(walletField.getText());
                    size = Integer.parseInt(groupField.getText());
                    mainFrame.resetFrame(nextPanel);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid answer entered", "Insane error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        groupInfoScreen = new JPanel(new BorderLayout());
        groupInfoScreen.add(imgPanel,BorderLayout.PAGE_START);
        groupInfoScreen.add(combineBoth, BorderLayout.CENTER);
        groupInfoScreen.add(submit, BorderLayout.PAGE_END);
    }

    public Garage initializeAndGetGarage(int distance) {
        garage = new Garage(distance, size);
        return garage;
    }

    public double checkWallet() {
        return totalCash;
    }

    public JPanel getJPanel() {
        return groupInfoScreen;
    }

    public int getGroupSize() {
        return size;
    }
}