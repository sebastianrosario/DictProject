package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Message {
    private SortedLList<Packet> list = new SortedLList<>();

    public Message(String filename) {
        setMessage(filename);
    }

    private void setMessage(String filename) {
        try {
            File f = new File(filename);
            Scanner fr = new Scanner(f);
            while(fr.hasNextLine()) {
                list.addEntry(new Packet(fr.nextLine())); 
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(Packet s : list) {
            str += s.getMsg();
        }

        return str;
    }
}
