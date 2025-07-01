
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run()throws Exception{
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket   socket = new Socket(address,port);
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toSocket.println("Hello from the client " );
        String line = fromSocket.readLine();
        System.out.println("Response from the socket is :"+line);
        socket.close();
        toSocket.close();
        fromSocket.close();
        System.out.println("Client is done");
        
    }

    public static void main(String[] args)throws Exception {
        Client client = new Client();
        client.run();

    }
}
