package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dao.MemberDao;
import service.ChangePasswordService;
import service.MemberJoinService;

@Configuration

//스프링 MVC 설정을 위한 애노테이션
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
	
	//Connection 설정
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/daehun3249?characterEncoding=utf8");
		ds.setUsername("daehun3249");
		ds.setPassword("beside12!");
		return ds;
	}
	
	//DispatcherServlet을 이용하여 JSP코드를 처리하기 위한 설정
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	//컨트롤러 코드 구성시 뷰 리턴을 위한 코드
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/", ".jsp");
	}
	
	//DataAccessObject Bean 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	//Service 객체 등록
	@Bean
	public MemberJoinService memberJoinService() {
		return new MemberJoinService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePasswordSerivce() {
		return new ChangePasswordService(memberDao());
	}
}
