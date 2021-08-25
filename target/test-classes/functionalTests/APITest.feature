#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Test–Radio and Music Services
 
  
    Scenario: Get request and response time below 1000 ms
    Given I  send GET HTTP request "https://testapi.io/api/ottplatform/media"
    Then  I recieve valid HTTP response code 200
    And verify response time below 4000 ms
  
    Scenario: Verify ID and Segment Type
    Given I  send GET HTTP request "https://testapi.io/api/ottplatform/media"
    Then  verify id field is not null or empty
    And segment_type for all track is "music"
 
    Scenario: Verify primary field in title
    Given I  send GET HTTP request "https://testapi.io/api/ottplatform/media"
    Then  verify primary field in the title is not null or empty
  
    Scenario: Verify Track list
    Given I  send GET HTTP request "https://testapi.io/api/ottplatform/media"
    Then  verify only one track list has "now_playing" field in "offset" as true
    
    