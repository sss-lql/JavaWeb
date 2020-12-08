package javathread1.aolige.Tongbu.Test1;

import java.util.HashMap;

//既然任意对象都可以用来作为同步对象，而所有的线程访问的都是同一个hero对象，索性就使用gareen来作为同步对象
//进一步的，对于Hero的hurt方法，加上：
//synchronized (this) {
//}
//表示当前对象为同步对象，即也是gareen为同步对象
public class Testsyn {
    public static void main(String[] args) {
        final Hero2 gareen = new Hero2();
        gareen.name="盖亚";
        gareen.hp=10000;
        int n=10000;



        Thread[] add = new Thread[n];
        Thread[] red = new Thread[n];

        for(int i=0;i<n;i++){

             Thread t = new Thread(){
                public void run(){
                    //使用gareen作为此对象
                    synchronized (gareen){
                        gareen.rec();
                    }

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            };
            t.start();
            add[i]=t;

        }

        for(int i=0;i<n;i++){
            Thread t = new Thread(){
                public void run(){
                    //使用gareen作为synchronized
                    //在方法hurt中有synchronized(this)
                    gareen.hurt();
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            red[i]=t;
        }

        for(Thread t:add){
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        for(Thread t :red){
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量是 %.0f%n", n,n,gareen.hp);

    }
}
