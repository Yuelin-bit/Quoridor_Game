Feature: Jump to start position
As a player using replay mode, I wish to scroll fast to the very beginning of the game.

	  Background: 
    Given The game is in replay mode

  Scenario Outline: Jump to start 
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
    When Jump to start position is initiated
    Then The next move shall be <nmov>.<nrnd>
    And White player's position shall be (<wrow>,<wcol>)
    And Black player's position shall be (<brow>,<bcol>)
    And White has <wwallno> on stock
    And Black has <bwallno> on stock

    Examples: 
      | movno | rndno | nmov | nrnd | wrow | wcol | wwall | brow | bcol | bwallno |
      |     1 |     1 |    1 |    1 |    9 |    5 |    10 |    1 |    5 |      10 |
      |     1 |     2 |    1 |    1 |    9 |    5 |    10 |    1 |    5 |      10 |
      |     2 |     1 |    1 |    1 |    9 |    5 |    10 |    1 |    5 |      10 |
      |     2 |     2 |    1 |    1 |    9 |    5 |    10 |    1 |    5 |      10 |
      |     3 |     1 |    1 |    1 |    9 |    5 |    10 |    1 |    5 |      10 |
	