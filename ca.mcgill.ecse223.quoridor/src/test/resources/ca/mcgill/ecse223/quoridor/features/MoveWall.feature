Feature: Move Wall
  As a player who grabbed a wall, I wish to move the wall between possible rows and columns of the board 
  so that I could move it to its designated target position. 
  I wish to get feedback from the game if a designated wall position is illegal.

  Background: 
    Given The game is running
    And It is my turn to move
    And I have a wall in my hand over the board

  Scenario Outline: Move wall over the board
    Given A wall move candidate exists with <dir> at position (<row>, <col>)
    And The wall candidate is not at the <side> edge of the board
    When I try to move the wall <side>
    Then The wall shall be moved over the board to position (<nrow>, <ncol>)
    And A wall move candidate shall exist with <dir> at position (<nrow>, <ncol>)

    Examples: 
      | dir        | row | col | side  | nrow | ncol |
      | vertical   |   2 |   3 | left  |    2 |    2 |
      | horizontal |   2 |   3 | right |    2 |    4 |
      | vertical   |   5 |   6 | up    |    4 |    6 |
      | horizontal |   5 |   6 | down  |    6 |    6 |

  Scenario Outline: Move wall at the edge of the board
    Given A wall move candidate exists with <dir> at position (<row>, <col>)
    And The wall candidate is at the <side> edge of the board
    When I try to move the wall <side>
    Then I should be notified that my move is illegal 
    And A wall move candidate shall exist with <dir> at position (<nrow>, <ncol>)

    Examples: 
      | dir        | row | col | side  | nrow | ncol |
      | vertical   |   2 |   1 | left  |    2 |    3 |
      | horizontal |   2 |   8 | right |    2 |    8 |
      | vertical   |   1 |   6 | up    |    1 |    6 |
      | horizontal |   8 |   6 | down  |    8 |    6 |
      