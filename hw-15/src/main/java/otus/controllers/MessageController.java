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
    public void addUser(User user) {
        frontendService.addUser(user);
    }

    @MessageMapping("/viewUser")
    @SendTo("/topic/response/viewUser")
    public List <User>  viewUser() {
        List<User> users = frontendService.viewUser();
        return  users;
    }
}
