package personal_practice.chapter8;

/**
 * 不相交集类
 */
public class DisJSets {
    private Rectangle[] rectangles;

    public DisJSets(int numElements){
        rectangles = new Rectangle[numElements];
        for (int i = 0; i<numElements; i++){
            rectangles[i].value = -1;
        }
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }

    public int size(){
        return rectangles.length;
    }

    public DisJSets(Rectangle[] rectangles){
        this.rectangles = rectangles;
        for (int i = 0; i < rectangles.length; i++){
            rectangles[i].value = -1;
        }
    }
    public void union(int root1,int root2,int direct){
        if(union(root1,root2)) {
            switch (direct) {
                case 0:
                    rectangles[root1].getRightLine().isPaint = false;
                    rectangles[root2].getLeftLine().isPaint = false;
                    break;
                case 1:
                    rectangles[root1].getLeftLine().isPaint = false;
                    rectangles[root2].getRightLine().isPaint = false;
                    break;
                case 2:
                    rectangles[root1].getDownLine().isPaint = false;
                    rectangles[root2].getUpLine().isPaint = false;
                    break;
                case 3:
                    rectangles[root1].getUpLine().isPaint = false;
                    rectangles[root2].getDownLine().isPaint = false;
                    break;
            }
        }
    }
    /**
     * 按高度求并Union two disjoint sets using the height heuristic
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names
     * @param root1
     * @param root2
     */
    public boolean union(int root1,int root2){
        int r1 = find(root1);
        int r2 = find(root2);
        if (r1 == r2) return false;
        if (rectangles[r1].value < rectangles[r2].value)    //root2 is deeper
            rectangles[r1].value = r2;                      //Make root2 new root
        else
        {
            if (rectangles[r1].value == rectangles[r2].value)
                rectangles[r1].value--; //Update height if same
            rectangles[r2].value = r1;  //Make root1 new root
        }
        return true;
    }


    public int find(int x){
        if (rectangles[x].value < 0){
            return x;
        }
        else {
            return rectangles[x].value = find(rectangles[x].value);
        }

    }

}
