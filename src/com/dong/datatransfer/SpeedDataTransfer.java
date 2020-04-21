/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dong.datatransfer;

import com.dong.factory.ConstantFactory;

/**
 *
 * @author Dong Gang
 */
public class SpeedDataTransfer extends DataTransfer{

    public SpeedDataTransfer(int realData,int range) {
        super(realData,range);
    }

    public SpeedDataTransfer(int range) {
        super(range);
    }

    @Override
    public void setScreenData(int screenData) {
            this.screenData = screenData;
    }


    @Override
    public int getScreenData() {

        if(realData>=0){
            if(realData<range){
                 screenData = 175 - (int)(realData/((range*2)/250));
            }
            else{}
        }
        else{
            if(realData>-12500){
                 screenData = 175+(int)(Math.abs(realData)/((range*2)/250));
            }
            else{}
        }
        return screenData;
    }

    @Override
    public int getRealData() {
        if(screenData>175){
            realData = -((screenData -175)*((range*2)/250));
        }
        else{
            realData = (175-screenData)*((range*2)/250);
        }
        return realData;
    }

    public int getRealDataForViewer(){
        if(screenData>260){
             realData = -((screenData -260)*((range)/250));
        }
        else{
            realData = (260-screenData)*((range)/250);
        }
        return realData;
    }
    public int getRealDataForViewerX5(){
        if(screenData>260){
             realData = -((screenData -1300)*((range)/(250*5)));
        }
        else{
            realData = (1300-screenData)*((range)/(250*5));
        }
        return realData;
    }
    public int getScreenDataViewer() {
        if(realData>=0){
            if(realData<range){
                 screenDataViewer = (ConstantFactory.z0Viewer.y - ConstantFactory.yLengthViewer/2) - (int)(realData/((range)/250));
            }
            else{}
        }
        else{
            if(realData>-range){
                 screenDataViewer = (ConstantFactory.z0Viewer.y - ConstantFactory.yLengthViewer/2)+(int)(Math.abs(realData)/((range)/250));
            }
            else{}
        }
        return screenDataViewer;
    }

    public int getScreenDataViewerX5() {
        if(realData>=0){
            if(realData<range){
                 screenDataViewerX5 = (ConstantFactory.z0Viewer.y*5 - (ConstantFactory.yLengthViewer*5)/2) - (int)(realData/((range)/(250*5)));
            }
            else{}
        }
        else{
            if(realData>-range){
                 screenDataViewerX5 = (ConstantFactory.z0Viewer.y*5 - (ConstantFactory.yLengthViewer*5)/2)+(int)(Math.abs(realData)/((range)/(250*5)));
            }
            else{}
        }
        return screenDataViewerX5;

    }



}
