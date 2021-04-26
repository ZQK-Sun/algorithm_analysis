package personal_practice.chapter9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphAlgorithm {


    // Runs the shortest path calculation from the adjacency map,
    // returns a List that contains the sequence of word changes to get from first to second.
    // Returns null if no sequence can be found for any reason.
    /**
     *  以词梯游戏为例, 每个单词均由其前面的单词改变一个字母而得到.
     *  实现图的无权(无负边值)最短路径算法
     * @param adjacentWords
     * @param first
     * @param second
     * @return
     */
    public static List<String> findChain(Map<String,List<String>> adjacentWords,String first,String second){
        Map<String, String> previousWord = new HashMap<>();
        LinkedList<String> q = new LinkedList<>();
        q.addLast(first);
        while (!q.isEmpty()){
            String current = q.removeFirst();
            List<String> adj = adjacentWords.get(current);

            if (adj != null){
                for (String adjWord : adj){
                    if (previousWord.get(adjWord) == null){
                        previousWord.put(adjWord,current);
                        q.addLast(adjWord);
                    }
                }

            }
        }
        previousWord.put(first,null);

        return getChainFromPreviousMap(previousWord,first,second);
    }

    // After the shortest path calculation has run,
    // computes the List that contains the sequence of word changes to get from first to second.
    // Returns null if there is no path
    private static List<String> getChainFromPreviousMap(Map<String, String> previousWord, String first, String second) {
       LinkedList<String> result = null;
       if (previousWord.get(second) != null){
           result = new LinkedList<String>();
           for (String str = second; str != null; str = previousWord.get(str)){
               result.addFirst(str);
           }

           return result;
       }
        return null;
    }

    /**
     * 词梯游戏的变换方式扩展到允许删除字母和添加字母的单字母替换
     * 若是指定值的多字母替换,产生一个可以用Dijkstra算法求解的赋权最短路径问题
     */
}
