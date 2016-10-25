package cq.core.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class ActionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				@SuppressWarnings("unused")
				JFrame frame = new ActionFrame();
			}
		});
	}
	
}
class ActionFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel myPanel=new JPanel();
	public ActionFrame() {
		// TODO Auto-generated constructor stub
		myPanel.setSize(100, 100);
		this.add(myPanel,BorderLayout.CENTER);
		ImageIcon icon=new ImageIcon(new ImageIcon("images/2.gif").getImage().getScaledInstance(20, 20, 4));
		ImageIcon icon2=new ImageIcon(new ImageIcon("images/3.jpg").getImage().getScaledInstance(20, 20, 4));
		ImageIcon icon3=new ImageIcon(new ImageIcon("images/4.gif").getImage().getScaledInstance(20, 10, 4));
		Action blue=new ColorAction("BLUE", icon, Color.blue);
		Action red=new ColorAction("RED", icon2, Color.red);
		Action green=new ColorAction("GREEN", icon3, Color.green);
	
		myPanel.add(new JButton(red));
		myPanel.add(new JButton(blue));
		myPanel.add(new JButton(green));
		this.setTitle("Action Frame");
		InputMap imap=myPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl g"), "panel.green");
		
		//associate the names with actons
		ActionMap map=myPanel.getActionMap();
		map.put("panel.red", red);
		map.put("panel.blue", blue);
		map.put("panel.green", green);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200,300);
		this.setVisible(true);
	}
	public class ColorAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Color c=(Color) getValue("color");
			myPanel.setBackground(c);
		}
		public ColorAction(String name,Icon icon,Color c) {
			putValue(NAME, name);
			putValue(SMALL_ICON, icon);
			putValue(SHORT_DESCRIPTION, "set Panle color to"+name.toLowerCase());
			putValue("color", c);
		}
		
	}
}