package 个人财务管理系统;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.Color;
//收支编辑
public class BalEditFrame extends JFrame implements ActionListener{
	private JLabel l_id,l_date,l_bal,l_type,l_item;
	private JTextField t_id,t_date,t_bal;
	private JComboBox c_type,c_item;
	private JButton b_update,b_delete,b_select,b_new,b_clear;
	private JPanel p1,p2,p3;
	private JScrollPane scrollpane;
	private JTable table;
	private String[] cloum = { "编号", "日期", "类型","内容", "金额"};
	private String s1[]={"收入","支出"};
	private String s2[]={"购物","餐饮","居家","交通","娱乐","人情","工资","奖金","其他"};
	private String s2in[]={"工资","奖金","其他"};
	private String s2out[]={"购物","餐饮","居家","交通","娱乐","人情","其他"};
	private Object[][] row = new Object[50][5];
	private ArrayList<oneAccount> list=new ArrayList<>();
	
	private Date date;
	private JXDatePicker datepick;
    //设置 date日期
	private SimpleDateFormat mymatter;
	
	/*private MainFrame mf;数据变化时改变之前的主界面*/
	public BalEditFrame(){
		super("收支编辑" );
		//mf=m;
		Image icon=new ImageIcon("BalEdit.jpg").getImage();
		this.setIconImage(icon);
		setBackground(Color.WHITE);
		l_id=new JLabel("编号：");
		l_id.setOpaque(true);
		l_id.setForeground(Color.BLACK);
		l_id.setBackground(Color.WHITE);
		l_date=new JLabel("日期：");
		l_date.setOpaque(true);
		l_date.setBackground(Color.WHITE);
		l_date.setForeground(Color.BLACK);
		l_bal=new JLabel("金额：");
		l_bal.setOpaque(true);
		l_bal.setBackground(Color.WHITE);
		l_bal.setForeground(Color.BLACK);
		l_type=new JLabel("类型：");
		l_type.setOpaque(true);
		l_type.setBackground(Color.WHITE);
		l_type.setForeground(Color.BLACK);
		l_item=new JLabel("内容：");
		l_item.setOpaque(true);
		l_item.setBackground(Color.WHITE);
		l_item.setForeground(Color.BLACK);
		t_id=new JTextField(8);
		t_id.setForeground(Color.BLACK);
		t_id.setBackground(Color.WHITE);
		/*t_date=new JTextField(8);
		t_date.setBackground(Color.WHITE);*/
		date = new Date();
		datepick = new JXDatePicker();
        //设置 date日期
		mymatter = new SimpleDateFormat("yyyyMMdd");
		datepick.setFormats(mymatter);
		datepick.setDate(date);

		t_bal=new JTextField(8);
		t_bal.setBackground(Color.WHITE);

		
		c_type=new JComboBox(s1);
		c_type.setBackground(Color.WHITE);
		c_type.setForeground(Color.BLACK);
		c_item=new JComboBox(s2in);
		c_item.setForeground(Color.BLACK);
		c_item.setBackground(Color.WHITE);
		
		b_select=new JButton("查询");
		b_select.setForeground(Color.BLACK);
		b_select.setBackground(Color.WHITE);
		b_update=new JButton("修改");
		b_update.setForeground(Color.BLACK);
		b_update.setBackground(Color.WHITE);
		b_delete=new JButton("删除");
		b_delete.setForeground(Color.BLACK);
		b_delete.setBackground(Color.WHITE);
		b_new=new JButton("录入");
		b_new.setBackground(Color.WHITE);
		b_new.setForeground(Color.BLACK);
		b_clear=new JButton("清空");
		b_clear.setForeground(Color.BLACK);
		b_clear.setBackground(Color.WHITE);
		
		Container c=this.getContentPane();
		c.setLayout(new BorderLayout());
		
		p1=new JPanel();
		p1.setForeground(Color.WHITE);
		p1.setBackground(Color.WHITE);
        p1.setLayout(new GridLayout(5,2,10,10));
        p1.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("编辑收支信息"), 
        BorderFactory.createEmptyBorder(5,5,5,5)));
		p1.add(l_id);
		p1.add(t_id);
		p1.add(l_date);
		//p1.add(t_date);
		p1.add(datepick);
		p1.add(l_type);
		p1.add(c_type);
		p1.add(l_item);
		p1.add(c_item);
		p1.add(l_bal);
		p1.add(t_bal);
		c.add(p1, BorderLayout.WEST);
		
		p2=new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setLayout(new GridLayout(5,1,10,10));
		p2.add(b_new);
		p2.add(b_update);
		p2.add(b_delete);
		p2.add(b_select);
		p2.add(b_clear);
	    
		c.add(p2,BorderLayout.CENTER);	
		
		p3=new JPanel();
		p3.setBackground(Color.WHITE);
		p3.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("显示收支信息"), 
		BorderFactory.createEmptyBorder(5,5,5,5)));		
		
		c_type.addActionListener(this);
		
		table = new JTable(row, cloum);
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		scrollpane = new JScrollPane(table);
		scrollpane.setViewportView(table);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p3.add(scrollpane);
		c.add(p3,BorderLayout.EAST);	
		
		/*CalendarPanel p1 = new CalendarPanel(t_date, "yyyyMMdd");
		p1.initCalendarPanel();
		JLabel l1 = new JLabel("日历面板");
		p1.add(l1);
		this.getContentPane().add(p1);
		l1.setVisible(false);*/
		//cnm
	 
		b_update.addActionListener(this);
		b_delete.addActionListener(this);
		b_select.addActionListener(this);
		b_new.addActionListener(this);
		b_clear.addActionListener(this);
		
		
		//添加代码，为table添加鼠标点击事件监听addMouseListener
		table.addMouseListener(new java.awt.event.MouseAdapter(){
             public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
            	 showDataLeft();}
         }); 
		inquiry();
		judgeBudget();
		
	    this.setResizable(false);
		this.setSize(800,300);
	   // this.setSize(1200,300);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.show();
		t_id.setEditable(false);
	}
	public void actionPerformed(ActionEvent e) {
		if(b_select==e.getSource()){  //查询所有收支信息
			inquiry();	 
		}else if(b_update==e.getSource()){  // 修改某条收支信息
			modifyIt();
		}else if(b_delete==e.getSource()){   //删除某条收支信息
			try {
				deleteData();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}else if(b_new==e.getSource()){   //新增某条收支信息 	
			try {
				addData();
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"非法输入！","录入失败",JOptionPane.ERROR_MESSAGE);
			}
		}else if(b_clear==e.getSource()){   //清空输入框
			clearInput();
		}else if(c_type==e.getSource()) {
			changeItem();
		}
	}
	//查询时更新信息
	public  void updateShow() {
		t_id.setEditable(true);
		for(int i=0;i<list.size();i++) {
			oneAccount a=list.get(i);
			row[i][0]=a.getNo();
			row[i][1]=a.getDate();
			row[i][2]=a.getIOtype();
			row[i][3]=a.getType();
			row[i][4]=a.getPrice();
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
						//setBackground();//设置奇数行底色
						//setBackgroundColor(#87CEFA);
						setBackground(Color.decode("#F0F8FF"));
						setForeground(Color.BLACK);
					}
					else if(row%2 == 1) {
						setBackground(Color.WHITE);//设置偶数行底色
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

	//查询
	public void inquiry() {
		//t_id.setEditable(true);
		list.clear();
		getDataFromTxt gt=new getDataFromTxt();
		gt.getData(list);
		updateShow();
		t_id.setEditable(true);
		t_id.setText(getNewNum()+"");
		t_id.setEditable(false);
	}
	//录入收支信息
	public void addData() {
		int id=Integer.parseInt(t_id.getText());
		int da=Integer.parseInt(mymatter.format(datepick.getDate()));
		double bal=Double.parseDouble(t_bal.getText());
		try {
			id=Integer.parseInt(t_id.getText());
			da=Integer.parseInt(mymatter.format(datepick.getDate()));//获取正确格式的日期
			bal=Double.parseDouble(t_bal.getText());
			if(oldId(id)==false) {
				//JOptionPane.showMessageDialog(null,"编号与已有编号重复！","编号重复",JOptionPane.ERROR_MESSAGE);
				t_id.setText(getNewNum()+"");
				id=Integer.parseInt(t_id.getText());
			}
			/*else {*/
				oneAccount a=new oneAccount(id,da,s1[c_type.getSelectedIndex()],s2[c_item.getSelectedIndex()],bal);
				FileWriter fw1=new  FileWriter("MyData.txt",true);
				fw1.write(String.valueOf(a.getNo())+","+String.valueOf(a.getDate())+","+a.getIOtype()+","+a.getType()
				+","+String.valueOf(a.getPrice())+"\n");
				fw1.flush();
				inquiry();
				judgeBudget();
				//mf.judgeOver();
			//}
		}
		catch(IOException e ) {
			e.printStackTrace();
		}
	}
	//判断新增编号是否已存在
	public boolean oldId(int id) {
		for(int i=0;i<list.size();i++) {
			oneAccount a=list.get(i);
			if(id==a.getNo()) {
				return false;
			}
		}
		return true;
	}
	//清空输入框
	public void clearInput() {
		//t_date.setText("");
		Date clear_d = new Date();
		datepick.setDate(clear_d);
		t_bal.setText("");
		c_type.setSelectedIndex(0);
		c_item.setSelectedIndex(0);
		t_id.setEditable(true);
		t_id.setText(getNewNum()+"");
		t_id.setEditable(false);
	}
	//自动将该条信息填写在左边的“编辑收支信息”内
	public void showDataLeft() {
		//得到选中的行列的索引值
		//得到选中的单元格的值，表格中都是字符串
		try {
	        int r= table.getSelectedRow();
	        if(r<list.size()) {
	        	String info0=table.getValueAt(r,0).toString();
		        t_id.setText(info0);
		        t_id.setEditable(false);//！！！编号不可编辑
		        /*String info1=table.getValueAt(r,1).toString();
		        /t_date.setText(info1);*/
		        String info1=table.getValueAt(r,1).toString();
		        Date show_date = new SimpleDateFormat("yyyyMMdd").parse(info1);
		        datepick.setDate(show_date);
		        String info4=table.getValueAt(r,4).toString();
		        t_bal.setText(info4);
		        String info2=table.getValueAt(r,2).toString();
		        for(int i=0;i<s1.length;i++) {
		        	if(info2.equals(s1[i]))
		        		c_type.setSelectedIndex(i);
		        }
		        String info3=table.getValueAt(r,3).toString();
		        for(int i=0;i<s2.length;i++) {
		        	if(info3.equals(s2[i]))
		        		c_item.setSelectedIndex(i);
		        }
		       
	        }
	        
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//删除
	public void deleteData() {
		if(JOptionPane.showConfirmDialog(null, 
				"你确认要删除此条收支记录吗？", "请确认", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)!=JOptionPane.YES_OPTION) {
			return;
		}
		t_id.setEditable(true);
		FileReader fr;
		try {
			fr = new  FileReader("MyData.txt");
			BufferedReader br=new BufferedReader(fr);
			list.clear();
			String linetext;
			String [] words=new String [5];
			while((linetext=br.readLine())!=null) {
				words=linetext.split(",");
				if(!words[0].equals(table.getValueAt(table.getSelectedRow(),0).toString())) {
					list.add(new oneAccount(Integer.parseInt(words[0]),
							Integer.parseInt(words[1]),words[2],words[3],Double.parseDouble(words[4])));
				}
			}
			br.close();
			updateDataTxt();
			//数据后文件里已经没了，当前界面却不能显示更新，于是渐起杀心——>再搞一个
			//很多天后的注释：知道为啥了，但还是再搞一个
			//updateShow();
			//inquiry();
			this.dispose();
			new BalEditFrame();
			
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"请在右边点击一条要删除的信息！","删除失败",JOptionPane.ERROR_MESSAGE);
		}	
	}
	//修改
	public void modifyIt() {
		if(JOptionPane.showConfirmDialog(null, 
				"你确认要修改此条收支记录吗？", "请确认", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)!=JOptionPane.YES_OPTION) {
			return;
		}
		try {
			int id=Integer.parseInt(t_id.getText());
			int da=Integer.parseInt(mymatter.format(datepick.getDate())); 
			String sz=s1[c_type.getSelectedIndex()];
			String content=s2[c_item.getSelectedIndex()];
			double bal=Double.parseDouble(t_bal.getText());
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getNo()==id) {
					list.remove(i);
					list.add(new oneAccount(id,da,sz,content,bal));
					break;
				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,"请在右边点击一条信息并在左边输入修改后的信息！","修改失败",JOptionPane.ERROR_MESSAGE);
		}
		updateDataTxt();
		inquiry();
		judgeBudget();
	}
	//数据改变/删除后，文件更新
	public void updateDataTxt() {
		FileWriter fw;
		try {
			fw = new FileWriter("MyData.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int i=0;i<list.size();i++) {
				oneAccount a=list.get(i);
				bw.write(String.valueOf(a.getNo())+","+String.valueOf(a.getDate())+","+a.getIOtype()+","+a.getType()
				+","+String.valueOf(a.getPrice()));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//判断是否超支
	public void judgeBudget() {
		Double sum=0.0;
		oneAccount a;
		ArrayList<oneAccount> jl=new ArrayList<>();
		getDataFromTxt jg=new getDataFromTxt();
		jg.getData(jl);
		for(int i=0;i<jl.size();i++) {
			a=jl.get(i);
			if(a.getIOtype().equals("收入")) {
				sum+=a.getPrice();
			}
			else if(a.getIOtype().equals("支出")) {
				sum-=a.getPrice();
			}
		}
		if(sum<0)
			new OverspendFrame(sum);
	}
	//得到下一个编号
	public Integer getNewNum() {
		getDataFromTxt gt=new getDataFromTxt();
		ArrayList<oneAccount> alist=new ArrayList<>();
		gt.getData(alist);
		Integer res=0;
		for(int i=1;i<9999999;i++) {
			int flag=0;
			for(int j=0;j<alist.size();j++) {
				if(i==alist.get(j).getNo())
					flag=1;
			}
			if(flag==0) {
				res=i;
				break;
			}
		}
		return res;
	}
	public void changeItem() {
		if(c_type.getSelectedIndex()==0) {
			c_item.removeAllItems();
			for(int i=0;i<s2in.length;i++)
			c_item.addItem(s2in[i]);
		}
		else {
			c_item.removeAllItems();
			for(int i=0;i<s2out.length;i++)
			c_item.addItem(s2out[i]);
		}
	}
	/*public static void main(String[] args) {
	 new BalEditFrame();
	}*/
}
class JTableCellRender extends JLabel implements TableCellRenderer {
    @Override

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        return (JLabel)value;

    }

}

