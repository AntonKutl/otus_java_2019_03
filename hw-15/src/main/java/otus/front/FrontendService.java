package otus.front;


import otus.messagesystem.Message;
import otus.model.User;

import java.util.List;
import java.util.UUID;

public interface FrontendService {
  void addUser(User user);
  void viewUser();
  void addAnswer(UUID uuid, Message msg);
}

