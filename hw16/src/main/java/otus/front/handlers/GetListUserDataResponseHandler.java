package otus.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.messagesystem.Message;
import otus.messagesystem.RequestHandler;
import otus.model.User;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class GetListUserDataResponseHandler implements RequestHandler {
    private static final int PORT = 8081;
    private static final String HOST = "localhost";
    private static final Logger logger = LoggerFactory.getLogger(GetListUserDataResponseHandler.class);

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        try {
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));

            go(Serializers.deserialize(msg.getPayload(), ArrayList.class));

            //frontendService.addAnswer(sourceMessageId,msg);

        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }

    private void go(Object obj) {
        try {
            try (Socket clientSocket = new Socket(HOST, PORT)) {
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(obj);
                out.flush();
            }

        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

}
