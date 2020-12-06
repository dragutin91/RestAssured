Feature: Symphony

  Scenario: Validate GET Users API ws
    Given I create /api/users request
    When I send GET request to the "api/users"
    Then I validate the JSON response
      | page | total | email                  | first_name | last_name |
      | 1    | 12    | george.bluth@reqres.in | George     | Bluth     |
      | 1    | 12    | janet.weaver@reqres.in | Janet      | Weaver    |


  Scenario: Validate when we send the POST request to a users API with empty body that we get Bad request - Negative scenario
    Given I create /api/register request without body
    When I send POST request to the "api/register"
    Then I get status "400"
    And I get the error "Missing email or username"


  Scenario: Validate when we send the POST request to a users API that user is successfully created
    When I send POST request to the "api/register" with JSON Body
    """
        {
         "email": "sydney@fife"
        }
    """
    Then I get status "400"
    And I get the error "Missing password"


  Scenario: Validate DELETE users API
    When I send DELETE request to the "api/users/2"
    Then I get status "204"

  Scenario: Validate GET Users API with delayed response
    Given I create /api/users request
    When I send GET request to the "api/users" with query parameter "?delay=5"
    Then I validate that delay is less than "5"
    Then I validate the JSON response
      | page | total | email                  | first_name | last_name |
      | 1    | 12    | george.bluth@reqres.in | George     | Bluth     |
      | 1    | 12    | janet.weaver@reqres.in | Janet      | Weaver    |
      #PROVERITI I OSTALE

