package io.github.maodua.wrench.common.util;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Springs  implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Springs.context = applicationContext;
    }

    /**
     * 获取当前应用的 ApplicationContext 实例
     * @return 当前应用的 ApplicationContext 实例
     */
    public static ApplicationContext context() {
        return Springs.context;
    }

    /**
     * 根据 id 获取 bean，相当于 @Qualifier
     * @param id 要获取的 bean 的 id
     * @param <T> 返回值的数据类型，因为调用方接收返回值时会有额外的类型转换检查，所以这个实现不会造成安全问题，只是把问题稍微延后了一点
     * @return 指定 id 的 bean 实例，如果没找到则返回 null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(@NonNull String id) {
        return (T) Springs.context.getBean(id);
    }

    /**
     * 根据 class 获取 bean，相当于 @Autowired
     * @param c 要获取的 bean 的 class
     * @param <T> 返回值的数据类型
     * @return 指定 class 的 bean 实例，如果没找到则返回 null
     */
    public static <T> T getBean(@NonNull Class<T> c) {
        return Springs.context.getBean(c);
    }

    /**
     * 根据类型获取 Bean
     * @param clazz Bean 的类型
     * @param <T> Bean 的类型
     * @return 符合要求的 Bean，Key 为 Bean 的 Name，Value 为 Bean 实例
     */
    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return Springs.context.getBeansOfType(clazz);
    }

    /**
     * 根据类型获取 Bean，并将其转换为 List
     * @param clazz Bean 的类型
     * @param <T> Bean 的类型
     * @return 符合要求的 Bean 列表
     */
    public static <T> List<T> getBeansList(Class<T> clazz) {
        return new ArrayList<>(Springs.getBeans(clazz).values());
    }


    /**
     * 根据名称获取 application 中的配置
     * @param name 配置的名称
     * @return 如果配置存在则返回配置的值，否则返回 null
     */
    public static String getProperty(@NonNull String name) {
        return Springs.context.getEnvironment().getProperty(name);
    }



}
