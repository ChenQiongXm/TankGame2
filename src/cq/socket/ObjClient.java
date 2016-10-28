package cq.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket s=new Socket("127.0.0.1", 10000);
		ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
		try {
			User one=(User) ois.readObject();
			System.out.println(one.getName()+" "+one.getAge());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ois.close();
			s.close();
		}
	}

}
class User implements Serializable{
	private String name="小李";
	private int  age=12;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}