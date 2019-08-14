package keyword_search_seedsprint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.Iterator;
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
    private ArrayList<XSSFWorkbook> wbs;

    public XlsxHandler() {
        return;
    }

    public void init(String fileDir) {
        try {
            InputStream excelFileToRead = new FileInputStream(fileDir);
            wb = new XSSFWorkbook(excelFileToRead);
        } catch (IOException e) {
            System.out.println("[FAIL] Catch Exception: " + e.getMessage());
        }
    }

    public void initF(String folderDir) {
        // InputStream ExcelFileFolderToRead = new FileInputStream(folderDir);
        // wbs = new ArrayList<XSSFWorkbook>();

        ArrayList<String> filesDir = new ArrayList<String>();
        try {
            Files.newDirectoryStream(Paths.get(folderDir),
            path -> path.toString().endsWith(".xlsm"))
            .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("[FAIL] Catch Exception: " + e.getMessage());
        }
    }
    
    /**
     * this function will read the first sheet and print fullname and skills columns
     */
    public ArrayList read() {
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell fullNameCell;
        XSSFCell skillsCell;

        ArrayList result = new ArrayList<Pair<String, String>>();

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