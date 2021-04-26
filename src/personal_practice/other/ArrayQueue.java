package personal_practice.other;

import java.util.Arrays;

/**
 * 用数组来实现队列
 * 自己琢磨
 */
public class ArrayQueue {
    private String[] items;
    private int capacity = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        items = new String[capacity];
    }

    //入队
    public boolean enqueue(String item) {
        if (tail == capacity) {
            try {
                throw new Exception("队列已满");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    //出队
    public String dequeue() {
        String ret = items[0];
        //数组中所有元素向前移一位
        for (int i = 0; i < items.length; i++) {
            if (i == capacity - 1) {
                items[i] = null;
                break;
            }
            items[i] = items[i + 1];
        }
        tail--;
        return ret;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }


    //测试一下
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        for (int i = 0; i < 10; i++) {
            queue.enqueue("queue:" + i);
        }
        // System.out.println(queue.enqueue("queue:"+10));
        System.out.println(queue);
        for (int i = 0; i < 3; i++) {
            String item = queue.dequeue();
            System.out.println(item + "出队了");
        }
        queue.enqueue("queue:" + 10);
        System.out.println(queue);
    }
}

/**
 * 答案
 * 只需集中触发一次数据搬移
 */
class ArrayQueue2 {
    private String[] items;
    private int capacity = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue2(int capacity) {
        this.capacity = capacity;
        items = new String[capacity];
    }

    //入队
    public boolean enqueue(String item) {
        if (tail == capacity) {
            if (head == 0) {//队列已满
                return false;
            }
            //数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            //更新head和tail
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    //出队
    public String dequeue(){
        if (head == tail) return null;
        String ret = items[head];
        head++;
        return ret;
    }
}

/**
 * 循环队列
 */
class CircularQueue{
    private String[] items;
    private int capacity = 0;
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        items = new String[capacity];
    }

    public boolean enqueue(String item){
        //队列满了
        if((tail+1)%capacity == head) return false;
        items[tail] = item;
        tail = (tail + 1)% capacity;
        return true;
    }

    public  String dequeue(){
        //head==tail表示队列为空
        if(head == tail) return null;
        String ret = items[head];
        head = (head + 1)%capacity;
        return ret;
    }
}

