Feature: cucumber API Demo


  @fullRegression
  Scenario Outline:test get request
    When I send a GET request to "XXXXXX"
    Then the response status should be "200"
    #    校验返回json的某个字段值为XXX
    Then the JSON response "genericPlan" equals "false"
    Examples:
      | id            |
      | 1469513209147 |
      | 1469513209148 |

  @fullRegression
  Scenario:test get request111
    When I send a GET request to "XXXXXX"
    Then the response status should be "200"
#    校验返回json的某个字段值为XXX
    Then the JSON response "genericPlan" equals "false"
    Then the JSON response "ehiCarrierId" equals "90121100"
    Then the JSON response "carrierName" equals "Anthem Blue Cross"

  @fullRegression
  Scenario Outline: use examples
    When I send a GET request to "XXXXXXX"
    Then the response status should be "200"
    Then the JSON response "<jsonPath>" equals "<value>"
    Examples:
      | jsonPath     | value             |
      | genericPlan  | false             |
      | ehiCarrierId | 90121100          |
      | carrierName  | Anthem Blue Cross |


  @sanity
  Scenario: test post request
    When I send a POST request to "XXXXXXXX"
    Then the response status should be "200"
    And the JSON response "message" equals "success"
    #    校验放回值是否是某种类型
    And the JSON response "sessionId" type should be "^\d{6}$"
#    校验返回值不为空
    And the JSON response "url" should be not null
#    校验是否以XX开头
    Then the JSON response "message" start with "su"
    #    校验是否以XX开头
    Then the JSON response "message" end with "ss"
    #    校验是否以XX开头
    Then the JSON response "message" include "ss"


  @unimplemented
  Scenario: test post request
    When I send a POST request to "XXXXX" and request json:
    """
      {
        "sessionId": 244636,
        "agentNotes": "notepad"
      }
    """
    Then the response status should be "200"
    And the JSON response "result" equals "success"


  @unimplemented
  Scenario: test post request
    When I send a POST request to "XXXXX" and request json:
    """
      {
        "sessionId": 244636,
        "agentNotes": "notepad"
      }
    """
    Then the response status should be "200"
#    校验返回json是否为XXX，对整个返回json的校验
    Then the JSON response equals
    """
      {
          "result":"success"
      }
    """

  @unimplemented
  Scenario: 返回的json直接跟某文件校验对比
    When I use a "requestTest.json" file to send a POST request to "XXXXX"
    Then the response status should be "200"
    Then the JSON response equals json file "reponseTest.json"
    And the JSON response "data.application.context_data.enter_when" equals "2016/07/25 23:06:45 -0700"

