package com.jrs_qms_llh.yueqian.views;

import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.BusinessWindow;
import com.jrs_qms_llh.yueqian.service.IBusinessWindowService;
import com.jrs_qms_llh.yueqian.service.impl.BusinessWindowServiceImpl;
/**
 * 窗口管理界面
 * @author 丨Madman_
 *
 */
public class BusinessWindowPanel extends JPanel{
	
	IBusinessWindowService businessWindowService = new BusinessWindowServiceImpl();
	
	
	private JTextField searchTextField;
	private JTable table;
	
	private JLabel pageLabel;
	private String pageStr;
	private Page<BusinessWindow> businessWindowPage;
	private String[] columName =  new String[] {
			"\u7A97\u53E3\u53F7", "\u529E\u7406\u4E1A\u52A1\u4EE3\u53F7", "\u529E\u7406\u4E1A\u52A1\u7C7B\u578B"};

	String condition = "";// 获得搜索框的文本信息

	private int currentPage = 1;// 当前页数
	
	private JComboBox selectboBox;
	
	public BusinessWindowPanel() {
		setLayout(new BorderLayout(0, 0));
		
		
		
	
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		searchTextField = new JTextField();
		topPanel.add(searchTextField);
		searchTextField.setColumns(20);

		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				condition = searchTextField.getText();// 获得搜索框的文本信息
				currentPage = 1;
				showPage(currentPage);
			}
		});
		
		topPanel.add(searchButton);
		
		
		
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel crudPanel = new JPanel();
		centerPanel.add(crudPanel, BorderLayout.NORTH);
		crudPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton addBtn = new JButton("\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAddBusinessWindowDialogFn();
			}
		});
		crudPanel.add(addBtn);

		JButton delBtn = new JButton("\u5220\u9664");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletesFn();
			}
		});
		crudPanel.add(delBtn);
		
		
		JButton updateBtn = new JButton("\u4FEE\u6539");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUpdateBusinessWindowDialogFn();
			}
		});
		crudPanel.add(updateBtn);

		JButton renewBtn = new JButton("\u5237\u65B0");
		renewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renewPage();
				JOptionPane.showMessageDialog(null, "刷新成功！");
			}

		});
		crudPanel.add(renewBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		centerPanel.add(scrollPane);

		table = new JTable();
		table.getTableHeader().getReorderingAllowed();
		
		scrollPane.setViewportView(table);
		
		

		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);

		JButton firstBtn = new JButton("\u9996\u9875");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = 1;
				showPage(currentPage);
			}
		});
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		bottomPanel.add(firstBtn);

		JButton upBtn = new JButton("\u4E0A\u4E00\u9875");
		upBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (businessWindowPage.getCurrentPage() > 1 ) {
					currentPage--;
				} else {
					JOptionPane.showMessageDialog(null, "已经是第一页了！！！");
					currentPage = 1;
				}
				showPage(currentPage);
			}
		});
		bottomPanel.add(upBtn);

		JButton nextBtn = new JButton("\u4E0B\u4E00\u9875");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (businessWindowPage.getCurrentPage() + 1 > businessWindowPage.getTotalPage()) {
					JOptionPane.showMessageDialog(null, "已经是最后一页了！！！");
						currentPage = businessWindowPage.getTotalPage();
						if (currentPage == 0) {
							currentPage=1;
						}
				} else {
					currentPage++;
				}
				showPage(currentPage);
			}
		});
		bottomPanel.add(nextBtn);

		JButton lastBtn = new JButton("\u672B\u9875");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = businessWindowPage.getTotalPage();
				if (currentPage == 0) {
					currentPage=1;
				}
				showPage(currentPage);
			}
		});
		bottomPanel.add(lastBtn);
		
//		pageLabel = new JLabel();
//		bottomPanel.add(pageLabel);
//		
		selectboBox = new JComboBox();
		selectboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPageFn();
			}
		});
		selectboBox.setBounds(148, 130, 200, 24);
		bottomPanel.add(selectboBox);
		
		
		showPage(currentPage);
		
	}
	/**
	 * 获得页面的全部信息，并显示在界面中
	 * @param currenrPage 
	 */
	private void showPage(int currenrPage) {
		int pageSize = 3;// 每页显示条数
		businessWindowPage = businessWindowService.getPage(condition, currentPage, pageSize);// 调用getPge()方法获得一页中的全部信息，然后定义一个页面来接收
		List<BusinessWindow> businessWindows = businessWindowPage.getList();
		Object[][] businessWindowDates = new Object[businessWindows.size()][columName.length];
		for (int i = 0; i < businessWindows.size(); i++) {
			BusinessWindow businessWindow = businessWindows.get(i);
			businessWindowDates[i][0] = businessWindow.getBw_no();
			businessWindowDates[i][1] = businessWindow.getBw_type_code();
			businessWindowDates[i][2] = businessWindow.getBw_type_name();
			
		}
		table.setModel(new DefaultTableModel(businessWindowDates, columName));
		selectboBox.setModel(new DefaultComboBoxModel(getTotalPageSelect()));
		getboBoxSecect(currenrPage+"", selectboBox);
		
//		pageStr = " 第" + businessWindowPage.getCurrentPage() + "页 / 共" + businessWindowPage.getTotalPage() + "页\t每页" + businessWindowPage.getPageSize()
//				+ "条记录   共" + businessWindowPage.getCount() + "条记录 ";
//		pageLabel.setText(pageStr);
	}
	/**
	 * 打开添加窗口管理界面方法
	 */
	public void openAddBusinessWindowDialogFn() {
		AddBusinessWindowDialog addBusinessWindowDialog = new AddBusinessWindowDialog(BusinessWindowPanel.this);
		addBusinessWindowDialog.setVisible(true);
	}
	/**
	 * 打开修改窗口管理界面方法
	 */
	public void openUpdateBusinessWindowDialogFn() {
		int[] rows = table.getSelectedRows();
		if (rows.length > 0 && rows != null) {
			if (rows.length == 1) {
				Integer id = Integer.parseInt((String)(table.getValueAt(rows[0], 0)));
				BusinessWindow updateBusinessWindow = businessWindowService.getById(id);
				if (updateBusinessWindow != null) {
					UpdateBusinessWindowDialog updateBusinessWindowDialog = new UpdateBusinessWindowDialog(this, updateBusinessWindow, id);
					updateBusinessWindowDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "您要修改的记录不存在，请核查！");
				}
			} else {
				JOptionPane.showMessageDialog(this, "只能选择一行进行修改！！！");
			}
		} else {
			JOptionPane.showMessageDialog(this, "请选择要修改的一行！！！");
		}
	}
	/**
	 * 删除按钮删除方法
	 */
	public void deletesFn() {
		int[] rows = table.getSelectedRows();

		if (rows.length > 0 && rows != null) {
			int flag = JOptionPane.showConfirmDialog(this, "确认删除所选信息？");
			if (flag == 0) {
				int[] ids = new int[rows.length];
				for (int i = 0; i < ids.length; i++) {
					Integer id = Integer.parseInt((String) table.getValueAt(rows[i], 0));
					System.out.println(id);
					ids[i] = id;
				}
				if (businessWindowService.deleteByIds(ids)) {
					JOptionPane.showMessageDialog(null, "删除成功！！！");
					renewPage();
				} else {
					JOptionPane.showMessageDialog(null, "删除失败！！！");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "至少选中一行！！！");
		}

	}

	/**
	 * 刷新按钮方法
	 */
	public void renewPage() {
		int currentPage = businessWindowPage.getPageSize();
		showPage(currentPage);
	}
	
	/**
	 * 获得选择下拉页数的方法
	 * @return 
	 */
//	table.setModel(new DefaultTableModel(businessWindowDates, columName));
//	pageStr = " 第" + businessWindowPage.getCurrentPage() + "页 / 共" + businessWindowPage.getTotalPage() + "页\t每页" + businessWindowPage.getPageSize()
//			+ "条记录   共" + businessWindowPage.getCount() + "条记录 ";
//	pageLabel.setText(pageStr);
	private Object[] getTotalPageSelect(){
		Object[] object;
		int objSize = businessWindowPage.getTotalPage();
 		if (objSize==0) {
			object = new Object[1];
		}else {
			object = new Object[objSize];
		}
		for (int i = 0; i < object.length; i++) {
			object[i] = i+1;
		}
		return object;
	}
	/**
	 * 下拉页数跳转方法
	 */
	private void selectPageFn() {
		currentPage = Integer.parseInt(selectboBox.getSelectedItem().toString());
		showPage(currentPage);
	}
	
	/**
	 * 获得传入控件中包含传入参数的下拉选项
	 * 
	 * @param updateEmpStr
	 * @param comboBox
	 */
	private void getboBoxSecect(String Str, JComboBox comboBox) {
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			String comStr = comboBox.getItemAt(i).toString();
			if (comStr.equals(Str)) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}

	}

}
