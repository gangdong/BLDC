/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphicviewer;

import com.dong.factory.ConstantFactory;
import com.dong.scrollbar.GraphicScrollBarEventListener;
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
public class GraphicViewerVoltageFrame extends JDialog {

    private JScrollBar graphicViewerScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 479, 1, 0, 480);
    private GraphicViewerVoltage graphicViewerVoltage = new GraphicViewerVoltage(graphicViewerScrollBar, 1);
    private JButton graphToolBarZoomIn, graphToolBarZoomOut, graphToolBarZoomFit;
    private static GraphicViewerVoltageFrame graphicViewerVoltageFrame = new GraphicViewerVoltageFrame();
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public GraphicViewerVoltage getGraphicViewerVoltage() {
        return graphicViewerVoltage;
    }

     private GraphicViewerVoltageFrame() {

        this.setSize(900, 660);
        this.setLocation(100, 100);
        this.setLayout(null);
        this.setForeground(Color.black);
        this.setModal(false);
        this.setTitle("Voltage Viewer");
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
       // topPanel.add(graphToolBarZoomFit);

        topPanel.setBounds(0, 0, 900, 45);
        topPanel.setBackground(Color.black);
        this.add(topPanel);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBounds(0, 45, 900, 540);
        scrollPane.setBounds(8, 0, 878, 540);
        scrollPane.setVisible(true);
        middlePanel.add(scrollPane);
        scrollPane.setViewportView(graphicViewerVoltage);

        if (ConstantFactory.stopUpdateView == true) {
            graphicViewerVoltage.setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewer);
            graphicViewerVoltage.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewer);
            graphicViewerVoltage.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewer);
        } else {
            graphicViewerVoltage.setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewer);
            graphicViewerVoltage.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewer);
            graphicViewerVoltage.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewer);
        }

        middlePanel.setBackground(Color.black);
        this.add(middlePanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0, 585, 900, 60);
        bottomPanel.setBackground(Color.black);

        JLabel lbRef = new JLabel("总线电压");

        lbRef.setBounds(480, 10, 80, 16);
        lbRef.setFont(new Font("黑体", Font.BOLD, 15));
        lbRef.setForeground(Color.white);

        JButton btRefVoltage = new JButton();
        btRefVoltage.setBounds(560, 10, 16, 16);
        btRefVoltage.setBackground(Color.red);
        btRefVoltage.setEnabled(false);

        graphicViewerScrollBar.setBounds(8, 10, 425, 20);
        graphicViewerScrollBar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        graphicViewerScrollBar.setBackground(Color.BLACK);
        graphicViewerScrollBar.setToolTipText("时间控制进度条");
        graphicViewerScrollBar.addAdjustmentListener(new GraphicScrollBarEventListener(graphicViewerScrollBar,GraphicViewerVoltageFrame.class.getName()));

        JLabel lbVol = new JLabel("D轴电压");
        lbVol.setBounds(630, 10, 80, 16);
        lbVol.setFont(new Font("黑体", Font.BOLD, 15));
        lbVol.setForeground(Color.white);

        JButton btVoltage = new JButton();
        btVoltage.setBounds(710, 10, 16, 16);
        btVoltage.setBackground(Color.yellow);
        btVoltage.setEnabled(false);

        JLabel lbVolQAxis = new JLabel("Q轴电压");
        lbVolQAxis.setBounds(760, 10, 80, 16);
        lbVolQAxis.setFont(new Font("黑体", Font.BOLD, 15));
        lbVolQAxis.setForeground(Color.white);

        JButton btVoltageQAxis = new JButton();
        btVoltageQAxis.setBounds(840, 10, 16, 16);
        btVoltageQAxis.setBackground(Color.green);
        btVoltageQAxis.setEnabled(false);




        bottomPanel.add(graphicViewerScrollBar);
        bottomPanel.add(lbRef);
        bottomPanel.add(btRefVoltage);
        bottomPanel.add(lbVol);
        bottomPanel.add(btVoltage);
        bottomPanel.add(lbVolQAxis);
        bottomPanel.add(btVoltageQAxis);

        this.add(bottomPanel);

    }

    public static GraphicViewerVoltageFrame getInstances() {
        return graphicViewerVoltageFrame;
    }

     public void buttonEvent(ActionEvent e) {
        if (e.getSource().equals(graphToolBarZoomIn)) {

            if (ConstantFactory.stopUpdateView == false) {
                graphicViewerVoltage = new GraphicViewerVoltage(graphicViewerScrollBar, 2);
                graphicViewerVoltage.setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewerX5);
                graphicViewerVoltage.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewerX5);
                graphicViewerVoltage.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewerX5);

                scrollPane.setViewportView(graphicViewerVoltage);
            } else {
                graphicViewerVoltage = new GraphicViewerVoltage(graphicViewerScrollBar, 2);
                graphicViewerVoltage.setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewerX5);
                graphicViewerVoltage.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewerX5);
                graphicViewerVoltage.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewerX5);
                scrollPane.setViewportView(graphicViewerVoltage);
            }
        }
        if (e.getSource().equals(graphToolBarZoomOut)) {

            if (ConstantFactory.stopUpdateView == false) {
                graphicViewerVoltage = new GraphicViewerVoltage(graphicViewerScrollBar, 1);
                graphicViewerVoltage.setDataBaseArray(ConstantFactory.voltageDataBaseArrayViewer);
                graphicViewerVoltage.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageDataBaseArrayViewer);
                graphicViewerVoltage.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageDataBaseArrayViewer);
                scrollPane.setViewportView(graphicViewerVoltage);
            } else {
                graphicViewerVoltage = new GraphicViewerVoltage(graphicViewerScrollBar, 1);
                graphicViewerVoltage.setDataBaseArray(ConstantFactory.voltageSnapshotArrayViewer);
                graphicViewerVoltage.setdAxisVoltageDataBaseArray(ConstantFactory.dAxisVoltageSnapshotArrayViewer);
                graphicViewerVoltage.setqAxisVoltageDataBaseArray(ConstantFactory.qAxisVoltageSnapshotArrayViewer);
                scrollPane.setViewportView(graphicViewerVoltage);
            }
        }
    }
}
