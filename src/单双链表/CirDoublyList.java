package 单双链表;

public class CirDoublyList<T> {
public DoubleNode<T>head;
public CirDoublyList()
{
	this.head=new DoubleNode<T>();
	this.head.prev=this.head;
	this.head.next=this.head;
}
public boolean isEmpty()
{
	return this.head.next==this.head;
}
public DoubleNode<T> insert(int i,T x)
{
	if(x==null)
		throw new NullPointerException("x==null");
	DoubleNode<T>front=this.head;
	for(int j=0;front.next!=this.head&&j<i;j++)
		front=front.next;
	DoubleNode<T>q=new DoubleNode<T>(x,front,front.next);
	front.next.prev=q;
	front.next=q;
	return q;
}
public DoubleNode<T>insert(T x)
{
	if(x==null)
		throw new NullPointerException("x==null");
	DoubleNode<T>q=new DoubleNode<T>(x,head.prev,head);
	head.prev.next=q;
	head.prev=q;
	return q;
}
}
