import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Jeffery extends Fighter{
	ImageIcon fireball = new ImageIcon(this.getClass().getResource("images/jkongFireball.png"));
	public Jeffery(int x, int y, ID id, Game game, Handler handler) {
		super(x, y, id, game, handler);
		//
		this.standImage = new ImageIcon(this.getClass().getResource("images/jkongStanding.png"));
		this.crouchImage = new ImageIcon(this.getClass().getResource("images/jkongCrouching.png"));
		this.punchImage = new ImageIcon(this.getClass().getResource("images/jkongPunch.png"));
		this.kickImage = new ImageIcon(this.getClass().getResource("images/jkongKick.png"));
		this.crouchPunchImage = new ImageIcon(this.getClass().getResource("images/jkongCrouchPunch.png"));
		this.crouchKickImage = new ImageIcon(this.getClass().getResource("images/jkongCrouchKick.png"));
		this.blockImage = new ImageIcon(this.getClass().getResource("images/jkongBlock.png"));
		this.image = standImage.getImage();
		this.specialMove = new ImageIcon(this.getClass().getResource("images/jkongDP.png"));
		this.jumpImage = new ImageIcon(this.getClass().getResource("images/jkongJump.png"));
		this.jumpAttackImage = new ImageIcon(this.getClass().getResource("images/jkongJumpAttack.png"));
		//FireBall
		if(this.id.equals(ID.player)){
			//keyCodes for fireball
			this.moveListL.add(235);
			this.moveListR.add(232);
			//keyCodes for DP
			this.moveListL.add(220);
			this.moveListR.add(214);
		}else{
			//keyCodes for fireball
			this.moveListL.add(157);
			this.moveListR.add(155);
			//keyCodes for DP
			this.moveListL.add(156);
			this.moveListR.add(152);
		}
		
	}

	public void crouchPunch(){
		this.image = crouchPunchImage.getImage();
		int activelength = 11;
		this.damage = 5;
		this.advantage = activelength + 40;
		this.knockBack = 45;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx);
		this.setHity(this.y + 75);
		this.setHitW(100);
		this.setHitH(50);
		if(direction()){
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setHitx(this.x - this.getHitW());			
		}
		this.stop = timer + activelength;
	}
	public void punch(){
		this.image = punchImage.getImage();
		int activelength = 11;
		this.damage = 5;
		this.advantage = activelength + 40;
		this.knockBack = 45;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx);
		this.setHity(this.y + 50);
		this.setHitW(90);
		this.setHitH(50);
		if(direction()){
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setHitx(this.x - this.getHitW());			
		}
		this.stop = timer + activelength;
	}
	
	public void crouchKick(){
		this.image = crouchKickImage.getImage();
		int activeLength = 18;
		this.damage = 22;
		this.knockBack = 17;
		this.advantage = activeLength + 5;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx);
		this.setHity(this.y + this.hurty - 50);
		this.setHitW(73);
		this.setHitH(50);
		if(direction()){
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setHitx(this.x - this.getHitW());			
		}
		this.stop = timer + activeLength;
	}
	
	public void kick(){
		if(crouch){
			this.image = crouchKickImage.getImage();
		}else{
			this.image = kickImage.getImage();
		}
		int activeLength = 30;
		this.damage = 30;
		this.knockBack = 45;
		this.advantage = activeLength + 7;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx);
		this.setHity(this.y + 80);
		this.setHitW(125);
		this.setHitH(50);
		if(direction()){
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setHitx(this.x - this.getHitW());			
		}
		this.stop = timer + activeLength;
	}
	
	public void fireBall(){
		Music.play5();
		image = fireball.getImage();
		int activeLength = 41;
		this.setActive(true);
		handler.addObject(new Projectile(this.x+this.getHurtx()+100,this.y+40,ID.projectile,handler,opponent));
		this.setSpecial(true);
		this.stop = timer + activeLength;
	}
	
	public void dragonPunch(){
		Music.play4();
		this.special = true;
		this.image = specialMove.getImage();
		int activelength = 60;
		this.damage = 33;
		this.advantage = activelength + 20;
		this.knockBack = 300;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx-300);
		this.setHity(this.y + 75);
		this.setHitW(75);
		this.setHitH(250);
		if(direction()){
			this.setVelX(2);
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setVelX(-2);
			this.setHitx(this.x - this.getHitW());			
		}
		this.setVelY(-22);
		this.stop = timer + activelength;
	}
	
	
	public void doSpecial(int command){
		System.out.println("Cheff jeff");
		if(this.id.equals(ID.player)){
			switch(command){
			case 235:
			case 232:
				fireBall();
				break;
			case 220:
			case 214:
				dragonPunch();
			}
		}else{
			switch(command){
			case 155:
			case 157:
				fireBall();
				break;
			case 156:
			case 152:
				dragonPunch();
				break;
			}
		}
		
	}
	
}
