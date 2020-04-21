/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.keyevent;

import com.dong.factory.ConstantFactory;
import com.dong.ui.ControlPanel;
import com.dong.ui.KpKiControlPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Dong Gang
 */
public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_ENTER) {
            
            if (ConstantFactory.focusOnSpeed) {
                ConstantFactory.focusOnPI = false;
                ControlPanel.getInstances().updateSpeed();
            } else if (ConstantFactory.focusOnPI) {
                ConstantFactory.focusOnSpeed = false;
                KpKiControlPanel.getInstances().setPIParameters(null);
            } else {
            }
        }
    }
}

