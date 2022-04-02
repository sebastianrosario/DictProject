package dictionary;

public class Name implements Comparable<Name> {
    private String full;
    private String first;
    private String last;

    public Name(String s) {
        full = s;
    }

    public String getValue() {
        return full;
    }

    @Override
    public int compareTo(Name o) {
        return full.compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return full;
    }

    
}
