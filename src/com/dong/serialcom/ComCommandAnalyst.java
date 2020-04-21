/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.serialcom;

import com.dong.factory.ComProtocolFactory;

/**
 *
 * @author Dong Gang
 */
public class ComCommandAnalyst {

    public byte[] msg_StopCan = new byte[3];
    public byte[] msg_ConfigAndStartCan = new byte[11];
    public byte[] msg_TestId = new byte[3];
    public byte[] msg_AutoTestSpeed = new byte[3];
    public byte[] msg_ClearSyncClock = new byte[3];
    public byte[] msg_StartSyncClock = new byte[3];
    public byte[] msg_CrcVal = new byte[3];
    public byte[] msg_ProgOver = new byte[3];

    public byte[] msg_CommChk = new byte[14];
    public byte[] msg_DwnSpeed = new byte[9];
    public byte[] msg_RstSpeed = new byte[6];
    public byte[] msg_DwnControl = new byte[14];


    public byte[] msg_RstCurrent = new byte[6];


    public ComCommandAnalyst() {
        msg_ConfigAndStartCan[0] = 13;
        msg_ConfigAndStartCan[1] = 9;
        msg_ConfigAndStartCan[2] = 0;
        msg_ConfigAndStartCan[3] = 0;
        msg_ConfigAndStartCan[4] = 0;
        msg_ConfigAndStartCan[5] = (byte) (0xc0 & 0xff);
        msg_ConfigAndStartCan[6] = (byte) ((0x4dc0 >> 8) & 0xff);
        msg_ConfigAndStartCan[7] = 3;
        msg_ConfigAndStartCan[8] = 0;
        msg_ConfigAndStartCan[9] = 0;
        msg_ConfigAndStartCan[10] = (byte) 0xaa;

        msg_StopCan[0] = 14;
        msg_StopCan[1] = 1;
        msg_StopCan[2] = (byte)0xaa;

        msg_TestId[0] = 15;
        msg_TestId[1] = 1;
        msg_TestId[2] = (byte)0xaa;


        msg_AutoTestSpeed[0] = 16;
        msg_AutoTestSpeed[1] =1;
        msg_AutoTestSpeed[2] =(byte)0xaa;

        msg_ClearSyncClock[0] = 21;
        msg_ClearSyncClock[1] = 1;
        msg_ClearSyncClock[2] = (byte)0xaa;

        msg_StartSyncClock[0] = 22;
        msg_StartSyncClock[1] = 1;
        msg_StartSyncClock[2] = (byte)0xaa;

        msg_CrcVal[0] = 29;
        msg_CrcVal[1] = 1;
        msg_CrcVal[2] = (byte)0xaa;

        msg_ProgOver[0] = 31;
        msg_ProgOver[1] = 1;
        msg_ProgOver[2] = (byte)0xaa;


        msg_CommChk[0] = ComProtocolFactory.msg_Type_Tx_Data_Std_Frame;
        msg_CommChk[1] = (byte)12;
        msg_CommChk[2] = (byte)0;
        msg_CommChk[3] = (byte)ComProtocolFactory.msg_Type_Motor_ComChk;
        msg_CommChk[4] = (byte)8;
        msg_CommChk[5] = (byte)0x55;
        msg_CommChk[6] = (byte)0x55;
        msg_CommChk[7] = (byte)0x55;
        msg_CommChk[8] = (byte)0x55;
        msg_CommChk[9] = (byte)0x55;
        msg_CommChk[10] = (byte)0x55;
        msg_CommChk[11] = (byte)0x55;
        msg_CommChk[12] = (byte)0x55;
        msg_CommChk[13] = (byte)0xaa;

        msg_RstSpeed[0] = ComProtocolFactory.msg_Type_Tx_Data_Std_Frame;
        msg_RstSpeed[1] = (byte)4;
        msg_RstSpeed[2] = (byte)0;
        msg_RstSpeed[3] = (byte)0x31;
        msg_RstSpeed[4] = (byte)0;
        msg_RstSpeed[5] = (byte)0xaa;

        msg_RstCurrent[0] = ComProtocolFactory.msg_Type_Tx_Data_Std_Frame;
        msg_RstCurrent[1] = (byte)4;
        msg_RstCurrent[2] = (byte)0;
        msg_RstCurrent[3] = (byte)0x33;
        msg_RstCurrent[4] = (byte)0;
        msg_RstCurrent[5] = (byte)0xaa;

    }

    public byte[] getConfigAndStartCan() {
        return msg_ConfigAndStartCan;
    }

    public byte[] getMsg_Stop_Can() {
        return msg_StopCan;
    }

    public byte[] getMsg_CommChk() {
        return msg_CommChk;
    }

    public void setMsg_DwnControl(byte[] msg_DwnControl){
        this.msg_DwnControl[0] = ComProtocolFactory.msg_Type_Tx_Data_Std_Frame;
        this.msg_DwnControl[1] = (byte)12;
        this.msg_DwnControl[2] = (byte)0;
        this.msg_DwnControl[3] = (byte)ComProtocolFactory.msg_Type_Motor_DwnControl;
        this.msg_DwnControl[4] = (byte)8;
        this.msg_DwnControl[5] = msg_DwnControl[0];
        this.msg_DwnControl[6] = msg_DwnControl[1];
        this.msg_DwnControl[7] = msg_DwnControl[2];
        this.msg_DwnControl[8] = msg_DwnControl[3];
        this.msg_DwnControl[9] = msg_DwnControl[4];
        this.msg_DwnControl[10] = msg_DwnControl[5];
        this.msg_DwnControl[11] = msg_DwnControl[6];
        this.msg_DwnControl[12] = msg_DwnControl[7];
        this.msg_DwnControl[13] = (byte)0xaa;
    }

    public void setMsg_DwnSpeed(byte[] msg_DwnSpeed) {
        this.msg_DwnSpeed[0] = ComProtocolFactory.msg_Type_Tx_Data_Std_Frame;
        this.msg_DwnSpeed[1] = (byte)7;
        this.msg_DwnSpeed[2] = (byte)0;
        this.msg_DwnSpeed[3] = (byte)ComProtocolFactory.msg_Type_Motor_DwnSpeed;
        this.msg_DwnSpeed[4] = (byte)3;
        this.msg_DwnSpeed[5] = msg_DwnSpeed[0];
        this.msg_DwnSpeed[6] = msg_DwnSpeed[1];
        this.msg_DwnSpeed[7] = msg_DwnSpeed[2];
        this.msg_DwnSpeed[8] = (byte)0xaa;
    }

    public byte[] getMsg_DwnSpeed() {
        return msg_DwnSpeed;
    }

    public byte[] getMsg_RstSpeed() {
        return msg_RstSpeed;
    }

    public byte[] getMsg_RstCurrent() {
        return msg_RstCurrent;
    }

    public byte[] getMsg_DwnControl() {
        return msg_DwnControl;
    }


}
