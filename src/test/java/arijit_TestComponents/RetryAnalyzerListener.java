package arijit_TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerListener implements IRetryAnalyzer{
	
	int counter=0; // should be declared globally not locally else infinite loop
	int maxtry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		
		if(counter<maxtry)
		{
			counter = counter+1;
			return true;
		}
		
		return false;
	}
	
}
