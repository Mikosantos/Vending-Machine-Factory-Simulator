package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseSVMVendingMethodScreen extends JFrame{
    private JPanel pBoard;
    private ArrayList<JButton> btnSVMFeatures;
    private JTextArea taSVMFeaturesLog;
    private JScrollPane scrollSVMVendingFeaturesLog;
    private ArrayList<JScrollPane> scrollSVMVendingFeaturesDetails;
    public ChooseSVMVendingMethodScreen(){
        super("Vending Machine Factory");

        scrollSVMVendingFeaturesDetails = new ArrayList<>();
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

        pBoard = new JPanelWithBG(new ImageIcon(getClass().getResource("/assets/svm_.png")), 450, 600);
        pBoard.setBackground(new Color(184, 188, 116));
        pBoard.setLayout(new BorderLayout());

        JPanel pLeft = new JPanel();
        pLeft.setPreferredSize(new Dimension(   150, 100));
        pLeft.setBackground(new Color(184, 188, 116));
        pCenter.add(pLeft, BorderLayout.WEST);
        pCenter.add(pBoard, BorderLayout.CENTER);

        //taSVMFeaturesLog
        taSVMFeaturesLog = new JTextArea("Welcome to Vending Machine Simulator!\n\t[Special Vending Machine]\n\nButtons below are the vending features.\nPick one!\n");
        taSVMFeaturesLog.setBorder(new EmptyBorder(10,10,10,10));
        taSVMFeaturesLog.setEditable(false);
        taSVMFeaturesLog.setLineWrap(true);
        taSVMFeaturesLog.setWrapStyleWord(true);
        scrollSVMVendingFeaturesLog = new JScrollPane(taSVMFeaturesLog);
        scrollSVMVendingFeaturesLog.setPreferredSize(new Dimension(400, 100));
        pCenter.add(scrollSVMVendingFeaturesLog);

        add(pCenter, BorderLayout.CENTER);

        pSouth = new JPanel();
        pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.X_AXIS));
        pSouth.setPreferredSize(new Dimension(1200, 120));

        pElements.add(new JPanel());
        pElements.get(0).setLayout(new GridLayout(3,2));

        ArrayList<String> svmVendingAcr = new ArrayList<>();
        svmVendingAcr.add("SEE ITEM/MEAL INVENTORY");
        svmVendingAcr.add("PAY");
        svmVendingAcr.add("MAKE CUSTOM MEAL");
        svmVendingAcr.add("DISPENSE ITEM");
        svmVendingAcr.add("DISPENSE PRE-MADE MEAL");
        svmVendingAcr.add("DISPENSE CUSTOM MADE MEAL");
        svmVendingAcr.add("GET THE CHANGE");
        svmVendingAcr.add("KNOW CALORIE AMOUNT");
        svmVendingAcr.add("EXIT");

        int svmFeaturesCount = 9;
        btnSVMFeatures = new ArrayList<>(svmFeaturesCount);

        for (int i = 0; i < svmFeaturesCount; i++){
            JButton button = new JButton(svmVendingAcr.get(i));
            pElements.get(0).add(button);
            btnSVMFeatures.add(button);
        }

        pSouth.add(pElements.get(0));
        add(pSouth, BorderLayout.SOUTH);


    }
    public void setActionListener(ActionListener listener) {
        for (JButton jbutton : btnSVMFeatures){
            jbutton.addActionListener(listener);
        }
    }
    public void setWindowListener(WindowListener listener) {
        addWindowListener(listener);
    }
    public void setConfirmEnabled(boolean isEnabled) {
        for (JButton jbutton : btnSVMFeatures) {
            jbutton.setEnabled(isEnabled);
        }
    }
    public void addToLog(String text) {
        taSVMFeaturesLog.insert(text, taSVMFeaturesLog.getText().length());
        taSVMFeaturesLog.setCaretPosition(taSVMFeaturesLog.getText().length());
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

