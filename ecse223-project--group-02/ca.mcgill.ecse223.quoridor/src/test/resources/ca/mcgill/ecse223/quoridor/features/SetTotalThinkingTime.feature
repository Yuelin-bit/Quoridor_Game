Feature: Set Total Thinking Time
  As a player, I wish to set the total thinking time (minutes and seconds) 
  enforced for both players to ensure that a game does not last forever 
  before the game starts.

  Background: 
    Given A new game is initializing

  Scenario Outline: Set thinking time for players
    When <min>:<sec> is set as the thinking time
    Then Both players shall have <min>:<sec> remaining time left

    Examples:
      | min | sec |
      |   5 |  13 |
      |  10 |  00 |
