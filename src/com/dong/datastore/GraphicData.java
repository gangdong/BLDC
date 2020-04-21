/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.datastore;

import com.dong.factory.ConstantFactory;
import java.awt.Point;
import java.util.ArrayList;

/**
 * GraphicData class defines the characters of the graphic data that to be displayed
 * on the screen.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class GraphicData {

    /**
     * the collection data point.
     */
    private ArrayList pointList = new ArrayList();
    /**
     * the number of data point.
     */
    private int pointNum = 41;

    /**
     * consturctor, initialize pointlist.
     */
    public GraphicData(Point point) {
        for (int i = 0; i < 41; i++) {
            pointList.add(new Point(point.x + (ConstantFactory.xLength / 40) * i, point.y - ConstantFactory.yLength / 2));
        }
    }

    /**
     * overloading constructor.
     */
    public GraphicData(Point point, int xLength, int yLength) {
        for (int i = 0; i < pointNum; i++) {
            pointList.add(new Point(point.x + (xLength / (pointNum - 1)) * i, point.y - yLength / 2));
        }
    }

    /**
     * return class member.
     */
    public ArrayList getPointList() {
        return pointList;
    }

    /**
     * update point list.
     */
    public void updatePointList(int index, int[] spdOrVolOrCur, ArrayList arrayListType) {

        if (index < 480) {
            for (int i = 0; i < pointNum; i++) {
                Point pTmp = (Point) arrayListType.get(i + (480-1) - index);
                pTmp.x = spdOrVolOrCur[i];
                pointList.set(i, pTmp);

            }
        }

    }
}
