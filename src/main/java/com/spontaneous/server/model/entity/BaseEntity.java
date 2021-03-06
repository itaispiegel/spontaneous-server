package com.spontaneous.server.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.Expose;
import com.spontaneous.server.util.DateTimeSerializer;
import com.spontaneous.server.util.GsonFactory;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is a Base Entity template.
 * Contains id and creation time fields.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * Id of the entity.
     * The id of the entity does not have a setter, since it's immutable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Expose
    private long id;

    /**
     * When the entity was created.
     * The creation time of the entity does not have a setter, since it's immutable.
     */
    @Column(name = "creation_time", nullable = false, updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @CreatedDate
    @JsonSerialize(using = DateTimeSerializer.class)
    @Expose
    private final DateTime creationTime;

    /**
     * Create a new empty entity.
     */
    BaseEntity() {
        this.creationTime = new DateTime();
    }

    /**
     * @return the id number of the entity.
     */
    public long getId() {
        return id;
    }

    /**
     * @return when the entity was created.
     */
    public DateTime getCreationTime() {
        return creationTime;
    }

    /**
     * @param obj to compare.
     * @return Whether the given entity is equal to this entity.
     */
    @Override
    public boolean equals(Object obj) {
        return !(null == obj || !(obj instanceof BaseEntity))
                && (this == obj || getId() == ((BaseEntity) obj).getId());
    }

    /**
     * @return The entity in JSON representation.
     */
    @Override
    public String toString() {
        return GsonFactory.getGson().toJson(this);
    }
}
