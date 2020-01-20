package 课设java;

public class CardBuffer                                //加互斥锁的缓冲区
{
    private Integer value[];                          //共享变量，临界资源
    private int number;                                    //人数
    
    CardBuffer(int number)
    {
        this.number = number;
    }
    synchronized void put(Card_24 card)
    {
        while (value!=null)                              //当value不空时，等待
            try
            {
                this.wait();                               //等待
            }
            catch(InterruptedException ex) {}
        this.value=card.number;                                      //当value空时，value获得值
        this.notifyAll();                                   //唤醒所有其他等待线程
    }
    
    synchronized Integer[] get()                
    {
        while (value==null)        //当value空时等待
            try
            {
                 this.wait();
            }
            catch(InterruptedException ex) {}
        Integer[] temp=value.clone();
        value=null;                                 //设置value为空状态，并返回值
        this.notifyAll(); 
        return temp;                      
    }
}
