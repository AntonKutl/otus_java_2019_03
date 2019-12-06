package otus.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.common.Serializers;
import otus.front.FrontendService;
import otus.messagesystem.Message;
import otus.messagesystem.RequestHandler;
import otus.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GetUserDataResponseHandler implements RequestHandler {
  private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

  private final FrontendService frontendService;

  public GetUserDataResponseHandler(FrontendService frontendService) {
    this.frontendService = frontendService;
  }

  @Override
  public Optional<Message> handle(Message msg) {
    logger.info("new message:{}", msg);
    try {

      Object object = Serializers.deserialize(msg.getPayload(), Object.class);
      if (object.getClass().equals(String.class)) {
        logger.info(object.toString());
      } else {
        List<User> list = Serializers.deserialize(msg.getPayload(), ArrayList.class);
        UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
        frontendService.addAnswer(sourceMessageId,list);
      }

    } catch (Exception ex) {
      logger.error("msg:" + msg, ex);
    }
    return Optional.empty();
  }
}
