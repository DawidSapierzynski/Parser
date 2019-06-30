package pl.edu.wat.wcy.jfk.parser;

import java.util.HashMap;
import java.util.Map;

class Memory {
    private final Map<String, Double> m;

    public Memory() {
        this.m = new HashMap<>();
    }

    public void put(String s, Double d) {
        this.m.put(s, d);
    }

    public double get(String s) {
        return this.m.get(s);
    }

    public boolean containsKey(String s) {
        return this.m.containsKey(s);
    }

    @Override
    public String toString() {
        return m.toString();
    }
}
