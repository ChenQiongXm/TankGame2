package cq.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Test2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//1.创建udp服务通过DataGram socket
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket ds=new DatagramSocket();
		byte[]buf=new byte[1024];
		String str=null;
		while(true){
			str=br.readLine();
			buf=str.getBytes();
			DatagramPacket dp=
			new DatagramPacket(buf, buf.length,InetAddress.getByName("192.168.1.255"),8000);
			ds.send(dp);
			if((str).equals("over")){
				break;
			}
		}
		//2.确定数据 并封装成数据包。
		//ystem.out.println(ds.getLocalPort());
		ds.close();
		
	}

}
