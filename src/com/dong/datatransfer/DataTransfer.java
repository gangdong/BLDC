/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dong.datatransfer;

/**
 *
 * @author Dong Gang
 */
public abstract class DataTransfer {

    protected int realData,screenData,screenDataViewer,screenDataViewerX5,range;

    public DataTransfer(int realData,int range) {
        this.realData = realData;
        this.range = range;
    }

    public DataTransfer(int range){
        this.range = range;
    }

    public abstract int getScreenData();
    public abstract int getRealData();
    public abstract void setScreenData(int screenData);

}



