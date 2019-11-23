Feature: Save Game
As a player, I want to save the current game in a text file 
even if the game has not yet been finished so that I can continue or review it later.

  Background: 
    Given The game is running

  Scenario Outline: Save game
  	Given No file "<filename>" exists in the filesystem
    When The user initiates to save the game with name "<filename>"
    Then A file with "<filename>" shall be created in the filesystem

    Examples: 
      | filename           |
      | save_game_test.mov |

  Scenario Outline: Save game with existing file name
  	Given File "<filename>" exists in the filesystem
    When The user initiates to save the game with name "<filename>"
    And The user confirms to overwrite existing file
    Then File with "<filename>" shall be updated in the filesystem

    Examples: 
      | filename           |
      | save_game_test.mov |

  Scenario Outline: Save game cancelled due to existing file name
  	Given File "<filename>" exists in the filesystem
    When The user initiates to save the game with name "<filename>"
    And The user cancels to overwrite existing file
    Then File "<filename>" shall not be changed in the filesystem

    Examples: 
      | filename           |
      | save_game_test.mov |
      