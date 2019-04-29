package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)
@interface ExampleInterface {

	String jobName(); // proposal of the study is apply annotation in JavaEE Jobs
	String functionName();
	
}
