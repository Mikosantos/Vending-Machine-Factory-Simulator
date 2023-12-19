package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;


public class ChooseRVMMaintenanceMethodScreen extends JFrame{
    private JPanel pBoard;
    private ArrayList<JButton> btnRVMFeatures;
    private JTextArea taRVMFeaturesLog;
    private JScrollPane scrollVMVendingFeaturesLog;
    private ArrayList<JScrollPane> scrollVMVendingFeaturesDetails;
    public ChooseRVMMaintenanceMethodScreen() {
        super("Vending Machine Factory");

        scrollVMVendingFeaturesDetails = new ArrayList<JScrollPane>();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        init();
    }

    public void init(){
        JPanel pCenter;
        JPanel pSouth;
        ArrayList<JPanel> pElements = new ArrayList<>();

        pCenter = new JPanel();
        pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.X_AXIS));

        pBoard = new JPanelWithBG(new ImageIcon(getClass().getResource("/assets/rvm_.png")), 450, 600);
        pBoard.setBackground(new Color(184, 188, 116));
        pBoard.setLayout(new BorderLayout());

        JPanel pLeft = new JPanel();
        pLeft.setPreferredSize(new Dimension(   150, 100));
        pLeft.setBackground(new Color(184, 188, 116));
        pCenter.add(pLeft, BorderLayout.WEST);
        pCenter.add(pBoard, BorderLayout.CENTER);

        taRVMFeaturesLog = new JTextArea("Welcome to Vending Machine Simulator!\n\t[Regular Vending Machine]\n\nButtons below are the maintenance features.\nPick one!\n");
        taRVMFeaturesLog.setBorder(new EmptyBorder(10,10,10,10));
        taRVMFeaturesLog.setEditable(false);
        taRVMFeaturesLog.setLineWrap(true);
        taRVMFeaturesLog.setWrapStyleWord(true);
        scrollVMVendingFeaturesLog = new JScrollPane(taRVMFeaturesLog);
        scrollVMVendingFeaturesLog.setPreferredSize(new Dimension(400, 100));
        pCenter.add(scrollVMVendingFeaturesLog);

        add(pCenter, BorderLayout.CENTER);

        pSouth = new JPanel();
        pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.X_AXIS));
        pSouth.setPreferredSize(new Dimension(1200, 120));

        pElements.add(new JPanel());
        pElements.get(0).setLayout(new GridLayout(3,2));

        ArrayList<String> rvmMaintenanceAcr = new ArrayList<>();
        rvmMaintenanceAcr.add("RESTOCK ITEMS");
        rvmMaintenanceAcr.add("CHANGE ITEM PRICE");
        rvmMaintenanceAcr.add("COLLECT PROFIT");
        rvmMaintenanceAcr.add("REPLENISH CASH");
        rvmMaintenanceAcr.add("ALL TRANSACTION SUMMARY");
        rvmMaintenanceAcr.add("TRANSACTION SUMMARY FROM LAST RESTOCKING");
        rvmMaintenanceAcr.add("EXIT");
        rvmMaintenanceAcr.add(" ");


        int vmFeaturesCount = 8;
        btnRVMFeatures =  new ArrayList<>(vmFeaturesCount);

        for (int i = 0; i < vmFeaturesCount; i++){
            JButton button = new JButton(rvmMaintenanceAcr.get(i));
            pElements.get(0).add(button);
            btnRVMFeatures.add(button);
        }
        // Excess button for even button count
        //btnRVMFeatures.get(7).setEnabled(false);

        pSouth.add(pElements.get(0));
        add(pSouth, BorderLayout.SOUTH);

    }

    public void setActionListener(ActionListener listener){
        for (JButton jbutton : btnRVMFeatures){
            jbutton.addActionListener(listener);
        }
    }

    public void setWindowListener(WindowListener listener) {
        addWindowListener(listener);
    }

    public void setConfirmEnabled(boolean isEnabled) {
        for (JButton jbutton : btnRVMFeatures) {
            jbutton.setEnabled(isEnabled);
        }
    }
    public void addToLog(String text) {
        taRVMFeaturesLog.insert(text, taRVMFeaturesLog.getText().length());
        taRVMFeaturesLog.setCaretPosition(taRVMFeaturesLog.getText().length());
    }

    public void showErrorMessage(String message) {
        JFrame errorMsg = new JFrame("Error");
        errorMsg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorMsg.setSize(250, 100);

        JLabel errorLbl = new JLabel(message);
        errorLbl.setHorizontalAlignment(JLabel.CENTER);
        errorLbl.setVerticalAlignment(JLabel.CENTER);
        errorMsg.add(errorLbl);
        errorMsg.setVisible(true);
    }
}
