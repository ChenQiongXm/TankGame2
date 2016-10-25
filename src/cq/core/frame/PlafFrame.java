package cq.core.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class PlafFrame extends JFrame {
	private JPanel buttonPanel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	PlafFrame frame=	new PlafFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 300);
		frame.setVisible(true);
	}
	public PlafFrame() {
		// TODO Auto-generated constructor stub
		buttonPanel=new JPanel();
		//得到不同的感官
		UIManager.LookAndFeelInfo[] infos=UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : infos) {
			makeButton(info.getName(), info.getClassName());
		}
		add(buttonPanel);
		//pack();
		
	}
	public void makeButton(String name,final String plafName){
		JButton button =new JButton(name);
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(PlafFrame.this);
					//pack();
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
