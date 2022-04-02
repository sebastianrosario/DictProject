package dictionary;

public class BusinessName implements Comparable<BusinessName> {
    private String name;

    public BusinessName(String s) {
        this.name = s.split(",")[0];
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(BusinessName o) {
        return this.name.compareTo(o.getName());
    }


}
