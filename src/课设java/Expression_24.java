package 课设java;

import java.io.Serializable;
import java.util.Arrays;

public class Expression_24 implements Serializable
{
	private StringBuffer[] temparray=new StringBuffer[11];
	private Integer a,b,c,d;
	StringBuffer[] Bracket;
	private String A,B,C,D;
	
	public Expression_24(int a,int b,int c,int d)
	{
	    this.a=a;
	    this.b=b;
	    this.c=c;
	    this.d=d;
		this.A=this.a.toString();
		this.B=this.b.toString();
		this.C=this.c.toString();
		this.D=this.d.toString();
	    this.temparray[0]=new StringBuffer("(a+b)+c+d");
		this.temparray[1]=new StringBuffer("(a+b+c)+d");
		this.temparray[2]=new StringBuffer("a+(b+c+d)");
		this.temparray[3]=new StringBuffer("(a+b)+(c+d)");
		this.temparray[4]=new StringBuffer("a+(b+c)+d");
		this.temparray[5]=new StringBuffer("((a+b)+c)+d");
		this.temparray[6]=new StringBuffer("a+b+(c+d)");
		this.temparray[7]=new StringBuffer("a+(b+(c+d))");
		this.temparray[8]=new StringBuffer("a+((b+c)+d)");
		this.temparray[9]=new StringBuffer("(a+(b+c))+d)");
		this.temparray[10]=new StringBuffer("a+b+c+d");
		
	}
	public StringBuffer getChar(char ch,int i,StringBuffer string)    //将第i个运算符改为ch，返回修改后的算术表达式
	{
		int k=1;
		for(int j=0;i<string.length();j++)
			if(string.charAt(j)=='+'||string.charAt(j)=='-'||string.charAt(j)=='*'||string.charAt(j)=='/')
				if(k==i)
				{
					StringBuffer a=new StringBuffer(string);
					a.setCharAt(j, ch);
					
				return a;
				}
				else
					k++;
		return null;
	}
	
	public static char getchar(StringBuffer bf,int i)
	{
		return bf.charAt(i);
	}
	public void replace0(StringBuffer buffer)
	{
		for(int i=0;i<buffer.length();i++)
		{
		if(getchar(buffer,i)=='a')
			buffer.replace(i, i+1,this.A);
		if(getchar(buffer,i)=='b')
			buffer.replace(i, i+1,this.B);
		if(getchar(buffer,i)=='c')
			buffer.replace(i, i+1,this.C);
		if(getchar(buffer,i)=='d')
			buffer.replace(i, i+1,this.D);
		}
	}
	public String[][] infix()                        //StringBuffer转String
	{
        StringBuffer[][] infix=new StringBuffer[64][];
		String[][] toinfix=new String[64][11];
		StringBuffer[][] array=this.NumberToExpression().clone();
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
				replace0(array[i][j]);
				infix[i]=Arrays.copyOf(array[i], array[i].length); 
			}
		for(int i=0;i<infix.length;i++)
			for(int j=0;j<infix[i].length;j++)
				toinfix[i][j]=infix[i][j].toString();
		return toinfix;
	}
	public  StringBuffer[][] NumberToExpression()           //返回算术表达式所有可能的组合
	{
		
		StringBuffer[][] Ex=new StringBuffer[64][];
		Bracket=temparray.clone();
		int major=0;
		for(int n=0;n<4;n++)    //第3个运算符的变化情况
		{
			switch(n)
			{
			case 0: 
			{
				for(int i=0;i<temparray.length;i++)
					temparray[i]=getChar('+',3,temparray[i]);Bracket=temparray.clone();
				for(int m=0;m<4;m++)                      //第2个运算符的变化情况
				{
					switch(m)                
					{
					case 0:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('+',2,temparray[i]);Bracket=temparray.clone(); 
						
						for(int j=0;j<4;j++)   //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);	
									
								}
								Ex[major]=Bracket.clone();
								major++;Bracket=temparray.clone();
							}
							break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
					
					}break;
					case 1:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('-',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)   //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 2:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('*',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)   //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 3:	
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('/',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
					
					}break;
					}
				}
				
			}break;
			case 1:
			{
				for(int i=0;i<temparray.length;i++)
					temparray[i]=getChar('-',3,temparray[i]);Bracket=temparray.clone();
				for(int m=0;m<4;m++)                         //第2个运算符的变化情况
				{
					switch(m)                
					{
					case 0:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('+',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 1:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('-',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 2:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('*',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 3:	
					{

						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('/',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					}
				}
	
			}break;
			case 2:
			{
				for(int i=0;i<temparray.length;i++)
					temparray[i]=getChar('*',3,temparray[i]);Bracket=temparray.clone();
				for(int m=0;m<4;m++)                        //第2个运算符的变化情况
				{
					switch(m)                
					{
					case 0:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('+',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 1:
					{

						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('-',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
							
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 2:
					{

						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('*',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
							
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 3:	
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('/',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					}
				}
	
			}break;
			case 3:	
			{
				for(int i=0;i<temparray.length;i++)
					temparray[i]=getChar('/',3,temparray[i]);Bracket=temparray.clone();
				for(int m=0;m<4;m++)                       //��2��������ı仯���
				{
					switch(m)                
					{
					case 0:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('+',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)   //��1��������ı仯���
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 1:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('-',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
							
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 2:
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('*',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)    //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}
						
					}break;
					case 3:	
					{
						for(int i=0;i<temparray.length;i++)
							temparray[i]=getChar('/',2,temparray[i]);Bracket=temparray.clone();
						for(int j=0;j<4;j++)     //第1个运算符的变化情况
						{
							switch(j)
							{
							case 0:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('+',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
							
							}break;
							case 1:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('-',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 2:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('*',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							case 3:
							{
								for(int i=0;i<temparray.length;i++)
								{
									Bracket[i]=getChar('/',1,Bracket[i]);
									
									
								}
								Ex[major]=Arrays.copyOf(Bracket, Bracket.length);major++;Bracket=temparray.clone();
								
							}break;
							}
						}	
					}break;
					}
				}
				
			}break;
			}
		}
		return Ex;		
	}
	public static void main(String args[])
	{
		Expression_24 Aa=new Expression_24(1,8,3,4);
		String[][] aa=Aa.infix();
	    for(int i=0;i<aa.length;i++)
	    	for(int j=0;j<aa[i].length;j++)
		System.out.println(aa[i][j]);
	}
}
