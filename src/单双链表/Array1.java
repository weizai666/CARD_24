package 单双链表;

public class Array1 
{
	public static void print(Object[] value)
	{
		for(Object obj:value)
			System.out.print(obj==null?"null":" "+obj.toString());
		System.out.println();
	}
	public static Integer[] randomInteger(int n,int size)
	{
		Integer[] values=new Integer[n];
		for(int i=0;i<values.length;i++)
			values[i]= (int) (Math.random() * size);
		return values;
	}
}
