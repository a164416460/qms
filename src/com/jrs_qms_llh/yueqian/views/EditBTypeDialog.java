package com.jrs_qms_llh.yueqian.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jrs_qms_llh.yueqian.domain.BusinessType;
import com.jrs_qms_llh.yueqian.service.IBusinessTypeService;
import com.jrs_qms_llh.yueqian.service.impl.BusinessTypeServiceImpl;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditBTypeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField btNameTf;
	private JTextField btLimitCountTf;
	private JTextField btDescTf;
	private JComboBox btCodeCbo;
	private BusinessTypeView businessTypeView;
	IBusinessTypeService businessTypeService = new BusinessTypeServiceImpl();
	private BusinessType bt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// try {
		// EditBTypeDialog dialog = new EditBTypeDialog();
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// dialog.setVisible(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Create the dialog.
	 */
	public EditBTypeDialog(BusinessTypeView businessTypeView, BusinessType bt) {
		this.bt = bt;
		this.businessTypeView = businessTypeView;
		this.setTitle("修改业务类型");
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("业务代号：");
			lblNewLabel.setBounds(47, 27, 83, 18);
			contentPanel.add(lblNewLabel);
		}
		/*
		 * 选择业务代号下拉框
		 */
		String[] btCodes = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };

		btCodeCbo = new JComboBox(btCodes);

		btCodeCbo.setBounds(142, 22, 171, 24);
		contentPanel.add(btCodeCbo);

		{
			JLabel label = new JLabel("业务名称：");
			label.setBounds(47, 65, 83, 18);
			contentPanel.add(label);
		}
		{
			btNameTf = new JTextField();
			btNameTf.setBounds(142, 62, 171, 24);
			contentPanel.add(btNameTf);
			btNameTf.setColumns(10);
		}
		{
			JLabel label = new JLabel("当天上限：");
			label.setBounds(47, 104, 83, 18);
			contentPanel.add(label);
		}
		{
			btLimitCountTf = new JTextField();
			btLimitCountTf.setColumns(10);
			btLimitCountTf.setBounds(142, 101, 171, 24);
			contentPanel.add(btLimitCountTf);
		}
		{
			JLabel label = new JLabel("业务备注：");
			label.setBounds(47, 138, 83, 18);
			contentPanel.add(label);
		}
		{
			btDescTf = new JTextField();
			btDescTf.setColumns(10);
			btDescTf.setBounds(142, 135, 171, 68);
			contentPanel.add(btDescTf);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okBtn = new JButton("确定");
				okBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							update();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				});
				okBtn.setActionCommand("OK");
				buttonPane.add(okBtn);
				getRootPane().setDefaultButton(okBtn);
			}
			{
				JButton resetBtn = new JButton("重置");
				resetBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();
					}
				});
				resetBtn.setActionCommand("Cancel");
				buttonPane.add(resetBtn);
			}
		}
		initBType();
	}


	// 修改员工信息
	public void update() throws ParseException {
		// 1.收集数据

		String btCodeStr = btCodeCbo.getSelectedItem().toString();

		String btNameStr = btNameTf.getText();
		String btLimitCountStr = btLimitCountTf.getText();
		String btDescStr = btDescTf.getText();

		// 2.组织数据，封装数据
//		BusinessType businessType = new BusinessType();
		bt.setBtCode(btCodeStr);
		bt.setBtName(btNameStr);
		bt.setBtLimitCount(Integer.parseInt(btLimitCountStr));
		bt.setBtDesc(btDescStr);

		// 3.调用逻辑层API，比如service层

		int rows = businessTypeService.update(bt);
		// 4.控制跳转
		if (rows > 0) {
			JOptionPane.showMessageDialog(this, "修改成功！");
			this.dispose();// 添加成功后隐藏界面
			businessTypeView.refreshPage();
		} else {
			JOptionPane.showMessageDialog(this, "修改失败！");
		}

	}

	// 重置
	public void reset() {
		initBType();
	}

	/**
	 * 显示业务信息
	 */
	private void initBType() {
		btNameTf.setText(bt.getBtName() + "");
		btLimitCountTf.setText(String.valueOf(bt.getBtLimitCount()));
		btDescTf.setText(bt.getBtDesc());
		// 显示代号的下拉列表框
		for (int i = 0; i < btCodeCbo.getItemCount(); i++) {
			String item = btCodeCbo.getItemAt(i).toString();
			if (item.equals(bt.getBtCode())) {
				btCodeCbo.setSelectedIndex(i);// 选中跟要修改领导信息
				break;
			}
		}

	}

}
