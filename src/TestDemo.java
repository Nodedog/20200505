

/*
*
*
*
*
*                           TOP K 问题
* 给定一个集合（元素个数很多N 个） 想找到前K个最大或最小的元素
*
* 以找前K个最大的值 为例
* 1.针对整个集合，建立一个大小为N的大堆，循环取K次堆顶元素即可
* 2.建立一个大小为K的小堆，对顶元素就是这个K 个元素中的最小值，循环遍历N个元素中的每个元素，
* 分别和守门员进行比较，如果比守门员大，此时直接把守门员替换成当前元素，并且向下调整，得到新的守门员
* 当所有元素便利完毕，堆中剩下的元素就是前K大的值
*
*
* */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
*
* 给定两个升序排列的整形数组nums1和nums2，以及一个整数K 定义一对值（u，v）
* u来自于nums1，v来自于nums2，找到和最小的K 对数字
*
*
* 实例nums1 = {1,7,11}   nums2={2,4,6} k=3
* 答案：{1,2}  {1，4} {1,6}
*
*
* */
public class TestDemo {
    static class Pair{
        public int num1;
        public int num2;
        public int sum;

        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
            this.sum = num1 + num2;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1,int[] nums2,int k){
        List<List<Integer>> ret = new ArrayList<>();
        if (k <= 0){
            return ret;
        }
        //先准备一个堆,此处需要制定一个比较器，告诉优先队列，啥样的情况优先级高
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.sum - o2.sum;
            }
        });
        //构造出所有数对，存放到queue之中
        for (int i = 0; i < nums1.length ; i++) {
            for (int j = 0; j < nums2.length ; j++) {
                queue.offer(new Pair(nums1[i],nums2[j]));
            }
        }
        //
        for (int i = 0; i < k && ! queue.isEmpty() ; i++) {
            Pair cur = queue.poll();
            List<Integer> tmp = new ArrayList<>();
            tmp.add(cur.num1);
            tmp.add(cur.num2);
            ret.add(tmp);
        }
        return ret;
    }


}
