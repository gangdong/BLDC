/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.timer;

import com.dong.factory.ConstantFactory;
import com.dong.graphicviewer.GraphicViewerCurrentFrame;
import com.dong.graphicviewer.GraphicViewerSpeedFrame;
import com.dong.graphicviewer.GraphicViewerVoltageFrame;
import com.dong.serialcom.ComBean;
import com.dong.serialcom.ComCommandAnalyst;
import java.util.TimerTask;

/**
 * This class is a task difinition class of timer executing, it is a sub-class
 * of {@link java.util.TimerTask }
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 * @see java.util.TimerTask
 */
public class TaskTimer extends TimerTask {

    /**
     * represents the global instance of ComBean class
     */
    private ComBean serialCom = ComBean.getInstances();
    /**
     * object of ComCommandAnalyst, used for getting command.
     */
    private ComCommandAnalyst data = new ComCommandAnalyst();

    /***
     *  Override function, this function is called and executed every time period.
     *  @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
        /* send command for get information */
        serialCom.writeCom(data.getMsg_RstSpeed());
        /* reserved for initial version application which used the muti-command */
        /* for get motor information */
        //serialCom.writeCom(data.getMsg_RstCurrent());

        /* update base data array of backstage */
        ConstantFactory.timeShiftForDataBaseArray(479);
        /* update real data array of backstage */
        ConstantFactory.timeShiftForRealDataArray(479);
        /* set real data array as the content of dataTable */
        ConstantFactory.dataTable.getRstModel().setParameters(
                ConstantFactory.realTimeArray,
                ConstantFactory.realRefSpeedArray,
                ConstantFactory.realSpeedArray,
                ConstantFactory.realVoltageArray,
                ConstantFactory.realDAxisVoltageArray,
                ConstantFactory.realQAxisVoltageArray,
                ConstantFactory.realDAxisCurrentArray,
                ConstantFactory.realQAxisCurrentArray);

        /* if it isn't in the stop update viewer state, then update dataTable */
        if (ConstantFactory.stopUpdateView == false) {
            ConstantFactory.dataTable.getRstModel().updateTableContent();
        }

        /* update graphicDisplayPanel panel */
        ConstantFactory.graphicDisplayPanel.repaint();

        /* if dialog speed_run of magnification function is active, update repainting this dialog */
        if (ConstantFactory.dialogRun_Speed == true) {
            GraphicViewerSpeedFrame.getInstances().repaint();
        }
        /* if dialog current_run of magnification function is active, update repainting this dialog */
        if (ConstantFactory.dialogRun_Current == true) {
            GraphicViewerCurrentFrame.getInstances().repaint();
        }
        /* if dialog voltage_run of maginfication function is active, update repainting this dialog */
        if (ConstantFactory.dialogRun_Voltage == true) {
            GraphicViewerVoltageFrame.getInstances().repaint();
        }

        /* if timer overflow, timer is reset to 1 */
        if (ConstantFactory.timerRun > (int) 0x0fffffff) {
            ConstantFactory.timerRun = (int) 1;
        } else {
            ConstantFactory.timerRun++;
        }
    }

    /***
     *  Override function, this function is called and executed when exit timer.
     *  @see java.util.TimerTask#cancel()
     */
    @Override
    public boolean cancel() {
        return super.cancel();
    }

    /***
     *  Override function.
     *  @see java.util.TimerTask#scheduledExecutionTime()
     */
    @Override
    public long scheduledExecutionTime() {
        return super.scheduledExecutionTime();
    }
}
