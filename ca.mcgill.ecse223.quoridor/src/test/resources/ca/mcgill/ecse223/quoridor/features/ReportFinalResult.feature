Feature: Report final result
	As a player, I want to be notified when I won, lost or draw a Quoridor game.
	
	Scenario: 
		When The game is no longer running
		Then The final result shall be displayed 
		And White's clock shall not be counting down
		And Black's clock shall not be counting down
		And White shall be unable to move
		And Black shall be unable to move
		