Feature: Load Position
  As a player, I want to continue a game from a given position as if this position were the current (starting) position of a new game.

  Background: 
    Given The game is not running

  Scenario Outline: Load position
    When I initiate to load a saved game <filename>
    And The position to load is valid
    Then It is <player>'s turn
    And <player> is at <p_row>:<p_col>
    And <opponent> is at <o_row>:<o_col>
    And <player> has a <pw_orientation> wall at <pw_row>:<pw_col>
    And <opponent> has a <ow_orientation> wall at <ow_row>:<ow_col>
    And Both players have <remaining_walls> in their stacks
    And It is <player>'s turn

    Examples: 
      | filename                 | player | p_row | p_col | opponent | o_row | o_col | pw_row | pw_col | pw_orientation | ow_row | ow_col | ow_orientation | remaining_walls |
      | quoridor_test_game_1.dat | player |     3 |     3 | white    |     7 |     7 |      5 |      5 | vertical       |      1 |      1 | horizontal     |               9 |

  Scenario Outline: Load invalid position
    When I initiate to load a saved game <filename>
    And The position to load is invalid
    Then The load returns <result>

    Examples: 
      | filename                                         | result  |
#     | quoridor_test_game_valid.dat                     | success |
      | quoridor_test_game_invalid_pawn.dat              | error   |
      | quoridor_test_game_invalid_wall_overlap_.dat     | error   |
      | quoridor_test_game_invalid_wall_out-of-track.dat | error   |
      