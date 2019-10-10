Feature: Start New Game
  As a Quoridor player, I want to start a new game of Quoridor against some opponent. 

  Background: 
    Given The game is not running

  Scenario: Initiate a new game
    When A new game is initializing
    And White player chooses a username
    And Black player chooses a username
    And Total thinking time is set
    Then The game is ready to start

  Scenario: Start clock 
  	Given The game is ready to start
  	When I start the clock
  	Then The game is running
  	And The board is initialized
