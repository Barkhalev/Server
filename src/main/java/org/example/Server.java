package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;

public class Server {

    private static final int PORT = 8889;

    public static void main(String[] args) {
        String city = null;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
                {
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");
                    } else {
                        out.println(city);
                        String newCity = in.readLine();
                        if (city.charAt(city.length() - 1) == newCity.charAt(0)) {
                            city = newCity;
                            out.println("OK");
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
