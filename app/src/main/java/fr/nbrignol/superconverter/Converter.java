package fr.nbrignol.superconverter;

import android.content.Context;

abstract public class Converter {

    protected ConverterListener listener = null;

    abstract public void init(Context context);
    abstract public float getRate();
    abstract public float convert(float value);

    public void setListener(ConverterListener theListener) {
        listener = theListener;
    }
}
