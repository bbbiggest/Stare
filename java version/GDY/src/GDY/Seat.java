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

    public Seat(int ID, Socket incomingSocket) throws IOException {
        this.ID = ID;
        this.incoming = incomingSocket;
        this.pWriter = new PrintWriter(incoming.getOutputStream(), true);
        this.bReader = new BufferedReader(new InputStreamReader(incoming.getInputStream(), StandardCharsets.UTF_8));
//        if (ID == 0) {
//            name = Main.start.getname();
//        }
    }

//    public void sitDown(Socket mySocket) throws IOException {
//        this.mySocket = mySocket;
//        this.pWriter = new PrintWriter(mySocket.getOutputStream(), true);
//        this.bReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream(), StandardCharsets.UTF_8));
//        this.name = read();
//        send(Integer.toString(ID));
//        System.out.println("玩家 " + this.ID);
//    }

    public void run()
    {
        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream();
             var in = new Scanner(inStream, StandardCharsets.UTF_8);
             var out = new PrintWriter(
                     new OutputStreamWriter(outStream, StandardCharsets.UTF_8),
                     true /* autoFlush */))
        {
//            if (ID != 0)
                this.name = in.next();
            System.out.println(ID + "-" + name);
            out.println(this.ID);

            // echo client input
            var done = false;
            while (!done && in.hasNextLine())
            {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE"))
                    done = true;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        pWriter.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public String read() throws IOException {
        return bReader.readLine();
    }
}
