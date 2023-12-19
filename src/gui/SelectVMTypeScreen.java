package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectVMTypeScreen extends JFrame{

    private JLabel lblSelectVMType;
    private ArrayList<JButton> btnVMTypeList;
    private ArrayList<JPanel> pVMTypeList;
    private ArrayList<JLabel> lblVMTypeList;


    public SelectVMTypeScreen(){
        super("Vending Machine Factory");
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(375, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        init();
        setConfirmEnabled(true);
    }
    private void init(){
        JPanel pNorth;
        JPanel pCenter;

        pNorth = new JPanel();
        pNorth.setBackground(new Color(184, 188, 116));
        pNorth.setBorder(new EmptyBorder(50, 0, 20, 0));
        pNorth.setLayout(new BorderLayout());

        /* Add prompt in the North Panel*/
        lblSelectVMType = new JLabel("SELECT YOUR PREFERRED VM TYPE", SwingConstants.CENTER);
        lblSelectVMType.setFont(new Font("IMPACT",  Font.PLAIN, 15));
        pNorth.add(lblSelectVMType, BorderLayout.CENTER);

        add(pNorth, BorderLayout.NORTH);

        pCenter = new JPanel();
        pCenter.setBackground(new Color(184, 188, 116));
        /*Box Layout was used for  center panel  with components displayed from top to bottom. */
        pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));

        /* ArrayLists for the components of the center panel */
        int vmTypeCount = 2;
        btnVMTypeList = new ArrayList<>(vmTypeCount);
        pVMTypeList = new ArrayList<>(vmTypeCount);
        lblVMTypeList = new ArrayList<>(vmTypeCount);

        ArrayList<String> vmName = new ArrayList<>();
        vmName.add("Regular");
        vmName.add("Special");

        ArrayList<String> vmAcr = new ArrayList<>();
        vmAcr.add("RVM");
        vmAcr.add("SVM");

        for (int i=0; i< 2; i++){

            pVMTypeList.add(new JPanel());
            pVMTypeList.get(i).setBackground(new Color(184, 188, 116));

            /* Flow layout was used for each panel for the buttons and labels (VM Types). */
            pVMTypeList.get(i).setLayout(new FlowLayout());
            pVMTypeList.get(i).setMaximumSize(new Dimension(400, 30));

            btnVMTypeList.add(new JButton(vmAcr.get(i)));
            btnVMTypeList.get(i).setEnabled(false);
            lblVMTypeList.add(new JLabel(vmName.get(i) + " Vending Machine"));

            pVMTypeList.get(i).add(btnVMTypeList.get(i));
            pVMTypeList.get(i).add(lblVMTypeList.get(i));

            pCenter.add(pVMTypeList.get(i));
        }
        add(pCenter, BorderLayout.CENTER);
    }

    public void setActionListener(ActionListener listener) {
        for (JButton jbutton : btnVMTypeList){
            jbutton.addActionListener(listener);
        }
    }

    public void setConfirmEnabled(boolean isEnabled) {

        for (JButton jbutton : btnVMTypeList) {
            jbutton.setEnabled(isEnabled);
        }

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
