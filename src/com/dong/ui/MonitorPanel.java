/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * monitor panel class,sub-class of JPanel.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class MonitorPanel extends JPanel {

    /** display label */
    private JLabel lbMonitor;
    /**
     * display of the speed.
     */
    private JLabel lbMonitorSpeed;
    /**
     * display of the bus voltage.
     */
    private JLabel lbMonitorBusVoltage;
    /**
     * display of the D-axis voltage.
     */
    private JLabel lbDAxisVol;
    /**
     * display of the Q-axis voltage.
     */
    private JLabel lbQAxisVol;
    /**
     * display of the D-axis current.
     */
    private JLabel lbDAxisCur;
    /**
     * display of the Q-axis current.
     */
    private JLabel lbQAxisCur;

    /**
     * get the global exclusive instance of this panel.
     */
    private static MonitorPanel monitorPanel = new MonitorPanel();


    /**
     * constructor, add component and initialize this panel. the constructor has
     * the private property for used as single - instance mode.
     */
    private MonitorPanel() {

        lbMonitor = new JLabel("状态显示");
        lbMonitor.setBounds(150, 10, 100, 40);
        lbMonitor.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, 14));

        JLabel lbMotorSpeed = new JLabel("Motor Speed(rpm):");
        lbMotorSpeed.setBounds(75, 10, 200, 20);
        lbMotorSpeed.setForeground(Color.WHITE);
        lbMotorSpeed.setFont(new Font("Arial", Font.BOLD, 14));

        lbMonitorSpeed = new JLabel("0");
        lbMonitorSpeed.setBounds(255, 10, 50, 20);
        lbMonitorSpeed.setForeground(Color.WHITE);
        lbMonitorSpeed.setFont(new Font("Arial", Font.BOLD, 14));


        JLabel lbDCBusVoltage = new JLabel("DC Bus Voltage(V):");
        lbDCBusVoltage.setBounds(75, 30, 200, 20);
        lbDCBusVoltage.setForeground(Color.WHITE);
        lbDCBusVoltage.setFont(new Font("Arial", Font.BOLD, 14));

        lbMonitorBusVoltage = new JLabel("0");
        lbMonitorBusVoltage.setBounds(255, 30, 50, 20);
        lbMonitorBusVoltage.setForeground(Color.WHITE);
        lbMonitorBusVoltage.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lbDAxisVoltage = new JLabel("D-Axis Voltage(V):");
        lbDAxisVoltage.setBounds(15, 55, 200, 20);
        lbDAxisVoltage.setForeground(Color.WHITE);
        lbDAxisVoltage.setFont(new Font("Arial", Font.BOLD, 12));

        lbDAxisVol = new JLabel("0");
        lbDAxisVol.setBounds(140, 55, 50, 20);
        lbDAxisVol.setForeground(Color.WHITE);
        lbDAxisVol.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel lbQAxisVoltage = new JLabel("Q-Axis Voltage(V):");
        lbQAxisVoltage.setBounds(175, 55, 200, 20);
        lbQAxisVoltage.setForeground(Color.WHITE);
        lbQAxisVoltage.setFont(new Font("Arial", Font.BOLD, 12));

        lbQAxisVol = new JLabel("0");
        lbQAxisVol.setBounds(300, 55, 50, 20);
        lbQAxisVol.setForeground(Color.WHITE);
        lbQAxisVol.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel lbDAxisCurrent = new JLabel("D-Axis Current(mA):");
        lbDAxisCurrent.setBounds(15, 75, 200, 20);
        lbDAxisCurrent.setForeground(Color.WHITE);
        lbDAxisCurrent.setFont(new Font("Arial", Font.BOLD, 12));

        lbDAxisCur = new JLabel("0");
        lbDAxisCur.setBounds(140, 75, 50, 20);
        lbDAxisCur.setForeground(Color.WHITE);
        lbDAxisCur.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel lbQAxisCurrent = new JLabel("Q-Axis Current(mA):");
        lbQAxisCurrent.setBounds(175, 75, 200, 20);
        lbQAxisCurrent.setForeground(Color.WHITE);
        lbQAxisCurrent.setFont(new Font("Arial", Font.BOLD, 12));

        lbQAxisCur = new JLabel("0");
        lbQAxisCur.setBounds(300, 75, 50, 20);
        lbQAxisCur.setForeground(Color.WHITE);
        lbQAxisCur.setFont(new Font("Arial", Font.BOLD, 12));


        JPanel interPanel = new JPanel();
        interPanel.setBounds(10, 50, 330, 100);
        interPanel.setBackground(Color.BLACK);

        interPanel.setLayout(null);
        interPanel.add(lbMotorSpeed);
        interPanel.add(lbMonitorSpeed);
        interPanel.add(lbDCBusVoltage);
        interPanel.add(lbMonitorBusVoltage);
        interPanel.add(lbDAxisVoltage);
        interPanel.add(lbDAxisVol);
        interPanel.add(lbDAxisVol);
        interPanel.add(lbQAxisVoltage);
        interPanel.add(lbQAxisVol);
        interPanel.add(lbDAxisCurrent);
        interPanel.add(lbDAxisCur);
        interPanel.add(lbQAxisCurrent);
        interPanel.add(lbQAxisCur);

        this.setLayout(null);
        this.add(interPanel);
        this.add(lbMonitor);

    }

    /**
     * return the global exclusive instance of this panel, calling this method is the only
     * way to get this panel.
     */
    public static MonitorPanel getInstances() {
        return monitorPanel;
    }

    /**
     * return the class member lbMonitorSpeed.
     */
    public JLabel getLbMonitorSpeed() {
        return lbMonitorSpeed;
    }

    /**
     * return the class member.
     */
    public JLabel getLbMonitorBusVoltage() {
        return lbMonitorBusVoltage;
    }

    /**
     * return the class member.
     */
    public JLabel getLbDAxisCur() {
        return lbDAxisCur;
    }

    /**
     * return the class member.
     */
    public JLabel getLbQAxisCur() {
        return lbQAxisCur;
    }

    /**
     * return the class member.
     */
    public JLabel getLbDAxisVol() {
        return lbDAxisVol;
    }

    /**
     * return the class member.
     */
    public JLabel getLbQAxisVol() {
        return lbQAxisVol;
    }
    
    
}
