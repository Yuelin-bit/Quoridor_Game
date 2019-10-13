Feature: Switch Current Player
  As a player, I wish to know when it is my turn to move. 
  My clock shall be counting down then. Once my move is finished,
  my clock shall be stopped and it shall be my opponent's turn.

  Background: 
    Given The game is running

  Scenario Outline: Switch current player
    Given The player to move is "<player>"
    And The clock of "<player>" is running
    And The clock of "<other>" is stopped
    When Player "<player>" completes his move
    Then The user interface shall be showing it is "<other>" turn
    And The clock of "<player>" shall be stopped
    And The clock of "<other>" shall be running
    And The next player to move shall be "<other>" 

    Examples: 
      | player | other |
      | white  | black |
      | black  | white |
