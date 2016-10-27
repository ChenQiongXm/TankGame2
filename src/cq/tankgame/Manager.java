package cq.tankgame;

import java.util.LinkedList;

public class Manager {
	private Hero hero;
	private LinkedList<IronWall>ironWalls;
	private LinkedList<Enemy> enemies;
	public Manager(Hero hero, LinkedList<Enemy> enemies,LinkedList<IronWall> walls) {
		super();
		this.hero = hero;
		this.enemies = enemies;
		this.ironWalls=walls;
	}
	public void checkEnemyCollid(Tank t){
		checkHitHero(t);
		checkCollidEnemies(t);
		checkCollidWalls(t);
	}
	/**
	 * 判断某一个坦克是否即将碰到墙中的某一个
	 * @param enemy
	 * @return
	 */
	public  boolean checkCollidEnemies(Tank t) {
		boolean flag=false;
		for (Enemy e : enemies) {
			flag=checkCollidOneTank(t, e);
			if(flag==true){
				t.isCollided=true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 判断某一辆坦克时候撞到所有墙中的某一个
	 * @param t
	 */
	public void checkCollidWalls(Tank t){
		boolean flag=false;
		for (Wall wall : ironWalls) {	
			flag=checkCollidOneWood(t, wall);
			if(flag==true){
				t.isCollided=true;
				break;
			}
		}
	}
	/**
	 * 判断两个坦克时候即将碰到
	 * @param Tank t 主要判断的坦克
	 * @param Tank e 参照的坦克
	 * @return
	 */
	private boolean checkCollidOneTank(Tank t,Tank e){
		if (t!=e) {
			switch (t.direction) {
			case UP:
				if ((t.y - t.speed > e.y - 40) && (t.y - t.speed < e.y + 40)
						&& (e.x < t.x + 40) && (e.x > t.x - 40))
					return true;
				break;
			case DOWN:
				if ((t.y + t.speed > e.y - 40) && (t.y + t.speed < e.y + 40)
						&& (e.x < t.x + 40) && (e.x > t.x - 40))
					return true;
				break;
			case LEFT:
				if ((t.x - t.speed < e.x + 40) && (t.x - t.speed > e.x - 40)
						&& (e.y < t.y + 40) && (e.y > t.y - 40))
					return true;
				break;
			case RIGHT:
				if ((t.x + t.speed < e.x + 40) && (t.x + t.speed > e.x - 40)
						&& (e.y < t.y + 40) && (e.y > t.y - 40))
					return true;
				break;
			default:
				break;
			}
		}
		return false;
	}
	/**
	 * 判断某个坦克是否撞到某个墙
	 * @param Tank 要判断的那个坦克
	 * @param Wall	要判断的那个墙	
	 * @return		如果撞到则放回true，如果所有墙都没有撞到则返回false
	 */
	private boolean checkCollidOneWood(Tank t,Wall w){
		switch (t.direction) {
		case UP:
			if((t.y-t.speed>w.y-40)&&(t.y-t.speed<w.y+15)&&(w.x<t.x+40)&&(w.x>t.x-15))
				return true;
			break;
		case DOWN:
			if((t.y+t.speed>w.y-40)&&(t.y+t.speed<w.y+15)&&(w.x<t.x+40)&&(w.x>t.x-15))
				return true;
			break;
		case LEFT:
			if((t.x-t.speed<w.x+15)&&(t.x-t.speed>w.x-40)&&(w.y<t.y+40)&&(w.y>t.y-15))
				return true;
			break;
		case RIGHT:
			if((t.x+t.speed<w.x+15)&&(t.x+t.speed>w.x-40)&&(w.y<t.y+40)&&(w.y>t.y-15))
				return true;
			break;
		default:
			break;
		}
		return false;		
	}
	private boolean checkHitHero(Tank t){
		if(t.x<hero.x+30&&t.x>hero.x-30&&t.y<hero.y+30&&t.y>hero.y-30){
			hero.isAlive=false;
			return true;
		}
		return false;
	}
	public boolean checkBeShot(Tank t,Tank e){
		for (Shot shot : e.shots) {
			if(isShotByShot(t, shot))
			return true;
		}
		return false;
	}
	private boolean isShotByShot(Tank t,Shot shot){
		if(t.x<shot.x&&t.x>shot.x-30&&t.y<shot.y&&t.y>shot.y-30){
			t.isAlive=false;
			return true;
		}
		return false;
	}
}

