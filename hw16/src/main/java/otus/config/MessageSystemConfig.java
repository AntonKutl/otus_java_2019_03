package otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.front.FrontendServer;
import otus.front.FrontendService;
import otus.front.FrontendServiceImpl;

@Configuration
public class MessageSystemConfig {

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean
    public FrontendService frontendService (){
        return new FrontendServiceImpl(FRONTEND_SERVICE_CLIENT_NAME,DATABASE_SERVICE_CLIENT_NAME);
    }
}
