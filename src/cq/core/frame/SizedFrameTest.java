package cq.core.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SizedFrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame();
				MyDrawPanel panel = new MyDrawPanel();
				frame.getContentPane().add(panel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 300);
				frame.setVisible(true);

			}
		});
	}
}
class SizedFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SizedFrame(){
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		this.setSize(width/3,height/3);
		//this.setResizable(false);//不能再放大缩小窗口了
		this.setLocationByPlatform(true);
		this.setTitle("Sized Frame");
		this.add(new StringComponent());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 200);
		Image img=new ImageIcon("images/3.jpg").getImage();
		this.setIconImage(img);
		pack();
		this.setVisible(true);
		
		
	}
}
class StringComponent extends JComponent{
	private static final long serialVersionUID = -1332285703212863497L;
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString("not a hello world", 75, 100);
	}
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(200,200);
	}
}
class NotHelloWorldFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public NotHelloWorldFrame() {
		// TODO Auto-generated constructor stub
		add(new StringComponent());
		this.setTitle("Sized Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
		
	}
}
class MyDrawPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponents(g);
		this.setBackground(Color.red);
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.orange);
		g.fillRect(20, 50, 100, 100);
		this.setBackground(Color.red);
	}
	public MyDrawPanel() {
		// TODO Auto-generated constructor stub
		JLabel label = new JLabel("aafasdfasfa");
		setBackground(Color.blue);
		add(label);
		
		
	}
	public static void main(String[] args){

		
	}
}
