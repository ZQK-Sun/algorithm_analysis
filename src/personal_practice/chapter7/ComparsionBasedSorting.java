package personal_practice.chapter7;

import sun.applet.Main;

import java.util.Arrays;

/**
 * 基于比较的排序
 */
public class ComparsionBasedSorting {

    public static void main(String[] args) {
        Integer[] a = {8,2,6,9,11,58,3,22,8,2,6,9,11,58,3,22,8,2,6,9,11,58,3,22};
       // heapSort(a);/*堆排序*/
        //mergeSort(a);//归并排序
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * Simple insertion sort.   (N^2)
     * @param a an array of Comparable items.
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void  insertionSort(AnyType []a){
        int j;

        for (int p=1;p<a.length;p++){
            AnyType tmp = a[p];
            for (j=p;j>0 && tmp.compareTo(a[j-1])<0;j--){
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }




    /**
     * ShellSort, using Shell's (poor) increments. (worst condition:N^2 )
     * Hibbard increments. (worst condition:N^(3/2) )
     * the best increments:{1,5,19,41,109...}
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void sellSort(AnyType []a){
        int j;

        for (int gap = a.length/2;gap > 0;gap /=2){
            for (int i = gap;i<a.length;i++){
                AnyType tmp = a[i];
                for (j = i; j > 0 && tmp.compareTo(a[j-gap]) < 0; j -=gap){
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }




    /**
     * Standard heapsort
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType []a){
        /*buildHeap*/
        for (int i = a.length / 2; i >= 0; i--)
            percDown(a,i,a.length);
        /*deleteMax*/
        for (int i = a.length - 1; i >= 0; i--){
            AnyType tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            percDown(a,0,i);
        }

    }

    private static <AnyType extends Comparable<? super AnyType>> void  percDown(AnyType []a,int i,int n){
        int child;
        AnyType tmp;

        for(tmp = a[i]; leftChild(i) < n; i = child){
            child = leftChild(i);
            if (child != n-1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0 ){
                a[i]  = a[child];
            }else{
                break;
            }
        }
        a[i] = tmp;
    }

    private static int leftChild(int i){
        return 2 * i + 1;
    }




    /**
     * MergeSort algorithm  (N*logN)
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType []a){
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a,tmpArray,0,a.length - 1);
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType []a,AnyType [] tmpArray,int left,int right){
        if (left < right){
            int center = (left + right) / 2;
            mergeSort(a,tmpArray,left,center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a,tmpArray,left,center+1,right);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a,AnyType[] tmpArray,int leftPos ,int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //Main loop
        while(leftPos <= leftEnd && rightPos <= rightEnd ){
            if (a[leftPos].compareTo(a[rightPos])<0){
                tmpArray[tmpPos++] = a[leftPos++];
            }else{
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd)  //copy  rest of first half
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd) //copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];

        //Copy tmpArray back
        for (int i = 0; i < numElements; i++,rightEnd--){
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    public final static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a,int index1,int index2){
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /**
     * Quicksort algorithm method that makes recursive calls
     * Uses median-of-three partitioning nad a cutoff of 10
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a){
        quickSort(a,0,a.length - 1);
    }

    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a,int left ,int right){
         if (left + 10 <= right){
             AnyType pivot = median3(a, left, right);

             //Begin partitioning
             int i = left,j = right -1;
             for (;;){
                 while (a[++i].compareTo(pivot)<0){}
                 while (a[--j].compareTo(pivot)>0){}
                 if (i < j)
                     swapReferences(a,i,j);
                 else
                     break;
             }
             swapReferences(a,i,right - 1);//Restore pivot

             quickSort(a,left,i-1); //Sort small elements
             quickSort(a,i+1,right); //Sort large elements
         }
         else
             insertionSort(a);
    }

    public static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right){
        int center = (left + right) / 2 ;
        if(a[center].compareTo(a[left])<0)
            swapReferences(a,left,center);
        if (a[right].compareTo(a[center])<0)
            swapReferences(a,right,center);
        if (a[right].compareTo(a[left])<0)
            swapReferences(a,right,left);

        //place pivot at position right - 1
        swapReferences(a,center,right - 1);
        return a[right - 1];
    }
}

