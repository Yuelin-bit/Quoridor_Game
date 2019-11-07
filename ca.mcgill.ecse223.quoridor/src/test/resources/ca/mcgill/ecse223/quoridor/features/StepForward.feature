Feature: Step forward
As a player using replay mode, 
I want to check the next move made by the next player in turn 
and see the board position after that move.

  Background: 
    Given The game is in replay mode

  Scenario Outline: Step Forward
    Given The following moves have been played in game:
      | mv | rnd | move |
      |  1 |   1 | e8   |
      |  1 |   2 | e2   |
      |  2 |   1 | e7   |
      |  2 |   2 | e3   |
      |  3 |   1 | e3h  |
      |  3 |   2 | e8h  |
      |  4 |   1 | d3v  |
      |  4 |   2 | f2   |
    And The next move is <movno>.<rndno>
    When Step backward is initiated
    Then The next move shall be <nmov>.<nrnd>
    And White player's position shall be (<wrow>,<wcol>)
    And Black player's position shall be (<brow>,<bcol>)
    And White has <wwallno> on stock
    And Black has <bwallno> on stock

    Examples: 
      | movno | rndno | nmov | nrnd | wrow | wcol | wwall | brow | bcol | bwallno |
      |     1 |     1 |    1 |    2 |    8 |    5 |    10 |    1 |    5 |      10 |
      |     1 |     2 |    2 |    1 |    8 |    5 |    10 |    2 |    5 |      10 |
      |     2 |     1 |    2 |    2 |    7 |    5 |    10 |    2 |    5 |      10 |
      |     2 |     2 |    3 |    1 |    7 |    5 |    10 |    3 |    5 |      10 |
      |     3 |     1 |    3 |    2 |    7 |    5 |     9 |    3 |    5 |      10 |
      |     3 |     2 |    4 |    1 |    7 |    5 |     9 |    3 |    5 |       9 |
      |     4 |     1 |    4 |    2 |    7 |    5 |     8 |    3 |    5 |       9 |
      |     4 |     2 |    5 |    1 |    7 |    5 |     8 |    3 |    6 |       9 |

