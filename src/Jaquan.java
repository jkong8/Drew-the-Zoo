import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Jaquan extends Fighter{

	public Jaquan(int x, int y, ID id, Game game, Handler handler) {
		super(x, y, id, game, handler);
		this.standImage = new ImageIcon(this.getClass().getResource("images/jqStanding.png"));
		this.crouchImage = new ImageIcon(this.getClass().getResource("images/jqCrouch.png"));
		this.punchImage = new ImageIcon(this.getClass().getResource("images/jqPunch.png"));
		this.kickImage = new ImageIcon(this.getClass().getResource("images/jqKick.png"));
		this.crouchPunchImage = new ImageIcon(this.getClass().getResource("images/jqCrouchPunch.png"));
		this.crouchKickImage = new ImageIcon(this.getClass().getResource("images/jqCrouchKick.png"));
		this.blockImage = new ImageIcon(this.getClass().getResource("images/jkongBlock.png"));
		this.specialMove = new ImageIcon(this.getClass().getResource("images/jqPunch.png"));
		this.jumpImage = new ImageIcon(this.getClass().getResource("images/jkongJump.png"));
		this.jumpAttackImage = new ImageIcon(this.getClass().getResource("images/jkongJumpAttack.png"));
		this.image = standImage.getImage();
		//FireBall
		if(this.id.equals(ID.player)){
			//keyCodes for buffaloPunch
			this.moveListL.add(222);
			this.moveListR.add(222);
		}else{
			//keyCodes for buffaloPunch
			this.moveListL.add(153);
			this.moveListR.add(153);
		}
		
	}
	public void buffaloPunch(){
		Music.play6();
		this.special = true;
		this.image = specialMove.getImage();
		if(direction()){
			this.setVelX(10);
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setVelX(-10);
			this.setHitx(this.x - this.getHitW());			
		}
		int activelength = 35;
		this.damage = 20;
		this.advantage = activelength + 20;
		this.knockBack = 300;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx-300);
		this.setHity(this.y + 75);
		this.setHitW(150);
		this.setHitH(75);
		
		this.stop = timer + activelength;
	}
	public void crouchPunch(){
		this.image = crouchPunchImage.getImage();
		int activelength = 10;
		this.damage = 7;
		this.advantage = activelength + 50;
		this.knockBack = 45;
		this.setActive(true);
		this.setHity(this.y + 30);
		this.setHitW(80);
		this.setHitH(80);
		if(direction()){
			this.setHitx(this.x + this.hurtx);
		}else{
			this.setHitx(this.x - this.getHitW());			
		}
		this.stop = timer + activelength;
	}
	public void punch(){
		this.image = punchImage.getImage();
		int activelength = 13;
		this.damage = 6;
		this.advantage = activelength + 30;
		this.knockBack = 45;
		this.setActive(true);
		this.setHitx(this.x + this.hurtx);
		this.setHity(this.y + 60);
		this.setHitW(120);
		this.setHitH(40);
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
		int activeLength = 27;
		this.damage = 24;
		this.knockBack = 160;
		this.advantage = activeLength + 7;
		this.setActive(true);
		this.setHity(this.y - 40);
		this.setHitW(80);
		this.setHitH(100);
		if(direction()){
			this.setHitx(this.x + this.hurtx+50);
		}else{
			this.setHitx(this.x - this.getHitW()-50);			
		}
		this.stop = timer + activeLength;
	}
	

	
	
	public void doSpecial(int command){
		System.out.println("Hammerdown Brown");
		if(this.id.equals(ID.player)){
			switch(command){
			case 222:
				buffaloPunch();
				break;
			}
		}else{
			switch(command){
			case 153:
				buffaloPunch();
			}
			}
		}
		
}
