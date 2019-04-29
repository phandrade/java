package annotation;

import java.lang.reflect.Method;

public class Runner {


	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		ExampleObject obj = new ExampleObject();
		Method method = obj.getClass().getMethod("exampleFunction");
		ExampleInterface annotation = method.getAnnotation(ExampleInterface.class);
		
		System.out.println("Job name: " + annotation.jobName());
		System.out.println("Function name: " + annotation.functionName());
	}	
}
	
	
