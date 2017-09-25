package models;

import java.util.ArrayList;


/**
 * An Interface for the MaxHeap class
 * 
 * @author  Ciara Power
 */
public interface MaxHeapInterface
{
    /** Task: Adds a new entry to the heap.
    * @param newEntry an object to be added */
    public void add (Comparable newEntry);
    
    /** Task: Removes and returns the largest item in the heap.
    * @return either the largest object in the heap or,
    * if the heap is empty before the operation, null */
    public Comparable removeMax ();
    
    /** Task: Retrieves the largest item in the heap.
    * @return either the largest object in the heap or,
    * if the heap is empty, null */
    public Comparable getMax ();
    
    /** Task: Detects whether the heap is empty.
    * @return true if the heap is empty, else returns false */
    public boolean isEmpty ();
    
    /** Task: Gets the size of the heap.
    * @return the number of entries currently in the heap */
    public int getSize ();
    
    /** Task: Removes all entries from the heap. */
    public void clear ();

    /** Task: Gets the Tree Traversal figure at that point in time
     * @return the number of tree traversals most recently taken in the recent search */
	int getTreeTraversal();

    /** Task: Sifts the newly added node upwards in the heap until it is larger than children and smaller than parent. */
	void siftUp();

	/** Task: Sifts the top node downwards in the heap until it is smaller than the parent but larger than children. */
	void siftdown();

	/** Task: Gets the heap arraylist
     * @return the arraylist */
	ArrayList<Comparable> getHeapArray();

	/** Task: sets the heap arraylist*/
	void setHeapArray(ArrayList<Comparable> heapArray);

	/** Task: creates a string of all heap elements 
     * @return string of the heap elements  */
	String display();

	/** Task: searches the heap for a certain item, and returns that match, used recursively
     * @return the object matched 
     * @param item that is to be searched for, current index to start at, booleans if the left/right child has been checked yet */
	Comparable searchHeap(Comparable item, int current, boolean leftChecked, boolean rightChecked);

    
} // end MaxHeapInterface