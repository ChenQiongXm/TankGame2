package mySnake.copy;

import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
	//ArrayList<Node> nodes=null;
	private LinkedList<Node> nodes=new LinkedList<Snake.Node>();
	private int speed=17;
	private int count=20;
	private Direction direction=Direction.UP;
	public Snake() {
		nodes.add(new Node(MyFrame.BLOCK_WIDTH*20, MyFrame.BLOCK_HEIGHT*20,Direction.UP));
	}
	public void changeDir(Direction dir){
		Direction dirF=nodes.getFirst().direction;
		if(((dirF==Direction.LEFT||dirF==Direction.RIGHT)&&(dir==Direction.DOWN||dir==Direction.UP))
				||((dir==Direction.LEFT||dir==Direction.RIGHT)&&(dirF==Direction.DOWN||dirF==Direction.UP)))
		{
			this.direction=dir;
			count=0;
		}
	}
	public void addNode(){
		Node node=nodes.getLast();
		switch (node.direction) {
		case UP:
			nodes.addLast(new Node(node.x, node.y + Node.height,Direction.UP));
			break;
		case DOWN:
			nodes.addLast(new Node(node.x, node.y - Node.height,Direction.UP));
			break;
		case LEFT:
			nodes.addLast(new Node(node.x+Node.width, node.y ,Direction.UP));
			break;
		case RIGHT:
			nodes.addLast(new Node(node.x-Node.width, node.y ,Direction.UP));
			break;
		default:
			break;
		}
	}
	public void draw(Graphics g){
		if (count==0) {
			count=20-speed;
			Node first = nodes.getFirst();
			switch (direction) {
			case UP:
				nodes.addFirst(new Node(first.x, first.y - Node.height,
						Direction.UP));
				break;
			case DOWN:
				nodes.addFirst(new Node(first.x, first.y + Node.height,
						Direction.DOWN));
				break;
			case LEFT:
				nodes.addFirst(new Node(first.x - Node.width, first.y,
						Direction.LEFT));
				break;
			case RIGHT:
				nodes.addFirst(new Node(first.x + Node.width, first.y,
						Direction.RIGHT));
				break;
			default:
				break;
			}
			nodes.removeLast();
		}
		count--;
		for (Node node : nodes) {
			node.draw(g);
		}
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}
	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	class Node{
		int x;
		int y;
		Direction direction;
		private final static int width= MyFrame.BLOCK_WIDTH;
		private final static int height= MyFrame.BLOCK_HEIGHT;
		public Node(int x,int y,Direction direction) {
			// TODO Auto-generated constructor stub
			this.x=x;
			this.y=y;
			this.direction=direction;
		}
		public void draw(Graphics g){
			g.fillRect(x, y, MyFrame.BLOCK_WIDTH, MyFrame.BLOCK_HEIGHT);
		}
	}
}
