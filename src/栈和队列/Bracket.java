package 栈和队列;

public class Bracket {
public static String isMached(String infix)
{
Stack<String>stack=new SeqStack<String>(infix.length());
for(int i=0;i<infix.length();i++)
{
	char ch=infix.charAt(i);
	switch(ch)
	{
	case '(':stack.push(ch+"");
	break;
	case ')':if(stack.isEmpty()||!stack.pop().equals("("))
	return "期望(";
	}
}
return (stack.isEmpty())?"":"期望(";
}
}

