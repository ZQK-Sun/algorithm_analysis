package personal_practice.chapter2;

/**
 * 最大子序列和问题的求解:几种不同时间复杂度的算法
 */
public class MaxSubSum {
    public static void main(String[] args) {

    }

    /**
     * Cubic maximum contiguous subsequence sum algorithm
     * O(N^3)
     * @param a
     * @return
     */
    public static int maxSubSum1(int[] a){
        int maxSum = 0;
        for(int i = 0;i<a.length;i++){
            for (int j = i;j<a.length;j++){
                int thisSum = 0;
                for (int k = i;k<=j;k++){
                    thisSum +=a[k];
                }
                if (thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * Quadratic
     * O(N^2)
     * @param a
     * @return
     */
    public static int maxSubSum2(int[] a){
        int maxSum = 0;
        for(int i = 0;i<a.length;i++){
            int thisSum = 0;
            for (int j = i;j<a.length;j++){
                thisSum += a[j];
                if (thisSum > maxSum){
                    maxSum = thisSum;;
                }
            }
        }
        return maxSum;
    }

    /**
     * Recursive
     * O(NlogN)
     */
    public static int maxSubsum3(int[]a ,int left ,int right){
        if(left == right){
            if(a[left] > 0){
                return a[left];
            }
        }else{
            return 0;
        }
        int center = (left + right)/2;
        int maxLeftSum = maxSubsum3(a, left, center);
        int maxRightSum = maxSubsum3(a, center + 1, right);
        int maxLeftBorderSum = 0,leftBorderSum = 0;
        for(int i = center;i>= left;i--){
            leftBorderSum +=a[i];
            if(leftBorderSum > maxLeftBorderSum){
                maxLeftBorderSum = leftBorderSum;
            }
        }
        int maxRightBorderSum = 0,rightBorderSum = 0;
        for (int i = center + 1;i<=right;i++){
            rightBorderSum +=a[i];
            if(rightBorderSum > maxRightBorderSum){
                maxRightBorderSum = rightBorderSum;
            }
        }
        int maxRLSum = maxLeftSum > maxRightSum ? maxLeftSum:maxRightSum;
        int maxBorderSum = maxRightBorderSum + maxLeftBorderSum;
        return maxRLSum > maxBorderSum ? maxRLSum:maxBorderSum;
    }

    /**
     * linear-time
     * O(N)
     */
    public static int maxSubSum4(int[] a){
        int maxSum = 0,thisSum = 0;
        for (int i = 0;i<a.length;i++){
            thisSum +=a[i];
            if (thisSum > maxSum){
                maxSum = thisSum;
            }else if(thisSum < 0){
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
