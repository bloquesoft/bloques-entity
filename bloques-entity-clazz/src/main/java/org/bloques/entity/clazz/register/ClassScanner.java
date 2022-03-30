/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassScanner.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

@Slf4j
public class ClassScanner implements ResourceLoaderAware {

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

    private final List<TypeFilter> includeFilters = new LinkedList<>();

    private final List<TypeFilter> excludeFilters = new LinkedList<>();

    public void addIncludeFilter(TypeFilter includeFilter) {
        this.includeFilters.add(includeFilter);
    }

    public void addExcludeFilter(TypeFilter excludeFilter) {
        this.excludeFilters.add(0, excludeFilter);
    }

    public static Set<Class> scan(String[] basePackages, List<Class<? extends Annotation>> annotations) {
        ClassScanner cs = new ClassScanner();
        for (Class anno : annotations)
            cs.addIncludeFilter(new AnnotationTypeFilter(anno));
        Set<Class> classes = new HashSet<>();
        for (String s : basePackages)
            try
            {
                classes.addAll(cs.doScan(s));
            }
            catch (IOException ept)
            {
                continue;
            }
        return classes;
    }

    public static Set<Class> scan(String[] basePackages,
                                  Class<? extends Annotation>... annotations) {
        return scan( basePackages, Arrays.asList(annotations));
    }

    public static Set<Class> scan(String basePackage,
                                  Class<? extends Annotation>... annotations) throws IOException {
        ClassScanner cs = new ClassScanner();
        for (Class anno : annotations)
            cs.addIncludeFilter(new AnnotationTypeFilter(anno));
        return cs.doScan(basePackage);
    }

    public Set<Class> doScan(String basePackage) throws IOException {

        Set<Class> classes = new HashSet<Class>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
                    + "/**/*.class";
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);

            for (int i = 0; i < resources.length; i++) {
                Resource resource = resources[i];
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory
                            .getMetadataReader(resource);
                    if ((includeFilters.size() == 0 && excludeFilters.size() == 0)
                            || matches(metadataReader)) {
                        try {
                            classes.add(Class.forName(metadataReader
                                    .getClassMetadata().getClassName()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (IOException ex) {
            log.error("can not read ");
            //throw new IOException("I/O failure during classpath scanning", ex);
        }
        return classes;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils
                .getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(
                resourceLoader);
    }

    public final ResourceLoader getResourceLoader() {
        return this.resourcePatternResolver;
    }

    protected boolean matches(MetadataReader metadataReader) throws IOException {
        for (TypeFilter tf : this.excludeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return false;
            }
        }
        for (TypeFilter tf : this.includeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return true;
            }
        }
        return false;
    }
}