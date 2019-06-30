package pl.edu.wat.wcy.jfk.parser;

public class Constant implements Expression {
    private final double value;

    public Constant(double v) {
        this.value = v;
    }

    public double eval(Memory m) {
        return value;
    }
}
