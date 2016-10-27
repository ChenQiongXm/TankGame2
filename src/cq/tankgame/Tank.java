package cq.tankgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Tank {
	protected int x;
	protected int y;
	int speed=10;
	protected Dir direction;
	protected ImageIcon body;
	protected LinkedList<Shot> shots;
	protected boolean isAlive=true;
	boolean isCollided=false;
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
		shots=new LinkedList<Shot>();
	}
	public Tank(int x,int y,Dir direction) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.body=direction.getBody();
		shots=new LinkedList<Shot>();
	}
	public void draw(Graphics g){
		this.body=direction.getBody();
		g.drawImage(body.getImage(),x,y,null);
		Iterator<Shot>iterator=shots.iterator();
			while(iterator.hasNext()) {
				Shot shot=iterator.next();
				if(!shot.isAlive)
					iterator.remove();
				else shot.draw(g);
			}
	}
	public void setDir(Dir direction) {
		this.direction = direction;
	}
	public void move(){
		switch (direction) {
		case UP:
			if(y>40)y-=speed;
			break;
		case DOWN:
			if(y<460)y+=speed;
			break;
		case LEFT:
			if(x>40)x-=speed;
			break;
		case RIGHT:
			if(x<700)x+=speed;
			break;
		default:
			break;
		}
	}
}
class Hero extends Tank{

	public Hero(int x, int y,Dir direction) {
		super(x, y, direction);
		// TODO Auto-generated constructor stub
	}

	public void keyDeal(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.VK_W)
		{
			if(direction!=Dir.UP)setDir(Dir.UP);
			else if(!isCollided)move();
			else isCollided=false;
		}
		else if(keyCode==KeyEvent.VK_D)
		{
			//向右前进
			if(direction!=Dir.RIGHT)setDir(Dir.RIGHT);
			else if(!isCollided)move();
			else isCollided=false;
		}
		else if(keyCode==KeyEvent.VK_S)
		{
			//向下前进
			if(direction!=Dir.DOWN)setDir(Dir.DOWN);
			else if(!isCollided)move();
			else isCollided=false;
		}
		else if(keyCode==KeyEvent.VK_A)
		{//向左前进
			if(direction!=Dir.LEFT)setDir(Dir.LEFT);
			else if(!isCollided)move();
			else isCollided=false;
		}else if(keyCode==KeyEvent.VK_J){
			Shot shot=new Shot(x, y, direction);
			shots.add(shot);
			new Thread(shot).start();
		}
	}
}
class Enemy extends Tank implements Runnable{
	private int time=10;
	private Manager manager;
	public Enemy(int x,int y,Manager manager) {
		// TODO Auto-generated constructor stub
		super(x, y);
		this.direction=getRandom();
		this.body=direction.getBody();
		this.speed=4;
		this.manager=manager;
	}
	public Enemy(int x, int y, Dir direction) {
		super(x, y, direction);
		this.speed=5;
	}
	@Override
	public void run() {
		while(isAlive){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			manager.checkEnemyCollid(this);
			if(!isCollided) move();
			else time=0;
			if(time==0){
				this.setDir(getRandom());
				isCollided=false;
				time=(int)(Math.random()*20)+20;
			}
			time--;
		}
	}
	private Dir getRandom(){
		Dir dir=null;
		switch((int)(Math.random()*4)){
		case 0:
			dir=Dir.UP;
			break;
		case 1:
			dir= Dir.DOWN;
			break;
		case 2:
			dir=Dir.LEFT;
			break;
		case 3:
			dir= Dir.RIGHT;
			break;
		default:
			break;
		}
		return dir;
	}
}