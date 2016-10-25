package mySnake.copy;

import java.util.LinkedList;

import mySnake.copy.Snake.Node;


public class Handle {
	private Snake snake;
	private LinkedList<Ball> balls;
	public Handle(Snake snake, LinkedList<Ball> balls) {
		super();
		this.snake = snake;
		this.balls = balls;
	}
	public void deal(){
		LinkedList<Node> nodes=snake.getNodes();
		Node first=nodes.getFirst();
		for (Ball ball : balls) {
		if((Math.abs(ball.x-first.x)<MyFrame.BLOCK_WIDTH)&&
				(Math.abs(ball.y-first.y)<MyFrame.BLOCK_WIDTH)){
			balls.remove(ball);
			balls.add(new Ball());
			snake.addNode();
		}
	  }
	  for (Node node_i : nodes) {
		  if(node_i.x<=40||node_i.x>=440||node_i.y<=40||node_i.y>=440){
				MyFrame.isDead=true;
				break;
		  }
		if(node_i!=first){
		if((Math.abs(first.x-node_i.x)<MyFrame.BLOCK_WIDTH)&&
			(Math.abs(first.y-node_i.y)<MyFrame.BLOCK_WIDTH)){

		}
		}
	}
	
	}
}
