package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.yueqian.service.ITicketTodayService;
import com.jrs_qms_llh.yueqian.service.impl.TicketTodayServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

//private ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
//private Map<String, Integer> date;
public class LineChartPanel extends JPanel {
    private Date start;
    private Date end;
    private ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();

    public LineChartPanel(Date start, Date end) {
        this.start = start;
        this.end = end;
        this.setLayout(new GridLayout(2, 1));
        JFreeChart lineChart = ChartFactory.createLineChart(
                "按窗口统计",
                "窗口号", "办理流量",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 200));
        this.add(chartPanel);
        JFreeChart lineChart2 = ChartFactory.createLineChart(
                "按业务类型统计",
                "窗口号", "办理流量",
                createDataset2(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel2 = new ChartPanel(lineChart2);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 200));
        this.add(chartPanel2);
    }

    private DefaultCategoryDataset createDataset() {
        Map<String, Integer> date = ticketTodayService.getBtNameAndCountByWindow(start, end);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Set<String> keys = date.keySet();
        for (String key : keys) {
            dataset.addValue(date.get(key), "办理流量", key);
        }
//        dataset.addValue(15, "schools", "1970");
//        dataset.addValue(30, "schools", "1980");
//        dataset.addValue(60, "schools", "1990");
//        dataset.addValue(120, "schools", "2000");
//        dataset.addValue(240, "schools", "2010");
//        dataset.addValue(300, "schools", "2014");
        return dataset;
    }

    private DefaultCategoryDataset createDataset2() {
        Map<String, Integer> date = ticketTodayService.getBtNameAndCountByDate(start, end);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Set<String> keys = date.keySet();
        for (String key : keys) {
            dataset.addValue(date.get(key), "办理流量", key);
        }
//        dataset.addValue(15, "schools", "1970");
//        dataset.addValue(30, "schools", "1980");
//        dataset.addValue(60, "schools", "1990");
//        dataset.addValue(120, "schools", "2000");
//        dataset.addValue(240, "schools", "2010");
//        dataset.addValue(300, "schools", "2014");
        return dataset;
    }

    public static void main(String[] args) throws ParseException {
        JFrame frame = new JFrame();
        frame.add(new LineChartPanel(new SimpleDateFormat("yyyy-MM-dd").parse("2018-1-1"), new Date()));
        frame.setVisible(true);
    }
}