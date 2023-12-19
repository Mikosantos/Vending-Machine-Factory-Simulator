package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class ChooseSVMMaintenanceMethodScreen extends JFrame {

    private JPanel pBoard;
    private ArrayList<JButton> btnSVMMaintenance;
    private JTextArea taRVMMaintenanceLog;
    private JScrollPane scrollVMMaintennanceFeaturesLog;
    private ArrayList<JScrollPane> scrollVMMaintennanceFeaturesDetails;

    public ChooseSVMMaintenanceMethodScreen() {
        super("Vending Machine Factory");

        scrollVMMaintennanceFeaturesDetails = new ArrayList<JScrollPane>();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        init();
    }

    public void init() {
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

        taRVMMaintenanceLog = new JTextArea("Welcome to Vending Machine Simulator!\n\t[Special Vending Machine]\n\nButtons below are the maintenance features.\nPick one!\n");
        taRVMMaintenanceLog.setBorder(new EmptyBorder(10,10,10,10));
        taRVMMaintenanceLog.setEditable(false);
        taRVMMaintenanceLog.setLineWrap(true);
        taRVMMaintenanceLog.setWrapStyleWord(true);
        scrollVMMaintennanceFeaturesLog = new JScrollPane(taRVMMaintenanceLog);
        scrollVMMaintennanceFeaturesLog.setPreferredSize(new Dimension(400, 100));
        pCenter.add(scrollVMMaintennanceFeaturesLog);

        add(pCenter, BorderLayout.CENTER);

        pSouth = new JPanel();
        pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.X_AXIS));
        pSouth.setPreferredSize(new Dimension(1200, 120));

        pElements.add(new JPanel());
        pElements.get(0).setLayout(new GridLayout(3,2));

        ArrayList<String> svmMaintenanceAcr = new ArrayList<>();
        svmMaintenanceAcr.add("RESTOCK ITEMS");
        svmMaintenanceAcr.add("CHANGE ITEM PRICE");
        svmMaintenanceAcr.add("COLLECT PROFIT");
        svmMaintenanceAcr.add("REPLENISH CASH");
        svmMaintenanceAcr.add("ALL TRANSACTION SUMMARY");
        svmMaintenanceAcr.add("TRANSACTION SUMMARY FROM LAST RESTOCKING");
        svmMaintenanceAcr.add("EXIT");

        int svmFeaturesCount = 7;
        btnSVMMaintenance =  new ArrayList<>(svmFeaturesCount);

        for (int i = 0; i < svmFeaturesCount; i++){
            JButton button = new JButton(svmMaintenanceAcr.get(i));
            pElements.get(0).add(button);
            btnSVMMaintenance.add(button);
        }
        pSouth.add(pElements.get(0));
        add(pSouth, BorderLayout.SOUTH);
    }

    public void setActionListener(ActionListener listener){
        for (JButton jbutton : btnSVMMaintenance){
            jbutton.addActionListener(listener);
        }
    }
    public void setWindowListener(WindowListener listener) {
        addWindowListener(listener);
    }
    public void addToLog(String text) {
        taRVMMaintenanceLog.insert(text, taRVMMaintenanceLog.getText().length());
        taRVMMaintenanceLog.setCaretPosition(taRVMMaintenanceLog.getText().length());
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

