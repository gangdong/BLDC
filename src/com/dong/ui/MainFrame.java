/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import com.dong.factory.ConstantFactory;
import com.dong.keyevent.KeyListener;
import com.dong.tablemodel.XTablePanel;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 * Main framework of whole application, extends JFrame. it divides the screen into
 * 3 sections. top-middle-bottom.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 * 
 */
public class MainFrame extends JFrame {

    /* 成员变量声明 */
    /** getting container panel of this frame.
     */
    private Container contentPane;
    /** top panel object
     */
    private JPanel topPanel;
    /**
     * middle panel object
     */
    private JPanel middlePanel;
    /**
     * bottom panel object
     */
    private JPanel bottomPanel;
    /**
     * information bar panel.
     */
    private JPanel infoPanel;
    /**
     * tabbed pane object.
     */
    private JTabbedPane tabbedPane;
    /**
     * status bar label, used for display status information.
     */
    private JLabel infoStatusLabel;
    /**
     * MCU status panel, is embedded into bottom panel.
     */
    private StatusPanel statusPanel;
    /**
     * communication setting panel, is embedded into bottom panel.
     */
    private ComPortSetPanel comPortSetPanel;
    /**
     * monitor panel, is embedded into bottom panel.
     */
    public MonitorPanel monitorPanel;
    /**
     * control panel, is embedded into bottom panel. updateing speed through this panel.
     */
    private ControlPanel controlPanel;
    /**
     * kp and ki control panel, is embedded into bottom pane.
     */
    private KpKiControlPanel kpKiControlPanel;
    /**
     * result record table, is embedded with tabbedpane with graphic pane into the
     * middle section.
     */
    private XTablePanel xTablePanel;

    /**
     * constructor of MainFrame class.initial basic widgets and constant variable.
     */
    public MainFrame() {
        super("Motor Demo 2.0");
        initCompoents();

        ConstantFactory.initStaticVar();
    }

    /**
     * initialize top panel.is called by {@link com.dong.ui.MainFrame#initCompoents()  }
     */
    private void initTopPanel() {
        topPanel.setLayout(new BorderLayout());

        JButton renesasLabel = new JButton();
        renesasLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        renesasLabel.setFocusable(false);
        /*!!! 注意大小写，文件名及其后缀 PNG写成png在调试环境下没有问题，但是打包后就不行了，找不到文件。因为文件是PNG */
        renesasLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RenesasLogo.PNG")));

        topPanel.add(renesasLabel, BorderLayout.EAST);
    }

    /**
     * initialize bottom panel.is called by {@link com.dong.ui.MainFrame#initCompoents()  }
     */
    public void initBottomPanel() {

        comPortSetPanel = new ComPortSetPanel();
        statusPanel = StatusPanel.getInstances();
        controlPanel = ControlPanel.getInstances();
        monitorPanel = MonitorPanel.getInstances();
        kpKiControlPanel = KpKiControlPanel.getInstances();

        infoPanel = new JPanel();
        infoStatusLabel = comPortSetPanel.getLbStatus();



        comPortSetPanel.setLocation(10, 9);
        monitorPanel.setBounds(372, 9, 350, 155);
        statusPanel.setBounds(735, 9, 400, 225);
        infoPanel.setBounds(735, 234, 400, 20);
        controlPanel.setBounds(372, 170, 350, 80);
        kpKiControlPanel.setBounds(10, 105, 350, 146);

        //infoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        comPortSetPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        monitorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        controlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        kpKiControlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());


        bottomPanel.setLayout(null);
        bottomPanel.add(kpKiControlPanel);
        bottomPanel.add(comPortSetPanel);
        bottomPanel.add(monitorPanel);
        bottomPanel.add(statusPanel);
        bottomPanel.add(infoPanel);
        infoPanel.add(infoStatusLabel);
        bottomPanel.add(controlPanel);

    }

    /**
     * initialize middle section of mainframe. is called by {@link com.dong.ui.MainFrame#initCompoents()  }
     */
    public void initMiddlePanel() {
        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        initGraphicPanel();
        xTablePanel = new XTablePanel();
        tabbedPane.add("运行结果", xTablePanel);
        tabbedPane.setToolTipTextAt(0, "图形显示窗口");
        tabbedPane.setToolTipTextAt(1, "数据显示窗口");
        middlePanel.setLayout(new BorderLayout());
        middlePanel.add(tabbedPane, BorderLayout.CENTER);
        
        
    }

    /**
     * initialize graphicpanel. is called by {@link com.dong.ui.MainFrame#initMiddlePanel()  }
     */
    public void initGraphicPanel() {
        tabbedPane.add("图形", ConstantFactory.graphicDisplayPanel);
    }

    /**
     * initialize basic widget. is called by {@link com.dong.ui.MainFrame#MainFrame() }
     */
    private void initCompoents() {

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        /* get content panel */
        contentPane = this.getContentPane();
        contentPane.setLayout(null);

        topPanel = new JPanel();
        bottomPanel = new JPanel();
        middlePanel = new JPanel();

        topPanel.setBounds(0, 0, 1152, 90);
        middlePanel.setBounds(10, 90, 1125, 390);
        bottomPanel.setBounds(0, 480, 1152, 288);

        contentPane.add(topPanel);
        contentPane.add(middlePanel);
        contentPane.add(bottomPanel);


        initTopPanel();
        initMiddlePanel();
        initBottomPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(64, 0);
        setSize(1152, 768);
        this.setResizable(false);

        this.setUndecorated(true); // 去掉窗口的装饰
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        this.addKeyListener(new KeyListener());

        this.setFocusable(true);
    }

    /**
     * the entrance method of this application. invoke thread and call the main framework.
     * @param args String[]
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}

