import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/15 下午9:31
 * @description HttpServerOne : socket 模拟http服务
 */
public class HttpServerOne {


  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8801);
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        server(socket);
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
      String body = "hello,http";
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
