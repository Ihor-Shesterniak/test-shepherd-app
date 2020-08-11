package net.worldticket.configuration;

import net.worldticket.SpringWebservicesTestCaseApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SpringWebservicesTestCaseApplication.class)
public class ApplicationConfiguration {
}
