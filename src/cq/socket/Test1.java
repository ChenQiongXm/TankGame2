package cq.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
//ipµÿ÷∑:InetAddress
public class Test1 {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		InetAddress i = InetAddress.getLocalHost();
		System.out.println(i.toString());
		System.out.println(i.getHostAddress()+" "+i.getHostName());
		
		InetAddress ia=InetAddress.getByName("120.198.201.155");
		System.out.println(ia.getHostAddress()+" "+ia.getHostName());
	}

}
