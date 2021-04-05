package de.pflugradts.guicedemo.constructor;

public class ClassConstructor implements InterfaceConstructor {

    private final String s;
    private final int i;
    private final boolean b;

    public ClassConstructor(String s, int i, boolean b) {
        this.s = s;
        this.i = i;
        this.b = b;
    }

    @Override
    public String getS() {
        return s;
    }

    @Override
    public int getI() {
        return i;
    }

    public boolean isB() {
        return b;
    }

    @Override
    public String toString() {
        return "ClassConstructor{" +
            "s='" + s + '\'' +
            ", i=" + i +
            ", b=" + b +
            '}';
    }

}
