Feature: Rotate Wall
  As a player who grabbed a wall, I want to rotate the wall by 90 degrees 
  (from horizontal to vertical or vice versa) to adjust its designated target position.

  Background: 
    Given The game is running
    And It is my turn to move
    And I have a wall in my hand over the board

  Scenario Outline: Flip wall from horizontal to vertical or vice versa
    Given A wall move candidate exists with "<dir>" at position (<row>, <col>)
    When I try to flip the wall
    Then The wall shall be rotated over the board to "<newdir>"
    And A wall move candidate shall exist with "<newdir>" at position (<row>, <col>)

    Examples: 
      | dir        | row | col | newdir     |
      | horizontal |   3 |   2 | vertical   |
      | vertical   |   5 |   6 | horizontal |
