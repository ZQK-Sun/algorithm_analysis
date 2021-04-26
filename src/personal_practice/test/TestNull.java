package personal_practice.test;

public class TestNull
{
        public static void main(String[] args) {
        String s = null;
        String s1 = "hello";
        Boolean s2 = null;
       // System.out.println(null==null);
       // System.out.println(s==s1);
        //   System.out.println(!s2);
           String s3 = tranf(s1);
            System.out.println(s3+":s3:"+s3.hashCode());

            System.out.println(s1+":s1:"+s1.hashCode());
    }

    public static String tranf(String s){
        System.out.println("s:"+s.hashCode());
            s=s.substring(0,3)+"p!";
        System.out.println("s:back:"+s.hashCode());
            return s;
    }
}
