package 单双链表;



public class SortedCirDoublyList<T extends Comparable<? super T>> extends CirDoublyList<T> 
{
public DoubleNode<T>insert(T x)
{
	if(this.isEmpty()||x.compareTo(this.head.prev.data)>0)
		return super.insert(x);
	DoubleNode<T>p=this.head.next;
	while(p!=head&&x.compareTo(p.data)>0)
		p=p.next;
	DoubleNode<T>q=new DoubleNode<T>(x,p.prev,p);
	p.prev.next=q;
	p.prev=q;
	return q;
}
}
