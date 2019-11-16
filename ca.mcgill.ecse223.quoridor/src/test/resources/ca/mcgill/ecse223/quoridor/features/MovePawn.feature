Feature: Move Pawn
  As a player
  I want to move to a neighboring tile.

  Background: 
    Given The game is running

  Scenario Outline: Move one tile
    Given The player to move is "<player>"
    And The player is located at <row>:<col>
    And There are no "<dir>" walls "<side>" from the player
    And The opponent is not "<side>" from the player
    When Player "<player>" initiates to move "<side>"
    Then The move "<side>" shall be "<status>"
    And Player's new position shall be <nrow>:<ncol>
    And The next player to move shall become "<nplayer>"

    Examples: 
      | player | row | col | dir        | side  | status  | nrow | ncol | nplayer |
      | white  |   3 |   3 | vertical   | left  | success |    3 |    2 | black   |
      | white  |   3 |   1 | vertical   | left  | illegal |    3 |    1 | white   |
      | white  |   6 |   6 | vertical   | right | success |    6 |    7 | black   |
      | white  |   6 |   9 | vertical   | right | illegal |    6 |    9 | white   |
      | white  |   3 |   3 | horizontal | up    | success |    2 |    3 | black   |
      | white  |   1 |   3 | horizontal | up    | illegal |    1 |    3 | white   |
      | white  |   6 |   6 | horizontal | down  | success |    7 |    6 | black   |
      | white  |   9 |   6 | horizontal | down  | illegal |    9 |    6 | white   |

  Scenario Outline: Move of player blocked by wall
    Given The player to move is "<player>"
    And The player is located at <prow>:<pcol>
    And There is a "<dir>" wall at <wrow>:<wcol>
    And The opponent is not "<side>" from the player
    When Player "<player>" initiates to move "<side>"
    Then The move "<side>" shall be "<status>"
    And Player's new position shall be <nrow>:<ncol>
    And The next player to move shall become "<nplayer>"

    Examples: 
      | player | prow | pcol | dir        | wrow | wcol | side  | status  | nrow | ncol | player |
      | white  |    3 |    3 | vertical   |    2 |    2 | left  | illegal |    3 |    3 | white  |
      | black  |    3 |    3 | vertical   |    3 |    2 | left  | illegal |    3 |    3 | black  |
      | white  |    3 |    3 | vertical   |    2 |    3 | right | illegal |    3 |    3 | white  |
      | black  |    3 |    3 | vertical   |    3 |    3 | right | illegal |    3 |    3 | black  |
      | white  |    3 |    3 | horizontal |    2 |    2 | up    | illegal |    3 |    3 | white  |
      | black  |    3 |    3 | horizontal |    2 |    3 | up    | illegal |    3 |    3 | black  |
      | white  |    3 |    3 | horizontal |    3 |    2 | down  | illegal |    3 |    3 | white  |
      | black  |    3 |    3 | horizontal |    3 |    3 | down  | illegal |    3 |    3 | black  |
