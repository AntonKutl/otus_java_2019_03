package otus.messagesystemserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.dao.handlers.ReadAllUsersDataRequestHandler;
import otus.dao.handlers.SaveUserDataRequestHandler;
import otus.front.handlers.GetAnswerDataResponseHandler;
import otus.front.handlers.GetListUserDataResponseHandler;
import otus.messagesystem.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainMessageSystem {
    private static Logger logger = LoggerFactory.getLogger(MainMessageSystem.class);
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
    private static MsClient databaseClient;
    private static MsClient frontendMsClient;
    public static void main(String[] args) {
        MessageSystem messageSystem = new MessageSystemImpl();

        databaseClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseClient.addHandler(MessageType.USER_DATA, new SaveUserDataRequestHandler());
        databaseClient.addHandler(MessageType.SYSTEM_MESSAGE, new ReadAllUsersDataRequestHandler());
        messageSystem.addClient(databaseClient);

        frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);

        frontendMsClient.addHandler(MessageType.USER_DATA, new GetAnswerDataResponseHandler());
        frontendMsClient.addHandler(MessageType.SYSTEM_MESSAGE,new GetListUserDataResponseHandler());
        messageSystem.addClient(frontendMsClient);

        MessageSystemServer messageSystemServer=new MessageSystemServer(databaseClient);
        messageSystemServer.go();

    }
}
