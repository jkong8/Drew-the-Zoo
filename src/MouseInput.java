import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	private Game game;
	private Handler handler;
	protected Boolean f1S=false, f2S=false;
	public MouseInput(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	public void mouseClicked(MouseEvent e){
		//System.out.println(e);
		//FIGHT START BUTTON

		if(game.getGameState().equals("Menu") || game.getGameState().equals("Credit")){
			if(e.getX() >= 1280/2 - 101 && e.getX() <= 1280/2 + 102 && e.getY() >= 720/2-54+50 && e.getY() <= 720/2+54+50){
				if(!f1S && !f2S){
					Music.play7();
					System.out.println("Fighter added");
					Fighter p1 = new Jeffery(0,(720-325),ID.player, game, handler);
					Fighter p2 = new Jaquan(1280, (720-325 ), ID.player2, game, handler);
					p1.assignOpponent(p2);
					p2.assignOpponent(p1);
					handler.addObject(p1);
					handler.addObject(p2);
					game.addKeyListener(new KeyInput(handler));
					//Stops from adding a whole bunch of people v
					//f1S = true;
					//f2S = true;
				}
				System.out.println("fight");
				game.setGameState("Fight");
			}
		}	
			//CREDIT BUTTON
			if(e.getX() >= 1280/2 - 133 && e.getX() <= 1280/2 + 133 && e.getY() >= 720/2 -53+150+50 && e.getY() <= 720/2+53+150+50){
				System.out.println("Credit");
				Music.play7();
				game.setGameState("Credit"); //ADD CREDIT STUFF LATER
				//game.removeMouseListener(this);
			}
			
			//BACK BUTTON
			if(e.getX()>=30 && e.getX() < 30+178 && e.getY()>=(720-168) && e.getY()<(720-168+108)){
				Music.play7();
				if(game.getGameState().equals("Credit")){
					game.setGameState("Menu");
				}else{
					System.out.println("Back");
					System.exit(1);
				}	
			}
		}
	
}
