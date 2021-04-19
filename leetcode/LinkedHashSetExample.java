// Java Program to illustrate the LinkedHashSet
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetExample
{
	
	// Main Method
	public static void main(String[] args)
	{
		LinkedHashSet<String> linkedset =
						new LinkedHashSet<String>();

		// Adding element to LinkedHashSet
		linkedset.add("A");
		linkedset.add("B");
		linkedset.add("C");
		linkedset.add("D");

		// This will not add new element as A already exists
		linkedset.add("A");
		linkedset.add("E");

		System.out.println("Size of LinkedHashSet = " +
									linkedset.size());
		System.out.println("Original LinkedHashSet:" + linkedset);
		System.out.println("Removing D from LinkedHashSet: " +
							linkedset.remove("D"));
		System.out.println("Trying to Remove Z which is not "+
							"present: " + linkedset.remove("Z"));
		System.out.println("Checking if A is present=" +
							linkedset.contains("A"));
		System.out.println("Updated LinkedHashSet: " + linkedset);

        Set<String> hs = new LinkedHashSet<>();
        hs.add("Geek");
        hs.add("For");
        hs.add("Geeks");
        hs.add("A");
        hs.add("B");
        hs.add("Z");

        int j = hs.size();
        Iterator itr = hs.iterator();
        while (itr.hasNext()) {
            if (j == 1) {
                System.out.print(itr.next());
            } else {
                System.out.print(itr.next() + ", ");   
            }
            j--;
        }

        System.out.println();

        int i = 0;
        for (String s : hs) {
            if (i == hs.size() - 1) {
                System.out.print(s);
            } else {
                System.out.print(s + ", "); 
            }

            i++;

        }

        System.out.println();
	}
}
