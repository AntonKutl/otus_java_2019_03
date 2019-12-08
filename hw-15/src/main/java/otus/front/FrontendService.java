package otus.front;


import otus.messagesystem.Message;
import otus.model.User;

import java.util.List;
import java.util.UUID;

public interface FrontendService {
  String addUser(User user);
  public List<User> viewUser();
  public void addAnswer(UUID uuid, Message msg);
}

