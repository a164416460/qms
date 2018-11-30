package com.jrs_qms_llh.yueqian.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.jrs_qms_llh.yueqian.domain.Admin;

/**
 * 主界面
 * @author 何庆浩
 *
 */
public class MainFrame extends JFrame {
	private BusinessWindowPanel BusinessWindowPanel;
	private JPanel leftPanel;
	private JPanel CallerPanel;
	private JPanel HandlePanel;
	private JTabbedPane rightTP;
	private JPanel contentPane;
	private LoginFrame loginFrame;
	private JLabel timeLbl;
	private Admin admin = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss   E");

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		 EventQueue.invokeLater(new Runnable() {
//		 public void run() {
//		 try {
//		 MainFrame frame = new MainFrame();
//		 frame.setVisible(true);
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 }
//		 }
//		 });
//	}

	/**
	 * Create the frame.
	 */
	public MainFrame(LoginFrame loginFrame, Admin admin) {
		this.loginFrame = loginFrame;
		this.admin = admin;
		//this.rightTP = rightTP;
		this.setTitle("主窗口");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(topPanel, BorderLayout.NORTH);

		JPanel timePanel = new JPanel();
		topPanel.add(timePanel);

		JLabel welcomeLbl = new JLabel("欢迎  " + admin.getAdm_username()+ " !    现在时间是：");
		timePanel.add(welcomeLbl);
		/*
		 * 显示时间模块,这里是有设置时间走动的
		 */
		String timeStr = sdf.format(new Date());
		timeLbl = new JLabel(timeStr);
		timePanel.add(timeLbl);
		this.startTime();

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(200);
		rightTP =  new JTabbedPane(JTabbedPane.TOP);
		leftPanel = new JPanel();
		leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new GridLayout(10, 1, 0, 10));

		/*
		 * 柜员管理模块按钮
		 */
		JButton callerBtn = new JButton("柜员管理");
		callerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//rightTP卡片容器没有柜员管理卡片，添加一张柜员管理卡片
				if (rightTP.indexOfTab("柜员管理")==-1) {
					rightTP.addTab("柜员管理", null,new CallerPanel(),null);
				}
				int index = rightTP.indexOfTab("柜员管理");
				rightTP.setSelectedIndex(index);
			}
		});
		leftPanel.add(callerBtn);

		/*
		 * 业务类型管理模块
		 */
		JButton bizBtn = new JButton("业务类型管理");
		bizBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rightTP.indexOfTab("业务类型管理")==-1) {
					rightTP.addTab("业务类型管理", null, new BusinessTypeView(),null);
				}
				int index = rightTP.indexOfTab("业务类型管理");
				rightTP.setSelectedIndex(index);
			}
		});
		leftPanel.add(bizBtn);

		/*
		 * 窗口管理模块
		 */
		JButton windowsBtn = new JButton("窗口管理");
		windowsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rightTP.indexOfTab("窗口管理")==-1) {
					rightTP.addTab("窗口管理", null, new BusinessWindowPanel(),null);
				}
				int index = rightTP.indexOfTab("窗口管理");
				rightTP.setSelectedIndex(index);
			}
		});
		leftPanel.add(windowsBtn);

		/*
		 * 办理情况查看模块
		 */
		JButton handleBtn = new JButton("办理情况查看");
		handleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rightTP.indexOfTab("办理情况查看")==-1) {
					rightTP.addTab("办理情况查看", null,new HandlePanel(),null);
				}
				int index = rightTP.indexOfTab("办理情况查看");
				rightTP.setSelectedIndex(index);	
			}
		});
		leftPanel.add(handleBtn);

		splitPane.setRightComponent(rightTP);

		JPanel welcomePanel = new JPanel();
		welcomePanel.setBorder(new EmptyBorder(50, 50, 10, 10));
		rightTP.addTab("欢迎", null, welcomePanel, null);
		welcomePanel.setLayout(new GridLayout(10, 1, 0, 10));

		JLabel lblNewLabel = new JLabel("欢迎使用叫号系统");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomePanel.add(lblNewLabel);

		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		JLabel copyRightLbl = new JLabel("版权所有");
		bottomPanel.add(copyRightLbl);
		startTime();
	}

	// 定时器
	public void startTime() {
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String timeStr = sdf.format(new Date()); // 获取当前时间
				timeLbl.setText(timeStr); // 重新设置时间
			}
		});
		timer.start(); // 启动定时器
	}
}
