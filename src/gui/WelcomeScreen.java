package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    private JButton btnContinue;
    private JLabel lblTitle;
    private JLabel lblCenterText;

    private ImageIcon leftImage;
    private ImageIcon rightImage;
    private JLabel lblLeftImage;
    private JLabel lblRightImage;

    public WelcomeScreen() {
        super("Vending Machine Factory!");

        /* Use Border layout. */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 210);
        setLocationRelativeTo(null);
        setVisible(true);           // The welcome screen is visible right from the start.
        setResizable(false);
        // Call the init() method to initialize components
        init();
    }

    private void init() {
        JPanel pSouth;              // South panel for the buttons
        JPanel pCenter;             // Center panel for the text (main prompt)

        pSouth = new JPanel();      // Constructor for south panel

        /* For south panel, flow was used to position buttons */
        pSouth.setLayout(new FlowLayout());
        pSouth.setBackground(Color.WHITE);

        /* For south panel. a button is created for "CONTINUE" prompt */
        btnContinue = new JButton("CONTINUE");
        pSouth.add(btnContinue);

        /* For south panel. the button was positioned */
        add(pSouth, BorderLayout.SOUTH);

        /* Add LEFT IMAGE*/
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(184, 188, 116));

        Image leftImageScaled;
        leftImage = new ImageIcon(getClass().getResource("/assets/rvm_.png"));
        leftImageScaled = leftImage.getImage().getScaledInstance(60, 100, Image.SCALE_DEFAULT);
        lblLeftImage = new JLabel(new ImageIcon(leftImageScaled));
        /* Add left padding for leftImage */
        lblLeftImage.setBorder(new EmptyBorder(0, 20, 0, 0));
        /* Positioning it overall on Border Layout */
        leftPanel.add(lblLeftImage, BorderLayout.CENTER);
        leftPanel.setBackground(new Color(184, 188, 116));
        add(leftPanel, BorderLayout.WEST);


        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(184, 188, 116));
        /* Add RIGHT IMAGE*/
        Image rightImageScaled;
        rightImage = new ImageIcon(getClass().getResource("/assets/svm_.png"));
        rightImageScaled = rightImage.getImage().getScaledInstance(60, 100, Image.SCALE_DEFAULT);
        lblRightImage = new JLabel(new ImageIcon(rightImageScaled));
        lblRightImage.setBorder(new EmptyBorder(0, 0, 0, 20));
        rightPanel.add(lblRightImage, BorderLayout.CENTER);
        rightPanel.setBackground(new Color(184, 188, 116));
        /* Positioning it overall on Border Layout */
        add(rightPanel, BorderLayout.EAST);


        /* Constructor for CENTER  panel */
        pCenter = new JPanel();
        pCenter.setBackground(new Color(184, 188, 116));
        pCenter.setBorder(new EmptyBorder(50, 0, 50, 0));
        // Border Layout was used for CENTER panel
        pCenter.setLayout(new BorderLayout());
        // Position it on Border Layout
        add(pCenter, BorderLayout.CENTER);

        lblTitle = new JLabel("<html><center>VENDING MACHINE SIMULATOR</center></html>", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Impact", Font.PLAIN, 20));


        pCenter.add(lblTitle, BorderLayout.NORTH);

        /* Add center text prompt. */
        lblCenterText = new JLabel("<html><center><b><i>SELECT TO PROCEED</i></b></center></html>", SwingConstants.CENTER);
        /* Position in the Border layout of the center panel. */
        pCenter.add(lblCenterText, BorderLayout.CENTER);
    }

    public void setActionListener(ActionListener listener) {
        btnContinue.addActionListener(listener);
    }
}


