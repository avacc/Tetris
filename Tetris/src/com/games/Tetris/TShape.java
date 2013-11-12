package com.games.Tetris;

import java.awt.Color;
import java.util.Random;

public class TShape {
      private static Random random = new Random();
      private Color[][] filled = new Color[4][4];
      private int x;
      private int y;
      private int width;
      private int height;
      private int velocityY;
      private int rightBound;
      private int bottomBound;
      private int orientationNum;
      private TShapeOrientation orientation;
      private Color color;

      public TShape(Color[][] filled, int velocityY, int x, int y,
                    TShapeOrientation orientation, int orientationNum) {
        this.filled = filled;
        this.velocityY = velocityY;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.orientationNum = orientationNum;

        setDimensions();
      }
  
  private static Color[][][] orientations1 = new Color[][][]
   	   {{{false, Color.BLUE, false, false},
  	  	 {false, Color.BLUE, false, false},
  	  	 {false, Color.BLUE, false, false},
  	  	 {false, Color.BLUE, false, false}},

  	  	{{false, false, false, false},
 	  	   {Color.BLUE,  Color.BLUE,  Color.BLUE,  Color.BLUE},
 	  	   {false, false, false, false},
 	  	   {false, false, false, false}}};

  private static Color[][][] orientations2 = new Color[][][]
   		 {{{true,   false , false, false},
    	 	 {true,   true,    false, false},
      	 {true,   false,  false, false},
   	   	 {false, false,  false, false}},

  	   	{{true,     true ,   true,   false},
     	   {false,   true,    false, false},
     	 	 {false,   false,  false, false},
       	 {false,   false,  false, false}},

       	{{false,   false,  true,   false},
     	 	 {false,   true,    true,   false},
       	 {false,   false,  true,   false},
   	   	 {false,   false,  false, false}},

   	   	{{false,   false , false, false},
     	   {false,   true,    false, false},
     	 	 {true,     true,    true,   false},
       	 {false,   false,  false, false}}};

  private static Color[][][] orientations3 = new Color[][][]
   		 {{{true, true, false, false},
   	   	 {false, true, true, false},
   	   	 {false, false, false, false},
   	   	 {false, false, false, false}},

   	   	{{false, false, true, false},
     	   {false, true, true, false},
     	 	 {false, true, false, false},
       	 {false, false, false, false}},

       	{{false, false, false, false},
     	 	 {true, true, false, false},
     	 	 {false, true, true, false},
     	 	 {false, false, false, false}},

     	 	{{false, true, false, false},
     	   {true, true, false, false},
     	 	 {true, false, false, false},
       	 {false, false, false, false}}};


  private static Color[][][] orientations4 = new Color[][][]
   		 {{{true, true, false, false},
   	   	 {true, true, false, false},
   	   	 {false,   false,   false, false},
   	   	 {false,   false,   false, false}}};


  private static Color[][][] orientations5 = new Color[][][]
   		 {{{true, false,  false, false},
   	   	 {true, false,  false, false},
   	   	 {true, true, false, false},
   	   	 {false,  false,  false, false}},

   	   	{{false,  false,  false,  false},
     	   {true, true, true, false},
     	 	 {true, false,  false,  false},
       	 {false,  false,  false,  false}},

       	{{false , false,   false, false},
     	   {true, true,  false, false},
     	   {false,  true,  false, false},
     	   {false,  true,  false, false}},

     	  {{false,  false,  false,  false},
     	 	 {false,  false,  true, false},
       	 {true, true, true, false},
   	   	 {false,  false,  false,  false}}};


  public void setDimensions() {
  	int w = 0;
  	int h = 0;
  	for(int y = 0; y < 4; y++) {
  		for(int x = 0; x < 4; x++) {
  			if(filled[y][x] != false) {
  				if(x + 1 > w)
  					w = x + 1;
  				if(y + 1 > h)
  					h = y + 1;
  			}
  		}
  	}
  	width = w;
  	height = h;
  }

  public void setBounds(int width, int height) {
  	rightBound = width - this.width;
  	bottomBound = height;
  }
  
  public void setFilled (Color[][] filled) {
  	this.filled = filled;
  }
  
  public void setVelocity(int velocityY) {
  	this.velocityY = velocityY;
  } 
  
  public void setPosition(int x, int y) {
  	this.x = x;
  	this.y = y;
  }
 
  public void clip() {
  	if(x < 0) {
  		x = 0;
  	}
  	else if(x >= rightBound) {
  		x = rightBound;
  	}
  	
  	if(y < 0) {
  		y = 0;
  	}
  }
  
  public void rotate(boolean direction) {
  	if(!direction) {
  		if(orientationNum == orientations.length - 1) {
  			filled = orientations[0];
  			orientationNum = 0;
  		}
  		else {
  			filled = orientations[orientationNum + 1];
  			orientationNum += 1;
  		}
  	}
  	else {
  		if(orientationNum == 0) {
  			filled = orientations[orientations.length - 1];
  			orientationNum = orientations.length - 1;
  		}
  		else {
  			filled = orientations[orientationNum - 1];
  			orientationNum -= 1;
  		}
  	}
  	setDimensions();
  	setBounds(15, 25);
  }
  
  public void move() {
		y += velocityY;
	}
  
  
  public static TShape makeShape() {
  	int randX = random.nextInt(11);
  	int shapeNum = random.nextInt(5);
  	TShape shape1 = new TShape(
   	  	new Color[][]
   	  	{{false, Color.BLUE, false, false},
   	  	 {false, Color.BLUE, false, false},
   	  	 {false, Color.BLUE, false, false},
   	  	 {false, Color.BLUE, false, false}}, 
   	  	  1, randX, 0, orientations1, 0); 
  	
  	TShape shape2 = new TShape(
   	    new Color[][]
   	   	{{true,   false , false, false},
   	   	 {true,   true,    false, false},
   	   	 {true,   false,  false, false},
   	   	 {false, false,  false, false}},1,
   	   	  randX, 0, orientations2, 0);
  	
  	TShape shape3 = new TShape(
   	   	new Color[][]
   	   	{{true, true, false, false},
   	   	 {false, true, true, false},
   	   	 {false, false, false, false},
   	   	 {false, false, false, false}}, 1, 
   	   	  randX, 0, orientations3, 0);
  	
  	TShape shape4 = new TShape(
   	   	new Color[][]
   	   	{{true, true, false, false},
   	   	 {true, true, false, false},
   	   	 {false,   false,   false, false},
   	   	 {false,   false,   false, false}},1, 
   	   	  randX, 0, orientations4, 0);
  	
  	TShape shape5 = new TShape(
   	   	new Color[][]
   	   	{{true, false,  false, false},
   	   	 {true, false,  false, false},
   	   	 {true, true, false, false},
   	   	 {false,  false,  false, false}},1, 
   	   	  randX, 0, orientations5, 0);
  	
  	if(shapeNum == 0) {
  		 return shape1;
  	 }
   	 else if(shapeNum == 1) {
  		 return shape2;
  	 }
  	 else if(shapeNum == 2) {
  		 return shape3;
  	 }
  	 else if(shapeNum == 3) {
  		 return shape4;
  	 }
  	 else {
  		 return shape5;
  	 }
  }
  
  public boolean checkCollision (TetrisPanel b) {
  	for(int y = 0; y < 4; y++) {
      for(int x = 0; x < 4; x++) {
      	if(((this.y + y > bottomBound) || (this.x + x > rightBound))
      			|| this.x + x < 0) {
  	  		if(filled[y][x] != false) {
   	            return true;
  	      }
  	    } 
      	if(filled[y][x] != false && 
  	  	  	b.tetrisArray[this.y + y][this.x + x] != false) {
      		this.y = this.y - 1;
  	      return true;
  	    }
  	    
  	  }
  	}  
  	return false;
  }
  
  public void draw(TetrisPanel b) {
  	for(int y = 0; y < 4; y++) {
  		for(int x = 0; x < 4; x++) {
  			if(filled[y][x] != false){
  				if(y+this.y < b.height && x+this.x < b.width)
  				  b.tetrisArray[y + this.y][x + this.x] = filled[y][x];
  			}
  		}
  	}
  }
  
  public void undraw(TetrisPanel b) {
  	for(int y = 0; y < 4; y++) {
  		for(int x = 0; x < 4; x++) {
  			if(filled[y][x] != false){
  				b.tetrisArray[y + this.y][x + this.x] = false;
  			}
  		}
  	}
  }

  private static class TShapeOrientation {

     private static final boolean[][][][] orientations =
        new boolean[][][][] {
            {
                {{false, true,  false, false},
                 {false, true,  false, false},
                 {false, true,  false, false},
                 {false, true,  false, false}},

                {{false, false, false, false},
                 {true,  true,  true,  true},
                 {false, false, false, false},
                 {false, false, false, false}}
            },

            {
                {{true,  false, false, false},
                 {true,  true,  false, false},
                 {true,  false, false, false},
                 {false, false, false, false}},

                {{true,  true,  true,  false},
                 {false, true,  false, false},
                 {false, false, false, false},
                 {false, false, false, false}},

                {{false, false, true,  false},
                 {false, true,  true,  false},
                 {false, false, true,  false},
                 {false, false, false, false}},

                {{false, false, false, false},
                 {false, true,  false, false},
                 {true,  true,  true,  false},
                 {false, false, false, false}}
            },

            {
                {{true,  true,  false, false},
                 {false, true,  true,  false},
                 {false, false, false, false},
                 {false, false, false, false}},

                {{false, false, true,  false},
                 {false, true,  true,  false},
                 {false, true,  false, false},
                 {false, false, false, false}},

                {{false, false, false, false},
                 {true,  true,  false, false},
                 {false, true,  true,  false},
                 {false, false, false, false}},

                {{false, true,  false, false},
                 {true,  true,  false, false},
                 {true,  false, false, false},
                 {false, false, false, false}}
            },

            {
                {{true,  true,  false, false},
                 {true,  true,  false, false},
                 {false, false, false, false},
                 {false, false, false, false}}
            },

            {
                {{true,  false, false, false},
                 {true,  false, false, false},
                 {true,  true,  false, false},
                 {false, false, false, false}},

                {{false, false, false, false},
                 {true,  true,  true,  false},
                 {true,  false, false, false},
                 {false, false, false, false}},

                {{false, false, false, false},
                 {true,  true,  false, false},
                 {false, true,  false, false},
                 {false, true,  false, false}},

                {{false, false, false, false},
                 {false, false, true,  false},
                 {true,  true,  true,  false},
                 {false, false, false, false}}
            }
        };

      private boolean[][] orientation;


     public TShapeOrientation() {

     }

     public TShapeOrientation(int orientationNum) {

     }



  }

  public class TShapeColor {

  }
}
