package otus.messagesystem;

import ch.qos.logback.core.boolex.EvaluationException;

import javax.print.DocFlavor;

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
