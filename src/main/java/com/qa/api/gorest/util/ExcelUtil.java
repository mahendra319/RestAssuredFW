package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * 
 * @author mahen
 *
 */
public class ExcelUtil {
	
	
	public static String TEST_DATA_SHEET_PATH=".\\src\\main\\java\\com\\qa\\api\\gorest\\testdata\\TestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	
	
	/**
	 * This method is used to read data from Excel sheet
	 * @param sheetName
	 * @return this method is returning data
	 */
	
	public static Object[][] getTestData(String sheetName) {
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			try {
				book = WorkbookFactory.create(ip);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Object [][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
