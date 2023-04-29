package back.io.input.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import back.io.exception.ReaderException;
import back.io.input.FileReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Reads data from Excel file.
 * @author Goylik D.V.
 * @see java.io.FileReader
 */
public class XLSXFileReader extends FileReader {
    /**
     * Constructor of XLSXFileReader.
     * @param filePath path where file to read is located.
     */
    public XLSXFileReader(String filePath) {
        super(filePath);
    }

    /**
     * Reads data from Excel file.
     * @param consumer method that handle read data.
     * @param <T>
     * @throws ReaderException
     */
    @Override
    public <T> void read(Consumer<T> consumer) throws ReaderException {
        try (
                FileInputStream fis = new FileInputStream(filePath);
                XSSFWorkbook wb = new XSSFWorkbook(fis)
        ) {
            List<List<String>> data = new ArrayList<>();
            XSSFSheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                ArrayList<String> str = new ArrayList<>();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    str.add(switch (cell.getCellTypeEnum()) {
                        case STRING -> cell.getStringCellValue();
                        case NUMERIC -> Integer.toString((int) cell.getNumericCellValue());
                        default -> null;
                    });
                }

                data.add(str);
            }

            consumer.accept((T) data);
        }
        catch (FileNotFoundException e) {
            throw new ReaderException("Can't find file by this way -> " + filePath, e);
        }
        catch (IOException e) {
            throw new ReaderException("Can't open excel file.", e);
        }
        catch (ClassCastException e) {
            throw new ReaderException("Cannot cast.", e);
        }
    }
}