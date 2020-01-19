package otus.dao.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.messagesystem.RequestHandler;
import otus.model.User;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;


public class SaveUserDataRequestHandler implements RequestHandler {
    private static final int PORT = 8100;
    private static final String HOST = "localhost";

    private static final Logger logger = LoggerFactory.getLogger(SaveUserDataRequestHandler.class);


    @Override
    public Optional<Message> handle(Message msg) {
        User user = Serializers.deserialize(msg.getPayload(), User.class);
        try {
            go(user);
            logger.info("User save in database");
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.USER_DATA.getValue(), Serializers.serialize("Пользователь сохранен")));
        }catch (Exception exp){
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.USER_DATA.getValue(), Serializers.serialize("Ошибка при сохранение пользователя")));

        }

    }

    private void go(User outUser) {
        try {
            try (Socket clientSocket = new Socket(HOST, PORT)) {
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(outUser);
                out.flush();
                logger.info("stop communication");
            }

        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }
}
