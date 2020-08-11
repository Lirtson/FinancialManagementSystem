package 个人财务管理系统;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//import javafx.scene.chart.PieChart;
import org.jfree.chart.ChartPanel;

import java.awt.Color;
public class chartFrame extends JFrame implements ActionListener{
	private JLabel l_fromdate,l_todate;
	private JTextField t_fromdate,t_todate;
	private JButton confirm_date;
	private JButton all_date;
	private BarChart bct;
	private ChartPanel f_bct;
	private PieChart pct;
	private ChartPanel f_pct;
	private ArrayList<oneAccount> cl;
	private getDataFromTxt gt;
	private String s_start=null,s_end=null;
	public chartFrame() {
		super("酸菜鱼数据统计图");
		this.setResizable(false);
		cl=new ArrayList<oneAccount>();
		gt=new getDataFromTxt();
		gt.getData(cl);
		JPanel jp=new JPanel();//此JPanel的布局代码使用WindowBuilder自动生成
		jp.setBackground(Color.WHITE);
		l_fromdate=new JLabel("起始日期");
		l_fromdate.setBackground(Color.WHITE);
		l_fromdate.setForeground(Color.BLACK);
		l_todate=new JLabel("终止日期");
		l_todate.setBackground(Color.WHITE);
		l_todate.setForeground(Color.BLACK);
		t_fromdate=new JTextField();
		t_todate=new JTextField();
		confirm_date=new JButton("确认");
		confirm_date.addActionListener(this);
		confirm_date.setForeground(Color.BLACK);
		confirm_date.setBackground(Color.WHITE);
		all_date=new JButton("重置");
		all_date.addActionListener(this);
		all_date.setForeground(Color.WHITE);
		all_date.setBackground(Color.BLACK);
		
		t_fromdate.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				s_start=t_fromdate.getText();
                //System.out.println(s1); 测试
            }
            public void removeUpdate(DocumentEvent e) {
                //System.out.println("removeUpdate");
            }
            public void changedUpdate(DocumentEvent e) {
                //System.out.println("changedUpdate");
            }
		}		
		);
		t_todate.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				s_end=t_todate.getText();
                //System.out.println(s1); 测试
            }
            public void removeUpdate(DocumentEvent e) {
                //System.out.println("removeUpdate");
            }
            public void changedUpdate(DocumentEvent e) {
                //System.out.println("changedUpdate");
            }
		}		
		);
		this.getContentPane().add(jp);
		CalendarPanel p1 = new CalendarPanel(t_fromdate, "yyyyMMdd");
		p1.initCalendarPanel();
		JLabel l1 = new JLabel("日历面板");
		p1.add(l1);
		CalendarPanel p2 = new CalendarPanel(t_todate, "yyyyMMdd");
		p2.initCalendarPanel();
		JLabel l2 = new JLabel("日历面板");
		p2.add(l2);
		all_date.setForeground(Color.BLACK);
		all_date.setBackground(Color.WHITE);
		GroupLayout gl_jp = new GroupLayout(jp);
		gl_jp.setHorizontalGroup(
			gl_jp.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jp.createSequentialGroup()
					.addGroup(gl_jp.createParallelGroup(Alignment.LEADING)
						.addComponent(p1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_jp.createSequentialGroup()
							.addComponent(l_fromdate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(t_fromdate, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addComponent(all_date))
					.addGroup(gl_jp.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jp.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_jp.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(l_todate)
								.addGap(18)
								.addComponent(t_todate, 115, 115, 115))
							.addGroup(gl_jp.createSequentialGroup()
								.addGap(202)
								.addComponent(confirm_date)))
						.addGroup(gl_jp.createSequentialGroup()
							.addGap(18)
							.addComponent(p2, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_jp.setVerticalGroup(
			gl_jp.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jp.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_jp.createParallelGroup(Alignment.BASELINE)
						.addComponent(l_fromdate)
						.addComponent(t_fromdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(t_todate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(l_todate))
					.addGap(10)
					.addGroup(gl_jp.createParallelGroup(Alignment.BASELINE)
						.addComponent(all_date)
						.addComponent(confirm_date))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jp.createParallelGroup(Alignment.LEADING)
						.addComponent(p1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(p2, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
					.addGap(385))
		);
		
		jp.setLayout(gl_jp);
		bct=new BarChart(cl);
		f_bct=bct.getChartPanel();
		pct=new PieChart(cl);
		f_pct=pct.getChartPanel();
		this.getContentPane().add(f_bct);           //添加柱形图
		this.getContentPane().add(f_pct);
		this.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		this.setBounds(50, 50, 1600, 500);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
		Image icon=new ImageIcon("bar.jpg").getImage();
		this.setIconImage(icon);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==confirm_date) {
			try {
				Integer s1=Integer.parseInt(s_start);
				Integer s2=Integer.parseInt(s_end);
				//更新图表
				bct.updateData(s1,s2);
				pct.updateData(s1,s2);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"请输入正确的日期范围！","错误",JOptionPane.ERROR_MESSAGE);
				ex.getMessage();
			}
		}
		else if(e.getSource()==all_date) {
			t_fromdate.setText("");
			t_todate.setText("");
			bct.updateData(-1,90000000);
			pct.updateData(-1,90000000);
		}
	}
}