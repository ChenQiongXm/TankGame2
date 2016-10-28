package cq.tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import cq.tankgame.client.Hero;
import cq.tankgame.client.TankClient;
import cq.tankgame.server.*;
class TankPanel extends JPanel implements Runnable ,KeyListener{
	private static final long serialVersionUID = 1L;
	private LinkedList<Enemy>enemies;
	private Iterator<Enemy>enemyIterator;
	private Hero hero;
	private Hero hero2;
	private LinkedList<IronWall> ironWalls;
	private Iterator<IronWall> iroIterator;
	private LinkedList<WoodWall> woodWalls;
	private Iterator<WoodWall>woodIterator;
	private TankClient tankClient;
	private Manager manager;
	public TankPanel() {
		// TODO Auto-generated constructor stub
		this.setSize(600,500);
		hero=new Hero(100, 100, Dir.UP);
		hero2=new Hero(300, 100, Dir.UP);
		enemies=new LinkedList<Enemy>();
		ironWalls=new LinkedList<IronWall>();
		woodWalls=new LinkedList<WoodWall>();
		tankClient=new TankClient(hero,hero2,enemies);
		manager=new Manager(hero, enemies, ironWalls);
		hero.setManager(manager);
		new Thread(hero).start();
		new Thread(tankClient).start();
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
		if(hero!=null&&hero.isAlive)hero.draw(g);
		if(hero2!=null&&hero2.isAlive)hero2.draw(g);
		enemyIterator=enemies.iterator();
		woodIterator=woodWalls.iterator();
		iroIterator=ironWalls.iterator();
		while(enemyIterator.hasNext()){
			Enemy enemy=enemyIterator.next();
			enemy.draw(g);
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
				hero2=tankClient.getHero2();
				enemies=tankClient.getEnemies();
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
		//hero=tankClient.getHero2();
		manager.checkCollidWalls(hero);
		hero.keyDeal(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
		hero.keyRel(e.getKeyCode());
	}
	
}
