/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.scrollbar;

import com.dong.factory.ConstantFactory;
import com.dong.graphicviewer.GraphicViewerCurrentFrame;
import com.dong.graphicviewer.GraphicViewerSpeedFrame;
import com.dong.graphicviewer.GraphicViewerVoltageFrame;
import com.dong.ui.GraphicDisplayPanel;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;

/**
 * this class implements {@link java.awt.event.AdjustmentListener }.
 * this class is registed as the adjustment listener of
 * {@link com.dong.graphic.GraphicArea#scrollBar }
 * and {@link com.dong.graphicviewer.GraphicViewer#scrollBar}.
 * when anyone of these scrollbars is adjustmented,the
 * {@link #adjustmentValueChanged(java.awt.event.AdjustmentEvent) } will be
 * executed.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class GraphicScrollBarEventListener implements AdjustmentListener {

    /**
     * constructor method. initialize the class member.
     */
    public GraphicScrollBarEventListener(JScrollBar scrollBar, String objName) {
        this.scrollBar = scrollBar;
        this.objName = objName;
    }
    /**
     * getter method, return class member.
     */
    public JScrollBar getScrollBar() {
        return scrollBar;
    }

    /**
     * setter method, set class member - {@link #scrollBar } as the intput
     * parameters value.
     * @param scrollBar JScrollBar
     */
    public void setScrollBar(JScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    /**
     * method relization of {@link java.awt.event.AdjustmentListener }. when value
     * of the {@link #scrollBar } changed, this method is called.
     * @param  adjustmentEvent AdjustmentEvent
     */
    public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {

        if (ConstantFactory.timerRun == 0) {

            if (objName.equals(GraphicDisplayPanel.class.getName())) {
                GraphicDisplayPanel.getInstances().repaint();
            } else if (objName.equals(GraphicViewerSpeedFrame.class.getName())) {
                GraphicViewerSpeedFrame.getInstances().repaint();
            } else if (objName.equals(GraphicViewerVoltageFrame.class.getName())) {
                GraphicViewerVoltageFrame.getInstances().repaint();
            } else if(objName.equals(GraphicViewerCurrentFrame.class.getName())){
                GraphicViewerCurrentFrame.getInstances().repaint();
            }
            else {
            }

        }
    }
    /**
     * define internal scrollbar.
     */
    private JScrollBar scrollBar;
    /**
     * define name of object.
     */
    private String objName;
}
