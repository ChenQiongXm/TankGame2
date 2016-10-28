package cq.tankgame.client;

import java.awt.event.KeyEvent;
import cq.tankgame.Dir;
import cq.tankgame.Manager;
import cq.tankgame.Shot;
import cq.tankgame.Tank;

public class Hero extends Tank implements Runnable{
	private static final long serialVersionUID = 1L;
	private boolean dirKey[]=new boolean[4];
	private boolean hasKey=false;
	private Manager manager;
	public Hero(int x, int y,Dir direction) {
		super(x, y, direction);
		// TODO Auto-generated constructor stub
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void keyDeal(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.VK_W)
		{
			setDir(Dir.UP);
			hasKey=true;
			dirKey[0]=true;
			isCollided=false;
		}else if(keyCode==KeyEvent.VK_S)
		{
			//向下前进
			setDir(Dir.DOWN);
			hasKey=true;
			dirKey[1]=true;
			isCollided=false;
		}else if(keyCode==KeyEvent.VK_A)
		{//向左前进
			setDir(Dir.LEFT);
			hasKey=true;
			dirKey[2]=true;
			isCollided=false;
		}
		else if(keyCode==KeyEvent.VK_D)
		{
			//向右前进
			setDir(Dir.RIGHT);
			hasKey=true;
			dirKey[3]=true;
			isCollided=false;
		}
else if(keyCode==KeyEvent.VK_J){
		}
	}
	public void keyRel(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.VK_W)
		{
			dirKey[0]=false;
			if(dirKey[1])setDir(Dir.DOWN);
			if(dirKey[2])setDir(Dir.LEFT);
			if(dirKey[3])setDir(Dir.RIGHT);
		}
		else if(keyCode==KeyEvent.VK_S)
		{
			dirKey[1]=false;
			if(dirKey[0])setDir(Dir.UP);
			if(dirKey[2])setDir(Dir.LEFT);
			if(dirKey[3])setDir(Dir.RIGHT);
		}
		else if(keyCode==KeyEvent.VK_A)
		{
			dirKey[2]=false;
			if(dirKey[0])setDir(Dir.UP);
			if(dirKey[1])setDir(Dir.DOWN);
			if(dirKey[3])setDir(Dir.RIGHT);
		}else if(keyCode==KeyEvent.VK_D)
		{
			dirKey[3]=false;
			if(dirKey[0])setDir(Dir.UP);
			if(dirKey[2])setDir(Dir.LEFT);
			if(dirKey[1])setDir(Dir.DOWN);
		}else if(keyCode==KeyEvent.VK_J){
			Shot shot=new Shot(x, y, direction);
			shots.add(shot);
			new Thread(shot).start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isAlive){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			manager.checkCollidWalls(this);
			for (int i = 0; i < dirKey.length; i++) {
				if(dirKey[i]){
					hasKey=true;
					break;
				}
				else hasKey=false;
			}
			if(hasKey&&!isCollided){
				move();
			}
		}
	}
}
