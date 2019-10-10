Feature: Save Position
  As a player, I want to be able to save the actual position during a Quoridor game so that I can continue the game at a later stage from the exact position.

  Background: 
    Given The game is running

  Scenario Outline: Save position
  	Given No file "<filename>" exists in the filesystem
    When The user initiates to save the game with name "<filename>"
    Then A file with "<filename>" shall be created in the filesystem

    Examples: 
      | filename           |
      | save_game_test.dat |

  Scenario Outline: Save position with existing file name
  	Given File "<filename>" exists in the filesystem
    When The user initiates to save the game with name "<filename>"
    And The user confirms to overwrite existing file
    Then File with "<filename>" shall be updated in the filesystem

    Examples: 
      | filename           |
      | save_game_test.dat |

  Scenario Outline: Save position cancelled due to existing file name
  	Given File "<filename>" exists in the filesystem
    When The user initiates to save the game with name "<filename>"
    And The user cancels to overwrite existing file
    Then File "<filename>" shall not be changed in the filesystem

    Examples: 
      | filename           |
      | save_game_test.dat |
      