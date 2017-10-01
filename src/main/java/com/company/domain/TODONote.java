package com.company.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Mongo document storing note information
 * Created by Aleksander on 01.10.2017.
 */
@Document(collection = "todo_notes")
public class TODONote {

    @Id
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String content;

    @CreatedDate
    @Getter @Setter
    private LocalDateTime createdDate;

    public TODONote() {}

    public TODONote(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public TODONote update(TODONote updated){
        this.title = updated.getTitle();
        this.content = updated.getContent();

        return this;
    }

    @Override
    public String toString() {
        return "TODONote{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TODONote todoNote = (TODONote) o;

        return id.equals(todoNote.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
