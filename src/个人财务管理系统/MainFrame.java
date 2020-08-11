package 个人财务管理系统;

import java.util.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;

import org.jdesktop.swingx.JXDatePicker;
class MainFrame extends JFrame implements ActionListener{
	ArrayList<oneAccount>screen=new ArrayList<>();
	private Object[][] row = new Object[50][5];
	private JMenuBar mb=new JMenuBar();
	private JMenu m_system=new JMenu("系统管理");
	private JMenu m_fm=new JMenu("收支管理");
	private JMenuItem mI[]={new JMenuItem("密码重置"),new JMenuItem("退出系统")};
	private JMenuItem m_FMEdit=new JMenuItem("收支编辑");
	//private JTextField t_id,t_date,t_bal;
	private JLabel l_type,l_fromdate,l_todate,l_bal=null,l_ps;  
	//private JTextField t_fromdate,t_todate; 
	private JButton b_select1,b_select2,b_clear;
	private JComboBox c_type,c_item;//
	private JPanel p_condition,p_detail;
	private String s1[]={"收入","支出"};
	private double bal1=0,bal2=0;	
	private JTable table;
	private String username;
	
	private JMenu m_chart=new JMenu("报表");
	private JMenu m_about=new JMenu("关于");
	private JMenuItem m_ch1=new JMenuItem("报表");
	private JMenuItem m_ab1=new JMenuItem("关于");
	
	//按内容（type)查询
	private String s2[]={"购物","餐饮","居家","交通","娱乐","人情","工资","奖金","其他"};
	private JLabel l_contentType;
	private JComboBox c_contentType;
	private JButton b_contentType;
	 String[] cloum = {"编号", "日期", "类型","内容","金额",};
	 //有关日历控件的
	 private Date date1;
	 private JXDatePicker datepick1;
	 private SimpleDateFormat mymatter1;
	 private Date date2;
	 private JXDatePicker datepick2;
	 private SimpleDateFormat mymatter2;
	 //显示全部按钮
	 private JButton b_all;
	public MainFrame(String username){
		super(username+",欢迎使用个人理财账本!");
		this.username=username;
		
		Image icon=new ImageIcon("Main_frame.png").getImage();
		this.setIconImage(icon);
		
		Container c=this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(mb,"North");
		mb.add(m_system);
		mb.add(m_fm);
		mb.add(m_chart);
		mb.add(m_about);
		m_system.add(mI[0]);
		m_system.add(mI[1]);
		m_fm.add(m_FMEdit);
		m_chart.add(m_ch1);
		m_about.add(m_ab1);
		m_FMEdit.addActionListener(this);
	        mI[0].addActionListener(this);
	    mI[1].addActionListener(this);
	    
	    m_ch1.addActionListener(this);
	    m_ab1.addActionListener(this);
	    
	    
	    date1 = new Date();
		datepick1 = new JXDatePicker();
		mymatter1 = new SimpleDateFormat("yyyyMMdd");
		datepick1.setFormats(mymatter1);
		datepick1.setDate(date1);
		date2 = new Date();
		datepick2 = new JXDatePicker();
		mymatter2 = new SimpleDateFormat("yyyyMMdd");
		datepick2.setFormats(mymatter2);
		datepick2.setDate(date2);
	    
	    l_type=new JLabel("收支类型：");	
	    c_type=new JComboBox(s1);
	    b_select1=new JButton("查询");
		l_fromdate=new JLabel("起始时间");
        //t_fromdate=new JTextField(8);
		l_todate=new JLabel("终止时间");
        //t_todate=new JTextField(8);
		b_select2=new JButton("查询");
		l_ps = new JLabel("注意：时间格式为YYYYMMDD，例如：20150901");
		p_condition=new JPanel();
		p_condition.setBackground(Color.WHITE);
		p_condition.setLayout(new GridLayout(4,1));
		p_condition.setBorder(BorderFactory.createCompoundBorder(
	    BorderFactory.createTitledBorder("输入查询条件"), 
	    BorderFactory.createEmptyBorder(5,5,5,5)));
		
		l_contentType=new JLabel("筛选内容");
		c_contentType=new JComboBox(s2);
		b_contentType=new JButton("查询");
		
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
	    JPanel p2 = new JPanel();
	    p2.setBackground(Color.WHITE);
	    JPanel p3 = new JPanel();
	    p3.setBackground(Color.WHITE);
	    JPanel p4=new JPanel();
	    p4.setBackground(Color.WHITE);
	    JPanel p5=new JPanel();
	    p5.setBackground(Color.WHITE);
	    b_all=new JButton("显示全部");
	    b_all.addActionListener(this);
	    p5.add(b_all);
	    p4.add(l_contentType);
	    p4.add(c_contentType);
	    p4.add(b_contentType);
	    p1.add(l_type);
	    p1.add(c_type);
	    p1.add(b_select1);
	    p2.add(l_fromdate);
		p2.add(datepick1);
		p2.add(l_todate);
		p2.add(datepick2);
		p2.add(b_select2);
		//p3.add(l_ps);
		p_condition.add(p1);
	    p_condition.add(p2);
	    //p_condition.add(p3);
	    p_condition.add(p4);
	    p_condition.add(p5);
        c.add(p_condition,"Center");
                
        b_select1.addActionListener(this);
        b_select2.addActionListener(this);
        b_contentType.addActionListener(this);
        
        
        p_detail=new JPanel();
        p_detail.setBackground(Color.WHITE);
        p_detail.setBorder(BorderFactory.createCompoundBorder(
	    BorderFactory.createTitledBorder("收支明细信息"), 
	    BorderFactory.createEmptyBorder(5,5,5,5)));
        l_bal=new JLabel();
        row = new Object[50][5];
		table = new JTable(row, cloum);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(580,350));
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setViewportView(table);
		p_detail.add(l_bal);
		p_detail.add(scrollpane);
		c.add(p_detail,"South");		
		 	
		//judgeOver();
		
        this.setResizable(false);
        this.setSize(800,700);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.show();
		startshow();
		
	}
	//判断是否超支
	public void judgeOver() {
		oneAccount a;
		ArrayList<oneAccount>jl=new ArrayList<>();
		getDataFromTxt jg=new getDataFromTxt();
		jg.getData(jl);
		/*for(int i=0;i<jl.size();i++) {
			a=jl.get(i);
			if(a.getIOtype().equals("收入")) {
				bal1+=a.getPrice();
			}else if(a.getIOtype().equals("支出")){
				bal1-=a.getPrice();
			}
		}*/
		/*
	    if(bal1<0)
		    l_bal.setText("个人总收支余额为"+bal1+"元。您已超支，请适度消费！");	
		else  		
			l_bal.setText("个人总收支余额为"+bal1+"元。");  
		*/
	}
	public void actionPerformed(ActionEvent e) {
	     Object temp=e.getSource();
	     if(temp==mI[0]){
	    	new ModifyPwdFrame(username);//修改密码
	     }
	     else if(temp==mI[1]){
	    	 System.exit(0);//退出系统
	     }else if(temp==m_FMEdit){
	    	// new BalEditFrame(this);
	    	 new BalEditFrame();
	     }else if(temp==b_select1){  //根据收支类型查询	 
	        typeInquiry();
	     }else if(temp==b_select2){   //根据时间范围查询		 
	    	timeInquiry(e);
	     }else if(temp==m_ch1) {
	    	 new chartFrame();//显示图表
	     }else if(temp==m_ab1) {
	    	 new AboutFrame();
	     }else if(temp==b_contentType) {
	    	 contentInquiry();//根据内容查询
	     }else if(temp==b_all) {
	    	 startshow();//显示全部收支信息
	     }
    }
	//显示全部收支信息
	public void startshow() {
		getDataFromTxt gt=new getDataFromTxt();
		clearShow();
		screen.clear();
		ArrayList<oneAccount>list=new ArrayList<>();
				gt.getData(list);
		for(int i=0;i<list.size();i++) {
			oneAccount x=list.get(i);
				screen.add(x);
		}
		updateShow();
	}
	//按收支类型
	public void typeInquiry() {
		getDataFromTxt gt=new getDataFromTxt();
		if(c_type.getSelectedIndex()==0) {
			clearShow();
			screen.clear();
			ArrayList<oneAccount>list1=new ArrayList<>();
					gt.getData(list1);
    		for(int i=0;i<list1.size();i++) {
    			oneAccount a=list1.get(i);
    			if(a.getIOtype().equals("收入")) {
    				screen.add(a);
    			}
    		}
    		updateShow();
    	}else if(c_type.getSelectedIndex()==1) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list1=new ArrayList<>();
			gt.getData(list1);
	        for(int i=0;i<list1.size();i++) {
		        oneAccount a=list1.get(i);
		        if(a.getIOtype().equals("支出")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}
		
	}
	//按时间范围
	public void timeInquiry(ActionEvent e) {
		getDataFromTxt gt=new getDataFromTxt();
		clearShow();
		screen.clear();
		ArrayList<oneAccount>list2=new ArrayList<>();
		gt.getData(list2);
		for(int i=0;i<list2.size();i++) {
			oneAccount b=list2.get(i);
			if(b.getDate()>=Integer.parseInt(mymatter1.format(datepick1.getDate()))
					&&b.getDate()<=Integer.parseInt(mymatter2.format(datepick2.getDate()))) {
				try {
					screen.add(b);
				}catch(Exception exception) {
					JOptionPane.showMessageDialog(null,"非法输入！","录入失败",JOptionPane.ERROR_MESSAGE);
				}	
			}else if(b_clear==e.getSource()){   //清空输入框
				//clearInput();
			}
		}
		updateShow();
	}
	//按内容
	public void contentInquiry() {
		getDataFromTxt gt=new getDataFromTxt();
		if(c_contentType.getSelectedIndex()==0) {
			clearShow();
			screen.clear();
			ArrayList<oneAccount>list3=new ArrayList<>();
					gt.getData(list3);
    		for(int i=0;i<list3.size();i++) {
    			oneAccount a=list3.get(i);
    			if(a.getType().equals("购物")) {
    				screen.add(a);
    			}
    			
    		}
    		for(int i=0;i<screen.size();i++)
				System.out.println(screen.get(i).getType());
    		updateShow();
    	}else if(c_contentType.getSelectedIndex()==1) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("餐饮")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==2) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("居家")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==3) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("交通")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==4) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("娱乐")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==5) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("人情")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==6) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("工资")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==7) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("奖金")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}else if(c_contentType.getSelectedIndex()==8) {
    		clearShow();
    		screen.clear();
    		ArrayList<oneAccount>list3=new ArrayList<>();
			gt.getData(list3);
	        for(int i=0;i<list3.size();i++) {
		        oneAccount a=list3.get(i);
		        if(a.getType().equals("其他")) {
			         screen.add(a);
    			}
    		}
	        updateShow();
    	}   
	}
	//更新页面
	public void updateShow() {
		for(int i=0;i<screen.size();i++) {
			oneAccount a=screen.get(i);
			row[i][0]=a.getNo();
			row[i][1]=a.getDate();
			row[i][2]=a.getIOtype();
			row[i][3]=a.getType();
			row[i][4]=a.getPrice();
			table.updateUI();
		}
		table.updateUI();
		//judgeOver();
	}
	public void clearShow() {
		for(int i=0;i<screen.size();i++) {
			oneAccount a=screen.get(i);
			row[i][0]="";
			row[i][1]="";
			row[i][2]="";
			row[i][3]="";
			row[i][4]="";
			table.updateUI();
		}
		setColumnColor(table);
		table.updateUI();
	}
	//实现每行颜色变化
		public static void setColumnColor(JTable table) {
			try
			{
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
					private static final long serialVersionUID = 1L;
					public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,
							int row, int column){
						if(row%2 == 0) {
							setBackground(Color.decode("#F0F8FF"));//设置偶数行底色
							setForeground(Color.BLACK);
						}
						else if(row%2 == 1) {
							setBackground(Color.WHITE);//设置奇数行底色
							setForeground(Color.BLACK);
						}
						return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
					}
				};
				for(int i = 0; i < table.getColumnCount(); i++) {
					table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
				}
				tcr.setHorizontalAlignment(JLabel.CENTER);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}

	/*public static void main(String[] args) {
		 new MainFrame("tom");
	}*/
}
 