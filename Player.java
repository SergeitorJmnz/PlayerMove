import apcs.Window;

public class Player {

   //vars
   int x = 250;
   int y = 425;
   int radius;
   
   //verticle speed
   int yspeed = 0;
   //hanging yspeed
   int oldyspeed = yspeed;
   //if up is held
   boolean yPressed = false;
   
   //character is facing right
   boolean faceR = true;
   //character is moving
   boolean moving = false;
   //character is moving up
   boolean moveUp = false;
   //Stores if standing
   boolean standing = true;
   
   //counts ticks while moving to sync aninmation
   int moveTick = 0;
   //number of ticks till new step is animated
   int takeStep = 5;
   //false = 1; true = 2; (look at walk png's)
   boolean step = false;
   
   //Constructors
   public Player()
   {
      x = 250;
      y = 425;
      radius = 25;//was 25
   }
   
   public Player(int x, int y, int rad)
   {
      this.x = x;
      this.y = y;
      radius = rad;
   }
	
   //Methods
   public void draw() {
   
   //if not moving
      if(!moving)
      {
      //check which way character is facing
         if(faceR)
         {
            Window.out.image("RightStand.png",x-radius,y-radius);
         }
         else
         {
            Window.out.image("LeftStand.png",x-radius,y-radius);
         }
      }
      //moving right
      else if(moving && !moveUp && faceR)
      {
         if(moveTick > takeStep)
         {
            step = !step;
            moveTick = 0;
         }
      
         if (step)
         {
            Window.out.image("RightWalk1.png",x-radius,y-radius);
         }
         else
         {
            Window.out.image("RightWalk2.png",x-radius,y-radius);
         }
      
      }
      //moveing left
      else if(moving && !moveUp && !faceR)
      {
         if(moveTick > takeStep)
         {
            step = !step;
            moveTick = 0;
         }
      
         if (step)
         {
            Window.out.image("LeftWalk1.png",x-radius,y-radius);
         }
         else
         {
            Window.out.image("LeftWalk2.png",x-radius,y-radius);
         }
      }
      //jump and right
      else if(faceR && moveUp)
      {
         Window.out.image("RightJump.png",x-radius,y-radius);
      }
      //jump and left
      else if(!faceR && moveUp)
      {
         Window.out.image("LeftJump.png",x-radius,y-radius);
      }
   }

//----------------------------------------------------------------------------------------
   public void move() {

		if (Window.key.pressed("up") && !yPressed) {
      
         
			yspeed = -20;//lower to increase jump hight
         yPressed = true;
		}
		else if (Window.key.pressed("left")) {
         
         faceR = false;
         
	    moving = true;
         
         moveTick++;
		}
		else if(Window.key.pressed("right")) {
         moving = true;
         faceR = true;

         moveTick++;
		}
		else
		{
			moving = false;
			
         standing = true;
      }
      
      //if on ground and up is not pressed
      if (yspeed == oldyspeed)
      {
         yPressed = false;
         moveUp = false;
      }
      else
      {
         moveUp = true;
      }

      
      oldyspeed = yspeed;
		
		yspeed = yspeed + 1;
		y = y + yspeed;
      
      
		if (closest != null) {
			if (Math.abs(x - closest.x) <= closest.width / 2 &&
				Math.abs(y + 25 - closest.y) <= 10) {
				y = closest.y - 25;
				yspeed = 0;
			}
		}
	}

   public void setClosestPlatform(Platform p) {
      closest = p;
   }
   
   public String toString()
   {
      return "x:" + x + ", y:" + y + ", r:" + radius;
   }
}
