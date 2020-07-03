package com.itheima.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable{

    static List<Socket> socketList = new ArrayList<>();
    static Socket socket = null;
    static ServerSocket serverSocket = null;
    public Server(){
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("服务端.......");
        //new 自己
        Server server = new Server();
        int count = 0;
        while (true){
            try {
                System.out.println("端口9999等待连接");
                //给上面的socket
                socket = serverSocket.accept();
//            Socket accept = serverSocket.accept();
                count++;
                System.out.println("第几个"+count+"客户已经连接");
                socketList.add(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Print print = new Print(socket);
            Thread thread = new Thread(server);
            Thread printThread = new Thread(print);
            thread.start();
            printThread.start();

        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                String s = bufferedReader.readLine();
                System.out.println(s);
                for (int i = 0; i < socketList.size(); i++) {
                    Socket socket=socketList.get(i);
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    if (socket!=this.socket) {
                        out.println(s);
                    }else{
                        out.println("(你)"+s);
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Print implements Runnable{
    static List<Socket> socketList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public Print(Socket socket){
        socketList.add(socket);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            while (true){
                String msg = scanner.next();
                for (int i = 0; i < socketList.size(); i++) {
                    Socket socket = socketList.get(i);
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println("服务端说: "+msg);
                    printWriter.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

