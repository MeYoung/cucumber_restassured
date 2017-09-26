**搭建这个完全是因为之前某个团队就要这么做而搭建，然后实际API自动化测试中，非常不建议通过(Cucucmber)这种方式来实现。完全可以用rest assured + TestNG/JUnit 完成你需要的API自动化测试**

# cucumber_restassured
cucumber + restassured api automation

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

## 通过Junit 运行feature.
1. 在Pom.xml 文件添加junit相关包：
```
        <dependency>
            <groupId>info.cukes</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>1.2.4</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
```
2. 新建个运行类，代码例子如下：

```
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        features = {"src/test/java/bosev2"},
        glue = {"com.bose.step"},
        tags = {"~@unimplemented"})
public class RunnerBoseTest {
}
```

@RunWith(Cucumber.class) ：  注解表示通过Cucumber的Junit 方式运行脚本
@CucumberOptions () ：注解用于配置运行信息，其中代码中的plugin 表示测试报告输出的路径和格式， feature 表示被运行的feature文件的包路径， glue中配置steps的包路径地址，tags中配置要运行的用例的tags名，其实~符号表示除了这个tags的所有tags.

## 通过Jenkins 执行
1. 在Pom.xml 文件里面添加运行插件，如下：
```
 <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.3</version>
                <inherited>true</inherited>
                <configuration>
                    <source>1.7</source>
                    <target>1.7</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.18.1</version>
                <configuration>
                    <reuseForks>false</reuseForks>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
2. 在Jenkins 中添加[Cucumber-JVM reports](https://github.com/jenkinsci/cucumber-reports-plugin)插件。
3. 新建Maven job，配置maven构建方式和构建后的测试报告展示。

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1992590-918aec8401020427.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Cucumber-JVM reports 提供了非常漂亮的report，如下：

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1992590-6f22e647b4eed391.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
