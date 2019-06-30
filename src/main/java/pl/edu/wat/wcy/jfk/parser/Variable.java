package pl.edu.wat.wcy.jfk.parser;

public class Variable implements Expression {
    private final String name;

    public Variable(String s) {
        this.name = s;
    }

    public double eval(Memory m) throws NotExist {
        if (m.containsKey(this.name)) {
            return m.get(name);
        } else {
            throw new NotExist("???");
        }
    }

    public String getName() {
        return this.name;
    }
}
