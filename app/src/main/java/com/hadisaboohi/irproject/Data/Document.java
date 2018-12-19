package com.hadisaboohi.irproject.Data;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("id"), @Index("title"), @Index("body")})
public class Document {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String title;
    private String body;

    public Document(String name, String title, String body) {
        this.name = name;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return name + " " + title + " " + body;
    }
}
