package com.jrs_qms_llh.yueqian.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.jrs_qms_llh.yueqian.domain.BusinessType;
import com.jrs_qms_llh.yueqian.domain.BusinessWindow;
import com.jrs_qms_llh.yueqian.service.IBusinessTypeService;
import com.jrs_qms_llh.yueqian.service.IBusinessWindowService;
import com.jrs_qms_llh.yueqian.service.impl.BusinessTypeServiceImpl;
import com.jrs_qms_llh.yueqian.service.impl.BusinessWindowServiceImpl;

/**
 * 窗口管理的修改界面
 * @author 丨Madman_
 *
 */
public class UpdateBusinessWindowDialog extends JDialog {

	IBusinessWindowService businessWindowService = new BusinessWindowServiceImpl();
	IBusinessTypeService businessTypeService = new BusinessTypeServiceImpl();
	
	private BusinessWindowPanel businessWindowPanel;
	private BusinessWindow businessWindow;
	JPanel centerPanel = new JPanel();
	private JTextPane bw_noTextPane;
	private JComboBox bTboBox;
	private int updateId;

	public UpdateBusinessWindowDialog(BusinessWindowPanel businessWindowPanel,BusinessWindow updateBusinessWindow,int id) {
		this.businessWindowPanel = businessWindowPanel;
		this.businessWindow = updateBusinessWindow;
		this.updateId = id;
		setTitle("修改业务窗口");
		setBounds(50, 50, 460, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);// 居中
		setResizable(false);// 固定大小
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);

		JLabel bw_noLbl = new JLabel("\u7A97\u53E3\u53F7\uFF1A");
		bw_noLbl.setForeground(new Color(0, 0, 0));
		bw_noLbl.setBounds(71, 51, 75, 24);
		centerPanel.add(bw_noLbl);

		bw_noTextPane = new JTextPane();
		bw_noTextPane.setBounds(148, 51, 200, 24);
		centerPanel.add(bw_noTextPane);


		JLabel bTLabel = new JLabel("\u4E1A\u52A1\u7C7B\u578B\uFF1A");
		bTLabel.setBounds(71, 130, 75, 24);
		centerPanel.add(bTLabel);
		
		bTboBox = new JComboBox();
		bTboBox.setModel(new DefaultComboBoxModel(getBTCodeAndBTName()));
		bTboBox.setBounds(148, 130, 200, 24);
		centerPanel.add(bTboBox);

		JButton updateBtn = new JButton("\u4FEE\u6539");
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				updateBusinessWindowFn();
				
			}
		});
		updateBtn.setBounds(120, 263, 63, 27);
		centerPanel.add(updateBtn);

		JButton resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				resetFn();
				
			}
		});
		resetBtn.setBounds(246, 263, 63, 27);
		centerPanel.add(resetBtn);
		
		resetFn();

	}

	/**
	 * 获得business_type表中业务类型代号和名称的方法
	 */
	public String[] getBTCodeAndBTName() {
		List<BusinessType> businessTypes = businessTypeService.listAll();
		String[] businessTypeStrs = new String[businessTypes.size()];
		for (int i = 0; i < businessTypeStrs.length; i++) {
			BusinessType businessType = businessTypes.get(i);
			String str =businessType.getBtCode()+ "." + businessType.getBtName();
			businessTypeStrs[i] = str;
		}
		return businessTypeStrs;

	}

	/**
	 * 获得修改界面的输入框信息，然后调用修改方法
	 */
	private void updateBusinessWindowFn() {
		String bw_no = bw_noTextPane.getText();
		String bw_type_code = (bTboBox.getSelectedItem().toString().split("\\."))[0];
		String bw_type_name = (bTboBox.getSelectedItem().toString().split("\\."))[1];
		BusinessWindow updateBusinessWindow = new BusinessWindow();
		updateBusinessWindow.setBw_id(businessWindow.getBw_id());
		updateBusinessWindow.setBw_no(bw_no);
		updateBusinessWindow.setBw_type_code(bw_type_code);
		updateBusinessWindow.setBw_type_name(bw_type_name);
		int re = businessWindowService.update(updateBusinessWindow);
		if (re > 0) {
			JOptionPane.showMessageDialog(null, "修改成功");
			this.dispose();
			businessWindowPanel.renewPage();
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}

	/**
	 * 重置全部输入框信息
	 */
	private void resetFn() {
		 bw_noTextPane.setText(businessWindow.getBw_no());
		 getboBoxSecect(businessWindow.getBw_type_code(), bTboBox);
	}
	
	/**
	 * 获得传入控件中包含传入参数的下拉选项
	 * 
	 * @param updateEmpStr
	 * @param comboBox
	 */
	private void getboBoxSecect(String Str, JComboBox comboBox) {
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			String comStr = comboBox.getItemAt(i).toString().split("\\.")[0].trim();
			if (comStr.equals(Str)) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}

	}
	
}
