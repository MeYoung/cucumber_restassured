package com.tools.apitools;


import com.tools.config.LoggerControler;
import org.testng.Assert;

import java.util.regex.Pattern;

/**
 * Created by vidorh on 8/1/2016.
 * <p>
 * 居于Testng Assert类二次封装，添加办法校验方式
 */
public class MyAssert extends Assert {
    static final LoggerControler logger = LoggerControler.getLogger(MyAssert.class);

    //    而外添加的校验方法 =======================================================================

    /**
     * 断言字符是否已某个字符串开头
     *
     * @param message 断言错误消息
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     */
    public static void assertStartWith(String content, String prefix, String message) {
        if (message != null)
            logger.info(message);
        if (content.startsWith(prefix)) {
            logger.info("前缀匹配校验成功");
        } else {
            logger.error("前缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的前缀表达式为:" + prefix);
            MyAssert.fail();
        }
    }

    /**
     * 断言字符是否已某个字符串开头
     *
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     */
    public static void assertStartWith(String content, String prefix) {
        MyAssert.assertStartWith(content, prefix, null);
    }

    /**
     * 断言字符是否已某个字符串结尾
     *
     * @param message 断言错误消息
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     */
    public static void assertEndWith(String content, String endfix, String message) {
        if (message != null)
            logger.info(message);
        if (content.endsWith(endfix)) {
            logger.info("后缀匹配校验成功！");
        } else {
            logger.error("后缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的后缀表达式为:" + endfix);
            MyAssert.fail();
        }
    }

    /**
     * 断言字符是否已某个字符串结尾
     *
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     */
    public static void assertEndWith(String content, String endfix) {
        MyAssert.assertEndWith(content, endfix, null);
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param message 断言错误信息
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertMatch(String matcher, String regex, String message) {
        if (message != null)
            logger.info(message);
        if (Pattern.matches(regex, matcher)) {
            logger.info("匹配校验成功！");
        } else {
            logger.error("匹配校验失败！\n待校验的字符串为:" + matcher + "\n校验的正则表达式为:" + regex);
            MyAssert.fail();
        }
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertMatch(String matcher, String regex) {
        MyAssert.assertMatch(matcher, regex, null);
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param message 断言错误信息
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertNoMatch(String matcher, String regex, String message) {
        if (message != null)
            logger.info(message);
        if (!Pattern.matches(regex, matcher)) {
            logger.info("匹配校验成功！");
        } else {
            logger.error("匹配校验失败！\n待校验的字符串为:" + matcher + "\n校验的正则表达式为:" + regex);
            MyAssert.fail();
        }
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertNoMatch(String matcher, String regex) {
        MyAssert.assertNoMatch(matcher, regex, null);
    }

    /**
     * 断言字符串中是否包含某些字符
     *
     * @param message  断言错误消息
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void assertInclude(String content, String included, String message) {
        if (message != null)
            logger.info(message);
        if (content.contains(included)) {
            logger.info("匹配校验成功！");
        } else {
            logger.error("匹配校验失败！\n待校验的字符串为:" + content + "\n包含字符串为:" + included);
            MyAssert.fail(message);
        }
    }

    /**
     * 断言字符串中是否包含某些字符
     *
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void assertInclude(String content, String included) {
        MyAssert.assertInclude(content, included, null);
    }
}
