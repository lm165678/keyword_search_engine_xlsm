package KeywordSearchEngine.model;

import KeywordSearchEngine.util.MessageHandler;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Zhongjie Shen
 */
public class XlsmHandler {

  private ArrayList<XSSFWorkbook> wbs;
  private Map<String, String> extracted;

  public XlsmHandler() {
    this.wbs = new ArrayList<>();
    this.extracted = new HashMap<>();
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

    if (paths.size() == 0) {
      return false;
    }

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

  public Map<String, String> extractWbs() {
    // NOTE: this method might overwirte duplicate names
    MessageHandler.infoMessage("Start extracting ...");

    for (XSSFWorkbook wb : this.wbs) {
      Map<String, Integer> colIxMap = this.getColIxMap(wb);

      // FIXME: should ignore the titiles
      int fullNameInx = colIxMap.get("fullName");
      int allSkillsInx = colIxMap.get("allSkills");

      Map<String, String> temp = this.extractWb(wb, fullNameInx, allSkillsInx);
      this.extracted.putAll(temp);
    }

    MessageHandler.infoMessage("Total entry extracted: " + this.extracted.size());

    return this.extracted;
  }

  private Map<String, Integer> getColIxMap(XSSFWorkbook wb) {
    Map<String, Integer> map = new HashMap<>();
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
  private Map<String, String> extractWb(XSSFWorkbook wb, int fullNameInx,
      int allSkillsInx) {
    XSSFSheet sheet = wb.getSheetAt(0);
    XSSFRow row;
    XSSFCell fullNameCell;
    XSSFCell skillsCell;

    Map<String, String> result = new HashMap<>();

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
          result.put(fullName, skills);
        }
      }
    }
    return result;
  }

  /**
   * get all input file paths
   *
   * @param folderDir target folder directory
   * @return path list
   */
  private ArrayList<Path> getPaths(String folderDir) {
    ArrayList<Path> paths = new ArrayList<>();

    try {
      Files.newDirectoryStream(Paths.get(folderDir), path -> path.toString().endsWith(".xlsm"))
          .forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
              try {
                paths.add(filePath);
                MessageHandler.infoMessage("File detected: " + filePath.toString());
              } catch (Exception e) {
                MessageHandler.errorMessage("Catch Exception: " + e.getMessage());
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
