package cq.tankgame;

import javax.swing.ImageIcon;

public enum Dir {
	UP(0,new ImageIcon("tankImages/tankU.gif")),DOWN(1,new ImageIcon("tankImages/tankD.gif")),
		LEFT(2,new ImageIcon("tankImages/tankL.gif")),RIGHT(3,new ImageIcon("tankImages/tankR.gif"));
	private int direction;
	private ImageIcon body;
	Dir(int dir,ImageIcon body){
		this.direction=dir;
		this.body=body;
	}
	public int getDirection() {
		return direction;
	}
	public ImageIcon getBody() {
		return body;
	}
	
}
