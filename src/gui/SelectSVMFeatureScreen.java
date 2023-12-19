package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectSVMFeatureScreen extends JFrame{
    private JLabel lblSVM;
    private JLabel lblSelectSVMFeature;
    private ArrayList<JButton> btnSVMFeaturesList;
    private ArrayList<JPanel> pSVMFeaturesList;
    private ArrayList<JLabel> lblSVMFeaturesList;

    public SelectSVMFeatureScreen(){
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

    public void init(){
        JPanel pNorth;
        JPanel pCenter;
        JPanel pSouth;

        pSouth = new JPanel();
        pSouth.setBackground(new Color(184, 188, 116));
        pSouth.setBorder(new EmptyBorder(50, 0, 0, 0));
        pSouth.setLayout(new BorderLayout());
        add(pSouth, BorderLayout.SOUTH);

        pNorth = new JPanel();
        pNorth.setBackground(new Color(184, 188, 116));
        pNorth.setBorder(new EmptyBorder(50, 70, 0,65 ));
        pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));

        /* Add prompt in the North Panel*/
        lblSVM = new JLabel("SPECIAL VENDING MACHINE", SwingConstants.CENTER);
        lblSVM.setFont(new Font("Impact", Font.PLAIN, 20));
        pNorth.add(lblSVM, BorderLayout.CENTER);

        lblSelectSVMFeature = new JLabel("                 PICK A FEATURE", SwingConstants.CENTER);
        pNorth.add(lblSelectSVMFeature);

        add(pNorth, BorderLayout.NORTH);

        pCenter = new JPanel(new GridBagLayout());  // Use GridBagLayout for vertical alignment
        pCenter.setBackground(new Color(184, 188, 116));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.anchor = GridBagConstraints.WEST;

        int vmFeatureCount = 3; // (Exit Included)

        btnSVMFeaturesList = new ArrayList<>(vmFeatureCount);
        lblSVMFeaturesList = new ArrayList<>(vmFeatureCount);

        ArrayList<String> featureName = new ArrayList<>();
        featureName.add("Vending Feature");
        featureName.add("Maintenance Feature");
        featureName.add("Exit");

        ArrayList<String> vmAcr = new ArrayList<>();
        vmAcr.add("1");
        vmAcr.add("2");
        vmAcr.add("3");

        for (int i=0; i< 3; i++){
            btnSVMFeaturesList.add(new JButton(vmAcr.get(i)));
            btnSVMFeaturesList.get(i).setEnabled(false);
            lblSVMFeaturesList.add(new JLabel(featureName.get(i)));

            gbc.gridx = 0;
            gbc.gridy = i;

            gbc1.gridx = 2;
            gbc1.gridy = i;

            pCenter.add(btnSVMFeaturesList.get(i), gbc);

            gbc.gridx = 1;
            pCenter.add(lblSVMFeaturesList.get(i), gbc1);
        }
        add(pCenter, BorderLayout.CENTER);
    }

    public void setActionListener(ActionListener listener) {
        for (JButton jbutton : btnSVMFeaturesList){
            jbutton.addActionListener(listener);
        }
    }

    public void setConfirmEnabled(boolean isEnabled) {

        for (JButton jbutton : btnSVMFeaturesList) {
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
