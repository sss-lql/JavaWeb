package javathread1.aolige.Lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//Lock是一个接口，为了使用一个Lock对象，需要用到
//Lock lock = new ReentrantLock();
//与 synchronized (someObject) 类似的，lock()方法，表示当前线程占用lock对象，一旦占用，其他线程就不能占用了。
//与 synchronized 不同的是，一旦synchronized 块结束，就会自动释放对someObject的占用。 lock却必须调用unlock方法进行手动释放，为了保证释放的执行，往往会把unlock() 放在finally中进行
public class tongbu {
    public static String now(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    public static void log(String msg){
        System.out.printf("%s %s %s %n",now(),Thread.currentThread().getName(),msg);

    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(){
            public void run(){
                try{
                    log("线程启动");
                    log("试图占有对象lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒业务操作");
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    log("释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t1.setName("t1");
        t1.start();
        try{
            //让t1飞行2秒
            Thread.sleep(2000);
        }catch ( InterruptedException e){
            e.printStackTrace();
        }

        Thread t2 = new Thread(){
            public void run(){
                try{
                    log("线程启动");
                    log("试图占有对象");

                    lock.lock();

                    log("占有对象:lock");
                    log("进行5秒操作");
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    log("释放对象");
                    lock.unlock();
                }
                log("线程结束");
            }

        };
        t2.setName("t2");
        t2.start();
    }
}
