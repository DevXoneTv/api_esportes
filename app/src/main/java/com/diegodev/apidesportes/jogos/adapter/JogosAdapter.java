package com.diegodev.apidesportes.jogos.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.diegodev.apidesportes.R;
import com.diegodev.apidesportes.jogos.item.ItemJogos;

import java.util.List;

public class JogosAdapter extends RecyclerView.Adapter<JogosAdapter.ViewHolder> {

    private static final String TAG = "AdapterJogos";
    private List<ItemJogos> list;
    private Context context;

    public JogosAdapter(Context context, List<ItemJogos> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.api_item_jogos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemJogos itemJogos = list.get(position);

        String descricao = itemJogos.getDescription();
        int gola = itemJogos.getGolsA();
        int golb = itemJogos.getGolsB();


        if (descricao.equals("Not started")){

            holder.txtPlacar.setVisibility(View.INVISIBLE);
            holder.imgvs.setVisibility(View.VISIBLE);
            holder.txtdescricao.setText("Em breve");

        } else if (descricao.equals("Ended")){

            holder.txtPlacar.setVisibility(View.VISIBLE);
            holder.imgvs.setVisibility(View.INVISIBLE);
            holder.txtPlacar.setText(gola+"-"+golb);
            holder.txtdescricao.setText("Encerrado");

        }else if (descricao.equals("Postponed")){

         holder.txtPlacar.setVisibility(View.INVISIBLE);
         holder.imgvs.setVisibility(View.VISIBLE);
         holder.txtdescricao.setText("Jogo Adiado");

        }else if (descricao.equals("AP")){

            holder.txtPlacar.setVisibility(View.VISIBLE);
            holder.imgvs.setVisibility(View.INVISIBLE);
            holder.txtPlacar.setText(gola+"-"+golb);
            holder.txtdescricao.setText("Enc. Agregado");

        }else if (descricao.equals("Halftime")){

            holder.txtPlacar.setVisibility(View.VISIBLE);
            holder.imgvs.setVisibility(View.INVISIBLE);
            holder.txtPlacar.setText(gola+"-"+golb);
            holder.txtdescricao.setText("Intervalo");

        }else if (descricao.equals("1st half") || descricao.equals("2nd half") ){

            holder.txtPlacar.setVisibility(View.VISIBLE);
            holder.imgvs.setVisibility(View.INVISIBLE);
            holder.txtPlacar.setText(gola+"-"+golb);
            holder.txtdescricao.setText("Ao Vivo");

        }else{

            holder.txtPlacar.setVisibility(View.INVISIBLE);
            holder.imgvs.setVisibility(View.VISIBLE);
            holder.txtdescricao.setText("Em breve");

        }


        // Carregar imagens dos times
        Glide.with(context)
                .load(itemJogos.getLogoA())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.TeamA);

        Glide.with(context)
                .load(itemJogos.getLogoB())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.TeamB);

        Glide.with(context)
                .load(itemJogos.getLogoCamp())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.logocamp);

        // Exibir textos
        holder.TimeA.setText(itemJogos.getTimeA());
        holder.TimeB.setText(itemJogos.getTimeB());
        holder.txtTime.setText(itemJogos.getStart());
        holder.campname.setText(itemJogos.getCampName());


        // Logs para depuração
        Log.d(TAG, "Logo Time A: " + itemJogos.getLogoA());
        Log.d(TAG, "Logo Time B: " + itemJogos.getLogoB());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView TeamA, TeamB,imgvs,logocamp;
        TextView txtTime, TimeA, TimeB,txtdescricao,txtPlacar,campname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TeamA = itemView.findViewById(R.id.iv_iconA);
            TeamB = itemView.findViewById(R.id.iv_iconB);
            txtTime = itemView.findViewById(R.id.tv_time);
            TimeA = itemView.findViewById(R.id.tv_name1);
            TimeB = itemView.findViewById(R.id.tv_name2);
            imgvs = itemView.findViewById(R.id.imgvs);
            txtdescricao = itemView.findViewById(R.id.txtdescricao);
            txtPlacar = itemView.findViewById(R.id.txtPlacar);
            logocamp = itemView.findViewById(R.id.iv_iconCamp);
            campname = itemView.findViewById(R.id.tv_nameCamp);

        }
    }
}
