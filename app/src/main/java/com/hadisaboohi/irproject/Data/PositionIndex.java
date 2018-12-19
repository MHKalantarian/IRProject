package com.hadisaboohi.irproject.Data;

import java.util.ArrayList;

public class PositionIndex {
    private int documentId;
    private ArrayList<Integer> positions;

    public PositionIndex(int documentId, ArrayList<Integer> positions) {
        this.documentId = documentId;
        this.positions = positions;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }
}
