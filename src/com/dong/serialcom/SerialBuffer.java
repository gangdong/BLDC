/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.serialcom;

import com.dong.datatransfer.CurrentDataTransfer;
import com.dong.datatransfer.SpeedDataTransfer;
import com.dong.datatransfer.VoltageDataTransfer;
import com.dong.factory.ComProtocolFactory;
import com.dong.factory.ConstantFactory;
import com.dong.ui.KpKiControlPanel;
import com.dong.ui.MonitorPanel;
import com.dong.ui.StatusPanel;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Dong Gang
 */
public class SerialBuffer {

    private int[] rxDataBuffer = new int[1024];
    private int[] cmdAnalysisBuffer = new int[256];
    private static int rxDataBufferIndex = 0;
    private static int cmdAnalysisBufferIndex = 0;
    private static boolean isCmdIntegrate = false;
    ComBean serialCom;

    public SerialBuffer() {
    }

    public SerialBuffer(ComBean comBean) {
        this.serialCom = comBean;
    }

    public void putByte(int data) {

       // int i = 0;

        if (rxDataBufferIndex < 1024) {
        } else {
            rxDataBufferIndex = 0;
        }
        rxDataBuffer[rxDataBufferIndex] = data;
        rxDataBufferIndex++;

        cmdAnalysisBuffer[cmdAnalysisBufferIndex] = data;
        if (data == 0xaa) {
            cmdAnalysisBufferIndex = 0;
            isCmdIntegrate = true;
        } else {
            cmdAnalysisBufferIndex++;
        }
        if (isCmdIntegrate) {
            switch (cmdAnalysisBuffer[0]) {
                case ComProtocolFactory.msg_Type_Config_Start_Can:
                    isCmdIntegrate = false;
                    serialCom.setIsConfigureAndStart(true);
                    break;
                case ComProtocolFactory.msg_Type_Program_Over:
                    isCmdIntegrate = false;
                    serialCom.setIsComProOver(true);
                    break;
                case ComProtocolFactory.msg_Type_Tx_Data_Std_Frame:
                    //    i++;
                    break;
                case 15:
                    //    i++;
                    break;
                case 16:
                    //     i++;
                    break;
                case 19:
                    //    i++;
                    break;
                case 20:
                    //    i++;
                    break;
                case 21:
                    //     i++;
                    break;
                case 22:
                    //    i++;
                    break;
                case 29:
                    //     i++;
                    break;
                case 30:
                    //     i++;
                    break;

                case ComProtocolFactory.msg_Type_Rx_Data_Std_Frame:
                    isCmdIntegrate = false;
                    int length = cmdAnalysisBuffer[1];
                    if (length > 6) {
                        if (cmdAnalysisBuffer[length + 1] == 170) {

                            switch (cmdAnalysisBuffer[3]) {
                                case ComProtocolFactory.msg_Type_Motor_ComChk:
                                    break;
                                case ComProtocolFactory.msg_Type_Motor_ComChkAck:

                                    int speedKp,
                                     speedKi,
                                     currentKp,
                                     currentKi;
                                    serialCom.setIsComTestOk(true);
                                    StatusPanel statusPanel = StatusPanel.getInstances();
                                    statusPanel.getLbClockFrequency().setText("Clock Frequency:          160MHz");
                                    statusPanel.getLbFlashAcc().setText("RAM Size:                        512KB");
                                    statusPanel.getLbFlashOpp().setText("ROM Size:                        32KB");
                                    statusPanel.getLbFlashAva().setText("CAN Baud-rate:               500KHz");
                                    statusPanel.getLbPwmMF().setText("PWM Frequency:            20KHz");
                                    statusPanel.getLbSmpleF().setText("Sample Frequency:        10KHz");
                                    statusPanel.getBtnStCpu().setBackground(Color.green);
                                    statusPanel.getBtnStFlash().setBackground(Color.green);
                                    statusPanel.getBtnStRam().setBackground(Color.green);
                                    statusPanel.getBtnCanEn().setBackground(Color.green);

                                    speedKp = (cmdAnalysisBuffer[5] << 8) | (cmdAnalysisBuffer[6] & (0xff));
                                    speedKi = (cmdAnalysisBuffer[7] << 8) | (cmdAnalysisBuffer[8] & (0xff));
                                    currentKp = (cmdAnalysisBuffer[9] << 8) | (cmdAnalysisBuffer[10] & (0xff));
                                    currentKi = (cmdAnalysisBuffer[11] << 8) | (cmdAnalysisBuffer[12] & (0xff));

                                    KpKiControlPanel kpKiControlPanel = KpKiControlPanel.getInstances();
                                    kpKiControlPanel.getSpeedKp().setText(String.valueOf(speedKp));
                                    kpKiControlPanel.getSpeedKi().setText(String.valueOf(speedKi));
                                    kpKiControlPanel.getCurrentKp().setText(String.valueOf(currentKp));
                                    kpKiControlPanel.getCurrentKi().setText(String.valueOf(currentKi));

                                    break;
                                case ComProtocolFactory.msg_Type_Motor_DwnSpeed:
                                    //  i++;
                                    break;
                                case ComProtocolFactory.msg_Type_Motor_DwnSpeedAck:
                                    //  i++;
                                    break;
                                case ComProtocolFactory.msg_Type_Motor_DwnControlAck:
                                    // i++;
                                    break;
                                case ComProtocolFactory.msg_Type_Motor_RstSpeedAck:
                                    int getSpeed,
                                     screenSpeed,
                                     screenSpeedViewer,
                                     screenSpeedViewerX5;
                                    /* big endian */
                                    getSpeed = (cmdAnalysisBuffer[5] << 8) | (cmdAnalysisBuffer[6] & (0xff));
                                    if (getSpeed > 32767) {
                                        getSpeed = -((~(Math.abs(getSpeed)) + 1) & (0x0000ffff));

                                    } else {
                                        //do nothing
                                    }
                                    screenSpeed = new SpeedDataTransfer(getSpeed, ConstantFactory.speedRange).getScreenData();
                                    screenSpeedViewer = new SpeedDataTransfer(getSpeed, ConstantFactory.speedRange).getScreenDataViewer();
                                    screenSpeedViewerX5 = new SpeedDataTransfer(getSpeed, ConstantFactory.speedRange).getScreenDataViewerX5();

                                    Point point = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenSpeed);
                                    Point pointViewer = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, screenSpeedViewer);
                                    Point pointViewerX5 = new Point(ConstantFactory.z0Viewer.x + ConstantFactory.xLengthViewer, screenSpeedViewerX5);
                                    ConstantFactory.speedDataBaseArray.set(0, point);
                                    ConstantFactory.speedDataBaseArrayViewer.set(0, pointViewer);
                                    ConstantFactory.speedDataBaseArrayViewerX5.set(0, pointViewerX5);
                                    ConstantFactory.realSpeedArray.set(0, getSpeed);

                                    if (ConstantFactory.stopUpdateView == false) {
                                        MonitorPanel monitorPanel = MonitorPanel.getInstances();
                                        monitorPanel.getLbMonitorSpeed().setText(String.valueOf(getSpeed));
                                    }

                                    break;
                                case ComProtocolFactory.msg_Type_Motor_RstCurrentAck:
                                    if (cmdAnalysisBuffer[4] == (int) 4) {
                                        int getCurrentDAxis, getCurrentQAxis, screenCurrent, screenCurrentDAxisViewer,
                                                screenCurrentDAxisViewerX5, screenCurrentQAxisViewer, screenCurrentQAxisViewerX5;

                                        getCurrentDAxis = (cmdAnalysisBuffer[5] << 8) | (cmdAnalysisBuffer[6] & (0xff));

                                        if (getCurrentDAxis > 32767) {
                                            getCurrentDAxis = -((~(Math.abs(getCurrentDAxis)) + 1) & (0x0000ffff));

                                        } else {
                                            //do nothing
                                        }
                                        screenCurrent = new CurrentDataTransfer(getCurrentDAxis, ConstantFactory.currentRange).getScreenData();
                                        screenCurrentDAxisViewer = new CurrentDataTransfer(getCurrentDAxis, ConstantFactory.currentRange).getScreenDataViewer();
                                        screenCurrentDAxisViewerX5 = new CurrentDataTransfer(getCurrentDAxis, ConstantFactory.currentRange).getScreenDataViewerX5();

                                        Point pointCurrentDAxis = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenCurrent);
                                        Point pointCurrentDAxisViewer = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenCurrentDAxisViewer);
                                        Point pointCurrentDAxisViewerX5 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenCurrentDAxisViewerX5);


                                        ConstantFactory.dAxisCurrentDataBaseArray.set(0, pointCurrentDAxis);
                                        ConstantFactory.dAxisCurrentDataBaseArrayViewer.set(0, pointCurrentDAxisViewer);
                                        ConstantFactory.dAxisCurrentDataBaseArrayViewerX5.set(0, pointCurrentDAxisViewerX5);

                                        ConstantFactory.realDAxisCurrentArray.set(0, getCurrentDAxis);

                                        getCurrentQAxis = (cmdAnalysisBuffer[7] << 8) | (cmdAnalysisBuffer[8] & (0xff));

                                        if (getCurrentQAxis > 32767) {
                                            getCurrentQAxis = -((~(Math.abs(getCurrentQAxis)) + 1) & (0x0000ffff));
                                        } else {
                                            //do nothing
                                        }

                                        screenCurrent = new CurrentDataTransfer(getCurrentQAxis, ConstantFactory.currentRange).getScreenData();
                                        screenCurrentQAxisViewer = new CurrentDataTransfer(getCurrentQAxis, ConstantFactory.currentRange).getScreenDataViewer();
                                        screenCurrentQAxisViewerX5 = new CurrentDataTransfer(getCurrentQAxis, ConstantFactory.currentRange).getScreenDataViewerX5();



                                        Point pointCurrentQAxis = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenCurrent);
                                        Point pointCurrentQAxisViewer = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenCurrentQAxisViewer);
                                        Point pointCurrentQAxisViewerX5 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenCurrentQAxisViewerX5);


                                        ConstantFactory.qAxisCurrentDataBaseArray.set(0, pointCurrentQAxis);
                                        ConstantFactory.qAxisCurrentDataBaseArrayViewer.set(0, pointCurrentQAxisViewer);
                                        ConstantFactory.qAxisCurrentDataBaseArrayViewerX5.set(0, pointCurrentQAxisViewerX5);


                                        ConstantFactory.realQAxisCurrentArray.set(0, getCurrentQAxis);

                                        if (ConstantFactory.stopUpdateView == false) {
                                            MonitorPanel monitorPanel = MonitorPanel.getInstances();
                                            monitorPanel.getLbDAxisCur().setText(String.valueOf(getCurrentDAxis));
                                            monitorPanel.getLbQAxisCur().setText(String.valueOf(getCurrentQAxis));
                                        }
                                    }
                                    break;
                                case ComProtocolFactory.msg_Type_Motor_RstVoltageAck:
                                    if (cmdAnalysisBuffer[4] == (int) 6) {
                                        int getVoltage, getDAxisVoltage, getQAxisVoltage, screenVoltage,
                                                screenVoltageViewer, screenVoltageViewerX5, screenDAxisVoltageViewer, screenDAxisVoltageViewerX5,
                                                screenQAxisVoltageViewer, screenQAxisVoltageViewerX5;
                                        
                                         float floatGetVoltage,floatGetDAxisVoltage,floatGetQAxisVoltage;


                                        getVoltage = (cmdAnalysisBuffer[5] << 8) | (cmdAnalysisBuffer[6] & (0xff));
                                       
                                        if (getVoltage > 32767) {
                                            getVoltage = -((~(Math.abs(getVoltage)) + 1) & (0x0000ffff));

                                        } else {
                                            //do nothing
                                        }

                                        getVoltage = getVoltage*100;
                                        floatGetVoltage = (float)(getVoltage/1000);

                                        screenVoltage = new VoltageDataTransfer(getVoltage, ConstantFactory.voltageRange).getScreenData();
                                        screenVoltageViewer = new VoltageDataTransfer(getVoltage, ConstantFactory.voltageRange).getScreenDataViewer();
                                        screenVoltageViewerX5 = new VoltageDataTransfer(getVoltage, ConstantFactory.voltageRange).getScreenDataViewerX5();


                                        Point pointVoltage = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenVoltage);
                                        Point pointVoltageViewer = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenVoltageViewer);
                                        Point pointVoltageViewerX5 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenVoltageViewerX5);

                                        ConstantFactory.voltageDataBaseArray.set(0, pointVoltage);
                                        ConstantFactory.voltageDataBaseArrayViewer.set(0, pointVoltageViewer);
                                        ConstantFactory.voltageDataBaseArrayViewerX5.set(0, pointVoltageViewerX5);
                                        ConstantFactory.realVoltageArray.set(0, floatGetVoltage);


                                        getDAxisVoltage = (cmdAnalysisBuffer[7] << 8) | (cmdAnalysisBuffer[8] & (0xff));
                                        if (getDAxisVoltage > 32767) {
                                            getDAxisVoltage = -((~(Math.abs(getDAxisVoltage)) + 1) & (0x0000ffff));

                                        } else {
                                            //do nothing
                                        }

                                        getDAxisVoltage = getDAxisVoltage*100;
                                        floatGetDAxisVoltage = (float)(getDAxisVoltage/1000);

                                        screenVoltage = new VoltageDataTransfer(getDAxisVoltage, ConstantFactory.voltageRange).getScreenData();
                                        screenDAxisVoltageViewer = new VoltageDataTransfer(getDAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewer();
                                        screenDAxisVoltageViewerX5 = new VoltageDataTransfer(getDAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewerX5();

                                        Point pointVoltageDAxis = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenVoltage);
                                        Point pointDAxisVoltageViewer = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenDAxisVoltageViewer);
                                        Point pointDAxisVoltageViewerX5 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenDAxisVoltageViewerX5);

                                        ConstantFactory.dAxisVoltageDataBaseArray.set(0, pointVoltageDAxis);
                                        ConstantFactory.dAxisVoltageDataBaseArrayViewer.set(0, pointDAxisVoltageViewer);
                                        ConstantFactory.dAxisVoltageDataBaseArrayViewerX5.set(0, pointDAxisVoltageViewerX5);
                                        ConstantFactory.realDAxisVoltageArray.set(0, floatGetDAxisVoltage);

                                        getQAxisVoltage = (cmdAnalysisBuffer[9] << 8) | (cmdAnalysisBuffer[10] & (0xff));
                                        if (getQAxisVoltage > 32767) {
                                            getQAxisVoltage = -((~(Math.abs(getQAxisVoltage)) + 1) & (0x0000ffff));

                                        } else {
                                            //do nothing
                                        }
                                        getQAxisVoltage = getQAxisVoltage*100;
                                        floatGetQAxisVoltage = (float)(getQAxisVoltage/1000);

                                        screenVoltage = new VoltageDataTransfer(getQAxisVoltage, ConstantFactory.voltageRange).getScreenData();
                                        screenQAxisVoltageViewer = new VoltageDataTransfer(getQAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewer();
                                        screenQAxisVoltageViewerX5 = new VoltageDataTransfer(getQAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewerX5();


                                        Point pointVoltageQAxis = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenVoltage);
                                        Point pointQAxisVoltageViewer = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenQAxisVoltageViewer);
                                        Point pointQAxisVoltageViewerX5 = new Point(ConstantFactory.z0.x + ConstantFactory.xLength, screenQAxisVoltageViewerX5);

                                        ConstantFactory.qAxisVoltageDataBaseArray.set(0, pointVoltageQAxis);
                                        ConstantFactory.qAxisVoltageDataBaseArrayViewer.set(0, pointQAxisVoltageViewer);
                                        ConstantFactory.qAxisVoltageDataBaseArrayViewerX5.set(0, pointQAxisVoltageViewerX5);
                                        ConstantFactory.realQAxisVoltageArray.set(0, floatGetQAxisVoltage);

                                        if (ConstantFactory.stopUpdateView == false) {

                                            MonitorPanel monitorPanel = MonitorPanel.getInstances();
                                            monitorPanel.getLbMonitorBusVoltage().setText(String.valueOf(floatGetVoltage));
                                            monitorPanel.getLbDAxisVol().setText(String.valueOf(floatGetDAxisVoltage));
                                            monitorPanel.getLbQAxisVol().setText(String.valueOf(floatGetQAxisVoltage));
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    break;

                default:
                    break;
            }
        }

    }
}
