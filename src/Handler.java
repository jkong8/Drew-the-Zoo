import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{
	public LinkedList<GameObject> list;
	//CONSTRUCTOR CLASS
	public Handler(){
		this.list = new LinkedList<GameObject>();
	}
	
	//adds a GameObject to the handler
	public void addObject(GameObject object){
		this.list.add(object);
	}
	//removes a GameObject to the handler
	public void removeObject(GameObject object){
		this.list.remove(object);
	}
	//returns a specified GameObject
	public GameObject get(ID id){
		GameObject temp = null;
		for(int i = 0; i < list.size();i++){
			if(list.get(i).getId().equals(id)){
				temp = list.get(i);
			}
		}
		return temp;
	}
	//Updates all of the GameObjects in the list.
	public void tick(double tick, int frames){
		for(int i = 0; i < this.list.size();i++){
			list.get(i).tick(tick, frames);
		}
	}
	//Gets Graphics from Game.render().
	public void render(Graphics g){
		for(int i = 0; i < this.list.size();i++){
			list.get(i).render(g);
		}
	}
	
	public LinkedList<Fighter> getAllFighters(){
		LinkedList<Fighter> get = new LinkedList<Fighter>();
		for(int i = 0; i < this.list.size();i++){
			if(this.list.get(i).getId() == ID.player || this.list.get(i).getId() == ID.player2){
				get.add((Fighter) this.list.get(i));
			}
		}
		return get;
	}
	
	public void removeAll(){
		while(!this.list.isEmpty()){
			list.removeFirst();
		}
	}
}