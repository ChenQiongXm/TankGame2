package cq.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPTest2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DatagramSocket ds=new DatagramSocket(8000);
		byte[]buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf, buf.length);
		while(true){
			
			ds.receive(dp);
			String ip=dp.getAddress().getHostAddress();
			System.out.println(ip+"" +dp.getPort());
			String data=new String(dp.getData(), 0, dp.getLength());
			System.out.println(data.toString());
			if(data.equals("over"))break;
		}
		//通过数据包的方法获取其中的数据
		ds.close();
		//
	}

}
