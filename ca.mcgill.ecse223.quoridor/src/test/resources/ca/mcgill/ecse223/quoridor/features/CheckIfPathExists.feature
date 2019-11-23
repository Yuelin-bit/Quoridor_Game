Feature: Check if path exists
  As a player, I want the app to verify current wall placement that 
  at least one path is still available that leads to my target area (i.e. the first row of my opponent).

  # White player aims to arrive to row 1 (up), Black player aims to arrive at row 9 (down)
  Background: 
    Given The game is running
    Given The following walls exist:
      | wrow | wcol | wdir       |
      |    3 |    1 | horizontal |
      |    3 |    3 | horizontal |
      |    4 |    4 | horizontal |
      |    4 |    6 | horizontal |
      |    4 |    8 | horizontal |
      |    3 |    6 | vertical   |

# Structure of existing walls
# R1
# R2
# R3 __ __   |
# R4     __ _|_ __

  Scenario Outline: Path to target area is not blocked
    Given A "<dir>" wall move candidate exists at position <frow>:<fcol>
    And The black player is located at <brow>:<bcol>
    And The white player is located at <wrow>:<wcol>
    When Check path existence is initiated
    Then Path is available for "<result>" player(s)

# A wall move candidate is placed at numerous places 
    Examples: 
      | dir        | frow | fcol | brow | bcol | wrow | wcol | result |
      | horizontal |    3 |    7 |    2 |    5 |    6 |    5 | both   |
      | horizontal |    4 |    2 |    2 |    5 |    6 |    5 | both   |
      | horizontal |    3 |    5 |    2 |    5 |    6 |    5 | none   |
      | horizontal |    3 |    5 |    6 |    5 |    2 |    5 | both   |
      | horizontal |    3 |    5 |    2 |    5 |    3 |    5 | white  |
      | horizontal |    3 |    5 |    5 |    5 |    6 |    5 | black  |
      | vertical   |    2 |    4 |    2 |    5 |    6 |    5 | both   |
      | vertical   |    3 |    4 |    2 |    5 |    6 |    5 | none   |
      | vertical   |    3 |    4 |    6 |    5 |    2 |    5 | both   |
      | vertical   |    3 |    4 |    5 |    5 |    6 |    5 | black  |
      | vertical   |    1 |    6 |    2 |    5 |    6 |    5 | both   |
      | vertical   |    1 |    6 |    2 |    7 |    6 |    5 | white  |
      | vertical   |    1 |    6 |    6 |    5 |    2 |    7 | both   |
