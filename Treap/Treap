/*Student: Roland John
 * CWID: 10474110
 * CS 570-B 
 * HW5 Treap
 * 
 * 
 * I couldnt figure out delete
 */

import java.util.*;
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	private static class Node<E extends Comparable> implements Comparable<Node<E>> {
		// Data Fields for Treap
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E> left; // Node to the left of our Node (if no node to our left will be null)
		public Node<E> right;// Node to the right of our Node (if no node to our right will be null)

		// Constructor for our Treap
		public Node(E data, int priority) {
			// We will throw an exception if our data given is null
			if (this.data != null) {
				throw new NullPointerException("Data cannot be null");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}

		// rotate right
		public Node<E> rotateRight() {
			/*
			 * create a temp node that holds the data and the priority number a new node
			 * that we are creating
			 */
			Node<E> temp = new Node<E>(this.data, this.priority);
			/*
			 * create a new Node that is equal to the temp node's right child that we
			 * created before
			 */
			Node<E> left = temp.right;

			temp.right = this;
			this.left = left;
			return temp;
		}

		// rotate left
		public Node<E> rotateLeft() {
			// create a new temp node to mirror our right node
			Node<E> temp = this.right;
			Node<E> right = temp.left;
			temp.left = this;
			this.right = right;
			return temp;

		}

		@Override
		public int compareTo(Treap.Node<E> other) {
			// TODO Auto-generated method stub
			return data.compareTo(other.data);
		}

		// put ROTATE RIGHT METHOD HERE

		// PUT ROTATE LEFT METHOD HERE
	}// end of Node inner class

	private Random priorityGenerator;
	private Node<E> root;

	/*
	 * our standard constructor will create a null node with a random number that is
	 * our priority
	 */
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();

	}

	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
	}

	boolean add(E key, int priority) {
		// create a new stack object to add our Nodes to
		Stack<Node<E>> ourStack = new Stack<>();
		Node<E> add = new Node(key, priority);
		/*
		 * if the root is currently null that means we have an unpopulated tree. Thus we
		 * simply have to set the root to the provided key and priority
		 */
		if (root == null) {
			root = new Node(key, priority);
			return true;
		}
		/*
		 * if there are already elements in the tree we will check to see if the node is
		 * equal to
		 */
		else {
			Node<E> newNode = root;
			while (newNode != null) {
				if (key.compareTo(newNode.data) == 0) {
					return false;
				}
				if (key.compareTo(newNode.data) > 0) {
					ourStack.push(newNode);
					newNode = newNode.right;
				} else {
					ourStack.push(newNode);
					newNode = newNode.left;
				}

			}
			if (key.compareTo(ourStack.peek().data) < 0) {
				ourStack.peek().left = add;
			}
			if (key.compareTo(ourStack.peek().data) > 0) {
				ourStack.peek().right = add;
			}
		}
		reHeap(add, ourStack);
		return true;
	}
	/*
	 * we will just make a random priority with a new int when we are provided a new
	 * key.
	 */

	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	/*
	 * using a key we will find if the node is located in the tree. If it is we will
	 * return true. Else we will return false.
	 */
	private boolean find(E key) {
		if (key == null) {
			throw new NullPointerException("Data on a tree cannot be null.");
		} else {
			return find(root, key);
		}

	}

	/*
	 * we will delete a node from the key and return true. First we have to find it
	 * in the true though. If we cannot find the key we will return false. Else we
	 * will delete it and return true.
	 */
	boolean delete(E key) {
		/*
		 * if our root is null we obviously have no keys that we can even search for
		 * thus we return false. Also if we cannot find the key we want to delete we
		 * will return false. Otherwise we will run our delete(root, key) method.
		 */
		if (!find(key)) {
			return false;
		}
		if (root == null) {
			return false;
		} else {
			root = deleteLeaf(key, root);
			return true;
		}

	}

	/*
	 * this method will help us delete the key we are looking for. We have set it to
	 * private to follow the UML. It follows similar logic to the first delete
	 * function. If the root is null we return null.
	 */
	private Node<E> deleteLeaf(E key, Node<E> root) {
		if (root == null) {
			return root;
		}
		if (root.left == null && root.right == null && key.compareTo(root.data) == 0) {
			return null;
		}
		root.left = deleteLeaf(key, root.left);
		root.right = deleteLeaf(key, root.right);
		return root;
	}

	/*
	 * using a key given and root we will see if the key is located at the tree.
	 * Else we will return false.
	 */
	private boolean find(Node<E> root, E key) {
		// neither the root nor the tree can be null.
		if (root == null || key == null) {
			return false;
		}
		/*
		 * if the key given is equal to the root then we have found our key. This is our
		 * best case scenario
		 */
		if (key.compareTo(root.data) == 0) {
			return true;
		}
		if (key.compareTo(root.data) < 0) {
			return find(root.left, key);
		}
		if (key.compareTo(root.data) > 0) {
			return find(root.right, key);
		}
		return false;

	}

	private void reHeap(Node<E> childNode, Stack<Node<E>> stack) {
		while (stack.isEmpty() != false) {
			Node<E> parent = stack.pop();
			if (!(parent.priority > childNode.priority)) {
				if (parent.data.compareTo(childNode.data) > 0) {
					childNode = parent.rotateRight();
				} else {
					childNode = parent.rotateLeft();
				}
				if (stack.isEmpty() == false) {
					if (stack.peek().left == parent) {
						stack.peek().left = childNode;
					} else {
						stack.peek().right = childNode;

					}
				} else {
					this.root = childNode;
				}
			} else {
				break;
			}
		}
	}

	// This is the physical manifestation of our string here
	public String toString() {
		StringBuilder ourString = new StringBuilder();
		traversal(root, 1, ourString);
		return ourString.toString();
	}

	private void traversal(Node<E> node, int depth, StringBuilder ourString) {
		// we always start the depth at 0
		int i = 0;
		while (i < depth) {
			ourString.append(" ");
			i++;

		}
		/*
		 * if there is no node placed there we will simply add a null to denote an empty
		 * node at that level.
		 */
		if (node == null) {
			ourString.append("null\n");
		} else {
			ourString.append("(Key:" + node.data);
			ourString.append(", ");
			ourString.append("Priority:" + node.priority);
			ourString.append(")\n");

			/*
			 * as we continue through the tree we add 1 to our depth to signify the levels
			 * we are traversing
			 */
			traversal(node.left, depth + 1, ourString);
			traversal(node.right, depth + 1, ourString);
		}
	}

	public static void main(String[] args) throws Exception {

		try {
			Treap treap = new Treap<Character>();

			treap.add(5, 10);
			treap.add(12);
			System.out.println(treap.find(5));
			System.out.println(treap.find(10));
			System.out.println(treap.find(12));

			System.out.println(treap.find(12));
			System.out.println(treap.toString());

			System.out.println("\n************** PROFESSOR PROVIDED TREAP TEST CODE **************\n");
			System.out.println();

			Treap treap2 = new Treap<Character>();
			treap2.add(4, 19);
			treap2.add(2, 31);
			treap2.add(6, 70);
			treap2.add(1, 84);
			treap2.add(3, 12);
			treap2.add(5, 83);
			treap2.add(99, 26);
			treap2.add(12, 55);
			treap2.add(0, 15);
			System.out.println(treap2.find(2));

			treap2.delete(1);
			System.out.println(treap.find(2));
			System.out.println(treap2.toString());
			treap2.delete(2);

		}

		catch (NullPointerException ex) {
			System.out.print(ex.getMessage());
		}
	}
}
