package otus.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import otus.common.Serializers;
import otus.controllers.MessageController;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.messagesystem.MsClient;
import otus.model.User;

import java.util.*;


public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

    private final Map<UUID, Message> consumerMap = new HashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    @Autowired
    MessageController messageController;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void addUser(User user) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, user, MessageType.USER_DATA);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void viewUser() {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, "viewUser", MessageType.SYSTEM_MESSAGE);
        msClient.sendMessage(outMsg);
    }

    public void addAnswer(UUID uuid, Message msg) {
        if (msg.getType()==MessageType.USER_DATA.getValue()){
            messageController.addUserResponse(Serializers.deserialize(msg.getPayload(), String.class));

        }if (msg.getType()==MessageType.SYSTEM_MESSAGE.getValue()){
            messageController.viewUserResponse(Serializers.deserialize(msg.getPayload(), ArrayList.class));}
        consumerMap.put(uuid, msg);
        logger.info("Add in Map key:" + uuid + " value" + msg);
    }

}
