package otus.dao.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.dao.DAOUser;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.messagesystem.RequestHandler;
import otus.model.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class ReadAllUsersDataRequestHandler implements RequestHandler {
    private static final int PORT = 8100;
    private static final String HOST = "localhost";
    private static final Logger logger = LoggerFactory.getLogger(SaveUserDataRequestHandler.class);

    private DAOUser dbService;

    public ReadAllUsersDataRequestHandler() {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        List<User> users = go();
        logger.info("Users read in database");
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.SYSTEM_MESSAGE.getValue(), Serializers.serialize(users)));
    }


    private List<User> go() {
        List<User> users = null;
        try {
            try (Socket clientSocket = new Socket(HOST, PORT)) {
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                out.writeObject("ReadAllUsersDataRequestHandler");
                out.flush();
                users = (List<User>) in.readObject();
            }

        } catch (Exception ex) {
            logger.error("error", ex);
        }
        return users;
    }


}
