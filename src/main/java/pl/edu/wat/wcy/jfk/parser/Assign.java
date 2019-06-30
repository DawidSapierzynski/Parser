package pl.edu.wat.wcy.jfk.parser;

class Assign {
    private final String var;
    private final Expression expr;

    public Assign(String v, Expression e) {
        this.var = v;
        this.expr = e;
    }

    void save(Memory m) throws NotExist {
        m.put(var, expr.eval(m));

    }
}
