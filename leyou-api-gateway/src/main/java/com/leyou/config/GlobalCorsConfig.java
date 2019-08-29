package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author li
 * @time:2018/8/7
 * 处理跨域请求的过滤器
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();

        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://manage.leyou.com");
        config.addAllowedOrigin("http://www.leyou.com");
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4）允许的头信息
        config.addAllowedHeader("*");

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}

/**
 *
 *          跨域解决方案（https://blog.csdn.net/lyj2018gyq/article/details/82150316）
 *
 * 跨域是指跨域名的访问，以下情况都属于跨域：
 *   跨域原因说明	      示例
     域名不同	          www.jd.com 与 www.taobao.com
     域名相同，端口不同	  www.jd.com:8080 与 www.jd.com:8081
     二级域名不同	      item.jd.com 与 miaosha.jd.com
 *
 * 如果域名和端口都相同，但是请求路径不同，不属于跨域，如：
 *  www.jd.com/item
 *  www.jd.com/goods
 * 但刚才是从manage.leyou.com去访问api.leyou.com，这属于二级域名不同，所以会产生跨域
 *  跨域不一定会有跨域问题。因为跨域问题是浏览器对于ajax请求的一种安全限制：一个页面发起的ajax请求，
 *  只能是于当前页同域名的路径，这能有效的阻止跨站攻击。因此：跨域问题 是针对ajax的一种限制。
 *
 * cors解决跨域问题
 *
 *  什么是cors?
 * CORS是一个W3C标准，全称是"跨域资源共享"（Cross-origin resource sharing）。它允许浏览器向跨源服务器，
 * 发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
 *
 * CORS需要浏览器和服务器同时支持。目前，所有浏览器都支持该功能，IE浏览器不能低于IE10。
 * 浏览器端：
 *      目前，所有浏览器都支持该功能（IE10以下不行）。整个CORS通信过程，都是浏览器自动完成，不需要用户参与。
 * 服务端：
 *      ORS通信与AJAX没有任何差别，因此你不需要改变以前的业务逻辑。只不过，浏览器会在请求中携带一些头信息，
 *      我们需要以此判断是否运行其跨域，然后在响应头中加入一些信息即可。这一般通过过滤器完成即可
 *
 *
 *
 *
 *
 *
 *
 */