package personal_practice.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Mytest {
    private String name;
    public static void main(String[] args){
        Person p = new Person("张三",18);
        ArrayList<Person> list = new ArrayList<>();
        list.add(p);
        System.out.println("原始:"+p);
        /*Person lp = list.get(0);
        System.out.println("list中"+lp);
        lp = new Person("王五",11);
        System.out.println("重新赋值"+lp);
        int n = 3;
        //演示值传递
        change(p,n);
        System.out.println(n);
        System.out.println(p);
        System.out.println(p.getName()+ "\t" + p.getAge());
        System.out.println('1'+2);*/
        /**
         * Object中的toString方法是调用的hashCode,
         * 要是Person中重写了hashcode方法,使得属性值相同的对象hashCode的返回值也相同,则打印出String的就相同
         */
        Person teacher = new Node(new Person("张三",18)).teacher;
        System.out.println(teacher+"    "+(p==teacher));
        GenericMemoryCell<String>[] cell = new GenericMemoryCell[10];

        /**
         *测试hashMap:
         * 即使具体的class都不同,若两个key的hashCode返回值相同,equals方法返回true,会被认为是同一个key(但是一般equals方法是要包含类型判断的)
         * keySet方法返回一个Set集合的内部类,iterator()方法返回泛型为key.class的迭代器,可以直接使用外部类的table(Node[]数组)来遍历key.   增加强for循环就是用调用Iterator里的方法来遍历集合的
         * entrySet方法返回一个Set集合的内部类,iterator()方法返回泛型为Node(父接口Map.Entry)的迭代器,直接迭代Node数组
         */
        HashMap<Person, Integer> hMap = new HashMap<>();
        Person p2 = new Person("张三",18){
            @Override
            public int hashCode() {
                return 1;
            }
        };
        Person p3 = new Person("张三",18){
            @Override
            public int hashCode() {
                return 1;
            }
        };
        hMap.put(p2,1);
        hMap.put(p3,2);
        System.out.println(hMap.get(p2));
        System.out.println(p2+"    "+p3);

    }

    static void change(Person person,int n){
        person.setName("李四");
        person.setAge(30);
        n = n + 1;
       System.out.println("方法内基本数据类型的值:"+n);
        person = new Person("张三",18);
       System.out.println("方法内引用类型的值:"+person);

    }

    public void test(){
        System.out.println("我能调用static方法吗?");
        change(null,0);//能
        Node.play(this);//static只是标识Node为嵌套类,并不代表里面的属性和方法都为static, 嵌套类除了可以通过外部类的引用访问私有字段外与普通类无区别

    }

    /**
     * 嵌套类
     */
    public static class Node{
        public Person teacher;

        private String tName;

        public String gettName() {
            return tName;
        }

        public void settName(String tName) {
            this.tName = tName;
        }

        public void testOuterMethod(){
            change(teacher,1);
//            test();  不能引用
        }
        public Node() {
        }

        public Node(Person teacher) {
            this.teacher = teacher;
        }

        static void play(Mytest mytest){
            System.out.println("play!"+mytest.name);
        }

    }



}
