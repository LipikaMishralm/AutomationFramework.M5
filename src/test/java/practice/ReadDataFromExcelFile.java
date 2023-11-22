package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		//Step 1: Open the document in java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Test.xlsx");
		
		//Step 2: Create a workBook
		Workbook wb = WorkbookFactory.create(fis);
		
		//step 3: Navigate to required sheet
		Sheet sh = wb.getSheet("Organizations");
		
		//Step 4: Navigate to required Row
		Row rw = sh.getRow(1);
		
		//Step 5: Navigate to required Cell
		Cell cl = rw.getCell(2);
		
		//Step 6: Capture the value in the cell
		String value = cl.getStringCellValue();
		System.out.println(value);
	}

}