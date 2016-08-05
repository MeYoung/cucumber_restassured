Feature: Bose Api test


  @fullRegression
  Scenario Outline:test get request
    When I send a GET request to "/bose-api/auth/user?cacheSlayer=<id>"
    Then the response status should be "200"
    #    校验返回json的某个字段值为XXX
    Then the JSON response "genericPlan" equals "false"
    Examples:
      | id            |
      | 1469513209147 |
      | 1469513209148 |

  @fullRegression
  Scenario:test get request111
    When I send a GET request to "/bose-api/plan/info?cacheSlayer=1469515117442&birthday=07-18-1956&carrierFamilyName=ANTHEM+PAPER&gender=F&method=PAPER&planKey=90121100:19&planType=pdp&zip=90001"
    Then the response status should be "200"
#    校验返回json的某个字段值为XXX
    Then the JSON response "genericPlan" equals "false"
    Then the JSON response "ehiCarrierId" equals "90121100"
    Then the JSON response "carrierName" equals "Anthem Blue Cross"

  @fullRegression
  Scenario Outline: use examples
    When I send a GET request to "/bose-api/plan/info?cacheSlayer=1469515117442&birthday=07-18-1956&carrierFamilyName=ANTHEM+PAPER&gender=F&method=PAPER&planKey=90121100:19&planType=pdp&zip=90001"
    Then the response status should be "200"
    Then the JSON response "<jsonPath>" equals "<value>"
    Examples:
      | jsonPath     | value             |
      | genericPlan  | false             |
      | ehiCarrierId | 90121100          |
      | carrierName  | Anthem Blue Cross |


  @sanity
  Scenario: test post request
    When I send a POST request to "/bose-api/session/create?scriptId=1&firstName=vrtyty&middleName=&lastName=vrtrt&zip=&phoneNumber=&dnis=8882960117&inboundNumber=8882960117&ciscoCallId=&allid=EHM39972&userRole=Medicare%20Sales&callType=&ticket=U0sKREVGQVVMVF9DTRAAELc8caJhoWSZxrvFIWRZvn%2ByNR%2FRaw9PKXyrcH8wmA%2FIx5TCSEaKDMLwysGr2nOZ4ziDhqkbddJq9k7oHaaLAV6FnIPgPYsSLUKNba8F47qm&returnURL=https://www9.qp.ehealthinsurance.com/ehi/mc/search&boUserID=medicaresales-july&email=&subAllianceId=&continueCall=Y&elg=&isPFAMember=&wantToBePFAMember=&rxSessionId="
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
    When I send a POST request to "/bose-api/lead/agent-notes" and request json:
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
    When I send a POST request to "/bose-api/lead/agent-notes" and request json:
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
    When I use a "requestTest.json" file to send a POST request to "/bose-api/page/submit?sessionId=244357&pageNum=1&pageName=Identify%20Caller"
    Then the response status should be "200"
    Then the JSON response equals json file "reponseTest.json"
    And the JSON response "data.application.context_data.enter_when" equals "2016/07/25 23:06:45 -0700"

