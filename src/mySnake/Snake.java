package mySnake;

import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
	//ArrayList<Node> nodes=null;
	Node first;
	Node last;
	private int nodeNumber=2;
	private int speed=1;
	private boolean movable=true;
	public Snake() {
		// TODO Auto-generated constructor stub
		this.first=new Node(MyFrame.BLOCK_WIDTH*20, MyFrame.BLOCK_HEIGHT*20,Direction.UP);
		this.last=new Node(MyFrame.BLOCK_WIDTH*20, MyFrame.BLOCK_HEIGHT*21,Direction.UP);
		first.next=last;
		last.pre=first;
	}
	public void changeDir(Direction direction){		
		Node node=first;
		last=first;
		movable=false;
		switch (direction) {
		case UP:
			first=new Node(node.x, node.y - Node.height,Direction.UP);
			break;
		case DOWN:
			first=new Node(node.x, node.y + Node.height,Direction.DOWN);
			break;
		case LEFT:
			first=new Node(node.x-Node.width, node.y,Direction.LEFT);
			break;
		case RIGHT:
			first=new Node(node.x+Node.width, node.y,Direction.RIGHT);
			break;
		default:
			break;
		}
		first.next=last;
		last.pre=first;
		movable=true;
		node=last;
		while(movable&&node!=null){
			node.move(speed);	
			if(node.pre!=null){
			node.direction=node.pre.direction;
			node=node.pre;
			}else 
				break;
		}

	}
	public void draw(Graphics g){
		Node node=last;
		while(movable&&node!=null){
			node.draw(g);
			node.move(speed);	
			if(node.pre!=null){
			node.direction=node.pre.direction;
			node=node.pre;
			}else 
				break;
		}
	}
	public void move(){

	}
	private class Node{
		int x;
		int y;
		@SuppressWarnings("unused")
		private Node next=null;
		private Node pre=null;
		Direction direction;
		final static int width= MyFrame.BLOCK_WIDTH;
		final static int height= MyFrame.BLOCK_HEIGHT;
		public Node(int x,int y, Direction direction) {
			// TODO Auto-generated constructor stub
			this.x=x;
			this.y=y;
			this.direction=direction;
		}
		public void draw(Graphics g){
			g.fillRect(x, y, MyFrame.BLOCK_WIDTH, MyFrame.BLOCK_HEIGHT);
		}
		public void move(int speed){
			switch (direction) {
			case UP:
				y-=speed;
				break;
			case DOWN:
				y+=speed;
				break;
			case LEFT:
				x-=speed;
				break;
			case RIGHT:
				x+=speed;
				break;
			default:
				break;
			}
		}
		
	}
}
