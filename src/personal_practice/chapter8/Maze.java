package personal_practice.chapter8;

import sun.print.ProxyGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Maze {
    public static void main(String[] args) {
        SimpleFrame frame = new Maze().new SimpleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(710,340);

}
class SimpleFrame extends JFrame{
        private final static int DEFAULT_WIDTH = 550;
        private final static int DEFAULT_HEIGHT = 450;

    public SimpleFrame() throws HeadlessException {
        add(new MyComponent());
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
class MyComponent extends JComponent{

        private DisJSets disJSets;

    public MyComponent() {
        Rectangle[] rectangles = new  Rectangle[2000];
        //初始化
        for (int i = 0 ; i < 50 ; i++){
            for (int j = 0 ; j < 40 ; j++){
                rectangles[i + j * 50] = new Rectangle(i * 10,j * 10,i + j * 50);
            }
        }
        //第一个和最后一个矩形要少打印两条边
        rectangles[0].getUpLine().isPaint =false;
        rectangles[0].getLeftLine().isPaint = false;
        rectangles[1999].getDownLine().isPaint =false;
        rectangles[1999].getRightLine().isPaint = false;

         disJSets = new DisJSets(rectangles);
    }

    public void paintComponent(Graphics g){
            // 设置线条颜色（RED为红色）
            g.setColor(Color.RED);
//            int x = 10, y=10, w=500, h=400;
//
//            // 绘制外层矩形框
//            g.drawRect(x, y, w, h);

            /**
             * 绘制50*40的小矩形
             * 利用不相交集的数据结构来形成一个迷宫
             */
            buildMaze(disJSets);
            for (Rectangle r :disJSets.getRectangles()){
                r.paint(g);
            }

        }

    /**
     * 用不相交集类构建迷宫
     * @param disJSets
     */
    public void buildMaze(DisJSets disJSets){
        while (disJSets.find(0) != disJSets.find(1999)){
            int index1 = new Random().nextInt(disJSets.size());
            int direct = new Random().nextInt(4);
            /*
             *上下左右四个相邻的矩形
             * 0    right
             * 1    left
             * 2    down
             * 3    up
             */
            int index2 =(direct < 2)? index1 + (int)Math.pow(-1,direct):index1 + (int)Math.pow(-1,direct)*50;
            boolean rightLine = disJSets.getRectangles()[index1].getRightLine().x1 == 500 && direct == 0;
            boolean LeftLine = disJSets.getRectangles()[index1].getLeftLine().x1 ==0 && direct == 1;
            if (index2>=0 && index2<2000 && !rightLine && !LeftLine){
                disJSets.union(index1,index2,direct);
            }
            else
                continue;
        }

    }
}
}
