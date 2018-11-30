package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.yueqian.domain.Admin;
import com.jrs_qms_llh.yueqian.service.IAdminService;
import com.jrs_qms_llh.yueqian.service.impl.AdminServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录页面
 * 
 * @author 何庆浩
 *
 */
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userFd;
	private JPasswordField passwordFd;
	private JButton loginBtn;
	private JButton resetBtn;
	private IAdminService adminService = new AdminServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		this.setTitle("登录页面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null); // 设置窗口居中
		this.setResizable(false); // 设置不可改变大小
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userName = new JLabel("用户名：");
		userName.setFont(new Font("宋体", Font.PLAIN, 35));
		userName.setBounds(160, 145, 140, 31);
		contentPane.add(userName);

		userFd = new JTextField();
		userFd.setFont(new Font("宋体", Font.PLAIN, 25));
		userFd.setBounds(306, 145, 290, 31);
		contentPane.add(userFd);
		userFd.setColumns(10);

		JLabel passwordLbl = new JLabel("密  码：");
		passwordLbl.setFont(new Font("宋体", Font.PLAIN, 35));
		passwordLbl.setBounds(160, 244, 169, 31);
		contentPane.add(passwordLbl);

		passwordFd = new JPasswordField();
		passwordFd.setFont(new Font("宋体", Font.PLAIN, 25));
		passwordFd.setBounds(306, 244, 290, 31);
		contentPane.add(passwordFd);

		/*
		 * 登录按钮
		 */
		loginBtn = new JButton("登录");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. 收集数据
				String loginName = userFd.getText();
				String password = passwordFd.getText();
				// 2. 封装数据
				Admin inAdmin = new Admin();
				inAdmin.setAdm_username(loginName);
				inAdmin.setAdm_password(password);
				try {
					boolean resultAdmin = adminService.Login(inAdmin);
					if (resultAdmin == false) {
						JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
					} else {
						MainFrame mainFrame = new MainFrame(LoginFrame.this,inAdmin);
						mainFrame.setVisible(true);
						LoginFrame.this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		loginBtn.setFont(new Font("宋体", Font.PLAIN, 30));
		loginBtn.setBounds(309, 346, 108, 43);
		contentPane.add(loginBtn);

		resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();// 重置方法
			}
		});
		resetBtn.setFont(new Font("宋体", Font.PLAIN, 30));
		resetBtn.setBounds(488, 346, 108, 43);
		contentPane.add(resetBtn);
	}

	// 重置方法
	public void reset() {
		userFd.setText("");
		passwordFd.setText("");
	}

}
