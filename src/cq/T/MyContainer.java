package cq.T;

import java.util.Arrays;

public class MyContainer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
interface IContainer<T>{
	public void add(T obj);//给容器添加元素
	public T get(int index);//获取指定索引位置的元素
	public int size();
	public T remove();
}
class ArrayList<T> implements IContainer<T>{
	//private T[] data=new T[10];//错误，不能创建泛型数组
	private Object[]data=null;
	private int size=0;
	public ArrayList() {
		data=new Object[10];//初始化大小为10
	}
	public ArrayList(int capacity){
		data=new Object[10];
	}
	@Override
	public void add(T obj) {
		// TODO Auto-generated method stub
		//判断元素的个数是否已经超过了容器的大小，超过了应该对容器进行扩容
		ensureCapacity(size+1);
		data[size++]=obj;//添加元素
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return (T) data[index];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}
	private void ensureCapacity(int capacity){
		if(capacity>data.length){
			int oldCapacity=data.length;//获取原有数据大侠
			int newCapacity=oldCapacity+(oldCapacity>>1);
			data=Arrays.copyOf(data, newCapacity);
		}
	}
}