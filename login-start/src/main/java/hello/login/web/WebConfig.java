package hello.login.web;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import lombok.extern.java.Log;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LogInterceptor())
                    .order(1)   // 인터셉터의 호출 순서
                    .addPathPatterns("/**") // 인터셉터 적용 URL 패턴 지정
                    .excludePathPatterns("/css/**", "/*.ico", "/error");
            // 인터셉터에서 제외할 패턴을 지정한다.

            registry.addInterceptor(new LoginCheckInterceptor())
                    .order(2)
                    .addPathPatterns("/**")
                    .excludePathPatterns(
                            "/", "/members/add", "/login", "/logout",
                            "/css/**", "/*.ico", "/error"
                    );
        }

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean LoginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
