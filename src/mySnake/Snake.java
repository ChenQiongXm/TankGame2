package mySnake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
	//ArrayList<Node> nodes=null;
	Node node;
	public Snake() {
		// TODO Auto-generated constructor stub
		this.node=new Node(MyFrame.BLOCK_WIDTH*20, MyFrame.BLOCK_HEIGHT*20);
	}
	public void draw(Graphics g){
		node.draw(g);
	}
	public void move(Direction direction){
		node.move(direction);
	}
	private class Node{
		int x;
		int y;
		private final int width= MyFrame.BLOCK_WIDTH;
		private final int height= MyFrame.BLOCK_HEIGHT;
		Direction direction;
		public Node(int x,int y) {
			// TODO Auto-generated constructor stub
			this.x=x;
			this.y=y;
		}
		public void draw(Graphics g){
			g.fillRect(x, y, MyFrame.BLOCK_WIDTH, MyFrame.BLOCK_HEIGHT);
			System.out.println("重新画");
		}
		public void move(Direction direction){
			switch (direction) {
			case UP:
				y-=height;
				System.out.println("按了上");
				break;
			case DOWN:
				y+=height;
				break;
			case LEFT:
				x-=width;
				break;
			case RIGHT:
				x+=width;
				break;
			default:
				break;
			}
		}
	}
}
