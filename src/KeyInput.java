import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

//Controller
public class KeyInput extends KeyAdapter{
	private Handler handler;
	private LinkedList<Fighter> fighters;
	private Fighter fighter;
	private Fighter fighter2;
	public KeyInput(Handler handler){
		this.handler = handler;
		fighters = this.handler.getAllFighters();
		for(int i = 0; i < fighters.size(); i++){
			if(fighters.get(i).getId()==ID.player){
				fighter = fighters.get(i);
			}
			if(fighters.get(i).getId()==ID.player2){
				fighter2 = fighters.get(i);
			}
		}
	}
	
	//Invokes when a key is pressed. Should increase velocity in direction
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		//control for player1-------------------------------------------------------------------------------------------------------------------------------------
		if(key == KeyEvent.VK_A){
			if(!fighter.getAirborne()){
				fighter.getCommand().enque(key);
				System.out.println(fighter.getCommand());
				if(!fighter.getSpecial()){
					fighter.setVelX(-7);//Doesn't do anything if airborne
				}
				
			}
			
			
		}else if(key == KeyEvent.VK_S){
			//INSERT CROUCH STATE HERE
			if(!fighter.getAirborne() && !fighter.getActive()){
				if(!fighter.isCrouching()){
					fighter.setCrouching(true);
					fighter.setHurty(150);
				}
				fighter.getCommand().enque(key);
				System.out.println(fighter.getCommand());
			}
			
			
		}else if(key == KeyEvent.VK_D){
			if(!fighter.getAirborne()){
				fighter.getCommand().enque(key);
				System.out.println(fighter.getCommand());
				if(!fighter.getSpecial()){
					fighter.setVelX(7);

				}
				
			}
			
			
		}
		
		
		else if( key == KeyEvent.VK_T){
			//******INSERT COMMAND KEY BEFORE ANYTHING!!!!!!!!!!!!!!!!!!!
			fighter.getCommand().enque(key);
			System.out.println(fighter.getCommand());
			if(!fighter.getActive()){
				if(!fighter.hasSpecial(fighter.getCommand().returnContent()) || fighter.isCrouching()){
					if(fighter.isCrouching()){
						fighter.crouchPunch();
					}else if(fighter.getAirborne()){
						System.out.println("JUMPY JUMP");
						fighter.jumpAttack();
					}else{
							fighter.punch();
						}
				}else{
					System.out.println("Special!");
					fighter.doSpecial(fighter.getCommand().returnContent());
				}
			}			
		}
		
		
		else if(key == KeyEvent.VK_Y){
			fighter.getCommand().enque(key);
			System.out.println(fighter.getCommand());
			if(!fighter.getActive() && !fighter.getAirborne()){
				if(!fighter.hasSpecial(fighter.getCommand().returnContent())){
					if(fighter.isCrouching()){
						fighter.crouchKick();
					}else{
						fighter.kick();
					}
				}else{
					fighter.doSpecial(fighter.getCommand().returnContent());
				}
			}
		}
		
		
		else if(key == KeyEvent.VK_C){
			fighter.setBlocking(true);		
		}
		//FIGHTER 2-------------------------------------------------------------------------------
		else if(key == KeyEvent.VK_LEFT){
			if(!fighter2.getAirborne()){
				fighter2.getCommand().enque(key);
				System.out.println(fighter2.getCommand());
				if(!fighter2.getSpecial()){
					fighter2.setVelX(-7);//Doesn't do anything if airborne
				}
				
			}
		}
		
		
		else if(key == KeyEvent.VK_DOWN){
			if(!fighter2.getAirborne() && !fighter2.getActive()){
				if(!fighter2.isCrouching()){
					fighter2.setCrouching(true);
					fighter2.setHurty(150);
				}
				fighter2.getCommand().enque(key);
				System.out.println(fighter2.getCommand());
			}
		}
		
		
		else if(key == KeyEvent.VK_RIGHT){
			if(!fighter2.getAirborne()){
				fighter2.getCommand().enque(key);
				System.out.println(fighter2.getCommand());
				if(!fighter2.getSpecial()){
					fighter2.setVelX(7);//Doesn't do anything if airborne
				}
				
			}
	
	}else if(key == KeyEvent.VK_SPACE){
		fighter2.setBlocking(true);
	}
	
	
	else if( key == KeyEvent.VK_N){
		//******INSERT COMMAND KEY BEFORE ANYTHING!!!!!!!!!!!!!!!!!!!
		fighter2.getCommand().enque(key);
		System.out.println(fighter2.getCommand());
		if(!fighter2.getActive() && !fighter2.getAirborne() ){
			if(!fighter2.hasSpecial(fighter2.getCommand().returnContent()) || fighter2.isCrouching()){
				if(fighter2.isCrouching()){
					fighter2.crouchPunch();
				}else{
					fighter2.punch();
				}
			}else{
				fighter2.doSpecial(fighter2.getCommand().returnContent());
			}
		}			
	}
	
	
	else if(key == KeyEvent.VK_M){
		fighter2.getCommand().enque(key);
		System.out.println(fighter2.getCommand());
		if(!fighter2.getActive() && !fighter2.getAirborne()){
			if(!fighter2.hasSpecial(fighter2.getCommand().returnContent())){
				if(fighter2.isCrouching()){
					fighter2.crouchKick();
				}else{
					fighter2.kick();
				}
			}else{
				fighter2.doSpecial(fighter2.getCommand().returnContent());
			}
		}
	}
	
	}
	
	//Invokes when a key is pressed. Should increase velocity in direction
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		//control for player1-------------------------------------------------------------------------------------------------------------------------------------
		if(key == KeyEvent.VK_A){
			if(!fighter.getAirborne()){
				fighter.setVelX(0);
			}	
		}else if(key == KeyEvent.VK_S){
			//INSERT CROUCH STATE HERE
			if(fighter.isCrouching()){
				fighter.setCrouching(false);
				//changes hurt box from crouching
				if(!fighter.getActive()){
					fighter.setHurty(300);
				}	
			}
		}else if(key == KeyEvent.VK_D){
			if(!fighter.getAirborne()){
				fighter.setVelX(0);
			}
		}else if(key == KeyEvent.VK_T || key == KeyEvent.VK_Y){
			if(fighter.readyToHit == true){
				fighter.setActive(false);
			}
		}else if(key == KeyEvent.VK_C){
			fighter.setBlocking(false);
		}
		else if(key == KeyEvent.VK_V){
			fighter.setHp(0);
		}
		//FIGHTER 2----------------------------------
		else if(key == KeyEvent.VK_LEFT){
			if(!fighter2.getAirborne()){
				fighter2.setVelX(0);
			}	
		}else if(key == KeyEvent.VK_DOWN){
			//INSERT CROUCH STATE HERE
			if(fighter2.isCrouching()){
				fighter2.setCrouching(false);
				//changes hurt box from crouching
				if(!fighter2.getActive()){
					fighter2.setHurty(300);
				}	
			}
		}else if(key == KeyEvent.VK_RIGHT){
			if(!fighter2.getAirborne()){
				fighter2.setVelX(0);
			}
		}else if(key == KeyEvent.VK_1 || key == KeyEvent.VK_2){
			if(fighter2.readyToHit == true){
				fighter2.setActive(false);
			}
		}else if(key == KeyEvent.VK_SPACE){
			fighter2.setBlocking(false);
		}
	}
}
