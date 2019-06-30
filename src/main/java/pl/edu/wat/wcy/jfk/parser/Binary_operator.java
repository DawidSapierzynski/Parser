package pl.edu.wat.wcy.jfk.parser;

public class Binary_operator implements Expression {
    private final char symbol;
    private final Expression left;
    private final Expression right;

    public Binary_operator(char s, Expression l, Expression r) {
        this.symbol = s;
        this.left = l;
        this.right = r;
    }

    public double eval(Memory m) throws NotExist {
        if (left != null && right != null) {
            switch (symbol) {
                case '*':
                    return left.eval(m) * right.eval(m);
                case '+':
                    return left.eval(m) + right.eval(m);
                case '-':
                    return left.eval(m) - right.eval(m);
                case '/': {
                    double r = right.eval(m);
                    if (r != 0.0) {
                        return left.eval(m) / r;
                    } else {
                        throw new NotExist("Error");
                    }
                }
                case '%':
                    return left.eval(m) % right.eval(m);
                default:
                    throw new NotExist("Error");
            }
        } else {
            throw new NotExist("Error");
        }
    }
}
