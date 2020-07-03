package com.itheima.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",10002);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("abcd".getBytes());
        socket.close();

    }
}
