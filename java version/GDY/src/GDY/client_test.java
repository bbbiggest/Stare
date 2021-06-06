package GDY;
import java.io.*;
import java.net.Socket;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class client_test {
    public static void main(String []args)
    {
        try(Socket s = new Socket("localhost",2323)){
            System.out.println("hhhhhh,接上了");
        }catch (IOException e)
        {
            System.out.println("error");
        }
    }
}
