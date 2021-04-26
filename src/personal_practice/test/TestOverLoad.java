package personal_practice.test;

public class TestOverLoad {
    public static void main(String[] args) {
        Student student = new Student();
        love(student);
        String s1 = "hello";//直接赋值会放入常量池中
        String s = "hello";//从常量池中直接取值
        System.out.println(s==s1);
        String s2 = s1.intern();
        //s1==s2,intern方法是将字符串放入常量池中,返回在常量池中的地址
        System.out.println(s1==s2);
        String s4 = new String("hello");
        System.out.println(s1==s4);//false
        System.out.println(s1==s4.intern());//true
    }

    public static void love(Person p){
        System.out.println("play with "+p.getName());
    }

    public static void love(Animal a){
        System.out.println("feed some food");
    }
}
