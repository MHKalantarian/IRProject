package com.hadisaboohi.irproject.Data;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("id")})
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int count;
    private String body;
    private ArrayList<PositionIndex> positionIndices;

    public Word(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<PositionIndex> getPositionIndices() {
        return positionIndices;
    }

    public void setPositionIndices(ArrayList<PositionIndex> positionIndices) {
        this.positionIndices = positionIndices;
    }

    public List<Integer> getDocumentIds() {
        List<Integer> documentIds = new ArrayList<>();
        for (PositionIndex positionIndex : positionIndices) {
            documentIds.add(positionIndex.getDocumentId());
        }
        return documentIds;
    }
}
