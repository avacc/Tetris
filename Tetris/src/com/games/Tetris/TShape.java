package com.games.Tetris;

import java.awt.Color;
import java.util.Random;
import com.general.utils.Matrix;

@SuppressWarnings("unused")
public class TShape {
	private final static boolean[][] L_SHAPE = new boolean[][]
		{{true,  false, false, false},
		 {true,  false, false, false},
		 {true,  true,  false, false},
		 {false, false, false, false}};
	private final static boolean[][] SQ_SHAPE = new boolean[][]
		{{true,  true,  false, false},
		 {true,  true,  false, false},
		 {false, false, false, false},
		 {false, false, false, false}};
	private final static boolean[][] LINE_SHAPE = new boolean[][]
		{{false, true,  false, false},
		 {false, true,  false, false},
		 {false, true,  false, false},
		 {false, true,  false, false}};
	private final static boolean[][] T_SHAPE = new boolean[][]
		{{true,  true,  true,  false},
		 {false, true,  false, false},
		 {false, false, false, false},
		 {false, false, false, false}};
	private final static boolean[][] N_SHAPE = new boolean[][]
		{{true,  true,  false, false},
		 {false, true,  true,  false},
		 {false, false, false, false},
		 {false, false, false, false}};
	
	
	private boolean[][] shape;
    private int x;
    private int y;
    private int width;
    private int height;
    private int velocityY;
    private int rightBound;
    private int bottomBound;
    private Color color;

    public TShape(String shapeName, int velocityY, int x, int y) {
    	this.shape = LINE_SHAPE;
		this.velocityY = velocityY;
		this.x = x;
		this.y = y;
    }
   
    public void setVelocity(int velocityY) {
    	this.velocityY = velocityY;
    } 
  
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
 
    public void clip() {
    	if (x < 0) 
    		x = 0;
    	else if (x >= rightBound) 
    		x = rightBound;
    	if (y < 0) 
    		y = 0;
    }
  
  
  
    public void move() {
    	y += velocityY;
    }
   
  /*
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
  */
}
