package otus.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
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

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public String addUser(User user) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, user, MessageType.USER_DATA);
        msClient.sendMessage(outMsg);
        while (consumerMap.get(outMsg.getId()) == null) {
        }
        return Serializers.deserialize(consumerMap.get(outMsg.getId()).getPayload(), String.class);
    }

    @Override
    public List<User> viewUser() {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, "viewUser", MessageType.SYSTEM_MESSAGE);
        msClient.sendMessage(outMsg);
        while (consumerMap.get(outMsg.getId()) == null) {
        }
        return Serializers.deserialize(consumerMap.get(outMsg.getId()).getPayload(), ArrayList.class);
    }

    public void addAnswer(UUID uuid, Message msg) {
        consumerMap.put(uuid, msg);
        logger.info("Add in Map key:" + uuid + " value" + msg);
    }

}
