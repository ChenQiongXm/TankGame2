package cq.tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

class TankPanel extends JPanel implements Runnable ,KeyListener{
	private static final long serialVersionUID = 1L;
	private Hero hero=new Hero(150,50,Dir.UP);
	private LinkedList<Enemy>enemies;
	private Iterator<Enemy>enemyIterator;
	private LinkedList<IronWall> ironWalls;
	private Iterator<IronWall> iroIterator;
	private LinkedList<WoodWall> woodWalls;
	private Iterator<WoodWall>woodIterator;
	private Manager manager;
	public TankPanel() {
		// TODO Auto-generated constructor stub
		this.setSize(600,500);
		enemies=new LinkedList<Enemy>();
		ironWalls=new LinkedList<IronWall>();
		woodWalls=new LinkedList<WoodWall>();
		manager=new Manager(hero, enemies,ironWalls);
		for (int i = 0; i < 10; i++) {
			Enemy enemy = new Enemy(50+i*70,400,manager);
			new Thread(enemy).start();
			enemies.add(enemy);
		}
		for (int i = 0; i < 5; i++) {
			ironWalls.add(new IronWall(50+i*140, 200));
			ironWalls.add(new IronWall(50+i*140+Wall.width, 200));
			ironWalls.add(new IronWall(50+i*140, 200+Wall.height));
			ironWalls.add(new IronWall(50+i*140+Wall.width, 200+Wall.height));
		}
		for (int i = 0; i < 5; i++) {
			WoodWall woodWall=new WoodWall(50+i*170, 300);
			woodWalls.add(woodWall);
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
		if(hero.isAlive)hero.draw(g);
		enemyIterator=enemies.iterator();
		woodIterator=woodWalls.iterator();
		iroIterator=ironWalls.iterator();
		while(enemyIterator.hasNext()){
			Enemy enemy=enemyIterator.next();
			manager.checkBeShot(enemy, hero);
			if(enemy.isAlive)enemy.draw(g);
			else enemyIterator.remove();
		}
		while(woodIterator.hasNext()){
			WoodWall wall=woodIterator.next();
			if(wall.isAlive)wall.draw(g);
			else woodIterator.remove();
		}
		while(iroIterator.hasNext()){
			IronWall wall=iroIterator.next();
			if(wall.isAlive)wall.draw(g);
			else iroIterator.remove();
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
		manager.checkCollidWalls(hero);
		System.out.println(manager.checkCollidEnemies(hero));
		hero.keyDeal(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
	
}
