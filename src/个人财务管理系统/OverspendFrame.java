package 个人财务管理系统;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;
public class OverspendFrame extends JFrame{
		private Double amount;
		private JLabel label_jpg;
		public OverspendFrame(Double amount) {
			super("超支警告");
			this.amount=amount;
			init();
		}
		public void init() {
			getContentPane().setBackground(Color.WHITE);
			this.setResizable(false);
			Image icon=new ImageIcon("alarm.jpg").getImage();
			this.setIconImage(icon);
			this.setBackground(Color.WHITE);
			setSize(400,400);
			Dimension screen = this.getToolkit().getScreenSize();
		    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		    getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		    JLabel sentence=new JLabel("您已超支,当前结余的数目为");
		    sentence.setFont(new Font("黑体", Font.PLAIN, 25));
		    getContentPane().add(sentence);
		    JLabel l_amount=new JLabel("            "+amount+"            ");
		    l_amount.setFont(new Font("宋体", Font.PLAIN, 20));
		    l_amount.setForeground(Color.RED);
		    getContentPane().add(l_amount);
			ImageIcon background = new ImageIcon(myjpg());
			label_jpg = new JLabel(background);
			JButton button=new JButton("      朕知道了      ");
			button.setForeground(Color.BLACK);
			button.setFont(new Font("黑体", Font.PLAIN, 30));
			button.setBackground(Color.WHITE);
			getContentPane().add(label_jpg);
			button.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						byebye();
						}
		            }
			);
			getContentPane().add(button);
			setVisible(true);
		}
		public String myjpg() {
			double d = Math.random();
			int i = (int)(d*3);
			switch(i) {
				case 0:return "poor1.jpg";
				case 1:return "poor2.jpg";
				case 2:return "poor3.jpg";
				default:return "";
			}
		}
		public void byebye() {
			this.dispose();
		}
		/*public static void main(String[] args) {
			 new OverspendFrame(-10000.0);
		}*/
}
