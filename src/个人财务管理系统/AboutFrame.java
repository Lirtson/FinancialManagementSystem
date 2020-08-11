package 个人财务管理系统;
import javax.swing.*;
import java.awt.*;
public class AboutFrame extends JFrame{
	public AboutFrame() {
		super("关于");
		this.setResizable(false);
		Image icon=new ImageIcon("about.jpg").getImage();
		this.setIconImage(icon);
		this.setBackground(Color.WHITE);
		setSize(500,400);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		ImageIcon background = new ImageIcon("B.jpeg");
		JLabel label = new JLabel(background);
		label.setBounds(0,0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		setVisible(true);
	}
}
