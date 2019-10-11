Feature: Provide Or Select User Name
  As a prospective player, I wish to use my unique user name when a new game starts, 
  or create a new user name if the designated user name does not exist yet.

  Background: 
    Given A new game is initializing

  Scenario Outline: Select existing user name
<<<<<<< HEAD
    Given Next player to set user name is <color>
    And There is existing user <username>
    When The player selects existing <username>
    Then The name of player <color> in the new game shall be <username>
=======
    Given Next player to set user name is "<color>"
    And There is existing user "<username>"
    When The player selects existing "<username>"
    Then The name of player "<color>" in the new game shall be "<username>"
>>>>>>> yuelin_liu

    Examples: 
      | color | username |
      | white | Daniel   |
      | black | Marton   |

  Scenario Outline: Create new user name
<<<<<<< HEAD
    Given Next player to set user name is <color>
    And There is no existing user <username>
    When The player provides new user name: <username>
    Then The name of player <color> in the new game shall be <username>
=======
    Given Next player to set user name is "<color>"
    And There is no existing user "<username>"
    When The player provides new user name: "<username>"
    Then The name of player "<color>" in the new game shall be "<username>"
>>>>>>> yuelin_liu

    Examples: 
      | color | username |
      | white | Rijul    |
      | black | Hyacinth |

  Scenario Outline: User name already exists
<<<<<<< HEAD
    Given Next player to set user name is <color>
    And There is existing user <username>
    When The player provides new user name: <username>
    Then The player shall be warned that <username> already exists
    And Next player to set user name is <color>
=======
    Given Next player to set user name is "<color>"
    And There is existing user "<username>"
    When The player provides new user name: "<username>"
    Then The player shall be warned that "<username>" already exists
    And Next player to set user name shall be "<color>"
>>>>>>> yuelin_liu

    Examples: 
      | color | username |
      | white | Daniel   |
      | black | Marton   |
      