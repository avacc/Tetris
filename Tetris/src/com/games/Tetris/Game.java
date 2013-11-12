package com.games.Tetris;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game {
	private static int score = 0;
	private static JLabel scoreDisplay = new JLabel("Score: " + score);
	
	public Game() {
		// Top-level frame
		final JFrame frame = new JFrame("Tetris");
		frame.setLocation(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main playing area
		final TetrisPanel board = new TetrisPanel();
		frame.add(board, BorderLayout.CENTER);

		
		final JPanel panel1 = new JPanel();
		frame.add(panel1, BorderLayout.NORTH);
		
	  // Reset button
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.reset();
			}
		});
		panel1.add(reset);
		
		final JPanel panel2 = new JPanel();
		frame.add(panel2, BorderLayout.EAST);
		panel2.add(scoreDisplay);

		// Put the frame on the screen
		frame.pack();
        frame.setVisible(true);
		// Start the game running
        board.reset();
	}
	
  
  public int getScore() {
    return score;
  }
  
  public static void updateScore(int amount) {
    if(amount == 0)
  	  score = amount; 
  	else if(amount == -1) {
  	  scoreDisplay.setText("Score: " + score + "\n GAME OVER");
  	}
  	else {
  		score += amount;
  	  scoreDisplay.setText("Score: " + score);
  	}  
  }

	/*
	 * Rather than directly building the top level frame object in the main
	 * method, we use the invokeLater utility method to ask the Swing framework
	 * to invoke the method 'run' of the Runnable object we pass it, at some
	 * later time that is convenient for it. (The key technical difference is
	 * that this will cause the new object to be created by a different
	 * "thread".)
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	}
}