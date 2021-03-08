package fund.investment.app.pledge.repo.server.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("fund.investment.app.pledge.repo.server.controller.instruction");
        Predicate<RequestHandler> selector2 = RequestHandlerSelectors.basePackage("fund.investment.app.pledge.repo.server.controller.trade");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档 //单个配置controller
                //.apis(RequestHandlerSelectors.basePackage("com.cnczsq.mall.elephant.v1.controller"))
                //controller批量配 方式一
                .apis(Predicates.or(selector1, selector2))
                // controller批量配方式二    指定所有controller的都实现的一个接口，比如@RestController
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // controller批量配方式三    指定所有controller路径的父级
                //.apis(RequestHandlerSelectors.basePackage("com.cnczsq.mall.elephant"))
                // controller批量配方式四    指定所有ApiOperation注解方法
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("pledge")
                .description("pledge")
                .version("1.0")
                .build();
    }

}