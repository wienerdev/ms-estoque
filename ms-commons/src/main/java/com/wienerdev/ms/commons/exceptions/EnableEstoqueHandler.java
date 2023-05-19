package com.wienerdev.ms.commons.exceptions;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({EnableEstoqueHandler.Authorization.class,
        EnableEstoqueHandler.class})
public @interface EnableEstoqueHandler {

    @Slf4j
    class Authorization implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(final @NonNull ConfigurableListableBeanFactory configurableListableBeanFactory)
                throws BeansException {
            log.debug("Enable Authorization module...");
        }
    }
}
