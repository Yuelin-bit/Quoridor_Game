Feature: Jump Pawn
  As a player
  I want to jump over my opponent when situated next to me 
  assuming there are no blocking walls

  Background: 
    Given The game is running

  Scenario Outline: Jump over opponent
    Given The player to move is "<player>"
    And The player is located at <prow>:<pcol>
    And The opponent is located at <orow>:<ocol>
    And There are no "<dir>" walls "<side>" from the player nearby
    When Player "<player>" initiates to move "<side>"
    Then The move "<side>" shall be "<status>"
    And Player's new position shall be <nrow>:<ncol>
    And The next player to move shall become "<nplayer>"

    Examples: 
      | player | prow | pcol | orow | ocol | dir        | side  | status  | nrow | ncol | nplayer |
      | white  |    3 |    3 |    3 |    2 | vertical   | left  | success |    3 |    1 | black   |
      | black  |    3 |    2 |    3 |    1 | vertical   | left  | illegal |    3 |    2 | black   |
      | black  |    6 |    6 |    6 |    7 | vertical   | right | success |    6 |    8 | white   |
      | white  |    6 |    8 |    6 |    9 | vertical   | right | illegal |    6 |    8 | white   |
      | black  |    3 |    3 |    2 |    3 | horizontal | up    | success |    1 |    3 | white   |
      | white  |    2 |    3 |    1 |    3 | horizontal | up    | illegal |    2 |    3 | white   |
      | white  |    6 |    6 |    7 |    6 | horizontal | down  | success |    8 |    6 | black   |
      | black  |    8 |    6 |    9 |    6 | horizontal | down  | illegal |    8 |    6 | black   |

  Scenario Outline: Move of player blocked by wall
    Given The player to move is "<player>"
    And The player is located at <row>:<col>
    And The opponent is located at <orow>:<ocol>
    And There is a "<dir>" wall at <wrow>:<wcol>
    When Player "<player>" initiates to move "<side>"
    Then The move "<side>" shall be "<status>"
    And Player's new position shall be <nrow>:<ncol>
    And The next player to move shall become "<player>"

    Examples: 
      | player | row | col | orow | ocol | dir      | wrow | wcol | side  | status  | nrow | ncol |
      | white  |   3 |   3 |    3 |    2 | vertical |    2 |    2 | left  | illegal |    3 |    3 |
      | black  |   3 |   3 |    3 |    2 | vertical |    3 |    1 | left  | illegal |    3 |    3 |
      | white  |   3 |   3 |    3 |    4 | vertical |    2 |    4 | right | illegal |    3 |    3 |
      | black  |   3 |   3 |    3 |    4 | vertical |    3 |    3 | right | illegal |    3 |    3 |
      | white  |   3 |   3 |    2 |    3 | vertical |    2 |    2 | up    | illegal |    3 |    3 |
      | black  |   3 |   3 |    2 |    3 | vertical |    1 |    3 | up    | illegal |    3 |    3 |
      | white  |   3 |   3 |    4 |    3 | vertical |    4 |    2 | down  | illegal |    3 |    3 |
      | black  |   3 |   3 |    4 |    3 | vertical |    3 |    3 | down  | illegal |    3 |    3 |
