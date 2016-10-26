package cq.tankgame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Tank {
	protected int x;
	protected int y;
	protected int speed=10;
	protected Dir direction;
	protected ImageIcon body;
	protected boolean isAlive=true;
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
	}
	public Tank(int x,int y,Dir direction) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.body=direction.getBody();
	}
	public void draw(Graphics g){
		this.body=direction.getBody();
		g.drawImage(body.getImage(),x,y,null);
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
}
class Enemy extends Tank implements Runnable{
	private int time=10;
	boolean isCollided=false;
	private Manager manager;
	public Enemy(int x,int y,Manager manager) {
		// TODO Auto-generated constructor stub
		super(x, y);
		this.direction=getRandom();
		this.speed=2;
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
			manager.checkCollid(this);
			if(!isCollided) move();
			if(time==0){
				this.setDir(getRandom());
				isCollided=false;
				time=(int)(Math.random()*10)+20;
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