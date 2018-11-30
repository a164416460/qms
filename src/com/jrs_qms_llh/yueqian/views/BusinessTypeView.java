package com.jrs_qms_llh.yueqian.views;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.BusinessType;
import com.jrs_qms_llh.yueqian.service.IBusinessTypeService;
import com.jrs_qms_llh.yueqian.service.impl.BusinessTypeServiceImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BusinessTypeView extends JPanel {
	private JTextField searchTf;
	private JTable table;
	private BusinessTypeView businessTypeView;
	
	private JComboBox selectPageCbo;
	private Page<BusinessType> btPage;
	private String[] columName = new String[] { "\u4E1A\u52A1\u4EE3\u7801",
			"\u4E1A\u52A1\u540D\u79F0", "\u5F53\u5929\u4E0A\u9650",
			"\u4E1A\u52A1\u5907\u6CE8" };
	private Object[][] btDatas = new Object[][] {
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null } };
	private int pageSize =10;

	private IBusinessTypeService businessTypeService = new BusinessTypeServiceImpl();
	/**
	 * Create the panel.
	 */
	public BusinessTypeView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(topPanel, BorderLayout.NORTH);
		
		searchTf = new JTextField();
		topPanel.add(searchTf);
		searchTf.setColumns(20);
		
		JButton searchBtn = new JButton("搜索");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			firstPage();// 按条件搜索出来的结果默认显示是第一页
			}
		});
		topPanel.add(searchBtn);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel crudPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) crudPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		centerPanel.add(crudPanel, BorderLayout.NORTH);
		
		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 弹出一个添加对话框
				AddBTypeDialog addBTypeDialog = new AddBTypeDialog(BusinessTypeView.this);
				addBTypeDialog.setVisible(true);
			}
		});
		crudPanel.add(addBtn);
		
		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBtByIdForUpdate();// 修改
			}
		});
		crudPanel.add(updateBtn);
		
		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBts();// 删除，支持批量删除
			}
		});
		crudPanel.add(deleteBtn);
		
		JButton refreshBtn = new JButton("刷新");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshPage();//刷新
			}
		});
		crudPanel.add(refreshBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		centerPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u4E1A\u52A1\u4EE3\u53F7", "\u4E1A\u52A1\u540D\u79F0", "\u5F53\u5929\u4E0A\u9650", "\u4E1A\u52A1\u5907\u6CE8"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		
		JButton firstBtn = new JButton("首页");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstPage();
			}
		});
		bottomPanel.add(firstBtn);
		
		JButton preBtn = new JButton("上一页");
		preBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prePage();
			}
		});
		bottomPanel.add(preBtn);
		
		JButton nextBtn = new JButton("下一页");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});
		bottomPanel.add(nextBtn);
		
		JButton lastBtn = new JButton("尾页");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastPage();
			}
		});
		bottomPanel.add(lastBtn);
		
		 selectPageCbo = new JComboBox<String >();
		 selectPageCbo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int currentPage=Integer.parseInt((selectPageCbo.getSelectedItem().toString()).split("/")[0]);
					showPage(currentPage);
				}
			});
			
			
		
		 
		 
		 bottomPanel.add(selectPageCbo);
		 firstPage();
			getPage();
			
		
	}
	
	
	// 修改
		protected void getBtByIdForUpdate() {
			int[] rows = table.getSelectedRows();// 得到选择的行的下标
			if ((rows.length == 0) || rows == null) { // 用户一行都没有选择
				JOptionPane.showMessageDialog(this, "请选择您要修改的记录!");
				return;
			}

			if (rows.length > 1) {// 选择多行
				JOptionPane.showMessageDialog(this, "只能选择一行！");
				return;
			}

			// 获得选择的记录行的编号
			// public Object getValueAt(int row, int column): 返回 row 和 column
			// 位置的单元格值。
			
			int row = table.getSelectedRow();
			
			List<BusinessType> lists = btPage.getList();
			BusinessType businessType = lists.get(row);// 根据员工编号找到对应的员工对象
			
			if (businessType == null) {
				JOptionPane.showMessageDialog(this, "您要修改的记录不存在！");
			}

			// 跳转，并把得到的 业务类型 通过构造方法传递过去 EditBusinessTypeDialog 界面
			EditBTypeDialog editBTypeDialog = new EditBTypeDialog(this,businessType);
			editBTypeDialog.setVisible(true);
		}

		// 显示一页信息到table表中
		private Object[][] getData(List<BusinessType> businessTypes) {
			Object[][] datas = new Object[businessTypes.size()][columName.length];
			for (int i = 0; i < businessTypes.size(); i++) {
				BusinessType businessType = businessTypes.get(i);
				datas[i][0] = businessType.getBtCode();
				datas[i][1] = businessType.getBtName();
				datas[i][2] = businessType.getBtLimitCount();
				datas[i][3] = businessType.getBtDesc();

			}
			return datas;
		}

		private void showPage(int currentPage) {
			String search = searchTf.getText();
			btPage = businessTypeService.getPages(search, currentPage, pageSize);
			List<BusinessType> businessTypes = btPage.getList();
			Object[][] datas = getData(businessTypes);
			table.setModel(new DefaultTableModel(datas, columName));
		}

		// 删除，支持批量删除
		protected void deleteBts() {
			int[] rows = table.getSelectedRows();
			if (rows.length > 0 && rows != null) {
				int ifdelete = JOptionPane.showConfirmDialog(this, "是否确定删除?");
				if (ifdelete == 0) {
					int[] ids = new int[rows.length];
					for (int i = 0; i < rows.length; i++) {
						
						BusinessType delbt = btPage.getList().get(i);
						ids[i] = delbt.getBtId();
					}
					boolean isYes = businessTypeService.deleteSelectAll(ids);
					if (isYes) {
						JOptionPane.showMessageDialog(this, "删除成功！");
						this.refreshPage();
					} else {
						JOptionPane.showMessageDialog(this, "删除失败！");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "至少选择一行！");
			}

		}

		// 刷新
		public void refreshPage() {
			int currentPage = btPage.getCurrentPage();
			showPage(currentPage);
		}

		// 首页
		public void firstPage() {
			int currentPage = 1;
			showPage(currentPage);
		}

		// 上一页
		public void prePage() {
			int currentPage = btPage.getCurrentPage() - 1;
			if (currentPage < 1) {
				currentPage = 1;
				JOptionPane.showMessageDialog(this, "已经到达首页了！");
				return;
			}

			showPage(currentPage);
		}

		// 下一页
		public void nextPage() {
			int currentPage = btPage.getCurrentPage() + 1;
			if (currentPage > btPage.getTotalPage()) {
				currentPage = btPage.getTotalPage();
				JOptionPane.showMessageDialog(this, "已经到达最后一页了！");
				return;
			}

			showPage(currentPage);
		}

		// 尾页
		public void lastPage() {
			int currentPage = btPage.getTotalPage();
			if (currentPage == 0) {
				return;
			}

			showPage(currentPage);
		}

		// 查询
		private void showDatas(int currentPage) {
			
			String cond = searchTf.getText();
			btPage = businessTypeService.getPages(cond, currentPage, pageSize);

			// 显示到主界面
			List<BusinessType> bts = btPage.getList();
			Object[][] btDatas = new Object[bts.size()][columName.length];

			for (int i = 0; i < bts.size(); i++) {
				BusinessType bt = bts.get(i);
				btDatas[i][0] = bt.getBtCode();
				btDatas[i][1] = bt.getBtName();
				btDatas[i][2] = bt.getBtLimitCount();
				btDatas[i][3] = bt.getBtDesc();

			}
			
		}
		/**
		 * 获取当前页数和总页数，并放到 selectPageCb 中
		 */
		private void getPage() {
			Integer pages = btPage.getTotalPage();
			String [] str = new String [pages];
			for (int i = 0; i < pages; i ++) {
				Integer a = i + 1;
				str [i] = a + "/" + pages;
			}
			selectPageCbo.setModel(new DefaultComboBoxModel<String>(str));

	}
	}
	
	
	
	
	

