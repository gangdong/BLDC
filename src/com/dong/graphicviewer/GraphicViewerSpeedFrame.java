/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphicviewer;

import com.dong.factory.ConstantFactory;
import com.dong.scrollbar.GraphicScrollBarEventListener;
import com.dong.ui.GraphicDisplayPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Dong Gang
 */
public class GraphicViewerSpeedFrame extends JDialog {

    private JScrollBar graphicViewerScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 479, 1, 0, 480);
    private GraphicViewerSpeed graphicViewerSpeed = new GraphicViewerSpeed(graphicViewerScrollBar, 1);
    private JButton graphToolBarZoomIn, graphToolBarZoomOut, graphToolBarZoomFit;
    private static GraphicViewerSpeedFrame graphicViewerFrame = new GraphicViewerSpeedFrame();
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public GraphicViewerSpeed getGraphicViewerSpeed() {
        return graphicViewerSpeed;
    }

    private GraphicViewerSpeedFrame() {

        this.setSize(900, 660);
        this.setLocation(100, 100);
        this.setLayout(null);
        this.setForeground(Color.black);
        this.setModal(false);
        this.setTitle("Speed Viewer");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        DialogClosedHandle dialogCloseHandle = new DialogClosedHandle(this);
        this.addWindowListener(dialogCloseHandle);
        this.setResizable(false);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        //this.setVisible(true);

        /* top panel */
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        JLabel lbMag = new JLabel("放大视图");
        lbMag.setBounds(100, 20, 80, 18);
        lbMag.setFont(new Font("黑体", Font.BOLD, 16));
        lbMag.setForeground(Color.LIGHT_GRAY);
        topPanel.add(lbMag);

        graphToolBarZoomIn = new JButton();
        graphToolBarZoomIn.setToolTipText("放大");
        graphToolBarZoomIn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        graphToolBarZoomIn.setFocusable(false);
        graphToolBarZoomIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        graphToolBarZoomIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        graphToolBarZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomIn32.png")));
        graphToolBarZoomIn.setBackground(Color.black);
        graphToolBarZoomIn.setBounds(200, 10, 32, 32);
        graphToolBarZoomIn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });

        topPanel.add(graphToolBarZoomIn);

        JLabel lbRed = new JLabel("缩小视图");
        lbRed.setBounds(300, 20, 80, 18);
        lbRed.setFont(new Font("黑体", Font.BOLD, 16));
        lbRed.setForeground(Color.LIGHT_GRAY);
        topPanel.add(lbRed);
        graphToolBarZoomOut = new JButton();
        graphToolBarZoomOut.setToolTipText("缩小");
        graphToolBarZoomOut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        graphToolBarZoomOut.setFocusable(false);
        graphToolBarZoomOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        graphToolBarZoomOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        graphToolBarZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomOut32.png")));
        graphToolBarZoomOut.setBackground(Color.black);
        graphToolBarZoomOut.setBounds(400, 10, 32, 32);
        graphToolBarZoomOut.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });
        topPanel.add(graphToolBarZoomOut);

        JLabel lbFit = new JLabel("合适比例");
        lbFit.setBounds(500, 20, 80, 18);
        lbFit.setFont(new Font("黑体", Font.BOLD, 16));
        lbFit.setForeground(Color.LIGHT_GRAY);
        //topPanel.add(lbFit);

        graphToolBarZoomFit = new JButton();
        graphToolBarZoomFit.setToolTipText("合适比例");
        graphToolBarZoomFit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        graphToolBarZoomFit.setFocusable(false);
        graphToolBarZoomFit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        graphToolBarZoomFit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        graphToolBarZoomFit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomFit32.png")));
        graphToolBarZoomFit.setBackground(Color.black);
        graphToolBarZoomFit.setBounds(600, 10, 32, 32);
        //topPanel.add(graphToolBarZoomFit);

        topPanel.setBounds(0, 0, 900, 45);
        topPanel.setBackground(Color.black);
        this.add(topPanel);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBounds(0, 45, 900, 540);
        scrollPane.setBounds(8, 0, 878, 540);
        scrollPane.setVisible(true);
        middlePanel.add(scrollPane);
        scrollPane.setViewportView(graphicViewerSpeed);

        if (ConstantFactory.stopUpdateView == true) {
            graphicViewerSpeed.setDataBaseArray(ConstantFactory.speedSnapshotArrayViewer);
            graphicViewerSpeed.setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewer);
        } else {
            graphicViewerSpeed.setDataBaseArray(ConstantFactory.speedDataBaseArrayViewer);
            graphicViewerSpeed.setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewer);
        }

        middlePanel.setBackground(Color.black);
        this.add(middlePanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0, 585, 900, 60);
        bottomPanel.setBackground(Color.black);

        JLabel lbRef = new JLabel("参考转速");

        lbRef.setBounds(480, 10, 80, 16);
        lbRef.setFont(new Font("黑体", Font.BOLD, 15));
        lbRef.setForeground(Color.white);

        JButton btRefVoltage = new JButton();
        btRefVoltage.setBounds(580, 10, 16, 16);
        btRefVoltage.setBackground(Color.yellow);
        btRefVoltage.setEnabled(false);

        graphicViewerScrollBar.setBounds(8, 10, 425, 20);
        graphicViewerScrollBar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        graphicViewerScrollBar.setBackground(Color.BLACK);
        graphicViewerScrollBar.setToolTipText("时间控制进度条");
        graphicViewerScrollBar.addAdjustmentListener(new GraphicScrollBarEventListener(graphicViewerScrollBar,GraphicViewerSpeedFrame.class.getName()));

        JLabel lbVol = new JLabel("测量转速");
        lbVol.setBounds(680, 10, 80, 16);
        lbVol.setFont(new Font("黑体", Font.BOLD, 15));
        lbVol.setForeground(Color.white);

        JButton btVoltage = new JButton();
        btVoltage.setBounds(780, 10, 16, 16);
        btVoltage.setBackground(Color.red);
        btVoltage.setEnabled(false);


        bottomPanel.add(graphicViewerScrollBar);
        bottomPanel.add(lbRef);
        bottomPanel.add(btRefVoltage);
        bottomPanel.add(lbVol);
        bottomPanel.add(btVoltage);
        this.add(bottomPanel);

    }

    public static GraphicViewerSpeedFrame getInstances() {
        return graphicViewerFrame;
    }

    public void buttonEvent(ActionEvent e) {
        if (e.getSource().equals(graphToolBarZoomIn)) {

            if (ConstantFactory.stopUpdateView == false) {
                graphicViewerSpeed = new GraphicViewerSpeed(graphicViewerScrollBar, 2);
                graphicViewerSpeed.setDataBaseArray(ConstantFactory.speedDataBaseArrayViewerX5);
                graphicViewerSpeed.setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewerX5);
                scrollPane.setViewportView(graphicViewerSpeed);
            } else {
                graphicViewerSpeed = new GraphicViewerSpeed(graphicViewerScrollBar, 2);
                graphicViewerSpeed.setDataBaseArray(ConstantFactory.speedSnapshotArrayViewerX5);
                graphicViewerSpeed.setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewerX5);
                scrollPane.setViewportView(graphicViewerSpeed);
            }
        }
        if (e.getSource().equals(graphToolBarZoomOut)) {

            if (ConstantFactory.stopUpdateView == false) {
                graphicViewerSpeed = new GraphicViewerSpeed(graphicViewerScrollBar, 1);
                graphicViewerSpeed.setDataBaseArray(ConstantFactory.speedDataBaseArrayViewer);
                graphicViewerSpeed.setRefDataBaseArray(ConstantFactory.refSpeedDataBaseArrayViewer);
                scrollPane.setViewportView(graphicViewerSpeed);
            } else {
                graphicViewerSpeed = new GraphicViewerSpeed(graphicViewerScrollBar, 1);
                graphicViewerSpeed.setDataBaseArray(ConstantFactory.speedSnapshotArrayViewer);
                graphicViewerSpeed.setRefDataBaseArray(ConstantFactory.refSpeedSnapshotArrayViewer);
                scrollPane.setViewportView(graphicViewerSpeed);
            }
        }
    }
}
