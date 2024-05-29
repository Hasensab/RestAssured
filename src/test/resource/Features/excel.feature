Feature: Adding the users to the list.
Scenario: Add user
Given user is on reqres url
When user enters the name and job
And user hit the users API
Then users are added to list
