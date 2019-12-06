package otus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.dao.DAOUser;
import otus.dao.handlers.saveUserDataRequestHandler;
import otus.front.FrontendService;
import otus.front.FrontendServiceImpl;
import otus.front.handlers.GetUserDataResponseHandler;
import otus.messagesystem.*;

@Configuration
public class MessageSystemConfig {

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
    private final DAOUser dbService;


    @Autowired
    public MessageSystemConfig (DAOUser dbService) {
        this.dbService = dbService;
    }

    @Bean
    public MessageSystem messageSystem(){
        return new MessageSystemImpl();
    }

    @Bean
    public FrontendService frontendService (){

        MsClient databaseClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem());
        databaseClient.addHandler(MessageType.USER_DATA, new saveUserDataRequestHandler(dbService));
        messageSystem().addClient(databaseClient);

        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem());

        FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);

        frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
        messageSystem().addClient(frontendMsClient);
        return frontendService;
    }
}
