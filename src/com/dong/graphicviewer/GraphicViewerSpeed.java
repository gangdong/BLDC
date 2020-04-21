/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphicviewer;

import com.dong.datastore.GraphicData;
import com.dong.datatransfer.SpeedDataTransfer;
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
 */
public class GraphicViewerSpeed extends GraphicViewer {

    /** represents the reference points which are displayed on the screen.
     */
    private GraphicData refDataArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** represents the background data array of reference speed
     */
    private ArrayList refDataBaseArray = new ArrayList();
    /** boolean value, indicates if the mouse position matches with the display point.
     */
    protected boolean isRefMouseMatch = false;
    /** mouse position point.
     */
    protected Point refMousePoint = new Point();

    /** constructor of this class
     * @param scrollBar JScrollBar, calls super constructor
     * @see com.dong.graphic.GraphicArea#GraphicArea(javax.swing.JScrollBar)
     */
    public GraphicViewerSpeed(JScrollBar scrollBar, int mag) {
        super(scrollBar, mag);
        super.setRange(ConstantFactory.speedRange, "rpm", 68,1);
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
            SpeedDataTransfer transfer = new SpeedDataTransfer(range);
            transfer.setScreenData(y);
            int speed;
            if (mag == 2) {
                speed = transfer.getRealDataForViewerX5();
            } else {
                speed = transfer.getRealDataForViewer();
            }

            g2d.drawOval(mousePoint.x, mousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("Measured Speed:", mousePoint.x - 5, mousePoint.y - 20);
            g2d.drawString(String.valueOf(speed) + "RPM", mousePoint.x - 5, mousePoint.y - 7);
        }
        if (isRefMouseMatch == true) {
            g2d.setColor(Color.yellow);
            int y = refMousePoint.y;
            SpeedDataTransfer transfer = new SpeedDataTransfer(range);
            transfer.setScreenData(y);

            int speed;
            if (mag == 2) {
                speed = transfer.getRealDataForViewerX5();
            } else {
                speed = transfer.getRealDataForViewer();
            }

            g2d.drawOval(refMousePoint.x, refMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("Reference Speed:", refMousePoint.x + 5, refMousePoint.y + 17);
            g2d.drawString(String.valueOf(speed) + "RPM", refMousePoint.x + 5, refMousePoint.y + 30);
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
        isRefMouseMatch = false;
        mousePositionX = e.getX();
        mousePositionY = e.getY();
        if ((mousePositionX <= width_x - (width_x - x_scale_length - z0.x)) && (mousePositionX >= z0.x)) {
            int position = (int) (((width_x - (width_x - x_scale_length - z0.x)) - mousePositionX) / (x_scale_length / 40));
            mousePoint = (Point) dataArray.getPointList().get(position);
            refMousePoint = (Point) refDataArray.getPointList().get(position);
            int uplim = mousePoint.y + 5;
            int downlim = mousePoint.y - 5;
            int refUpLim = refMousePoint.y + 5;
            int refDownlim = refMousePoint.y - 5;

            if ((mousePositionY >= downlim) && (mousePositionY <= uplim)) {

                isMouseMatch = true;
            }
            if ((mousePositionY >= refDownlim) && (mousePositionY <= refUpLim)) {
                isRefMouseMatch = true;
            }
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

        Point pTmps = (Point) refDataBaseArray.get(i - 1);
        Point pTmpd = (Point) refDataBaseArray.get(i);
        pTmpd.y = pTmps.y;
        refDataBaseArray.set(i, pTmpd);

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
            srcPoint = (Point) refDataArray.getPointList().get(i);
            desPoint = (Point) refDataArray.getPointList().get(i - 1);
            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 1);
            path.moveTo(srcPoint.x, srcPoint.y);
            path.lineTo(desPoint.x, desPoint.y);
            g2d.draw(path);
        }

    }

    /** initialize reference speed background data array, this function is called
     * by {@link #GraphicViewerSpeed(javax.swing.JScrollBar, int) gr  }
     */
    public void initOtherDataArray() {
        for (int i = 0; i < 480; i++) {
            refDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), 300));
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
            refDataArray.updatePointList(40, xScale, refDataBaseArray);
        } else {
            refDataArray.updatePointList(value, xScale, refDataBaseArray);
        }
    }

    /** set reference background data array.
     * @param refDataBaseArray ArrayList, the reference background data array.
     */
    public void setRefDataBaseArray(ArrayList refDataBaseArray) {
        this.refDataBaseArray = refDataBaseArray;
    }

    public void reMakeDataBaseArray() {
        int screenSpeed, screenReferenceSpeed,screenSpeedX5,screenReferenceSpeedX5, xScale;
        String tmpStr;
        Point tmpPoint = new Point();
        for (int i = 0; i < 480; i++) {

            Object realSpeed = (Object) ConstantFactory.realSpeedArray.get(i);
            tmpStr = realSpeed.toString();
            screenSpeed = new SpeedDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.speedRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.speedDataBaseArrayViewer.get(i));
            tmpPoint.y = screenSpeed;
            ConstantFactory.speedDataBaseArrayViewer.set(i, tmpPoint);

            screenReferenceSpeed = new SpeedDataTransfer(ConstantFactory.refSpeed, ConstantFactory.speedRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.refSpeedDataBaseArrayViewer.get(i));
            tmpPoint.y = screenReferenceSpeed;
            ConstantFactory.refSpeedDataBaseArrayViewer.set(i, tmpPoint);

            screenSpeedX5 = new SpeedDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.speedRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.speedDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenSpeedX5;
            ConstantFactory.speedDataBaseArrayViewerX5.set(i, tmpPoint);

            screenReferenceSpeedX5 = new SpeedDataTransfer(ConstantFactory.refSpeed, ConstantFactory.speedRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.refSpeedDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenReferenceSpeedX5;
            ConstantFactory.refSpeedDataBaseArrayViewerX5.set(i, tmpPoint);
        }

    }
}
