
public class Node {
	private int value;
	protected Node next;
	
	public Node(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setNext(int value){
		this.next = new Node(value);
	}
	
	public Node getNext(){
		if(this.next == null){
			return null;
		}else{
			return this.next;
		}
	}
}
