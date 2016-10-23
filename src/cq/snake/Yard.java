package cq.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
public class Yard extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int ROW=40;
	public static final int COL=40;
	public  static final int BLOCK_SIZE=10;
	private Snake snake=new Snake();
	Image image=null;
	public static void main(String[] args) {
		new Yard().lanuch();
	}
	public  void lanuch(){
		this.setLocation(300, 300);
		this.setSize(COL*BLOCK_SIZE,ROW*BLOCK_SIZE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		new Thread(new PaintThread()).start();
		this.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Color c=g.getColor();
		g.setColor(Color.gray);
		g.drawRect(0, 0, COL*BLOCK_SIZE,ROW*BLOCK_SIZE);
		//g.fillRect(0, 0, COL*BLOCK_SIZE,ROW*BLOCK_SIZE);
		for (int i = 0; i < ROW; i++) {
			g.drawLine(0, (BLOCK_SIZE)*i, COL*BLOCK_SIZE, (BLOCK_SIZE)*i);
			g.drawLine((BLOCK_SIZE)*i,0 , (BLOCK_SIZE)*i,ROW*BLOCK_SIZE);
		}
		g.setColor(c);
		snake.draw(g);
	}
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if(image==null){
			image=this.createImage(COL*BLOCK_SIZE,ROW*BLOCK_SIZE);
		}
		Graphics gOff=image.getGraphics();
		paint(gOff);
		g.drawImage(image, 0, 0, null);
	}
	private class PaintThread implements Runnable{
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	private  class KeyMonitor extends KeyAdapter{
		

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			snake.keyPressed(e);
		}
	}
}