/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import com.dong.factory.ConstantFactory;
import com.dong.graphicviewer.DialogClosedHandle;
import com.dong.graphicviewer.GraphicViewerCurrentFrame;
import com.dong.graphicviewer.GraphicViewerSpeedFrame;
import com.dong.graphicviewer.GraphicViewerVoltageFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * this class extends JDialog class, contructs a dialog used for setting the view
 * range.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class SetRangePanel extends JDialog {

    /**
     * class member, textfield for setting speed range.
     * this member has registered a document listener.
     *
     */
    private JTextField speedRange = new JTextField();
    /**
     * class member, textfield for setting voltage range.
     * this member has registered a document listener.
     */
    private JTextField voltageRange = new JTextField();
    /**
     * class member,textfield for setting current range.
     * this member has registered a document listener.
     */
    private JTextField currentRange = new JTextField();
    /**
     * class member, button for confirming. this button has a action listener.
     * @see #buttonEvent(java.awt.event.ActionEvent)
     */
    private JButton confirmBtn = new JButton("确认");

    /**
     * constructor method. add component and initialize.
     */
    public SetRangePanel() {

        this.setSize(370, 210);
        this.setLocation(450, 325);

        this.setLayout(null);
        this.setForeground(Color.black);
        this.setTitle("Setting Window");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        DialogClosedHandle dialogCloseHandle = new DialogClosedHandle(this);
        this.addWindowListener(dialogCloseHandle);
        this.setModal(true);
        this.setResizable(false);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);


        JLabel speedLb = new JLabel("转速范围(RPM)：");
        speedLb.setBounds(20, 35, 120, 20);
        speedLb.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(speedLb);
        JLabel rpmLb = new JLabel("最小");
        rpmLb.setBounds(125, 10, 80, 20);
        rpmLb.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(rpmLb);
        speedRange.setBounds(190, 35, 55, 20);
        speedRange.setHorizontalAlignment(JTextField.CENTER);
        speedRange.setToolTipText("输入转速观测范围");
        speedRange.getDocument().addDocumentListener(new setRangeDocumentListener(speedRange));
        this.add(speedRange);

        JLabel voltageLb = new JLabel("电压范围(V)：");
        voltageLb.setBounds(20, 70, 120, 20);
        voltageLb.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(voltageLb);
        JLabel mVLb = new JLabel("设定");
        mVLb.setBounds(205, 10, 80, 20);
        mVLb.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(mVLb);
        voltageRange.setBounds(190, 70, 55, 20);
        voltageRange.setHorizontalAlignment(JTextField.CENTER);
        voltageRange.setToolTipText("输入电压观测范围");
        voltageRange.getDocument().addDocumentListener(new setRangeDocumentListener(voltageRange));
        this.add(voltageRange);

        JLabel currentLb = new JLabel("电流范围(mA)：");
        currentLb.setBounds(20, 105, 120, 20);
        currentLb.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(currentLb);
        JLabel mALb = new JLabel("最大");
        mALb.setBounds(285, 10, 80, 20);
        mALb.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(mALb);
        currentRange.setBounds(190, 105, 55, 20);
        currentRange.setHorizontalAlignment(JTextField.CENTER);
        currentRange.setToolTipText("输入电流观测范围");
        currentRange.getDocument().addDocumentListener(new setRangeDocumentListener(currentRange));
        this.add(currentRange);


        JTextField minSpeed = new JTextField("1250");
        minSpeed.setEnabled(false);
        //minSpeed.setEditable(false);
        minSpeed.setBounds(110, 35, 55, 20);
        minSpeed.setHorizontalAlignment(JTextField.CENTER);
        this.add(minSpeed);

        JTextField maxSpeed = new JTextField("125000");
        maxSpeed.setEnabled(false);
        //maxSpeed.setEditable(false);
        maxSpeed.setBounds(270, 35, 55, 20);
        maxSpeed.setHorizontalAlignment(JTextField.CENTER);
        this.add(maxSpeed);


        JTextField minVoltage = new JTextField("1.25");
        minVoltage.setEnabled(false);
        //minVoltage.setEditable(false);
        minVoltage.setBounds(110, 70, 55, 20);
        minVoltage.setHorizontalAlignment(JTextField.CENTER);
        this.add(minVoltage);

        JTextField maxVoltage = new JTextField("125");
        maxVoltage.setEnabled(false);
        //maxVoltage.setEditable(false);
        maxVoltage.setBounds(270, 70, 55, 20);
        maxVoltage.setHorizontalAlignment(JTextField.CENTER);
        this.add(maxVoltage);


        JTextField minCurrent = new JTextField("1250");
        minCurrent.setEnabled(false);
        //minCurrent.setEditable(false);
        minCurrent.setBounds(110, 105, 55, 20);
        minCurrent.setHorizontalAlignment(JTextField.CENTER);
        this.add(minCurrent);

        JTextField maxCurrent = new JTextField("125000");
        maxCurrent.setEnabled(false);
        //maxCurrent.setEditable(false);
        maxCurrent.setBounds(270, 105, 55, 20);
        maxCurrent.setHorizontalAlignment(JTextField.CENTER);
        this.add(maxCurrent);


        confirmBtn.setBounds(145, 145, 80, 20);
        /* add confirmBtn action listener */
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });

        this.add(confirmBtn);
        /* should set visible after add all of the component, or the component will not
        display properly !*/
        this.setVisible(true);
    }

    /**
     * class member confirmBtn action respond event, this method is called and executed when
     * the confirmBtn is pressed.
     * @param  e ActionEvent
     */
    private void buttonEvent(ActionEvent e) {
        String strSpeed, strVoltage, strCurrent;
        int speed, voltage, current;
        strSpeed = speedRange.getText();
        strVoltage = voltageRange.getText();
        strCurrent = currentRange.getText();
        GraphicDisplayPanel graphicDisplayPanel = GraphicDisplayPanel.getInstances();
        GraphicViewerSpeedFrame graphicViewerSpeedFrame = GraphicViewerSpeedFrame.getInstances();
        GraphicViewerCurrentFrame graphicViewerCurrentFrame = GraphicViewerCurrentFrame.getInstances();
        GraphicViewerVoltageFrame graphicViewerVoltageFrame = GraphicViewerVoltageFrame.getInstances();

        if (!strSpeed.isEmpty()) {

            Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
            Matcher matcher = pattern.matcher(strSpeed);

            if (!matcher.find()) {
                JOptionPane.showMessageDialog(rootPane, "请输入正整数值到速度栏", "输入错误", JOptionPane.ERROR_MESSAGE);

            } else {
                speed = Integer.parseInt(strSpeed);
                if (speed >= 1250) {
                    ConstantFactory.speedRange = speed;

                    graphicDisplayPanel.getArea1().setRange(ConstantFactory.speedRange, "rpm", 68, 1);
                    graphicDisplayPanel.getArea1().remakeDataBaseArray();

                    graphicViewerSpeedFrame.getGraphicViewerSpeed().setRange(ConstantFactory.speedRange, "rpm", 68, 1);
                    graphicViewerSpeedFrame.getGraphicViewerSpeed().reMakeDataBaseArray();
                }
            }

        }
        if (!strVoltage.isEmpty()) {
            float floatVoltage = Float.valueOf(strVoltage);
            voltage = (int) (floatVoltage * 1000);
            if (voltage >= 1250) {
                ConstantFactory.voltageRange = voltage;
                graphicDisplayPanel.getArea2().setRange(ConstantFactory.voltageRange, "V", 52, 1000);
                graphicDisplayPanel.getArea2().remakeDataBaseArray();

                graphicViewerVoltageFrame.getGraphicViewerVoltage().setRange(ConstantFactory.voltageRange, "V", 58, 1000);
                graphicViewerVoltageFrame.getGraphicViewerVoltage().reMakeDataBaseArray();
            }

        }
        if (!strCurrent.isEmpty()) {

            Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
            Matcher matcher = pattern.matcher(strCurrent);

            if (!matcher.find()) {
                JOptionPane.showMessageDialog(rootPane, "请输入正整数值到电流栏", "输入错误", JOptionPane.ERROR_MESSAGE);

            } else {
                current = Integer.parseInt(strCurrent);
                if (current >= 1250) {
                    ConstantFactory.currentRange = current;
                    graphicDisplayPanel.getArea3().setRange(ConstantFactory.currentRange, "mA", 60, 1);
                    graphicDisplayPanel.getArea3().remakeDataBaseArray();

                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setRange(ConstantFactory.currentRange, "mA", 60, 1);
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().reMakeDataBaseArray();
                }
            }

        }

        graphicDisplayPanel.repaint();
        graphicViewerSpeedFrame.repaint();
        graphicViewerCurrentFrame.repaint();
        graphicViewerVoltageFrame.repaint();
        this.dispose();
    }

    /**
     * internal class, realize a class which implements DocumentListener. when the
     * contents of JTextField changed, the related method will act.
     * @see javax.swing.event.DocumentListener
     */
    class setRangeDocumentListener implements DocumentListener {

        private JTextField textField;

        public setRangeDocumentListener(JTextField textField) {
            this.textField = textField;
        }

        public void changedUpdate(DocumentEvent e) {
            this.textField.setText(e.getDocument().toString());
        }

        public void insertUpdate(DocumentEvent e) {
        }

        public void removeUpdate(DocumentEvent e) {
        }
    }
}
