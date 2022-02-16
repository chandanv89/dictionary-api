package com.github.chandanv89.dictionary.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.ico", "/**/*.txt", "/**/*.*")
                .setCachePeriod(0)
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/", "/**")
                .setCachePeriod(0)
                .addResourceLocations("classpath:/static/index.html")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        if (resourcePath.startsWith("/rest/api")) return null;
                        return location.exists() && location.isReadable() ? location : null;
                    }
                });
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/v1/dictionary/**")
                .allowedMethods("GET")
                .allowedOrigins("http://localhost:4200", "http://localhost:9091");
    }
}
