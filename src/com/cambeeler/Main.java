package com.cambeeler;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try
        {
            InetAddress    address = InetAddress.getLocalHost();  // .getByName is a good non-local option
            DatagramSocket socket     = new DatagramSocket();
            Scanner        scanner = new Scanner(System.in);
            String echoString;
            do
            {
                System.out.println("Enter String to be echoed");
                echoString = scanner.nextLine();
                byte[] buffer = echoString.getBytes();
                byte[] receiveBuffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                socket.send(packet);
                if (echoString.equalsIgnoreCase("exit"))
                    break;
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String receive = new String(receiveBuffer, 0, receivePacket.getLength());
                System.out.println(receive);
            }while(!echoString.equalsIgnoreCase("exit"));

        }catch (SocketTimeoutException e)
        {
            System.out.println("Socket Exception: " + e.getMessage());
        }catch (IOException f)
        {
            System.out.println("IOException: " + f.getMessage());
        }

    }
}
