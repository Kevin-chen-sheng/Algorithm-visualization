package com.itheima.demo.thinkinjava;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

class CBox2 extends Canvas{
    private static final Color[] COLORS={
            Color.black,Color.blue,Color.cyan,
            Color.darkGray,Color.gray,Color.green,
            Color.lightGray,Color.magenta,
            Color.orange,Color.pink,Color.red,
            Color.white,Color.yellow
    };
    private static final Color newColor(){
        return COLORS[(int) (Math.random()*COLORS.length)];
    }
    private Color cColor=newColor();
    void nextColor(){
        cColor=newColor();
        repaint();
    }
    public void paint(Graphics g){
        g.setColor(cColor);
        Dimension s =getSize();
        g.fillRect(0,0,s.width,s.height);
    }

}

class CBoxVector extends Vector implements Runnable {

    private Thread t;
    private int pause;

    public CBoxVector(int pause) {
        this.pause = pause;
        t=new Thread(this);
    }
    public void go(){
        t.start();
    }

    @Override
    public void run() {
        while (true){
            int i= (int) (Math.random()*size());
            ((CBox2)elementAt(i)).nextColor();
            try {
                t.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ColorBoxes2 extends Frame{
    private CBoxVector[] v;
    public ColorBoxes2(int pause,int grid){
        setTitle("ColorBoxes2");
        setLayout(new GridLayout(grid,grid));
        v=new CBoxVector[grid];
        for (int i = 0; i < grid; i++) {
            v[i]=new CBoxVector(pause);

        }
        for (int i = 0; i < grid*grid; i++) {
            v[i%grid].addElement(new CBox2());
            add((CBox2)v[i%grid].lastElement());
        }
        for (int i = 0; i < grid; i++) {
            v[i].go();

        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        int pause=5;
        int grid=8;
        if(args.length>0){
            pause=Integer.parseInt(args[0]);

        }
        if(args.length>1){
            grid=Integer.parseInt(args[1]);

        }
        Frame f=new ColorBoxes2(pause,grid);
        f.setSize(500,400);
        f.setVisible(true);
    }
}
