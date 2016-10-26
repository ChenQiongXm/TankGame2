package cq.tankgame;

import java.util.LinkedList;

public class Manager {
	private Hero hero;
	private LinkedList<Enemy> enemies;
	public Manager(Hero hero, LinkedList<Enemy> enemies) {
		super();
		this.hero = hero;
		this.enemies = enemies;
	}
	
	public void checkCollid(Enemy enemy){
		for (Enemy e : enemies) {
			if(enemy!=e&&(Math.abs(enemy.x-e.x)<40&&Math.abs(enemy.y-e.y)<40)){
				if(
					((enemy.direction==Dir.DOWN||enemy.direction==Dir.UP)
					&&(e.direction==Dir.LEFT||e.direction==Dir.LEFT))||
					((enemy.direction==Dir.LEFT||enemy.direction==Dir.RIGHT)
					&&(e.direction==Dir.UP||e.direction==Dir.DOWN))){
					enemy.isCollided=true;
					break;
				}

			}
		}
	}
}
