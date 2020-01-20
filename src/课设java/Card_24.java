package 课设java;

import 栈和队列.LinkedStack;
import 栈和队列.SeqStack;
import 栈和队列.Stack;

import java.io.Serializable;
import java.util.*;


public class Card_24 implements Serializable
{
	private Integer a[]=new Integer[4];
	public  int[][]aa=new int[24][4];     
    public final Integer number[];
	public Card_24()            //构造，产生四个1-13的随机数并计算所有可能的组合
	{
		Random[] radom=new Random[4];
		for(int i=0;i<4;i++)
		{
			radom[i]=new Random();
			this.a[i]=radom[i].nextInt(13);
			while(a[i]==0)
				this.a[i]=radom[i].nextInt(13);
		}
		this.number=a.clone();
		int p=0;
		for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(j==i)//除去j==i的情况，下同
                    continue;

                for(int k=0;k<4;k++){
                    if(k==j||k==i)
                        continue;
                    for(int h=0;h<4;h++){
                        if(h==k||h==j||h==i)
                            continue;
                        	    this.aa[p][0]=a[i];
                        	    this.aa[p][1]=a[j];
                        	    this.aa[p][2]=a[k];
                        	    this.aa[p][3]=a[h];   
                        		p++;
                    }
                }
            }
		}             
	}
	public Card_24(Integer[] a)           //构造，指定4个数
	{
		if(a.length!=4)
		throw new ArithmeticException("error");
		this.a=a;
		this.number=a.clone();
		int p=0;
		for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(j==i)//除去j==i的情况，下同
                    continue;

                for(int k=0;k<4;k++){
                    if(k==j||k==i)
                        continue;
                    for(int h=0;h<4;h++){
                        if(h==k||h==j||h==i)
                            continue;
                        	    this.aa[p][0]=a[i];
                        	    this.aa[p][1]=a[j];
                        	    this.aa[p][2]=a[k];
                        	    this.aa[p][3]=a[h];   
                        		p++;
                    }
                }
            }
		}             
	}
	public Integer get(int i)
	{
		if(i>=0&&i<4)
		return number[i];
		return -1;
	}
	public static StringBuffer toPostfix(String infix)//返回将infix中缀表达式转换成后缀表达式
	{
	Stack<String>stack=new SeqStack<String>(infix.length());
	StringBuffer postfix=new StringBuffer(infix.length()*2);
	int i=0;
	while(i<infix.length())
	{
		char ch=infix.charAt(i);
		switch(ch)
		{
		case '+':case'-':
			while(!stack.isEmpty()&&!stack.peek().equals("("))
				postfix.append(stack.pop());
			stack.push(ch+"");
			i++;break;
		case'*':case'/':
			while(!stack.isEmpty()&&(stack.peek().equals("*")||stack.peek().equals("/")))
				postfix.append(stack.pop());
			stack.push(ch+"");
			i++;break;
		case'(':
			stack.push(ch+"");
			i++;break;
		case')':
			String out=stack.pop();
			while(out!=null&&!out.equals("("))
			{
				postfix.append(out);
				out=stack.pop();
			}
			i++;break;
			default:
				while(i<infix.length()&&ch>='0'&&ch<='9')
				{
					postfix.append(ch);
					i++;
					if(i<infix.length())
						ch=infix.charAt(i);
				}
				postfix.append(" ");
		}
	}
	while (!stack.isEmpty())
		postfix.append(stack.pop());
	return postfix;
	}
	public static int toValue(StringBuffer postfix) throws NullPointerException  //计算后缀表达式的值
	{
		Stack<Integer>stack=new LinkedStack<Integer>();
		int value=0;
		for(int i=0;i<postfix.length();i++)
		{
			char ch=postfix.charAt(i);
			if(ch>='0'&&ch<='9')
			{
				value=0;
				while(ch!=' ')
				{
					value=value*10+ch-'0';
					ch=postfix.charAt(++i);
				}
				stack.push(value);
			}
			else 
				if(ch!=' ')
				{
					
					int y=stack.pop(),x=stack.pop();
					switch(ch)
					{
					case'+':value=x+y;break;
					case'-':value=x-y;break;
					case'*':value=x*y;break;
					case'/':{
						if(y==0)
						throw new ArithmeticException("除数不能为0");
					value=x/y;
					}
						break;
					}
					stack.push(value);
				} 
		}
		return stack.pop();
	}
    public static void main(String args[])
    {
    	int[] result=new int[13824];
    	int p=0;
    	String[][] expression=new String[64][9];
    	Card_24 A=new Card_24();
    	Expression_24[] AA24=new Expression_24[24];
    	for(int i=0;i<24;i++)
    			AA24[i]=new Expression_24(A.aa[i][0],A.aa[i][1],A.aa[i][2],A.aa[i][3]);
    	for(int i=0;i<24;i++)
    	        expression=AA24[i].infix();
    	        
				for(int m=0;m<expression.length;m++)
    	        	for(int n=0;n<expression[m].length;n++)
    	        	{
    	        		result[p]=toValue(toPostfix(expression[m][n]));
				if(result[p]==24)
					System.out.println(expression[m][n]);
				p++;
    	        	}
    		}
 
}

