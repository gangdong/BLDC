/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.graphic;

import com.dong.datastore.GraphicData;
import com.dong.factory.ConstantFactory;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

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
public abstract class GraphicArea extends JPanel implements MouseMotionListener {

    /** This is the width and height of this panel.
     * 
     */
    protected int width_x = 375, height_y = 340;
    /** X-axis length (pixels) {@link com.dong.factory.ConstantFactory#xLength }
     */
    //protected final int x_scale_length = 280, y_scale_length = 250;
    protected int x_scale_length = ConstantFactory.xLength;
    /** Y-axis length (pixels) {@link com.dong.factory.ConstantFactory#yLength }
     */
    protected int y_scale_length = ConstantFactory.yLength;
    /** original point position
     * {@link com.dong.graphic.GraphicArea#z0 }
     */
    //protected final Point z0 = new Point(80, 300);
    protected Point z0 = ConstantFactory.z0;
    /** end point position of X-axis
     */
    protected Point x0;
    /** end point position of Y-axis
     */
    protected Point y0;
    /** Graphics2D object
     */
    protected Graphics2D g2d;
    /** local JScrollBar
     */
    protected JScrollBar scrollBar;
    /* 前台数据 */
    /** the point array of curve needs displaying on the screen
     */
    protected GraphicData dataArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** the point array of scale needs displaying on the screen
     */
    protected GraphicData scaleDataArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** the character array needs displaying on the screen
     */
    public GraphicData scaleStringArray = new GraphicData(z0, x_scale_length, y_scale_length);
    /** the X-axis scale
     */

  //  protected final int perSamplePoint = 8;

    protected final int xScale[] = new int[41];
    /** the background data point array of curve
     */
    protected ArrayList dataBaseArray = new ArrayList();
    /** the background data point array of scale
     */
    public ArrayList scaleDataBaseArray = new ArrayList();
    /** the background data point array of character
     */
    public ArrayList scaleStringBaseArray = new ArrayList();
    /** the time scale character of foreground
     */
    public String[] timeScaleStringArray = new String[41];
    /** the time scale character of background
     */
    public String[] timeScaleString = new String[481];
    /** boolean value, indicates whether the mouse matches the point need to be
     * displayed
     */
    protected boolean isMouseMatch = false;
    /** records the current mouse position
     */
    protected int mousePositionX, mousePositionY;
    /** the current mouse position point
     */
    protected Point mousePoint = new Point();
    protected int range = 0;
    protected String unitName = "";
    protected int space = 0;
    protected int factor = 1;

    /** constructor
     * 
     */
    public GraphicArea() {
    }

    /**
     * Constructor,Creates a new JPanel with the specified layout manager and Paint
     * strategy.
     *
     * @param scrollBar JScrollBar, the JScrollBar to be loaded
     */
    public GraphicArea(JScrollBar scrollBar) {
        
        /* 加载滚动条 */
        this.scrollBar = scrollBar;
        /* 设定属性 */
        this.setSize(width_x, height_y);
        this.setBackground(Color.black);
        this.setForeground(Color.RED);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        /* 增加 鼠标监听 */
        this.addMouseMotionListener(this);
        /* 初始化 */
        initPoint();
        initTimeScaleString();
        initDataBaseArray();
        initXScale();

    }

    public void initComponents() {
        /* 初始化 */
        initPoint();
        initTimeScaleString();
        initDataBaseArray();
        initXScale();
    }

    public void setRange(int range, String str, int space,int factor) {
        this.range = range;
        this.unitName = str;
        this.space = space;
        this.factor = factor;
    }

    /**
     * Respond to the mouse drag event, implements the MouseMotionListener
     * interface
     * @param e MouseEvent, object of the mouse drag event
     */
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Respond to the mouse move event, implements the MouseMotionListener
     * interface
     * @param e  MouseEvent, object of the mouse move event
     */
    public void mouseMoved(MouseEvent e) {

        mouseMovedAction(e);

    }

    /**
     * abstract function, sub-class should realize this function
     * @param e MouseEvent, object of the mouse event
     */
    public abstract void mouseMovedAction(MouseEvent e);

    /**
     * load the background database
     * @param dataBaseArray ArrayList, call for the database to be loaded
     */
    public void setDataBaseArray(ArrayList dataBaseArray) {
        this.dataBaseArray = dataBaseArray;
    }

    /**
     * acquire the foreground database
     */
    public GraphicData getDataArray() {
        return dataArray;
    }

    /**
     * load the background database
     *
     */
    public ArrayList getDataBaseArray() {
        return dataBaseArray;
    }

    /**
     * load the JScrollbar
     * @param scrollBar JScrollBar, the scrollBar to be loaded
     */
    public void setScrollBar(JScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    /**
     * initialize end point of x-axis and y-axis
     *
     */
    public void initPoint() {
        x0 = new Point(z0.x + x_scale_length, z0.y);
        y0 = new Point(z0.x, z0.y - y_scale_length);
    }

    /**
     * call paintComponent(g) function
     * @param g Graphics, object of graphics
     */
    protected void clear(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * override the paintComponent of parent class
     * @param g Graphics, object of graphics
     */
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
            int label = (-range + range / 5 * i);
            int factorLabel = label/factor;
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

    /**
     * abstract class, called by paintComponent(Graphics) function. sub-class should realize
     * this function for exetending function.
     * @param value int, the size of database
     */
    public abstract void updateOtherDataArray(int value);

    /**
     * draw zero scale line
     * @param p Point, original point
     */
    void drawZeroLine(Point p) {
        Point pzx = new Point(p.x, p.y - y_scale_length / 2);
        Point pzy = new Point(p.x + x_scale_length, p.y - y_scale_length / 2);

        Line2D.Float zAxis = new Line2D.Float(pzx, pzy);
        g2d.draw(zAxis);
    }

    /**
     * initialize x scale
     *
     */
    void initXScale() {
        for (int i = 0; i < 41; i++) {
            xScale[i] = z0.x + x_scale_length - i * ((x_scale_length) / 40);
        }
    }

    /**
     * initialize background database
     *
     */
    public void initDataBaseArray() {
        /* 曲线点的 数据 */
        for (int i = 0; i < 480; i++) {
            dataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), z0.y - y_scale_length / 2));
        }
        /* 坐标点的 数据 */
        for (int i = 0; i < 480; i++) {
            if (i % 8 == 0) {
                scaleDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), z0.y + 5));
                scaleStringBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), z0.y + 20));

            } else {
                scaleDataBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), z0.y));
                scaleStringBaseArray.add(new Point(z0.x + (x_scale_length - (x_scale_length / 40) * i), z0.y));

            }
        }
    }

    /**
     * redraw time scale when jscrollbar value is changged
     * @param index int, the size of timescale dataarray
     */
    public void updateTimeScaleString(int index) {
        for (int i = 0; i < 41; i++) {
            timeScaleStringArray[i] = timeScaleString[i + 479 - index];
        }
    }

    /**
     * initialize time scale
     *
     */
    public void initTimeScaleString() {
        for (int i = 0; i < 481; i++) {
            if (0 == (i % 8)) {
                int j = i / 8;
                timeScaleString[i] = "-" + String.valueOf(j) + "sec";
            } else {
                timeScaleString[i] = "";
            }
        }
    }

    /**
     * abstract function,called by function displaygraphic(). sub-class can extend
     * function by realizing this function.
     * @param size int,the size of point to be paint
     */
    public abstract void displayOtherGraphic(int size);

    /**
     * abstarct function, called by displayRealData().
     *
     */
    public abstract void displayRealData();

    /**
     * paint screen
     * @param size int,the size of pointArray need painting
     */
    void displayGraphicData(int size) {

        Point srcPoint = new Point();
        Point desPoint = new Point();
        for (int i = 1; i < size; i++) {
            /* 划速度曲线 */
            g2d.setColor(Color.red);
            srcPoint = (Point) dataArray.getPointList().get(i);
            desPoint = (Point) dataArray.getPointList().get(i - 1);
            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 1);
            path.moveTo(srcPoint.x, srcPoint.y);
            path.lineTo(desPoint.x, desPoint.y);
            g2d.draw(path);

            /* 划速度坐标点 */
            g2d.setColor(Color.WHITE);
            srcPoint = (Point) scaleDataArray.getPointList().get(i);
            desPoint = new Point(xScale[i], z0.y);
            Line2D.Float speedAxis = new Line2D.Float(srcPoint, desPoint);
            g2d.draw(speedAxis);

            /* 划速度坐标点字符串 */
            g2d.setColor(Color.WHITE);
            srcPoint = (Point) scaleStringArray.getPointList().get(i);
            if (srcPoint.y == z0.y + 20) {
                g2d.drawString(timeScaleStringArray[i], srcPoint.x - 5, srcPoint.y);
            } else {
            }
        }
        displayOtherGraphic(size);
    }

    /**
     * update background data array when the timekick arrived
     * @param i int, the length of the data base array
     */
    public void timeShiftForDataBaseArray(int i) {
        while (i > 0) {
            Point pTmps = (Point) dataBaseArray.get(i - 1);
            Point pTmpd = (Point) dataBaseArray.get(i);
            pTmpd.y = pTmps.y;
            dataBaseArray.set(i, pTmpd);
            timeShiftForOhterDataBaseArray(i);
            i--;
        }

    }

    /**
     * abstarct function, called by timeShiftForDataBaseArray()
     * @param i int, the length of the data base array
     */
    public abstract void timeShiftForOhterDataBaseArray(int i);

    public void setHeight_y(int height_y) {
        this.height_y = height_y;
    }

    public void setWidth_x(int width_x) {
        this.width_x = width_x;
    }

    public void setZ0(Point z0) {
        this.z0 = z0;
    }

    public void setX_scale_length(int x_scale_length) {
        this.x_scale_length = x_scale_length;
    }

    public void setY_scale_length(int y_scale_length) {
        this.y_scale_length = y_scale_length;
    }

    
 public abstract void remakeDataBaseArray();
   

}
