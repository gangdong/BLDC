/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.serialcom;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Dong Gang
 */
public class ReadSerial extends Thread {

    private InputStream ComPort;
    private SerialBuffer Buffer;
 

    public ReadSerial(InputStream ComPort,SerialBuffer buffer) {
        this.ComPort = ComPort;
        this.Buffer = buffer;
    }

    public void run() {
        int c;
        try {
            while (true) {
               if(0<ComPort.available()){
                     c = ComPort.read();
                     Buffer.putByte(c);
                }
               
            }
        } catch (IOException e) {
        }
    }
}
