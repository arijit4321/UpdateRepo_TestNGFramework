package arijit_DataProviderutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelData {

	@DataProvider(parallel = false, name ="DataProvider_Selenium")
	public Object[][] dataprovider(Method m) throws IOException
	{
		String filepath = System.getProperty("user.dir")+"\\src\\main\\java\\arijit_DataProviderutils\\DataProviderExcel.xlsx";
		Object[][] data = getExcelData(filepath, m.getName());
		return data;
		
	}

	public Object[][] getExcelData(String filepath, String methodName) throws IOException
	{
		
		Object rawdata[][]=null;
		List<List<Object>> listobj = new ArrayList<List<Object>>();
		
		FileInputStream file = new FileInputStream(filepath);

		XSSFWorkbook wrkbk = new XSSFWorkbook(file);
		XSSFSheet sheet = wrkbk.getSheetAt(0);
		
		int rows = sheet.getLastRowNum();
		for(int r=1; r<=rows;r++)// cnt starting from 1 cz 0 is column heading
		{
			XSSFRow row = sheet.getRow(r);
			int cols = row.getLastCellNum();
			
			//rawdata = new Object[rows-1][cols];
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(methodName))
			{
				List<Object> singledata = new ArrayList<Object>();
			for(int j=1;j<cols;j++)// cnt starting from 2 cz 1 is Execution status in excel
			{			
				singledata.add(row.getCell(j).getStringCellValue());				
			}
			listobj.add(singledata);
			}
		}
		
		rawdata = new Object[listobj.size()][listobj.get(0).size()];
		
		/* How 2 dimen array is working
		 * singledata [arijit,1234], 
		 * singledata [arijit1,5678],
		 * singledata [arijit2,5679] 
		 * listobj [[arijit,1234],[arijit1, 5678],[arijit2, 5679] ] 
		 * listobj[3][2]
		 */
		
		for(int i=0; i<listobj.size();i++)
		{
			for(int j=0; j<listobj.get(0).size();j++)
			{
				rawdata[i][j] = listobj.get(i).get(j);
			}
		}
		
		System.out.println(Arrays.deepToString(rawdata));
		return rawdata;

	}

}
