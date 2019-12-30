import java.awt.Graphics;
public class GameObject {
	protected Boolean active = false;
	protected ID id;
	protected int timer = 0;
	protected int hitx, hity, hitW, hitH, hurtx, hurty,x, y, velX,velY,width,hp;

		public int initialX, initialY;
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	//Tick method to update game state
	public void tick(double delta, int frames){
	
	}
	//Render method to render the object. Should be overridden
	public void render(Graphics g){
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getHitx() {
		return hitx;
	}

	public void setHitx(int hitx) {
		this.hitx = hitx;
	}

	public int getHity() {
		return hity;
	}

	public void setHity(int hity) {
		this.hity = hity;
	}

	public int getHurtx() {
		return hurtx;
	}

	public void setHurtx(int hurtx) {
		this.hurtx = hurtx;
	}

	public int getHurty() {
		return hurty;
	}

	public void setHurty(int hurty) {
		this.hurty = hurty;
	}
	

	public int getHitW() {
		return hitW;
	}

	public void setHitW(int hitW) {
		this.hitW = hitW;
	}

	public int getHitH() {
		return hitH;
	}

	public void setHitH(int hitH) {
		this.hitH = hitH;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public boolean getActive(){
		return this.active;
	}
	
	public void setActive(Boolean bool){
		this.active = bool;
	}
	
	public void resetHit(){
		this.setHitx(-101);
		this.setHity(-100);
	}
	
}
