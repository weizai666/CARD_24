package 课设java;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.CaretListener;

import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
public class ReceiveCardJFrame extends JFrame implements Runnable,ActionListener  //取牌图形用户界面，包含线程
{
    private CardBuffer cardbuffer;                         //存放牌的缓冲区（加互斥锁）
    private JTextArea text[]=new JTextArea[4];
    JPanel button;
    private JTextField show;
    private Button[] but=new Button[12];
    private Button none;
    private Button ready;
    private int CurSec=120;
    private Integer a,b,c,d;
    private String[] str={"+","-","*","/","(",")","del","="};
    private String result="";
    private Integer result0=0;
    private MessageJDialog jdialog;
	private  String temp="";
	private JMenu[] menus=new JMenu[1];
	private Integer score=0;
	protected Thread thread,y;
	protected Integer[] value;
	int k=0;
    String temp0[];;
    protected PrintWriter cout;
    private String name;
    private Socket socket;
    protected Timer timer; 
    protected Tim timetask;
    protected BufferedReader br;
    protected String line;

    public ReceiveCardJFrame(CardBuffer cardbuffer, String name,Socket socket) throws IOException
    {
    	
        super("player:  "+name+" "+InetAddress.getLocalHost()+":"+socket.getLocalPort());
        synchronized(this)
        {
        	this.socket=socket;
        	this.name=name;
        this.cardbuffer = cardbuffer ;
        this.setBounds(200,300,400,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6,1));
        JMenuBar menubar=new JMenuBar();
        this.add(menubar);
        String[] menustr={"菜单"};
        String[] menuitemstr={"退出"};
        this.menus=new JMenu[menustr.length];
        JMenuItem menuitems[]=new JMenuItem[menuitemstr.length];
        menus[0]=new JMenu(menustr[0]);
        menubar.add(menus[0]);
        for(int i=0;i<menuitemstr.length;i++)
        {
        	menuitems[i]=new JMenuItem(menuitemstr[i]);
        	this.menus[0].add(menuitems[i]);
        	menuitems[i].addActionListener(this);
        }
        JPanel Text0=new JPanel(new GridLayout(1,3));
        JPanel Text1=new JPanel(new GridLayout(1,1));
        text[0]=new JTextArea(1,1);
        text[1]=new JTextArea(1,1);
        text[2]=new JTextArea(1,1);
        text[2].setEditable(false);
        text[3]=new JTextArea(1,1);
        Text0.add(text[3]);
        text[3].setText("得分"+score);
        text[3].setEditable(false);
        this.text[1].setFont(new Font("Arial", Font.PLAIN, 20));
        Text0.add(text[0]);
        Text0.add(text[2]);
        Text1.add(text[1]);
        Text0.setVisible(true);
        text[0].setEditable(false);
        text[1].setEditable(false);
        this.add(Text0);
        this.add(Text1);
        JPanel textField=new JPanel(new GridLayout(1,1));
        show=new JTextField();
        textField.add(show);
        textField.setVisible(true);
        this.add(textField);
        button=new JPanel(new GridLayout(4,4));
        for(int i=4;i<12;i++)
        	but[i]=new Button(str[i-4]);
        for(int i=4;i<12;i++)
        	button.add(but[i]);
        this.add(button);
        button.setVisible(true);
        for(int i=4;i<12;i++)
        	but[i].addActionListener(this);
        for(int i=0;i<4;i++)
        	but[i]=new Button("");
        for(int i=0;i<4;i++)
        button.add(but[i],i);
        JPanel J=new JPanel(new GridLayout(1,2));
        none=new Button("no result");
        none.setEnabled(true);
        ready=new Button("continue");
        J.add(none);
        J.add(ready);
        ready.setEnabled(false);
        this.add(J);
        none.addActionListener(this);
        ready.addActionListener(this);
        for(int i=0;i<4;i++)
        	but[i].addActionListener(this);
        this.setVisible(true);
        new Thread(this).start();
        timetask=new Tim();
        timer.schedule(timetask, 0, 1000);
        jdialog=new MessageJDialog();
        this.cout=new PrintWriter(socket.getOutputStream(),true);
        this.cout.println(name);
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        line="已连接"+br.readLine();
        while (line!=null&&!line.endsWith("退出"))
        {
        	text[2].append(line);
        	line=br.readLine();
        }
        br.close();
        this.cout.close();
        socket.close();

    }
    }
    public ReceiveCardJFrame(CardBuffer buffer,String name,String host,int port)throws IOException
    {
    	this(new CardBuffer(1),name,new Socket(host,port));
    }
    public void run()
    {
    	try
    	{
    	value=null;
    	this.text[1].setText("");
    	show.setText("");
    	this.cardbuffer.put(new Card_24());
            value = this.cardbuffer.get();
            for(int i=0;i<value.length;i++)
            {
            this.text[1].append(String.format("%8d", value[i]));
            }//文本区添加牌
            a=value[0];b=value[1];c=value[2];d=value[3];
            for(int i=0;i<4;i++)
            	but[i].setLabel(value[i].toString());

         
        	result="";
        	but[0].setEnabled(true);
            but[1].setEnabled(true);
            but[2].setEnabled(true);
            but[3].setEnabled(true);       
            }
          catch(Exception ex){}
     } 


  public void actionPerformed(ActionEvent ev)
  {

	  String finalresult="";
	  
	  if(ev.getSource() instanceof JMenuItem)
	  {
		  if(ev.getActionCommand().equals("退出"))
		  {
			  text[2].setText("已退出");
			  this.cout.println("   对方已离线");
			  this.cout.println(name+"已退出");
			  timer.cancel();
			  System.exit(0);
		  }
	  }
	  if(ev.getSource()==but[0]){temp=a.toString();show.setText(result+=a.toString());but[0].setEnabled(false);}
	  if(ev.getSource()==but[1]){temp=b.toString();show.setText(result+=b.toString());but[1].setEnabled(false);}
	  if(ev.getSource()==but[2]){temp=c.toString();show.setText(result+=c.toString());but[2].setEnabled(false);}
	  if(ev.getSource()==but[3]){temp=d.toString();show.setText(result+=d.toString());but[3].setEnabled(false);}
	  if(ev.getSource()==but[4]){temp=str[0];show.setText(result+=str[0]);}
	  if(ev.getSource()==but[5]){temp=str[1];show.setText(result+=str[1]);}
	  if(ev.getSource()==but[6]){temp=str[2];show.setText(result+=str[2]);}
	  if(ev.getSource()==but[7]){temp=str[3];show.setText(result+=str[3]);}
	  if(ev.getSource()==but[8]){temp=str[4];show.setText(result+=str[4]);}
	  if(ev.getSource()==but[9]){temp=str[5];show.setText(result+=str[5]);}
	  if(ev.getSource()==ready&&socket.isConnected()) {thread=new Thread(this);thread.start();ready.setEnabled(false);none.setEnabled(true);but[11].setEnabled(true);}
	  if(ev.getSource()==but[10]){result="";finalresult="";
	  but[0].setEnabled(true);
	  but[1].setEnabled(true);
	  but[2].setEnabled(true);
	  but[3].setEnabled(true);
		  show.setText(result);}
	  if(ev.getSource()==none)
	  {
		  result=finalresult="";
		  show.setText("");
		  try
		  {
			  temp0=new String[5];
			Card_24 caculate=new Card_24(value);
			String[][] expression=new String[64][9];
	    	Expression_24[] AA24=new Expression_24[24];
	    	for(int m=0;m<24;m++)
	    			AA24[m]=new Expression_24(caculate.aa[m][0],caculate.aa[m][1],caculate.aa[m][2],caculate.aa[m][3]);
	    	for(int n=0;n<24;n++)
	    	        expression=AA24[n].infix();

					for(int m=0;m<expression.length;m++)
	    	        	for(int n=0;n<expression[m].length;n++)
	    	        	{
					if(Card_24.toValue(Card_24.toPostfix(expression[m][n]))==24)
					{
						this.temp0[0]=expression[m][n];
						System.out.println(expression[m][n]);
					}
	    	        	}
					
		  }
		  catch(NullPointerException ex) {}
		  catch(ArithmeticException nfex){}
		  if(this.temp0[0]==null)
		  {
				jdialog.show("回答正确");
				score++;	
				text[3].setText("得分:"+score);
				none.setEnabled(false);
				ready.setEnabled(true);
				but[11].setEnabled(false);
		  }
			else
			{
				jdialog.show("错误，当前4个数可算");
				but[0].setEnabled(true);
				  but[1].setEnabled(true);
				  but[2].setEnabled(true);
				  but[3].setEnabled(true);
				for(int i=0;i<this.temp0.length;i++)
				{
					if(temp0[i]!=null)
					System.out.println(this.temp0[i]);
				}
				temp0=null;
			}
	  }
  if(ev.getSource()==but[11])
  {
	  finalresult=result;
	  try { 
	  Card_24.toValue(Card_24.toPostfix(finalresult));
	  result0=Card_24.toValue(Card_24.toPostfix(finalresult));
      show.setText(result0.toString());
      if(result0==24&&!but[0].isEnabled()&&!but[1].isEnabled()&&!but[2].isEnabled()&&!but[3].isEnabled()
    		  &&existsOperator(finalresult)==3)
      {
    		jdialog.show("回答正确");
    		score++;
    		text[3].setText("得分:"+score);
    		none.setEnabled(false);
    		ready.setEnabled(true);
    		but[11].setEnabled(false);
      }
      else
      {
    	  jdialog.show("回答错误，请重新计算");
    	  show.setText("");
    	  result="";
    	  but[0].setEnabled(true);
    	  but[1].setEnabled(true);
    	  but[2].setEnabled(true);
    	  but[3].setEnabled(true);
      }
	  }
	  catch(NullPointerException nfex) {jdialog.show("表达式输入有误，请重新输入");}
	  catch(ArithmeticException ex) {jdialog.show("除数不能为0");}
	  }

			  				
  }
  public static int existsOperator(String str)
  {
	  int n=0;
	  for(int i=0;i<str.length();i++)
	  {
		  if(str.charAt(i)=='-'||str.charAt(i)=='+'||str.charAt(i)=='*'||str.charAt(i)=='/')
			  n++;
	  } 
	  return n;
  }

  private   class MessageJDialog extends JDialog
  {
  	private JLabel jlabel;
  	public MessageJDialog()
  	{
  		super(ReceiveCardJFrame.this,"提示",true);
  		this.setSize(300,80);
  		jlabel=new JLabel("",JLabel.CENTER);
  		this.getContentPane().add(jlabel);
  		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
  	}
  	private void show(String message)
  	{
  		jlabel.setText(message);
  		this.setLocation(ReceiveCardJFrame.this.getX()+100,ReceiveCardJFrame.this.getY()+100);
  		this.setVisible(true);
  	}

  }
  private class Tim extends TimerTask implements Runnable
  {
 	 private Tim()
 	 {
 		 timer=new Timer();
 	 }
 	 public void run()
 	 {
 		 if(CurSec>0)
              text[0].setText("Time remians "+ --CurSec +" s");
          	if(CurSec==0)
          	{
          		text[0].setText("Time is out");
					jdialog.show("你的得分"+score);
          		but[11].setEnabled(false);
          		timer.cancel();
          	}
 	 }
  }
  public static void main(String args[]) throws IOException
  {
  new ReceiveCardJFrame(new CardBuffer(1),"b","127.0.0.1",2008);
  }

}
