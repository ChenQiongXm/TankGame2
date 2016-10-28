package cq.tankgame.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import cq.tankgame.server.Enemy;

public class TankClient implements Runnable{
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket s=null;
	private int port=10002;
	private Hero hero1;
	private Hero hero2;
	private LinkedList<Enemy>enemies;
	public TankClient(Hero hero,Hero hero2,LinkedList<Enemy> enemies ) {
		// TODO Auto-generated constructor stub
		try {
			s=new Socket("127.0.0.1",port);
			oos=new ObjectOutputStream(s.getOutputStream());
			ois=new ObjectInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			System.out.println("找不到服务器");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("网络错误");
		}
		this.hero1=hero;
		this.hero2=hero2;
		this.enemies=enemies;
	}
	public Hero getHero2() {
		return hero2;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
				//oos.writeObject(new Hero(hero.x, hero.y, hero.direction));
				oos.writeObject(hero1);
				hero2=(Hero) ois.readObject();
				enemies=(LinkedList<Enemy>) ois.readObject();
				oos.reset();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("连接失败");
				System.out.println(s.isClosed());
				try {
					ois.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ois=new ObjectInputStream(s.getInputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		}
	}
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}
}
