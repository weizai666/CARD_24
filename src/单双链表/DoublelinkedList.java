package 单双链表;

public class DoublelinkedList<T> {
	public DoubleNode<T> head;
	public DoublelinkedList()
	{
		this.head=new DoubleNode<T>();
	}
	public DoublelinkedList(T[] values)
	{
		this();
		DoubleNode<T>rear=this.head;
		for(int i=0;i<values.length;i++)
		{
			rear.next=new DoubleNode<T>(values[i],null,null);
			rear=rear.next;
		}
	}
	public String toString()
	{
		String str=this.getClass().getName()+"(";
		for(DoubleNode<T>p=this.head.next;p!=null;p=p.next)
		{
			str+=p.data.toString();
			if(p.next!=null)
				str+=",";
		}
		return str+")";
	}
	public static void main(String args[])
	{
		Integer[] values= {1,2,10,31,56,568};
		DoublelinkedList<Integer> p=new DoublelinkedList<Integer>(values);
DoubleNode<Integer>m=p.head.next.next;

			System.out.println(m.prev);
		
	}
}
