import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = -2786188649753636794L;
	public  int width = 1280;
	public  int height = 720;
	protected Boolean running = false;
	private Thread thread;
	private Handler handler;
	private String gameState = "Menu";//"images/byJeff.png"
	ImageIcon background = new ImageIcon(this.getClass().getResource("/images/mainMenu.png"));
	ImageIcon start = new ImageIcon(this.getClass().getResource("images/button.png"));
	ImageIcon credit = new ImageIcon(this.getClass().getResource("images/button2.png"));
	ImageIcon back = new ImageIcon(this.getClass().getResource("images/button3.png"));
	ImageIcon title1 = new ImageIcon(this.getClass().getResource("images/drew.png"));
	ImageIcon title2 = new ImageIcon(this.getClass().getResource("images/the.png"));
	ImageIcon title3 = new ImageIcon(this.getClass().getResource("images/zoo.png"));
	ImageIcon commandsL = new ImageIcon(this.getClass().getResource("images/commands.png"));
	ImageIcon me = new ImageIcon(this.getClass().getResource("images/byJeff.png"));
	Image credits = me.getImage();
	Image commands = commandsL.getImage();
	Image bg = background.getImage();
	Image button1 = start.getImage();
	Image button2 = credit.getImage();
	Image button3 = back.getImage();
	Image drew = title1.getImage();
	Image the = title2.getImage();
	Image zoo = title3.getImage();
	
	//Constructor for the game class!
	public Game(){
		new Music();
		new Window(width,height,this);
		handler = new Handler();
		this.addMouseListener(new MouseInput(this, handler));
		/*Fighter p1 = new Jeffery(0,(height-325),ID.player, this, handler);
		Fighter p2 = new Jeffery(width - 1000, (height-325 ), ID.player2, this, handler);
		p1.assignOpponent(p2);
		p2.assignOpponent(p1);
		handler.addObject(p1);
		handler.addObject(p2);
		this.addKeyListener(new KeyInput(handler)); //Uses the this.addKeyListener() to add a class that extends KeyListener to the component. (Canvas)	
		*/
	}
	
	//Start method to start the thread
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		//makes the running boolean true
		running = true;
	}
	
	//***IDK WHAT STOP DOES YET***
	public synchronized void stop(){
		System.out.println("The thread has stopped");
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	///Thread.start() starts this run() method
	public void run() {
			//INSERT THE GAME LOOP *VERY IMPORTANT TO LEARN!!!*
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0; //60 ticks a second
			double ns = 1000000000/amountOfTicks;
			double delta = 0;
			long timer = System.currentTimeMillis();
			int frames = 0;
			int endTimer = 0;
			while(running){
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				
				while(delta >= 1){
					if(this.gameState.equals("end")){
						endTimer++;
					}
					frames++;
					//System.out.println(frames);
					tick(delta, frames);
					delta--;
				}
				
				if(running){
					render();
				}
				if(System.currentTimeMillis() - timer >1000){
					timer += 1000;
					//System.out.println("FPS: " + frames);
					//frames = 0;
				}
				if(endTimer == 180){
					endTimer = 0;
					handler.removeAll();
					this.setGameState("Menu");
				}
			}
			stop();
		
	}
	//Handler.tick() to update the games position
	public void tick(double delta, int frames){
		if(this.gameState.equals("Menu")){
			//System.out.println("menus");
		}else if(this.gameState.equals("Fight")){
			handler.tick(delta, frames);
		}
			
	
	}
	public void render(){
		//this.getBufferStrategy() only works on Classes that extends Canvas (As far as I know) 
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			/*This would return
			"Thread-2" java.lang.IllegalStateException: Component must have a valid peer
			if I FORGET THE ADD THIS CLASS TO THE FRAME IN THE WINDOW CLASS*/
			this.createBufferStrategy(4);
			return;//restarts the render();
		}
		Graphics g = bs.getDrawGraphics();

		if(this.gameState.equals("Menu")){
			//RENDER GAME MENU!
			mainMenu(g);
		}else if(this.gameState.equals("Credit")){
			credits(g);
		}else if(this.gameState.equals("Fight")){
			//Runs handler.render(g) method. which runs each added GameObject's render() method.			
			ImageIcon i = new ImageIcon(this.getClass().getResource("images/stage.png"));
			Image image = i.getImage();
			g.drawImage(image, 0, 0, null);
			handler.render(g);
		}
		g.dispose(); //Clears everything in Graphics g
		bs.show(); //Shows bs.
	}
	
	//Draws Main Menu Screen
	public void mainMenu(Graphics g){
				Music.loop();
				g.drawImage(bg,0,0,null);
				g.drawImage(drew, 5, 50, null);
				g.drawImage(the,5, 50+drew.getHeight(null), null);
				g.drawImage(zoo, 5+drew.getWidth(null)+10, 0, null);
				g.setColor(Color.blue);
			//	g.fillRect(width/2-101, height/2-54, 203, 109);
				g.setColor(Color.orange);
			//	g.fillRect(width/2-133, height/2-53+150,267	 , 107);
				g.drawImage(button1, width/2-101, height/2-54+50, null);
				g.drawImage(button2,width/2-133, height/2-53+150+50,null);
				g.drawImage(button3, 30, height-108-60, null);
				g.setColor(Color.black);
				g.fillRect(drew.getWidth(null)+zoo.getWidth(null)+50+10, 40, 320, 670-30);
				g.setColor(Color.white);
				g.fillRect(drew.getWidth(null)+zoo.getWidth(null)+50+20, 50, 300, 670-50);
				g.drawImage(commands, drew.getWidth(null)+zoo.getWidth(null)+50+35, 50, null);
	}
	
	//Draws Credit Screen
	public void credits(Graphics g){
		g.drawImage(bg,0,0,null);
		g.drawImage(drew, 5, 50, null);
		g.drawImage(the,5, 50+drew.getHeight(null), null);
		g.drawImage(zoo, 5+drew.getWidth(null)+10, 0, null);
		g.drawImage(button3, 30, height-108-60, null);
		g.setColor(Color.black);
		g.fillRect(width/2-credits.getWidth(null)/2-10, height/2-10, credits.getWidth(null)+20, credits.getHeight(null)+20);
		g.drawImage(credits, width/2-credits.getWidth(null)/2, height/2, null);
		
	}
	
	public void setGameState(String string){
		this.gameState = string;
	}
	
	public String getGameState(){
		return this.gameState;
	}
	
	//Main Method
	public static void main(String[] args){
		System.out.println("Please Open!");
		new Game();
	}
	
	
}
