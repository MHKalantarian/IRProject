package com.hadisaboohi.irproject.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hadisaboohi.irproject.Data.Document;
import com.hadisaboohi.irproject.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by MHK on 18/01/08.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private Context mContext;
    private List<Document> mDataset;

    public DocumentAdapter(Context mContext, List<Document> mDataset) {
        this.mContext = mContext;
        this.mDataset = mDataset;
    }

    @Override
    public DocumentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView;
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_item, parent, false);
        return new DocumentViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final DocumentViewHolder holder, final int position) {
        final Document document = mDataset.get(position);
        holder.titleTv.setText(document.getTitle());
        holder.bodyTv.setText(document.getBody());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class DocumentViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv, bodyTv;

        DocumentViewHolder(View view) {
            super(view);
            titleTv = view.findViewById(R.id.title_tv);
            bodyTv = view.findViewById(R.id.body_tv);
        }
    }
}

