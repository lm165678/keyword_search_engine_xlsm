package keyword_search_seedsprint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author Zhongjie Shen
 */
public class XlsxHandler {

    private XSSFWorkbook wb;

    public XlsxHandler() {
        return;
    }

    public void init(String fileDir) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(fileDir);
        wb = new XSSFWorkbook(ExcelFileToRead);
    }
    
    /**
     * this function will read the first sheet and print fullname and skills columns
     */
    public List read() {
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell fullNameCell;
        XSSFCell skillsCell;

        List result = new ArrayList<Pair<String, String>>();

        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            fullNameCell = row.getCell(4); // full name
            skillsCell = row.getCell(28); // skills

            // this part only handles string cells
            if (fullNameCell != null && skillsCell != null){
                if (fullNameCell.getCellType() == CellType.STRING){
                    String fullName = fullNameCell.getStringCellValue();
                    String skills = skillsCell.getStringCellValue();
                    // System.out.print(fullName+" -- ");
                    // System.out.println(skills + " ");
                    Pair p = new Pair<String, String>(fullName, skills);
                    result.add(p);
                }
            }
        }
        return result;
    }
}