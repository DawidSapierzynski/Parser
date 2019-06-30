package pl.edu.wat.wcy.jfk.parser;

class Parser {
    private final String input;
    private int position;
    private final Memory m;

    public Parser(String input, Memory memory) {
        this.input = input + "$";
        this.position = 0;
        this.m = memory;
    }

    private void skip_whitespace() {
        while (Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    }

    private char look_ahead() {
        skip_whitespace();
        return input.charAt(position);
    }

    public Expression parse_Input() throws NotParsed, NotExist {
        boolean assign = false;

        if (look_ahead() != '$') {
            while (look_ahead() != '$') {
                if (look_ahead() == '=') {
                    assign = true;
                }
                position++;
            }

            position = 0;
            if (assign) {
                parse_Assign();
            } else {
                return parse_Expression();
            }
        }

        return null;
    }

    private void parse_Assign() throws NotParsed, NotExist {
        Expression v;

        if (Character.isLetter(look_ahead())) {
            v = parse_Variable();
        } else {
            throw new NotParsed("Error");
        }
        if (look_ahead() == '=') {
            position++;
            Expression e = parse_Expression();
            Assign a = new Assign(((Variable) v).getName(), e);
            a.save(m);
        }
    }

    private Expression parse_Expression() throws NotParsed {
        Expression e;

        e = parse_sum();

        if (look_ahead() == '$') {
            return e;
        } else {
            throw new NotParsed("Error");
        }
    }

    private Expression parse_sum() throws NotParsed {
        Expression l, r;
        l = parse_mult();
        char c = look_ahead();

        while (c == '+' || c == '-') {
            position++;
            r = parse_mult();
            l = new Binary_operator(c, l, r);
            c = look_ahead();
        }
        return l;
    }

    private Expression parse_mult() throws NotParsed {
        Expression l, r;
        l = parse_term();
        char c = look_ahead();

        while (c == '*' || c == '/' || c == '%') {
            position++;
            r = parse_term();
            l = new Binary_operator(c, l, r);
            c = look_ahead();
        }

        return l;
    }

    private Expression parse_term() throws NotParsed {
        char c = look_ahead();
        if (c == '(')
            return parse_paren();
        else if (Character.isDigit(c))
            return parse_Constant();
        else if (Character.isLetter(c))
            return parse_Variable();
        else {
            throw new NotParsed("Error");
        }
    }

    private Expression parse_Constant() throws NotParsed {
        double n = 0;
        while (Character.isDigit(input.charAt(position))) {
            n *= 10;
            n += input.charAt(position) - '0';
            position++;
        }
        if (look_ahead() == '.') {
            int k = 1;
            position++;
            if (Character.isDigit(input.charAt(position))) {
                while (Character.isDigit(input.charAt(position))) {
                    k *= 10;
                    n += (double) (input.charAt(position) - '0') / k;
                    position++;
                }
            } else {
                throw new NotParsed("Error");
            }
        }

        return new Constant(n);
    }

    private Expression parse_Variable() {
        String s = "";
        while (Character.isLetterOrDigit(input.charAt(position))) {
            s = s.concat(String.valueOf(input.charAt(position)));
            position++;
        }
        return new Variable(s);
    }

    private Expression parse_paren() throws NotParsed {
        position++;
        Expression e = parse_sum();
        if (look_ahead() == ')') {
            position++;
            return e;
        } else {
            throw new NotParsed("Error");
        }
    }
}
