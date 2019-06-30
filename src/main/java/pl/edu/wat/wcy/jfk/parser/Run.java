package pl.edu.wat.wcy.jfk.parser;

import java.util.Scanner;


class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parser;
        Expression e;
        String input;
        Memory m = new Memory();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            } else {
                parser = new Parser(input, m);
                try {
                    e = parser.parse_Input();
                    if (e != null) {
                        System.out.println(e.eval(m));
                    }
                } catch (NotParsed | NotExist n) {
                    System.out.println(n.getMessage());
                }
            }
        }
        sc.close();
    }
}
