package TemperatureConverter;

public class TempConverter extends Subject{
    private double cel;
    private double fah;

    
    public TempConverter() {}

    public TempConverter(double cel, double fah) {
        this.cel = cel;
        this.fah = fah;
    }

    public double c2f() {
        double fah = 1.8 * this.cel + 32;
        this.fah = fah;
        notifyObservers();
        return Math.round(fah * 100.0) / 100.0;
    }
    
    public double f2c() {
        double cel = 0.556 * (this.fah - 32);
        this.cel = cel;
        notifyObservers();
        return Math.round(cel * 100.0) / 100.0;
    }

    public void setCel(double cel) {
        this.cel = cel;
    }

    public void setFah(double fah) {
        this.fah = fah;
    }
    public double getCel() {
        return this.cel+1;
    }

    public double getFah() {
        return this.fah;
    }

}
