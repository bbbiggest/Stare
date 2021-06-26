package GDY;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Seat implements Runnable {
    private int ID;
    private String name;
    private Socket incoming;
    private PrintWriter pWriter;
    private BufferedReader bReader;
    private Scanner in;
    private PrintWriter out;

    public Seat(int ID, Socket incomingSocket) throws IOException {
        this.ID = ID;
        this.incoming = incomingSocket;
        this.pWriter = new PrintWriter(incoming.getOutputStream(), true);
        this.bReader = new BufferedReader(new InputStreamReader(incoming.getInputStream(), StandardCharsets.UTF_8));
        InputStream inStream = incoming.getInputStream();
        OutputStream outStream = incoming.getOutputStream();
        in = new Scanner(inStream, StandardCharsets.UTF_8);
        out = new PrintWriter(
                new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true /* autoFlush */);
    }

    public void run()
    {
//        this.name = in.next();
//        System.out.println(ID + "-" + name);
//        out.println(this.ID);
        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream();
             var in = new Scanner(inStream, StandardCharsets.UTF_8);
             var out = new PrintWriter(
                     new OutputStreamWriter(outStream, StandardCharsets.UTF_8),
                     true /* autoFlush */))
        {
            this.name = in.next();
            System.out.println(ID + "-" + name);
            out.println(this.ID);

            // echo client input
//            while (true) {
//                if (in.hasNextLine()) {
//                    String line = in.nextLine();
//                    System.out.println(line);
//                    if (line.trim().equals("END"))
//                        break;
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("run end");
    }

    public void send(String msg) {
//        out.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
        out.println(msg);
        System.out.println("send: " + msg);
    }

    public String read() throws IOException {
//        return bReader.readLine();
        return in.nextLine();
    }
}
