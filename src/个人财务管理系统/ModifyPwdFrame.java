package 个人财务管理系统;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
public class ModifyPwdFrame extends JFrame implements ActionListener{
	private JLabel l_oldPWD,l_newPWD,l_newPWDAgain;
	private JPasswordField t_oldPWD,t_newPWD,t_newPWDAgain;
	private JButton b_ok,b_cancel;
	private String username;
	private JButton clear_old,clear_new,clear_again;
	public ModifyPwdFrame(String username){
		super("修改密码");
		this.username=username;
		Image icon=new ImageIcon("logoff.png").getImage();
		this.setIconImage(icon);
		l_oldPWD=new JLabel("    \u65E7\u5BC6\u7801\uFF1A");
		l_oldPWD.setBackground(Color.WHITE);
		l_oldPWD.setForeground(Color.BLACK);
		l_newPWD=new JLabel("    \u65B0\u5BC6\u7801\uFF1A");
		l_newPWDAgain=new JLabel("确认新密码：");
		l_newPWDAgain.setForeground(Color.BLACK);
		t_oldPWD=new JPasswordField(15);
		t_newPWD=new JPasswordField(15);
		t_newPWD.setBackground(Color.WHITE);
		t_newPWD.setForeground(Color.BLACK);
		t_newPWDAgain=new JPasswordField(15);
		b_ok=new JButton("确定");
		b_ok.setForeground(Color.BLACK);
		b_cancel=new JButton("取消");
		b_cancel.setForeground(Color.BLACK);
		b_ok.setBackground(Color.WHITE);
		b_cancel.setBackground(Color.WHITE);
		b_ok.setBorder(null);
		b_cancel.setBorder(null);
		clear_old=new JButton("x");
		clear_new=new JButton("x");
		clear_new.setForeground(Color.BLACK);
		clear_again=new JButton("x");
		clear_old.setBorder(null);
		clear_new.setBorder(null);
		clear_again.setBorder(null);
		clear_old.addActionListener(this);
		clear_new.addActionListener(this);
		clear_again.addActionListener(this);
		clear_old.setBackground(Color.WHITE);
		clear_new.setBackground(Color.WHITE);
		clear_again.setBackground(Color.WHITE);
		Container c=this.getContentPane();
		//clear_old.setPressedIcon(pressedIcon);
		JPanel cp=new JPanel(new GridLayout(4,1));
		JPanel p1=new JPanel();
		p1.setBackground(Color.WHITE);
		JPanel p2=new JPanel();
		p2.setBackground(Color.WHITE);
		JPanel p3=new JPanel();
		p3.setForeground(Color.BLACK);
		p3.setBackground(Color.WHITE);
		JPanel p4=new JPanel();
		p4.setBackground(Color.WHITE);
		p4.add(b_ok);
		p4.add(b_cancel);
		cp.add(p1);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p1.add(l_oldPWD);
		p1.add(t_oldPWD);
		p1.add(clear_old);
		cp.add(p2);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p2.add(l_newPWD);
		p2.add(t_newPWD);
		p2.add(clear_new);
		cp.add(p3);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p3.add(l_newPWDAgain);
		p3.add(t_newPWDAgain);
		p3.add(clear_again);
		cp.add(p4);
		c.add(cp);
		b_ok.addActionListener(this);
		b_cancel.addActionListener(this);
		this.setResizable(false);
		this.setSize(280,160);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.show();
	}
	public void modify(String npwd) {
		try {
			FileWriter fw1=new  FileWriter("pwd.txt");
			fw1.write("");
            fw1.flush(); 
            BufferedWriter bw=new BufferedWriter(fw1);
            bw.write(username);
            bw.newLine();
            bw.write(npwd);
            bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(b_cancel==e.getSource()){
			this.dispose();
		}else if(clear_old==e.getSource()) {
			t_oldPWD.setText("");
		}else if(clear_new==e.getSource()) {
			t_newPWD.setText("");
		}else if(clear_again==e.getSource()) {
			t_newPWDAgain.setText("");
		}else if(b_ok==e.getSource()){  //修改密码
			if(String.valueOf(t_oldPWD.getPassword()).equals(LoginFrame.getPwd())==false) {
				JOptionPane.showMessageDialog(null,"密码错误！","修改失败",JOptionPane.ERROR_MESSAGE);
			}
			else if(String.valueOf(t_newPWD.getPassword()).equals(String.valueOf(t_newPWDAgain.getPassword()))==false){
				JOptionPane.showMessageDialog(null,"新密码不一致，请重新输入","修改失败",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String np=String.valueOf(t_newPWD.getPassword());
				modify(np);
				this.dispose();
				JOptionPane.showMessageDialog(null,"您已成功修改密码","修改成功",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}