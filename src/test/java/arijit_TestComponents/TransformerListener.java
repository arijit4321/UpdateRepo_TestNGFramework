package arijit_TestComponents;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class TransformerListener implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
		Class retry = annotation.getRetryAnalyzerClass();
		if(retry!= RetryAnalyzerListener.class)
		{
		annotation.setRetryAnalyzer(RetryAnalyzerListener.class);
		}
	}

}
