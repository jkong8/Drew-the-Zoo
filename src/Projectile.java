import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
public class Projectile extends GameObject{
	private Handler handler;
	private Fighter opponent;
	protected ImageIcon p1 = new ImageIcon(this.getClass().getResource("images/jkongProjectile1.png"));
	protected ImageIcon p2 = new ImageIcon(this.getClass().getResource("images/jkongProjectile2.png"));
	protected ImageIcon p3 = new ImageIcon(this.getClass().getResource("images/jkongProjectile3.png"));
	protected ImageIcon p4 = new ImageIcon(this.getClass().getResource("images/jkongProjectile4.png"));
	protected Image image = p1.getImage();
	protected int timer = 0;
	protected int damage,knockBack,advantage;
	public Projectile(int x, int y, ID id, Handler handler, Fighter opponent) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.hitH=100;
		this.hitW = 100;
		this.handler = handler;
		this.opponent = opponent;
		this.velX = 13;
		this.damage = 13;
		this.knockBack = 45;
		this.advantage = 20;
		if(opponent.direction()){
			velX = -velX;
			knockBack = - knockBack;
			this.x -= (225+200);
		}
	}
	
	public void tick(double delta, int frames){
		//MOVES THE ACTUAL PROJECTILE
		x += velX;
		y += velY;
		
		//CHANGES PICTURE FOR ANIMATION
		timer++;
		
		if(timer <=10){
			image = p1.getImage();
		}else if(timer<=20){
			image = p2.getImage();
		}else if(timer<=30){
			image = p3.getImage();
		}else if(timer<=40d){
			image = p4.getImage();
			timer = 0;
		}
		//DEMOLISHES OPPONENT
		if(hit()){
			if(opponent.isBlocking()){
				damage = damage/10;
				knockBack = knockBack/10;
				advantage = advantage/10;
				Music.play3();
			}else{
				Music.play2();
			}
			opponent.hp-=damage;	
			opponent.setActive(true);
			System.out.println("Opponent is active");
			opponent.stop = opponent.timer + advantage;
			opponent.setX(opponent.getX()+knockBack);//REMEBER TO CHANGE FOR DIRECTION LATER
			handler.removeObject(this);
		}

		if(getX() > 1280 - (hurtx+5)){ //Deletes from handler if out of bounds
			handler.removeObject(this);
		}else if(getX() <= 0){
			handler.removeObject(this);
		}
		
		if(getY() > 720-(hurty+25)){//Deletes from handler if out of bounds
			handler.removeObject(this);
		}else if(getY()<=0){
			handler.removeObject(this);
		}
	}
	
	public void render(Graphics g){
		//HURT BOX
		g.setColor(Color.red);
		//g.fillRect(this.x, this.y, hitW, hitH);
		//IMAGE
		if(opponent.direction()){
			g.drawImage(image, x + this.getHurtx(), y+5, -image.getWidth(null), image.getHeight(null), null);
		}else{
			g.drawImage(image, x, y, null);
		}
		
	}

	public Boolean hit(){
			if(this.getX() > (opponent.getX()+opponent.getHurtx()) || opponent.getX() > (this.getX()+this.getHitW())){
				return false;
			}
			if(this.getY() > (opponent.getY()+opponent.getHurty()) || opponent.getY() > (this.getY()+this.getHitH())){
				return false;
			}
			return true;
	}
}
