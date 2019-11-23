Feature: Load Game
  As a player, I want to load a previously played game recorded in an algebraic notation in a text file 
  so that I can continue or review it. If the game is not yet finished, 
  I wish to continue playing from the final position.

  Background: 
    Given The game is not running

  Scenario Outline: Load valid incomplete game 
    When I initiate to load a game in "<filename>"
    And Each game move is valid
    And The game has no final results
    And The position to load is valid
    Then It shall be "<player>"'s turn
    And "<player>" shall be at <p_row>:<p_col>
    And "<opponent>" shall be at <o_row>:<o_col>
    And "<player>" shall have a <pw_orientation> wall at <pw_row>:<pw_col>
    And "<opponent>" shall have a <ow_orientation> wall at <ow_row>:<ow_col>
    And Both players shall have <remaining_walls> in their stacks
    And The game shall become ready to start

    Examples: 
      | filename                 | player | p_row | p_col | opponent | o_row | o_col | pw_row | pw_col | pw_orientation | ow_row | ow_col | ow_orientation | remaining_walls |
      | quoridor_test_game_1.mov | white  |     3 |     5 | black    |     7 |     5 |      6 |      5 | horizontal     |      3 |      5 | vertical       |               9 |
      | quoridor_test_game_2.mov | white  |     2 |     5 | black    |     8 |     5 |      6 |      5 | horizontal     |      3 |      5 | vertical       |               8 |

  Scenario Outline: Load valid incomplete game 
    When I initiate to load a game in "<filename>"
    And Each game move is valid
    And The game has a final result
    And The position to load is valid
    Then The game shall be in replay mode 

    Examples: 
      | filename                 | 
      | quoridor_test_game_3.mov |  

  Scenario Outline: Invalid move in game file
    When I initiate to load a saved game "<filename>"
    And The game to load has an invalid move
    Then The game shall notify the user that the game file is invalid

    Examples: 
      | filename                                 |
      | quoridor_test_game_invalid_pawn_move.mov |
      | quoridor_test_game_invalid_wall_move.mov |
      | quoridor_test_game_invalid_jump_move.mov |

