package com.tchokoapps.apache.wicket;

import com.tchokoapps.apache.wicket.page.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

@Slf4j
@SpringBootApplication
public class SpringbootWicketApplication extends WebApplication {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWicketApplication.class, args);
	}

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        new BeanValidationConfiguration().configure(this );
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
    }
}
