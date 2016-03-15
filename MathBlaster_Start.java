import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.math.*;
import java.net.URL;
import java.awt.Robot; 


public class MathBlaster_Start extends JFrame{
		public static void main(String[]args){
				MathBlaster_Start e = new MathBlaster_Start();
		}
		MathBlaster_Start(){//create a construcor and define the JFrame
			setSize(600,600);//set the content pane
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setResizable(true);
			MathBlaster_Starter p = new MathBlaster_Starter();			
			setContentPane(p);
			setVisible(true);
		}
}
	
 class MathBlaster_Starter extends JPanel implements ActionListener{
			JButton starter;
			JButton tutorialer;
			static CardLayout cl;
			static JPanel cards;
			static Start start;
			Tutorial tutorial;
			Game game;
			public boolean change = false;
			public boolean cstart = false;
			MathBlaster_Starter(){//create the constructor

				cards = new JPanel();				//make a card layout
				cl = new CardLayout();
				cards.setLayout(cl);
				start = new Start();
				tutorial = new Tutorial();
				game = new Game();
				starter = new JButton("start");			//create the JButtons
				starter.addActionListener(this);
				tutorialer = new JButton("tutorial");
				tutorialer.addActionListener(this);
				start.setLayout(null);
				
				start.setPreferredSize( new Dimension(600,600) ); //set the null layout para,meters
				start.add(starter);
				start.add(tutorialer);
				starter.setBounds(275,250,100,50);
				tutorialer.setBounds(275,350,100,50);
				cards.add(start,"start");
				cards.add(tutorial,"tutorial");
				cards.add(game,"game");
				add(cards);
				repaint();//call the paint component method
		}	
		
		
		public   void paintComponent(Graphics g){//create the paint component method
			super.paintComponent(g);
			BufferedImage icon32 = loadBufferedImage("/images/Unknown-3.jpeg");
			g.drawImage(icon32,0,0,600,600,this);
			cl.show(cards,"start");
			if(change == true){
				cl.show(cards,"tutorial");
			}
			else if(cstart == true){
				cl.show(cards,"game");
			}
			
		
		}	
		
		private BufferedImage loadBufferedImage(String string)
		{
		    try
		    {
		        BufferedImage bi = ImageIO.read(this.getClass().getResource(string));
		        return bi;
		    } catch (IOException e)
		    {
		        e.printStackTrace();
		    }
		    return null;
		}
		
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == tutorialer){
			change = true;
			repaint();
		}
		
		else if(e.getSource() == starter){
			cstart = true;
			repaint();
		}
	}
	
	
}

class Tutorial extends JPanel{
			Tutorial(){//create the constructor 
				setLayout(new GridLayout(3,1));
				repaint();
				//add the back button
			}
			public void paintComponent(Graphics g){//create the paint component method
				super.paintComponent(g);
				Image img1= Toolkit.getDefaultToolkit().getImage("type1.png");
				Image img2= Toolkit.getDefaultToolkit().getImage("type2.png");
				Image img3= Toolkit.getDefaultToolkit().getImage("type3.png");
				g.drawImage(img1,0,0,200,200,this);
				g.drawImage(img2,200,0,200,200,this);
				g.drawImage(img3,400,0,200,200,this);
			}
		// In the paint component method show the three different types of problems
		
	//In the action Listener if back is pressed run MathBlaster_Game.java
		
	
}

class Game extends JPanel  implements ActionListener,KeyListener,MouseListener{
	JTextField field;
	JButton next;
	String answer;
	JButton restart;
	
	public int problem;
	public int lives = 0;
	public int value = 0;		//add the components for the JText field on the bottom
	int variable =0;
	int one=0;
	int two=0;
	int oner = 0;
	int twor = 0;
	int once=0;
	public int score = 0;
	public String[] array = {"purity.png"};
	public boolean type1 = false;
	public boolean type2 = false;
	public boolean type3 = false;
	public boolean failed = false;
	public Robot robot;
	public  int runs=0;
	public int xstart = 300;
	public int ystart = 300;
	public int xend = 300;
	public int yend = 590;
	

	
	
	Game() {
		addKeyListener(this);
		addMouseListener(this);
		restart = new JButton("Home");
		field = new JTextField(10);
		next = new JButton("Submit");
		setLayout(null);		
		setPreferredSize( new Dimension(600,600));
		add(restart);
		add(field);
		add(next);
		restart.addActionListener(this);
		next.addActionListener(this);
		Icon icon = new ImageIcon("giphy.gif");
		JLabel label = new JLabel(icon);
		add(label);
		label.setBounds(0,0,600,600);
		next.setBounds(400,500,100,50);
		restart.setBounds(0,500,100,50);
		field.setBounds(250,500,100,50);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		here();
		
		//add the components for the JText field on the bottom
		
		//restart = new JButton("restart");
		//add(restart);//add the restart button
		//restart.addActionListener(this);
		
	}
		public void paintComponent(Graphics g){//create the paint component method// in the paint component method draw the first game screen accoridng to the what problem  method is chosen
		//at the end, call the lives method
		//define what happens when there are no lives remaing 
			
			super.paintComponent(g);
			BufferedImage purity = loadBufferedImage("/images/purity.png");
			Image icon = new ImageIcon(getClass().getResource("/images/giphy.gif")).getImage();
			
			
			g.drawImage(icon,0,0,600,600,this);
			g.setColor(Color.BLACK);
			g.drawRect(0,0,600,600);
			
				int rand = (int)(Math.random()*10);
				if(rand!=1){
					int randnum = (int)(Math.random()*10);
					if(randnum != 0)
						g.drawImage(purity,0,100,300,300,this);
					
				}
			
						//use a while loop to say what happens while you still have lives using the lives method
			//call the randProblems method
			
			g.setColor(Color.WHITE);
			
			if(type1==true){
				Font bigBoldFont = new Font("SansSerif", Font.BOLD, 24);
				g.setFont(bigBoldFont);
				g.drawString(one + "+" + two+ "=", 300,200);
				
				
			}else if(type2 == true){
				Font bigBoldFont = new Font("SansSerif", Font.BOLD, 24);
				g.setFont(bigBoldFont);
				g.drawString(oner + "+" + "y" + "=" + twor,300,200);
			}else if (type3==true){
				Font bigBoldFont = new Font("SansSerf", Font.BOLD, 24);
				g.setFont(bigBoldFont);
				g.drawString("x" + "+" + "y" + "=" + one,300,200);
			}
			
			g.drawString("Score = " + score + "",0,50);
			g.drawString("Lives = " + (3 -lives) + "",450,50);
			
			if(failed==true){
				g.setColor(Color.RED);
				g.drawString("You Failed",290,100);
				if(type1==true)
					g.drawString(value + "",290,300);
				else if(type2==true)
					g.drawString(variable + "",290,300);
				else if(type3==true)
					g.drawString(oner + "," + twor + "", 290, 300);
			}
			
			
				
				
				
					
		
			
			
				
		}
		
		private BufferedImage loadBufferedImage(String string)
		{
		    try
		    {
		        BufferedImage bi = ImageIO.read(this.getClass().getResource(string));
		        return bi;
		    } catch (IOException e)
		    {
		        e.printStackTrace();
		    }
		    return null;
		}
				
		public  void here(){
			if(lives!=3){
				problem = randProblem();
				if(problem ==1){
					firstProblem();
				}else if (problem==2){
					secondProblem();
				}else if (problem==3){
					thirdProblem();
				}
				
				
			}
		}
		
	
		
		
		public static int randProblem(){//create the randProblems method
			int x = (int)(Math.random()*3+1);	//In the randProblems method create a random number generator between 1 and 3.
			return x;
		}
		public void firstProblem(){//In the firstProblem method create the first type of problem
			one = (int) (Math.random()*10);//IT is a simple addition problem
			two = (int) (Math.random()*100);
			value = one + two;
			type1=true;
			type2=false;
			type3=false;
			repaint();

		}

		
		public void secondProblem(){//In the secondProblem method create the second tupe of problem
			oner = (int) (Math.random()*100);//IT has x + y = z, where x and z are given, and you have to find y.
			 twor = (int) (Math.random()*100);
			variable = twor-oner;
			type1=false;
			type2=true;
			type3=false;
			repaint();
		}
		
		public void thirdProblem(){//In the third problem method create the third type of problem
			once = (int) (Math.random()*100);//IT has x + y = z. where z is given and you have to choose a posible answer for x and y.
			type1=false;
			type2=false;
			type3=true;
			repaint();
			
		}
	
	
		
		public void keyTyped(KeyEvent evt){}
		
		 public void keyReleased(KeyEvent evt){}
		
		 public void keyPressed(KeyEvent evt){
				
		}
		
		public void mousePressed(MouseEvent evt) {
         requestFocus();
         repaint();
      }   
      
      
      public void mouseEntered(MouseEvent evt) { }  // Required by the
      public void mouseExited(MouseEvent evt) { }   //    MouseListener
      public void mouseReleased(MouseEvent evt) { }       
      public void mouseClicked(MouseEvent evt) { }
      
      public void actionPerformed(ActionEvent e){
    	  if(e.getSource()==restart){
    		  
    		  int x =300;
    		  int y = 300;
    		  robot.mouseMove(x, y);
    		  Runnable helloRunnable = new Runnable() {
    			    public void run() {
    			    	runs++;
    			    	for (int i=0; i<100; i++){  
    			    	    int mov_x = ((xend* i)/100) + (xstart*(100-i)/100);
    			    	    int mov_y = ((yend * i)/100) + (ystart*(100-i)/100);
    			    	    robot.mouseMove(mov_x,mov_y);
    			    	    robot.delay(10);
    			    	}
    			    	if(runs>=3){
    			    		if(type1==true){
    		    		        field.setText(value + "");
    		    		        	
    		    			}else if (type2==true){
    		    				field.setText(variable + "");
    		    				
    		    			}else if(type3==true){
    		    				field.setText(one-10 + "," + 10);
    		    			}
    			    		throw new RuntimeException();
    			    	}
    			    	
    			    }
    			};

    			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    			executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    			
    		  
    	  }
    	  if(lives!=3){
			if(e.getSource()==next){
				
				answer = field.getText();
				if(type1==true){
					if(Integer.parseInt(answer)==value){
						failed = false;
						type1 = false;
						type2 = false;
						type3 = false;
						score+=50;
						
						here();
					}else if(Integer.parseInt(answer)!=value){
						failed = true;
						lives+=1;
						if(lives==3){
							scorer();
						}
						System.out.println(lives + "1");
						

						repaint();
					}
						
				}
				else if(type2 == true){
					if(Integer.parseInt(answer)==variable){
						failed = false;
						score+=100;
						 
						type1 = false;
						type2 = true;
						type3 = false;
						here();
					}else{
						System.out.println(answer + " " + variable);
						failed = true;
						lives+=1;
						if(lives==3){
							scorer();
						}
						System.out.println(lives + "2");

						repaint();
					}
				}
				
				else if(type3 == true){
					String preanswer = field.getText();
					int answer1 = Integer.parseInt(preanswer.substring(0,preanswer.indexOf(",")));
				
					int answer2 = Integer.parseInt(preanswer.substring(preanswer.indexOf(",")+1));
					if(answer1 + answer2  == one){
						failed = false;
						score+=50;
						 
						type1 = false;
						type2 = true;
						type3 = false;
						here();
					}else{
						failed = true;
						oner=0;
						twor=one;
								
						lives+=1;
						if(lives==3){
							scorer();
						}
						System.out.println(lives + "3");
						repaint();
					}
				}
				
				
				
				
				
				
				
			}
    	  }
	  }
      
      public  void scorer(){
    	  score=0;
		  lives=0;
		 failed = false;
		  here();
      }
}

class Start extends JPanel{
	Image img;

	public void paintComponent(Graphics g){
		

	}
}


