package com.diegodev.apidesportes.jogos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
        String logoa = itemJogos.getLogoA();
        String logob = itemJogos.getLogoB();


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
        if (logoa == null || logoa.trim().isEmpty()) {
            // String vazia → usa drawable padrão
            holder.TeamA.setImageResource(R.drawable.ic_launcher_foreground);
        } else if (logoa.startsWith("http")) {
            // URL → Glide carrega direto
            Glide.with(context)
                    .load(logoa)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(android.R.drawable.stat_notify_error)
                    .into(holder.TeamA);
        } else {
            // Base64 → converte para Bitmap
            Bitmap bitmapA = base64ToBitmap(logoa);
            if (bitmapA != null) {
                Glide.with(context)
                        .load(bitmapA)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(android.R.drawable.stat_notify_error)
                        .into(holder.TeamA);
            } else {
                // Se falhar a conversão, usa drawable padrão
                holder.TeamA.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }





        if (logob == null || logob.trim().isEmpty()) {
            // String vazia → usa drawable padrão
            holder.TeamB.setImageResource(R.drawable.ic_launcher_foreground);
        } else if (logob.startsWith("http")) {
            // URL → Glide carrega direto
            Glide.with(context)
                    .load(logob)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(android.R.drawable.stat_notify_error)
                    .into(holder.TeamB);
        } else {
            // Base64 → converte para Bitmap
            Bitmap bitmapB = base64ToBitmap(logob);
            if (bitmapB != null) {
                Glide.with(context)
                        .load(bitmapB)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(android.R.drawable.stat_notify_error)
                        .into(holder.TeamB);
            } else {
                // Se falhar a conversão, usa drawable padrão
                holder.TeamB.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }


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

    private Bitmap base64ToBitmap(String base64String) {
        try {
            if (base64String == null) {
                Log.e("Base64Decode", "A string recebida é nula.");
                return null;
            }

            if (!base64String.startsWith("data:image")) {
                Log.e("Base64Decode", "A string não começa com 'data:image'. Valor: " + base64String);
                return null;
            }

            Log.d("Base64Decode", "Iniciando decodificação da imagem base64...");

            // Remove o prefixo "data:image/png;base64,"
            String base64Data = base64String.substring(base64String.indexOf(",") + 1);

            byte[] decodedBytes = Base64.decode(base64Data, Base64.DEFAULT);

            Log.d("Base64Decode", "Decodificação concluída. Tamanho do byte array: " + decodedBytes.length);

            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            if (bitmap != null) {
                Log.d("Base64Decode", "Bitmap criado com sucesso. Dimensões: " + bitmap.getWidth() + "x" + bitmap.getHeight());
            } else {
                Log.e("Base64Decode", "Falha ao converter os bytes em Bitmap.");
            }

            return bitmap;

        } catch (Exception e) {
            Log.e("Base64Decode", "Erro ao converter Base64 em Bitmap: " + e.getMessage(), e);
            return null;
        }
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
