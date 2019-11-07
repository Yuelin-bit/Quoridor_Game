Feature: Identify if game drawn
  As a player, I want the Quoridor app to identify 
  if a game has a threefold repetition of moves (when the game is drawn) 
  and stop the game immediately.

  # We execute the following sequence of moves:
  # 1. e2 e8
  # 2. e1 e9
  # 3. e2 e8
  # 4. e1 e9
  # Unlike in chess, we check for the threefold repetition of moves (and not position)
  Background: 
    Given The game is running
    Given The following moves were executed:
      | move | turn | row | col |
      |    1 |    1 |   8 |   5 |
      |    1 |    2 |   2 |   5 |
      |    2 |    1 |   9 |   5 |
      |    2 |    2 |   1 |   5 |
      |    3 |    1 |   8 |   5 |
      |    3 |    2 |   2 |   5 |
      |    4 |    1 |   9 |   5 |
      |    4 |    2 |   1 |   5 |

  Scenario Outline: Player repeats for the third time
    Given Player "<player>" has just completed his move
    And The last move of "<player>" is pawn move to <row>:<col>
    When Checking of game result is initated
    Then Game result shall be "<result>"
    And The game shall no longer be running

    Examples: 
      | player | row | col | result |
      | white  |   8 |   5 | Drawn  |

  Scenario Outline: Player deviates for the third time
    Given Player "<player>" has just completed his move
    And The last move of "<player>" is pawn move to <row>:<col>
    When Checking of game result is initated
    Then Game result shall be "<result>"

    Examples: 
      | player | row | col | result  |
      | white  |   8 |   4 | Pending |
      | white  |   8 |   6 | Pending |
      