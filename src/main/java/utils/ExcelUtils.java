package utils;
import org.apache.poi.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;


public class ExcelUtils {

    public static void main(String[] args) {
        getRowCount();
        getCellData();
    }


    public static  void getRowCount()
    {

        try {
            String projDir= System.getProperty("user.dir");
            String excelPath= projDir+"/src/data/data.xlsx";


            XSSFWorkbook workbook=new XSSFWorkbook(excelPath);
            XSSFSheet sheet=workbook.getSheet("Sheet1");

            int rowCount=sheet.getPhysicalNumberOfRows();
            System.out.println(rowCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static  void getCellData()
    {

        try {
            String projDir= System.getProperty("user.dir");
            String excelPath= projDir+"/src/data/data.xlsx";

            XSSFWorkbook workbook=new XSSFWorkbook(excelPath);
            XSSFSheet sheet=workbook.getSheet("Sheet1");

            DataFormatter formatter=new DataFormatter();

            Object value=formatter.formatCellValue(sheet.getRow(1).getCell(0));

            System.out.println(value);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
