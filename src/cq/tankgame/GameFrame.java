package cq.tankgame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;



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
 

class TankPanel extends JPanel implements Runnable ,KeyListener{
	private static final long serialVersionUID = 1L;
	private Hero hero=new Hero(150,50,Dir.UP);
	private LinkedList<Enemy>enemies;
	private Manager manager;
	public TankPanel() {
		// TODO Auto-generated constructor stub
		this.setSize(600,500);
		enemies=new LinkedList<Enemy>();
		manager=new Manager(hero, enemies);
		for (int i = 0; i < 10; i++) {
			Enemy enemy = new Enemy(50+i*70,400,manager);
			new Thread(enemy).start();
			enemies.add(enemy);
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Color c=g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(40,40, 720, 520);
		g.setColor(Color.black);
		g.fillRect(50,50,700, 500);
		g.setColor(c);
		hero.draw(g);
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
	}
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			hero.setDir(Dir.UP);
			hero.move();
		}
		else if(e.getKeyCode()==KeyEvent.VK_D)
		{
			//向右前进
			hero.setDir(Dir.RIGHT);
			hero.move();
		}
		else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			//向下前进
			hero.setDir(Dir.DOWN);
			hero.move();
		}
		else if(e.getKeyCode()==KeyEvent.VK_A)
		{//向左前进
			hero.setDir(Dir.LEFT);
			hero.move();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
	
}