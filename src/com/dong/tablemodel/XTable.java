/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.tablemodel;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTableHeader;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 * this class extends {@link org.jdesktop.swingx.JXTable}.
 * this class defines a table.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class XTable extends JXTable {

    /**
     * table model, should be set as {@link com.dong.tablemodel.ResultTableModel}.
     */
    public ResultTableModel rstModel;

    /**
     * constructor method, initialize table and set model to {@link com.dong.tablemodel.ResultTableModel}.
     */
    public XTable() {
        Object[][] data = {{new Integer(0), new Double(0), new Integer(0), 
                new Float(0),new Float(0),new Float(0), new Integer(0),new Integer(0),new Integer(0)}};
        Object[] header = {"索引", "时间 (S)","参考转速 (rpm)", "测量转速 (rpm)", "总线电压 (V)", "D轴电压 (V)","Q轴电压 (V)","D轴电流 (mA)","Q轴电流 (mA)"};

        /* 创建 Model */
        rstModel = new ResultTableModel(data, header);
        this.setModel(rstModel);
        this.setColumnControlVisible(true);
        this.setEditable(false);
        this.setFont(new Font("TimesRoman", 0, 12));

        /* 设置单元格渲染器 */
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        /* 居中显示 */
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setVerticalAlignment(SwingConstants.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);
        this.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);
        this.setHighlighters(HighlighterFactory.createSimpleStriping());
        this.setAutoscrolls(true);
    }

    /**
     * override of {@link org.jdesktop.swingx.JXTable#createDefaultTableHeader()}.
     */
    @Override
    protected JTableHeader createDefaultTableHeader() {
        return new JXTableHeader(columnModel) {

            @Override
            public void updateUI() {
                super.updateUI();
                // need to do in updateUI to survive toggling of LAF
                if (getDefaultRenderer() instanceof JLabel) {
                    ((JLabel) getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
                }
            }
            //                    </snip>
        };
    }

    /**
     * getter method, return {@link #rstModel }.
     */
    public ResultTableModel getRstModel() {
        return rstModel;
    }

}
