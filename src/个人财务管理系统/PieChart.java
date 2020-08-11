package ���˲������ϵͳ;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
//����ͼ
public class PieChart {
	private ChartPanel frame1;
	private JFreeChart chart;
	private ArrayList<oneAccount> list;
	public PieChart(ArrayList<oneAccount> l){
		  list=l;
		  DefaultPieDataset data = getDataSet();
	      chart = ChartFactory.createPieChart3D("����ͼ",data,true,false,false);
	    //���ðٷֱ�
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
	      NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����
	      pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�
	  
	  //û�����ݵ�ʱ����ʾ������
	      pieplot.setNoDataMessage("��������ʾ");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
	      pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
	     frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
	      PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
	      piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������
	      chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));
	}
    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
		   getDataFromTxt gt=new getDataFromTxt();
		   ArrayList<oneAccount> list=new ArrayList<>();
		   list.clear();
		   gt.getData(list);
		   for(oneAccount a:list) {
			   dataset.setValue(a.getType(),a.getPrice());
		   }
        return dataset;
}
    public ChartPanel getChartPanel(){
    	return frame1;
    }
  //�õ����˺������
    public DefaultPieDataset getNewData(Integer s1,Integer s2) {
    	DefaultPieDataset dataset = new DefaultPieDataset();
		 for(oneAccount a:list) {
			 if(a.getDate()>=s1&&a.getDate()<=s2)
			   dataset.setValue(a.getType(),a.getPrice());
		   }
		 return dataset;
	}
	//����ͼ��
	public void updateData(Integer s1,Integer s2) {
		PiePlot plot = (PiePlot)chart.getPlot();
		DefaultPieDataset dataset = getNewData(s1,s2);
		plot.setDataset(dataset);
	}
}
