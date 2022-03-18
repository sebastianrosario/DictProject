package proj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Packet implements Comparable<Packet>{
    private int num;
    private String msg;
    Pattern p = Pattern.compile("^([0-9]+)\s(.+)$");
    public Packet(String s) {
        Matcher m = p.matcher(s);
        if(m.find()) {
            num = Integer.valueOf(m.group(1)); 
            msg = m.group(2);
        }
    }

    public int getNum() {
        return num;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public int compareTo(Packet o) {
        return (num - o.getNum());
    }
}
