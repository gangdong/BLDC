/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.tablemodel;



import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

/**
 * this class extends {@link javax.swing.table.DefaultTableModel}.
 * this class is setted as the model of {@link com.dong.tablemodel.XTable}
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class ResultTableModel extends DefaultTableModel {

    /**
     * column class type. totally has 9 columns.
     */
    Class[] classType = {Integer.class, Double.class, Integer.class,Float.class,Float.class,Float.class,Integer.class,Integer.class,Integer.class};

    /**
     * data container of column 1.
     */
    ArrayList time = new ArrayList();
    /**
     * data container of column 2.
     */
    ArrayList refSpeed = new ArrayList();
    /**
     * data container of column 3.
     */
    ArrayList speed = new ArrayList();
    /**
     * data container of column 4.
     */
    ArrayList voltage = new ArrayList();
    /**
     * data container of column 5.
     */
    ArrayList dVoltage = new ArrayList();
    /**
     * data container of column 6.
     */
    ArrayList qVoltage = new ArrayList();
    /**
     * data container of column 7.
     */
    ArrayList dCurrent = new ArrayList();
    /**
     * data container of column 8.
     */
    ArrayList qCurrent = new ArrayList();


    /**
     * constructor method,call super constructor method.
     * @param data Object[][], header Object[][]
     */
    public ResultTableModel(Object[][] data, Object[] header) {
        super(data, header);
    }
    /**
     *  constructor method.
     */
    public ResultTableModel() {
    }

    /**
     * setter method, set the class member -
     * {@link #time },{@link #refSpeed},{@link #speed},{@link #voltage},
     * {@link #dVoltage},{@link #qVoltage},{@link #dCurrent},{@link #qCurrent}.
     * @param time ArrayList,refSpeed ArrayList,speed Arraylist,voltage ArrayList,
     * dVoltage ArrayList,qVoltage ArrayList,dCurrent ArrayList,qCurrent ArrayList.
     */
    public void setParameters(ArrayList time,ArrayList refSpeed,ArrayList speed,ArrayList voltage,ArrayList dVoltage,ArrayList qVoltage,ArrayList dCurrent,
            ArrayList qCurrent){
        this.time = time;
        this.refSpeed = refSpeed;
        this.speed = speed;
        this.voltage = voltage;
        this.dVoltage = dVoltage;
        this.qVoltage = qVoltage;
        this.dCurrent = dCurrent;
        this.qCurrent = qCurrent;
    }

    /**
     * update table cell with value of
     * {@link #time },{@link #refSpeed},{@link #speed},{@link #voltage},
     * {@link #dVoltage},{@link #qVoltage},{@link #dCurrent},{@link #qCurrent}.
     */
    public void updateTableContent(){
        int length = time.size();
        Object[][] dataArray = new Object[length][9];
        /* 更新 Buffer */
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                dataArray[i][0] = i;
                dataArray[i][1] = time.get(i);
                dataArray[i][2] = refSpeed.get(i);
                dataArray[i][3] = speed.get(i);
                dataArray[i][4] = voltage.get(i);
                dataArray[i][5] = dVoltage.get(i);
                dataArray[i][6] = qVoltage.get(i);
                dataArray[i][7] = dCurrent.get(i);
                dataArray[i][8] = qCurrent.get(i);
            }
        }
        else{}
        /* 更新 Tabel 单元格 */
        if (length > 0) {
            for (int i = 0; i < length; i++) {

                this.setValueAt(dataArray[i][0], i, 0);
                this.setValueAt(dataArray[i][1], i, 1);
                this.setValueAt(dataArray[i][2], i, 2);
                this.setValueAt(dataArray[i][3], i, 3);
                this.setValueAt(dataArray[i][4], i, 4);
                this.setValueAt(dataArray[i][5], i, 5);
                this.setValueAt(dataArray[i][6], i, 6);
                this.setValueAt(dataArray[i][7], i, 7);
                this.setValueAt(dataArray[i][8], i, 8);

                if (this.getRowCount() < length) {
                    this.addRow(dataArray[i]);
                }
            }
        }
        else{}
        this.setRowCount(length);
    }

    /**
     * getter method, return {@link #dCurrent}
     */
    public ArrayList getdCurrent() {
        return dCurrent;
    }
    /**
     * getter method, return {@link #dVoltage }
     */
    public ArrayList getdVoltage() {
        return dVoltage;
    }
    /**
     * getter method, return {@link #qCurrent }
     */
    public ArrayList getqCurrent() {
        return qCurrent;
    }
    /**
     * getter method, return {@link #qVoltage }
     */
    public ArrayList getqVoltage() {
        return qVoltage;
    }
    /**
     * getter method, return {@link #speed }
     */
    public ArrayList getSpeed() {
        return speed;
    }
    /**
     * getter method, return {@link #time }
     */
    public ArrayList getTime() {
        return time;
    }

    /**
     * getter method, return {@link #voltage }
     */
    public ArrayList getVoltage() {
        return voltage;
    }

}
