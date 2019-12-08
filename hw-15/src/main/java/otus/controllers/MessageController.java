package otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import otus.front.FrontendService;
import otus.model.User;

import java.util.List;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    private FrontendService frontendService;

    @MessageMapping("/addUser")
    @SendTo("/topic/response/addUser")
    public String addUser(User user) {
        String answer=frontendService.addUser(user);
        logger.info("Ответ пользователю "+answer);
        return answer;
    }

    @MessageMapping("/viewUser")
    @SendTo("/topic/response/viewUser")
    public List <User>  viewUser() {
        List<User> users = frontendService.viewUser();
        return  users;
    }
}
