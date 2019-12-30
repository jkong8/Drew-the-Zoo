
public class Queue_LinkedList {
	private Node front;
	private Node rear;
	private int size;
	private int count = 0;
	public Queue_LinkedList(int size){
		this.size = size;
	}
	
	public String toString(){
		String content = "";
		if(isEmpty()){
			System.out.println("Is Empty");
			return content;
		}else{
			Node temp = front;
			while(temp != null){
				content += "["+temp.getValue()+"]";
				temp = temp.getNext();
			}
			System.out.println("Count: "+count +" Total: " + returnContent());
			return content;
		}
	}
	
	public Boolean isEmpty(){
		if(front==null){
			return true;
		}else{
			return false;
		}
	}
	
	public void enque(int input){
		if(isEmpty()){
			front = new Node(input);
			rear = front;
			count++;
		}else{
			if(maxedOut()){
				deque();
			}
			rear.setNext(input);
			rear = rear.getNext();
			count++;
		}
	}
	
	public void deque(){
		if(isEmpty()){
			System.out.println("Is Empty!");
		}else{
			front = front.getNext();
			count--;
		}
	}
	
	public Boolean maxedOut(){
		if(count == size){
			return true;
		}else{
			return false;
		}
	}
	
	public int returnContent(){
		int total = 0;
		Node temp = front;
		while(temp!=null){
			total += temp.getValue();
			temp = temp.getNext();
		}
		return total;
	}
	
	public void resetContent(){
		this.front = null;
		count = 0;
	}
}
