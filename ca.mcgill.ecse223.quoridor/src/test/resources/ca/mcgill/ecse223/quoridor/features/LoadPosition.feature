Feature: Load Position
  As a player, I want to continue a game from a given position as if this position were the current (starting) position of a new game.

  Background: 
    Given The game is not running

  Scenario Outline: Load position
    When I initiate to load a saved game "<filename>"
    And The position to load is valid
    Then It shall be "<player>"'s turn
    And "<player>" shall be at <p_row>:<p_col>
    And "<opponent>" shall be at <o_row>:<o_col>
    And "<player>" shall have a <pw_orientation> wall at <pw_row>:<pw_col>
    And "<opponent>" shall have a <ow_orientation> wall at <ow_row>:<ow_col>
    And Both players shall have <remaining_walls> in their stacks
    And It shall be "<player>"'s turn

    Examples: 
      | filename                 | player | p_row | p_col | opponent | o_row | o_col | pw_row | pw_col | pw_orientation | ow_row | ow_col | ow_orientation | remaining_walls |
      | quoridor_test_game_1.dat | player |     3 |     3 | white    |     7 |     7 |      5 |      5 | vertical       |      1 |      1 | horizontal     |               9 |

  Scenario Outline: Load invalid position
    When I initiate to load a saved game "<filename>"
    And The position to load is invalid
    Then The load shall return an error

    Examples: 
      | filename                                         |
      | quoridor_test_game_invalid_pawn.dat              |
      | quoridor_test_game_invalid_wall_overlap_.dat     |
      | quoridor_test_game_invalid_wall_out-of-track.dat |
      