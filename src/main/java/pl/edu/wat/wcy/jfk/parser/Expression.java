package pl.edu.wat.wcy.jfk.parser;

public interface Expression {
    double eval(Memory m) throws NotExist;
}
