import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AVL {
	class Node {
	    int val, height;
	    Node left, right;
	    Node(int d) {
	        val = d;
	        left = right = null;
	        height = 1;
	    }
	}
	Node root;
	double start,end;
	public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}
	// Create AVL
	public AVL() {
		root = null;
	}
	
	// To Insert in AVL Tree
	int height (Node N) {
		if (N == null) return 0;
		return N.height;		
	}
	int isBalanced (Node N) {
		if (N == null) return 0;
		return height(N.left) - height(N.right);		
	}
	Node rotateLeft (Node n) {
		Node down = n.right;
		Node toNull = down.left;
		down.left = n;
		n.right = toNull;
		n.height = 1+ Math.max(height(n.left),height(n.right));
		down.height = 1+ Math.max(height(down.left),height(down.right));
		return down;
	}
	Node rotateRight (Node n) {
		Node down = n.left;
		Node toNull = down.right;
		down.right = n;
		n.left = toNull;
		n.height = 1+ Math.max(height(n.left),height(n.right));
		down.height = 1+ Math.max(height(down.left),height(down.right));
		return down;
	}
	Node doubleRotateLeftRight (Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}
	Node doubleRotateRightLeft (Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}
	void insert(int key) {
		root = insertHelper(root, key);
	}
	Node insertHelper(Node n, int key) {
        if (n == null)
            return (new Node(key));
		if (key < n.val) 
			n.left = insertHelper(n.left,key);
		if (key > n.val)
			n.right = insertHelper(n.right,key);
		n.height = 1 + Math.max(height(n.left), height(n.right));
		int b = isBalanced(n);
		if (b > 1 && key < n.left.val)
			return rotateRight(n);
		if (b < -1 && key > n.right.val)
			return rotateLeft(n);
		if (b > 1 && key > n.left.val) 
			return doubleRotateLeftRight(n);
		if (b < -1 && key < n.right.val) 
			return doubleRotateRightLeft(n);
		return n;
	}
	
	// To Delete in AVL Tree
    int minValue(Node root)
    {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        } return minv;
    }
    void delete(int key) { root = deleteHelper(root, key); }
	Node deleteHelper(Node n, int key) {
		if (n == null) return n;
		if (key < n.val) n.left = deleteHelper(n.left, key);
		else if (key > n.val) n.right = deleteHelper(n.right, key);
		else {
			if (n.left == null)  n= n.right;
            else if (n.right == null) n= n.left;
            else {
            n.val = minValue(n.right);
            n.right = deleteHelper(n.right,n.val);
            }
		}
		if (n == null) return n;
		n.height = 1 + Math.max(height(n.left), height(n.right));
		int b = isBalanced(n);
		if (b > 1 && isBalanced(n.left) >= 0)
			return rotateRight(n);
		if (b < -1 && isBalanced(n.right) >= 0)
			return rotateLeft(n);
		if (b > 1 && isBalanced(n.left) < 0)  //left right case
			return doubleRotateLeftRight(n);
		if (b < -1 && isBalanced(n.right) < 0)  //right left case
			return doubleRotateRightLeft(n);
		return n;
	}
	
	// Search in AVL Tree
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
    void inorder(Node n) {
    		if(n!=null) {
    			inorder(n.left);
    			System.out.print(n.val);
    			if(n.left != null) System.out.print(" | Left Child: "+n.left.val);
    			if(n.right != null) System.out.print(" | Right Child: "+n.right.val);
    			System.out.println("");
    			inorder(n.right);
    		}
    }
	void testCaseOne(int elements){
        int i,a;
        Random r = new Random();
        List<Integer> TestNums = new ArrayList<Integer>(16384);
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
//       		search((i+5));
        end = getUserTime();
        System.out.print((end-start)/1000000 +",");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVL tree = new AVL();
		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131072,181072,221072,262144,300000};
//		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};//,16384,32768,65536,131072,262144,300000};
		for(int i = 0; i < listOfElements.length; i++){
			tree.testCaseOne(listOfElements[i]);
		}
//        tree.inorder(tree.root);
	}

}
