import java.util.Random;
/**
A class of bags whose entries are stored in a chain of linked nodes.
The bag is never full.
@author Frank M. Carrano
 * This code is from Chapter 4 of
 * Data Structures and Abstractions with Java 4/e
 *      by Carrano
 * 
 * The toString method is overwritten to give a nice display of the items in
 * the bag in this format Bag{Size:# [1] [2] [3] [4] }
 * //- * @version 4.0
 */
public final class LinkedBag<T> implements BagInterface<T> {

    private Node firstNode; // reference to first node
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    /** Sees whether this bag is empty.
    @return true if the bag is empty, or false if not */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    /** Gets the current number of entries in this bag.
    @return the integer number of entries currently in the bag */
    public int getCurrentSize() {
        return numberOfEntries;
    } // end getCurrentSize


    /** Adds a new entry to this bag.
    @param newEntry The object to be added as a new entry.
    @return True. */
    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Make new node reference rest of chain
        // (firstNode is null if chain is empty)
        firstNode = newNode; // New node is at beginning of chain
        numberOfEntries++;
        return true;
    } // end add

    /** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. */
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        } // end while
        return result;
    } // end toArray

    /** Counts the number of times a given entry appears in this bag.
    @param anEntry The entry to be counted.
    @return The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;
        while ((loopCounter < numberOfEntries) && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            }
            loopCounter++;
            currentNode = currentNode.next;
        } // end while
        return frequency;
    } // end getFrequencyOf
    
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) 
                found = true;
             else 
                currentNode = currentNode.next;
        } // end while
        return found;
    } // end contains

    /** Removes one occurrence of a given entry from this bag, if possible.
    @param anEntry The entry to be removed.
    @return True if the removal was successful, or false otherwise. */
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null) {
            nodeN.data = firstNode.data; // replace located entry with entry
                // in first node
            firstNode = firstNode.next; // Remove first node
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove

// Locates a given entry within this bag.
// Returns a reference to the node containing the entry, if located,
// or null otherwise.
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        } // end while
        return currentNode;
    } // end getReferenceTo

    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    } // end clear

    private class Node {

        private T data; // Entry in bag
        private Node next; // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node

    /** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful,
    or null. */
    public T remove() {
        T result = null;

        // MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG
        Random rand = new Random();
        int  n = rand.nextInt(numberOfEntries) + 0;
        int i=1;
        if (firstNode != null) {
            Node temp;
            temp=firstNode;
            while(i!=n-1)
            {
            	temp=temp.next;
            }
            result=temp.next.data;
            temp.next=temp.next.next;
            numberOfEntries-=1;
        } // end if


        return result;
    } // end remove

    /** Override the toString method so that we can inspect the contents of the bag.
     * @return A string representation of the contents of the bag. */
    public String toString() {

        String result = "Bag{Size:" + numberOfEntries + " ";

        Node scout = firstNode;

        for (int index = 0; index < numberOfEntries; index++) {
            result += "[" + scout.data + "] ";
            scout = scout.next;
        } // end for

        result += "}";
        return result;
    } // end toString

    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     * 
     ************************************************************************/
    
    /** Check to see if two bags are equals.  
     * @param aBag Another object to check this bag against.
     * @return True if the two bags contain the same objects with the same frequencies.
     */
    public boolean equals(LinkedBag<T> aBag) {
        boolean result = false; // result of comparison of bags
         LinkedBag<T> bag;
        // COMPLETE THIS METHOD 
         //System.out.println("hello");
        if(this.numberOfEntries==aBag.numberOfEntries)
        {  Node temp=firstNode;
          
          if(temp==null)
        	  return true;
        while(temp!=null)
        {
          T a=temp.data;
            if(aBag.contains(a)==true)
            {
            if(aBag.getFrequencyOf(a)==getFrequencyOf(a))
            	result=true;
            else
            	result=false;
            }
            else
            	result=false;
          temp=temp.next;  
        }
        System.out.println(result);
        }
        return result;
    }  // end equals

    /** Duplicate all the items in a bag.
     * @return True if the duplication is possible.
     */
    public boolean duplicateAll() {
        boolean success = true; // should always return true
                                // if there is a problem allocating nodes
                                // an exception will be thrown

        // COMPLETE THIS METHOD 
        Node temp;
        temp=firstNode;
        T a;
        while(temp!=null)
        {
         a=temp.data;
         if(getFrequencyOf(a)==1)
         {
        	 Node node=new Node(a);
        	 node.next=null;
        	 if(node==null)
        	 {
        		 success=false;
        	 }
        	 else
        	 {
        		Node temp1=temp;
        		while(temp1.next!=null)
        			temp1=temp1.next;
        		temp1.next=node;
        	 } 
         }
         temp=temp.next;
        }
        return success;
    }  // end duplicateAll

    /** Remove all duplicate items from a bag
     */
    public void removeDuplicates() {

        // COMPLETE THIS METHOD 
          Node temp=firstNode;
           
          while(temp!=null)
          { 
        	  T a=temp.data;
        	  Node temp1=temp;
        	  while(temp1.next!=null)
        	  {
        		 
        		if(temp1.next.data==a)
        		{
        			temp1.next=temp1.next.next;
        			numberOfEntries-=1;
        		}
        		
        		else
        		{
        		temp1=temp1.next;
        		}
        	  }
        	  temp=temp.next;
          }
          //System.out.println(numberOfEntries);

        return;
    }  // end removeDuplicates
}
