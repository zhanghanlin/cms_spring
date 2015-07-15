package com.demo.java.utils.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil implements ApplicationContextAware {

    protected final static Logger LOG = LoggerFactory.getLogger(ApplicationContextUtil.class);

    private static ApplicationContext context;// 声明一个静态变量保存

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        this.context = contex;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取spring配置的bean ApplicationContextUtil.getBean()<BR>
     * .
     * <P>
     * Author : wangchen
     * </P>
     * .
     * <P>
     * Date : 2012-11-5
     * </P>
     * 
     * @param beanName
     *            bean名称
     * @return
     */
    public static Object getBean(String beanName) {
        if (context == null) {
            return null;
        }
        return context.getBean(beanName);
    }

    /**
     * 获取SeckillLogDao ApplicationContextUtil.getSeckillLogDaoBean()<BR>
     * .
     * <P>
     * Author : wangchen
     * </P>
     * .
     * <P>
     * Date : 2012-11-5
     * </P>
     * 
     * @return bean对象
     */
    public static Object getSeckillLogDaoBean() {
        if (context == null) {
            return null;
        }
        return context.getBean("seckillLogDao");
    }

    /**
     * 测试使用方法,初始化spring配置 ApplicationContextUtil.InitialContext()<BR>
     * .
     * <P>
     * Author : wangchen
     * </P>
     * .
     * <P>
     * Date : 2012-11-5
     * </P>
     */
    public static void InitialContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath:application*.xml");
            LOG.debug("Manual load spring config");
        }
    }
}
