/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import com.dong.datatransfer.SpeedDataTransfer;
import com.dong.factory.ConstantFactory;
import com.dong.keyevent.KeyListener;
import com.dong.serialcom.ComBean;
import com.dong.serialcom.ComCommandAnalyst;
import com.dong.timer.TaskTimer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Dong Gang
 */
public class ControlPanel extends JPanel {

    private JButton speedUpdateBtn, speedReverseBtn, stopBtn;
    private JLabel motorControl, lbMotorN;
    private JTextField motorN;
    private static ControlPanel controlPanelInstance = new ControlPanel();
    private ComBean serialCom = ComBean.getInstances();
    private ComCommandAnalyst data = new ComCommandAnalyst();
    GraphicDisplayPanel graphicDisplayPanelInstance = GraphicDisplayPanel.getInstances();

    private ControlPanel() {

        initStatus();
        initComponent();


        this.setLayout(null);
        this.add(motorControl);
        this.add(lbMotorN);
        this.add(motorN);
        this.add(speedUpdateBtn);
        this.add(speedReverseBtn);
        this.add(stopBtn);
        setEvent();
    }

    public static ControlPanel getInstances() {
        return controlPanelInstance;
    }

    private void initComponent() {
        motorControl = new JLabel();
        motorControl.setBounds(150, 5, 100, 40);
        motorControl.setText("马达控制");
        motorControl.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, 14));

        lbMotorN = new JLabel("电机转速(转/分):");
        lbMotorN.setSize(120, 20);


        motorN = new JTextField("0");
        motorN.setBackground(Color.WHITE);
        motorN.setHorizontalAlignment(JTextField.CENTER);
        motorN.setFont(new Font("黑体", Font.BOLD, 12));
        motorN.setSize(50, 20);
        //motorN.setToolTipText("输入马达参考转速");
        motorN.setFocusable(true);

        speedUpdateBtn = new JButton("更新");
        speedUpdateBtn.setSize(65, 20);
        //speedUpdateBtn.setToolTipText("更新马达转速为参考转速");

        speedReverseBtn = new JButton("反转");
        speedReverseBtn.setSize(65, 20);
        //speedReverseBtn.setToolTipText("当前参考转速反转");

        stopBtn = new JButton("停止");
        stopBtn.setSize(65, 20);

        lbMotorN.setLocation(8, 45);
        motorN.setLocation(98, 45);
        speedUpdateBtn.setLocation(152, 45);
        speedReverseBtn.setLocation(215, 45);
        stopBtn.setLocation(278, 45);
        //  lbKi.setLocation(230, 40);
        //  Ki.setLocation(250, 40);
        // lbKp.setLocation(370, 40);


        // Kp.setLocation(390, 40);

    }

    private void initStatus() {
        ConstantFactory.motorRun = false;
    }

    private void setEvent() {
        speedUpdateBtn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                motorControlEvent(e);
            }
        });

        speedReverseBtn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                motorControlEvent(e);
            }
        });

        stopBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                motorControlEvent(e);
            }
        });

        motorN.getDocument().addDocumentListener(new MotorNDocumentListener());
        motorN.addKeyListener(new KeyListener());
    }

    private void motorControlEvent(java.awt.event.ActionEvent evt) {

        byte[] speed = new byte[4];

        if (evt.getSource().equals(speedUpdateBtn)) {
            updateSpeed();
        } else if (evt.getSource().equals(speedReverseBtn)) {
            String motorNVal = motorN.getText();
            if (motorNVal.isEmpty()) {
            } else {
                int setSpeed = Integer.parseInt(motorNVal);
                if (setSpeed >= 0) {
                    if (setSpeed < ConstantFactory.speedRange) {
                        int revSpeed = -setSpeed;
                        int speedScreen = new SpeedDataTransfer(revSpeed, ConstantFactory.speedRange).getScreenData();
                        Point p1 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, speedScreen);
                        ConstantFactory.refSpeedDataBaseArray.set(0, p1);
                        ConstantFactory.realRefSpeedArray.set(0, setSpeed);
                        String str = String.valueOf(revSpeed);
                        motorN.setText(str);
                    } else {
                    }
                } else if (setSpeed < 0) {
                    if (setSpeed > -ConstantFactory.speedRange) {
                        int revSpeed = -setSpeed;
                        int speedScreen = new SpeedDataTransfer(revSpeed, ConstantFactory.speedRange).getScreenData();
                        Point p1 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, speedScreen);
                        ConstantFactory.refSpeedDataBaseArray.set(0, p1);
                        ConstantFactory.realRefSpeedArray.set(0, setSpeed);
                        String str = String.valueOf(revSpeed);
                        motorN.setText(str);
                    }
                } else {
                }
            }
        } else if (evt.getSource().equals(stopBtn)) {

            speed[0] = (byte) 0;
            speed[1] = (byte) 0;
            speed[2] = (byte) 0;
            speed[3] = (byte) 0;

            int speedScreen = new SpeedDataTransfer(0, ConstantFactory.speedRange).getScreenData();
            int speedScreenViewer = new SpeedDataTransfer(0, ConstantFactory.speedRange).getScreenDataViewer();
            int speedScreenViewerX5 = new SpeedDataTransfer(0, ConstantFactory.speedRange).getScreenDataViewerX5();

            Point p1 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, speedScreen);
            Point p2 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, speedScreenViewer);
            Point p3 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, speedScreenViewerX5);
            ConstantFactory.refSpeedDataBaseArray.set(0, p1);
            ConstantFactory.refSpeedDataBaseArrayViewer.set(0, p2);
            ConstantFactory.refSpeedDataBaseArrayViewerX5.set(0, p3);
            ConstantFactory.realRefSpeedArray.set(0, 0);
            motorN.setForeground(Color.black);
            ConstantFactory.refSpeed = (int) 0;

            motorN.setText("0");
            data.setMsg_DwnSpeed(speed);
            serialCom.writeCom(data.getMsg_DwnSpeed());


        } else {
        }
    }

    public void updateSpeed() {
        byte[] speed = new byte[4];
        if (serialCom.getIsComTestOk() == true) {
            String motorNVal = motorN.getText();
            if (motorNVal.isEmpty()) {
            } else {
                int setSpeed = Integer.parseInt(motorNVal);

                if (setSpeed >= 0) {
                    if (setSpeed < ConstantFactory.speedRange) {

                        speed[0] = (byte) (setSpeed >> 8);
                        speed[1] = (byte) (setSpeed);
                        speed[2] = (byte) (0);
                        speed[3] = (byte) (0);

                        int speedScreen = new SpeedDataTransfer(setSpeed, ConstantFactory.speedRange).getScreenData();
                        int speedScreenViewer = new SpeedDataTransfer(setSpeed, ConstantFactory.speedRange).getScreenDataViewer();
                        int speedScreenViewerX5 = new SpeedDataTransfer(setSpeed, ConstantFactory.speedRange).getScreenDataViewerX5();

                        Point p1 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, speedScreen);
                        Point p2 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, speedScreenViewer);
                        Point p3 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, speedScreenViewerX5);
                        ConstantFactory.refSpeedDataBaseArray.set(0, p1);
                        ConstantFactory.refSpeedDataBaseArrayViewer.set(0, p2);
                        ConstantFactory.refSpeedDataBaseArrayViewerX5.set(0, p3);
                        ConstantFactory.realRefSpeedArray.set(0, setSpeed);
                        motorN.setForeground(Color.black);
                        ConstantFactory.refSpeed = setSpeed;
                    } else {
                        motorN.setForeground(Color.red);
                    }
                } else {
                    int abs = Math.abs(setSpeed);
                    if (abs < ConstantFactory.speedRange) {


                        speed[0] = (byte) (abs >> 8);
                        speed[1] = (byte) (abs);
                        speed[2] = (byte) (1);
                        speed[3] = (byte) (0);


                        int speedScreen = new SpeedDataTransfer(setSpeed, ConstantFactory.speedRange).getScreenData();
                        int speedScreenViewer = new SpeedDataTransfer(setSpeed, ConstantFactory.speedRange).getScreenDataViewer();
                        int speedScreenViewerX5 = new SpeedDataTransfer(setSpeed, ConstantFactory.speedRange).getScreenDataViewerX5();
                        Point p1 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, speedScreen);
                        Point p2 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, speedScreenViewer);
                        Point p3 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, speedScreenViewerX5);
                        ConstantFactory.refSpeedDataBaseArray.set(0, p1);
                        ConstantFactory.refSpeedDataBaseArrayViewer.set(0, p2);
                        ConstantFactory.refSpeedDataBaseArrayViewerX5.set(0, p3);
                        ConstantFactory.realRefSpeedArray.set(0, setSpeed);
                        motorN.setForeground(Color.black);
                        ConstantFactory.refSpeed = setSpeed;
                    } else {
                        motorN.setForeground(Color.red);
                    }
                }

                data.setMsg_DwnSpeed(speed);
                serialCom.writeCom(data.getMsg_DwnSpeed());

                if (ConstantFactory.timerRun == 0) {
                    ConstantFactory.timer = new Timer();
                    ConstantFactory.timer.schedule(new TaskTimer(), 1000, 250 / 2);
                } else {
                }
            }
        } else {
        }
    }
}

class MotorNDocumentListener implements DocumentListener {

    public void insertUpdate(DocumentEvent e) {
        ConstantFactory.focusOnPI = false;
        ConstantFactory.focusOnSpeed = true;
    }

    public void removeUpdate(DocumentEvent e) {
        ConstantFactory.focusOnPI = false;
        ConstantFactory.focusOnSpeed = true;
    }

    public void changedUpdate(DocumentEvent e) {
    }
}