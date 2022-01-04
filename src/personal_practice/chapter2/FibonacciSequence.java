package personal_practice.chapter2;

/**
 * 斐波那契数列:1,1,2,3,5,8,13,21...
 * 求第N个数的值
 */
public class FibonacciSequence {
    public static void main(String[] args) {
        int n = 30;
        /*long startTime = System.currentTimeMillis();
       // long r1 = fib(n);
        long endTime1 = System.currentTimeMillis();
        //long r2 = fib2(n*10000);
        long endTime2 = System.currentTimeMillis();
        long r3 = fib3(n*10000);
        long endTime3 = System.currentTimeMillis();
        long r4 = fib4(n*10000);
        long endTime4 = System.currentTimeMillis();

        //System.out.println("递归用时:"+(endTime1 - startTime)+" 结果为:"+r1);
        System.out.println("for循环N放大10000倍用时:"+(endTime2 - endTime1)+" 结果为:"+fib2(n));
        System.out.println("fib3放大10000倍用时:"+(endTime3 - endTime2)+" 结果为:"+fib3(n));
        System.out.println("fib4放大10000倍用时:"+(endTime4 - endTime3)+" 结果为:"+fib4(n));*/

        long startTime = System.currentTimeMillis();
        double r1 = eval(n);
        long endTime1 = System.currentTimeMillis();
        double r2 = eval2(n*1000);
        long endTime2 = System.currentTimeMillis();
        double r3 = eval3(n*100000);
        long endTime3 = System.currentTimeMillis();
        double r4 = eval4(n*100000);
        long endTime4 = System.currentTimeMillis();
        
        System.out.println("递归用时:"+(endTime1 - startTime)+" 结果为:"+r1);//n=30时 约3000
        System.out.println("eval2放大1000倍用时:"+(endTime2 - endTime1)+" 结果为:"+eval2(n));// 约500
        System.out.println("eval3放大100000倍用时:"+(endTime3 - endTime2)+" 结果为:"+eval3(n));// 约40
        System.out.println("eval4放大100000倍用时:"+(endTime4 - endTime3)+" 结果为:"+eval4(n));// 约30
    }

    /**
     * 求数列的第N个值
     * 递归方法求斐波那契数列的第你n个值,时间复杂度是O((3/2)^n),运算时间随n的增加成指数级爆炸增长
     * @param n
     * @return
     */
    public static long fib(int n){
        if(n<=2){return  1;}
        return fib(n-1) + fib(n-2);
    }

    /**
     * 用for循环+数组来求值,时间复杂度为O(n),运算时间随n的增加线性增长
     * 在N=10000000时需要用将近80M的内存,随着N的增大程序会报堆内存不够的错误
     */
    public static long fib2(int n){
        long[] fib2 = new long[n];
        fib2[0]=fib2[1]=1;
        for (int i = 2;i <= n-1;i++){
            fib2[i] = fib2[i-1] + fib2[i-2];
        }
        return fib2[n-1];
    }

    /**
     * 继续优化:
     * 减少不必要的内存引用,在N=10000000时测试用时每次都少于fib2
     * 无论N用多大只需要用到3个寄存器,不需要用到内存
     */
    public static long fib3(int n){
        long acc = 1;
        long acc_1 = 1;
        long acc_2;
        for (int i = 3;i <= n;i++){
            acc_2 = acc;
            acc = acc + acc_1;
            acc_1 = acc_2;
        }
        return acc;
    }

    /**
     * 继续优化:N=100000000时才能看出优化效果
     * "K×1循环展开"提升不大(整数加大约能提升一倍性能,而浮点加、浮点乘、整数乘等性能几乎无提升),而本例无法进行K×K展开
     * Intel Core i7 Haswell作为参考机器,有两个加载单元
     * 能执行"整数运算"的功能单元有4个,整数加的延迟为1,发射时间为1
     * 能执行"整数乘法"的功能单元有1个,延迟为3,发射时间为1
     * 浮点乘法的功能单元有1个,延迟为5
     * 除法的功能单元1个,延迟浮动(3-15,3-30)
     */
    public static long fib4(int n){
        long acc = 1;
        long acc_1 = 1;
        long tmp;
        int i;
        for (i = 3;i <= n;i+=2){
            tmp = acc;
            acc = acc + acc_1;
            acc_1 = tmp;

            tmp = acc;
            acc = acc + acc_1;
            acc_1 = tmp;
        }
        for(;i < n;i++){
            tmp = acc;
            acc = acc + acc_1;
            acc_1 = tmp;
        }
        return acc;
    }



    /**
     *
     * 求解另外一类似问题： C(N) = 2/N *(C(1) + C(2) + C(3) + ... + C(N-1)) + N,   C(0)=1
     * 这是一个动态规划的例子
     * 先看指数级增长的低效递归
     */
    public static double eval(int n){
        if(n==0){
            return 1.0;
        }else{
            double sum = 0.0;
            for(int i = 0; i < n; i++)
                sum += eval(i);
            return 2.0 * sum / n + n;
        }
    }

    /**
     *  改成非递归算法，把子问题的答案系统的记录在一个表内
     *  复杂度O(N^2)
     */
    public static double eval2(int n){
        double[] c = new double[n + 1];

        c[0] = 1.0;
        for (int i = 1; i <= n;i++){
            double sum = 0.0;
            for(int j = 0; j < i;j++)
                sum +=c[j];
            c[i] = 2.0 * sum / i + i;
        }
        return c[n];
    }

    /**
     * 把前N-1 个c的值的和记录在一个表内
     * 复杂度O(N)
     */
    public static double eval3(int n){
        double[] cSum = new double[n + 1];
        double c = 1.0;
        for(int i = 1; i <= n ; i++){
            cSum[i] = c + cSum[i-1];
            c = 2.0 * cSum[i] / i + i;
        }
        return c;
    }

    /**
     * 时间复杂度O(N)
     * 去除数组，减少内存占用，用2个寄存器即可
     * @param n
     * @return
     */
    public static double eval4(int n){
        double cSum = 0.0;
        double c = 1.0;
        for(int i = 1; i <= n ; i++){
            cSum += c;
            c = 2.0 * cSum / i + i;
        }
        return c;
    }
}
