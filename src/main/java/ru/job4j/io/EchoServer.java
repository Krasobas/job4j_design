package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    private static String readMessage(String message) {
        int start = message.indexOf("msg=");
        int end = message.indexOf("HTTP");
        return message.substring(start + 4, end - 1);
    }
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    String message = readMessage(str);
                    if ("Exit".equals(message)) {
                        server.close();
                    } else if ("Hello".equals(message)) {
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write(message.getBytes());
                    }
                    while (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Some problem with socket reading/writing!", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Server wasn't created!", e);
        }
    }
}
