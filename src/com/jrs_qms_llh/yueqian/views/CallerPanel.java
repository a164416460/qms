package com.jrs_qms_llh.yueqian.views;


import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.Caller;
import com.jrs_qms_llh.yueqian.service.ICallerService;
import com.jrs_qms_llh.yueqian.service.impl.CallerServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CallerPanel extends JPanel {
    private ICallerService callerService = new CallerServiceImpl();
    private String cond;
    private JComboBox pageCob;
    private JTextField searchFd;
    private JTable table;
    private Page<Caller> page;
    private int pageSize = 5;
    private String[] tableHead = new String[]{
            "\u67DC\u5458\u5DE5\u53F7", "\u67DC\u5458\u59D3\u540D", "\u67DC\u5458\u6027\u522B", "\u5165\u804C\u65E5\u671F", "\u51FA\u751F\u65E5\u671F", "\u6700\u540E\u767B\u5F55\u65F6\u95F4", "\u6700\u540E\u767B\u5F55IP"
    };

    public Page<Caller> getPage() {
        return page;
    }

    public void setPage(Page<Caller> page) {
        this.page = page;
    }

    public CallerPanel() {
        setLayout(new BorderLayout(0, 0));

        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout(0, 0));

        JPanel searchPanel = new JPanel();
        FlowLayout fl_searchPanel = (FlowLayout) searchPanel.getLayout();
        fl_searchPanel.setAlignment(FlowLayout.LEFT);
        topPanel.add(searchPanel, BorderLayout.NORTH);

        searchFd = new JTextField();
        searchFd.setHorizontalAlignment(SwingConstants.LEFT);
        searchPanel.add(searchFd);
        searchFd.setColumns(25);

        JButton searchBtn = new JButton("搜索");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得搜索框的信息,赋给模糊查询的变量
                cond = searchFd.getText();
                //显示查询到的第一页
                fistPage();
            }
        });
        searchPanel.add(searchBtn);

        JPanel curdPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) curdPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        topPanel.add(curdPanel, BorderLayout.SOUTH);

        JButton addBtn = new JButton("添加");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCallerDialog add = new AddCallerDialog(CallerPanel.this);
                add.setVisible(true);
            }
        });
        curdPanel.add(addBtn);

        JButton updateBtn = new JButton("修改");
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得选中的行
                int selectedRow = table.getSelectedRow();
                List<Caller> list = page.getList();
                if (selectedRow > list.size() - 1 || list.size() <= 0 || table.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "要选择有记录的一行才可以修改！");
                    return;
                }
                EditCallerDialog edit = new EditCallerDialog(CallerPanel.this, list.get(selectedRow));
                edit.setVisible(true);
            }
        });
        curdPanel.add(updateBtn);

        JButton delBtn = new JButton("删除");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();//获得选中的行的索引
                List<Caller> list = page.getList();//获得本页的详细信息
                /*通过循环获得选中了柜员的id*/
                if (rows.length > 0 && rows != null && list.size() > 0) {
                    for (int i = 0; i < rows.length; i++) {
                        rows[i] = list.get(rows[i]).getC_id();//数组重用，节省开销
                    }
                    //判断是否真的删除
                    int code = JOptionPane.showConfirmDialog(null, "是否真的删除?");
                    if (code == 0) {//0为删除
                        int num = callerService.deleteSelectAll(rows);
                        JOptionPane.showMessageDialog(null, num > 0 ? "删除成功" : "删除失败");
                    }
                    //刷新页面
                    cond = "";
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "必须要选择删除的行(1-N行)");
                }
            }
        });
        curdPanel.add(delBtn);

        JButton refreshBtn = new JButton("刷新");
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        curdPanel.add(refreshBtn);

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();

        scrollPane.setViewportView(table);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        JButton firstBtn = new JButton("首页");
        firstBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fistPage();
            }
        });
        panel.add(firstBtn);

        JButton preBtn = new JButton("上一页");
        preBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prePage();
            }
        });
        panel.add(preBtn);

        JButton nextBtn = new JButton("下一页");
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
            }
        });
        panel.add(nextBtn);

        JButton lastBtn = new JButton("尾页");
        lastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastPage();
            }
        });
        panel.add(lastBtn);

        pageCob = new JComboBox();
        pageCob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = (String) pageCob.getSelectedItem();
                showPage(Integer.parseInt(val));
            }
        });
        panel.add(pageCob);
        cond = "";
        showPage(1);


    }

    public void showPage(int currentPage) {
        //获得一页
        page = this.callerService.getPage(cond, currentPage, pageSize);
        Object[][] info = new Object[pageSize][7];
        //获得详细信息
        List<Caller> callers = page.getList();
        for (int i = 0; i < callers.size(); i++) {//把信息放入表格
            Caller caller = callers.get(i);//获得查到的第i个柜员
            info[i][0] = caller.getC_workno();
            info[i][1] = caller.getC_name();
            info[i][2] = caller.getC_sex();
            info[i][3] = caller.getC_hiredate();
            info[i][4] = caller.getC_birth();
            info[i][5] = caller.getC_last_login_time();
            info[i][6] = caller.getC_last_login_ip();
        }
        table.setModel(new DefaultTableModel(info, tableHead));
        String[] strs = new String[page.getTotalPage()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(i + 1);
        }
        pageCob.setModel(new DefaultComboBoxModel(strs));
        if (strs.length > 0 && strs != null)
            this.pageCob.setSelectedIndex(currentPage - 1);

    }

    /**
     * 首页
     */
    public void fistPage() {
        showPage(1);
    }

    /**
     * 下一页
     */
    public void nextPage() {
        int totalPage = page.getTotalPage();
        int currentPage = page.getCurrentPage() + 1;
        showPage(currentPage > totalPage ? totalPage : currentPage);
    }

    /**
     * 上一页
     */
    public void prePage() {
        int currentPage = page.getCurrentPage() - 1;//当前页码
        showPage(currentPage < 1 ? 1 : currentPage);
    }

    /**
     * 最后一页
     */
    public void lastPage() {
        int totalPage = page.getTotalPage();
        showPage(totalPage == 0 ? 1 : totalPage);
    }

    /**
     * 刷新
     */
    public void refresh() {
        showPage(page.getCurrentPage());
    }
}
