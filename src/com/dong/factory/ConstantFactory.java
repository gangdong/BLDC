/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.factory;

import com.dong.tablemodel.XTable;
import com.dong.ui.GraphicDisplayPanel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;

/**
 *
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 * This is the parent class of the GraphicAreaSpeed class, GraphicAreaCurrent
 * class and GraphicVoltage class.This class is abstract class, it defines the
 * basic method and character of GraphicArea. This class implements the interface
 * of MouseMotionListener, which can lead to response of mouse event.
 *
 */
public class ConstantFactory {

    public static boolean comProRun = false;
    public static int comBtnPressed;
    public static String infoStr;
    public static boolean motorRun = false;
    public static String[] timeScaleString = new String[481];
    /* 定义逻辑坐标间隔，每个逻辑点（241）的坐标间隔转化到屏幕坐标 14个像素 */
    public static int errScaleShift = 14; // the meaning is 280/20

    /* X/Y 轴 长度, 坐标间距离， */
    public final static int xLength = 280, yLength = 250, spaceLength = 80, scaleLength = 5;
    public final static int xLengthViewer = 760, yLengthViewer = 500;
    /* 速度坐标/电压坐标/电流坐标 原点 */
    public final static Point z0 = new Point(80, 300),z1 = new Point(460, 300), z2 = new Point(820, 300);;
    public final static Point z0Viewer = new Point(80, 510);
    /* 每个采样显示点（21）的屏幕 坐标 X 值 */
    public final static int scaleSpeed[] = new int[41];
    public final static int scaleVoltage[] = new int[41];
    public final static int scaleCurrent[] = new int[41];

    /* 后台采集数据 （241） 4点/秒*60秒 */
    public static ArrayList speedDataBaseArray = new ArrayList();
    public static ArrayList refSpeedDataBaseArray = new ArrayList();
    public static ArrayList voltageDataBaseArray = new ArrayList();
    public static ArrayList dAxisVoltageDataBaseArray = new ArrayList();
    public static ArrayList qAxisVoltageDataBaseArray = new ArrayList();
    public static ArrayList dAxisCurrentDataBaseArray = new ArrayList();
    public static ArrayList qAxisCurrentDataBaseArray = new ArrayList();

    public static ArrayList speedDataBaseArrayViewer = new ArrayList();
    public static ArrayList refSpeedDataBaseArrayViewer = new ArrayList();
    public static ArrayList speedDataBaseArrayViewerX5 = new ArrayList();
    public static ArrayList refSpeedDataBaseArrayViewerX5 = new ArrayList();

    public static ArrayList dAxisCurrentDataBaseArrayViewer = new ArrayList();
    public static ArrayList qAxisCurrentDataBaseArrayViewer = new ArrayList();
    public static ArrayList dAxisCurrentDataBaseArrayViewerX5 = new ArrayList();
    public static ArrayList qAxisCurrentDataBaseArrayViewerX5 = new ArrayList();

    public static ArrayList voltageDataBaseArrayViewer = new ArrayList();
    public static ArrayList dAxisVoltageDataBaseArrayViewer = new ArrayList();
    public static ArrayList qAxisVoltageDataBaseArrayViewer = new ArrayList();

    public static ArrayList voltageDataBaseArrayViewerX5 = new ArrayList();
    public static ArrayList dAxisVoltageDataBaseArrayViewerX5 = new ArrayList();
    public static ArrayList qAxisVoltageDataBaseArrayViewerX5 = new ArrayList();


    /* 存储当前的数据，用于暂停更新的功能 */
    public static ArrayList speedSnapshotArray = new ArrayList();
    public static ArrayList refSpeedSnapshotArray = new ArrayList();
    public static ArrayList voltageSnapshotArray = new ArrayList();
    public static ArrayList dAxisVoltageSnapshotArray = new ArrayList();
    public static ArrayList qAxisVoltageSnapshotArray = new ArrayList();
    public static ArrayList dAxisCurrentSnapshotArray = new ArrayList();
    public static ArrayList qAxisCurrentSnapshotArray = new ArrayList();

    public static ArrayList speedSnapshotArrayViewer = new ArrayList();
    public static ArrayList speedSnapshotArrayViewerX5 = new ArrayList();
    public static ArrayList refSpeedSnapshotArrayViewer = new ArrayList();
    public static ArrayList refSpeedSnapshotArrayViewerX5 = new ArrayList();

    public static ArrayList dAxisCurrentSnapshotArrayViewer = new ArrayList();
    public static ArrayList dAxisCurrentSnapshotArrayViewerX5 = new ArrayList();
    public static ArrayList qAxisCurrentSnapshotArrayViewer = new ArrayList();
    public static ArrayList qAxisCurrentSnapshotArrayViewerX5 = new ArrayList();

    public static ArrayList voltageSnapshotArrayViewer = new ArrayList();
    public static ArrayList dAxisVoltageSnapshotArrayViewer = new ArrayList();
    public static ArrayList qAxisVoltageSnapshotArrayViewer = new ArrayList();

    public static ArrayList voltageSnapshotArrayViewerX5 = new ArrayList();
    public static ArrayList dAxisVoltageSnapshotArrayViewerX5 = new ArrayList();
    public static ArrayList qAxisVoltageSnapshotArrayViewerX5 = new ArrayList();

    /* 存放真实的数据信息，而不是屏幕坐标，用于Table 的显示*/
    public static ArrayList realTimeArray = new ArrayList();
    public static ArrayList realRefSpeedArray = new ArrayList();
    public static ArrayList realSpeedArray = new ArrayList();
    public static ArrayList realVoltageArray = new ArrayList();
    public static ArrayList realDAxisVoltageArray = new ArrayList();
    public static ArrayList realQAxisVoltageArray = new ArrayList();
    public static ArrayList realDAxisCurrentArray = new ArrayList();
    public static ArrayList realQAxisCurrentArray = new ArrayList();
    /* 全局唯一图像句柄，代表图像显示区 */
    public static GraphicDisplayPanel graphicDisplayPanel = GraphicDisplayPanel.getInstances();
    //  public static GraphicViewerFrame graphicViewerFrame = GraphicViewerFrame.getInstances();

    /* 全局唯一数据表句柄，代表数据显示区 */
    public static XTable dataTable = new XTable();
    public static ArrayList speedScaleDataBaseArray = new ArrayList();
    public static ArrayList speedScaleStringArray = new ArrayList();
    public static ArrayList voltageScaleDataBaseArray = new ArrayList();
    public static ArrayList voltageScaleStringArray = new ArrayList();
    public static ArrayList currentScaleDataBaseArray = new ArrayList();
    public static ArrayList currentScaleStringArray = new ArrayList();
    public static int timerRun;
    public static Timer timer;
    public static boolean stopUpdateBtnPressed;
    public static boolean stopUpdateView;
    public static final int zeroValue = z0.y - yLength / 2;
    public static boolean dialogRun_Speed = false;
    public static boolean dialogRun_Current = false;
    public static boolean dialogRun_Voltage = false;

    public static int speedRange = 12500,voltageRange = 30000,currentRange = 3000;
    public static int refSpeed;

    public static int samplePointPerSecond = 8;
    public static int samplePointNumber = 480;

    public static boolean focusOnSpeed = false,focusOnPI = false;
    
    public ConstantFactory() {
    }

    public static void initStaticVar() {

        comProRun = false;
        comBtnPressed = 0;
        infoStr = "";
        motorRun = false;
        focusOnSpeed = false;
        focusOnPI = false;
        
        
        speedRange = 12500;
        voltageRange = 30000;
        currentRange = 3000;

        refSpeed = 0;
        samplePointPerSecond = 8;
        samplePointNumber = samplePointPerSecond * 60;
        errScaleShift = xLength/(samplePointPerSecond*5);


        initRealData();
        initTimeScaleString();
        initDataBaseArray();
        initScale();




        timerRun = 0;
        stopUpdateBtnPressed = false;
        stopUpdateView = false;

       
    }

    public static void initScale() {
        for (int i = 0; i < (samplePointPerSecond*5 +1); i++) {
            scaleSpeed[i] = z0.x + xLength - i * errScaleShift;
            scaleVoltage[i] = z1.x + xLength - i * errScaleShift;
            scaleCurrent[i] = z2.x + xLength - i * errScaleShift;
        }
    }

    public static void initDataBaseArray() {
        for (int i = 0; i < samplePointNumber; i++) {

            speedDataBaseArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            refSpeedDataBaseArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            voltageDataBaseArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            dAxisVoltageDataBaseArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            qAxisVoltageDataBaseArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            qAxisCurrentDataBaseArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            dAxisCurrentDataBaseArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y - yLength / 2));

            speedSnapshotArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            refSpeedSnapshotArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            voltageSnapshotArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            dAxisVoltageSnapshotArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            qAxisVoltageSnapshotArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            qAxisCurrentSnapshotArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y - yLength / 2));
            dAxisCurrentSnapshotArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y - yLength / 2));

            speedDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            refSpeedDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            dAxisCurrentDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            qAxisCurrentDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            speedDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            refSpeedDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            dAxisCurrentDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            qAxisCurrentDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            voltageDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            dAxisVoltageDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            qAxisVoltageDataBaseArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            voltageDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            dAxisVoltageDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            qAxisVoltageDataBaseArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));


            speedSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            speedSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            refSpeedSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            refSpeedSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            dAxisCurrentSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            dAxisCurrentSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            qAxisCurrentSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            qAxisCurrentSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));

            voltageSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            voltageSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            dAxisVoltageSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            dAxisVoltageSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));
            qAxisVoltageSnapshotArrayViewer.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y - yLengthViewer / 2));
            qAxisVoltageSnapshotArrayViewerX5.add(new Point(z0Viewer.x + (xLengthViewer - (xLengthViewer / (samplePointPerSecond*5)) * i), z0Viewer.y * 5 - (yLengthViewer * 5) / 2));



        }

        for (int i = 0; i < samplePointNumber; i++) {
            if (i % samplePointPerSecond == 0) {
                speedScaleDataBaseArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y + 5));
                speedScaleStringArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y + 20));

                voltageScaleDataBaseArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y + 5));
                voltageScaleStringArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y + 20));

                currentScaleDataBaseArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y + 5));
                currentScaleStringArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y + 20));
            } else {
                speedScaleDataBaseArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y));
                speedScaleStringArray.add(new Point(z0.x + (xLength - errScaleShift * i), z0.y));

                voltageScaleDataBaseArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y));
                voltageScaleStringArray.add(new Point(z1.x + (xLength - errScaleShift * i), z0.y));

                currentScaleDataBaseArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y));
                currentScaleStringArray.add(new Point(z2.x + (xLength - errScaleShift * i), z0.y));
            }

        }

    }

    public static void initRealData() {
        for (int i = 0; i < samplePointNumber; i++) {
            realTimeArray.add((double) (0.125 * i));
            realRefSpeedArray.add((int)0);
            realSpeedArray.add((int) 0);
            realVoltageArray.add((float) 0);
            realDAxisVoltageArray.add((float) 0);
            realQAxisVoltageArray.add((float) 0);
            realDAxisCurrentArray.add((int) 0);
            realQAxisCurrentArray.add((int) 0);
        }
    }

    public static void timeShiftForRealDataArray(int i) {
        while (i > 0) {

            Object oldRefSpeed = (Object)realRefSpeedArray.get(i-1);
            Object oldSpeed = (Object) realSpeedArray.get(i - 1);
            Object oldVoltage = (Object) realVoltageArray.get(i - 1);

            Object oldVoltaged = (Object) realDAxisVoltageArray.get(i - 1);
            Object oldVoltageq = (Object) realQAxisVoltageArray.get(i - 1);

            Object oldCurrentd = (Object) realDAxisCurrentArray.get(i - 1);
            Object oldCurrentq = (Object) realQAxisCurrentArray.get(i - 1);


            realRefSpeedArray.set(i, oldRefSpeed);
            realSpeedArray.set(i, oldSpeed);
            realVoltageArray.set(i, oldVoltage);

            realDAxisVoltageArray.set(i, oldVoltaged);
            realQAxisVoltageArray.set(i, oldVoltageq);

            realDAxisCurrentArray.set(i, oldCurrentd);
            realQAxisCurrentArray.set(i, oldCurrentq);

            i--;
        }


    }

    public static void timeShiftForDataBaseArray(int i) {
        while (i > 0) {
            Point prTmps = (Point) refSpeedDataBaseArray.get(i - 1);
            Point prTmpd = (Point) refSpeedDataBaseArray.get(i);
            prTmpd.y = prTmps.y;
            refSpeedDataBaseArray.set(i, prTmpd);

            Point pTmps = (Point) speedDataBaseArray.get(i - 1);
            Point pTmpd = (Point) speedDataBaseArray.get(i);
            pTmpd.y = pTmps.y;
            speedDataBaseArray.set(i, pTmpd);

            Point vTmps = (Point) voltageDataBaseArray.get(i - 1);
            Point vTmpd = (Point) voltageDataBaseArray.get(i);
            vTmpd.y = vTmps.y;
            voltageDataBaseArray.set(i, vTmpd);


            Point vTmpsd = (Point) dAxisVoltageDataBaseArray.get(i - 1);
            Point vTmpdd = (Point) dAxisVoltageDataBaseArray.get(i);
            vTmpdd.y = vTmpsd.y;
            dAxisVoltageDataBaseArray.set(i, vTmpdd);

            Point vTmpsq = (Point) qAxisVoltageDataBaseArray.get(i - 1);
            Point vTmpdq = (Point) qAxisVoltageDataBaseArray.get(i);
            vTmpdq.y = vTmpsq.y;
            qAxisVoltageDataBaseArray.set(i, vTmpdq);

            Point cTmps = (Point) dAxisCurrentDataBaseArray.get(i - 1);
            Point cTmpd = (Point) dAxisCurrentDataBaseArray.get(i);
            cTmpd.y = cTmps.y;
            dAxisCurrentDataBaseArray.set(i, cTmpd);

            Point crTmps = (Point) qAxisCurrentDataBaseArray.get(i - 1);
            Point crTmpd = (Point) qAxisCurrentDataBaseArray.get(i);
            crTmpd.y = crTmps.y;
            qAxisCurrentDataBaseArray.set(i, crTmpd);


            Point pTmpsV = (Point) speedDataBaseArrayViewer.get(i - 1);
            Point pTmpdV = (Point) speedDataBaseArrayViewer.get(i);
            pTmpdV.y = pTmpsV.y;
            speedDataBaseArrayViewer.set(i, pTmpdV);

            Point pTmpsVf = (Point) refSpeedDataBaseArrayViewer.get(i - 1);
            Point pTmpdVf = (Point) refSpeedDataBaseArrayViewer.get(i);
            pTmpdVf.y = pTmpsVf.y;
            refSpeedDataBaseArrayViewer.set(i, pTmpdVf);


            Point pTmpsDAxisCurrent = (Point) dAxisCurrentDataBaseArrayViewer.get(i - 1);
            Point pTmpdDAxisCurrent = (Point) dAxisCurrentDataBaseArrayViewer.get(i);
            pTmpdDAxisCurrent.y = pTmpsDAxisCurrent.y;
            dAxisCurrentDataBaseArrayViewer.set(i, pTmpdDAxisCurrent);

            Point pTmpsQAxisCurrent = (Point) qAxisCurrentDataBaseArrayViewer.get(i - 1);
            Point pTmpdQAxisCurrent = (Point) qAxisCurrentDataBaseArrayViewer.get(i);
            pTmpdQAxisCurrent.y = pTmpsQAxisCurrent.y;
            qAxisCurrentDataBaseArrayViewer.set(i, pTmpdQAxisCurrent);

            Point pTmpsVoltage = (Point) voltageDataBaseArrayViewer.get(i - 1);
            Point pTmpdVoltage = (Point) voltageDataBaseArrayViewer.get(i);
            pTmpdVoltage.y = pTmpsVoltage.y;
            voltageDataBaseArrayViewer.set(i, pTmpdVoltage);

            Point pTmpsDAxisVoltage = (Point) dAxisVoltageDataBaseArrayViewer.get(i - 1);
            Point pTmpdDAxisVoltage = (Point) dAxisVoltageDataBaseArrayViewer.get(i);
            pTmpdDAxisVoltage.y = pTmpsDAxisVoltage.y;
            dAxisVoltageDataBaseArrayViewer.set(i, pTmpdDAxisVoltage);

            Point pTmpsQAxisVoltage = (Point) qAxisVoltageDataBaseArrayViewer.get(i - 1);
            Point pTmpdQAxisVoltage = (Point) qAxisVoltageDataBaseArrayViewer.get(i);
            pTmpdQAxisVoltage.y = pTmpsQAxisVoltage.y;
            qAxisVoltageDataBaseArrayViewer.set(i, pTmpdQAxisVoltage);


            Point pTmpsVX5 = (Point) speedDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdVX5 = (Point) speedDataBaseArrayViewerX5.get(i);
            pTmpdVX5.y = pTmpsVX5.y;
            speedDataBaseArrayViewerX5.set(i, pTmpdVX5);

            Point pTmpsVX5f = (Point) refSpeedDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdVX5f = (Point) refSpeedDataBaseArrayViewerX5.get(i);
            pTmpdVX5f.y = pTmpsVX5f.y;
            refSpeedDataBaseArrayViewerX5.set(i, pTmpdVX5f);

            Point pTmpsDAxisCurrentX5 = (Point) dAxisCurrentDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdDAxisCurrentX5 = (Point) dAxisCurrentDataBaseArrayViewerX5.get(i);
            pTmpdDAxisCurrentX5.y = pTmpsDAxisCurrentX5.y;
            dAxisCurrentDataBaseArrayViewerX5.set(i, pTmpdDAxisCurrentX5);

            Point pTmpsQAxisCurrentX5 = (Point) qAxisCurrentDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdQAxisCurrentX5 = (Point) qAxisCurrentDataBaseArrayViewerX5.get(i);
            pTmpdQAxisCurrentX5.y = pTmpsQAxisCurrentX5.y;
            qAxisCurrentDataBaseArrayViewerX5.set(i, pTmpdQAxisCurrentX5);

            Point pTmpsVoltageX5 = (Point) voltageDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdVoltageX5 = (Point) voltageDataBaseArrayViewerX5.get(i);
            pTmpdVoltageX5.y = pTmpsVoltageX5.y;
            voltageDataBaseArrayViewerX5.set(i, pTmpdVoltageX5);


            Point pTmpsDAxisVoltageX5 = (Point) dAxisVoltageDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdDAxisVoltageX5 = (Point) dAxisVoltageDataBaseArrayViewerX5.get(i);
            pTmpdDAxisVoltageX5.y = pTmpsDAxisVoltageX5.y;
            dAxisVoltageDataBaseArrayViewerX5.set(i, pTmpdDAxisVoltageX5);

            Point pTmpsQAxisVoltageX5 = (Point) qAxisVoltageDataBaseArrayViewerX5.get(i - 1);
            Point pTmpdQAxisVoltageX5 = (Point) qAxisVoltageDataBaseArrayViewerX5.get(i);
            pTmpdQAxisVoltageX5.y = pTmpsQAxisVoltageX5.y;
            qAxisVoltageDataBaseArrayViewerX5.set(i, pTmpdQAxisVoltageX5);

            i--;
        }

    }

    public static void initTimeScaleString() {
        for (int i = 0; i < samplePointNumber+1; i++) {
            if (0 == (i % samplePointPerSecond)) {
                int j = i / samplePointPerSecond;
                timeScaleString[i] = "-" + String.valueOf(j) + "sec";
            } else {
                timeScaleString[i] = "";
            }
        }
    }
}
