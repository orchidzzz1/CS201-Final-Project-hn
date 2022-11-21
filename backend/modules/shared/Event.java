package backend.modules.shared;

import java.security.Timestamp;
import java.util.ArrayList;

public class Event {
    String name;
    String description;
    ArrayList<String> activityType;
    Timestamp postedDateTime;
    Timestamp eventDateTime;
    String eventLocation;
    boolean expired;
    String createdUserName;
}
