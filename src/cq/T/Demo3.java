package cq.T;

public class Demo3 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		GenericClass<Dog> dog=new GenericClass<>();
		dog.setObj(new Dog());
		dog.getObj().eat();

	}

}
//泛型类锁接受的参数做了限制只能接受Animal或者它的子类
class GenericClass<T extends Animal>{
	private T obj;
	public void setObj(T obj){
		this.obj=obj;
	}
	public T getObj(){
		return obj;
	}
	
	
}
abstract class Animal{
	public  abstract void eat();
}
class Dog extends Animal{
	@Override
	public void eat() {
		// TODO Auto-generated method stub
	System.out.println("坑骨头");	
	}
}
class Cat extends Animal{
	@Override
	public void eat() {
		// TODO Auto-generated method stub
	System.out.println("吃小鱼");	
	}
}