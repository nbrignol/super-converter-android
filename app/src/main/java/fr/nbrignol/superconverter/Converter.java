package fr.nbrignol.superconverter;

import android.content.Context;

abstract public class Converter {
    abstract public void init(Context context);
    abstract public float getRate();
    abstract public float convert(float value);
}
