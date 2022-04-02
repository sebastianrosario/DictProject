package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class TDProject {
        private DictionaryInterface <BusinessName, String> phonebook;
	
	public TDProject () {
		phonebook = new SortedArrayDictionary <>();
		//phonebook = new LinkedDictionary <>();
	}
	
	public boolean readFile (String fileName) {
		String phoneNumber;
        BusinessName bName;
        String line;
        String fullName;
        int lastIdx;
        try {
        	Scanner read = new Scanner (new File (fileName));
        	while (read.hasNext()) {
        		line = read.nextLine();
        		if (line.isEmpty())
        			continue;
        		lastIdx = line.lastIndexOf(" ");
        		fullName = line.substring(0, lastIdx).trim();
        		phoneNumber = line.substring(lastIdx).trim();
        		bName = new BusinessName (fullName);
        		phonebook.add(bName, phoneNumber);	
        	}
        }
        	
        catch (FileNotFoundException ex) {
        	System.out.println ("The file with phone numbers not found");
        	return false;
        }
        return true;
	}
	
	public String getPhoneNumber (String name) {
		BusinessName bName = new BusinessName (name);
		return phonebook.getValue(bName);
	}
	
	public String remove (String name) {
		BusinessName bname = new BusinessName (name);
		return phonebook.remove (bname);
	}
	
	public String getFullNameAndPhone (String s) {
		// to implement
		return null;
	}
	
	public void printAll () {
		Iterator <BusinessName> ni = phonebook.getKeyIterator();
    	Iterator <String> si = phonebook.getValueIterator();
    	
    	while (ni.hasNext()) {
    		System.out.println (ni.next() + " " + si.next());
    	}
    	
    	System.out.println("Finished displaying");
	}
	
	public static void main (String[] args) {
		TDProject td = new TDProject();
		if (!td.readFile("restaurants.txt")) 
			System.exit (1);
		td.printAll();
		
		Scanner input = new Scanner (System.in);
		String s = "";
		System.out.println ("\n======================================================");
		System.out.println ("Bistro 5: " + td.getPhoneNumber("Bistro"));
		do {
			System.out.print("What restaurant to search?\n " +
		     "Type Quit if no more search desired");	
			s = input.nextLine();
			if (s.equalsIgnoreCase("Quit"))
				break;
			// System.out.println ("The phone number for " + td.getPhoneNumber(s));
			String out = td.getFullNameAndPhone(s);
			if (out == null)
				System.out.println ("This restaurant is not found");
			else
				System.out.println (out);
		} while (true);
		
		System.out.println ("removed: " + td.remove ("Brelundi"));
		
		System.out.println (td.getPhoneNumber("Brelundi, Waltham"));
		
		System.out.print("Good bye!");
	}

}
