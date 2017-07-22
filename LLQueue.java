
public class LLQueue {
	SinglyLinkedList data;
	int size;
	
	public LLQueue() {
		data = new SinglyLinkedList();
		size = 0;
	}
	
	public void offer(Object item) {
		data.append(item);
		size++;
	}
	
	public Object poll() {
		if (isEmpty())
			return null;
		
		size--;
		return data.behead();
	}
	
	public Object peek() {
		if (isEmpty())
			return null;
		
		return data.getHead();
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int getSize() {
		return size;
	}
	
	public void display() {
		data.display();
	}
}
