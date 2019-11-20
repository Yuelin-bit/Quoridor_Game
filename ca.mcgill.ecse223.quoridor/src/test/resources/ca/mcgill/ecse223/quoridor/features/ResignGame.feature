Feature: Resign game
  As a player, I wish to resign a game if I am sure that my opponent has a winning position.
	Background:
		Given The game is running
		
  Scenario Outline: Player resigns
    Given The player to move is "<player>"
    When Player initates to resign
    Then Game result shall be "<result>"
    And The game shall no longer be running

    Examples:
    | player | result   |
    | white  | BlackWon |
    | black  | WhiteWon |