package com.diegodev.apidesportes.jogos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diegodev.apidesportes.R;
import com.diegodev.apidesportes.jogos.ActivityEsporte;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<String> datas;
    private Context context;
    private ActivityEsporte fragment;

    public DataAdapter(Context context, List<String> datas, ActivityEsporte fragment) {
        this.context = context;
        this.datas = datas;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {


        String dataOriginal = datas.get(position); // Ex: "17/05"
        String dateName;

        if (position == 0) {
            dateName = "Hoje";
            holder.tvData.requestFocus();
            if (fragment != null) {
                fragment.buscarJogosPorData(dataOriginal);
            }
        } else {
            dateName = dataOriginal;
        }

        holder.tvData.setText(dateName);


        holder.tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fragment != null) {
                    fragment.buscarJogosPorData(dataOriginal);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvData;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvData = itemView.findViewById(R.id.tvData);
        }
    }
}
