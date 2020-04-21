/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphic;

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
 * @version 1.0 2011-06-12
 * @since 1.6
 * This is the sub-class class extends the GraphicArea class, this class represents
 * the speed display area on the screen.
 *
 */
public class GraphicAreaVoltage extends GraphicArea {

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

     /** constructor of this class
     * @param scrollBar JScrollBar, calls super constructor
     * @see com.dong.graphic.GraphicArea#GraphicArea(javax.swing.JScrollBar)
     */
    public GraphicAreaVoltage(JScrollBar scrollBar,int range) {
        super(scrollBar);
        super.setRange(range," V",52,1000);
        initOtherDataArray();
    }

    /** Overrides parent function, displays the real data.this function is called
     * by {@link com.dong.graphic.GraphicArea#paintComponent(java.awt.Graphics)  }
     * @see com.dong.graphic.GraphicArea#displayRealData()
     */
    @Override
    public void displayRealData() {
        if (isMouseMatch == true) {
            g2d.setColor(Color.RED);
            int y = mousePoint.y;
            VoltageDataTransfer transfer = new VoltageDataTransfer(range);
            transfer.setScreenData(y);
            int voltage = transfer.getRealData();

            g2d.drawOval(mousePoint.x, mousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("Bus Voltage:", mousePoint.x + 10, mousePoint.y - 20);
            g2d.drawString(String.valueOf(voltage) + "mV", mousePoint.x + 10, mousePoint.y - 7);
        }
        if (isDAxisMouseMatch == true) {
            g2d.setColor(Color.yellow);
            int y = dAxisMousePoint.y;
            VoltageDataTransfer transfer = new VoltageDataTransfer(range);
            transfer.setScreenData(y);
            int voltage = transfer.getRealData();

            g2d.drawOval(dAxisMousePoint.x, dAxisMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("d-Axis Voltage:", dAxisMousePoint.x + 5, dAxisMousePoint.y + 17);
            g2d.drawString(String.valueOf(voltage) + "mV", dAxisMousePoint.x + 5, dAxisMousePoint.y + 30);
        }
         if (isQAxisMouseMatch == true) {
            g2d.setColor(Color.green);
            int y = qAxisMousePoint.y;
            VoltageDataTransfer transfer = new VoltageDataTransfer(range);
            transfer.setScreenData(y);
            int voltage = transfer.getRealData();

            g2d.drawOval(qAxisMousePoint.x, qAxisMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("q-Axis Voltage:", qAxisMousePoint.x - 45, qAxisMousePoint.y - 20);
            g2d.drawString(String.valueOf(voltage) + "mV", qAxisMousePoint.x - 45, qAxisMousePoint.y - 7);
        }
    }

     /** Override function, implements mouse motionlistener.this function is called
     * by {@link com.dong.graphic.GraphicArea#mouseMoved(java.awt.event.MouseEvent)  }.
     * @param e MouseEvent.
     * @see com.dong.graphic.GraphicArea#mouseMovedAction(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMovedAction(MouseEvent e) {
        isMouseMatch = false;
        isDAxisMouseMatch = false;
        isQAxisMouseMatch = false;
        mousePositionX = e.getX();
        mousePositionY = e.getY();
        if ((mousePositionX <= 360) && (mousePositionX >= 80)) {
            int position = (int) ((360 - mousePositionX) / 7);
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

     /** initialize reference speed background data array, this function is called
     * by {@link #GraphicAreaVoltage(javax.swing.JScrollBar, int)   }
     */
    public void initOtherDataArray() {
        for (int i = 0; i < 480; i++) {
            dAxisVoltageDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), 300));
            qAxisVoltageDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), 300));
        }
    }

    /** display reference speed curve, this function Overrides the parent function
     *  and is called by {@link com.dong.graphic.GraphicArea#displayGraphicData(int)  }.
     * @see com.dong.graphic.GraphicArea#displayOtherGraphic(int)
     * @param size int, the data array size
     */
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

     /** Override parents function, update foreground data according to the scrollBar
     *  position from background data array, this function is called by
     * {@link com.dong.graphic.GraphicArea#paintComponent(java.awt.Graphics)  }.
     *  @param  value int, the position of scrollBar.
     *  @see com.dong.graphic.GraphicArea#updateOtherDataArray(int)
     */
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

    /** Override parent function, this funcion is called by
     * {@link com.dong.graphic.GraphicArea#timeShiftForDataBaseArray(int)  }
     * of parent class.
     * @param  i int, the size of data array.
     * @see com.dong.graphic.GraphicArea#timeShiftForOhterDataBaseArray(int)
     */
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

     /** set reference background data array.
     * @param dAxisVoltageDataBaseArray ArrayList, the reference background data array.
     */
    public void setdAxisVoltageDataBaseArray(ArrayList dAxisVoltageDataBaseArray) {
        this.dAxisVoltageDataBaseArray = dAxisVoltageDataBaseArray;
    }

     /** set reference background data array.
     * @param qAxisVoltageDataBaseArray ArrayList, the reference background data array.
     */
    public void setqAxisVoltageDataBaseArray(ArrayList qAxisVoltageDataBaseArray) {
        this.qAxisVoltageDataBaseArray = qAxisVoltageDataBaseArray;
    }

    @Override
    public void remakeDataBaseArray() {
        int screenDAxisVoltage, screenQAxisVoltage, screenBusVoltage;
        String tmpStr;
        Point tmpPoint = new Point();
        for (int i = 0; i < 480; i++) {

            Object realVoltage = (Object) ConstantFactory.realVoltageArray.get(i);

            tmpStr = realVoltage.toString();

            float voltageData = Float.valueOf(tmpStr);
            int intVoltageData = (int)(voltageData*1000);

            screenBusVoltage = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenData();
            tmpPoint = (Point) (ConstantFactory.voltageDataBaseArray.get(i));
            tmpPoint.y = screenBusVoltage;
            ConstantFactory.voltageDataBaseArray.set(i, tmpPoint);

            realVoltage = (Object) ConstantFactory.realDAxisVoltageArray.get(i);
            tmpStr = realVoltage.toString();

            voltageData = Float.valueOf(tmpStr);
            intVoltageData = (int)(voltageData*1000);


            screenDAxisVoltage = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenData();
            tmpPoint = (Point) (ConstantFactory.dAxisVoltageDataBaseArray.get(i));
            tmpPoint.y = screenDAxisVoltage;
            ConstantFactory.dAxisVoltageDataBaseArray.set(i, tmpPoint);

            realVoltage = (Object) ConstantFactory.realQAxisVoltageArray.get(i);
            tmpStr = realVoltage.toString();

            voltageData = Float.valueOf(tmpStr);
            intVoltageData = (int)(voltageData*1000);


            screenQAxisVoltage = new VoltageDataTransfer(intVoltageData, ConstantFactory.voltageRange).getScreenData();
            tmpPoint = (Point) (ConstantFactory.qAxisVoltageDataBaseArray.get(i));
            tmpPoint.y = screenQAxisVoltage;
            ConstantFactory.qAxisVoltageDataBaseArray.set(i, tmpPoint);

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
