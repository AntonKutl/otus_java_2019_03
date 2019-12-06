package otus.front;


import otus.model.User;

import java.util.List;
import java.util.UUID;

public interface FrontendService {
  void addUser(User user);
  public List<User> viewUser();
  public void addAnswer(UUID uuid, List<User> list);
}

