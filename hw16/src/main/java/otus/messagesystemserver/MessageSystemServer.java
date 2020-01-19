package otus.messagesystemserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.messagesystem.Message;
import otus.messagesystem.MsClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageSystemServer {

    private  MsClient msClient;
    private static final int PORT = 8090;
    private static Logger logger = LoggerFactory.getLogger(MessageSystemServer.class);

    public MessageSystemServer(MsClient msClient) {
        this.msClient = msClient;
    }

    public   void go() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (!Thread.currentThread().isInterrupted()) {
                logger.info("waiting for client connection");
                try (Socket clientSocket = serverSocket.accept()) {
                    clientHandler(clientSocket);
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

    private  void clientHandler(Socket clientSocket) {
        try (ObjectInputStream in=new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            Message msg=(Message) in.readObject();
            logger.info("Message from frontend accepted: "+msg.toString());
            msClient.sendMessage(msg);
            //out.writeObject();
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

}
