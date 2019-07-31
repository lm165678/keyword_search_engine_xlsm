package keyword_search_seedsprint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class XlsxHandler {
    
    public static void readXLSMFile(String fileDir) throws IOException 
    {
        InputStream ExcelFileToRead = new FileInputStream(fileDir);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        
        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) 
        {
            row = (XSSFRow) rows.next();

            cell = row.getCell(4);
            if (cell != null)
            {
                if (cell.getCellType() == CellType.STRING) 
                {
                    System.out.println(cell.getStringCellValue()+" ");
                }
                else if (cell.getCellType() == CellType.NUMERIC)
                {
                    System.out.println(cell.getNumericCellValue()+" ");
                }
                else
                {
                    // handle other types
                }    
            }
        }
    }
}