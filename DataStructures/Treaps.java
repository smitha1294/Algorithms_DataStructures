import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
public class Treaps {
	Random r = new Random();
    class Node {
        int val; 
        float priority;
        Node left, right;
        public Node(int item) {
            val = item;
            left = right = null;
            priority = 1 + r.nextInt(100);
        }
    }
    Node root;
    double start,end;
	public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}
    // Create a Treaps root
	public Treaps() {
		root = null;
	}
	
	// Insert in Treaps
	Node rotateLeft (Node n) {
		Node down = n.right;
		Node toNull = down.left;
		down.left = n;
		n.right = toNull;
		return down;
	}
	Node rotateRight (Node n) {
		Node down = n.left;
		Node toNull = down.right;
		down.right = n;
		n.left = toNull;
		return down;
	}
	void insert(int key) { root = insertHelper(root, key); }
	Node insertHelper(Node n, int key) {
        if (n == null)
            return (new Node(key));
		if (key < n.val) {
			n.left = insertHelper(n.left,key);
			if (n.left.priority > n.priority) n = rotateRight(n);
		}
		else if (key > n.val) {
			n.right = insertHelper(n.right,key);
			if (n.right.priority > n.priority) n = rotateLeft(n);
		}
		return n;
	}
	
	//Delete in Treaps
	void delete(int key) { root = deleteHelper(key, root); }
	Node deleteHelper(int key, Node root) {
		if (root == null) return root;
		if (key < root.val) root.left = deleteHelper(key, root.left);
		else if (key > root.val) root.right = deleteHelper(key, root.right);
		else if (root.left == null)  root = root.right;
        else if (root.right == null) root = root.left;
        else if (root.right.priority > root.left.priority) {
        		root = rotateLeft(root);
        		root.left = deleteHelper(key,root.left);}
        else {
        		root = rotateRight(root);
        		root.right = deleteHelper(key,root.right);}
        return root;
	}
	
	// Search  in Treaps
    void search(int key) { 
    	Node n;
    	n = searchHelper(key,root); 
//	if(n != null && key == n.val) System.out.println("Found");
//    else System.out.println("Not Found");
    }
	Node searchHelper(int key, Node root) {
		if (root == null) return null;
		if ( root.val == key) return root;
		if (key < root.val) return searchHelper(key, root.left);
		return searchHelper(key, root.right);
	}
	
    // PRINT TO DEBUG
    void inorder(Node root) {
    		if(root!=null) {
    			inorder(root.left);
    			System.out.print(root.val+" - "+root.priority+" %");
    			if(root.left != null) System.out.print(" | Left Child: "+root.left.val);
    			if(root.right != null) System.out.print(" | Right Child: "+root.right.val);
    			System.out.println("");
    			inorder(root.right);
    		}
    }
	void testCaseOne(int elements){
        int i,a;
        List<Integer> TestNums = new ArrayList<Integer>(16384);
        Random r = new Random();
//        start = getUserTime();
        for(i = 1; i <= elements; i++) {
        		a = 1 + r.nextInt(Integer.MAX_VALUE);
        		insert(a);
        		TestNums.add(a);
        }
//        end = getUserTime();
        //System.out.print((end-start)/1000000 +",");
        start = getUserTime();
//		delete (TestNums.get(1));
		search(TestNums.get(1));
//        		search((i+5));
        end = getUserTime();
        System.out.print((end-start)/1000000 +",");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Treaps tree = new Treaps();
		Random r = new Random();
		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131072,262144,300000};
//		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};//,16384,32768,65536,131072,202144,262144,300000};
		for(int i = 0; i < listOfElements.length; i++){
			tree.testCaseOne(listOfElements[i]);
		}
//        tree.inorder(tree.root);
	}

}
