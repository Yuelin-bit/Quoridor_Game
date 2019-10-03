Feature: Validate Position
  As a player, I want to check if a given position is invalid with e.g. 
  overlapping walls or out-of-track pawn or wall positions.

  Scenario Outline: Validate pawn position
    Given A game position is supplied with pawn coordinate <row>:<col>
    When Validation of the position is initiated
    Then The position is <result>

    Examples: 
      | row | col | result |
      |   1 |   1 | ok     |
      |   9 |   9 | ok     |
      |   4 |   2 | ok     |
      |   0 |   3 | error  |
      |  -1 |   4 | error  |
      |   3 |   0 | error  |
      |   4 |  -1 | error  |
      |  10 |   5 | error  |
      |   5 |  10 | error  |

  Scenario Outline: Validate wall position
    Given A game position is supplied with wall coordinate <row>:<col>-<dir>
    When Validation of the position is initiated
    Then The position is <result>

    Examples: 
      | row | col | dir        | result |
      |   1 |   1 | horizontal | ok     |
      |   8 |   8 | vertical   | ok     |
      |   4 |   2 | horizontal | ok     |
      |   0 |   3 | vertical   | error  |
      |   3 |   0 | horizontal | error  |
      |   9 |   5 | vertical   | error  |
      |   5 |   9 | horizontal | error  |

  Scenario: Validate overlapping walls (all valid)
    Given The following walls exist:
      | wrow | wcol | wdir       |
      |    1 |    1 | horizontal |
      |    7 |    4 | vertical   |
    When Validation of the position is initiated
    Then The position is valid

  Scenario: Validate overlapping walls (invalid-1)
    Given The following walls exist:
      | wrow | wcol | wdir       |
      |    2 |    3 | horizontal |
      |    2 |    4 | horizontal |
    When Validation of the position is initiated
    Then The position is invalid

  Scenario: Validate overlapping walls (invalid-2)
    Given The following walls exist:
      | wrow | wcol | wdir     |
      |    3 |    2 | vertical |
      |    2 |    2 | vertical |
    When Validation of the position is initiated
    Then The position is invalid

  Scenario: Validate overlapping walls (invalid-3)
    Given The following walls exist:
      | wrow | wcol | wdir       |
      |    3 |    2 | vertical   |
      |    3 |    2 | horizontal |
    When Validation of the position is initiated
    Then The position is invalid
