# custom-autoconfig-springboot-webapp

<h3> This is a multi module maven project which has 2 modules ( custom-autoconfig & springboot-webapp) in it. It helps to define dependencies and build the modules. <h3/>
<h3> <b> Intent -</b> To demostrate spring boot custom autoconfiguration </h3>
<h4> <b> custom-autoconfig module -</b> demonstrates custom autoconfiguration for datasource bean </h4>
<h4> <b> springboot-webapp module -</b> demonstrates simple spring boot web application that depends on custom-autoconfig module for datasource bean autoconfiguration </h4> <br/>

<h3>About spring boot autoconfiguration-</h3> spring boot intelligently decides which beans to be created on the startup and registers them in the application context.<br/>
<b> Philosophy:</b> Let the developer add the required dependencies in pom.xml and configure the required properties in application.properties <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html">common application properties</a>. <br/>
Spring boot will intelligently guess, create and configure the beans on the developer's behalf, sounds great, right? <br/>
spring-boot-autoconfigure module in the spring boot manages the show for auto configurations. It has the framework to define the guess logic and process it to create and configure the beans. Mainly, it has defined a lot of @Conditional annotations to define the auto configuration guess logic and you ( developer ) can use it to define your own custom autoconfigurations.<br/><br/>

<b>Custom autoconfiguration in action-</b> <a href="https://github.com/mane-ashok/custom-autoconfig-springboot-webapp/blob/main/custom-autoconfig/src/main/java/org/ashok/custom/autoconfig/CustomDatasourceAutoconfiguration.java">Check the custom autoconfiguration class</a> <br/><br/>
<b> Lets decode the guess logic -</b> <br/>
<b>@AutoConfiguration(before = DataSourceAutoConfiguration.class)</b> - You need to annotate your custom configuration class with this annotation, before attribute tells the auto configuration framework that it needs to be evaluated before evaluating the spring boot's DataSourceAutoConfiguration.class logic. <br/>
<b>@ConditionalOnClass(JdbcDataSource.class)</b> - check if JdbcDataSource.class is on the class path ( h2 dependency in pom.xml) <br/>
<b>@ConditionalOnMissingBean(DataSource.class) </b> - There should not be any bean already created of type DataSource.class <br/>
<b>@ConditionalOnProperty(prefix = "custom.autoconfig", name = "enabled", havingValue = "true") </b> - There should be a property (custom.autoconfig.enabled = true )  in application.properties <br/>
<b>@EnableConfigurationProperties(CustomAutoConfigDataSourceProperties.class) </b> - creates and registers CustomAutoConfigDataSourceProperties as a bean with the properties values assigend from application.properties <br/>
Once all above conditions are matched then go ahead and create a bean defined in the class. <br/><br/>
<b> simple plain normal configuration bean - </b><br/>
    @Bean
    public DataSource dataSource(CustomAutoConfigDataSourceProperties properties) {
        
		    JdbcDataSource ds = new JdbcDataSource();
        
        ds.setUrl(properties.getUrl());
        ds.setUser(properties.getUsername());
        ds.setPassword(properties.getPassword());
              
        return ds;
    }
<br/><br/>
<b> How to communicate this to the spring boot auto configuration framework?</b> <br/>
Enter your fully qualified autoconfiguration class name in <a href="https://github.com/mane-ashok/custom-autoconfig-springboot-webapp/blob/main/custom-autoconfig/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports">This file</a> <br/><br/>

<b>How it is set up?</b> <br/>
<b>springboot-webapp</b> has a dependency on the custom-autoconfig module. Check your <a href="https://github.com/mane-ashok/custom-autoconfig-springboot-webapp/blob/main/springboot-webapp/src/main/resources/application.properties">custom autoconfig properties </a> in springboot-webapp's application.properties file. <br/><br/>

<b> How to test? </b> <br/>
When you build the <a href="https://github.com/mane-ashok/custom-autoconfig-springboot-webapp">parent project</a> it builds both the modules (custom-autoconfig & springboot-webapp). Go to springboot-webapp/target folder and execute the command - <b>java -jar springboot-webapp-0.0.1-SNAPSHOT.jar>/b>. <br/>
On the application startup, spring boot's autoconfiguration framework will detect your custom autoconfig, check the condition logic and create, configure and register your datasource bean in the application context. You will see the logger in console logs that is added in <a href="https://github.com/mane-ashok/custom-autoconfig-springboot-webapp/blob/main/springboot-webapp/src/main/java/org/ashok/springboot/SpringBootWebapp.java"> This class </a> as below:<br/><br/>
<b>******This Spring Boot app is using datasource class = org.h2.jdbcx.JdbcDataSource</b> <br/><br/>

Go ahead and test the application with the url <b> http://localhost:8080 </b>












