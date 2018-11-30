package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.commons.utils.DateUtil;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 黄浩文
 */
public class HandleByMonthDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JComboBox yearCbo;
    private JComboBox monthCbo;
    private HandlePanel handlePanel;


    /**
     * Create the dialog.
     */
    public HandleByMonthDialog(HandlePanel handlePanel) {
        this.handlePanel = handlePanel;
        setBounds(100, 100, 299, 226);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(5, 1, 0, 0));
        {
            JLabel yearLab = new JLabel("请选择年份");
            yearLab.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(yearLab);
        }
        {
            yearCbo = new JComboBox();
            contentPanel.add(yearCbo);
        }
        {
            JLabel label = new JLabel("请选择月份");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label);
        }
        {
            monthCbo = new JComboBox();
            contentPanel.add(monthCbo);
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
                        String year = (String) yearCbo.getSelectedItem();
                        String month = (String) monthCbo.getSelectedItem();
                        int day = DateUtil.monthHasDay(Integer.parseInt(year), Integer.parseInt(month) - 1);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        StringBuffer sb = new StringBuffer(year).append("-").append(month);
                        String startStr = sb + "-1";
                        String endStr = sb.append("-").append(String.valueOf(day)).toString();
                        try {
                            Date startTime = simpleDateFormat.parse(startStr);
                            Date endTime = simpleDateFormat.parse(endStr);
                            handlePanel.setStart(startTime);
                            handlePanel.setEnd(endTime);
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
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> list = new ArrayList<String>();
        String[] months = new String[12];
        for (int i = calendar.get(Calendar.YEAR); i >= 1970; i--) {
            list.add(String.valueOf(i));
        }
        yearCbo.setModel(new DefaultComboBoxModel(list.toArray()));
        for (int i = 0; i < 12; i++) {
            months[i] = String.valueOf(i + 1);
        }
        monthCbo.setModel(new DefaultComboBoxModel(months));
    }

}
