package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 하나 이상의 뷰 컨트롤러를 등록하기 위해 사용할 수있는 ViewControllerRegistry를 인자로받는다.
	// "/"를 인자로 전달하여 addViewController()를 호출하고 이 메서드는 ViewControllerRegistration 객체를 반환한다.
	// "/" 경로의 요청이 전달되어야 하는 뷰로 home을 지정하기 위해 
	// ViewControllerRegistry 객체의 setViewName()을 호출한다.
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
	
	// WebMvcConfiguer의 인터페이스를 상속받아
	// addViewControllers메서드를 구현하는데 ViewControllerRegistry를 인자로받는다.
	// ViewControllerRegistry는 하나 이상의 뷰 컨트롤러를 등록하기위해 사용가능하는데.
	// ViewControllerRegistry에서 addViewController()메서드를 사용할때 "/"인자로 받아서 
	// setViewName을 home이라고 지정한다.
	// 즉 루트 요청이 들어온다면 home 뷰 페이지로 이동시키겠다는 소리
}
