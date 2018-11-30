package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.commons.utils.DateChooser;
import com.jrs_qms_llh.yueqian.domain.Caller;
import com.jrs_qms_llh.yueqian.service.ICallerService;
import com.jrs_qms_llh.yueqian.service.impl.CallerServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditCallerDialog extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField noFd;
    private JTextField nameFd;
    private JLabel hirFd;
    private JLabel birthFd;
    private JPasswordField passFd;
    private CallerPanel callerPanel;
    private ICallerService callerService = new CallerServiceImpl();
    private Caller caller;
    private JTextPane msgTextPane;
    JRadioButton man;
    JRadioButton women;

    /**
     * Create the dialog.
     */
    public EditCallerDialog(CallerPanel callerPanel, Caller caller) {
        this.caller = caller;
        this.callerPanel = callerPanel;
        setBounds(100, 100, 462, 384);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel passLab = new JLabel("密码");
        passLab.setBounds(104, 57, 54, 15);
        contentPanel.add(passLab);

        noFd = new JTextField();
        noFd.setColumns(20);
        noFd.setBounds(180, 23, 156, 21);
        contentPanel.add(noFd);

        JLabel noLab = new JLabel("柜员工号");
        noLab.setBounds(104, 26, 54, 15);
        contentPanel.add(noLab);

        nameFd = new JTextField();
        nameFd.setColumns(20);
        nameFd.setBounds(180, 85, 156, 21);
        contentPanel.add(nameFd);

        JLabel nameLab = new JLabel("柜员名字");
        nameLab.setBounds(104, 88, 54, 15);
        contentPanel.add(nameLab);

        JLabel sexLab = new JLabel("柜员性别");
        sexLab.setBounds(104, 119, 54, 15);
        contentPanel.add(sexLab);

        man = new JRadioButton("男");
        man.setBounds(180, 115, 46, 23);
        contentPanel.add(man);

        women = new JRadioButton("女");
        women.setBounds(228, 115, 78, 23);
        contentPanel.add(women);

        /* 声明个按钮组 */
        ButtonGroup sexGroup = new ButtonGroup();
        /* 往按钮组添加按钮 */
        sexGroup.add(man);
        sexGroup.add(women);
        man.setSelected(true);
        DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
        DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");

        JLabel birthLab = new JLabel("出生日期");
        birthLab.setBounds(104, 178, 54, 15);
        contentPanel.add(birthLab);

        hirFd = new JLabel("请单击选择日期");
        hirFd.setBounds(180, 144, 156, 21);
        dateChooser1.register(hirFd);
        contentPanel.add(hirFd);


        birthFd = new JLabel("请单击选择日期");
        birthFd.setBounds(180, 175, 156, 21);
        dateChooser2.register(birthFd);
        contentPanel.add(birthFd);


        JLabel hirLab = new JLabel("入职日期");
        hirLab.setBounds(104, 147, 54, 15);
        contentPanel.add(hirLab);

        JLabel msgLab = new JLabel("备注信息");
        msgLab.setBounds(104, 206, 54, 15);
        contentPanel.add(msgLab);

        msgTextPane = new JTextPane();
        msgTextPane.setBounds(179, 206, 157, 82);
        contentPanel.add(msgTextPane);

        passFd = new JPasswordField();
        passFd.setBounds(180, 54, 156, 21);
        contentPanel.add(passFd);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("确定");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        //收集数据
                        String noStr = noFd.getText();
                        String passStr = passFd.getText();
                        String nameStr = nameFd.getText();
                        String sex = man.isSelected() ? "男" : "女";
                        String hirStr = hirFd.getText();
                        String birthStr = birthFd.getText();
                        String msgStr = msgTextPane.getText();
                        //封装数据
                        caller.setC_workno(noStr);
                        caller.setC_password(passStr);
                        caller.setC_name(nameStr);
                        caller.setC_sex(sex);
                        caller.setC_remark(msgStr);
                        try {
                            caller.setC_hiredate(sdf.parse(hirStr));
                            caller.setC_birth(sdf.parse(birthStr));
                            //调用service方法
                            int i = callerService.updateCaller(caller);
                            JOptionPane.showMessageDialog(null, i > 0 ? "修改成功" : "修改失败");
                            dispose();
                            callerPanel.refresh();
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton refreshButton = new JButton("重置");
                refreshButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        init();
                    }
                });
                refreshButton.setActionCommand("Cancel");
                buttonPane.add(refreshButton);
            }
        }
        this.setLocationRelativeTo(null);
        init();
    }

    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        noFd.setText(caller.getC_workno());
        passFd.setText(caller.getC_password());
        nameFd.setText(caller.getC_name());
        if (caller.getC_sex().equals("男")) {
            man.setSelected(true);
        } else {
            women.setSelected(true);
        }
        hirFd.setText(sdf.format(caller.getC_hiredate()));
        birthFd.setText(sdf.format(caller.getC_birth()));
        msgTextPane.setText(caller.getC_remark());
    }
}
