package cq.tankgame;

import java.awt.Color;

public class IronWall extends Wall{
	private static final long serialVersionUID = 1L;

	public IronWall(int x, int y) {
		super(x, y);
		this.c=Color.white;
	}
}