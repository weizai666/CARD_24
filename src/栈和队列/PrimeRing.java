package 栈和队列;

import 单双链表.SeqList;
import 单双链表.SortedSeqList;

//��1-max������
public class PrimeRing {
public PrimeRing(int max)
{
	SortedSeqList<Integer> primeset=createPrime(max);
	System.out.println("素数集合:"+primeset.toString());
	SeqList<Integer> ring=new SeqList<Integer>(max);
	ring.insert(1);
	Queue<Integer>que=new SeqQueue<Integer>(max);
	for(int i=2;i<=max;i++)
		que.add(i);
	System.out.println("队列:"+que.toString());
	int i=0;
	while(!que.isEmpty())
	{
		int key=que.poll();
		if(primeset.contains(ring.get(i)+key))
		{
			i++;
			ring.insert(key);
		}
		else
			que.add(key);
	}
	System.out.println("1~"+max+"素数环:"+ring.toString());
}
public SortedSeqList<Integer>createPrime(int max)
{
	if(max<=0)
		return null;
	SortedSeqList<Integer>primeset=new SortedSeqList<Integer>(max*2);
	primeset.insert(2);
	for(int key=3;key<max*2;key+=2)
	{
		int i=0;
		while(i<primeset.size()&&key%primeset.get(i)!=0)
			i++;
		if(i==primeset.size())
			primeset.insert(key);
	}
	return primeset;
}
public static void main(String args[])
{
	new PrimeRing(10);
}
}
