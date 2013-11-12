package com.games.Tetris;

import java.awt.*;
import java.awt.Shape;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class TetrisPanel extends JPanel{
  
	Random random = new Random();
  private int interval = 100;
  private Timer timer;
  
  final int BOARDWIDTH = 240;
  final int BOARDHEIGHT = 400;
  final Color[][] tetrisArray = new Color[25][15];
  public int width = 15;
  public int height = 25;
  
  final int SHAPE_VEL = 1;
  
  private java.awt.Shape currentShape;
  private java.awt.Shape checkShape;
  
  
  
  public TetrisPanel() {
  	setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
  	setBorder(BorderFactory.createLineBorder(Color.CYAN));
  	setFocusable(true);
  	
  	
  	
  	timer = new Timer(interval, new ActionListener() {
  		public void actionPerformed(ActionEvent e) { tick(); }});
  	timer.start();
  	
  	addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					updateTetrisArray("moveL");
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					updateTetrisArray("moveR");
				else if (e.getKeyCode() == KeyEvent.VK_A)
					updateTetrisArray("rccw");
				else if (e.getKeyCode() == KeyEvent.VK_S)
					updateTetrisArray("rcw");
			}
			
  	});
  }
  
  public void reset() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tetrisArray[y][x] = Color.BLACK;
			}
		}
		Game.updateScore(0);
		addShape();
		grabFocus();
		repaint();
	}

  public void addShape() {

  	currentShape = Shape.makeShape();
  	/*if(currentShape.checkCollision(this)) {
  		timer.stop();
  		Game.updateScore(-1);
  	}*/
  	currentShape.setBounds(width, height);
  	currentShape.draw(this);
  }

  void updateTetrisArray(String command) {
  	currentShape.undraw(this);
  	if(command.equals("rccw")) {
    	checkShape = currentShape;
    	checkShape.rotate(true);
    	if(checkShape.checkCollision(this)){
    	}
    	else {
    		currentShape = checkShape;
    	}  
    }
    else if(command.equals("rcw")) {
    	checkShape = currentShape;
    	checkShape.rotate(false);
    	if(checkShape.checkCollision(this)){
    	}
    	else {
    	  currentShape = checkShape;
    	}  
    }
    else if(command.equals("move")) {
    	checkShape = currentShape;
    	checkShape.move();
    	if(checkShape.checkCollision(this)){
    		currentShape.setVelocity(0);
    	}
    	else {
    	  currentShape = checkShape;
    	} 
    }
    else if(command.equals("moveR")) {
    	checkShape = currentShape;
    	checkShape.x += 1;
    	if(checkShape.checkCollision(this)){
    	}
    	else {
    	  currentShape = checkShape;
    	} 	
    }
    else if(command.equals("moveL")) {
    	checkShape = currentShape;
    	checkShape.x -= 1;
    	if(checkShape.checkCollision(this)){
     	}
    	else {
    	  currentShape = checkShape;
    	} 
    }
  	currentShape.setDimensions();
  	currentShape.draw(this);
  	repaint();
  }
  
  void tick() {
  	removeFilledRows();
  	if(currentShape.bottomBound - currentShape.height == currentShape.y
  			|| currentShape.velocityY == 0) {
  		addShape();
  	}
		updateTetrisArray("move");
	}
  
  public boolean rowFilled (int row) {
  	for(int x = 0; x < width; x++) {
  		if(tetrisArray[row][x] == Color.BLACK) {
  			return false;
  		}
  	}
  	return true;
  }
  
  public void removeFilledRows() {
  	int row = height - 1;
  	int fillRow = height - 1;
  	while(row >= 0) {
  		for(int col = 0; col < width; col++) {
  			tetrisArray[fillRow][col] = tetrisArray[row][col];
  		}
  		if(!rowFilled(row)) {
  			fillRow--;
  		}
  		else {
  			Game.updateScore(10);
  		}
  		row--;
  	}
  }
  
  public void paintSquare(Graphics g, int x, int y) {
  	Color color = tetrisArray[y][x];
  	g.setColor(color);
  	g.fill3DRect((x*16), (y*16), 16, 16, true);
  }
  
  public void paintComponent(Graphics g) {
  	super.paintComponent(g);
  	for (int y = 0; y < 25; y++) {
      for (int x = 0; x < 15; x++) {
        paintSquare(g, x, y);  
      }
  }
	}
}
