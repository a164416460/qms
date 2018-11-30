package com.jrs_qms_llh.yueqian.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jrs_qms_llh.yueqian.service.IBusinessTypeService;
import com.jrs_qms_llh.yueqian.service.impl.BusinessTypeServiceImpl;

public class BusinessType extends JFrame {

	private JPanel contentPane;
	private JTextField searchTf;
	private JTable table;
	private IBusinessTypeService businessTypeService=new BusinessTypeServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusinessType frame = new BusinessType();
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
	public BusinessType() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanle = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanle.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(topPanle, BorderLayout.NORTH);
		
		searchTf = new JTextField();
		topPanle.add(searchTf);
		searchTf.setColumns(20);
		
		JButton searchBtn = new JButton("搜索");
		topPanle.add(searchBtn);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel crudPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) crudPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		centerPanel.add(crudPanel, BorderLayout.NORTH);
		
		JButton addBtn = new JButton("添加");
		crudPanel.add(addBtn);
		
		JButton deleteBtn = new JButton("删除");
		crudPanel.add(deleteBtn);
		
		JButton updateBtn = new JButton("修改");
		crudPanel.add(updateBtn);
		
		JButton refreshBtn = new JButton("刷新");
		crudPanel.add(refreshBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u4E1A\u52A1\u4EE3\u7801", "\u4E1A\u52A1\u540D\u79F0", "\u5F53\u5929\u4E0A\u9650", "\u4E1A\u52A1\u5907\u6CE8"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		JButton firstBtn = new JButton("首页");
		bottomPanel.add(firstBtn);
		
		JButton preBtn = new JButton("上一页");
		bottomPanel.add(preBtn);
		
		JButton nextBtn = new JButton("下一页");
		bottomPanel.add(nextBtn);
		
		JButton lastBtn = new JButton("尾页");
		bottomPanel.add(lastBtn);
	}
	
	
	
	
	
	
	

}
