package com.jrs_qms_llh.yueqian.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 黄浩文
 */
public class HandleByYearDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private HandlePanel handlePanel;
    private JComboBox yearCob;


    /**
     * Create the dialog.
     */
    public HandleByYearDialog(HandlePanel handlePanel) {
        this.handlePanel = handlePanel;
        setBounds(100, 100, 299, 226);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(3, 1, 0, 0));
        {
            JLabel label = new JLabel("请选择年份");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label);
        }
        {
            yearCob = new JComboBox();
            contentPanel.add(yearCob);
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
                        String yearStr = (String) yearCob.getSelectedItem();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date start = simpleDateFormat.parse(yearStr + "-1-1");
                            Date end = simpleDateFormat.parse(yearStr + "-12-31");
                            handlePanel.setStart(start);
                            handlePanel.setEnd(end);
                            //刷新页面
                            handlePanel.showPage(1);
                            //设置标题
                            handlePanel.refreshTitleLab();
                            //设置显示饼图
                            handlePanel.initRightPanelShowPie();
                            dispose();
                        } catch (ParseException e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                            e1.printStackTrace();
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
        init();
        this.setLocationRelativeTo(null);
    }

    public void init() {
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = calendar.get(Calendar.YEAR); i >= 1970; i--) {
            list.add(String.valueOf(i));
        }
        yearCob.setModel(new DefaultComboBoxModel(list.toArray()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String start = simpleDateFormat.format(handlePanel.getStart());
        for (int i = 0; i < yearCob.getItemCount(); i++) {
            if (yearCob.getItemAt(i).equals(start))
                yearCob.setSelectedIndex(i);
        }
    }

}
