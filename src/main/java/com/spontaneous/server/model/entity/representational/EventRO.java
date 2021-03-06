package com.spontaneous.server.model.entity.representational;

import com.spontaneous.server.model.entity.Event;
import com.spontaneous.server.model.entity.Guest;
import com.spontaneous.server.model.entity.User;
import com.spontaneous.server.util.GsonFactory;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a representational object for the {@link Event} entity.
 */
public class EventRO {

    private final long id;

    private final UserProfileRO host;
    private final List<GuestRO> guests;
    private final String title;
    private final String description;
    private final DateTime date;
    private final String location;

    public EventRO(long id, User host, String title, String description, DateTime date, String location, List<Guest> guests) {
        this.id = id;
        this.host = host.createUserProfile();
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;

        //In case that there are no guests.
        if (guests == null) {
            this.guests = new ArrayList<>();
            return;
        }

        this.guests = new ArrayList<>(guests.size());
        this.guests.addAll(guests.stream()
                .map(Guest::createRepresentationalObject)
                .collect(Collectors.toList()));
    }

    public long getId() {
        return id;
    }

    public UserProfileRO getHost() {
        return host;
    }

    public List<GuestRO> getGuests() {
        return guests;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return GsonFactory.getGson()
                .toJson(this);
    }
}
