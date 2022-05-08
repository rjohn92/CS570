//Student: Roland John 10474110

//Class: CS 570-B

import java.util.ArrayList;

/*every IDLList will have the four data fields head, tail, 
*size, and indices no matter it's size
*/
public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	/*
	 * Inner class for Node Holds data and pointers between
	 */

	private class Node<E> {
		private E data;
		// points to the next element
		private Node<E> next;
		// points to the previous element
		private Node<E> prev;

		Node(E elem) {
			this.data = elem;
		}// end Node (E elem) operation

		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.next = next;
			this.prev = prev;
		} // End Node (E elem, Node<E> prev, Node <E> next) operation
	}// end inner class

	// default constructor will create default empty list
	public IDLList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
		// makes empty array when we call this
		this.indices = new ArrayList<Node<E>>();
	}// end default constructor

	public boolean add(int index, E elem) throws Exception, NullPointerException, RuntimeException {
		Node<E> newNode = new Node<E>(elem);
		// if our index given is greater than the size or less than 0 we throw an
		// exception
		if (index > size) {
			throw new Exception("Index out of boundary. (Index given is greater than list size).");
		}
		if (index < 0) {
			throw new Exception("Index out of boundary. (Index given is less than list size).");
		}
		/*
		 * if the index is 0 then we will just run our add() method to add the element
		 * to the beginning of the list
		 */
		if (index == 0) {
			return add(elem);
		}
		// if the index is equal to the size then we will add the element to the tail
		if (index == size) { // Add elem at tail
			return append(elem);
		}
		// we will have our nextNode equal the index at the given index
		Node<E> nextNode = indices.get(index);

		// then create a prevNode which equals our nextNode previous pointer
		Node<E> prevNode = nextNode.prev;
		// the prevNode.next equals our newNode
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = nextNode;
		nextNode.prev = newNode;
		// Update indices and size
		indices.add(index, newNode);
		size++;
		return true;
	}

	// put the element at the beginning of the linked list
	public boolean add(E elem) {
		Node<E> currentNode = new Node<E>(elem);
		/*
		 * when the list is empty we just set the currentNode to be the both the head
		 * AND tail
		 */
		if ((head == null && tail == null)) {
			head = currentNode;
			tail = currentNode;

			/*
			 * if the list is currently populated then we will make this node the head. The
			 * head node now becomes the currentNode
			 */
		} else {
			currentNode.next = head;
			head.prev = currentNode;
			head = currentNode;
		}
		/*
		 * we will now use the add(index, E elem) method to add the elem at the first
		 * index using our previous add method
		 */
		indices.add(0, currentNode);
		// the size now has to increase by one
		size++;
		return true;
	}

	// we will add the value the user gives us to the end of the list
	public boolean append(E elem) {
		/*
		 * create the current node that will be the value of the elem the user gives us.
		 */
		Node<E> currentNode = new Node<E>(elem);
		/*
		 * if the list we have is empty then we will simply assign the head and tail to
		 * be the currentNode value the user has given us.
		 */
		if (head == null && tail == null) {
			tail = currentNode;
			head = currentNode;
			/*
			 * if there are already values in the list then we will simply add the values to
			 * the end of the list.
			 */
		} else {
			tail.next = currentNode;
			currentNode.prev = tail;
			tail = currentNode;
		}
		indices.add(indices.size(), currentNode);
		size++;
		return true;
	}

	// here we will get the element at the given index using fast accessing
	public E get(int index) throws Exception, RuntimeException {
		// We can't allow the user to input a negative index
		if (index < 0) {
			throw new Exception("Index out of range.\n(Index must be postive.)");
		}

		// if the index given is greater than the size of the we throw an exception
		if (index > size - 1) {
			throw new Exception("Index out of range.\n(Index given is greater than list size.)");
		}

		else {
			return indices.get(index).data;
		}

	}

	/*
	 * getHead will always return the value located at the head of the list thus
	 * there is no need for user-generated input. If the list is empty we will
	 * return null.
	 */
	public E getHead() {
		if (size == 0) {
			return null;
		} else {
			return indices.get(0).data;
		}
	}

	/*
	 * getLast will always return the value located at the end of the list thus
	 * there is no need for user-generated input. If the list is empty we will
	 * return null.
	 */
	public E getLast() {
		/*
		 * if the list is empty we cannot return anything. Thus we will have to return
		 * 'null'
		 */
		if (size == 0) {
			return null;
		} else {
			return indices.get(size - 1).data;
		}
	}

	// size() will simply return the size of the list
	public int size() {
		return size;
	}

	// removes and returns the element at the head.
	public E remove() throws Exception {
		// create nodeToRemove which will be set to our head value
		Node<E> nodeToRemove = head;
		// if the size is 0 then we throw the exception telling the user
		if (size == 0) {
			throw new Exception("List is empty.");
		}
		if (head == null || head.next == null) {
			head = null;
			size--;
			return nodeToRemove.data;

		} else {
			// we will now make the head equal to the next node
			head = head.next;
			// the previous node will become null
			head.prev = null;
			// we will decrement the size by 1
			size--;
			indices.remove(nodeToRemove);
			// now return the value of the nodeToRemove
			return nodeToRemove.data;
		}
	}

	public E removeLast() throws Exception {
		// create the variable we want to delete and set it to be the tail
		Node<E> nodeToRemove = head;
		if (size == 0) {
			throw new Exception("List is empty.");
		}
		/*
		 * if the head is null or the next pointer is null we will set the head to null,
		 * then decrement the size.
		 */
		if (head == null || head.next == null) {
			head = null;
			size--;
			indices.remove(nodeToRemove);
			return nodeToRemove.data;
		}
		// loop through the list references to find the tail
		while (nodeToRemove.next != null) {
			// keep setting the nodeToRemove to be equal to the next reference of
			// nodeToRemove
			nodeToRemove = nodeToRemove.next;

		}
		// remember to decrement the size by one
		size--;
		// remember to keep the index management
		indices.remove(nodeToRemove);
		nodeToRemove.prev.next = null;
		return nodeToRemove.data;
	}

	// remove a point at a specific index and decrement the size of the list
	public E removeAt(int index) throws Exception {
		Node<E> nodeToRemove = indices.get(index);
		//if the size of the list is 0 list is empty. we can't remove anything
		if (size() == 0) {
			throw new Exception("The list is empty.");
		}
		//if the index is less than 0 we can't perform the operation
		if (index < 0) {
			throw new Exception("Index requested cannot be less than 0.");
		}
		//if the index is greater or equal to the size of the list we throw an exception
		if (index >= size) {
			throw new Exception("Index requested is greater than list size.");
		}
		//if the index is equal to 0 we can simply perform the remove() function
		if (index == 0) {
			return remove();
		}
		//if the index requested is the last index we can perform removeLast()
		if (index == size() - 1) {
			return removeLast();
		} else {
			/*create a new node and set it to our nodeToRemove which is equal 
			 * to the index we are given. 
			 */
			Node<E> thisNode = nodeToRemove;
			thisNode.prev.next = nodeToRemove.next;
			thisNode.next.prev= nodeToRemove.prev;
		}
				// remember to decrement the size by one
				size--;
				// remember to keep the index management
				indices.remove(index);
				nodeToRemove.next = null;
				//return the data for nodeToRemove
				return nodeToRemove.data;
	}
/*We will see if the requested element is in the list we have. If it is in the
 * list we will return true. Otherwise we will return false. 
 */
	public boolean remove(E elem) {
		/*We will create a thisNode object and set it to the elem we are
		 *  searching for
		 */
		E thisNode = elem;
		/*Next we will set our index and initialize it to 0 because we want to 
		 * start searching the list from the head first
		 */
		int indexSearch = 0;
		/*we will create a while loop to iterate over the list search 
		 * we will remember to increment to indexSearch variable so we 
		 * can properly search the entire list. If our indexSearch reaches
		 * a value equal/greater to the size of our list we will terminate
		 * and return false.
		 */
		while (indexSearch<indices.size()) {
			/*we have to check to see if each specific index has a value equal
			 * to our thisNode object.
			 */
			if (indices.get(indexSearch).data==thisNode) {
				try {
					removeAt(indexSearch);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			} 
			indexSearch++;
		}
		return false;
		}
	public String toString() {
		// create a node and set it to the head
		Node<E> nodeToAdd = head;
		String listString = "";
		// if the list is empty we will tell the user
		if (size == 0) {
			return null;
			// if the list is not empty we will return the list in order
		} else {
			// start the String off as a blank String type named tmp

			// while the nodeToAdd is not null we will add it to the tmp variable
			while (nodeToAdd != null) {
				listString += nodeToAdd.data;
				// if the nodeToAdd.next reference is not null we will add a comma
				if (nodeToAdd.next != null) {
					listString = listString + ", ";
				}
				// we will make the nodeToAdd equal to the next reference
				nodeToAdd = nodeToAdd.next;
			}
			return listString;
		}

	}

}// end public class method
