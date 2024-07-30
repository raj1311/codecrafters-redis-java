import command.Command;
import command.DataType;
import command.RedisCommandParser;
import model.CommandData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
                try {
                    String response = executor.submit(() -> process(clientSocket)).get();
                    System.out.println(response);
                    clientSocket.getOutputStream().write(response.getBytes());
                }
                catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        if (clientSocket != null) {
                            System.out.println("Closing connection");
                            clientSocket.close();
                        }
                    } catch (IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                    }
                }
            }
            // Wait for connection from client.
        } catch (Exception e) {
          System.out.println("IOException: " + e.getMessage());
        }

  }

    private static String process(Socket clientSocket) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))) {
            String line = null;
            CommandData commandData = new CommandData();
            while ((line = br.readLine()) != null) {
                RedisCommandParser.findDataTypeAndSetInfo(line,commandData);
            }
            System.out.println("Command Data : " + commandData);
            return RedisCommandParser.parseAndExecute(commandData);
        } catch (Exception e) {
            System.out.println("Got exception: " + e.getMessage());
        }
        return "";
    }
}
