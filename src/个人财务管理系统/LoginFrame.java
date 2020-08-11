package 个人财务管理系统;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
public class LoginFrame extends JFrame implements ActionListener{
	private JLabel l_user,l_pwd,ll; //用户名标签，密码标签
	private JTextField t_user;//用户名文本框
	private JPasswordField t_pwd; //密码文本框
	private JButton b_ok,b_cancel; //登录按钮，退出按钮
	private JButton clear_user;//清除用户名文本框
	private JButton clear_pwd;//清除密码文本框
	/*默认用户名:tom 密码:t123 */
	public LoginFrame(){
		super("我是一个没有感情的账本");
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		Image icon=new ImageIcon("2.png").getImage();
		this.setIconImage(icon);
		ll=new JLabel("            ( $ _ $ )            ");
		ll.setForeground(Color.BLACK);
		l_user=new JLabel("用户名：",JLabel.RIGHT);
		l_user.setForeground(Color.BLACK);
		l_user.setBackground(Color.BLACK);
		l_pwd=new JLabel("    密码：",JLabel.RIGHT);
		l_pwd.setBackground(Color.WHITE);
		l_pwd.setForeground(Color.BLACK);
		t_user=new JTextField(31);
		t_user.setBackground(Color.WHITE);
		t_pwd=new JPasswordField(31);
		b_ok=new JButton("登录");
		b_ok.setForeground(Color.BLACK);
		b_ok.setMargin(new Insets(3,3,3,3));
		b_ok.setBackground(Color.WHITE);
		b_ok.setBorder(null);
		b_cancel=new JButton("退出");
		b_cancel.setForeground(Color.BLACK);
		b_cancel.setMargin(new Insets(3,3,3,3));
		b_cancel.setBorder(null);
		b_cancel.setBackground(Color.WHITE);
		clear_user=new JButton("×");
		clear_user.setForeground(Color.BLACK);
		clear_pwd=new JButton("×");
		clear_user.setBackground(Color.WHITE);
		clear_pwd.setBackground(Color.WHITE);
		clear_user.setBorder(null);
		clear_pwd.setBorder(null);
		
		t_user.setBorder(null);
		t_user.setText("tom");
		t_user.setEditable(false);
		clear_user.setVisible(false);
		
		Container c=this.getContentPane();
		JPanel cp=new JPanel();
		GridLayout g=new GridLayout(3,1);
		g.setVgap(0);
		cp.setLayout(g);
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
	    JPanel p2 = new JPanel();
	    p2.setBackground(Color.WHITE);
	    JPanel p3 = new JPanel();
	    p3.setBackground(Color.WHITE);
	    p1.add(l_user);
	    p1.add(t_user);
	    p1.add(clear_user);
	    p2.add(l_pwd);
	    p2.add(t_pwd);
	    p2.add(clear_pwd);
		p3.add(b_ok);
		p3.add(ll);
		p3.add(b_cancel);
		cp.add(p1);
		cp.add(p2);
		cp.add(p3);
		
		c.add(cp);
		
		//为按钮添加监听事件
		b_ok.addActionListener(this);
		b_cancel.addActionListener(this);
		//clear_user.addActionListener(this);
		clear_pwd.addActionListener(this);
        //界面大小不可调整 
		this.setResizable(false);
		this.setSize(455,150);
		
		//界面显示居中
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.show();
	}
	public static String getUser(){
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader("pwd.txt");
			br=new BufferedReader(fr);
			String a=br.readLine();
			return a;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	//从文件中读取用户名和密码
	public static String getPwd() {
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader("pwd.txt");
			br=new BufferedReader(fr);
			br.readLine();
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	public void actionPerformed(ActionEvent e){
		if(b_cancel==e.getSource()){
		    this.dispose();
		}else if(b_ok==e.getSource()){
	            //添加代码，验证身份成功后显示主界面
				//this.dispose();
				//new MainFrame(t_user.getText().trim());
			if(t_user.getText().equals(getUser())&&(String.valueOf(t_pwd.getPassword())).equals(getPwd())) {
				this.dispose();
				new MainFrame(t_user.getText().trim());
			}
			else {
				JOptionPane.showMessageDialog(null,"密码错误！","登录失败",JOptionPane.ERROR_MESSAGE);
			}
		}
		/*else if(clear_user==e.getSource()) {
			t_user.setText("");
		}*/
		else if(clear_pwd==e.getSource()) {
			t_pwd.setText("");
		}
	}
}
