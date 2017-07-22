
public class SinglyLinkedList {
	private Node head;
	
	public SinglyLinkedList() {
		head = null;
	}
	
	public void prepend(Object item) {
		Node first = new Node(item);
		
		first.next = head;
		head = first;
	}
	
	public void append(Object item) {
		if(head == null) {
			prepend(item);
			return;
		}
		
		Node t = head;
		
		while(t.next != null)
			t = t.next;
		
		t.next = new Node(item);
	}
	
	public Object behead() {
		if(head == null)
			return null;
		
		Node removed = head;
		
		head = head.next;
		return removed.data;
	}
	
	public Object truncate() {
		if(head == null)
			return null;
		if(head.next == null)
			return this.behead();
		
		Node t = head;
		Node removed;
		
		while(t.next.next != null)
			t = t.next;
		
		removed = t.next;
		t.next = null;
		return removed.data;
	}
	
	public Object getHead() {
		return head.data;
	}
	
	public Object getTail() {
		if(head == null)
			return null;
		
		Node t = head;
		
		while(t.next != null)
			t = t.next;
		
		return t.data;
	}
	
	public void display() {
		Node t = head;
		
		while(t != null) {
			System.out.print(" > " + t.data.toString());
			t = t.next;
		}
		
		System.out.println();
	}
}
