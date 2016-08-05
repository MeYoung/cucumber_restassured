## 框架实现
1.	通过引入cucumber相关包，实现支持gerkin语言编写case。
2.	引入Rest Assured 并做简要的Post 和 Get请求封装，实现Restful类型API的POST和GET请求。
3.	引入TestNG 并继承Assert，增加部分校验方式。
4.	引入log4j 完成log日志
5.	引入json-unit，完成对两个json文件的校验


## 快速入门：
### 发起Get请求step：
```
  When I send a GET request to "Api URL"
```
### 发起Post请求，不带json：
```
I send a POST request to "Api URL"
```
### 发起带body的post请求：
```
    When I send a POST request to "API URL" and request json:
    """
      {
        "key1": "value1",
        "key2": "value2"
      }
    """
```
*注意：不要遗漏了 ： 和 """ """*
### 校验返回的状态码：
```
Then the response status should be "XXX"
```
*注意：最后的xxx 只能是3位数据*
### 校验json返回的某个值:
假设需要校验如下json中的result值是否是success：

```
{    "result":"success"}
```

step写法：

```
Then the JSON response "result" equals "success"
```

假设需要校验如下json中的rxSessionId值：

step写法：

```
{
    "rxSessionId": "R1D9VDLN",
    "medicareLeadExtension": {
        "rxSessionId": "123"
    },
    "leadMembers": [
        {
            "phoneType": "1"
        }
    ],
    "leadMembers": [
        {
            "phoneType": "2"
        }
    ]
}
```

step写法：

```
Then the JSON response "medicareLeadExtension.rxSessionId" equals "123"
```

3.假设同样上面的json，需要获取第一个phoneType的值并校验：

step写法：

```
Then the JSON response "leadMembers[0].phoneType" equals "1"
```

## 对返回的整个json一次性校验：

step写法：

```
    Then the JSON response equals
    """
      {
          "result":"success"
      }
    """
```

### 对返回的json某个字段不为空校验：

step写法:

```
Then the JSON response "jsonpath" should be not null
```

### 对返回的json某个字段是否以XX开头校验

step写法：

```
Then the JSON response "jsonpath" start with "xx"
```

### 对返回的json某个字段是否以XX结尾校验

step写法：

```
Then the JSON response "jsonpath" end with "xx"
```

### 对返回的json某个字段是否包含xx

step写法：

```
Then the JSON response "jsonpath" include "xx"
```

### 对返回的json某个字段是否是XXX类型的校验

step写法:

```
And the JSON response "jsonpath" type should be "这里是正则表达式"
```

### 通过外部文件发起request和校验response

step写法：

```
When I use a "外部文件名" file to send a POST request to "api url"
Then the JSON response equals json file "外部文件名"
```

*注意：外部文件名需要放于项目结构的jsondir文件夹中，文件支持.txt和.json文件*


## feature编写注意事项：
1. 不涉及 Examples的Scenario，用Scenario关键字，设计到Examples 用Scenario Outline 关键字，如下：

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1992590-daec06e0754bf5dd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## Case Demo

```
Scenario Outline: use examples
       When I send a GET request to "paiurl"
       Then the response status should be "200"
       And the JSON response "message" include "ss"
       And the JSON response "message" end with "ss"
       And the JSON response "message" start with "su"
       And the JSON response "url" should be not null
       And the JSON response "sessionId" type should be "^\d{6}$"
       Then the JSON response "<jsonPath>" equals "<value>"
       Examples:
         | jsonPath     | value             |
         | genericPlan  | false             |
         | ehiCarrierId.carrierName | 90121100          |
         | ehi[0].carrierName | ha          |
```
```
 Scenario: json file
          When I use a "requestTest.json" file to send a POST request to "postapi url"
          Then the response status should be "200"
          Then the JSON response equals json file "reponseTest.json"
```
    
```   
     Scenario: test post request
       When I send a POST request to "postapi url" and request json:
       """
         {
           "sessionId": 244636,
           "agentNotes": "notepad"
         }
       """
       Then the response status should be "200"
       And the JSON response "result" equals "success"
```