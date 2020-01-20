package 单双链表;

public class SeqList<T> extends Object
{
	protected Object[] element;
	 protected int n;
	public SeqList(int length)
	{
	 this.element=new Object[length];

	this.n=0;
	}
	public  SeqList()
	{this(64);
	}
	public SeqList(T[] values)
	{
	 this(values.length);

	for(int i=0;i<values.length;i++)
	  this.element[i]=values[i];
	this.n=element.length;
	}
	public Boolean isEmpty()
	{return this.n==0;
	}
	public int size()
	{return this.n;
	}
	@SuppressWarnings("unchecked")
	public T get(int i)
	{
	 if(i>=0&&i<this.n)
	 return (T)this.element[i];
	return null;
	}
	public void set(int i,T x)
	{if(x==null)
	 throw new NullPointerException("x==null");
	if(i>=0&&i<this.n)
	 this.element[i]=x;
	else throw new IndexOutOfBoundsException(i+"");
	}
	public String toString()
	{
	 String str=this.getClass().getName()+"(";
	if(this.n>0)
	 str+=this.element[0].toString();
	for(int i=1;i<this.n;i++)
	str+=","+this.element[i].toString();
	return str+")";
	}
	public int insert(int i,T x)
	{
		if(x==null)
			throw new NullPointerException("x==null");
		if(i<0)
			i=0;
		if(i>this.n)
			i=this.n;
		Object[] source=this.element;
		if(this.n==this.element.length)
		{
			this.element=new Object[source.length*2];
			for(int j=0;j<i;j++)
				this.element[j]=source[j];
		}
		for(int j=this.n-1;j>=i;j--)
			this.element[j+1]=source[j];
		this.element[i]=x;
		this.n++;
		return i;
	}
	public int insert(T x)
	{
		return this.insert(this.n,x);
	}
	public T remove(int i)
	{
		if(this.n>0&&i>=0&&i<this.n)
		{
			T old=(T)this.element[i];
			for(int j=i;j<this.n-1;j++)
				this.element[j]=this.element[j+1];
			this.element[this.n-1]=null;
			this.n--;
			return old;
		}
		return null;
	}
	public void clear()
	{
		this.n=0;
	}
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if(obj instanceof SeqList<?>)
		{
			SeqList<T>list=(SeqList<T>)obj;
			if(this.n==list.n)
			{
				for(int i=0;i<this.n;i++)
					if(!(this.get(i).equals(list.get(i))))
						return false;
				return true;
			}
		}
		return false;
	}
	public int search(T key)
	{
		for(int i=0;i<this.n;i++)
			if(key.equals(this.element[i]))
				return i;
		return -1;
	}
	public boolean contains(T key)
	{
		return this.search(key)!=-1;
	}
	public void removeAll(T key)
	{
		
	for(int i=0;i<this.n;i++)
		{while(this.element[i]==key)
			this.remove(i);
		}
	}
	public T remove(T key)
	{
		return this.remove(this.search(key));
	}
	public void repalceAll(T key,T x)
	{
		for(int i=0;i<this.n;i++)
		{
			if(this.element[i]==key)
				this.element[i]=x;
		}
	}
	public void removeLast(T key)
	{
		for(int i=this.n-1;i>=0;i--)
		{
			if(this.element[i]==key)
			{
				this.remove(i);
				break;
			}
		}
		
	}
	public SeqList(SeqList<?extends T> list)
	{
		this.n=list.n;
		this.element=new Object[list.element.length];
		for(int i=0;i<list.n;i++)
			this.element[i]=list.element[i];
	}
	public static void main(String args[])
	{
		String values[]={"A","A","A","v","E"};
		SeqList<String> lista=new SeqList<String>(values);
		lista.removeLast("A");
		for(int i=0;i<lista.size();i++)
			System.out.println(lista.get(i));
		}
	
}
