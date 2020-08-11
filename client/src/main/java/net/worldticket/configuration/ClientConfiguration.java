package net.worldticket.configuration;

import com.paazl.gui.GuiInterface;
import net.worldticket.SpringWebservicesTestCaseClientApplication;
import net.worldticket.scheduling.ServerStatusTask;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {GuiInterface.class, SpringWebservicesTestCaseClientApplication.class, ServerStatusTask.class})
public class ClientConfiguration {
}