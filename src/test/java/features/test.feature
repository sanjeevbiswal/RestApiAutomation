Feature: Testing Rest apis

  Scenario: Testing rest apis
    Given I got response from rest api
    And I get hourly data from hourly

  @animals

  Scenario: Testing animals api
    Given I got respnse from animal api

  @holidayList
  Scenario Outline: Getting Holiday list for a country from Holiday API
    #https://calendarific.com/api/v2/holidays?country=AF&year=2019&api_key=fdbf15ac72df12a0ba035829b23035fe69aa4c89
    Given I got holiday list for Country "<country>" and Year <year>

    Examples:
      | country | year |
      | AF      | 2019 |