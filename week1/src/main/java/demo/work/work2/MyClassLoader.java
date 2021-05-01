package demo.work.work2;


import com.google.common.primitives.Bytes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

/**
 * @author xxl
 *
 * 读取Hello.xlass文件，调用它的hello方法
 *
 */
public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception{
        MyClassLoader classLoader = new MyClassLoader();

        Object o = classLoader.findClass("D:\\app\\ideaProjects\\infoq\\week1\\src\\main\\java\\demo\\work\\classloader\\Hello.xlass")
                .newInstance();
        o.getClass().getMethod("hello").invoke(o);
    }



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(name);
            LinkedList<Byte> list = new LinkedList<Byte>();
            int i;
            while ((i=inputStream.read()) != -1){
                list.add((byte)(255 - i));
            }

            byte[] bytes = Bytes.toArray(list);

            return defineClass("Hello", bytes, 0, bytes.length);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return super.findClass(name);

    }
}
