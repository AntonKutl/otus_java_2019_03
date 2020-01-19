package otus.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import otus.common.Serializers;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.model.TextMessage;
import otus.model.User;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);
    private static final int PORT = 8090;
    private static final String HOST = "localhost";
    private final String nameMsClient;
    private final String databaseServiceClientName;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public FrontendServiceImpl(String nameMsClient, String databaseServiceClientName) {
        this.nameMsClient=nameMsClient;
        this.databaseServiceClientName = databaseServiceClientName;
        new Thread(() -> {
            new FrontendServer(this).go();
        }).start();
    }

    @Override
    public void addUser(User user) {
        Message outMsg=new Message(nameMsClient,databaseServiceClientName,null, MessageType.USER_DATA.getValue(), Serializers.serialize(user));
        go(outMsg);
    }

    @Override
    public void viewUser() {

        Message outMsg=new Message(nameMsClient,databaseServiceClientName,null, MessageType.SYSTEM_MESSAGE.getValue(), Serializers.serialize("viewUser"));
        go(outMsg);

    }

    private void go(Message outMessage) {
        try {
            try (Socket clientSocket = new Socket(HOST, PORT)) {
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(outMessage);
                out.flush();
            }
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }








    public void addAnswer( Object object) {
/*        if (msg.getType() == MessageType.USER_DATA.getValue()) {
            messagingTemplate.convertAndSend("/topic/response/addUser", new TextMessage(Serializers.deserialize(msg.getPayload(), String.class)));

        }*/
        //if (msg.getType() == MessageType.SYSTEM_MESSAGE.getValue()) {
            messagingTemplate.convertAndSend("/topic/response/viewUser", object);
            //logger.info("Add in Map key:" + uuid + " value" + msg);
        //}

    }

}
