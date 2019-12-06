package otus.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.messagesystem.Message;
import otus.messagesystem.MessageType;
import otus.messagesystem.MsClient;
import otus.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

    private final Map<UUID, List<User>> consumerMap = new HashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

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
    public List<User> viewUser() {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, "viewUser", MessageType.USER_DATA);
        msClient.sendMessage(outMsg);
        while (consumerMap.get(outMsg.getId()) == null) {
        }
        return consumerMap.get(outMsg.getId());
    }

    public void addAnswer(UUID uuid, List<User> list) {
        consumerMap.put(uuid, list);
        logger.info("Add in Map key:" + uuid + " value" + list);
    }

}
