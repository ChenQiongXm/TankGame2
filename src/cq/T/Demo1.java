package cq.T;


//相同类，只是数据不同，操作相同，会导致类膨胀的个问题
//考虑用object属性，但取出的类型需要强制转换
//所以考虑使用泛型，jdk1.5引入的特性，
public class Demo1 {
	public static void main(String[] args) {
	}
}
class MyClass1{
	private String data;
	public MyClass1(String data){
		this.data=data;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
class MyClass2{
	private int data;
	public MyClass2(int data){
		this.data=data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
}