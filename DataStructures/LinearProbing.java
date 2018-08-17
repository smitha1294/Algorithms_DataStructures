import java.lang.management.ThreadMXBean;
import java.util.*;
import java.lang.management.ManagementFactory;

public class LinearProbing {
	int arrsize = 5000, elemCount=0, markDeleted =-1, empty=0, collisionCount=0;
	int [] hashTable;
	
	public LinearProbing() {
		hashTable= new int[arrsize];
		}
	
	public LinearProbing(int size) {
		arrsize = size;
		hashTable= new int[arrsize];
	}
	
	public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}
	
	void resizeArr(int size) {
		LinearProbing lpr = new LinearProbing(size);
		for(int i=0;i<arrsize;i++) {
			if(hashTable[i] == markDeleted || hashTable[i] == empty) continue;
			else lpr.insert(hashTable[i], false);
		}
		hashTable = lpr.hashTable;
		arrsize = lpr.arrsize;
	}
	
	int hashFunc(int key) {
		return key%arrsize;
		}
	
	 void insert(int key, boolean isNew) {
	    if (elemCount >= (arrsize)) resizeArr(arrsize*2); // double size for load factor 1
	    int hashIndex = hashFunc(key); 
	    while (hashTable[hashIndex] != empty && hashTable[hashIndex] != markDeleted) {
	      ++hashIndex; 
	      hashIndex %= arrsize;
	      if(isNew) {collisionCount++;isNew=false;}
	    }
	    hashTable[hashIndex] = key;
	    elemCount++;
	  }
	
	 void delete(int key) {
		 	if (elemCount <= (arrsize/3)) resizeArr(arrsize/2); // half size for load factor 0.3
		    int hashIndex = hashFunc(key); 
		    int head = hashIndex;
		    while (hashTable[hashIndex] != empty) 
		    { 
		      if (hashTable[hashIndex] == key) {
		        hashTable[hashIndex] = markDeleted;
		        elemCount--;
		        return;
		      }
		      ++hashIndex; 
		      hashIndex %= arrsize;
		      if(hashIndex == head) return;
		    }
		  }

	 int find(int key) {
		    int hashIndex = hashFunc(key); 
		    int head = hashIndex;
		    while (hashTable[hashIndex] != empty) 
		    { 
		      if (hashTable[hashIndex] == key)
		        return hashTable[hashIndex]; 
		      ++hashIndex; 
		      hashIndex %= arrsize;
		      if(hashIndex == head) return -2;
		    }
		    return -2; 
		  }
	
	// For Debug Purpose
	 void printHashTable() {
	    System.out.println("Table: ");
	    System.out.println("RESULT: "+arrsize+" "+elemCount+" "+collisionCount);
	    for (int j = 0; j < arrsize; j++) 
	        System.out.print(hashTable[j] + " ");
	    System.out.println("");
	  }
		  
	public static void main(String[] args) {
		LinearProbing c = new LinearProbing();
		double start,end;
		int input = 50000;
		List<Integer> TestNums = new ArrayList<Integer>(input);
		for(int i=0;i<input;i++) {
			Random r = new Random();
			TestNums.add(1 + r.nextInt(Integer.MAX_VALUE));
		}
		for(int num:TestNums)
			c.insert(num, true);
		//c.printHashTable(); // debugging
		start = getUserTime( );
		System.out.println("Find element "+TestNums.get(0)+" : "+c.find(TestNums.get(0)));
		end = getUserTime( );
		System.out.println("Time to find "+(end-start)/1000000);
		for(int num:TestNums)
			c.delete(num);
		//c.printHashTable(); // debugging
	}
}

