package com.jrs_qms_llh.yueqian.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.jrs_qms_llh.yueqian.domain.BusinessType;
import com.jrs_qms_llh.yueqian.service.IBusinessTypeService;
import com.jrs_qms_llh.yueqian.service.impl.BusinessTypeServiceImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBTypeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField btNameTf;
	private JTextField btLimitCountTf;
	private JTextField btDescTf;
	private JComboBox btCodeCbo;
	private IBusinessTypeService businessTypeService = new BusinessTypeServiceImpl();
	private BusinessTypeView businessTypeView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// try {
		// AddBTypeDialog dialog = new AddBTypeDialog();
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// dialog.setVisible(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Create the dialog.
	 */
	public AddBTypeDialog(BusinessTypeView businessTypeView) {
		this.businessTypeView = businessTypeView;
		setLocationRelativeTo(null);
		setTitle("添加业务");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("业务代号：");
			lblNewLabel.setBounds(77, 27, 72, 18);
			contentPanel.add(lblNewLabel);
		}

		 btCodeCbo = new JComboBox();
		List<BusinessType> bTypes = businessTypeService.listAll();
		String[] btStrs = new String[bTypes.size()];
		for (int i = 0; i < btStrs.length; i++) {
			BusinessType bt = bTypes.get(i);
			String btStr = bt.getBtCode();
			btStrs[i] = btStr;

		}

		btCodeCbo.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N","O","P","Q","R","S","T","U","V","X","Y"}));
		btCodeCbo.setBounds(142, 22, 171, 24);
		contentPanel.add(btCodeCbo);

		JLabel label = new JLabel("业务名称：");
		label.setBounds(77, 61, 72, 18);
		contentPanel.add(label);

		btNameTf = new JTextField();
		btNameTf.setColumns(10);
		btNameTf.setBounds(142, 58, 171, 24);
		contentPanel.add(btNameTf);

		JLabel label_1 = new JLabel("当天上限：");
		label_1.setBounds(77, 95, 72, 18);
		contentPanel.add(label_1);

		btLimitCountTf = new JTextField();
		btLimitCountTf.setColumns(10);
		btLimitCountTf.setBounds(142, 92, 171, 24);
		contentPanel.add(btLimitCountTf);

		JLabel label_2 = new JLabel("业务备注：");
		label_2.setBounds(77, 132, 72, 18);
		contentPanel.add(label_2);

		btDescTf = new JTextField();
		btDescTf.setColumns(10);
		btDescTf.setBounds(142, 129, 171, 74);
		contentPanel.add(btDescTf);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addBtn = new JButton("添加");
				addBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						add();// 添加操作
					}
				});
				addBtn.setActionCommand("OK");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				JButton resetBtn = new JButton("重置");
				resetBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();// 重置
					}
				});
				resetBtn.setActionCommand("Cancel");
				buttonPane.add(resetBtn);
			}
		}
	}

	// 重置
	public void reset() {

		btCodeCbo.setSelectedIndex(0);
		;
		btNameTf.setText("");
		btLimitCountTf.setText("");
		btDescTf.setText("");

	}

	// 添加业务信息
	public void add() {
		// 1.收集数据

		String btCodeStr = btCodeCbo.getSelectedItem().toString();
		String btNameStr = btNameTf.getText();
		String btLimitCountStr = btLimitCountTf.getText();
		String btDescStr = btDescTf.getText();

		// 2.组织数据，封装数据
		BusinessType businessType = new BusinessType();
		businessType.setBtCode(btCodeStr);
		businessType.setBtName(btNameStr);
		businessType.setBtLimitCount(Integer.parseInt(btLimitCountStr));
		businessType.setBtDesc(btDescStr);

		// 3.调用逻辑层API
		int result = businessTypeService.addBtType(businessType);
		// 4.确定跳转
		if (result > 0) {
			JOptionPane.showMessageDialog(this, "添加成功");
			this.dispose();
			businessTypeView.refreshPage();
		} else {
			JOptionPane.showMessageDialog(this, "添加失败");

		}

	}
}
