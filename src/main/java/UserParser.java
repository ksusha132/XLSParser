import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.*;

/**
 * Created by ksusha on 21.11.2017.
 */
public class UserParser {
    private final String FILE_NAME = "/Users/ksusha/Desktop/JavaData/User.xlsx";

    private final String NAME = "name";
    private final String SECOND_NAME = "secName";
    private final String PATRONIMIC = "patronimic";
    private final String AGE = "age";
    private final String SEX = "sex";

    public List<User> parse() throws IOException {

        if (FILE_NAME == null) {
            throw new FileNotFoundException(FILE_NAME);
        }

        InputStream in = new FileInputStream(FILE_NAME); // got content of exel file

        if (in == null){
            in.close();
            // logger
        }

        XSSFWorkbook wb = new XSSFWorkbook(in);
        XSSFSheet sheet = wb.getSheetAt(0); // got first exel sheet
        XSSFRow row;
        XSSFCell cell;

        List<User> parsedUser = Lists.newArrayListWithCapacity(sheet.getPhysicalNumberOfRows());
        Iterator<Row> rowIterator = sheet.rowIterator(); // Apache Poi has own iterator
        rowIterator.next(); // miss header

        while (rowIterator.hasNext()) {
            parsedUser.add(parseRow(sheet, rowIterator.next()));
        }

        return parsedUser;
    }

    private User parseRow(XSSFSheet sheet, Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();

        User user = new User();
        user.setAge(getStringOrEmpty(sheet, cellIterator.next(), AGE));
        user.setName(getStringOrEmpty(sheet, cellIterator.next(), NAME));
        user.setSecName(getStringOrEmpty(sheet, cellIterator.next(), SECOND_NAME));
        user.setPatronimic(getStringOrEmpty(sheet, cellIterator.next(), PATRONIMIC));
        user.setSex(getStringOrEmpty(sheet, cellIterator.next(), SEX));

        return user;

    }

    private String getStringOrEmpty(XSSFSheet sheet, Cell cell, String colunmName) {
        return stringOrEmpty(sheet, "", 0, cell.getRowIndex(), cell.getColumnIndex(), colunmName);
    }

    private static String stringOrEmpty(XSSFSheet sheet, String sheetName, int sheetIndex,
                                        int rowNumber, int colNummber, String colunmName) {
        XSSFCell cell = getCell(sheet, rowNumber, colNummber);

        if (cell == null) {
            return null;
        }
        String str = null;

        int cellType = cell.getCellType();

        if (cellType == CELL_TYPE_FORMULA) {
            str = String.valueOf(cell.getCachedFormulaResultType());
        }

        if (cellType == CELL_TYPE_NUMERIC) {
            str = String.valueOf((cell.getNumericCellValue()));
        }

        if (cellType == CELL_TYPE_STRING) {
            str = cell.getStringCellValue();
        }

        if (cellType == CELL_TYPE_BOOLEAN) {
            str = String.valueOf(cell.getBooleanCellValue());
        }

        if (str == null) {
            return null;
        }

        return str;
    }

    private static XSSFCell getCell(XSSFSheet sheet, int rowNum, int cellNum) {
        XSSFRow row = sheet.getRow(rowNum); // get row from sheet
        if (row == null) {
            return null;
        }
        return row.getCell(cellNum); // get cell from row
    }


}
