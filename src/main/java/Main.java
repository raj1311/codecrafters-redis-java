import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");


        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 6379;
        try {
          serverSocket = new ServerSocket(port);
          // Since the tester restarts your program quite often, setting SO_REUSEADDR
          // ensures that we don't run into 'Address already in use' errors
          serverSocket.setReuseAddress(true);
            clientSocket = serverSocket.accept();
            // Wait for connection from client.
            process(clientSocket);
        } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
        } finally {
          try {
            if (clientSocket != null) {
              clientSocket.close();
            }
          } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
          }
        }
  }

    private static void process(Socket clientSocket) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("PING")) {
                    clientSocket.getOutputStream().write("+PONG\r\n".getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
