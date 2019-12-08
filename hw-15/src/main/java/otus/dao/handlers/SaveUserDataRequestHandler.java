package otus.dao.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.dao.DAOUser;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.messagesystem.RequestHandler;
import otus.model.User;

import java.util.Optional;


public class SaveUserDataRequestHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(SaveUserDataRequestHandler.class);

    private DAOUser dbService;

    public SaveUserDataRequestHandler(DAOUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        User user = Serializers.deserialize(msg.getPayload(), User.class);
        dbService.save(user);
        logger.info("User save in database");
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.USER_DATA.getValue(), Serializers.serialize("Пользователь добавлен")));
    }
}
