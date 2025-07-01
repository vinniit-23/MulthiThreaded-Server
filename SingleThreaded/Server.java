import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws Exception {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000);

        while (true) {
            System.out.println("Server is listening on port " + port);

            try (Socket acceptedConnection = socket.accept()) {
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());

                // Initialize streams
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true); // auto-flush
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                // Read client message
                String line = fromClient.readLine();
                System.out.println("Message from client: " + line);

                // Send response
                toClient.println("Hello from the server");
                System.out.println("Response sent to the client");

                // Streams & socket will auto-close due to try-with-resources
            } catch (Exception e) {
                System.err.println("Error handling client: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.run();
    }
}