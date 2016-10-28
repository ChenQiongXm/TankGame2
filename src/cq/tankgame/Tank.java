package cq.tankgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Tank implements Serializable{
	private static final long serialVersionUID = 7141552924659769210L;
	public int x;
	public int y;
	public int speed=10;
	public  Dir direction;
	public  ImageIcon body;
	public  LinkedList<Shot> shots;
	public  boolean isAlive=true;
	public boolean isCollided=false;
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