package com.invillia.acme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@SuppressWarnings("rawtypes")
	@Bean
	public Docket api() {
		ApiInfo apiInfo = new ApiInfo("Backend Challenge Api", 
				"The ACME company is migrating their monolithic system to a microservice architecture", 
				"1.0", 
				"", 
				ApiInfo.DEFAULT_CONTACT, 
				"Apache 2.0", 
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<VendorExtension>());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET());

	}

	@SuppressWarnings({ "unused", "serial" })
	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder()
						.code(500)
						.message("500 message")
						.responseModel(new ModelRef("Error"))
						.build());
				add(new ResponseMessageBuilder()
						.code(403)
						.message("Forbidden!")
						.build());
				add(new ResponseMessageBuilder()
						.code(404)
						.message("Not Found!")
						.build());
			}
		};
	}

}
