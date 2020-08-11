package ���˲������ϵͳ;

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
//��֧�༭
public class BalEditFrame extends JFrame implements ActionListener{
	private JLabel l_id,l_date,l_bal,l_type,l_item;
	private JTextField t_id,t_date,t_bal;
	private JComboBox c_type,c_item;
	private JButton b_update,b_delete,b_select,b_new,b_clear;
	private JPanel p1,p2,p3;
	private JScrollPane scrollpane;
	private JTable table;
	private String[] cloum = { "���", "����", "����","����", "���"};
	private String s1[]={"����","֧��"};
	private String s2[]={"����","����","�Ӽ�","��ͨ","����","����","����","����","����"};
	private String s2in[]={"����","����","����"};
	private String s2out[]={"����","����","�Ӽ�","��ͨ","����","����","����"};
	private Object[][] row = new Object[50][5];
	private ArrayList<oneAccount> list=new ArrayList<>();
	
	private Date date;
	private JXDatePicker datepick;
    //���� date����
	private SimpleDateFormat mymatter;
	
	/*private MainFrame mf;���ݱ仯ʱ�ı�֮ǰ��������*/
	public BalEditFrame(){
		super("��֧�༭" );
		//mf=m;
		Image icon=new ImageIcon("BalEdit.jpg").getImage();
		this.setIconImage(icon);
		setBackground(Color.WHITE);
		l_id=new JLabel("��ţ�");
		l_id.setOpaque(true);
		l_id.setForeground(Color.BLACK);
		l_id.setBackground(Color.WHITE);
		l_date=new JLabel("���ڣ�");
		l_date.setOpaque(true);
		l_date.setBackground(Color.WHITE);
		l_date.setForeground(Color.BLACK);
		l_bal=new JLabel("��");
		l_bal.setOpaque(true);
		l_bal.setBackground(Color.WHITE);
		l_bal.setForeground(Color.BLACK);
		l_type=new JLabel("���ͣ�");
		l_type.setOpaque(true);
		l_type.setBackground(Color.WHITE);
		l_type.setForeground(Color.BLACK);
		l_item=new JLabel("���ݣ�");
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
        //���� date����
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
		
		b_select=new JButton("��ѯ");
		b_select.setForeground(Color.BLACK);
		b_select.setBackground(Color.WHITE);
		b_update=new JButton("�޸�");
		b_update.setForeground(Color.BLACK);
		b_update.setBackground(Color.WHITE);
		b_delete=new JButton("ɾ��");
		b_delete.setForeground(Color.BLACK);
		b_delete.setBackground(Color.WHITE);
		b_new=new JButton("¼��");
		b_new.setBackground(Color.WHITE);
		b_new.setForeground(Color.BLACK);
		b_clear=new JButton("���");
		b_clear.setForeground(Color.BLACK);
		b_clear.setBackground(Color.WHITE);
		
		Container c=this.getContentPane();
		c.setLayout(new BorderLayout());
		
		p1=new JPanel();
		p1.setForeground(Color.WHITE);
		p1.setBackground(Color.WHITE);
        p1.setLayout(new GridLayout(5,2,10,10));
        p1.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("�༭��֧��Ϣ"), 
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
		BorderFactory.createTitledBorder("��ʾ��֧��Ϣ"), 
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
		JLabel l1 = new JLabel("�������");
		p1.add(l1);
		this.getContentPane().add(p1);
		l1.setVisible(false);*/
		//cnm
	 
		b_update.addActionListener(this);
		b_delete.addActionListener(this);
		b_select.addActionListener(this);
		b_new.addActionListener(this);
		b_clear.addActionListener(this);
		
		
		//��Ӵ��룬Ϊtable���������¼�����addMouseListener
		table.addMouseListener(new java.awt.event.MouseAdapter(){
             public void mouseClicked(MouseEvent e) {//������굥��ʱ��Ӧ
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
		if(b_select==e.getSource()){  //��ѯ������֧��Ϣ
			inquiry();	 
		}else if(b_update==e.getSource()){  // �޸�ĳ����֧��Ϣ
			modifyIt();
		}else if(b_delete==e.getSource()){   //ɾ��ĳ����֧��Ϣ
			try {
				deleteData();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}else if(b_new==e.getSource()){   //����ĳ����֧��Ϣ 	
			try {
				addData();
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"�Ƿ����룡","¼��ʧ��",JOptionPane.ERROR_MESSAGE);
			}
		}else if(b_clear==e.getSource()){   //��������
			clearInput();
		}else if(c_type==e.getSource()) {
			changeItem();
		}
	}
	//��ѯʱ������Ϣ
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
	//ʵ��ÿ����ɫ�仯
	public static void setColumnColor(JTable table) {
		try
		{
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
				private static final long serialVersionUID = 1L;
				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,
						int row, int column){
					if(row%2 == 0) {
						//setBackground();//���������е�ɫ
						//setBackgroundColor(#87CEFA);
						setBackground(Color.decode("#F0F8FF"));
						setForeground(Color.BLACK);
					}
					else if(row%2 == 1) {
						setBackground(Color.WHITE);//����ż���е�ɫ
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

	//��ѯ
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
	//¼����֧��Ϣ
	public void addData() {
		int id=Integer.parseInt(t_id.getText());
		int da=Integer.parseInt(mymatter.format(datepick.getDate()));
		double bal=Double.parseDouble(t_bal.getText());
		try {
			id=Integer.parseInt(t_id.getText());
			da=Integer.parseInt(mymatter.format(datepick.getDate()));//��ȡ��ȷ��ʽ������
			bal=Double.parseDouble(t_bal.getText());
			if(oldId(id)==false) {
				//JOptionPane.showMessageDialog(null,"��������б���ظ���","����ظ�",JOptionPane.ERROR_MESSAGE);
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
	//�ж���������Ƿ��Ѵ���
	public boolean oldId(int id) {
		for(int i=0;i<list.size();i++) {
			oneAccount a=list.get(i);
			if(id==a.getNo()) {
				return false;
			}
		}
		return true;
	}
	//��������
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
	//�Զ���������Ϣ��д����ߵġ��༭��֧��Ϣ����
	public void showDataLeft() {
		//�õ�ѡ�е����е�����ֵ
		//�õ�ѡ�еĵ�Ԫ���ֵ������ж����ַ���
		try {
	        int r= table.getSelectedRow();
	        if(r<list.size()) {
	        	String info0=table.getValueAt(r,0).toString();
		        t_id.setText(info0);
		        t_id.setEditable(false);//��������Ų��ɱ༭
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
	//ɾ��
	public void deleteData() {
		if(JOptionPane.showConfirmDialog(null, 
				"��ȷ��Ҫɾ��������֧��¼��", "��ȷ��", 
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
			//���ݺ��ļ����Ѿ�û�ˣ���ǰ����ȴ������ʾ���£����ǽ���ɱ�ġ���>�ٸ�һ��
			//�ܶ�����ע�ͣ�֪��Ϊɶ�ˣ��������ٸ�һ��
			//updateShow();
			//inquiry();
			this.dispose();
			new BalEditFrame();
			
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"�����ұߵ��һ��Ҫɾ������Ϣ��","ɾ��ʧ��",JOptionPane.ERROR_MESSAGE);
		}	
	}
	//�޸�
	public void modifyIt() {
		if(JOptionPane.showConfirmDialog(null, 
				"��ȷ��Ҫ�޸Ĵ�����֧��¼��", "��ȷ��", 
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
			JOptionPane.showMessageDialog(null,"�����ұߵ��һ����Ϣ������������޸ĺ����Ϣ��","�޸�ʧ��",JOptionPane.ERROR_MESSAGE);
		}
		updateDataTxt();
		inquiry();
		judgeBudget();
	}
	//���ݸı�/ɾ�����ļ�����
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
	//�ж��Ƿ�֧
	public void judgeBudget() {
		Double sum=0.0;
		oneAccount a;
		ArrayList<oneAccount> jl=new ArrayList<>();
		getDataFromTxt jg=new getDataFromTxt();
		jg.getData(jl);
		for(int i=0;i<jl.size();i++) {
			a=jl.get(i);
			if(a.getIOtype().equals("����")) {
				sum+=a.getPrice();
			}
			else if(a.getIOtype().equals("֧��")) {
				sum-=a.getPrice();
			}
		}
		if(sum<0)
			new OverspendFrame(sum);
	}
	//�õ���һ�����
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

