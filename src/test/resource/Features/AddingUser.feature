@users
Feature: Adding the users to the list.
Background:
Given user is on reqres url
@update
Scenario: Updating user details
When enters name and job
|Alima|Doctor|
|Husen|Former|
And user hits the API
Then user data is updated
@add
Scenario Outline: Add user
When user enters the "<name>" and "<job>"
And user hit the users API
Then users are added to list

Examples:
|name|job|
|praveen|chef|
|hasen|Analyst|

