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
public class GraphicViewerCurrentFrame extends JDialog{
    private JScrollBar graphicViewerScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 479, 1, 0, 480);
    private GraphicViewerCurrent graphicViewerCurrent = new GraphicViewerCurrent(graphicViewerScrollBar, 1);
    private JButton graphToolBarZoomIn, graphToolBarZoomOut, graphToolBarZoomFit;
    private static GraphicViewerCurrentFrame graphicViewerCurrentFrame = new GraphicViewerCurrentFrame();
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public GraphicViewerCurrent getGraphicViewerCurrent() {
        return graphicViewerCurrent;
    }

    private GraphicViewerCurrentFrame() {

        this.setSize(900, 660);
        this.setLocation(100, 100);
        this.setLayout(null);
        this.setForeground(Color.black);
        this.setModal(false);
        this.setTitle("Current Viewer");
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
        scrollPane.setViewportView(graphicViewerCurrent);

        if (ConstantFactory.stopUpdateView == true) {
            graphicViewerCurrent.setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewer);
            graphicViewerCurrent.setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewer);
        } else {
            graphicViewerCurrent.setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewer);
            graphicViewerCurrent.setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewer);
        }



        middlePanel.setBackground(Color.black);
        this.add(middlePanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0, 585, 900, 60);
        bottomPanel.setBackground(Color.black);

        JLabel lbRef = new JLabel("D轴电流");

        lbRef.setBounds(480, 10, 80, 16);
        lbRef.setFont(new Font("黑体", Font.BOLD, 15));
        lbRef.setForeground(Color.white);

        JButton btRefVoltage = new JButton();
        btRefVoltage.setBounds(580, 10, 16, 16);
        btRefVoltage.setBackground(Color.red);
        btRefVoltage.setEnabled(false);

        graphicViewerScrollBar.setBounds(8, 10, 425, 20);
        graphicViewerScrollBar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        graphicViewerScrollBar.setBackground(Color.BLACK);
        graphicViewerScrollBar.setToolTipText("时间控制进度条");
        graphicViewerScrollBar.addAdjustmentListener(new GraphicScrollBarEventListener(graphicViewerScrollBar,GraphicViewerCurrentFrame.class.getName()));

        JLabel lbVol = new JLabel("Q轴电流");
        lbVol.setBounds(680, 10, 80, 16);
        lbVol.setFont(new Font("黑体", Font.BOLD, 15));
        lbVol.setForeground(Color.white);

        JButton btVoltage = new JButton();
        btVoltage.setBounds(780, 10, 16, 16);
        btVoltage.setBackground(Color.green);
        btVoltage.setEnabled(false);


        bottomPanel.add(graphicViewerScrollBar);
        bottomPanel.add(lbRef);
        bottomPanel.add(btRefVoltage);
        bottomPanel.add(lbVol);
        bottomPanel.add(btVoltage);
        this.add(bottomPanel);

    }

    public static GraphicViewerCurrentFrame getInstances() {
        return graphicViewerCurrentFrame;
    }

    public void buttonEvent(ActionEvent e) {
        if (e.getSource().equals(graphToolBarZoomIn)) {

            if (ConstantFactory.stopUpdateView == false) {
                graphicViewerCurrent = new GraphicViewerCurrent(graphicViewerScrollBar, 2);
                graphicViewerCurrent.setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewerX5);
                graphicViewerCurrent.setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewerX5);
                scrollPane.setViewportView(graphicViewerCurrent);
            } else {
                graphicViewerCurrent = new GraphicViewerCurrent(graphicViewerScrollBar, 2);
                graphicViewerCurrent.setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewerX5);
                graphicViewerCurrent.setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewerX5);
                scrollPane.setViewportView(graphicViewerCurrent);
            }
        }
        if (e.getSource().equals(graphToolBarZoomOut)) {

            if (ConstantFactory.stopUpdateView == false) {
                graphicViewerCurrent = new GraphicViewerCurrent(graphicViewerScrollBar, 1);
                graphicViewerCurrent.setDataBaseArray(ConstantFactory.dAxisCurrentDataBaseArrayViewer);
                graphicViewerCurrent.setqAxisDataBaseArray(ConstantFactory.qAxisCurrentDataBaseArrayViewer);
                scrollPane.setViewportView(graphicViewerCurrent);
            } else {
                graphicViewerCurrent = new GraphicViewerCurrent(graphicViewerScrollBar, 1);
                graphicViewerCurrent.setDataBaseArray(ConstantFactory.dAxisCurrentSnapshotArrayViewer);
                graphicViewerCurrent.setqAxisDataBaseArray(ConstantFactory.qAxisCurrentSnapshotArrayViewer);
                scrollPane.setViewportView(graphicViewerCurrent);
            }
        }
    }
}
