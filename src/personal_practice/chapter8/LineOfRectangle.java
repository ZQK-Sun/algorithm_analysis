package personal_practice.chapter8;

import java.awt.*;

/**
 * 小矩形的边
 */
public class LineOfRectangle {
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public boolean isPaint = true;

    public LineOfRectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void paint(Graphics g){
        if(isPaint)
        g.drawLine(x1,y1,x2,y2);
    }

}
