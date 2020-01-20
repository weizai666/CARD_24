package 课设java;


import java.io.*;
public class Card_24File_byte 
{
	private int n;
	private Card_24 card;
	public Card_24File_byte(int n)                       //获得n组数据
	{
		 this.n=n; 
	}
	public  void writeTo(String filename)
	{
		try
		{
			FileOutputStream fout=new FileOutputStream(filename);
			ObjectOutputStream objout=new ObjectOutputStream(fout);
			for(int i=0;i<this.n;i++)
			{
				this.card=new Card_24();
				for(int j=0;j<4;j++)
				objout.writeObject(card.get(j));
			}
			objout.close();
			fout.close();
		}
		catch(IOException ex) {}
	}
	public void readFrom(String filename)
	{
		try
		{
			FileInputStream fin=new FileInputStream(filename);
			ObjectInputStream objin=new ObjectInputStream(fin);
			while(true)
				try {
					System.out.print(objin.readObject()+"\t");
				}
			catch(EOFException eofx) {break;}
			objin.close();
			fin.close();
		}
		catch(Exception ex) {}
	}
	public static void main(String args[])
	{
		Card_24File_byte ca=new Card_24File_byte(2);
		ca.writeTo("D:\\abc.tf");
		ca.readFrom("D:\\abc.tf");
	}
}
