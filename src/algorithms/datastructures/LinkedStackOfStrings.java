package algorithms.datastructures;

/**
 * Proposition: every operation takes constant time in the worst case.
 * Proposition: A stack with N items uses ~40N bytes
 * 16 bytes (object overhead)
 *  8 bytes inner class extra overhead
 *  8 bytes reference to String
 *  8 bytes reference to next Node
 *
 *  Analysis includes memory for stack
 *  but not the string themselves, which the client owns
 */
public class LinkedStackOfStrings {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(String s){
        Node n = new Node();
        n.item = s;
        n.next = first;
        first = n;
    }

    public String pop(){
        String s = first.item;
        first = first.next;
        return s;
    }
}
