package Helpers;

    /**
    A linked list that remembers the last item.
    */

public class PPLinkedList {
    
    private PPLinkedListNode head;
    private PPLinkedListNode last;
    
    public void add (PixelPoint pp) {
        if(this.head == null) {
            this.head = new PPLinkedListNode(pp);
            this.last = this.head;
            return;
        }
        this.last.next = new PPLinkedListNode(pp); 
        this.last = last.next;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public PixelPoint remove() {
        PPLinkedListNode next = head.next;
        PPLinkedListNode temp = this.head;
        this.head = next;
        return temp.current;
    }
}
