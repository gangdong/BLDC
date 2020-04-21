/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import com.dong.factory.ConstantFactory;
import com.dong.graphic.GraphicArea;
import com.dong.graphic.GraphicAreaCurrent;
import com.dong.graphic.GraphicAreaSpeed;
import com.dong.graphic.GraphicAreaVoltage;

import com.dong.graphicviewer.GraphicViewerCurrentFrame;
import com.dong.graphicviewer.GraphicViewerSpeedFrame;
import com.dong.graphicviewer.GraphicViewerVoltageFrame;
import com.dong.scrollbar.GraphicScrollBarEventListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;

/**
 * this class extends JPanel class, construct a panel to display the graphic view,
 * this panel is embedded into bottom part of main frame.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class GraphicDisplayPanel extends JPanel {

    private GraphicDisplayPanel() {

        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 479, 1, 0, 480);

        area1 = new GraphicAreaSpeed(scrollBar,12500);
        area2 = new GraphicAreaVoltage(scrollBar,30000);
        area3 = new GraphicAreaCurrent(scrollBar,3000);

        btnRefSpeed = new JButton();
        btnRefSpeed.setBounds(120, 15, 16, 16);
        btnRefSpeed.setBackground(Color.YELLOW);
        btnRefSpeed.setEnabled(false);

        btnMeaSpeed = new JButton();
        btnMeaSpeed.setBounds(210, 15, 16, 16);
        btnMeaSpeed.setBackground(Color.red);
        btnMeaSpeed.setEnabled(false);

        btnUpdate = new JButton();
        btnUpdate.setBounds(880, 330, 32, 32);
        btnUpdate.setFont(new Font("宋体", Font.PLAIN, 12));
        btnUpdate.setBackground(Color.BLACK);

        btnUpdate.setToolTipText("停止更新视图");
        btnUpdate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnUpdate.setFocusable(false);
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pause32.png")));

        lbMeaSpeed = new JLabel("测量转速");
        lbMeaSpeed.setBounds(230, 15, 80, 16);
        lbMeaSpeed.setFont(new Font("黑体", Font.BOLD, 12));
        lbMeaSpeed.setForeground(Color.white);

        lbRefSpeed = new JLabel("参考转速");
        lbRefSpeed.setBounds(140, 15, 80, 16);
        lbRefSpeed.setFont(new Font("黑体", Font.BOLD, 12));
        lbRefSpeed.setForeground(Color.white);
        this.setLayout(null);

        //   graphicShowPane.setBounds(0, 0, 1125, 360);
        scrollBar.setBounds(20, 340, 425, 18);
        scrollBar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        scrollBar.setBackground(Color.BLACK);
        scrollBar.setToolTipText("时间控制进度条");


        JLabel lbVoltage = new JLabel("总线电压");
        lbVoltage.setBounds(530, 15, 80, 16);
        lbVoltage.setFont(new Font("黑体", Font.BOLD, 12));
        lbVoltage.setForeground(Color.white);

        JButton btVoltage = new JButton();
        btVoltage.setBounds(510, 15, 16, 16);
        btVoltage.setBackground(Color.red);
        btVoltage.setEnabled(false);

        JLabel lbDVoltage = new JLabel("D 轴电压");
        lbDVoltage.setBounds(610, 15, 80, 16);
        lbDVoltage.setFont(new Font("黑体", Font.BOLD, 12));
        lbDVoltage.setForeground(Color.white);

        JButton btDVoltage = new JButton();
        btDVoltage.setBounds(590, 15, 16, 16);
        btDVoltage.setBackground(Color.yellow);
        btDVoltage.setEnabled(false);

        JLabel lbQVoltage = new JLabel("Q 轴电压");
        lbQVoltage.setBounds(690, 15, 80, 16);
        lbQVoltage.setFont(new Font("黑体", Font.BOLD, 12));
        lbQVoltage.setForeground(Color.white);

        JButton btQVoltage = new JButton();
        btQVoltage.setBounds(670, 15, 16, 16);
        btQVoltage.setBackground(Color.green);
        btQVoltage.setEnabled(false);


        JLabel lbCurrent1 = new JLabel("D 轴电流");
        lbCurrent1.setBounds(900, 15, 80, 16);
        lbCurrent1.setFont(new Font("黑体", Font.BOLD, 12));
        lbCurrent1.setForeground(Color.white);

        JButton btCurrent1 = new JButton();
        btCurrent1.setBounds(870, 15, 16, 16);
        btCurrent1.setBackground(Color.red);
        btCurrent1.setEnabled(false);

        JLabel lbCurrent2 = new JLabel("Q 轴电流");
        lbCurrent2.setBounds(1000, 15, 80, 16);
        lbCurrent2.setFont(new Font("黑体", Font.BOLD, 12));
        lbCurrent2.setForeground(Color.white);

        JButton btCurrent2 = new JButton();
        btCurrent2.setBounds(970, 15, 16, 16);
        btCurrent2.setBackground(Color.green);
        btCurrent2.setEnabled(false);


        lbPause.setBounds(800, 338, 80, 16);
        lbPause.setFont(new Font("黑体", Font.BOLD, 16));
        lbPause.setForeground(Color.LIGHT_GRAY);

        JLabel lbViewer = new JLabel("放大视图");
        lbViewer.setBounds(950, 338, 80, 16);
        lbViewer.setFont(new Font("黑体", Font.BOLD, 16));
        lbViewer.setForeground(Color.LIGHT_GRAY);

        btnViewer = new JButton();
        btnViewer.setToolTipText("打开视图观测窗口");
        btnViewer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnViewer.setFocusable(false);
        btnViewer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnViewer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnViewer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomFit32.png")));

        btnViewer.setBounds(1040, 330, 32, 32);
        btnViewer.setBackground(Color.BLACK);

        this.setBackground(Color.BLACK);
        this.setForeground(Color.RED);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));

        this.add(scrollBar);
        this.add(btnRefSpeed);
        this.add(lbRefSpeed);
        this.add(btnMeaSpeed);
        this.add(lbMeaSpeed);
        this.add(btnUpdate);
        this.add(lbVoltage);
        this.add(btVoltage);
        this.add(lbDVoltage);
        this.add(btDVoltage);
        this.add(lbQVoltage);
        this.add(btQVoltage);
        this.add(lbCurrent1);
        this.add(btCurrent1);
        this.add(lbCurrent2);
        this.add(btCurrent2);
        this.add(btnViewer);
        this.add(lbPause);
        this.add(lbViewer);

//        this.add(scrollLabel);
        //       scrollLabel.setBounds(20, 30, 100, 10);
        //      scrollLabel.setForeground(Color.red);

        for (int i = 0; i < 11; i++) {
            String str = "", str1 = "", str2 = "";
            int j = 12500 - i * 2500;
            int k = 50 - i * 10;
            int h = 3000 - 600 * i;

            if (i <= 1) {
                str = "  " + String.valueOf(j);
                str1 = "   " + String.valueOf(k);
                str2 = "    " + String.valueOf(h);
            } else if ((i > 1) && (i < 5)) {
                str = "    " + String.valueOf(j);
                str1 = "   " + String.valueOf(k);
                str2 = "    " + String.valueOf(h);
            } else if (i == 5) {
                str = "           " + String.valueOf(j);
                str1 = "     " + String.valueOf(k);
                str2 = "           " + String.valueOf(h);
            } else if ((i > 5) && (i < 9)) {
                str = "  " + String.valueOf(j);
                str1 = "  " + String.valueOf(k);
                str2 = "  " + String.valueOf(h);
            } else if (i >= 9) {
                str = "" + String.valueOf(j);
                str1 = "  " + String.valueOf(k);
                str2 = "  " + String.valueOf(h);
            } else {
            }
            JLabel lbRpm = new JLabel(str + "  rpm");
            JLabel lbV = new JLabel(str1 + "  V");
            JLabel lbmA = new JLabel(str2 + " mA");
            lbRpm.setBounds(5, 42 + 25 * i, 80, 15);
            lbV.setBounds(45 + ConstantFactory.xLength + ConstantFactory.spaceLength, 42 + 25 * i, 80, 15);
            lbmA.setBounds(30 + (ConstantFactory.xLength + ConstantFactory.spaceLength) * 2, 42 + 25 * i, 80, 15);
            lbRpm.setFont(new Font("Arial", Font.BOLD, 12));
            lbV.setFont(new Font("Arial", Font.BOLD, 12));
            lbmA.setFont(new Font("Arial", Font.BOLD, 12));
            lbRpm.setForeground(Color.WHITE);
            lbV.setForeground(Color.WHITE);
            lbmA.setForeground(Color.WHITE);

            //this.add(lbRpm);
            // this.add(lbV);
            //  this.add(lbmA);

        }

        JLabel LbSpeedLogo = new JLabel("SPEED");
        LbSpeedLogo.setFont(new Font("Arial", Font.BOLD, 18));
        LbSpeedLogo.setForeground(Color.WHITE);
        LbSpeedLogo.setBounds(25, 10, 100, 20);
        this.add(LbSpeedLogo);

        JLabel LbVoltageLogo = new JLabel("VOLTAGE");
        LbVoltageLogo.setFont(new Font("Arial", Font.BOLD, 18));
        LbVoltageLogo.setForeground(Color.WHITE);
        LbVoltageLogo.setBounds(50 + ConstantFactory.xLength + ConstantFactory.spaceLength, 10, 100, 20);
        this.add(LbVoltageLogo);

        JLabel LbCurrentLogo = new JLabel("CURRENT");
        LbCurrentLogo.setFont(new Font("Arial", Font.BOLD, 18));
        LbCurrentLogo.setForeground(Color.WHITE);
        LbCurrentLogo.setBounds(40 + (ConstantFactory.xLength + ConstantFactory.spaceLength) * 2, 10, 100, 20);
        this.add(LbCurrentLogo);


 
        setBtn.setBounds(650, 338, 80, 20);
        setBtn.setToolTipText("打开量程设定窗口");
        this.add(setBtn);


        area1.setDataBaseArray(ConstantFactory.speedDataBaseArray);
        area1.setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArray);

        area2.setDataBaseArray(ConstantFactory.voltageDataBaseArray);
        area2.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArray);
        area2.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArray);

        area3.setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArray);
        area3.setQAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArray);


        this.add(area1);
        area1.setLocation(0, 0);
        this.add(area2);
        area2.setLocation(375, 0);
        this.add(area3);
        area3.setLocation(750, 0);

        setEvent();




        //groupItem.

        popupMenu.add(groupItemSpd);
        popupMenu.add(groupItemVol);
        popupMenu.add(groupItemCur);




        // popupMenu.add(speedBtn);
        //popupMenu.add(voltageBtn);
        //popupMenu.add(currentBtn);
    }

    public static GraphicDisplayPanel getInstances() {
        return graphicDisplayPanelInstance;
    }



    public JScrollBar getScrollBar() {
        return scrollBar;
    }

    void setEvent() {

        setBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });

        groupItemSpd.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                groupItemEvent(e);
            }
        });

        groupItemVol.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                groupItemEvent(e);
            }
        });

        groupItemCur.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                groupItemEvent(e);
            }
        });

        scrollBar.addAdjustmentListener(new GraphicScrollBarEventListener(scrollBar,GraphicDisplayPanel.class.getName()));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });

        btnViewer.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });
    }

    public void groupItemEvent(ActionEvent e) {
        if (e.getSource().equals(groupItemSpd)) {
            groupItemVol.setSelected(false);
            groupItemCur.setSelected(false);

            if(ConstantFactory.dialogRun_Current == true){
                GraphicViewerCurrentFrame.getInstances().dispose();
                ConstantFactory.dialogRun_Current = false;
            }
            if(ConstantFactory.dialogRun_Voltage == true){
                GraphicViewerVoltageFrame.getInstances().dispose();
                ConstantFactory.dialogRun_Voltage = false;
            }


            ConstantFactory.dialogRun_Speed = true;
            GraphicViewerSpeedFrame graphicViewerframe = GraphicViewerSpeedFrame.getInstances();

            if (ConstantFactory.stopUpdateView == true) {
                if (graphicViewerframe.getGraphicViewerSpeed().getMag() == 2) {
                    graphicViewerframe.getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedSnapshotArrayViewerX5);
                    graphicViewerframe.getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewerX5);
                } else {
                    graphicViewerframe.getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedSnapshotArrayViewer);
                    graphicViewerframe.getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewer);
                }
            } else {
                if (graphicViewerframe.getGraphicViewerSpeed().getMag() == 2) {
                    graphicViewerframe.getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedDataBaseArrayViewerX5);
                    graphicViewerframe.getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewerX5);
                } else {
                    graphicViewerframe.getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedDataBaseArrayViewer);
                    graphicViewerframe.getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewer);
                }
            }
            graphicViewerframe.show();

        } else if (e.getSource().equals(groupItemVol)) {
            groupItemSpd.setSelected(false);
            groupItemCur.setSelected(false);

            if(ConstantFactory.dialogRun_Speed == true){
                GraphicViewerSpeedFrame.getInstances().dispose();
                ConstantFactory.dialogRun_Speed = false;
            }
            if(ConstantFactory.dialogRun_Current == true){
                GraphicViewerCurrentFrame.getInstances().dispose();
                ConstantFactory.dialogRun_Current = false;
            }
            ConstantFactory.dialogRun_Voltage = true;
            GraphicViewerVoltageFrame graphicViewerVoltageFrame = GraphicViewerVoltageFrame.getInstances();
            if (ConstantFactory.stopUpdateView == true) {
                if (graphicViewerVoltageFrame.getGraphicViewerVoltage().getMag() == 2) {
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewerX5);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewerX5);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewerX5);
                } else {
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewer);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewer);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewer);
                }
            } else {
                if (graphicViewerVoltageFrame.getGraphicViewerVoltage().getMag() == 2) {
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewerX5);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewerX5);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewerX5);
                } else {
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewer);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewer);
                    graphicViewerVoltageFrame.getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewer);
                }
            }
            graphicViewerVoltageFrame.show();
            
        } else if (e.getSource().equals(groupItemCur)) {
            groupItemSpd.setSelected(false);
            groupItemVol.setSelected(false);

            if(ConstantFactory.dialogRun_Speed == true){
                GraphicViewerSpeedFrame.getInstances().dispose();
                ConstantFactory.dialogRun_Speed = false;
            }
            if(ConstantFactory.dialogRun_Voltage == true){
                GraphicViewerVoltageFrame.getInstances().dispose();
                ConstantFactory.dialogRun_Voltage = false;
            }

            ConstantFactory.dialogRun_Current = true;
            GraphicViewerCurrentFrame graphicViewerCurrentFrame = GraphicViewerCurrentFrame.getInstances();

            if (ConstantFactory.stopUpdateView == true) {
                if (graphicViewerCurrentFrame.getGraphicViewerCurrent().getMag() == 2) {
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewerX5);
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewerX5);
                } else {
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewer);
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewer);
                }
            } else {
                if (graphicViewerCurrentFrame.getGraphicViewerCurrent().getMag() == 2) {
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewerX5);
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewerX5);
                } else {
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewer);
                    graphicViewerCurrentFrame.getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewer);
                }
            }
            graphicViewerCurrentFrame.show();

        } else {
        }
    }

    public void buttonEvent(ActionEvent e) {

        if (e.getSource().equals(btnUpdate)) {
            if (ConstantFactory.stopUpdateBtnPressed == true) {
                ConstantFactory.stopUpdateBtnPressed = false;
                lbPause.setText("停止更新");
                btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pause32.png")));
                btnUpdate.setToolTipText("停止更新视图");
                ConstantFactory.stopUpdateView = false;

                area1.setDataBaseArray(ConstantFactory.speedDataBaseArray);
                area1.setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArray);
                area2.setDataBaseArray(ConstantFactory.voltageDataBaseArray);
                area2.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArray);
                area2.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArray);


                area3.setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArray);
                area3.setQAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArray);

                if (ConstantFactory.dialogRun_Speed == true) {
                    if (GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().getMag() == 2) {
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedDataBaseArrayViewerX5);
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewerX5);
                    } else {
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedDataBaseArrayViewer);
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewer);
                    }

                } else if(ConstantFactory.dialogRun_Current==true){

                    if (GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().getMag() == 2) {
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewerX5);
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewerX5);
                    } else {
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewer);
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewer);
                    }
                }
                else if(ConstantFactory.dialogRun_Voltage==true){
                    if (GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().getMag() == 2) {
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewerX5);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewerX5);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewerX5);
                        
                    } else {
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewer);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewer);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewer);
                    }
                }


            } else if (ConstantFactory.stopUpdateBtnPressed == false) {
                ConstantFactory.stopUpdateBtnPressed = true;
                ConstantFactory.stopUpdateView = true;
                lbPause.setText("恢复更新");
                btnUpdate.setToolTipText("恢复更新视图");
                btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go32.png")));

                copyPoint(ConstantFactory.speedDataBaseArray, ConstantFactory.speedSnapshotArray);
                area1.setDataBaseArray(ConstantFactory.speedSnapshotArray);
                copyPoint(ConstantFactory.refSpeedDataBaseArray, ConstantFactory.refSpeedSnapshotArray);
                area1.setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArray);

                copyPoint(ConstantFactory.voltageDataBaseArray, ConstantFactory.voltageSnapshotArray);
                area2.setDataBaseArray(ConstantFactory.voltageSnapshotArray);
                copyPoint(ConstantFactory.dAxisVoltageDataBaseArray, ConstantFactory.dAxisVoltageSnapshotArray);
                area2.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArray);
                copyPoint(ConstantFactory.qAxisVoltageDataBaseArray, ConstantFactory.qAxisVoltageSnapshotArray);
                area2.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArray);

                copyPoint(ConstantFactory.dAxisCurrentDataBaseArray, ConstantFactory.dAxisCurrentSnapshotArray);
                area3.setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArray);
                copyPoint(ConstantFactory.qAxisCurrentDataBaseArray, ConstantFactory.qAxisCurrentSnapshotArray);
                area3.setQAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArray);

                copyPoint(ConstantFactory.speedDataBaseArrayViewerX5, ConstantFactory.speedSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.speedDataBaseArrayViewer, ConstantFactory.speedSnapshotArrayViewer);

                copyPoint(ConstantFactory.refSpeedDataBaseArrayViewerX5, ConstantFactory.refSpeedSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.refSpeedDataBaseArrayViewer, ConstantFactory.refSpeedSnapshotArrayViewer);

                copyPoint(ConstantFactory.dAxisCurrentDataBaseArrayViewerX5, ConstantFactory.dAxisCurrentSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.dAxisCurrentDataBaseArrayViewer, ConstantFactory.dAxisCurrentSnapshotArrayViewer);

                copyPoint(ConstantFactory.qAxisCurrentDataBaseArrayViewerX5, ConstantFactory.qAxisCurrentSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.qAxisCurrentDataBaseArrayViewer, ConstantFactory.qAxisCurrentSnapshotArrayViewer);

                copyPoint(ConstantFactory.voltageDataBaseArrayViewerX5, ConstantFactory.voltageSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.voltageDataBaseArrayViewer, ConstantFactory.voltageSnapshotArrayViewer);

                copyPoint(ConstantFactory.dAxisVoltageDataBaseArrayViewerX5, ConstantFactory.dAxisVoltageSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.dAxisVoltageDataBaseArrayViewer, ConstantFactory.dAxisVoltageSnapshotArrayViewer);

                copyPoint(ConstantFactory.qAxisVoltageDataBaseArrayViewerX5, ConstantFactory.qAxisVoltageSnapshotArrayViewerX5);
                copyPoint(ConstantFactory.qAxisVoltageDataBaseArrayViewer, ConstantFactory.qAxisVoltageSnapshotArrayViewer);


                if (ConstantFactory.dialogRun_Speed == true) {
                    if (GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().getMag() == 2) {
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedSnapshotArrayViewerX5);
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewerX5);
                    } else {
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setDataBaseArray(ConstantFactory.speedSnapshotArrayViewer);
                        GraphicViewerSpeedFrame.getInstances().getGraphicViewerSpeed().setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewer);
                    }
                }
                else if(ConstantFactory.dialogRun_Current == true){
                    if (GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().getMag() == 2) {
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewerX5);
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewerX5);
                    } else {
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewer);
                        GraphicViewerCurrentFrame.getInstances().getGraphicViewerCurrent().setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewer);
                    }
                }
                else if(ConstantFactory.dialogRun_Voltage == true){
                    if (GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().getMag() == 2) {
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewerX5);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewerX5);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewerX5);
                    } else {
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewer);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewer);
                        GraphicViewerVoltageFrame.getInstances().getGraphicViewerVoltage().setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewer);
                    }
                }
            }
        } else if (e.getSource().equals(btnViewer)) {
            popupMenu.show(btnViewer, 0, 0);
        }
        else if(e.getSource().equals(setBtn)){
            SetRangePanel setRangePanel = new SetRangePanel();
            setRangePanel.setModal(true);
            
        }
        else{}
    }

    public GraphicArea getArea1() {
        return area1;
    }

    public GraphicArea getArea2() {
        return area2;
    }

    public GraphicArea getArea3() {
        return area3;
    }

    private void copyPoint(ArrayList pList, ArrayList dList) {

        for (int i = 0; i < pList.size(); i++) {
            Point p = new Point();
            Point nP = new Point();
            p = (Point) (pList.get(i));
            nP.x = p.x;
            nP.y = p.y;
            dList.set(i, nP);
        }
    }

    
    private static GraphicDisplayPanel graphicDisplayPanelInstance = new GraphicDisplayPanel();

    private GraphicAreaSpeed area1;
    private GraphicAreaVoltage area2;
    private GraphicAreaCurrent area3;
    
    private JScrollBar scrollBar;
    private JButton btnRefSpeed, btnMeaSpeed, btnUpdate;
    private JLabel lbRefSpeed, lbMeaSpeed;
    private JButton btnViewer;
    JLabel lbPause = new JLabel("暂停更新");
    JPopupMenu popupMenu = new JPopupMenu();
    JRadioButtonMenuItem groupItemSpd = new JRadioButtonMenuItem("速度", false);
    JRadioButtonMenuItem groupItemVol = new JRadioButtonMenuItem("电压", false);
    JRadioButtonMenuItem groupItemCur = new JRadioButtonMenuItem("电流", false);
    JButton setBtn = new JButton("设定");
}

