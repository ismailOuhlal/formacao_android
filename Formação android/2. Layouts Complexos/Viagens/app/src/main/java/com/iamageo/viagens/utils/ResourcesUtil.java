package com.iamageo.viagens.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.iamageo.viagens.model.Pacote;

public class ResourcesUtil {

    public static final String DRAWABLE = "drawable";

    public static Drawable getDrawable(Context context, String drawbleEmTexto) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(drawbleEmTexto, DRAWABLE, context.getPackageName());
        return resources.getDrawable(id);
    }

}
