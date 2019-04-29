package annotation;

public class ExampleObject {

	@ExampleInterface(jobName = "ExampleObject", functionName = "exampleFunction")
	public void exampleFunction() {
		System.out.println("funcao testarAnnotation executada");
	}
}

