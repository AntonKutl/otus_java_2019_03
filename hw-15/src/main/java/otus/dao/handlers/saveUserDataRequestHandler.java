package otus.dao.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.dao.DAOUser;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.messagesystem.RequestHandler;
import otus.model.User;

import java.util.List;
import java.util.Optional;


public class saveUserDataRequestHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(saveUserDataRequestHandler.class);

    private DAOUser dbService;
    List<User> users;

    public saveUserDataRequestHandler(DAOUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        Object object = Serializers.deserialize(msg.getPayload(), Object.class);
        if (object.getClass().equals(String.class)) {
            List<User> users = dbService.readAll();
            logger.info("Users read in database");
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.USER_DATA.getValue(), Serializers.serialize(users)));
        } else {
            User user = Serializers.deserialize(msg.getPayload(), User.class);
            dbService.save(user);
            logger.info("User save in database");
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.USER_DATA.getValue(), Serializers.serialize("Пользователь добавлен")));
        }
    }
}
