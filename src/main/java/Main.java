import command.RedisCommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args){
      System.out.println("Logs from your program will appear here!");
      int threadPoolSize = 5;

      ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        ServerSocket serverSocket = null;
        int port = 6379;
        try {
          serverSocket = new ServerSocket(port);
          // Since the tester restarts your program quite often, setting SO_REUSEADDR
          // ensures that we don't run into 'Address already in use' errors
          serverSocket.setReuseAddress(true);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(() -> process(clientSocket));
            }
            // Wait for connection from client.
        } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
        }
  }

    private static void process(Socket clientSocket) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String response = RedisCommandParser.parseAndExecute(line);
                clientSocket.getOutputStream().write(response.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
