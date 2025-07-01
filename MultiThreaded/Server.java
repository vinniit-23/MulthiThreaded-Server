import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket>getConsumer(){

        return (socket) -> {
            try {
                PrintWriter toClient = new PrintWriter(socket.getOutputStream());
                toClient.println("Hello from the server");
                toClient.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server();
         int port = 8010;

             ServerSocket serverSocket = new ServerSocket(port);
             serverSocket.setSoTimeout(10000);
                System.out.println("Server is listening on port " + port);
                while (true)
                {

                        Socket acceptedConnection = serverSocket.accept();
                        Thread thread = new Thread(()->server.getConsumer().accept(acceptedConnection));
                        System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                }
    }
}
