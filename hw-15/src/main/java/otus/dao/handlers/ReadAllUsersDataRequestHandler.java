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

public class ReadAllUsersDataRequestHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(SaveUserDataRequestHandler.class);

    private DAOUser dbService;

    public ReadAllUsersDataRequestHandler(DAOUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        List<User> users = dbService.readAll();
        logger.info("Users read in database");
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.SYSTEM_MESSAGE.getValue(), Serializers.serialize(users)));
    }
}
