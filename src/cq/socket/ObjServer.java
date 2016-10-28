package cq.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjServer {
	private static ServerSocket ss;
	private static ObjectOutputStream oos;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			ss=new ServerSocket(10000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Socket s;
		try {
			s = ss.accept();
			oos=new ObjectOutputStream(s.getOutputStream());
			User one=new User();
			oos.writeObject(one);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			oos.close();
			ss.close();
		}
	}

}
