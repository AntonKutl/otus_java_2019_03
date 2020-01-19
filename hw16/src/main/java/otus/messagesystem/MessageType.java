package otus.messagesystem;

public enum MessageType {
  USER_DATA("UserData"),SYSTEM_MESSAGE("SystemMessage");


  private final String value;

  MessageType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
