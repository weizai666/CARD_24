package 栈和队列;

public class NumberTower {
public static void line(int i)
{
	if(i<10)
		line(i+1);
	System.out.print(i+" ");
}
public static int[] array(int[] a)
{
	System.out.println(a);
	return a;
}
public static void main(String args[])
{
int[] a= {1,2,1};
int[] b=array(a);
int[] c=new int[2];
c[0]=1;
c[1]=4;
int[] d=c;
System.out.println(array(a));
System.out.println(c);
System.out.println(d);
for(int i=0;i<b.length;i++)
	System.out.println(b[i]);
}
}