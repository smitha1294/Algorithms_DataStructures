import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class BST {
    class Node {
        int val;
        Node left, right;
        public Node(int item) {
            val = item;
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
    // Create a BST root
	public BST() {
		root = null;
	}
	
	// Insert key in BST
	void insert(int key) {
		root = insertHelper(key, root);
	}
	Node insertHelper(int key, Node root) {
        if (root == null)
            return (new Node(key));	
		if (key < root.val) root.left = insertHelper(key, root.left);
		if (key > root.val) root.right = insertHelper(key, root.right);
		return root;
	}
	
	// Delete key in BST
	void delete(int key) { root = deleteHelper(key, root); }
	Node deleteHelper(int key, Node root) {
		if (root == null) return root;
		if (key < root.val) root.left = deleteHelper(key, root.left);
		else if (key > root.val) root.right = deleteHelper(key, root.right);
		else {
            if (root.left == null)  return root.right;
            else if (root.right == null) return root.left;
            root.val = minValue(root.right);
            root.right = deleteHelper(root.val, root.right);
        } return root;
	}
    int minValue(Node root)
    {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        } return minv;
    }
    
	// Search key in BST
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
    			System.out.println(root.val);
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
        		System.out.print(a);
        }
//        end = getUserTime();
        //System.out.print((end-start)/1000000 +",");
        start = getUserTime();
//        		delete (TestNums.get(1));
       		search(TestNums.get(1));
//       		search(1);
        end = getUserTime();
    //    System.out.print((end-start)/1000000 +",");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST tree = new BST();
		int listOfElements[] = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384 };//,32768,65536,131072,262144,300000};
		for(int i = 0; i < listOfElements.length; i++) {
			tree.testCaseOne(listOfElements[i]);
		}
//        tree.inorder(tree.root);
	}
}
