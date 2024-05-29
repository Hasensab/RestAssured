Feature: Add List of users
Scenario: Add user
Given user should be on reqres url
When user should enter "<name>" and "<job>"
And hit on users api
Then add user to list
