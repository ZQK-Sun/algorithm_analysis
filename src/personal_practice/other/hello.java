package personal_practice.other;

public class hello {
    public static void main(String[] args) {
        System.out.println("hello,world!");
        char[] a={'a','b'};
        System.out.println(a);
        char[] b=a;//传的也是地址值
        b[0]='c';b[1]='d';
        System.out.println(b);
        System.out.println(a);
    }
}
