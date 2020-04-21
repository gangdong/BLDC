/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.serialcom;

import com.dong.factory.ConstantFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.Vector;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

/**
 *
 * @author Dong Gang
 */
public class ComBean implements SerialPortEventListener {

    static String portName;
    static int baudrate;
    static int dataBit;
    static int stopBit;
    static int parityBit;
    CommPortIdentifier portId;
    SerialPort serialPort;
    static OutputStream out;
    static InputStream in;
    private static ComBean serialComInstance = new ComBean();
    ReadThread readThread = new ReadThread();
    private byte[] dataPool = new byte[1024];
    private int[] b = new int[10240];
    private static int readBufferIndex = 0;
    private boolean readDataEnd = false;
    SerialBuffer RB;
    ReadSerial RT;
    private boolean isComProOver = false;
    private boolean isConfigureAndStart = false;
    private boolean isComTestOk = false;
    private boolean isComOpen = false;

    private ComBean() {

        portName = "";
        baudrate = 2000000;
        dataBit = SerialPort.DATABITS_8;
        parityBit = SerialPort.PARITY_NONE;
        stopBit = SerialPort.STOPBITS_1;

    }

    public static ComBean getInstances() {
        return serialComInstance;
    }

    public boolean closePort() {
        boolean rst = true;
        String infoStr = "端口关闭";
        if (isComOpen) {
            try {
                in.close();
                out.close();
                RT.stop();
                serialPort.notifyOnDataAvailable(false);
                serialPort.removeEventListener();
                serialPort.close();
                isComProOver = false;
                isConfigureAndStart = false;
                isComTestOk = false;

                if (ConstantFactory.timerRun != 0) {
                    ConstantFactory.timerRun = (int) 0;
                    ConstantFactory.timer.cancel();
                } else {
                }
            } catch (IOException e) {
                rst = false;
            }
        } else {
            rst = false;
        }

        return rst;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        ComBean.portName = portName;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        switch (baudrate) {
            case 0:
                ComBean.baudrate = 300;
                break;
            case 1:
                ComBean.baudrate = 600;
                break;
            case 2:
                ComBean.baudrate = 1200;
                break;
            case 3:
                ComBean.baudrate = 2400;
                break;
            case 4:
                ComBean.baudrate = 4800;
                break;
            case 5:
                ComBean.baudrate = 9600;
                break;
            case 6:
                ComBean.baudrate = 19200;
                break;
            case 7:
                ComBean.baudrate = 38400;
                break;
            case 8:
                ComBean.baudrate = 43000;
                break;
            case 9:
                ComBean.baudrate = 56000;
                break;
            case 10:
                ComBean.baudrate = 57600;
                break;
            case 11:
                ComBean.baudrate = 115200;
                break;
            case 12:
                ComBean.baudrate = 2000000;
            default:
                break;
        }
    }

    public int getDataBit() {
        return dataBit;
    }

    public void setDataBit(int dataBit) {
        switch (dataBit) {
            case 0:
                ComBean.dataBit = SerialPort.DATABITS_5;
                break;
            case 1:
                ComBean.dataBit = SerialPort.DATABITS_6;
                break;
            case 2:
                ComBean.dataBit = SerialPort.DATABITS_7;
                break;
            case 3:
                ComBean.dataBit = SerialPort.DATABITS_8;
                break;
            default:
                break;
        }
    }

    public int getParityBit() {
        return parityBit;
    }

    public void setParityBit(int parityBit) {
        switch (parityBit) {
            case 0:
                ComBean.parityBit = SerialPort.PARITY_NONE;
                break;
            case 1:
                ComBean.parityBit = SerialPort.PARITY_ODD;
                break;
            case 2:
                ComBean.parityBit = SerialPort.PARITY_EVEN;
                break;
            default:
                break;
        }
    }

    public int getStopBit() {
        return stopBit;
    }

    public void setStopBit(int stopBit) {
        switch (stopBit) {
            case 0:
                ComBean.stopBit = SerialPort.STOPBITS_1;
                break;
            case 1:
                ComBean.stopBit = SerialPort.STOPBITS_1_5;
                break;
            case 2:
                ComBean.stopBit = SerialPort.STOPBITS_2;
                break;
            default:
                break;
        }
    }

    public Vector getComId() {

        Vector comIdList = new Vector();
        Enumeration commId = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;

        while (commId.hasMoreElements()) {
            portId = (CommPortIdentifier) commId.nextElement();

            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                comIdList.add(portId.getName());
            }
        }
        return comIdList;
    }

    public boolean initCom() {

        ConstantFactory.infoStr = "";
        ConstantFactory.comProRun = false;
        isComOpen = false;
        try {
            portId = CommPortIdentifier.getPortIdentifier(portName);
            try {
                serialPort = (SerialPort) portId.open("Serial_Communication", 2000);
            } catch (PortInUseException e) {
                ConstantFactory.infoStr = "端口已经被占用！";
                return false;
            }
            try {
                in = new DataInputStream(serialPort.getInputStream());
                out = serialPort.getOutputStream();
            } catch (IOException e) {
                ConstantFactory.infoStr = "IO 错误！";
                return false;
            }
            try {
                serialPort.setSerialPortParams(baudrate, dataBit, stopBit, parityBit);
            } catch (UnsupportedCommOperationException e) {
                ConstantFactory.infoStr = "参数设置错误！";
                return false;
            }

            if (false) {
                try {
                    serialPort.addEventListener(this);
                } catch (TooManyListenersException e) {
                    serialPort.close();
                    ConstantFactory.infoStr = "监听错误！";
                    return false;
                }
                serialPort.notifyOnDataAvailable(true);
            }

            RB = new SerialBuffer(serialComInstance);
            RT = new ReadSerial(in, RB);
            RT.setPriority(Thread.MAX_PRIORITY);
            RT.start();

        } catch (NoSuchPortException e) {
            ConstantFactory.infoStr = "端口未找到！";
            return false;
        }
        isComOpen = true;
        return isComOpen;
        
    }

    public void serialEvent(SerialPortEvent e) {
        switch (e.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                readComm();
                break;
            default:
                break;
        }
    }

    void readComm() {

        dataAnalysis();
        /*
        try {
        InputStreamReader asciiInput = new InputStreamReader(in, "US-ASCII");
        BufferedReader bReader = new BufferedReader(asciiInput);
        try {
        String text;
        String lineSeparator = System.getProperty("line.separator");
        while((text = bReader.readLine()) != null){
        MainFrame.textAreaCmd.append(text);
        MainFrame.textAreaCmd.append(lineSeparator);
        }
        // asciiInput.read(dataBuffer.getReadChar());
        // String tmpStr = String.valueOf(dataBuffer.getReadChar());
        //  String formateStr = tmpStr.trim();
        //  dataBuffer.getReadStr().append(formateStr);
        //  MainFrame.textAreaCmd.append(formateStr);

        asciiInput.close();

        } catch (IOException e) {
        }
        } catch (UnsupportedEncodingException e) {
        }
        }
         * */
        //readThread.setStartThread(true);
        //comPro.start();
        /*
        try {

        InputStreamReader asciiInput = new InputStreamReader(in, "US-ASCII");
        try {
        asciiInput.read(readBuffer, 0, in.available());
        } catch (IOException e) {
        }
        } catch (UnsupportedEncodingException e) {
        }

        String outputStr = String.valueOf(readBuffer);
        String formatStr = outputStr.trim();
        content.append(formatStr);

        }
         */
    }

    public void receiveData() {
        int k = 0, f = 0, s = 0, l = 0, y = 0;
        readDataEnd = false;
        try {
            // while (in.available() > 0) {
            b[readBufferIndex] = in.read();
            if (b[readBufferIndex] == 170) {
                readBufferIndex = 0;
                readDataEnd = true;
            } else {
                readBufferIndex++;
            }
            if (readDataEnd) {
                switch (b[0]) {
                    case 19:
                        f++;
                        break;
                    case 13:
                        l++;
                        break;
                    case 14:
                    case 15:
                        y++;
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                        break;
                    case 20:

                        k++;
                        break;

                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        s++;
                        break;

                    default:
                        break;
                }
            }
            //in.read(dataPool,0,1);

            //in.read(dataPool,0,256);
            //  }
            //in.close();
        } catch (IOException e) {
            k++;
        }
        //  stopRun();
        int j = 0;
    }

    public void dataAnalysis() {
        int c;
        try {
            c = in.read();
            b[readBufferIndex] = c;
            readBufferIndex++;
            if (c == 170) {
                int n = 0;
            }


            if (readBufferIndex == 6) {
                int m = 0;
            }
        } catch (IOException e) {
        }
    }

    class ReadThread extends Thread {

        public ReadThread() {
        }

        public void run() {
            while (!ConstantFactory.comProRun) {
                //ConstantFactory.comProRun = true;
                readCom();
            }
        }

        public void stopRun() {
            ConstantFactory.comProRun = false;
        }

        public void readCom() {
            byte[] dataByte = new byte[1024];
            int i = 0;
            try {
                while (in.available() > 0) {
                    int readnum = in.read(dataByte);
                }
                //in.close();

            } catch (IOException e) {
            }
            //  stopRun();
            int j = 0;
            /*
            try {
            InputStreamReader asciiInput = new InputStreamReader(in, "US-ASCII");
            BufferedReader bReader = new BufferedReader(asciiInput);
            try {
            String text;
            String lineSeparator = System.getProperty("line.separator");
            while ((text = bReader.readLine()) != null) {
            MainFrame.textAreaCmd.append(text);
            MainFrame.textAreaCmd.append(lineSeparator);
            }
            bReader.close();
            asciiInput.close();
            stopRun();
            } catch (IOException e) {
            }
            } catch (UnsupportedEncodingException e) {
            }
             */
        }
    }

    public void writeCom(String wrtStr) {
        try {
            for (int i = 0; i < wrtStr.length(); i++) {
                out.write(wrtStr.charAt(i));
            }
        } catch (IOException e) {
        }
    }

    public void writeCom(byte[] wrtByte, int length) {
        try {
            for (int i = 0; i < length; i++) {
                out.write(wrtByte[i]);
            }
        } catch (IOException e) {
        }
    }

    public void writeCom(byte[] b) {
        try {
            out.write(b);

        } catch (IOException e) {
        }
    }

    public boolean getIsComProOver() {
        return isComProOver;
    }

    public void setIsComProOver(boolean isComProOver) {
        this.isComProOver = isComProOver;
    }

    public boolean getIsConfigureAndStart() {
        return isConfigureAndStart;
    }

    public void setIsConfigureAndStart(boolean isConfigureAndStart) {
        this.isConfigureAndStart = isConfigureAndStart;
    }

    public boolean getIsComTestOk() {
        return isComTestOk;
    }

    public void setIsComTestOk(boolean isComTestOk) {
        this.isComTestOk = isComTestOk;
    }
}
