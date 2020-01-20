package 课设java;
import java.io.*;
public class Card_24File_char 
{
	private int n;
	private Card_24 card;
	private final Integer[][] data;
	private final String[]  temp=new String[2000];
	private int k=0;
	public Card_24File_char(int n,String filename)   //获得n组数据
	{
		this.n=n;
		data=new Integer[this.n][4];
		try
		{
			FileWriter fw=new FileWriter(filename);
			for(int i=1;i<this.n+1;i++)
			{
				fw.write("��"+i+"��:"+"\t");
				this.card=new Card_24();
				for(int j=0;j<4;j++)
				{		
					data[i-1][j]= Integer.valueOf(card.get(j));
					fw.write(card.get(j)+"\t");			
				}
				fw.write("\r\n");
			}
			fw.close();
		}
		catch(IOException ex) {}
		try
		{
			FileReader fr=new FileReader(filename);
			BufferedReader br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null)
			{
				try
				{
					System.out.println(line);
				}
				catch(Exception ex) {}
			}
			br.close();
			fr.close();
		}
		catch(IOException ex) {}
	}
	public void is_24(String filename2)
	{
	
		for(int i=0;i<this.n;i++)
		{
			Card_24 caculate=new Card_24(data[i]);
			String[][] expression=new String[64][11];
	    	Expression_24[] AA24=new Expression_24[24];
	    	for(int m=0;m<24;m++)
	    			AA24[m]=new Expression_24(caculate.aa[m][0],caculate.aa[m][1],caculate.aa[m][2],caculate.aa[m][3]);
	    	for(int n=0;n<24;n++)
	    	{
	    	        expression=AA24[n].infix();
					for(int m=0;m<expression.length;m++)
	    	        	for(int q=0;q<expression[m].length;q++)
	    	        	{
	    	        		try
	    	        		{
					if(Card_24.toValue(Card_24.toPostfix(expression[m][q]))==24)
					{
						temp[k]=new String(expression[m][q]);
                        k++;
						System.out.println(expression[m][q]);
					}
	    	        	}
	    	        		catch(ArithmeticException nfex) {}
	    	}

		}
		}
		
		try
		{
	    	FileWriter fs=new FileWriter(filename2);
		for(int i=0;i<k;i++)
			fs.write(temp[i]+"\r\n");
		fs.close();
	}
        catch(Exception ex) {}
	}

	public static void main(String args[])
	{
		Card_24File_char ca=new Card_24File_char(5,"D:\\001.txt");
		ca.is_24("D:\\002.txt");
	}
}
