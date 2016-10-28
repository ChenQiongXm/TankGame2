package cq.tankgame;

import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Shot implements Runnable,Serializable{
	public int x;
	public int y;
	public int speed=5;
	public Dir direction;
	boolean isAlive=true;
	ImageIcon shotBody;
	public Shot(int x, int y,Dir dir) {
		direction=dir;
		switch (direction) {
		case UP:
			this.x=x+13;
			this.y=y-5;
			break;
		case DOWN:
			this.x=x+10;
			this.y=y+25;
			break;
		case LEFT:
			this.x=x-5;
			this.y=y+15;
			break;
		case RIGHT:
			this.x=x+30;
			this.y=y+15;
			break;
		default:
			break;
		}
		this.shotBody=dir.getShotBody();
	}
	public void draw(Graphics g){
		this.shotBody=direction.getShotBody();
		g.drawImage(shotBody.getImage(),x,y,null);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isAlive){
		try {
				Thread.sleep(50);
			switch (direction) {
			case UP:
				if(y>40)y-=speed;
				else isAlive=false;
				break;
			case DOWN:
				if(y<460)y+=speed;
				else isAlive=false;
				break;
			case LEFT:
				if(x>40)x-=speed;
				else isAlive=false;
				break;
			case RIGHT:
				if(x<700)x+=speed;
				else isAlive=false;
				break;
			default:
				break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
