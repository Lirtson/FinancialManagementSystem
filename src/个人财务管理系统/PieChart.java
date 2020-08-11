package 个人财务管理系统;
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
//扇形图
public class PieChart {
	private ChartPanel frame1;
	private JFreeChart chart;
	private ArrayList<oneAccount> list;
	public PieChart(ArrayList<oneAccount> l){
		  list=l;
		  DefaultPieDataset data = getDataSet();
	      chart = ChartFactory.createPieChart3D("扇形图",data,true,false,false);
	    //设置百分比
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
	      NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
	      pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
	  
	  //没有数据的时候显示的内容
	      pieplot.setNoDataMessage("无数据显示");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//设置不显示空值
	      pieplot.setIgnoreZeroValues(true);//设置不显示负值
	     frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
	      PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	      piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));//解决乱码
	      chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
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
  //得到过滤后的数据
    public DefaultPieDataset getNewData(Integer s1,Integer s2) {
    	DefaultPieDataset dataset = new DefaultPieDataset();
		 for(oneAccount a:list) {
			 if(a.getDate()>=s1&&a.getDate()<=s2)
			   dataset.setValue(a.getType(),a.getPrice());
		   }
		 return dataset;
	}
	//更新图表
	public void updateData(Integer s1,Integer s2) {
		PiePlot plot = (PiePlot)chart.getPlot();
		DefaultPieDataset dataset = getNewData(s1,s2);
		plot.setDataset(dataset);
	}
}
