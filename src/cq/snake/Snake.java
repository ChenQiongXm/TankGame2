package cq.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	Node head=null;
	Node tail=null;
	int size=1;
	public Snake(Node node){
		head=node;
		tail=node;
		size=1;
	}
	public Snake(){
		tail=head=new Node(20,30,Dir.L);
		size=1;
	}
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			head.dir=Dir.L;
			break;
		case KeyEvent.VK_RIGHT:
			head.dir=Dir.R;
			break;
		case KeyEvent.VK_UP:
			head.dir=Dir.U;
			break;
		case KeyEvent.VK_DOWN:
			head.dir=Dir.D;
			break;
		default:
			break;
		}
	}
	public void addToTail(){
		Node node=null;
		switch(tail.dir){
		case L:
			node=new Node(tail.row, tail.col+1, tail.dir);
			break;
		case U:
			node=new Node(tail.row+1, tail.col, tail.dir);
			break;
		case R:
			node=new Node(tail.row, tail.col-1, tail.dir);
			break;
		case D:
			node=new Node(tail.row-1, tail.col, tail.dir);
			break;
		default : 
			break;
		}
		tail.next=node;
		tail=node;
		size++;
	}
	public void addToHead(){
		Node node=null;
		switch(head.dir){
		case L:
			node=new Node(head.row, head.col-1, head.dir);
			break;
		case U:
			node=new Node(head.row-1, head.col, head.dir);
			break;
		case R:
			node=new Node(head.row, head.col+1, head.dir);
			break;
		case D:
			node=new Node(head.row+1, head.col, head.dir);
			break;
		default : 
			break;
		}
		node.next=head;
		head=node;
		size++;
	}
	public void draw(Graphics g){
		while(head!=null){
			head.draw(g);
			head=head.next;
			move();
		}
	}
	private void move() {
		// TODO Auto-generated method stub
		addToHead();
		deleteFromTail();
		
	}
	private void deleteFromTail() {
		// TODO Auto-generated method stub
		if(size==0)return;
		
	}
	private class Node{
		int width=Yard.BLOCK_SIZE;
		int height=Yard.BLOCK_SIZE;
		int row,col;
		Dir dir=Dir.L;
		Node next;
		public Node(int row,int col,Dir dir) {
			// TODO Auto-generated constructor stub
			this.row=row;
			this.col=col;
			this.dir=dir;
		}
		void draw(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.black);
			g.fillRect(row*width, height*col, width, height);
			g.setColor(c);
		}
	}
		
}
