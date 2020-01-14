package otus.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import otus.dbserver.DBServer;
import otus.model.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class FrontendServer {
    private static Logger logger = LoggerFactory.getLogger(DBServer.class);
    private static final int PORT = 8081;
    //private SimpMessagingTemplate messagingTemplate;
    private FrontendServiceImpl frontendService;

    public FrontendServer(FrontendServiceImpl frontendService) {
        this.frontendService = frontendService;
        System.out.println(frontendService);
    }

    public void go() {
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

    private void clientHandler(Socket clientSocket) {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            Object object = in.readObject();
/*            if (msg.getType() == MessageType.USER_DATA.getValue()) {
                messagingTemplate.convertAndSend("/topic/response/addUser", new TextMessage(Serializers.deserialize(msg.getPayload(), String.class)));

            }*/
            if (object.getClass()==ArrayList.class) {
                frontendService.addAnswer(object);
                //System.out.println(frontendService);

                //List<User> list= (List<User>) object;
                //System.out.println(object.toString());
                //messagingTemplate.convertAndSend("/topic/response/viewUser", list);
            }

        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }
}
