package com.example.gk3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryItem> historyList;

    public HistoryAdapter(List<HistoryItem> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem historyItem = historyList.get(position);
        holder.actionTextView.setText(historyItem.getSelectedAction());
        holder.inputTextView.setText(historyItem.getInput());
        holder.resultTextView.setText(historyItem.getResult());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView actionTextView;
        public TextView inputTextView;
        public TextView resultTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            actionTextView = itemView.findViewById(R.id.actionTextView);
            inputTextView = itemView.findViewById(R.id.inputTextView);
            resultTextView = itemView.findViewById(R.id.resultTextView);
        }

    }
}
