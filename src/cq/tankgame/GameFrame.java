package cq.tankgame;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;


/**
 * 游戏界面
 * @author chenq
 *
 */
public class GameFrame extends JFrame {
	private TankPanel panel;
	private JMenuBar menuBar;
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new GameFrame();
			}
		});
	}
	//初始化界面
	public GameFrame() {
		// TODO Auto-generated constructor stub
		panel=new TankPanel();
		menuBar=new JMenuBar();
		//游戏菜单		
		JMenu game=new JMenu("游戏");
		JMenuItem gameStart=game.add(new JMenuItem("开始游戏"));
		JMenuItem gamePause=game.add(new JMenuItem("暂停游戏"));
		JMenuItem gameAgain=game.add(new JMenuItem("继续游戏"));
		game.addSeparator();
		JMenuItem gameOver=game.add(new JMenuItem("结束游戏"));
		gameOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}});
		//设置皮肤菜单
		JMenu setting=new JMenu("设置皮肤");
		UIManager.LookAndFeelInfo[] infos=UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo feel : infos) {
			JMenuItem s=setting.add(new JMenuItem(feel.getName()));
			s.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						UIManager.setLookAndFeel(feel.getClassName());
						SwingUtilities.updateComponentTreeUI(GameFrame.this);
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		menuBar.add(game);
		menuBar.add(setting);
		this.setJMenuBar(menuBar);
		new Thread(panel).start();
		this.add(panel);
		this.addKeyListener(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,700);
		setLocation(100, 100);
		setVisible(true);
	}
}
 