package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.security.Timestamp;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * Model for event
 */
public class Event {
    public String name;
    public String description;
    public ArrayList<String> activityType;
    public Timestamp postedDateTime;
    public Timestamp eventDateTime;
    public String eventLocation;
    public boolean expired;
    public String createdUserName;
}
