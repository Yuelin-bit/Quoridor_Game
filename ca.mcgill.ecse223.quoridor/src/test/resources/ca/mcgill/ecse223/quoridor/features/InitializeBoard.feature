Feature: Initialize Board
  As a player, I want to see the Quoridor board in its initial position and 
  my stock of walls and my clock is counting down 
  so that I can start playing the game. 
  
  #Background:
	#	Given The game is running
	
	Scenario: Initialize board
    When The initialization of the board is initiated 
    Then It is white player to move
		And White's pawn is in its initial position
		And Black's pawn is in its initial position
		And All of White's walls are in stock
		And All of Black's walls are in stock
		And White's clock is counting down
		And It is shown that this is White's turn

