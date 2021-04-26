package personal_practice.other;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * java对数组的操作是指令级的
 * 数组的length即不是方法，也不是字段
 * 在一个数组对象上调用length，会被Java编译器编译成一条arraylength指令（Java binary code）
 * Java字节码中有许多单独针对数组的指令，它们在其它任何非数组类型的对象上调用都是毫无意义的。
 */
//几种排序算法
public class Sort {
    public static void main(String[] args) throws Exception {
        int[] a = {6,5,2,9,1,3};
        int[] b = {11,25,3,69,0};
        //合并数组
        /*int[] c = new int[a.length + b.length];
        System.arraycopy(a,0,c,0,a.length);*/
        int[] c = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b,0,c,a.length,b.length);
        HashMap<Integer, String> d = new HashMap<>();
        d.put(2,"hello");
        d.put(3,"world");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\develop\\WorkSpace\\arithmetic_demo\\out\\production\\arithmetic_demo\\personal_practice\\resource"));
        String line;
        HashMap<String, Integer> map = new HashMap<>();
        while ((line=bufferedReader.readLine())!=null){
            String[] words = line.split(",");

            for (String word : words) {
                if(null == map.get(word)) {
                    map.put(word,1);
                }else{
                    map.put(word,map.get(word)+1);
                }
            }
        }
        for (String s : map.keySet()) {
            System.out.println(s+":"+map.get(s));
        }

        bubbleSort(a,a.length);
        insertionSort(c,c.length);
        //mergeSort(a);
        //quickSort(a);
        //System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(c));
    }

        public static void bubbleSort(int[] a,int n ){
            if (n <= 1) return;

            //顺序
            for (int i = 0;i < n;i++ ){
                boolean flag = false;
                for (int j = 0;j < n-i-1;j++){
                    if (a[j] > a[j+1]){
                        //交换位置
                        int tmp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = tmp;
                        flag = true;//表示有数据交换发生
                    }

                }
                if (!flag) break;//如果没有数据交换发生,提前退出循环
            }
        }

        public static void insertionSort(int[] a,int n){
            if (n <= 1 ) return;
            for (int i = 1;i < n ;i++){
                int value = a[i];
                int j = i - 1;
                //查找插入点
                for (;j >= 0;j--){
                    if (a[j] > value){
                        a[j+1] = a[j];//数据移动
                    }else{
                        break;
                    }
                }

                //插入数据
                a[j+1] = value;
            }
        }

        //归并排序算法
        public static void mergeSort(int[] a){
            merge_sort_c( a,0,a.length-1);
        }

    private static void merge_sort_c(int[] a,int p,int r) {
        //递归终止条件
        if (p>=r) return;
        //取p到r之间的中间位置q
        int q = (int) Math.floor((p+r)/2);

        //分治递归
        merge_sort_c(a,p,q);
        merge_sort_c(a,q+1,r);
        //合并
        merge(a,p,q,r);
    }

    private static void merge(int[] a ,int p,int q,int r) {
        int[] tmp = new int[r - p + 1];
        int k = 0;
        int i = p;
        int j = q+1;
        while (i<=q && j<=r){
            if(a[i]<a[j]){
                tmp[k++]= a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }

        //判断还有剩余的子数组
        int start = i,end = q;
        if (j<=r){
            start = j;
            end = r;
        }

        //将剩余的数据拷贝到临时数组tmp
        while (start<=end){
            tmp[k++] = a[start++];
        }

        //将tmp中的数据拷贝回a[]中
        for (int b = 0; b<=r-p;b++){
            a[p+b] = tmp[b];
        }
    }

    //快速排序
    public static void quickSort(int[] a){
        quick_sort_c(a,0,a.length-1);
    }

    private static void quick_sort_c(int[] a, int p, int r) {
        if (p>=r) return;

        //获取分区点
        int q = partition(a,p,r);

        quick_sort_c(a,p,q-1);
        quick_sort_c(a,q+1,r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j<=r-1;j++){
            if (a[j]<pivot){
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
                i++;
            }
        }
        a[r] = a[i];
        a[i] = pivot;
        return i;
    }
}
