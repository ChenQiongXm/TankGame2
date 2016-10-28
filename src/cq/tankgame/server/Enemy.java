package cq.tankgame.server;

import cq.tankgame.Dir;
import cq.tankgame.Manager;
import cq.tankgame.Tank;

public class Enemy extends Tank implements Runnable{
	private static final long serialVersionUID = 1L;
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
