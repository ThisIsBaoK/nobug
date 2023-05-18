package application;

import javafx.event.Event;
import javafx.event.EventType;

public class UserEvent extends Event {
  private static final long serialVersionUID = 1L;
  public static final EventType<UserEvent> ANY = new EventType<>(Event.ANY, "ANY");
  public static final EventType<UserEvent> TASK_FORM_SUBMITTED =
      new EventType<>(ANY, "TASK_FORM_SUBMITTED");
  public static final EventType<UserEvent> LOGIN_SUCCEEDED =
      new EventType<>(ANY, "LOGIN_SUCCEEDED");
  public static final EventType<UserEvent> LOGIN_FAILED = new EventType<>(ANY, "LOGIN_FAILED");

  public UserEvent(EventType<? extends Event> eventType) {
    super(eventType);
  }
}
