package com.diegodev.apidesportes.jogos.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.diegodev.apidesportes.R;


public class ExpiredDialogFragment extends DialogFragment {


    private TextView errocode;

    private ImageView qrcode1,qrcode2;
    private ImageButton buttonYes,buttonSair;
    public static String type_Expired = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.api_expired, container, false);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialog);


        buttonSair = view.findViewById(R.id.buttonSair);
        errocode = view.findViewById(R.id.errocode);


        if(!type_Expired.isEmpty()){
            errocode.setVisibility(View.VISIBLE);
            errocode.setText("Erro: "+type_Expired);
        }

        buttonSair.setOnClickListener(v -> {

            System.exit(0);
        });

        buttonSair.requestFocus();


        return view;
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnKeyListener((dialogInterface, keyCode, event) -> {
            // Bloqueia o botão voltar
            return keyCode == KeyEvent.KEYCODE_BACK;
        });

        // Impede o fechamento ao clicar fora
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }



    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            // Define tamanho personalizado
            getDialog().getWindow().setLayout(1200, 800); // px ou use dimens
            Window window = getDialog().getWindow();

            // Define o tamanho do dialog
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // Remove o fundo acinzentado/dim
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }
}

