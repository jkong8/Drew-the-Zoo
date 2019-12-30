import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.LinkedList;

import javax.swing.ImageIcon;
public class Fighter extends GameObject{
	protected Fighter opponent;
	protected int timer = 0; //uses this for frame data
	protected int advantage,knockBack,stop, damage = 0;
	protected Handler handler;
	protected Boolean airBorne = false;
	protected Boolean readyToHit = true; //used to stop the hit box appearing when holding a key.
	protected Boolean blocking = false;
	protected Boolean crouch = false;
	protected Boolean special = false; //lets you move during special
	protected ImageIcon standImage;	//JEFFERY COMPLETED
	protected ImageIcon crouchImage; //JEFFERY COMPLETED
	protected ImageIcon jumpImage;
	protected ImageIcon punchImage;//JEFFERY COMPLETED
	protected ImageIcon kickImage;
	protected ImageIcon crouchPunchImage;
	protected ImageIcon crouchKickImage;
	protected ImageIcon jumpAttackImage;
	protected ImageIcon blockImage;
	protected ImageIcon specialMove;
	protected Image image;
	protected Queue_LinkedList inputCommands;
	protected LinkedList<Integer> moveListL = new LinkedList<Integer>();
	protected LinkedList<Integer> moveListR = new LinkedList<Integer>();
	Game game;
	public Fighter(int x, int y, ID id, Game game, Handler handler) {
		super(x, y, id);
		this.initialX = x;
		this.initialY = y;
		this.hurty = 300;
		this.hurtx = 125;
		this.hitx = - 100;
		this.hity = -100;
		hp = 100;
		this.handler = handler;
		this.inputCommands = new Queue_LinkedList(3);
		this.game = game;
	}

	public void tick(double now, int frames){
		//FOR ALL FIGHTERS
		if(hp <= 0){
			game.setGameState("end");
		}
		//IMPORTANT TIMER INT
		timer++;
		//IMAGES CHANGE DURING CERTAIN ACTIONS
		if(crouch && !active){
			image = crouchImage.getImage();
		}else if(airBorne){
			if(special){
				this.image = specialMove.getImage();
			}else if(active){
				this.image = jumpAttackImage.getImage();
			}else{
				image = jumpImage.getImage();//change later
			}
		}else if(blocking && !active){
			image = blockImage.getImage();
		}else if(special){
			if(direction()){
				this.setHitx(this.x+this.hurtx);
			}else{
				this.setHitx(this.x-this.hitW);
			}
		}
		else{
			if(!active && !special){
				image = standImage.getImage();
			}
		}
		
		if(!(blocking || crouch || active) || airBorne || special){
			this.x += getVelX();
			//Kinematic Equation for distance
			}
		this.y += getVelY()*now + (((17)*(now*now))/2);	
		
		
			//CANNOT HIT UNLESS YOU ARE NOT ACTIVE  //UNDERSTAND THIS BETTER!!!!!!!!!
			if(this.getActive() || this.getSpecial()){
				this.readyToHit = false;
				if(this.getAirborne()){
					if(direction()){
						if(special){
							this.setHity(this.y-50);
							this.setHitx(this.x + this.getHitW());
						}else if(active){
							this.setHitx(this.x + this.getHurtx()-this.getHitW());
							this.setHity(this.y + this.getHurty()-this.getHurty());
						}
					}else{
						if(special){
							this.setHity(this.y-50);
							this.setHitx(this.x - this.getHitW()/2);
						}else if(active){
							this.setHitx(this.x);
							this.setHity(this.y + 75);
						}
					}
				}
				//System.out.println("Not ready to hit");
				if(timer >= stop){
					//System.out.println("Ready to hit");
					this.readyToHit = true;
					this.active = false;
					this.special = false;
					this.velX = 0;
					resetHit();	
					//this.image = standImage.getImage();
				}
				
		}
			
		//Sets jump limit
		if(this.getY() <= 720 - (300+225+100+50)){
			this.setVelY(0);
		}
		
		//Determine if airborne and airborne behavior
		if(this.getY() < 720 - (this.getY()+1)){
			this.airBorne = true;
			this.setVelX(this.getVelX());
			System.out.println("Airborne");
		}
		
		//Determine if crouch and set crouch behavior
		if(this.isCrouching()){
			this.setY(720-(hurty+25));
		}
		//1280 is the x value of the window 
		if(getX() >= 1280 - (hurtx+5)){ //reverses direction if going out of bounds
			this.setX(1280 - (hurtx+5));
		}else if(getX() <= 0){
			this.setX(0);
		}

		//720 is the y value of the window. 
		if(getY() > 720-(this.getHurty()+50)){//reverses direction if going out of bounds
			if(this.getAirborne()==true){ 
				this.airBorne = false;
				this.setHurty(300);
				this.setHurtx(125);
				this.setVelX(0);
				if(active){//**** :)
					active =false;
					this.resetHit();
				}
			}
			this.setY(720-(hurty+50));
		}else if(getY()<=0){
			this.setY(0);
		}
		
		//IF YOU HIT SOMEBODY THEN YOU GOT HIT DERRR...
		if(hit()){
			if(!direction()){
				knockBack = -knockBack;
			}
			if(opponent.isBlocking()){//IF OPPONENT IS BLOCKING. CUT DAMAGE BY 80PERCENT
				damage = damage/10;
				knockBack = knockBack/10;
				advantage = advantage/10;
				Music.play3();
			}else{
				Music.play2();
			}
			opponent.hp-=damage;	//damage
			opponent.setActive(true);
			System.out.println("Opponent is active");
			opponent.stop = opponent.timer + advantage;	//advantage
			opponent.setX(opponent.getX()+knockBack);//REMEBER TO CHANGE FOR DIRECTION LATER  knockback
			damage = 0;
			advantage = 0;
			knockBack = 0;
			this.resetHit();
			opponent.resetHit();
			//hitEffect.play();
		}

		if(this.getHurty()<150){
			this.setHurty(300);
		}
	}
	
	//gets graphics parameter from handler which gets from Game.render()
	public void render(Graphics g){
		//HIDING HIT BOX!!!!!!!!!!!!!!!!!!!!!!!!!
		//FOR PLAYER 1
		if(getId()==ID.player){
			int height = this.getHurty();
			//HURT BOX
			g.setColor(Color.yellow);
			g.drawRect(getX(), getY(), hurtx, hurty);	//REMOVE LATER TO GET RID OF UGLY HIT BOX
			g.setColor(Color.black);
			//Health Bar
			g.setColor(Color.black);
			g.fillRect(0, 45, this.hp*5 + 5, 50);
			g.setColor(Color.yellow);
			g.fillRect(0, 50, this.hp*5, 30);
			g.setColor(Color.red);
			g.drawString("HEALTH: " + this.hp, 10, 70);
			//Hit Box
			g.setColor(Color.red);
			g.drawRect(this.getHitx(), this.getHity(), this.getHitW(), this.getHitH());
			//CHARACTER MODEL
			if(this.getAirborne()){
				if(special){
					height = 300;
				}else{
					height = 200;
				}
			}
			if(direction()){
				g.drawImage(image, x, y+5, image.getWidth(null), height, null);
			}else{
				//INVERTS THE IMAGE
				g.drawImage(image, x + this.getHurtx(), y+5, -image.getWidth(null), height, null);
			}
			
			//FOR PLAYER 2
		}else if(getId()==ID.player2){
			int height = this.getHurty();
			//HURT BOX
			g.setColor(Color.blue);
			g.drawRect(getX(), getY(), hurtx, hurty);
			g.setColor(Color.black);
			//Health Bar
			g.setColor(Color.black);
			g.fillRect(1275-this.hp*5, 45, this.hp*5 + 10, 50);
			g.setColor(Color.yellow);
			g.fillRect(1280-this.hp*5, 50, this.hp*5, 30);
			g.setColor(Color.red);
			g.drawString("HEALTH: " + this.hp, 1190, 70);
			//Hit Box
			g.setColor(Color.red);
			g.drawRect(this.getHitx(), this.getHity(), this.getHitW(), this.getHitH());
			//Character Model
			if(this.getAirborne()){
				if(special){
					height = 300;
				}else{
					height = 200;
				}
			}
			if(direction()){
				g.drawImage(image, x, y+5, image.getWidth(null), height, null);
			}else{
				//INVERTS THE IMAGE
				g.drawImage(image, x + this.getHurtx(), y+5, -image.getWidth(null), height, null);
			}
			
		}
		
	}
	
	public void assignOpponent(Fighter fighter){
		this.opponent = fighter;
	}
	//Determines Direction of the opponent
	public Boolean direction(){
		if(opponent.getX()>=this.getX()+this.getHurtx()){
			//System.out.println("facing");
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean hasSpecial(int command){
		if(direction()){
			if(moveListL.contains(command)){
				return true;
			}
		}else{
			if(moveListR.contains(command)){
				return true;
			}
		}
		return false;
	}
	
	public void doSpecial(int command){
		System.out.println("hi");
	}
	
	public void punch(){	
	}
	
	public void kick(){
	}
	
	public void crouchPunch(){
		
	}
	public void crouchKick(){
		
	}
	public void jumpAttack(){
		
	}
	public Boolean getSpecial(){
		return this.special;
	}
	
	public Boolean getAirborne(){
		return this.airBorne;
	}
	
	public void setSpecial(Boolean special){
		this.special = special;
	}
	
	public void setBlocking(Boolean block){
		this.blocking = block;
	}
	public Boolean isBlocking(){
		return this.blocking;
	}
	public void resetTimer(){
		this.timer = 0;
	}
	public void setCrouching(Boolean crouch){
		this.crouch = crouch;
	}
	public Boolean isCrouching(){
		return this.crouch;
	}
	public Queue_LinkedList getCommand(){
		return inputCommands;
	}
	public Boolean hit(){
		if(this.getHitx() > (opponent.getX()+opponent.getHurtx()) || opponent.getX() > (this.getHitx()+this.getHitW())){
			return false;
		}
		if(this.getHity() > (opponent.getY()+opponent.getHurty()) || opponent.getY() > (this.getHity()+this.getHitH())){
			return false;
		}
		return true;
	}
}
