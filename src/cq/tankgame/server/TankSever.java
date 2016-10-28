package cq.tankgame.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import cq.tankgame.*;
import cq.tankgame.client.Hero;

public class TankSever implements Runnable{
	private  ServerSocket ss;
	private LinkedList<Enemy>enemies;
	private Iterator<Enemy>enemyIterator;
	private Manager manager;
	private LinkedList<IronWall> ironWalls;
	private Iterator<IronWall> iroIterator;
	private LinkedList<WoodWall> woodWalls;
	private Iterator<WoodWall>woodIterator;
	private Hero hero1,hero2;
	ClentThread clients[]=new ClentThread[2];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankSever server=new TankSever();
		new Thread(server).start();
	}
		public TankSever(){
		hero1=new Hero(100, 100, Dir.UP);
		hero2=new Hero(300, 100, Dir.UP);
		enemies=new LinkedList<Enemy>();
		ironWalls=new LinkedList<IronWall>();
		manager=new Manager(hero1, enemies,ironWalls);
		hero1.setManager(manager);
		for (int i = 0; i < 5; i++) {
			Enemy enemy = new Enemy(50+i*70,400,manager);
			new Thread(enemy).start();
			enemies.add(enemy);
		}
		try {
			ss=new ServerSocket(10002);
			System.out.println("建立服务器");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("端口被占用");
		}
			try {
				Socket s1 = ss.accept();
				Socket s2 = ss.accept();
				clients[0]=new ClentThread(s1,hero1);
				clients[1]=new ClentThread(s2,hero2);
				new Thread(clients[0]).start();
				new Thread(clients[1]).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	//socketServer里的多线程socket
	class ClentThread implements Runnable{
		private Socket s;
		private  ObjectInputStream ois;
		private  ObjectOutputStream oos;
		private Boolean bConnected=false;
		private int port;
		private Hero hero;
		public ClentThread(Socket s,Hero hero) {
			// TODO Auto-generated constructor stub
			this.s=s;
			this.hero=hero;
			System.out.println("Client: "+port+" 已连接");//调试代码
			try {
				ois=new ObjectInputStream(s.getInputStream());
				oos=new ObjectOutputStream(s.getOutputStream());
				bConnected=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public Hero getHero() {
			return hero;
		}
		public void send(Tank hero){
			try {
				oos.writeObject(hero);
				oos.writeObject(enemies);
				oos.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			try {
			while(bConnected){
					hero=(Hero) ois.readObject();
					for (ClentThread clentThread : clients) {
						if(clentThread!=this)clentThread.send(hero);
					}
					//ois.reset();
				}
			}catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("没有这个类，请修改代码");
			} catch (IOException e) {
					// TODO Auto-generated catch block
				System.out.println("断开连接");
			}finally{
				try {
					if(ois!=null)ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(s!=null)s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void run() {
		while (true) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			enemyIterator=enemies.iterator();
			hero1=clients[0].getHero();
			hero2=clients[1].getHero();
			while (enemyIterator.hasNext()) {
				Enemy enemy = enemyIterator.next();
				manager.checkBeShot(enemy, hero1);
				manager.checkBeShot(enemy, hero2);
				if (!enemy.isAlive){
					enemyIterator.remove();
					System.out.println("被击中");
				}
			}
		}
	}
}
