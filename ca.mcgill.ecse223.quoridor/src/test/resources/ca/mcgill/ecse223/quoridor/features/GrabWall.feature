Feature: Grab Wall
  As a player, I want to grab one of my walls in my stock 
  to initiate wall placement as a move.

	Background:
		Given The game is running
		And It is my turn to move
		And I do not have a wall in my hand
  
	Scenario: Start wall placement
    Given  I have more walls on stock
		When I try to grab a wall from my stock
<<<<<<< HEAD
		Then I have a wall in my hand over the board
		And The wall in my hand should disappear from my stock
		And A wall move candidate shall be created at initial position
=======
		Then A wall move candidate shall be created at initial position
		And I shall have a wall in my hand over the board
		And The wall in my hand shall disappear from my stock
>>>>>>> yuelin_liu
		
	Scenario: No more walls in stock
    Given  I have no more walls on stock
		When I try to grab a wall from my stock
<<<<<<< HEAD
		Then I should be notified that I have no more walls
		But I do not have a wall in my hand 
=======
		Then I shall be notified that I have no more walls
		And I shall have no walls in my hand 
>>>>>>> yuelin_liu
		
