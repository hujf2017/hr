package aa;

/**
 * @Auther: xupc
 * @Date: 2019/4/25 10:32
 * @Description:
 */
public  class Singleton {   //静态类里面必须全是静态方法
    //内部产生的实例---INSTANCE，只在第一次调用的时候生成
    private final static  Singleton INSTANCE = new Singleton();  //
    public static int counter1=5;
    public static int counter2=0;
    private Singleton(){  //先运行构造器
        counter1++;
        counter2++;
    }


    //外部访问只能通过改方法  获取到之前创建的实例
    public static Singleton getInstance(){
        return INSTANCE;
    }


    public  void say(){
        System.out.println("this is a reflect method");
    }

    private static void say2(){
        System.out.println("this is a reflect method");
    }
}
