package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.TicketToday;
import com.jrs_qms_llh.yueqian.service.ITicketTodayService;
import com.jrs_qms_llh.yueqian.service.impl.TicketTodayServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 黄浩文
 */
public class HandlePanel extends JPanel {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
    private Date start;
    private Date end;
    private Page<TicketToday> page;
    private static final long serialVersionUID = 1L;
    private JTable table;
    private int pageSize = 20;
    private String[] tHead = {"\u4E1A\u52A1\u4EE3\u53F7", "\u53F7\u7801", "\u4E1A\u52A1\u7C7B\u578B", "\u53D6\u53F7\u65F6\u95F4", "\u53EB\u53F7\u65F6\u95F4", "\u53D6\u53F7IP", "\u53EB\u53F7IP", "\u88AB\u53EB\u6B21\u6570", "\u662F\u5426\u5B8C\u6210"};
    private JComboBox comboBox;
    private JLabel titleLab;
    private JSplitPane splitPane;
    private JPanel TuXingPanel;

    public void refreshTitleLab() {
        titleLab.setText(dateFormat.format(start) + " 到 " + dateFormat.format(end) + " 业务情况如下");
    }

    public Page<TicketToday> getPage() {
        return page;
    }

    public void setPage(Page<TicketToday> page) {
        this.page = page;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * Create the panel.
     */
    public HandlePanel() {
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(panel, BorderLayout.NORTH);

        JButton showByYear = new JButton("按年查看");
        showByYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HandleByYearDialog handleByYearDialog = new HandleByYearDialog(HandlePanel.this);
                handleByYearDialog.setVisible(true);
            }
        });
        panel.add(showByYear);

        JButton showByMonth = new JButton("按月查看");
        showByMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HandleByMonthDialog handleByMonthDialog = new HandleByMonthDialog(HandlePanel.this);
                handleByMonthDialog.setVisible(true);
            }
        });
        panel.add(showByMonth);

        JButton showByTime = new JButton("按时间段查看");
        showByTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HandleByTimeDialog dialog = new HandleByTimeDialog(HandlePanel.this);
                dialog.setVisible(true);
            }
        });
        panel.add(showByTime);

        JButton showToday = new JButton("当天流量");
        showToday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                today();
            }
        });
        panel.add(showToday);

        splitPane = new JSplitPane();
        splitPane.setDividerLocation(600);
        add(splitPane, BorderLayout.CENTER);


        JPanel leftPanel = new JPanel();
        splitPane.setLeftComponent(leftPanel);
        leftPanel.setLayout(new BorderLayout(0, 0));

        JPanel bottomPanel = new JPanel();
        leftPanel.add(bottomPanel, BorderLayout.SOUTH);

        JButton firstBtn = new JButton("首页");
        firstBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstPage();
            }
        });
        bottomPanel.add(firstBtn);

        JButton preBtn = new JButton("上一页");
        preBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prePage();
            }
        });
        bottomPanel.add(preBtn);

        JButton nextBtn = new JButton("下一页");
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
            }
        });
        bottomPanel.add(nextBtn);

        JButton lastBtn = new JButton("尾页");
        lastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastPage();
            }
        });
        bottomPanel.add(lastBtn);

        comboBox = new JComboBox();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = (String) comboBox.getSelectedItem();
                showPage(Integer.parseInt(val));
            }
        });
        bottomPanel.add(comboBox);

        JScrollPane scrollPane = new JScrollPane();
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        today();//初始化默认显示当天流量
        scrollPane.setViewportView(table);

        JPanel panel1 = new JPanel();
        leftPanel.add(panel1, BorderLayout.NORTH);

        titleLab = new JLabel();
        panel1.add(titleLab);
        titleLab.setFont(new Font("黑体", Font.BOLD, 15));
        refreshTitleLab();
    }

    /*初始化图形报表面板饼图显示*/
    public void initRightPanelShowPie() {
        TuXingPanel = new PieChartPanel(titleLab.getText(), start, end);
        splitPane.setRightComponent(TuXingPanel);
    }

    /*初始化图形报表折线图显示*/
    public void initRightPanelShowLine() {
        TuXingPanel = new LineChartPanel(start, end);
        splitPane.setRightComponent(TuXingPanel);
    }

    /**
     * 当天流量
     */
    public void today() {
        try {
            Calendar instance = Calendar.getInstance();
            String todayStr = instance.get(Calendar.YEAR) + "-" + (instance.get(Calendar.MONTH) + 1) + "-" + instance.get(Calendar.DAY_OF_MONTH);
            start = this.dateFormat.parse(todayStr);
            end = new Date();
            showPage(1);
            initRightPanelShowLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页
     */
    public void firstPage() {
        showPage(1);
    }

    /**
     * 上一页
     */
    public void prePage() {
        int currentPage = page.getCurrentPage() - 1;//当前页码
        showPage(currentPage < 1 ? 1 : currentPage);
    }

    /**
     * 下一页
     */
    public void nextPage() {
        int currentPage = page.getCurrentPage() + 1;
        int totalPage = page.getTotalPage();
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        showPage(currentPage);
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

    /**
     * 显示一页
     *
     * @param currentPage
     */
    public void showPage(int currentPage) {
        this.page = this.ticketTodayService.getPageByDate(start, end, currentPage, pageSize);
        Object[][] objects = new Object[pageSize][9];
        List<TicketToday> list = page.getList();//获得一页中的详细信息
        for (int i = 0; i < list.size(); i++) {
            TicketToday ticketToday = list.get(i);//第i行的对象
            objects[i][0] = ticketToday.getTicket_id();
            objects[i][1] = ticketToday.getTicket_no();
            objects[i][2] = ticketToday.getTicket_business_name();
            objects[i][3] = ticketToday.getTicket_take_time();
            objects[i][4] = ticketToday.getTicket_call_time();
            objects[i][5] = ticketToday.getTicket_call_ip();
            objects[i][6] = ticketToday.getTicket_take_ip();
            objects[i][7] = ticketToday.getTicket_call_count();
            objects[i][8] = ticketToday.getTicket_is_success();
        }
        String[] strs = new String[page.getTotalPage()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(i + 1);
        }
        comboBox.setModel(new DefaultComboBoxModel(strs));
        if (strs.length > 0 && strs != null)
            comboBox.setSelectedIndex(currentPage - 1);
        table.setModel(new DefaultTableModel(objects, tHead));
    }


}
