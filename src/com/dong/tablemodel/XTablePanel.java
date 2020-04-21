/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.tablemodel;

import com.dong.datatransfer.CurrentDataTransfer;
import com.dong.datatransfer.SpeedDataTransfer;
import com.dong.datatransfer.VoltageDataTransfer;
import com.dong.excelpro.SystemFileFilter;
import com.dong.factory.ConstantFactory;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * this class extends {@link javax.swing.JPanel}.
 * this class defines a panel to hold table.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class XTablePanel extends JPanel {

    /**
     * toolbar for search.
     */
    private JPanel rstToolBar;
    /**
     * scrollpanel to hold table.
     */
    private JScrollPane scrollPaneTextRst;
    /**
     * search text.
     */
    private JTextField searchText;
    /**
     * button to export and import data.
     */
    private JButton exPortBtn, imPortBtn;

    /**
     * constructor method. layout panel and initialize class member.
     */
    public XTablePanel() {
        rstToolBar = new JPanel();
        scrollPaneTextRst = new JScrollPane();

        /* 设置查找Label */
        JLabel searchLabel = new JLabel("查找:    ");
        searchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find22.png")));

        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        exPortBtn = new JButton("导出数据");
        imPortBtn = new JButton("导入数据");
        tmpPanel.add(exPortBtn, BorderLayout.EAST);
        tmpPanel.add(imPortBtn, BorderLayout.WEST);

        searchText = new JTextField();
        rstToolBar.setLayout(new BorderLayout());

        rstToolBar.add(searchLabel, BorderLayout.WEST);
        rstToolBar.add(searchText, BorderLayout.CENTER);
        rstToolBar.add(tmpPanel, BorderLayout.EAST);
        scrollPaneTextRst.setViewportView(ConstantFactory.dataTable);





        this.setLayout(new BorderLayout());
        this.add(rstToolBar, BorderLayout.NORTH);
        this.add(scrollPaneTextRst, BorderLayout.CENTER);

        setEvent();
    }

    /**
     * registes event of components.
     * search text adds document listener
     * {@link com.dong.tablemodel.SearchFunDocumentListener }.
     * export button and import button add action listener.
     * {@link #buttonEvent(java.awt.event.ActionEvent)}.
     */
    private void setEvent() {
        XTable rstTable = ConstantFactory.dataTable;
        DefaultTableModel rstModel = ConstantFactory.dataTable.getRstModel();
        searchText.getDocument().addDocumentListener(new SearchFunDocumentListener(rstTable, rstModel, searchText));
        exPortBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });

        imPortBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonEvent(e);
            }
        });
    }

    /**
     * button action event.
     * @param  e ActionEvent.
     */
    private void buttonEvent(ActionEvent e) {
        if (e.getSource().equals(exPortBtn)) {
            fileSaveAs();
        } else if (e.getSource().equals(imPortBtn)) {
            if (ConstantFactory.comBtnPressed == 1) {
                JOptionPane.showMessageDialog(null,
                        "Please disconnect the communication first!",
                        "operation error",
                        JOptionPane.ERROR_MESSAGE);

            } else {
                fileOpen();
            }
        } else {
        }
    }

    /**
     * file open to load data. is called by {@link #buttonEvent(java.awt.event.ActionEvent)}.
     */
    private void fileOpen() {
        JFileChooser fileChooser = new JFileChooser();
        SystemFileFilter fileNameFilter = new SystemFileFilter();
        fileChooser.setFileFilter(fileNameFilter);
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getName();
            if (!fileName.isEmpty()) {
                //String path = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    Workbook book = Workbook.getWorkbook(fileChooser.getSelectedFile());
                    Sheet sheet = book.getSheet(0);

                    Cell cell = sheet.getCell(0, 1);
                    String strIndex = cell.getContents();
                    cell = sheet.getCell(1, 1);
                    String strTime = cell.getContents();
                    cell = sheet.getCell(2, 1);
                    String strRefSpeed = cell.getContents();

                    cell = sheet.getCell(3, 1);
                    String strSpeed = cell.getContents();

                    cell = sheet.getCell(4, 1);
                    String strVoltage = cell.getContents();
                    cell = sheet.getCell(5, 1);
                    String strDAxisVoltage = cell.getContents();
                    cell = sheet.getCell(6, 1);
                    String strQAxisVoltage = cell.getContents();
                    cell = sheet.getCell(7, 1);
                    String strDAxisCurrent = cell.getContents();
                    cell = sheet.getCell(8, 1);
                    String strQAxisCurrent = cell.getContents();

                    if (strIndex.equals("索引") &&
                            strTime.equals("时间") &&
                            strRefSpeed.equals("参考转速")&&
                            strSpeed.equals("测量转速") &&
                            strVoltage.equals("总线电压") &&
                            strDAxisVoltage.equals("D电压") &&
                            strQAxisVoltage.equals("Q电压") &&
                            strDAxisCurrent.equals("D电流") &&
                            strQAxisCurrent.equals("Q电流")) {

                        int row = sheet.getRows();
                        int intRefSpd, intSpd, intVoltage, intDAxisVoltage,
                                intQAxisVoltage, intDAxisCurrent, intQAxisCurrent;
                        Double doubleTime;
                        int screenSpeed,
                                screenSpeedViewer,
                                screenSpeedViewerX5;
                        for (int i = 3; i <= row; i++) {
                           
                            doubleTime = Double.valueOf(sheet.getCell(1, i).getContents());
                            intRefSpd = Integer.parseInt(sheet.getCell(2, i).getContents());
                            intSpd = Integer.parseInt(sheet.getCell(3, i).getContents());
                            intVoltage = Integer.parseInt(sheet.getCell(4, i).getContents());
                            intDAxisVoltage = Integer.parseInt(sheet.getCell(5, i).getContents());
                            intQAxisVoltage = Integer.parseInt(sheet.getCell(6, i).getContents());
                            intDAxisCurrent = Integer.parseInt(sheet.getCell(7, i).getContents());
                            intQAxisCurrent = Integer.parseInt(sheet.getCell(8, i).getContents());

                            ConstantFactory.realTimeArray.set(i - 3, doubleTime);
                            ConstantFactory.realRefSpeedArray.set(i-3, intRefSpd);
                            ConstantFactory.realSpeedArray.set(i - 3, intSpd);
                            ConstantFactory.realVoltageArray.set(i - 3, intVoltage);
                            ConstantFactory.realDAxisVoltageArray.set(i - 3, intDAxisVoltage);
                            ConstantFactory.realQAxisVoltageArray.set(i - 3, intQAxisVoltage);
                            ConstantFactory.realDAxisCurrentArray.set(i - 3, intDAxisCurrent);
                            ConstantFactory.realQAxisCurrentArray.set(i - 3, intQAxisCurrent);


                            screenSpeed = new SpeedDataTransfer(intSpd, ConstantFactory.speedRange).getScreenData();
                            screenSpeedViewer = new SpeedDataTransfer(intSpd, ConstantFactory.speedRange).getScreenDataViewer();
                            screenSpeedViewerX5 = new SpeedDataTransfer(intSpd, ConstantFactory.speedRange).getScreenDataViewerX5();

                            Point point = new Point(ConstantFactory.z0.x + (ConstantFactory.xLength - (i - 3) * 14), screenSpeed);
                            Point pointViewer = new Point(ConstantFactory.z0Viewer.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenSpeedViewer);
                            Point pointViewerX5 = new Point(ConstantFactory.z0Viewer.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenSpeedViewerX5);
                            ConstantFactory.speedDataBaseArray.set(i - 3, point);
                            ConstantFactory.speedDataBaseArrayViewer.set(i - 3, pointViewer);
                            ConstantFactory.speedDataBaseArrayViewerX5.set(i - 3, pointViewerX5);


                            int refSpeedScreen = new SpeedDataTransfer(intRefSpd,ConstantFactory.speedRange).getScreenData();
                            int refSpeedScreenViewer = new SpeedDataTransfer(intRefSpd,ConstantFactory.speedRange).getScreenDataViewer();
                            int refSpeedScreenViewerX5 = new SpeedDataTransfer(intRefSpd,ConstantFactory.speedRange).getScreenDataViewerX5();

                            Point refSpeedPoint = new Point(ConstantFactory.z0.x +(ConstantFactory.xLength - (i - 3) * 14), refSpeedScreen);
                            Point refSpeedPointViewer = new Point(ConstantFactory.z0Viewer.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), refSpeedScreenViewer);
                            Point refSpeedPointViewerX5 = new Point(ConstantFactory.z0Viewer.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), refSpeedScreenViewerX5);
                            ConstantFactory.refSpeedDataBaseArray.set(i-3, refSpeedPoint);
                            ConstantFactory.refSpeedDataBaseArrayViewer.set(i-3, refSpeedPointViewer);
                            ConstantFactory.refSpeedDataBaseArrayViewerX5.set(i-3, refSpeedPointViewerX5);

                            
                            int screenCurrent, screenCurrentDAxisViewer,
                                    screenCurrentDAxisViewerX5, screenCurrentQAxisViewer, screenCurrentQAxisViewerX5;

                            screenCurrent = new CurrentDataTransfer(intDAxisCurrent, ConstantFactory.currentRange).getScreenData();
                            screenCurrentDAxisViewer = new CurrentDataTransfer(intDAxisCurrent, ConstantFactory.currentRange).getScreenDataViewer();
                            screenCurrentDAxisViewerX5 = new CurrentDataTransfer(intDAxisCurrent, ConstantFactory.currentRange).getScreenDataViewerX5();

                            Point pointCurrentDAxis = new Point(ConstantFactory.z0.x + (ConstantFactory.xLength - (i - 3) * 14), screenCurrent);
                            Point pointCurrentDAxisViewer = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenCurrentDAxisViewer);
                            Point pointCurrentDAxisViewerX5 = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenCurrentDAxisViewerX5);


                            ConstantFactory.dAxisCurrentDataBaseArray.set(i - 3, pointCurrentDAxis);
                            ConstantFactory.dAxisCurrentDataBaseArrayViewer.set(i - 3, pointCurrentDAxisViewer);
                            ConstantFactory.dAxisCurrentDataBaseArrayViewerX5.set(i - 3, pointCurrentDAxisViewerX5);


                            screenCurrent = new CurrentDataTransfer(intQAxisCurrent, ConstantFactory.currentRange).getScreenData();
                            screenCurrentQAxisViewer = new CurrentDataTransfer(intQAxisCurrent, ConstantFactory.currentRange).getScreenDataViewer();
                            screenCurrentQAxisViewerX5 = new CurrentDataTransfer(intQAxisCurrent, ConstantFactory.currentRange).getScreenDataViewerX5();



                            Point pointCurrentQAxis = new Point(ConstantFactory.z0.x + (ConstantFactory.xLength - (i - 3) * 14), screenCurrent);
                            Point pointCurrentQAxisViewer = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenCurrentQAxisViewer);
                            Point pointCurrentQAxisViewerX5 = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenCurrentQAxisViewerX5);


                            ConstantFactory.qAxisCurrentDataBaseArray.set(i - 3, pointCurrentQAxis);
                            ConstantFactory.qAxisCurrentDataBaseArrayViewer.set(i - 3, pointCurrentQAxisViewer);
                            ConstantFactory.qAxisCurrentDataBaseArrayViewerX5.set(i - 3, pointCurrentQAxisViewerX5);

                            int screenVoltage,
                                    screenVoltageViewer, screenVoltageViewerX5, screenDAxisVoltageViewer, screenDAxisVoltageViewerX5,
                                    screenQAxisVoltageViewer, screenQAxisVoltageViewerX5;

                            screenVoltage = new VoltageDataTransfer(intVoltage, ConstantFactory.voltageRange).getScreenData();
                            screenVoltageViewer = new VoltageDataTransfer(intVoltage, ConstantFactory.voltageRange).getScreenDataViewer();
                            screenVoltageViewerX5 = new VoltageDataTransfer(intVoltage, ConstantFactory.voltageRange).getScreenDataViewerX5();


                            Point pointVoltage = new Point(ConstantFactory.z0.x + (ConstantFactory.xLength - (i - 3) * 14), screenVoltage);
                            Point pointVoltageViewer = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenVoltageViewer);
                            Point pointVoltageViewerX5 = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenVoltageViewerX5);

                            ConstantFactory.voltageDataBaseArray.set(i - 3, pointVoltage);
                            ConstantFactory.voltageDataBaseArrayViewer.set(i - 3, pointVoltageViewer);
                            ConstantFactory.voltageDataBaseArrayViewerX5.set(i - 3, pointVoltageViewerX5);


                            screenVoltage = new VoltageDataTransfer(intDAxisVoltage, ConstantFactory.voltageRange).getScreenData();
                            screenDAxisVoltageViewer = new VoltageDataTransfer(intDAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewer();
                            screenDAxisVoltageViewerX5 = new VoltageDataTransfer(intDAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewerX5();

                            Point pointVoltageDAxis = new Point(ConstantFactory.z0.x + (ConstantFactory.xLength - (i - 3) * 14), screenVoltage);
                            Point pointDAxisVoltageViewer = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenDAxisVoltageViewer);
                            Point pointDAxisVoltageViewerX5 = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenDAxisVoltageViewerX5);

                            ConstantFactory.dAxisVoltageDataBaseArray.set(i - 3, pointVoltageDAxis);
                            ConstantFactory.dAxisVoltageDataBaseArrayViewer.set(i - 3, pointDAxisVoltageViewer);
                            ConstantFactory.dAxisVoltageDataBaseArrayViewerX5.set(i - 3, pointDAxisVoltageViewerX5);

                            screenVoltage = new VoltageDataTransfer(intQAxisVoltage, ConstantFactory.voltageRange).getScreenData();
                            screenQAxisVoltageViewer = new VoltageDataTransfer(intQAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewer();
                            screenQAxisVoltageViewerX5 = new VoltageDataTransfer(intQAxisVoltage, ConstantFactory.voltageRange).getScreenDataViewerX5();


                            Point pointVoltageQAxis = new Point(ConstantFactory.z0.x + (ConstantFactory.xLength - (i - 3) * 14), screenVoltage);
                            Point pointQAxisVoltageViewer = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenQAxisVoltageViewer);
                            Point pointQAxisVoltageViewerX5 = new Point(ConstantFactory.z0.x + (ConstantFactory.xLengthViewer - (i - 3) * 38), screenQAxisVoltageViewerX5);

                            ConstantFactory.qAxisVoltageDataBaseArray.set(i - 3, pointVoltageQAxis);
                            ConstantFactory.qAxisVoltageDataBaseArrayViewer.set(i - 3, pointQAxisVoltageViewer);
                            ConstantFactory.qAxisVoltageDataBaseArrayViewerX5.set(i - 3, pointQAxisVoltageViewerX5);


                            ConstantFactory.dataTable.getRstModel().setParameters(
                                    ConstantFactory.realTimeArray,
                                    ConstantFactory.realRefSpeedArray,
                                    ConstantFactory.realSpeedArray,
                                    ConstantFactory.realVoltageArray,
                                    ConstantFactory.realDAxisVoltageArray,
                                    ConstantFactory.realQAxisVoltageArray,
                                    ConstantFactory.realDAxisCurrentArray,
                                    ConstantFactory.realQAxisCurrentArray);
                        }

                    }
                } catch (Exception e) {
                }
                ConstantFactory.dataTable.getRstModel().updateTableContent();
                ConstantFactory.graphicDisplayPanel.repaint();
            } else {
            }
        }
    }

    /**
     * methods to save data to excel file. is called by {@link #buttonEvent(java.awt.event.ActionEvent)}.
     */
    private void fileSaveAs() {
        JFileChooser fileChooser = new JFileChooser();
        SystemFileFilter fileNameFilter = new SystemFileFilter();
        fileChooser.setFileFilter(fileNameFilter);
        int option = fileChooser.showSaveDialog(null);

        // 如果确认选取文件
        if (option == JFileChooser.APPROVE_OPTION) {
            // 取得选择的文件
            String fileName = fileChooser.getSelectedFile().getName();
            if (fileName.isEmpty()) {
            } else {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if (path.endsWith(".xls")) {
                } else {
                    path = path + ".xls";
                }
                File file = new File(path);

                try {
                    // 建立文件
                    WritableWorkbook book = Workbook.createWorkbook(file);
                    WritableSheet sheet = book.createSheet("第一页", 0);

                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(currentTime);
                    Label date = new Label(0, 0, "数据保存时间：" + dateString);
                    Label indexLb = new Label(0, 1, "索引");
                    Label timeLb = new Label(1, 1, "时间");
                    Label rpmLb = new Label(2, 1, "参考转速");
                    Label rpmRefLb = new Label(3, 1, "测量转速");
                    Label voltageLb = new Label(4, 1, "总线电压");
                    Label dAxisVoltageLb = new Label(5, 1, "D电压");
                    Label qAxisVoltageLb = new Label(6, 1, "Q电压");
                    Label dAxisCurrentLb = new Label(7, 1, "D电流");
                    Label qAxisCurrentLb = new Label(8, 1, "Q电流");

                    try {
                        sheet.addCell(date);
                        sheet.addCell(indexLb);
                        sheet.addCell(timeLb);
                        sheet.addCell(rpmRefLb);
                        sheet.addCell(rpmLb);
                        sheet.addCell(voltageLb);
                        sheet.addCell(dAxisVoltageLb);
                        sheet.addCell(qAxisVoltageLb);
                        sheet.addCell(dAxisCurrentLb);
                        sheet.addCell(qAxisCurrentLb);

                        int countNum = ConstantFactory.dataTable.getRstModel().getRowCount();
                        for (int i = 0; i < countNum; i++) {
                            jxl.write.Number column0 = new jxl.write.Number(0, i + 2, i);

                            jxl.write.Number column1 = new jxl.write.Number(1, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 1).toString()));
                            jxl.write.Number column2 = new jxl.write.Number(2, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 2).toString()));
                            jxl.write.Number column3 = new jxl.write.Number(3, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 3).toString()));
                            jxl.write.Number column4 = new jxl.write.Number(4, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 4).toString()));
                            jxl.write.Number column5 = new jxl.write.Number(5, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 5).toString()));
                            jxl.write.Number column6 = new jxl.write.Number(6, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 6).toString()));
                            jxl.write.Number column7 = new jxl.write.Number(7, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 7).toString()));
                            jxl.write.Number column8 = new jxl.write.Number(8, i + 2, Double.valueOf(ConstantFactory.dataTable.getRstModel().getValueAt(i, 8).toString()));

                            sheet.addCell(column0);
                            sheet.addCell(column1);
                            sheet.addCell(column2);
                            sheet.addCell(column3);
                            sheet.addCell(column4);
                            sheet.addCell(column5);
                            sheet.addCell(column6);
                            sheet.addCell(column7);
                            sheet.addCell(column8);
                        }
                        book.write();
                        book.close();
                    } catch (jxl.write.WriteException e) {
                    }


                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.toString(),
                            "无法建立新文件", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
