import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Timer;
import javax.swing.Timer;
import java.awt.Color; // Import Color class
import java.awt.Graphics; 
import java.awt.Rectangle;
import java.awt.Graphics2D;

 class Play extends JPanel implements KeyListener , ActionListener
{
    boolean play = true;
	int score = 0;
	int totalBricks = 22;
	Timer timer;
    int delay = -3;///////////// speed of ball /////
	int playerX = 310;
	int ballposX = 120;
    int ballposY = 350;
    int ballXdir = -1;
    int ballYdir = -2;
     private Map map;

	public Play()
	{
		map=new Map(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paintComponent(Graphics g) 
	{
        super.paintComponent(g); // Call the parent class's paintComponent method
        draw(g);
    }
	public void draw(Graphics g)
	{
		//background///
		g.setColor(new Color(51, 153, 255));
		g.fillRect(1, 1, 692, 592);
		////border///
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691,0,3,592);

		//paddle//
		g.setColor(Color.blue);
		g.fillRect(playerX,550,100,20);

		//Ball//
		g.setColor(Color.PINK);
		g.fillOval(ballposX,ballposY,20,20);

		//draw bricks//
		map.draw((Graphics2D) g);

		g.dispose();
	}
	 public void actionPerformed(ActionEvent e) {
	 	timer.start();
	 	if(play)
	 	{
	 		// Ball - Pedal  interaction 
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) 
			{
				ballYdir = -ballYdir;
			}
	 		ballposX += ballXdir;
	 		ballposY += ballYdir;
	 		if(ballposX<0)
	 		{
	 			ballXdir =-ballXdir;
	 		}
	 		if(ballposY<0)
	 		{
	 			ballYdir =-ballYdir;
	 		}
	 		if(ballposX>670)
	 		{
	 			ballXdir =-ballXdir;
	 		}
	 	}
	 	repaint();
	 }
     public void keyTyped(KeyEvent e) {}
     public void keyReleased(KeyEvent e) {}

    
    public void keyPressed(KeyEvent e)
     {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){ // if right arrow key is pressed then paddle moves right ///
		  if(playerX >= 600) 
		{
		  playerX = 600;
		}
		 else 
		{
		  moveRight();
		}}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) { // if left arrow key is pressed then paddle moves left///
		if(playerX < 10) 
		{
			playerX = 10;
		} 
		else 
		{
			moveLeft();
		}
		}
  	 }

  	public void moveRight() { // paddle moves right by 50 pixels
		play = true;
		playerX += 50;
	}
	public void moveLeft() { // paddle moves left by 50 pixels
		play = true;
		playerX -= 50;
	}
}

//////////////////////////////////////////class 2 //////////////////////////////////

class Map
{
	public int map[][],brickWidth,brickHeight;
	public Map(int row,int col)
	{
		map = new int[row][col];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=1;
			}
		}
		brickWidth=540/col;
		brickHeight=150/row;
	}

	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j* brickWidth +80, i* brickHeight +50, brickWidth,brickHeight);
				}
			}
	}

}
}



////////////////////////////////// main class ///////////////////////////////////////
public class project
{
	public static void main (String []args)
	{
		JFrame obj= new JFrame();
		Play play= new Play();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Welcome to Breakout Ball");
		obj.setResizable(false);////changed
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(play);
	}

}