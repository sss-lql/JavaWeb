package JavaIO.JavaIO1.Guanbiliu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
//在try的作用域里关闭文件输入流，在前面的示例中都是使用这种方式，这样做有一个弊端；
//如果文件不存在，或者读取的时候出现问题而抛出异常，那么就不会执行这一行关闭流的代码，
//存在巨大的资源占用隐患。 不推荐使用
public class testtry {
    public static void main(String[] args) {
        try{
            File f = new File("d:/xyz/test.txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] all = new byte[(int)f.length()];

            fis.read(all);
            for(byte b:all){
                System.out.println(b);
            }

            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
