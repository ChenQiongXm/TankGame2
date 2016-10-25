package mySnake.copy;

import java.awt.Graphics;
public class Ball {
	int x;
	int y;
	public static final int HEIGHT=MyFrame.BLOCK_HEIGHT;
	public static final int WIDTH=MyFrame.BLOCK_WIDTH;
	public Ball() {
		// TODO Auto-generated constructor stub
		x=(int) (Math.random()*40+4)*10;
		y=(int) (Math.random()*40+4)*10;
	}
	public void draw(Graphics g){
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}