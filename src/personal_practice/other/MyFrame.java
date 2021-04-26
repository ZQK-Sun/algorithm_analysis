package personal_practice.other;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MyFrame extends JFrame {
    private int FIELDSIZE = 40;
    public MyFrame(){
        setTitle("国际象棋方框示例");
        setName("test");
        setBounds(400, 200, 330, 350);
        setResizable(false);
        JPanel boardPane = new JPanel();
        boardPane.setLayout(null);
        add(boardPane);
        JLabel[][] labs = new JLabel[8][8];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++) {
                JLabel backgroundLabel = new JLabel();
                backgroundLabel.setOpaque(true);
                backgroundLabel.setBounds(x*FIELDSIZE,y*FIELDSIZE,FIELDSIZE,FIELDSIZE);
                boardPane.add(backgroundLabel,new Integer(1),0);
                labs[x][y] = backgroundLabel;
            }
        }
        //setColor(labs);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void setColor(JLabel[][] labs){
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                if(x==0){
                    if(y==0){
                        labs[x][y].setBackground(Color.BLACK);
                    }else{
                        if(labs[x][y-1].getBackground().equals(Color.BLACK)){
                            labs[x][y].setBackground(Color.GRAY);
                        }else{
                            labs[x][y].setBackground(Color.BLACK);
                        }
                    }
                }else{
                    if(labs[x-1][y].getBackground().equals(Color.BLACK)){
                        labs[x][y].setBackground(Color.GRAY);
                    }else{
                        labs[x][y].setBackground(Color.BLACK);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        new MyFrame();
    }
}