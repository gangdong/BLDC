/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dong.excelpro;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Dong Gang
 */
public class SystemFileFilter extends FileFilter{
    public boolean accept(File f){
        if(f.isDirectory()){
            return true;
        }
        String extension = f.getName().toString();
        if (extension != null) {
            if(extension.endsWith(".xls")){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    public String getDescription() {
        return "Excel File (*.xls)";
    } 
}
