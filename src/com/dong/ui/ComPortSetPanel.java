/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import com.dong.factory.ConstantFactory;
import com.dong.serialcom.ComBean;
import com.dong.serialcom.ComCommandAnalyst;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dong Gang
 */
public class ComPortSetPanel extends JPanel {

    /* 构造函数 */
    public ComPortSetPanel() {

        initStatus();
        initComponent();

        //  this.setSize(350, 155);
        this.setSize(350, 90);
        this.setLayout(null);

        //  this.setBackground(Color.WHITE);


        this.add(portSetting);
        this.add(lbCommId);
        this.add(cbCommId);
        /*注释掉*/
        /*
        this.add(lbBaudrate);
        this.add(cbBaudrate);
        this.add(lbParity);
        this.add(cbParity);
        this.add(lbDataBit);
        this.add(cbDataBit);
        this.add(lbStopBit);
        this.add(cbStopBit);
         */
        this.add(openComBtn);

        setEvent();



    }

    public JLabel getLbStatus() {
        return lbStatus;
    }


    /* 初始化面板 */
    private void initComponent() {

        /* label */
        portSetting = new JLabel();
        portSetting.setBounds(150, 10, 100, 40);
        portSetting.setText("端口设置");
        portSetting.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, 14));


        lbCommId = new JLabel("端口选择:");
        lbCommId.setSize(70, 20);

        Vector vCommId = this.serialCom.getComId();
        cbCommId = new JComboBox(vCommId);

        cbCommId.setBackground(Color.WHITE);
        cbCommId.setSize(70, 20);
        cbCommId.setToolTipText("可选择端口");
        /* 注释掉 */
        /*
        lbBaudrate = new JLabel("波特率: ");
        lbBaudrate.setSize(70, 20);

        String[] strBaudrate = {"300", "600", "1200", "2400", "4800", "9600", "19200", "38400", "43000", "56000", "57600", "115200","2000000"};
        cbBaudrate = new JComboBox(strBaudrate);
        cbBaudrate.setSelectedItem("200000");
        cbBaudrate.setBackground(Color.WHITE);
        cbBaudrate.setSize(70, 20);

        lbParity = new JLabel("奇偶校验: ");
        lbParity.setSize(70, 20);

        String[] strParity = {"无校验", "奇校验", "偶校验"};
        cbParity = new JComboBox(strParity);        // cbParity.setBorder(BorderFactory.createLineBorder(Color.black));
        cbParity.setBackground(Color.WHITE);
        cbParity.setSize(70, 20);

        lbDataBit = new JLabel("数据长度:");
        lbDataBit.setSize(70, 20);

        String[] strDataBit = {"5", "6", "7", "8"};
        cbDataBit = new JComboBox(strDataBit);
        cbDataBit.setSelectedItem("8");        // cbDataBit.setBorder(BorderFactory.createLineBorder(Color.black));
        cbDataBit.setBackground(Color.WHITE);
        cbDataBit.setSize(70, 20);

        lbStopBit = new JLabel("停止位:");
        lbStopBit.setSize(70, 20);

        String[] strStopBit = {"1", "1.5", "2"};
        cbStopBit = new JComboBox(strStopBit);        // cbStopBit.setBorder(BorderFactory.createLineBorder(Color.black));
        cbStopBit.setSelectedItem("1");
        cbStopBit.setBackground(Color.WHITE);
        cbStopBit.setSize(70, 20);
         */
        openComBtn = new JButton("建立连接");
        openComBtn.setSize(140, 20);
        //openComBtn.setToolTipText("建立通讯连接");


        /* 位置调整 */
        lbCommId.setLocation(20, 50);
        cbCommId.setLocation(90, 50);
        /* 注释掉 */
        /*
        lbBaudrate.setLocation(180, 60);
        cbBaudrate.setLocation(250, 60);
        lbParity.setLocation(20, 90);
        cbParity.setLocation(90, 90);
        lbDataBit.setLocation(180, 90);
        cbDataBit.setLocation(250, 90);
        lbStopBit.setLocation(20, 120);
        cbStopBit.setLocation(90, 120);
         */
        openComBtn.setLocation(180, 50);

       

        lbStatus.setText("通讯中断");
        lbStatus.setBounds(0, 0, 10, 20);
        lbStatus.setFont(new Font("宋体", Font.BOLD, 10));
    }

    /* 初始化状态变量 */
    private void initStatus() {
        ConstantFactory.comBtnPressed = 0;
    }
    /* 注册事件 */

    private void setEvent() {

        openComBtn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comConnectEvent(e);
            }
        });
    }

    /* 打开串口按钮的事件响应方法 */
    /**
     *
     */
    public void comConnectEvent(java.awt.event.ActionEvent evt) {

        long timer = (long) 0x0fffffff;

        switch (ConstantFactory.comBtnPressed) {
            case 0:
                String portId = cbCommId.getSelectedItem().toString();
                /*
                int baudrate = cbBaudrate.getSelectedIndex();
                int stopbit = cbStopBit.getSelectedIndex();
                int parity = cbParity.getSelectedIndex();
                int databit = cbDataBit.getSelectedIndex();

                String strBaud = cbBaudrate.getSelectedItem().toString();
                String strDataBit = cbDataBit.getSelectedItem().toString();
                String strParity = cbParity.getSelectedItem().toString();
                String strStopBit = cbStopBit.getSelectedItem().toString();

                this.serialCom.setPortName(portId);
                this.serialCom.setBaudrate(baudrate);
                this.serialCom.setDataBit(databit);
                this.serialCom.setParityBit(parity);
                this.serialCom.setStopBit(stopbit);
                 */
                //               String str = "连接成功: 端口_" + portId + ", 波特率_" + strBaud + ", 数据位_" + strDataBit + ", 奇偶_" + strParity + ", 停止位_" + strStopBit;
                String str = "连接成功: 端口_" + portId + ", 波特率_2000000" + ", 数据位_8" + ", 奇偶_无校验" + ", 停止位_1";
                String str1 = "连接成功，系统运行中！                  CAN总线状态：正常";
                this.serialCom.setPortName(portId);
                if (serialCom.initCom()) {

                    serialCom.writeCom(command.msg_ProgOver);
                    while ((serialCom.getIsComProOver() == false) && (timer > 0)) {
                        timer--;
                    }
                    if (timer > 0) {
                        timer = 0xfffffff;
                        serialCom.writeCom(command.getConfigAndStartCan());
                        while ((serialCom.getIsConfigureAndStart() == false) && (timer > 0)) {
                            timer--;
                        }
                        if (timer > 0) {
                            timer = 0xfffffff;
                            serialCom.writeCom(command.msg_CommChk);
                            while ((serialCom.getIsComTestOk() == false) && (timer > 0)) {
                                timer--;
                            }
                            if (timer > 0) {
                                ConstantFactory.comBtnPressed = 1;
                                cbCommId.setEnabled(false);
                                openComBtn.setText("中断连接");
                                openComBtn.setToolTipText("中断通讯连接");
                            } else {
                                str = "    请运行下位机！    ";
                                ConstantFactory.comBtnPressed = 0;
                                serialCom.closePort();
                            }
                        } else {
                            str = "    Can station 配置失败！    ";
                            ConstantFactory.comBtnPressed = 0;
                            serialCom.closePort();
                        }
                    } else {
                        str = "    程序下载失败！    ";
                        ConstantFactory.comBtnPressed = 0;
                        serialCom.closePort();
                    }

                    ConstantFactory.infoStr = str1;
                    this.lbStatus.setText(ConstantFactory.infoStr);
                } else {
                    ConstantFactory.comBtnPressed = 0;
                    //   serialCom.closePort();
                    this.lbStatus.setText(ConstantFactory.infoStr);
                }
                break;
            case 1:
                openComBtn.setText("建立连接");
                openComBtn.setToolTipText("建立通讯连接");
                ConstantFactory.comBtnPressed = 0;
                serialCom.closePort();
                ConstantFactory.infoStr = "通讯中断";
                this.lbStatus.setText(ConstantFactory.infoStr);
                cbCommId.setEnabled(true);
                StatusPanel statusPanel = StatusPanel.getInstances();
                statusPanel.resetParameters();
                break;
            default:
                break;
        }
    }
    private JButton openComBtn;
    private JLabel portSetting;
    private JComboBox cbCommId;
    // private JComboBox cbBaudrate, cbParity, cbDataBit, cbStopBit;
    private JLabel lbCommId;
    //private JLabel lbBaudrate, lbParity, lbDataBit, lbStopBit;
    private JLabel lbStatus = new JLabel();
    private ComBean serialCom = ComBean.getInstances();
    private ComCommandAnalyst command = new ComCommandAnalyst();
}
