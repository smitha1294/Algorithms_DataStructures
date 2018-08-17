import java.lang.management.ThreadMXBean;
import java.util.*;
import java.lang.management.ManagementFactory;

public class CuckooHash {
	int arrsize = 1000, elemCount=0, collisionCount=0, empty=0;
	int [][] hashTable;
	int[] pos = new int[2];
	
	public CuckooHash() {
		hashTable= new int[2][arrsize];
		}
	
	public CuckooHash(int size) {
		arrsize = size;
		hashTable= new int[2][arrsize];
	}
	
	public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}
	
	void resizeArr(int size) {
		CuckooHash cpr = new CuckooHash(size);
		for(int i=0;i<2;i++) {
			for(int j=0;j<arrsize;j++) {
				if(hashTable[i][j]!=empty) cpr.insert(hashTable[i][j], 0, 0, false);
				else continue;
			}
		}
		hashTable = cpr.hashTable;
		arrsize = cpr.arrsize;
	}
	
	int hashFunc(int num, int key) {
		return num==1 ? key%arrsize : (key/arrsize)%arrsize;
	}
		
	 void insert(int key, int rowNum, int cycles, boolean isNew) {
		 while(true) {
		 if(cycles >= arrsize || elemCount >= (arrsize*2)) { // double size for load factor 1 and cycles
			 resizeArr(arrsize*2);}
		 for(int x=0;x<2;x++) {
			 pos[x]=hashFunc(x+1,key);
			 if(hashTable[x][pos[x]] == key) return;
		 }
		 if (hashTable[rowNum][pos[rowNum]]!=empty)
		    {
		        int temp = hashTable[rowNum][pos[rowNum]];
		        hashTable[rowNum][pos[rowNum]] = key;
		        if(isNew) {
		        	elemCount++;
		        collisionCount++;
		        }
		       // insert(temp, (rowNum+1)%2, cycles+=1, false); made it iterative
		        key=temp;rowNum=(rowNum+1)%2;cycles+=1; isNew=false;
		    }
		    else {
		       hashTable[rowNum][pos[rowNum]] = key;
		       if(isNew) elemCount++;
		       return;
		    }
		}
	 }
	
	 void delete(int key) { // find and set it 0
		 if (elemCount <= ((arrsize*2)/3)) { if((arrsize/2)!=0)resizeArr(arrsize/2);} // half size for load factor 0.3
		 int found = find(key);
		 	if(found != -1) {
		 		if(hashTable[0][found] == key) hashTable[0][found] = 0;
		 		else if(hashTable[1][found] == key) hashTable[1][found] = 0;
		 		elemCount--;
		 	}
		 	return;
		  }

	 int find(int key) {
		 int index = 0;
		 for(int x=0;x<2;x++) {
			 index=hashFunc(x+1,key);
			 if(hashTable[x][index] == key) return index;
			 }
		 return -1;
	 }
	// For Debug Purpose
	 void printHashTable() {
		System.out.println(arrsize+" "+elemCount+" "+collisionCount);
		 for (int i=0; i<2; i++, System.out.println(""))
		        for (int j=0; j<arrsize; j++)
		        		System.out.print(hashTable[i][j]+" ");
		 System.out.println("");
	  }
		  
	public static void main(String[] args) {
		CuckooHash c = new CuckooHash();
		double start,end;
		int input = 10000;
		List<Integer> TestNums = new ArrayList<Integer>(input);
		for(int i=0;i<input;i++) {
			Random r = new Random();
			TestNums.add(1 + r.nextInt(Integer.MAX_VALUE));
		}
		for(int num:TestNums) 
			c.insert(num,0,0,true);
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