package mySnake;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class MyFrame extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final static int COW_NUMBER=40;
	protected final static int COL_NUMBER=40;
	protected final static int BLOCK_WIDTH=20;
	protected final static int BLOCK_HEIGHT=20;
	protected final static int WIDTH=(COL_NUMBER+8)*BLOCK_WIDTH;
	protected final static int HEIGHT=(COW_NUMBER+8)*BLOCK_HEIGHT;
	private Snake snake;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		MyFrame myFrame = new MyFrame();
		new Thread(myFrame).start();
		
	}
	public  MyFrame(){
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(300,300);
		this.snake=new Snake();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawRect(BLOCK_WIDTH*4, BLOCK_HEIGHT*4, COL_NUMBER*BLOCK_WIDTH, COW_NUMBER*BLOCK_HEIGHT);
		snake.draw(g);
	}

		public void run() {
			// TODO Auto-generated method stub
			while(true){
			snake.move();
			repaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			System.out.println("key is pressed");
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				snake.changeDir(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				snake.changeDir(Direction.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				snake.changeDir(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				snake.changeDir(Direction.RIGHT);
				break;
			default:
				break;
			}

		}
	}
}
