package com.diegodev.apidesportes.jogos.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.diegodev.apidesportes.R;

@SuppressLint("AppCompatCustomView")
public class RotatingImageView extends ImageView {

    private RotateAnimation rotate;

    public RotatingImageView(Context context) {
        super(context);
        init();
    }

    public RotatingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotatingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // Método de inicialização
    private void init() {
        // Definir o background
        setBackgroundResource(R.drawable.ic_loading_7);

        // Criar animação de rotação
        rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1700); // Duração da rotação
        rotate.setInterpolator(new LinearInterpolator()); // Rodar suavemente
        rotate.setRepeatCount(Animation.INFINITE); // Repetir infinitamente

        // Iniciar a animação
        startAnimation(rotate);
    }

    // Quando a visibilidade mudar, parar ou reiniciar a animação
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            startAnimation(rotate); // Reinicia a animação se a view estiver visível
        } else {
            clearAnimation(); // Para a animação se a view ficar invisível ou gone
        }
    }
}
