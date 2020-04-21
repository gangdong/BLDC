/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphic;

import com.dong.datastore.GraphicData;
import com.dong.datatransfer.CurrentDataTransfer;
import com.dong.factory.ConstantFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import javax.swing.JScrollBar;

/**
 *
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 * This is the sub-class class extends the GraphicArea class, this class represents
 * the current display area on the screen.
 *
 */
public class GraphicAreaCurrent extends GraphicArea {

    /** represents the q-axis current points which are displayed on the screen.
     */
    private GraphicData qAxisDataArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** represents the q-axis current points which are background datas.
     */
    private ArrayList qAxisDataBaseArray = new ArrayList();
    /** boolean value, indicates if the mouse position matches with the display point.
     */
    protected boolean isQAxisMouseMatch = false;
    /** q-axis current mouse position point.
     */
    protected Point qAxisMousePoint = new Point();

    /** constructor of this class
     * @param scrollBar JScrollBar, calls super constructor
     * {@link com.dong.graphic.GraphicArea#GraphicArea(javax.swing.JScrollBar) }
     * @see com.dong.graphic.GraphicArea#GraphicArea(javax.swing.JScrollBar)
     */
    public GraphicAreaCurrent(JScrollBar scrollBar,int range) {
        super(scrollBar);
        super.setRange(range, "mA", 60,1);
        initOtherDataArray();
    }

    /** Override parents function,display the real data of q-axis current.
     * this function is called by {@link com.dong.graphic.GraphicArea#paintComponent(java.awt.Graphics)  }
     * @see com.dong.graphic.GraphicArea#displayRealData()
     */
    @Override
    public void displayRealData() {
        if (isMouseMatch == true) {
            g2d.setColor(Color.RED);
            int y = mousePoint.y;
            CurrentDataTransfer transfer = new CurrentDataTransfer(range);
            transfer.setScreenData(y);
            int current = transfer.getRealData();

            g2d.drawOval(mousePoint.x, mousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("d-Axis Current:", mousePoint.x - 5, mousePoint.y - 20);
            g2d.drawString(String.valueOf(current) + "mA", mousePoint.x - 5, mousePoint.y - 7);
        }
        if (isQAxisMouseMatch == true) {
            g2d.setColor(Color.green);
            int y = qAxisMousePoint.y;
            CurrentDataTransfer transfer = new CurrentDataTransfer(range);
            transfer.setScreenData(y);
            int current = transfer.getRealData();

            g2d.drawOval(qAxisMousePoint.x, qAxisMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("q-Axis Current:", qAxisMousePoint.x + 5, qAxisMousePoint.y + 17);
            g2d.drawString(String.valueOf(current) + "mA", qAxisMousePoint.x + 5, qAxisMousePoint.y + 30);
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
        isQAxisMouseMatch = false;
        mousePositionX = e.getX();
        mousePositionY = e.getY();
        if ((mousePositionX <= 360) && (mousePositionX >= 80)) {
            int position = (int) ((360 - mousePositionX) / 7);
            mousePoint = (Point) dataArray.getPointList().get(position);
            qAxisMousePoint = (Point) qAxisDataArray.getPointList().get(position);
            int uplim = mousePoint.y + 5;
            int downlim = mousePoint.y - 5;
            int refUpLim = qAxisMousePoint.y + 5;
            int refDownlim = qAxisMousePoint.y - 5;

            if ((mousePositionY >= downlim) && (mousePositionY <= uplim)) {

                isMouseMatch = true;
            }
            if ((mousePositionY >= refDownlim) && (mousePositionY <= refUpLim)) {
                isQAxisMouseMatch = true;
            }
        }
    }

    /** display q-axis current curve, this function Overrides the parent function
     *  and is called by {@link com.dong.graphic.GraphicArea#displayGraphicData(int)  }.
     * @see com.dong.graphic.GraphicArea#displayOtherGraphic(int)
     * @param size int, the data array size
     */
    @Override
    public void displayOtherGraphic(int size) {
        Point srcPoint = new Point();
        Point desPoint = new Point();
        for (int i = 1; i < size; i++) {
            g2d.setColor(Color.green);
            srcPoint = (Point) qAxisDataArray.getPointList().get(i);
            desPoint = (Point) qAxisDataArray.getPointList().get(i - 1);
            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 1);
            path.moveTo(srcPoint.x, srcPoint.y);
            path.lineTo(desPoint.x, desPoint.y);
            g2d.draw(path);
        }

    }

    /** initialize q-axis current background data array, this function is called
     * by {@link #GraphicAreaCurrent(javax.swing.JScrollBar, int)   }
     */
    public void initOtherDataArray() {
        for (int i = 0; i < 480; i++) {
            qAxisDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), 300));
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
            qAxisDataArray.updatePointList(40, xScale, qAxisDataBaseArray);
        } else {
            qAxisDataArray.updatePointList(value, xScale, qAxisDataBaseArray);
        }

    }

    /** set q-axis current background data array.
     * @param refDataBaseArray ArrayList, the reference background data array.
     */
    public void setQAxisDataBaseArray(ArrayList refDataBaseArray) {
        this.qAxisDataBaseArray = refDataBaseArray;
    }

    /** Override parent function, this funcion is called by 
     * {@link com.dong.graphic.GraphicArea#timeShiftForDataBaseArray(int)  }
     * of parent class.
     * @param  i int, the size of data array.
     * @see com.dong.graphic.GraphicArea#timeShiftForOhterDataBaseArray(int)
     */
    @Override
    public void timeShiftForOhterDataBaseArray(int i) {

        Point pTmps = (Point) qAxisDataBaseArray.get(i - 1);
        Point pTmpd = (Point) qAxisDataBaseArray.get(i);
        pTmpd.y = pTmps.y;
        qAxisDataBaseArray.set(i, pTmpd);

    }

    @Override
    public void remakeDataBaseArray() {
        int screenDAxisCurrent, screenQAxisCurrent, xScale;
        String tmpStr;
        Point tmpPoint = new Point();
        for (int i = 0; i < 480; i++) {

            Object realCurrent = (Object) ConstantFactory.realDAxisCurrentArray.get(i);
            tmpStr = realCurrent.toString();
            screenDAxisCurrent = new CurrentDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.currentRange).getScreenData();
            tmpPoint = (Point) (ConstantFactory.dAxisCurrentDataBaseArray.get(i));
            tmpPoint.y = screenDAxisCurrent;
            ConstantFactory.dAxisCurrentDataBaseArray.set(i, tmpPoint);

            realCurrent = (Object) ConstantFactory.realQAxisCurrentArray.get(i);
            tmpStr = realCurrent.toString();
            screenQAxisCurrent = new CurrentDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.currentRange).getScreenData();
            tmpPoint = (Point) (ConstantFactory.qAxisCurrentDataBaseArray.get(i));
            tmpPoint.y = screenQAxisCurrent;
            ConstantFactory.qAxisCurrentDataBaseArray.set(i, tmpPoint);

        }
    }
}
