package cq.tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * 坦克大战里的墙。
 * @author chenq
 *
 */
public class Wall implements Serializable{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	Color c;
	boolean isAlive=true;
	final static int width=15; 
	final static int height=15; 
	
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g){
		Color c1=g.getColor();
		g.setColor(c);
		g.fill3DRect(x, y, width, height,true);
		g.setColor(c1);
	}
	public void draw4(Graphics g){
		Color c1=g.getColor();
		g.setColor(c);
		g.fill3DRect(x, y, width, height,true);
		g.fill3DRect(x+width, y, width, height,true);
		g.fill3DRect(x, y+height, width, height,true);
		g.fill3DRect(x+width, y+height, width, height,true);
		g.setColor(c1);
	}
}