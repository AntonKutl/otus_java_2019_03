package otus.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.messagesystem.Message;
import otus.messagesystem.RequestHandler;


import java.util.Optional;
import java.util.UUID;

public class GetAnswerDataResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetAnswerDataResponseHandler.class);

    public GetAnswerDataResponseHandler() {

    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);

        try {
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            logger.info(Serializers.deserialize(msg.getPayload(), String.class));

            //frontendService.addAnswer(sourceMessageId,msg);
        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }
}
