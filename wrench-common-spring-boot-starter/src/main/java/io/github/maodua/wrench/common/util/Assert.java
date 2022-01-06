package io.github.maodua.wrench.common.util;

import io.github.maodua.wrench.common.exception.MessageException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 辅助验证参数的断言实用程序类。
 */
public class Assert extends org.springframework.util.Assert {

    /**
     * Assert a boolean expression, throwing an {@code MessageException}
     * 如果表达式的计算结果为 {@code false}.
     * <pre class="code">Assert.isTrue(i &gt; 0, "该值必须大于零");</pre>
     * @param expression 布尔表达式
     * @param message 断言失败时使用的异常消息
     * @throws MessageException if {@code expression} is {@code false}
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new MessageException(message);
        }
    }

    /**
     * 断言给定的字符串包含有效的文本内容; 也就是说，它不能为{@code null}，并且必须至少包含一个非空格字符.
     * <pre class="code">Assert.hasText(name, "'name'不能为空");</pre>
     * @param text 要检查的字符串
     * @param message 断言失败时使用的异常消息
     * @throws MessageException 如果文本不包含有效的文本内容
     * @see StringUtils#hasText
     */
    public static void hasText(@Nullable String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new MessageException(message);
        }
    }

    /**
     * 断言对象不是 {@code null}.
     * <pre class="code">Assert.notNull(clazz, "该类不能为null");</pre>
     * @param object 检查对象
     * @param message 断言失败时使用的异常消息
     * @throws MessageException 如果对象是 {@code null}
     */
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new MessageException(message);
        }
    }

    /**
     * 断言集合必须包含元素
     * 不是 {@code null} 和 必须包含至少一个元素.
     * <pre class="code">Assert.notEmpty(collection, "集合必须包含元素");</pre>
     * @param collection 要检查的集合
     * @param message 断言失败时使用的异常消息
     * @throws MessageException 如果集合是 {@code null} 或者不含任何元素
     * contains no elements
     */
    public static void notEmpty(@Nullable Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new MessageException(message);
        }
    }


    /**
     * 断言 Map 包含条目; 也就是说它不能为 {@code null} 并且必须包含至少一个条目
     * <pre class="code">Assert.notEmpty(map, "Map 必须包含条目");</pre>
     * @param map 要检查的 map
     * @param message 断言失败时使用的异常消息
     * @throws MessageException 如果 map 是 {@code null} 或不包含任何条目
     */
    public static void notEmpty(@Nullable Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new MessageException(message);
        }
    }
}
