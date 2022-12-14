package com.luufery.bytebuddy.api.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.util.List;

/**
 * 这里的设计延续了alibaba-sandbox的风格,但很明显粒度变细了,但又忽略了方法粒度. 主要是因为我目前懒得细分.
 * 这导致操作单位只能在类级别.
 */
@Data
@Builder
public class CoreModule {

    private Class<?>[] targetClass;

    private ClassLoader classLoader;

    private ClassFileTransformer transformer;

    private String name;

    public void clear(){
        this.targetClass = null;
        this.classLoader = null;
        this.transformer = null;
        this.name = null;
    }

}
