package regex;
/*1.匹配
 * 2.切割
*/
public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		split("zhangshan  .  lisi .   wangwu","[ \\.]+");
	}
	public static void checkQQ(String qq){
		String  regex="[1-9][0-9]{4,14}";
		boolean flag=qq.matches(regex);
		System.out.println("flag :"+flag);
	}
	public static void split(String str,String reg){
		String []arr=str.split(reg);
		for (String s : arr) {
			System.out.println(s);
		}
	}
}
