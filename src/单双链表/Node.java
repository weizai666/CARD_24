package 单双链表;

public class Node<T> 
{
	public Node<T>next;
	public T data;
	public Node(T data,Node<T>next)
	{
		this.data=data;
		this.next=next;
	}
	public Node() {
		this(null,null);
	}
	public String toString()
	{
		return this.data.toString();
	}

}
