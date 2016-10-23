package cq.T;

public class Demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	//·ºÐÍ·½·¨
	public <T> String get( T s){
		return s.toString();
		
	}
}
class MyClass<T>{
	private T data;
	public MyClass() {
		// TODO Auto-generated constructor stub
	}
	public MyClass(T data){
		this.data=data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
class MyClass3<T1,T2>extends MyClass<T1>{
	private T2 var2;
	
	MyClass3(T1 var1,T2 var2){
		super(var1);
		this.setVar2(var2);
	}

	public T2 getVar2() {
		return var2;
	}

	public void setVar2(T2 var2) {
		this.var2 = var2;
	}
	
}