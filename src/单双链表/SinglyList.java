package 单双链表;

public class SinglyList<T> extends Object
{
public Node<T>head;
public Node<T> p;
public SinglyList()
{
	this.head=new Node<T>();
}
public SinglyList(T[] values)
{
	this();
	Node<T> rear=this.head;
	for(int i=0;i<values.length;i++)
	{
		rear.next=new Node<T>(values[i],null);
		rear=rear.next;
	}
	}

public boolean isEmpty()
{
	return this.head.next==null;
}
public T get(int i)
{
	Node<T>p=this.head.next;
	for(int j=0;p!=null&&j<i;j++)
		p=p.next;
	return (i>=0&&p!=null)?p.data:null;
}
public String toString()
{
	String str=this.getClass().getName()+"(";
	for(Node<T>p=this.head.next;p!=null;p=p.next)
	{
		str+=p.data.toString();
		if(p.next!=null)
			str+=",";
	}
	return str+")";
}
public Node<T>insert(int i,T x)
{
	if(x==null)
		throw new NullPointerException("x==null");  
	Node<T>front=this.head;
	for(int j=0;front.next!=null&&j<i;j++)
		front=front.next;
	front.next=new Node<T>(x,front.next);
	return front.next;
}
public Node<T> insert(T x)
{
	return insert(Integer.MAX_VALUE,x);
}
public T remove(int i)
{
	Node<T> front=this.head;
	for(int j=0;front.next!=null&&j<i;j++)
		front=front.next;
	if(i>=0&&front.next!=null)
	{
		T old=front.next.data;
		front.next=front.next.next;
		return old;
	}
	return null;
}
public void clear()
{
	this.head.next=null;
}
public int size()
{
	p=this.head;
	int i=0;
	while(p!=null)
	{
		i++;
		p=p.next;
	}
	return i;
}
public Node<T> first()
{
	return this.head.next;
}
public boolean equals(Object obj)
{
	if(this==obj)
		return true;
	if(!(obj instanceof SinglyList<?>))
		return false;
	Node<T> p=this.head.next;
	Node<T> q=((SinglyList<T>)obj).head.next;
	while(p!=null&&q!=null&&p.data==q.data)
	{
		p=p.next;
		q=q.next;
	}
	return(p==null&&q==null);
}
public SinglyList(SinglyList<T> list)
{
	
	for(Node<T> p=list.head;p!=null;p=p.next)
	{
		p.next=new Node<T>();
		p.next=this.head.next;
	this.head=this.head.next;
	}

}
public Node<T> next(Node<T> p)
{
	Node<T> test=this.head.next;
	
			test=p;
			return test.next;
}
public void removeAll(T key)
{
	for(int i=0;i<this.size()-1;i++)
		if(this.get(i)==key)
			this.remove(i);
}
public Node<T> search(T key)
{
	Node<T> p=this.head.next;
	while(p!=null)
	{
		if(key==p.data)
			return p;
		p=p.next;
	}
	return null;
}



public boolean containsAll(SinglyList<T> list)
{ 
	boolean p=true;
	boolean q=false;
	Node<T> m=list.head.next;
        for(int j=0;m!=null&&j<list.size()-1;j++)
        {
		for(int i=0;m!=null&&i<this.size()-1;i++)
	    if(m.data.equals(this.get(i))==true)  
			m=m.next;
	    else
	    	return q;
        }

       
return p;
}

public static void main(String[] args)
{

	String values[]= {"A","B","C","D","E","F","G"}; 
	String values1[]= {"A","B","C","D","E","F"};
	SinglyList<String> lista=new SinglyList<String>(values);
	SinglyList<String> list=new SinglyList<String>(values1);
                	   System.out.println(lista);
}
public T remove(T key)             //ɾ���׸���key���Ԫ�ؽ�㣬���ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null��O(n)ɢ�б���
{
    Node<T> front=this.head, p=front.next;
    while (p!=null && !key.equals(p.data))             //˳������״γ��ֵ���key���Ԫ��
    {
        front = p;                                     //frontָ��p��ǰ�����
        p=p.next;
    }
    if (p!=null)                                       //�����ҳɹ���ɾ��front�ĺ�̣�p��㣩
    {
        front.next = p.next;                           //����ͷɾ�����м�/βɾ��
        return p.data;
    }
    return null;
}
public Node<T> insertDifferent(T x)
{
    Node<T> front=this.head, p=front.next;             //front��p��ǰ�����
    while (p!=null && !p.data.equals(x))               //˳�����
    {
        front = p; 
        p = p.next;
    }
    if (p!=null)                                       //���ҳɹ���Ԫ���ظ��������룬����p���
    {
 //       System.out.println("x="+x+"��Ԫ���ظ���δ���롣");
        return p;
    }
    return front.next = new Node<T>(x,null);           //β����ֵΪx��㣬���ز�����
}
}


