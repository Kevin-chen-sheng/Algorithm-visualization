package com.itheima.demo.thinkinjava;

import java.awt.*;

class Blockable extends Thread{
    private Peeker peeker;
    protected TextField state=new TextField(40);
    protected int i;
    public Blockable(Container c){
        c.add(state);
        peeker=new Peeker(this,c);
    }
    public synchronized int read(){
        return i;
    }
    protected synchronized void update(){
        state.setText(getClass().getName()+"state:i"+i);
    }
    public void stopPeeker(){
        peeker.terminate();
    }
}
class Peeker extends Thread{
    private Blockable b;
    private int session;
    private TextField status=new TextField(40);
    private boolean stop=false;
    public Peeker(Blockable b,Container c){
        c.add(status);
        this.b=b;
        start();
    }
    public void terminate(){
        stop=true;
    }
    public void run(){
        while (!stop){
            status.setText(b.getClass().getName()+"Peeker"+(++session)+";value="+b.read());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
