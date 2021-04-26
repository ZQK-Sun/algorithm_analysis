package personal_practice.other;

import java.net.InetAddress;

public class IPtoHostName {
    public static void main(String[] args) {
        /*boolean b = true;
        int a = -1;
        int c = 0xff;
        double d = 0.2;
        float f = 0.2f;
        System.out.println(Float.toHexString(f));
        System.out.println(Double.toHexString(d));
        double e = 0x1.999999999999ap-3;
        float e2 = 0x1.99999ap-3f;
        System.out.println("e2:"+e2);
        System.out.println("e:"+e);*/
        //double f = 0x3fe0000000000000;//0.5的编码格式,但是java默认的int类型
        //System.out.println(Integer.toHexString(a*2));
        //System.out.println(a*2);
        //System.out.println(~a);
        //System.out.println(c);
        //System.out.println(Math.floorMod(-1,4));//取模3

        try {
            InetAddress byName = InetAddress.getByName("192.168.43.138");
            InetAddress b = InetAddress.getByName("www.baidu.com");
            System.out.println(byName.getHostName());
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
