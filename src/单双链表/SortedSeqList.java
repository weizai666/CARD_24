package 单双链表;

public class SortedSeqList<T extends Comparable<? super T>> extends SeqList<T> {
public SortedSeqList()
{
	super();
}
public SortedSeqList(int length)
{
	super(length);
}
public SortedSeqList(T[] values)
{
	super(values.length);
	for(int i=0;i<values.length;i++)
		this.insert(values[i]);
}
public int insert(T x)
{
	int i=0;
	if(this.isEmpty()||x.compareTo(this.get(this.size()-1))>0)
		i=this.n;
	else
		while(i<this.n&&x.compareTo(this.get(i))>0)
			i++;
	super.insert(i,x);
	return i;
}
public void set(int i,T x)
{
	throw new UnsupportedOperationException("set(int i,T x)");
}
public int insert(int i,T x)
{
	throw new UnsupportedOperationException("insert(int i,T x)");
}
public int search(T key)
{
	for(int i=0;i<this.n&&key.compareTo(this.get(i))==0;i++)
		if(key.compareTo(this.get(i))==0)
			return i;
	return -1;
}
public  void bubbleSort(Object[] keys, boolean asc) //ð������ascȡֵtrue�����򣩡�false������
{
    System.out.println("ð������"+(asc?"��":"��")+"��");
    boolean exchange=true;                             //�Ƿ񽻻��ı��
    for (int i=1; i<keys.length && exchange; i++)      //�н���ʱ�ٽ�����һ�ˣ����n-1��
    {
        exchange=false;                                //�ٶ�Ԫ��δ���� 
        for (int j=0; j<keys.length-i; j++)            //һ�˱Ƚϡ�����
            if (asc ?  ((T)keys[j]).compareTo((T) keys[j+1])>0 : ((T) keys[j]).compareTo((T) keys[j+1])<0)//����Ԫ�رȽϣ��������򽻻�
            {
                swap(keys, j, j+1);
                exchange=true;                         //�н��� 
            }
        System.out.print("��"+i+"�ˣ��±�0��"+(keys.length-i)+"��");
        Array1.print(keys);
    }
}
private void swap(Object[] keys, int j, int i) {
Object temp;
temp=keys[j];
keys[j]=keys[i];
keys[i]=temp;
}
public SortedSeqList(SeqList<?extends T>list)   //��˳���������˳�������һ�������㷨
{
	super(list);
	Object[] temp=new Object[n];
	for(int i=0;i<this.n;i++)
	temp[i]=(T)(element[i]);
	bubbleSort(temp,true);
}
public static void main(String args[])
{
	String[] array1={"a","b","d","f","e","c","g"};
	Integer array[]= {4,7,1,6,3,5,2};
	SeqList<Integer> list=new SeqList<Integer>(array);
	SeqList<String> list1=new SeqList<String>(array1);
	SortedSeqList<Integer> test=new SortedSeqList<Integer>(list);
	SortedSeqList<String> test1=new SortedSeqList<String>(list1);
	test.toString();
	test1.toString();
}
}
