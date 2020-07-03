package com.tianda.mymazevisual01;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;

    private MazeData data;
    private AlgoFrame frame;
    private static final int d[][]={{-1,0},{0,1},{1,0},{0,-1}};

    //深度优先遍历,递归型
    public AlgoVisualizer(String mazeFile){

        // 初始化数据
        data = new MazeData(mazeFile);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        setData(-1,-1,false);
        if(!go(data.getEntranceX(),data.getEntranceY())){
            System.out.println("从这个点出发没有解");
        }
        setData(-1,-1,false);
    }

    //从(x,y)的位置开始求解迷宫,如果求解成功,返回true,否则返回false
    private boolean go(int x,int y){
        if(!data.inArea(x,y)){
            throw new IllegalArgumentException("x,y are out of index in go function");
        }
        data.visited[x][y]=true;
        setData(x,y,true);
        if(x==data.getExitX()&&y==data.getExitY()){
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int newX = x+d[i][0];
            int newY = y+d[i][1];
            if(data.inArea(newX,newY)&&data.getMaze(newX,newY)== MazeData.ROAD&&!data.visited[newX][newY]){
                //返回true,那就对了
                if(go(newX,newY)){
                    return true;
                }
            }
        }
        //如果四个方向都没找到,变为设置false
        setData(x,y,false);
        return false;
    }

    private void setData(int x,int y,boolean isPath){
        if(data.inArea(x,y)){
            //进到一个点,然后渲染
            data.path[x][y]=isPath;
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        String mazeFile = "C:\\Users\\da\\IdeaProjects\\untitled\\src\\maze_101_101.txt";

        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);

    }
}