package cq.tankgame;

import javax.swing.ImageIcon;

public enum Dir {
	UP(0,new ImageIcon("tankImages/tankU.gif"),new ImageIcon("tankImages/missileD.gif")),
	DOWN(1,new ImageIcon("tankImages/tankD.gif"),new ImageIcon("tankImages/missileU.gif")),
	LEFT(2,new ImageIcon("tankImages/tankL.gif"),new ImageIcon("tankImages/missileL.gif")),
	RIGHT(3,new ImageIcon("tankImages/tankR.gif"),new ImageIcon("tankImages/missileR.gif"));
	private int direction;
	private ImageIcon body;
	private ImageIcon shotBody;
	Dir(int dir,ImageIcon body,ImageIcon shotBody ){
		this.direction=dir;
		this.body=body;
		this.shotBody=shotBody;
	}
	public int getDirection() {
		return direction;
	}
	public ImageIcon getBody() {
		return body;
	}
	public ImageIcon getShotBody() {
		return shotBody;
	}
}
