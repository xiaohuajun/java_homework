package httpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午9:16
 * @description HttpServerOne 模拟http服务
 */
public class HttpServerOne {


  public static void main(String[] args) throws IOException {
    ExecutorService executorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);
    final ServerSocket serverSocket = new ServerSocket(8801);
    while (true) {
      try {
        //接受socket连接
        final Socket socket = serverSocket.accept();
        executorService.execute(() -> server(socket));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  public static void server(Socket socket) {
    try {
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
      printWriter.println("HTTP/1.1 200 OK");
      printWriter.println("Content-Type:text/html;charset=utf-8");
      String body = "hello,i am http-01";
      printWriter.println("Content-Length:" + body.getBytes().length);
      printWriter.println();
      printWriter.write(body);
      printWriter.close();
      socket.close();
    } catch (IOException e) {
      throw new RuntimeException("网络异常", e);
    }
  }


}
