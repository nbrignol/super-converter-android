package fr.nbrignol.superconverter;

public interface ConverterListener {
    public void onRateUpdated(float currentRate);
    public void onError(String message);
}
