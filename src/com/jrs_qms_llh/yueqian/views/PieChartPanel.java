package com.jrs_qms_llh.yueqian.views;

import com.jrs_qms_llh.yueqian.service.ITicketTodayService;
import com.jrs_qms_llh.yueqian.service.impl.TicketTodayServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class PieChartPanel extends JPanel {
    private ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
    private Map<String, Integer> data;
    private String title;

    public PieChartPanel(String title, Date start, Date end) {
        this.title = title;
        data = ticketTodayService.getBtNameAndCountByDate(start, end);
        this.add(createDemoPanel());
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (data.size() != 0) {
            Set<String> btName = this.data.keySet();
            for (String key : btName) {
                dataset.setValue(key, data.get(key));
            }
        }
//        dataset.setValue("IPhone 5s", new Double(20));
//        dataset.setValue("SamSung Grand", new Double(20));
//        dataset.setValue("MotoG", new Double(40));
//        dataset.setValue("Nokia Lumia", new Double(10));
        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                this.title,  // chart title
                dataset,        // data
                true,           // include legend
                true,
                false);

        return chart;
    }

    public JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2018-1-1");
        PieChartPanel demo = new PieChartPanel("Mobile Sales", parse, new Date());
        demo.setSize(400, 367);
//        RefineryUtilities.centerFrameOnScreen(demo);

//        demo.setVisible(true);
        JFrame jFrame = new JFrame();
        jFrame.add(demo);
        jFrame.setVisible(true);
    }
}
