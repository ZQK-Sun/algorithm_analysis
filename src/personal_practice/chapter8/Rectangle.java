package personal_practice.chapter8;

import java.awt.*;

/**
 * 矩形,用坐标(x,y)来标识
 */
public class Rectangle {
    private int x;
    private int y;
    public int value;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {

        this.index = index;
    }

    public Rectangle() {

    }


    private final static int LEN = 10;
    private LineOfRectangle upLine;
    private LineOfRectangle downLine;
    private LineOfRectangle leftLine;
    private LineOfRectangle rightLine;

    public Rectangle(int x, int y,int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.value = -1;
        this.upLine = new LineOfRectangle(x,y,x+LEN,y);
        this.downLine = new LineOfRectangle(x,y+LEN,x+LEN,y+LEN);
        this.leftLine = new LineOfRectangle(x,y,x,y+LEN);
        this.rightLine = new LineOfRectangle(x+LEN,y,x+LEN,y+LEN);
    }

    public void paint(Graphics g){
        upLine.paint(g);
        downLine.paint(g);
        leftLine.paint(g);
        rightLine.paint(g);
    }
    public LineOfRectangle getUpLine() {
        return upLine;
    }

    public void setUpLine(LineOfRectangle upLine) {
        this.upLine = upLine;
    }

    public LineOfRectangle getDownLine() {
        return downLine;
    }

    public void setDownLine(LineOfRectangle downLine) {
        this.downLine = downLine;
    }

    public LineOfRectangle getLeftLine() {
        return leftLine;
    }

    public void setLeftLine(LineOfRectangle leftLine) {
        this.leftLine = leftLine;
    }

    public LineOfRectangle getRightLine() {
        return rightLine;
    }

    public void setRightLine(LineOfRectangle rightLine) {
        this.rightLine = rightLine;
    }
}
