package KeywordSearchEngine.model;

import KeywordSearchEngine.util.MessageHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javafx.util.Pair;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author Zhongjie Shen
 */
public class XlsmHandler {

  private ArrayList<XSSFWorkbook> wbs;
  private ArrayList<Pair<String, String>> extracted; // Pair<fullname:skills>

  public XlsmHandler() {
    this.wbs = new ArrayList<XSSFWorkbook>();
    this.extracted = new ArrayList<Pair<String, String>>();
    return;
  }

  /**
   * init XlsmHandler with a folderDir String
   * 
   * @param folderDir src folder dir
   * @return true if folder is not empty
   */
  public boolean init(String folderDir) {
    // InputStream ExcelFileFolderToRead = new FileInputStream(folderDir);

    ArrayList<Path> paths = this.getPaths(folderDir);

    if (paths.size() == 0)
      return false;

    try {
      for (Path path : paths) {
        String fileDir = path.toString();
        InputStream excelFileToRead = new FileInputStream(fileDir);
        XSSFWorkbook wb = new XSSFWorkbook(excelFileToRead);
        this.wbs.add(wb);
      }
    } catch (IOException e) {
      MessageHandler.errorMessage("Catch Exception: " + e.getMessage());
    }

    return true;
  }

  public ArrayList<Pair<String, String>> extractWbs() {
    MessageHandler.infoMessage("Start extracting ...");

    for (XSSFWorkbook wb : this.wbs) {
      Map<String, Integer> colIxMap = this.getColIxMap(wb);

      int fullNameInx = colIxMap.get("fullName");
      int allSkillsInx = colIxMap.get("allSkills");

      ArrayList<Pair<String, String>> temp = this.extractWb(wb, fullNameInx, allSkillsInx);
      this.extracted.addAll(temp);
    }

    MessageHandler.infoMessage("Total entry extracted: " + this.extracted.size());

    return this.extracted;
  }

  private Map<String, Integer> getColIxMap(XSSFWorkbook wb) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    XSSFSheet sheet = wb.getSheetAt(0);
    XSSFRow row = sheet.getRow(0);

    short minColIx = row.getFirstCellNum();
    short maxColIx = row.getLastCellNum();

    for (short colIx = minColIx; colIx < maxColIx; colIx++) {
      XSSFCell cell = row.getCell(colIx);
      map.put(cell.getStringCellValue(), cell.getColumnIndex());
    }

    return map;
  }

  /**
   * this function will read the first sheet and print fullname and skills columns
   */
  private ArrayList<Pair<String, String>> extractWb(XSSFWorkbook wb, int fullNameInx,
      int allSkillsInx) {
    XSSFSheet sheet = wb.getSheetAt(0);
    XSSFRow row;
    XSSFCell fullNameCell;
    XSSFCell skillsCell;

    ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();

    Iterator rows = sheet.rowIterator();

    while (rows.hasNext()) {
      row = (XSSFRow) rows.next();
      fullNameCell = row.getCell(fullNameInx); // full name
      skillsCell = row.getCell(allSkillsInx); // skills

      // this part only handles string cells
      if (fullNameCell != null && skillsCell != null) {
        if (fullNameCell.getCellType() == CellType.STRING) {
          String fullName = fullNameCell.getStringCellValue();
          String skills = skillsCell.getStringCellValue();
          Pair p = new Pair<String, String>(fullName, skills);
          result.add(p);
        }
      }
    }
    return result;
  }

  private ArrayList<Path> getPaths(String folderDir) {
    ArrayList<Path> paths = new ArrayList<Path>();

    try {
      Files.newDirectoryStream(Paths.get(folderDir), path -> path.toString().endsWith(".xlsm"))
          .forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
              try {
                paths.add(filePath);
                MessageHandler.infoMessage("[INFO] File detected: " + filePath.toString());
              } catch (Exception e) {
                MessageHandler.errorMessage("[FAIL] Catch Exception: " + e.getMessage());
              }
            }
          });
    } catch (IOException e) {
      MessageHandler.errorMessage("Catch Exception: " + e.getMessage());
    }
    if (paths.size() != 0) {
      MessageHandler.infoMessage("Total Found files (.xlsm): " + paths.size());
    } else {
      MessageHandler.errorMessage("Cannot find any file (.xlsm)");
    }

    return paths;
  }
}
