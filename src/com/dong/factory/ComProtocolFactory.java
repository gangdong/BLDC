/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.factory;

/**
 * this class defines the communication protocol format.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class ComProtocolFactory {

    /* CAN station command define */
    public static final int msg_Type_Rx_Data_Std_Frame = 0;
    public static final int msg_Type_Rx_Data_Ex_Frame = 1;
    public static final int msg_Type_Rx_Rem_Std_Frame = 2;
    public static final int msg_Type_Rx_Rem_Ex_Frame = 3;
    public static final int msg_Type_Tx_Data_Std_Frame = 4;
    public static final int msg_Type_Tx_Data_Ex_Frame = 5;
    public static final int msg_Type_Tx_Rem_Std_Frame = 6;
    public static final int msg_Type_Tx_Rem_Ex_Frame = 7;
    /* Error */
    public static final int msg_Type_Bus_Err = 8;
    /* Control */
    public static final int msg_Type_Config_Start_Can = 13;
    public static final int msg_Type_Stop_Can = 14;
    public static final int msg_Type_Tester_Id = 15;
    public static final int msg_Type_Auto_Detect_Speed = 16;
    public static final int msg_Type_Wr_Frame_List = 17;
    public static final int msg_Type_Finish_Frame_List = 18;
    public static final int msg_Type_Tm_St_Over = 19;
    public static final int msg_Type_Bus_Load = 20;
    public static final int msg_Type_Clear_Syn_Clock = 21;
    public static final int msg_Type_Start_Syn_Clock = 22;

    public static final int msg_Type_Program_Over = 31;

    /* Motor communication protocol */

    public static final int msg_Type_Motor_ComChk = 16;
    public static final int msg_Type_Motor_ComChkAck = 17;
    public static final int msg_Type_Motor_DwnSpeed = 32;
    public static final int msg_Type_Motor_DwnSpeedAck = 33;
    public static final int msg_Type_Motor_DwnControl = 34;
    public static final int msg_Type_Motor_DwnControlAck = 35;
    public static final int msg_Type_Motor_RstSpeed = 49;
    public static final int msg_Type_Motor_RstSpeedAck = 50;
    public static final int msg_Type_Motor_RstCurrent = 51;
    public static final int msg_Type_Motor_RstCurrentAck = 52;
    public static final int msg_Type_Motor_RstVoltage = 53;
    public static final int msg_Type_Motor_RstVoltageAck = 54;


    public ComProtocolFactory() {

    }

}
