package aa;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: xupc
 * @Date: 2019/4/25 09:16
 * @Description:
 */
public class list {
    public static int counter3;
    static{
        counter3++;
    }
    public static void main(String [] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        int a[] ={1,2,3,4,5};
      //  System.out.println(a[4]);
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.counter1);
        System.out.println(singleton.counter2);
        System.out.println(counter3);
        System.out.println("-----------------");


       // Singleton a = new Singleton(); 创建不了，why？？？，private 的构造器
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);

        /*
         * @Author xupc
         * @Date 2019/4/25 14:49
         * @return
         * 反射创建对象
         **/
        Class b = Class.forName("aa.Singleton");
        Constructor cons = b.getDeclaredConstructor(null); //构造方法
        cons.setAccessible(true);
        Singleton singleton3 = (Singleton) cons.newInstance(null);
        System.out.println(singleton3);
        /*
         * @Author xupc
         * @Date 2019/4/25 14:49
         * @return
         * invoke 调用方法
         **/
        Method m =b.getDeclaredMethod("say");
        m.invoke(singleton3);



    }
}
