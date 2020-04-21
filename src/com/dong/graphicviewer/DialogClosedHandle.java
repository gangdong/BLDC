/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dong.graphicviewer;

import com.dong.factory.ConstantFactory;
import com.dong.ui.SetRangePanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Dong Gang
 */
public class DialogClosedHandle extends WindowAdapter{

    private Object obj;

    public DialogClosedHandle(Object object) {
        this.obj = object;
    }




    @Override
    public void windowClosing(WindowEvent e) {

       if(obj instanceof GraphicViewerSpeedFrame){
            ConstantFactory.dialogRun_Speed = false;
       }
       else if(obj instanceof GraphicViewerCurrentFrame){
        ConstantFactory.dialogRun_Current = false;
       }
       else if(obj instanceof GraphicViewerVoltageFrame){
        ConstantFactory.dialogRun_Voltage = false;
       }
       else if(obj instanceof SetRangePanel){

       }
    }



}
