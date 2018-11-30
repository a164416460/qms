package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.commons.utils.DateChooser;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.TicketToday;
import com.jrs_qms_llh.yueqian.service.ITicketTodayService;
import com.jrs_qms_llh.yueqian.service.impl.TicketTodayServiceImpl;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黄浩文
 */
public class HandleByTimeDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField startTime;
    private JTextField endTime;
    private DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
    private DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
    private HandlePanel handlePanel;


    /**
     * Create the dialog.
     */
    public HandleByTimeDialog(HandlePanel handlePanel) {
        this.handlePanel = handlePanel;
        setBounds(100, 100, 299, 226);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(5, 1, 0, 0));
        {
            JLabel startTime = new JLabel("开始时间");
            startTime.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(startTime);
        }
        {
            startTime = new JTextField("单击选择时间");
            /*时间工具*/
            dateChooser1.register(startTime);
            contentPanel.add(startTime);
            startTime.setColumns(10);
        }
        {
            JLabel endTimeLab = new JLabel("结束时间");
            endTimeLab.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(endTimeLab);
        }
        {
            endTime = new JTextField("单击选择时间");
            dateChooser2.register(endTime);
            contentPanel.add(endTime);
            endTime.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("确定");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date start = simpleDateFormat.parse(startTime.getText());
                            Date end = simpleDateFormat.parse(endTime.getText());
                            if (end.compareTo(start) == -1) {
                                throw new RuntimeException("结束日期不能早于开始日期");
                            }
                            handlePanel.setEnd(end);
                            handlePanel.setStart(start);
                            //刷新页面
                            handlePanel.showPage(1);
                            //设置标题
                            handlePanel.refreshTitleLab();
                            //设置显示饼图
                            handlePanel.initRightPanelShowPie();
                            dispose();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("取消");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        this.setLocationRelativeTo(null);
    }

}
