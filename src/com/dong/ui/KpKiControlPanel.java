/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.ui;

import com.dong.factory.ConstantFactory;
import com.dong.keyevent.KeyListener;
import com.dong.serialcom.ComBean;
import com.dong.serialcom.ComCommandAnalyst;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * this class extends JPanel class, construct a panel to set the PI control
 * information. this panel is embedded into bottom part of main frame..
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class KpKiControlPanel extends JPanel {

    /**
     * get the global exclusive instance of this panel. signle-instance mode.
     */
    private static KpKiControlPanel kpKiControlPanel = new KpKiControlPanel();
    /**
     * textfield for setting the pi parameters.has registed the documentlistener.
     */
    private JTextField speedKp, speedKi, currentKp, currentKi;
    /**
     * for get the command format
     */
    private ComCommandAnalyst data = new ComCommandAnalyst();
    /**
     * get the global instance of comBean.
     */
    private ComBean serialCom = ComBean.getInstances();
    /**
     * constructor method, private property for used as single - instance mode.
     */
    private JButton setBtn = new JButton("设定");

    private KpKiControlPanel() {

        JLabel lbControlParameters = new JLabel("控制参数");

        lbControlParameters.setBounds(150, 10, 100, 40);
        lbControlParameters.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, 14));

        JLabel lbSpeedKp = new JLabel("速度环Kp：");
        lbSpeedKp.setSize(100, 20);
        lbSpeedKp.setLocation(10, 55);

        JLabel lbSpeedKi = new JLabel("速度环Ki：");
        lbSpeedKi.setSize(100, 20);
        lbSpeedKi.setLocation(180, 55);

        JLabel lbCurrentKp = new JLabel("电流环Kp：");
        lbCurrentKp.setSize(100, 20);
        lbCurrentKp.setLocation(10, 85);

        JLabel lbCurrentKi = new JLabel("电流环Ki：");
        lbCurrentKi.setSize(100, 20);
        lbCurrentKi.setLocation(180, 85);


        setBtn.setSize(100, 20);
        setBtn.setLocation(230, 120);
        //setBtn.setToolTipText("设定PI控制参数");
        setBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setPIParameters(e);
            }
        });

        speedKp = new JTextField();
        speedKp.setBackground(Color.WHITE);
        speedKp.setHorizontalAlignment(JTextField.CENTER);
        speedKp.setFont(new Font("黑体", Font.BOLD, 12));
        //speedKp.setToolTipText("输入速度环的Kp参数");

        speedKi = new JTextField();
        speedKi.setBackground(Color.WHITE);
        speedKi.setHorizontalAlignment(JTextField.CENTER);
        speedKi.setFont(new Font("黑体", Font.BOLD, 12));
        //speedKi.setToolTipText("输入速度环的Ki参数");

        currentKp = new JTextField();
        currentKp.setBackground(Color.WHITE);
        currentKp.setHorizontalAlignment(JTextField.CENTER);
        currentKp.setFont(new Font("黑体", Font.BOLD, 12));
        //currentKp.setToolTipText("输入电流环的Kp参数");


        currentKi = new JTextField();
        currentKi.setBackground(Color.WHITE);
        currentKi.setHorizontalAlignment(JTextField.CENTER);
        currentKi.setFont(new Font("黑体", Font.BOLD, 12));
        //currentKi.setToolTipText("输入电流环的Ki参数");

        speedKp.setSize(80, 20);
        speedKi.setSize(80, 20);
        currentKp.setSize(80, 20);
        currentKi.setSize(80, 20);

        speedKp.setLocation(80, 55);
        speedKi.setLocation(250, 55);
        currentKp.setLocation(80, 85);
        currentKi.setLocation(250, 85);

        speedKp.getDocument().addDocumentListener(new PIParameterDocumentListener(speedKp));
        speedKi.getDocument().addDocumentListener(new PIParameterDocumentListener(speedKi));
        currentKp.getDocument().addDocumentListener(new PIParameterDocumentListener(currentKp));
        currentKi.getDocument().addDocumentListener(new PIParameterDocumentListener(currentKi));

        speedKp.setFocusable(true);
        speedKp.addKeyListener(new KeyListener());
        this.setLayout(null);

        this.add(lbControlParameters);
        this.add(lbSpeedKp);
        this.add(lbSpeedKi);
        this.add(lbCurrentKp);
        this.add(lbCurrentKi);
        this.add(speedKp);
        this.add(speedKi);
        this.add(currentKp);
        this.add(currentKi);
        this.add(setBtn);

    }

    /**
     * return the global exclusive instance of this panel. calling this method is
     * the only way to get the panel instance.
     */
    public static KpKiControlPanel getInstances() {
        return kpKiControlPanel;
    }

    /**
     * return class member.
     */
    public JTextField getCurrentKi() {
        return currentKi;
    }

    /**
     * return class member.
     */
    public JTextField getCurrentKp() {
        return currentKp;
    }

    /**
     * return class member.
     */
    public JTextField getSpeedKi() {
        return speedKi;
    }

    /**
     * return class member.
     */
    public JTextField getSpeedKp() {
        return speedKp;
    }

    /**
     * send the pi parameters value. this method is called and executed when the
     * confrim button is pressed.
     */
    public void setPIParameters(ActionEvent e) {

        byte[] parameterArray = new byte[8];
        int speedKp, speedKi, currentKp, currentKi;


        speedKp = Integer.parseInt(this.speedKp.getText());
        speedKi = Integer.parseInt(this.speedKi.getText());
        currentKp = Integer.parseInt(this.currentKp.getText());
        currentKi = Integer.parseInt(this.currentKi.getText());

        parameterArray[0] = (byte) (speedKp >> 8);
        parameterArray[1] = (byte) (speedKp);
        parameterArray[2] = (byte) (speedKi >> 8);
        parameterArray[3] = (byte) (speedKi);
        parameterArray[4] = (byte) (currentKp >> 8);
        parameterArray[5] = (byte) (currentKp);
        parameterArray[6] = (byte) (currentKi >> 8);
        parameterArray[7] = (byte) (currentKi);

        data.setMsg_DwnControl(parameterArray);
        serialCom.writeCom(data.getMsg_DwnControl());

    }
}

/**
 * internal class, realize a class which implements DocumentListener. when the
 * contents of JTextField changed, the related method will act.
 * @see javax.swing.event.DocumentListener
 */
class PIParameterDocumentListener implements DocumentListener {

    private JTextField textField;

    public PIParameterDocumentListener(JTextField textField) {
        this.textField = textField;

    }

    public void changedUpdate(DocumentEvent e) {
        this.textField.setText(e.getDocument().toString());

    }

    public void insertUpdate(DocumentEvent e) {
        ConstantFactory.focusOnPI = true;
        ConstantFactory.focusOnSpeed = false;
    }

    public void removeUpdate(DocumentEvent e) {
        ConstantFactory.focusOnPI = true;
        ConstantFactory.focusOnSpeed = false;
    }
}
