/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this class extends JPanel class, construct a panel to display the controller
 * information. this panel is embedded into bottom part of main frame.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class StatusPanel extends JPanel {

    /**
     * display label.
     */
    private JLabel lbBoardStatus;
    /**
     * internal panel
     */
    private JPanel infoPanel, statusPanel;
    /**
     * button to be used as light.
     */
    private JButton btnStCpu, btnStFlash, btnStRam, btnMAlarm, btnCanEn;
    /**
     * the global exculsive instance of status panel.
     */
    private static StatusPanel statusPanelInstance = new StatusPanel();
    /**
     * frequency label.
     */
    private JLabel lbClockFrequency = new JLabel("Clock Frequency:            0MHz");
    /**
     * ram size.
     */
    private JLabel lbRAMSize = new JLabel("RAM Size:                          0KB");
    /**
     * rom size.
     */
    private JLabel lbROMSize = new JLabel("ROM Size:                          0KB");
    /**
     * Can baudrate.
     */
    private JLabel lbCANBaudrate = new JLabel("CAN Baud-rate:                0KHz");
    /**
     * pwm module frequency.
     */
    private JLabel lbPwmMF = new JLabel("PWM Frequency:             0KHz");
    /**
     * sample frequency.
     */
    private JLabel lbSmpleF = new JLabel("Sample Frequency:         0KHz");

    /**
     * constructor method, has priate property for used as signle-instantce mode.
     */
    private StatusPanel() {

        lbBoardStatus = new JLabel("控制器信息");
        lbBoardStatus.setBounds(150, 10, 100, 40);
        lbBoardStatus.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, 14));

        infoPanel = new JPanel();
        // infoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        infoPanel.setBounds(10, 60, 205, 160);

        statusPanel = new JPanel();
        statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusPanel.setBounds(225, 60, 165, 160);


        infoPanel.setLayout(null);
        lbClockFrequency.setBounds(10, 0, 200, 20);
        lbClockFrequency.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lbClockFrequency);


        lbRAMSize.setBounds(10, 25, 200, 20);
        lbRAMSize.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lbRAMSize);


        lbROMSize.setBounds(10, 50, 200, 20);
        lbROMSize.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lbROMSize);



        lbCANBaudrate.setBounds(10, 75, 200, 20);
        lbCANBaudrate.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lbCANBaudrate);


        lbPwmMF.setBounds(10, 100, 200, 20);
        lbPwmMF.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lbPwmMF);


        lbSmpleF.setBounds(10, 125, 200, 20);
        lbSmpleF.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lbSmpleF);


        btnStCpu = new JButton();
        btnStCpu.setBounds(20, 20, 10, 10);
        btnStCpu.setEnabled(false);
        btnStCpu.setBackground(Color.white);

        btnStFlash = new JButton();
        btnStFlash.setBounds(20, 45, 10, 10);
        btnStFlash.setEnabled(false);
        btnStFlash.setBackground(Color.white);

        btnStRam = new JButton();
        btnStRam.setBounds(20, 70, 10, 10);
        btnStRam.setEnabled(false);
        btnStRam.setBackground(Color.white);

        btnMAlarm = new JButton();
        btnMAlarm.setBounds(20, 95, 10, 10);
        btnMAlarm.setEnabled(false);
        btnMAlarm.setBackground(Color.white);

        btnCanEn = new JButton();
        btnCanEn.setBounds(20, 120, 10, 10);
        btnCanEn.setEnabled(false);
        btnCanEn.setBackground(Color.white);


        statusPanel.setLayout(null);
        statusPanel.add(btnStCpu);
        statusPanel.add(btnStFlash);
        statusPanel.add(btnStRam);
        statusPanel.add(btnMAlarm);
        statusPanel.add(btnCanEn);



        JLabel lbStCPU = new JLabel("Self test CPU Ok");
        lbStCPU.setBounds(40, 14, 150, 20);
        lbStCPU.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(lbStCPU);

        JLabel lbStFlash = new JLabel("Self test Flash Ok");
        lbStFlash.setBounds(40, 39, 150, 20);
        lbStFlash.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(lbStFlash);

        JLabel lbStRam = new JLabel("Self test RAM Ok");
        lbStRam.setBounds(40, 64, 150, 20);
        lbStRam.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(lbStRam);

        JLabel lbMAlarm = new JLabel("Motor Alarm");
        lbMAlarm.setBounds(40, 89, 150, 20);
        lbMAlarm.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(lbMAlarm);

        JLabel lbCanEn = new JLabel("CAN Enabled");
        lbCanEn.setBounds(40, 114, 150, 20);
        lbCanEn.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(lbCanEn);


        this.setLayout(null);
        this.add(lbBoardStatus);
        this.add(infoPanel);
        this.add(statusPanel);

        // this.setBackground(Color.WHITE);
    }

    /**
     * get the global exclusive instance of this panel, signle - instance mode.
     * calling this method is the only way to get the instance of this panel.
     */
    public static StatusPanel getInstances() {
        return statusPanelInstance;
    }

    /**
     * return class member.
     */
    public JLabel getLbClockFrequency() {
        return lbClockFrequency;
    }

    /**
     * return class member.
     */
    public JLabel getLbFlashAcc() {
        return lbRAMSize;
    }

    /**
     * return class member.
     */
    public JLabel getLbFlashAva() {
        return lbCANBaudrate;
    }

    /**
     * return class member.
     */
    public JLabel getLbFlashOpp() {
        return lbROMSize;
    }

    /**
     * return class member.
     */
    public JLabel getLbPwmMF() {
        return lbPwmMF;
    }

    /**
     * return class member.
     */
    public JLabel getLbSmpleF() {
        return lbSmpleF;
    }

    /**
     * return class member.
     */
    public JButton getBtnCanEn() {
        return btnCanEn;
    }

    /**
     * return class member.
     */
    public JButton getBtnMAlarm() {
        return btnMAlarm;
    }

    /**
     * return class member.
     */
    public JButton getBtnStCpu() {
        return btnStCpu;
    }

    /**
     * return class member.
     */
    public JButton getBtnStFlash() {
        return btnStFlash;
    }

    /**
     * return class member.
     */
    public JButton getBtnStRam() {
        return btnStRam;
    }

    /**
     * reset the status of label.
     */
    public void resetParameters() {
        lbClockFrequency.setText("Clock Frequency:            0MHz");
        lbRAMSize.setText("RAM Size:                          0KB");
        lbROMSize.setText("ROM Size:                          0KB");
        lbCANBaudrate.setText("CAN Baud-rate:                0KHz");
        lbPwmMF.setText("PWM Frequency:             0KHz");
        lbSmpleF.setText("Sample Frequency:         0KHz");
        btnCanEn.setBackground(Color.white);
        btnStCpu.setBackground(Color.white);
        btnStFlash.setBackground(Color.white);
        btnStRam.setBackground(Color.white);

    }
}
