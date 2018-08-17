import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Splay {
	class Node {
	    int val;
	    Node left, right;
	    Node(int d) {
	        val = d;
	        left = right = null;
	    }
	}
	Node root;
	double start,end;
	public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}
	// Create Splay
	public Splay() {
		root = null;
	}
	Node splay(Node root, int key) {
	    if (root == null || root.val == key) return root;
	    if (key < root.val) { // Key lies in left subtree
	        if (root.left == null) return root;
	        if (key < root.left.val) {  // Zig-Zig (Left Left)
	            root.left.left = splay(root.left.left, key);
	            root = rotateRight(root); }
	        else if (key > root.left.val) { // Zig-Zag (Left Right)
	            root.left.right = splay(root.left.right, key);
	            if (root.left.right != null) 
	            		root.left = rotateLeft(root.left);
	        }
	        return (root.left == null)? root: rotateRight(root);
	    }
	    else { // Key lies in right subtree
	        if (root.right == null) return root;
	        if (key > root.right.val) { // Zag-Zag (Right Right)
	            root.right.right = splay(root.right.right, key);
	            root = rotateLeft(root);}
	        else if (key < root.right.val) { // Zag-Zig (Right Left)
	            root.right.left = splay(root.right.left, key);
	            if (root.right.left != null)
	                root.right = rotateRight(root.right);
	        }
	        return (root.right == null)? root:rotateLeft(root);
	    }}
	
	// Insert in Splay
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
	void insert(int key) {root = insertHelper(key, root);}
	Node insertHelper(int key, Node root) {
		if (root == null) return (new Node(key));	
		root = splay(root, key);
		Node n = new Node(key);
   		if (key < root.val) {
   			n.right = root;
   			n.left = root.left;
   			root.left = null;
   		} else {
   			n.left = root;
   			n.right = root.right;
   			root.right = null;
   		}
   		return n;
	}
	
	//Delete in Splay
	void delete(int key) { root = deleteHelper(key, root); }
	Node deleteHelper(int key, Node root) {
		Node x;
	    if (root == null) return null;	
	    root = splay(root, key);
	    if (root.val != key) return root;
		if (root.left == null) {
		    root = root.right;
		} else {
		    x = root.right;
		    root = root.left;
		    root = splay(root, key);
		    root.right = x;
		}
		return root;
	}
	
	// Search  in Splay
    void search(int key) {
    		root = splay(root, key);
//    		if(root!=null && key == root.val) System.out.println("Found");
//        else System.out.println("Not Found");
    	}
	
    // PRINT TO DEBUG
    void inorder(Node root) {
    		if(root!=null) {
    			inorder(root.left);
    			System.out.print(root.val);
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
		Splay tree = new Splay();
//		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};//32768,65536,131072,262144,300000};
		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};
		for(int i = 0; i < listOfElements.length; i++)
			tree.testCaseOne(listOfElements[i]);
//        tree.inorder(tree.root);
	}
}
