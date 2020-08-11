package ���˲������ϵͳ;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
//��״ͼ
public class BarChart {
	private ChartPanel frame1;
	private JFreeChart chart;
	private ArrayList<oneAccount> list;
	public  BarChart(ArrayList<oneAccount> l){
			list=l;
			CategoryDataset dataset = getDataSet();
			chart = ChartFactory.createBarChart3D(
      		                 "��״ͼ", // ͼ�����
                           "��֧����", // Ŀ¼�����ʾ��ǩ
                           "���", // ��ֵ�����ʾ��ǩ
                           dataset, // ���ݼ�
                           PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                           true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                           false,          // �Ƿ����ɹ���
                           false           // �Ƿ�����URL����
                           );
       
       //�����￪ʼ
       CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
       CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
        domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
         chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
         chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
         
         //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������
         
        frame1=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame
		}
	   private CategoryDataset getDataSet() {
		   DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		   for(oneAccount a:list) {
			   dataset.addValue(a.getPrice(), a.getType(),a.getType());
		   }
		   //�������࣬�·�����
           return dataset;
	   }
	public ChartPanel getChartPanel(){
		return frame1;
	}
	//�õ����˺������
	public CategoryDataset getNewData(Integer s1,Integer s2) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 for(oneAccount a:list) {
			 if(a.getDate()>=s1&&a.getDate()<=s2)
			   dataset.addValue(a.getPrice(), a.getType(),a.getType());
		   }
		 return dataset;
	}
	//����ͼ��
	public void updateData(Integer s1,Integer s2) {
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		CategoryDataset dataset = getNewData(s1,s2);
		plot.setDataset(dataset);
	}
}
