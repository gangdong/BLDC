/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphicviewer;

import com.dong.datastore.GraphicData;
import com.dong.datatransfer.VoltageDataTransfer;
import com.dong.factory.ConstantFactory;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JScrollBar;

/**
 *
 * @author Dong Gang
 */
public class GraphicViewerVoltage extends GraphicViewer {

    /** represents the d-axis voltage points which are displayed on the screen.
     */
    private GraphicData dAxisVoltageDataArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** represents the d-axis voltage background points.
     */
    public ArrayList dAxisVoltageDataBaseArray = new ArrayList();
    /** boolan value, indicates whether the mouse position matches the d-axis voltage
     * curve point which needs displaying
     */
    protected boolean isDAxisMouseMatch = false;
    /** mouse position of d-axis point
     */
    protected Point dAxisMousePoint = new Point();
    /** represents the q-axis voltage points which are displayed on the screen.
     */
    private GraphicData qAxisVoltageDataArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** represents the q-axis voltage background points.
     */
    public ArrayList qAxisVoltageDataBaseArray = new ArrayList();
    /** boolan value, indicates whether the mouse position matches the q-axis voltage
     * curve point which needs displaying
     */
    protected boolean isQAxisMouseMatch = false;
    /** mouse position of q-axis point
     */
    protected Point qAxisMousePoint = new Point();

    public GraphicViewerVoltage(JScrollBar scrollBar, int mag) {
        super(scrollBar, mag);
        super.setRange(ConstantFactory.voltageRange, "V", 58,1000);
        initOtherDataArray();
    }

    public void initOtherDataArray() {
        for (int i = 0; i < 480; i++) {
            qAxisVoltageDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), 300));
            dAxisVoltageDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), 300));
        }
    }

    @Override
    public void displayOtherGraphic(int size) {
        Point srcPoint = new Point();
        Point desPoint = new Point();
        
        for (int i = 1; i < size; i++) {
            g2d.setColor(Color.yellow);
            srcPoint = (Point) dAxisVoltageDataArray.getPointList().get(i);
            desPoint = (Point) dAxisVoltageDataArray.getPointList().get(i - 1);
            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 1);
            path.moveTo(srcPoint.x, srcPoint.y);
            path.lineTo(desPoint.x, desPoint.y);
            g2d.draw(path);
        }

        for (int i = 1; i < size; i++) {
            g2d.setColor(Color.green);
            srcPoint = (Point) qAxisVoltageDataArray.getPointList().get(i);
            desPoint = (Point) qAxisVoltageDataArray.getPointList().get(i - 1);
            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 1);
            path.moveTo(srcPoint.x, srcPoint.y);
            path.lineTo(desPoint.x, desPoint.y);
            g2d.draw(path);
        }
 
    }

    @Override
    public void displayRealData() {
        if (isMouseMatch == true) {
            g2d.setColor(Color.RED);
            int y = mousePoint.y;
            VoltageDataTransfer transfer = new VoltageDataTransfer(range);
            transfer.setScreenData(y);
            int voltage;
            if (mag == 2) {
                voltage = transfer.getRealDataForViewerX5();
            } else {
                voltage = transfer.getRealDataForViewer();
            }

            g2d.drawOval(mousePoint.x, mousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("Bus Voltage:", mousePoint.x - 5, mousePoint.y - 20);
            g2d.drawString(String.valueOf(voltage) + "mV", mousePoint.x - 5, mousePoint.y - 7);
        }
        if (isDAxisMouseMatch == true) {
            g2d.setColor(Color.yellow);
            int y = dAxisMousePoint.y;
            VoltageDataTransfer transfer = new VoltageDataTransfer(range);
            transfer.setScreenData(y);

            int voltage;
            if (mag == 2) {
                voltage = transfer.getRealDataForViewerX5();
            } else {
                voltage = transfer.getRealDataForViewer();
            }

            g2d.drawOval(dAxisMousePoint.x, dAxisMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("D-Axis Voltage:", dAxisMousePoint.x + 5, dAxisMousePoint.y + 17);
            g2d.drawString(String.valueOf(voltage) + "mV", dAxisMousePoint.x + 5, dAxisMousePoint.y + 30);
        }
        if (isQAxisMouseMatch == true) {
            g2d.setColor(Color.green);
            int y = qAxisMousePoint.y;
            VoltageDataTransfer transfer = new VoltageDataTransfer(range);
            transfer.setScreenData(y);

            int voltage;
            if (mag == 2) {
                voltage = transfer.getRealDataForViewerX5();
            } else {
                voltage = transfer.getRealDataForViewer();
            }

            g2d.drawOval(qAxisMousePoint.x, qAxisMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("D-Axis Voltage:", qAxisMousePoint.x + 5, qAxisMousePoint.y + 17);
            g2d.drawString(String.valueOf(voltage) + "mV", qAxisMousePoint.x + 5, qAxisMousePoint.y + 30);
        }
    }

    @Override
    public void mouseMovedAction(MouseEvent e) {
        isMouseMatch = false;
        isDAxisMouseMatch = false;
        isQAxisMouseMatch = false;
        mousePositionX = e.getX();
        mousePositionY = e.getY();
        if ((mousePositionX <= width_x - (width_x - x_scale_length - z0.x)) && (mousePositionX >= z0.x)) {
            int position = (int) (((width_x - (width_x - x_scale_length - z0.x)) - mousePositionX) / (x_scale_length / 40));
            mousePoint = (Point) dataArray.getPointList().get(position);
            dAxisMousePoint = (Point) dAxisVoltageDataArray.getPointList().get(position);
            qAxisMousePoint = (Point) qAxisVoltageDataArray.getPointList().get(position);
            int uplim = mousePoint.y + 5;
            int downlim = mousePoint.y - 5;
            int dAxisUpLim = dAxisMousePoint.y + 5;
            int dAxisDownlim = dAxisMousePoint.y - 5;
            int qAxisUpLim = qAxisMousePoint.y + 5;
            int qAxisDownlim = qAxisMousePoint.y - 5;

            if ((mousePositionY >= downlim) && (mousePositionY <= uplim)) {
                isMouseMatch = true;
            }
            if ((mousePositionY >= dAxisDownlim) && (mousePositionY <= dAxisUpLim)) {
                isDAxisMouseMatch = true;
            }
            if ((mousePositionY >= qAxisDownlim) && (mousePositionY <= qAxisUpLim)) {
                isQAxisMouseMatch = true;
            }
        }
    }

    @Override
    public void timeShiftForOhterDataBaseArray(int i) {
        Point pTmps = (Point) dAxisVoltageDataBaseArray.get(i - 1);
        Point pTmpd = (Point) dAxisVoltageDataBaseArray.get(i);
        pTmpd.y = pTmps.y;
        dAxisVoltageDataBaseArray.set(i, pTmpd);

        Point pTmpsq = (Point) qAxisVoltageDataBaseArray.get(i - 1);
        Point pTmpdq = (Point) qAxisVoltageDataBaseArray.get(i);
        pTmpdq.y = pTmpsq.y;
        qAxisVoltageDataBaseArray.set(i, pTmpdq);
    }

    @Override
    public void updateOtherDataArray(int value) {
        if (value < 40) {
            dAxisVoltageDataArray.updatePointList(40, xScale, dAxisVoltageDataBaseArray);
            qAxisVoltageDataArray.updatePointList(40, xScale, qAxisVoltageDataBaseArray);
        } else {
            dAxisVoltageDataArray.updatePointList(value, xScale, dAxisVoltageDataBaseArray);
            qAxisVoltageDataArray.updatePointList(value, xScale, qAxisVoltageDataBaseArray);
        }
    }

    public void setdAxisVoltageDataBaseArray(ArrayList dAxisVoltageDataBaseArray) {
        this.dAxisVoltageDataBaseArray = dAxisVoltageDataBaseArray;
    }

    public void setqAxisVoltageDataBaseArray(ArrayList qAxisVoltageDataBaseArray) {
        this.qAxisVoltageDataBaseArray = qAxisVoltageDataBaseArray;
    }

    public GraphicData getdAxisVoltageDataArray() {
        return dAxisVoltageDataArray;
    }

    public ArrayList getdAxisVoltageDataBaseArray() {
        return dAxisVoltageDataBaseArray;
    }

   
    public void reMakeDataBaseArray() {
        int screenVoltage,screenDAxisVoltage, screenQAxisVoltage,screenVoltageX5,screenDAxisVoltageX5,screenQAxisVoltageX5;
        String tmpStr;
        Point tmpPoint = new Point();
        for (int i = 0; i < 480; i++) {

            Object realVoltage = (Object) ConstantFactory.realVoltageArray.get(i);
            tmpStr = realVoltage.toString();

            float voltageData = Float.valueOf(tmpStr);
            int intVoltageData = (int)(voltageData*1000);

            screenVoltage = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.voltageDataBaseArrayViewer.get(i));
            tmpPoint.y = screenVoltage;
            ConstantFactory.voltageDataBaseArrayViewer.set(i, tmpPoint);

            screenVoltageX5 = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.voltageDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenVoltageX5;
            ConstantFactory.voltageDataBaseArrayViewerX5.set(i, tmpPoint);

            realVoltage = (Object) ConstantFactory.realDAxisVoltageArray.get(i);
            tmpStr = realVoltage.toString();
            voltageData = Float.valueOf(tmpStr);
            intVoltageData = (int)(voltageData*1000);

            screenDAxisVoltage = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.dAxisVoltageDataBaseArrayViewer.get(i));
            tmpPoint.y = screenDAxisVoltage;
            ConstantFactory.dAxisVoltageDataBaseArrayViewer.set(i, tmpPoint);

            screenDAxisVoltageX5 = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.dAxisVoltageDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenDAxisVoltageX5;
            ConstantFactory.dAxisVoltageDataBaseArrayViewerX5.set(i, tmpPoint);

            realVoltage = (Object) ConstantFactory.realQAxisVoltageArray.get(i);
            tmpStr = realVoltage.toString();

           voltageData = Float.valueOf(tmpStr);
           intVoltageData = (int)(voltageData*1000);

            screenQAxisVoltage = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.qAxisVoltageDataBaseArrayViewer.get(i));
            tmpPoint.y = screenQAxisVoltage;
            ConstantFactory.qAxisVoltageDataBaseArrayViewer.set(i, tmpPoint);

            screenQAxisVoltageX5 = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.qAxisVoltageDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenQAxisVoltageX5;
            ConstantFactory.qAxisVoltageDataBaseArrayViewerX5.set(i, tmpPoint);
        }
    }

    @Override
    public void paintComponent(Graphics g) {


        clear(g);
        g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);

        /* 画 X轴 Y轴 */
        Line2D.Float x0Axis = new Line2D.Float(z0, x0);
        Line2D.Float y0Axis = new Line2D.Float(z0, y0);
        g2d.draw(x0Axis);
        g2d.draw(y0Axis);

        /* 画0 坐标线 */
        drawZeroLine(z0);

        /* 画 Y轴刻度 */
        if (mag == 1) {
            for (int i = 0; i < 11; i++) {
                Point srcPoint = new Point(z0.x - 5, z0.y - (y_scale_length / 10) * i);
                Point desPoint = new Point(z0.x, z0.y - (y_scale_length / 10) * i);
                Line2D.Float lineX = new Line2D.Float(srcPoint, desPoint);
                g2d.draw(lineX);
            }

            for (int i = 0; i < 11; i++) {
                Point srcPoint = new Point(z0.x - 2, z0.y - (y_scale_length / 10) * i + y_scale_length / 20);
                Point desPoint = new Point(z0.x, z0.y - (y_scale_length / 10) * i + y_scale_length / 20);
                Line2D.Float lineX = new Line2D.Float(srcPoint, desPoint);
                g2d.draw(lineX);
            }

            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            for (int i = 0; i < 11; i++) {
                float label = (-range + range / 5 * i);
                float factorLabel = label/factor;
                g2d.drawString(String.valueOf(factorLabel) + unitName, z0.x - space, z0.y - (y_scale_length / 10) * i + 5);
            }
        } else if (mag == 2) {
            for (int i = 0; i < 11; i++) {
                Point srcPoint = new Point(z0.x - 15, z0.y - (y_scale_length / 10) * i);
                Point desPoint = new Point(z0.x, z0.y - (y_scale_length / 10) * i);
                Line2D.Float lineX = new Line2D.Float(srcPoint, desPoint);
                g2d.draw(lineX);
            }

            for (int i = 0; i < 11; i++) {
                Point srcPoint = new Point(z0.x - 10, z0.y - (y_scale_length / 10) * i + y_scale_length / 20);
                Point desPoint = new Point(z0.x, z0.y - (y_scale_length / 10) * i + y_scale_length / 20);
                Line2D.Float lineX = new Line2D.Float(srcPoint, desPoint);
                g2d.draw(lineX);
            }

            for (int i = 0; i < 100; i++) {
                if (i % 10 != 0) {
                    Point srcPoint = new Point(z0.x - 10, z0.y - (y_scale_length / 100) * i);
                    Point desPoint = new Point(z0.x, z0.y - (y_scale_length / 100) * i);
                    Line2D.Float lineX = new Line2D.Float(srcPoint, desPoint);
                    g2d.draw(lineX);
                }

            }


            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            for (int i = 0; i < 11; i++) {
                float label = (-range + range / 5 * i);
                float factorLabel = label/factor;
                g2d.drawString(String.valueOf(factorLabel) + unitName, z0.x - space, z0.y - (y_scale_length / 10) * i + 5);
            }
        }


        /* 获取当前滚动条位置 */
        int scrollValue = scrollBar.getValue();

        /* 如果 滚动条 小于 20,当前屏 */
        if (scrollValue < 40) {
            /* 更新 前台坐标*/
            dataArray.updatePointList(40, xScale, dataBaseArray);
            /* 抽象函数, 用于子类扩展 */
            updateOtherDataArray(scrollValue);
            scaleDataArray.updatePointList(40, xScale, scaleDataBaseArray);
            scaleStringArray.updatePointList(40, xScale, scaleStringBaseArray);
            /* 更新 时间轴的 坐标*/
            updateTimeScaleString(40);
        } else {
            dataArray.updatePointList(scrollValue, xScale, dataBaseArray);
            updateOtherDataArray(scrollValue);
            scaleDataArray.updatePointList(scrollValue, xScale, scaleDataBaseArray);
            scaleStringArray.updatePointList(scrollValue, xScale, scaleStringBaseArray);

            updateTimeScaleString(scrollValue);
        }

        /* 显示曲线 */
        displayGraphicData(41);
        /* 显示测试数据 */
        displayRealData();
    }
    
}
