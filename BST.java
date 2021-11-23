public class BST {
	public class Node {
		public String data;
		public Node left;
		public Node right;

		Node() {
			data = null;
			left = null;
			right = null;
		}

		Node(String root) {
			data = root;
			left = null;
			right = null;
		}
	}

	///////////////////
	private Node root;

	///////////////////
	public BST() {
		root = null;
	}

	public Node recursiveInsert(Node root, String key) {
		if (root == null) {
			root = new Node(key);
			// System.out.println(root);
			return root;
		}
		if (key.length() < root.data.length()) { // if key is smaller than root
			root.left = recursiveInsert(root.left, key);
		} else { // if key is greater than or equal to root
			root.right = recursiveInsert(root.right, key);
		}
		return root;
	}

	public void insert(String key) {
		root = recursiveInsert(root, key);
	}

	public boolean search(String key) {
		Node r= recursiveSearch(root, key);
		if (r== null) {
			//System.out.println("NULL BST");
			return false;
		}
		return true;
	}

	public Node recursiveSearch(Node root, String key) {
		if (root == null || root.data.equalsIgnoreCase(key)) {
			return root;
		}
		if (key.length() < root.data.length()) {
			return recursiveSearch(root.left, key);
		} else {
			return recursiveSearch(root.right, key);
		}
	}

	public void inorder() {
		recursiveInorder(root);
	}

	public void recursiveInorder(Node root) {
		if (root == null) {
			return;
		}
		recursiveInorder(root.left);
		System.out.println(root.data);
		recursiveInorder(root.right);
	}

	public static void main(String args[]) {
		BST B = new BST();
		B.insert("a");
		B.insert("abc");
		B.insert("ab");
		B.insert("abcd");
		B.inorder();
	}

}
