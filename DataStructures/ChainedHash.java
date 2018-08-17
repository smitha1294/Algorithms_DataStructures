package chainedHash;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.lang.management.ManagementFactory;
public class ChainedHash {
	public int listsize = 5000, collisionCount=0;
	public LinkedList<Integer> [] l2 = new LinkedList[listsize];
	
	public ChainedHash() {
		for (int i=0;i<listsize;i++) {
			l2[i]= new LinkedList<Integer>();
		}
	}
	public int hashFunc(int key) {
		return key%listsize;
		}
	
	public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}
	
	public void insert(int key) {
	    int hashIndex = hashFunc(key); 
	    if (l2[hashIndex].contains(key)) collisionCount++;
	    l2[hashIndex].add(key);
	  }
	
	public void delete(int key) {
		    int hashIndex = hashFunc(key); 
		    while (!(l2[hashIndex].isEmpty())) 
		    { 
		      if (l2[hashIndex].contains(key)) {
		    	   int delIndex = l2[hashIndex].indexOf(key);
		        l2[hashIndex].remove(delIndex);
		      }
		      return;
		  }
	}

	public int find(int key) {
		    int hashIndex = hashFunc(key); 
		    while (!(l2[hashIndex].isEmpty())) 
		    { 
		      if (l2[hashIndex].contains(key)) {
		    	   int foundIndex = l2[hashIndex].indexOf(key);
		       return l2[hashIndex].get(foundIndex);
		      }
		    return -2; 
		  }
		    return -2; 
	}
	
	// For Debug Purpose
	public void printHashTable() {
		System.out.println(listsize+" "+collisionCount);
	    System.out.println("Table: ");
	    	for (int j = 0; j < listsize; j++) {
	        System.out.print(j + " ");
	        for(int num:l2[j]) {
	        		System.out.print("--> "+num);
	        }
	        System.out.println("");
	    }
	}
	
	public static void main(String[] args) {
		ChainedHash c = new ChainedHash();
		double start,end;
		int input = 50000;
		List<Integer> TestNums = new ArrayList<Integer>(input);
		for(int i=0;i<input;i++) {
			Random r = new Random();
			TestNums.add(1 + r.nextInt(Integer.MAX_VALUE));
		}
		for(int num:TestNums)
			c.insert(num);
		//c.printHashTable(); // debugging
		start = getUserTime( );
		System.out.println("Find element "+TestNums.get(0)+" : "+c.find(TestNums.get(0)));
		end = getUserTime( );
		System.out.print("Time to find "+(end-start)/1000000);
		for(int num:TestNums) 
			c.delete(num);
		//c.printHashTable(); // debugging
	}
}
