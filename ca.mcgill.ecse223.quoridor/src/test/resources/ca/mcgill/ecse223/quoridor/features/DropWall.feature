Feature: Drop Wall
<<<<<<< HEAD
  As a player, I wish to drop a wall after I navigated it to a designated 
=======
  As a player, I wish to drop a wall after I navigated it to a designated 
>>>>>>> yuelin_liu
  (valid) target position in order to register my wall placement as my move.

  Background: 
    Given The game is running
<<<<<<< HEAD
    And The following walls exist:
=======
    Given The following walls exist:
>>>>>>> yuelin_liu
      | wrow | wcol | wdir       |
      |    1 |    1 | horizontal |
      |    7 |    4 | vertical   |
    And It is my turn to move
    And I have a wall in my hand over the board

  Scenario Outline: Valid wall placement 
<<<<<<< HEAD
    Given The wall move candidate with <dir> at position (<row>, <col>) is valid
    When I release the wall in my hand
    Then I do not have a wall in my hand
    But A wall move is registered with <dir> at position (<row>, <col>)
    And My move is completed
    And It is not my turn to move
=======
    Given The wall move candidate with "<dir>" at position (<row>, <col>) is valid
    When I release the wall in my hand
    Then A wall move shall be registered with "<dir>" at position (<row>, <col>)
    And I shall not have a wall in my hand
    And My move shall be completed
    And It shall not be my turn to move
>>>>>>> yuelin_liu

    Examples: 
      | dir        | row | col |
      | horizontal |   3 |   2 |
      | vertical   |   5 |   6 |

  Scenario Outline: Invalid wall placement
<<<<<<< HEAD
    Given The wall move candidate with <dir> at position (<row>, <col>) is invalid
    When I release the wall in my hand
    Then I shall be notified that my wall move is invalid
    And I have a wall in my hand over the board
    And It is my turn to move
  	But No wall move is registered with <dir> at position (<row>, <col>)
=======
    Given The wall move candidate with "<dir>" at position (<row>, <col>) is invalid
    When I release the wall in my hand
    Then I shall be notified that my wall move is invalid
    And I shall have a wall in my hand over the board
    And It shall be my turn to move
  	But No wall move shall be registered with "<dir>" at position (<row>, <col>)
>>>>>>> yuelin_liu
  	
    Examples: 
      | dir        | row | col |
      | vertical 	 |   1 |   1 |
      | horizontal |   1 |   2 |
  	  | horizontal |   7 |   4 |
      | vertical 	 |   6 |   6 |
  	