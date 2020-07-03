package com.itheima.chatroom01;
 
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
public class SingleServer {
    public static void main(String[] args) throws IOException {
        //1.创建服务器端的ServerSocket对象,等待客户端连接其端口
        ServerSocket serverSocket=new ServerSocket(6666);
        System.out.println("服务器的端口号为:6666,正在等待客户端的连接...");
        //2.侦听并接收服务器端的连接,返回套接字Socket对象
        Socket socket=serverSocket.accept();
        //3.获取客户端的输入流,读取客户端输入的内容
        Scanner scanner=new Scanner(socket.getInputStream());
        scanner.useDelimiter("\n");
        if(scanner.hasNext()){
            System.out.println("客户端发来消息:"+scanner.next());
        }
        //4.获取客户端的输出流,向客户端输出内容
        PrintStream printStream=new PrintStream(socket.getOutputStream());
        printStream.println("客户端你好!我是服务器端:"+serverSocket.getLocalPort());
        //5.关闭流
        serverSocket.close();
    }
}
 