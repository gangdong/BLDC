/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dong.graphicviewer;

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
 */
public class GraphicViewerCurrent extends GraphicViewer{

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
     * @see com.dong.graphic.GraphicArea#GraphicArea(javax.swing.JScrollBar)
     */
    public GraphicViewerCurrent(JScrollBar scrollBar,int mag) {
        super(scrollBar,mag);
        super.setRange(ConstantFactory.currentRange, "mA", 68,1);
        initOtherDataArray();
    }

     /** initialize q-axis current background data array, this function is called
     * by {@link #GraphicViewerCurrent(javax.swing.JScrollBar, int) g  }
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

    @Override
    public void displayRealData() {

        
        if (isMouseMatch == true) {
            g2d.setColor(Color.RED);
            int y = mousePoint.y;
            CurrentDataTransfer transfer = new CurrentDataTransfer(range);
            transfer.setScreenData(y);
            int current;
            if(mag == 2){
               current = transfer.getRealDataForViewerX5();
            }
            else{
                current = transfer.getRealDataForViewer();
            }

            g2d.drawOval(mousePoint.x, mousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("D-Axis Current:", mousePoint.x - 5, mousePoint.y - 20);
            g2d.drawString(String.valueOf(current) + "mA", mousePoint.x - 5, mousePoint.y - 7);
        }
        if (isQAxisMouseMatch == true) {
            g2d.setColor(Color.green);
            int y = qAxisMousePoint.y;
            CurrentDataTransfer transfer = new CurrentDataTransfer(range);
            transfer.setScreenData(y);

           int current;
            if(mag == 2){
               current = transfer.getRealDataForViewerX5();
            }
            else{
                current = transfer.getRealDataForViewer();
            }

            g2d.drawOval(qAxisMousePoint.x, qAxisMousePoint.y, 5, 5);
            g2d.setFont(new Font(null, Font.BOLD, 12));
            g2d.drawString("Q-Axis Current:", qAxisMousePoint.x + 5, qAxisMousePoint.y + 17);
            g2d.drawString(String.valueOf(current) + "mA", qAxisMousePoint.x + 5, qAxisMousePoint.y + 30);
        }
         

    }

    @Override
    public void mouseMovedAction(MouseEvent e) {
       isMouseMatch = false;
       isQAxisMouseMatch = false;
        mousePositionX = e.getX();
        mousePositionY = e.getY();
        if ((mousePositionX <= width_x - (width_x-x_scale_length-z0.x)) && (mousePositionX >= z0.x)) {
            int position = (int) (((width_x - (width_x-x_scale_length-z0.x)) - mousePositionX) / (x_scale_length/40));
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

    @Override
    public void timeShiftForOhterDataBaseArray(int i) {
       Point pTmps = (Point) qAxisDataBaseArray.get(i - 1);
        Point pTmpd = (Point) qAxisDataBaseArray.get(i);
        pTmpd.y = pTmps.y;
        qAxisDataBaseArray.set(i, pTmpd);
    }

    public void setqAxisDataBaseArray(ArrayList qAxisDataBaseArray) {
        this.qAxisDataBaseArray = qAxisDataBaseArray;
    }

 
    public void reMakeDataBaseArray() {
       int screenDAxisCurrent, screenQAxisCurrent,screenDAxisCurrentX5,screenQAxisCurrentX5, xScale;
        String tmpStr;
        Point tmpPoint = new Point();
        for (int i = 0; i < 480; i++) {

            Object realCurrent = (Object) ConstantFactory.realDAxisCurrentArray.get(i);
            tmpStr = realCurrent.toString();
            screenDAxisCurrent = new CurrentDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.currentRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.dAxisCurrentDataBaseArrayViewer.get(i));
            tmpPoint.y = screenDAxisCurrent;
            ConstantFactory.dAxisCurrentDataBaseArrayViewer.set(i, tmpPoint);

            screenDAxisCurrentX5 = new CurrentDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.currentRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.dAxisCurrentDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenDAxisCurrentX5;
            ConstantFactory.dAxisCurrentDataBaseArrayViewerX5.set(i, tmpPoint);


            realCurrent = (Object) ConstantFactory.realQAxisCurrentArray.get(i);
            tmpStr = realCurrent.toString();
            screenQAxisCurrent = new CurrentDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.currentRange).getScreenDataViewer();
            tmpPoint = (Point) (ConstantFactory.qAxisCurrentDataBaseArrayViewer.get(i));
            tmpPoint.y = screenQAxisCurrent;
            ConstantFactory.qAxisCurrentDataBaseArrayViewer.set(i, tmpPoint);

            screenQAxisCurrentX5 = new CurrentDataTransfer(Integer.parseInt(tmpStr), ConstantFactory.currentRange).getScreenDataViewerX5();
            tmpPoint = (Point) (ConstantFactory.qAxisCurrentDataBaseArrayViewerX5.get(i));
            tmpPoint.y = screenQAxisCurrentX5;
            ConstantFactory.qAxisCurrentDataBaseArrayViewerX5.set(i, tmpPoint);
        }
    }


}
