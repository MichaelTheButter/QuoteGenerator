package infrastructure;

import com.opencsv.CSVReader;
import configuration.MyDBConnection;
import presentation.PrintInTerminal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.function.Function;

/**
 * Keeps methods for reading data from CSV and inserting directly into Database
 */
public class InsertFromCSV {
    private static final int MAX_PRODUCT_LENGTH = 50;
    private static final int PRODUCT_NAME_COLUMN = 0;
    private static final int PRODUCT_DESC_COLUMN = 2;
    private static final int PRODUCT_PRICE_COLUMN = 4;
    private static final int SKIP_CURRENCY = 1;
    private static int wrongInput = 0;
    private static int totalRows = 0;

    /**
     * Insert products from CSV to database
     * @param fileString Path to a product data file
     * @param myDB specify database connection
     */
    public static void insertProducts(String fileString, MyDBConnection myDB) {
        Path filePath = Paths.get(fileString);
        String SQL = "INSERT INTO product(product_name, product_desc, price_1) "
                + "VALUES(?,?,?)";

        try (
                var fileReader = Files.newBufferedReader(filePath);
                CSVReader reader = new CSVReader(fileReader);
                Connection conn = myDB.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Skip row with column name
                if (totalRows==0){
                    totalRows++;
                    continue;
                }
                if (!checkData(line)) {
                    totalRows++;
                    wrongInput++;
                    continue;
                }
                // converts String from CSV to Double
                Function<String, Double> convertCurrency = price -> Double.parseDouble(price.substring(SKIP_CURRENCY));

                //Sets values into statement directly from csv line
                pstmt.setString(1, line[PRODUCT_NAME_COLUMN]);
                pstmt.setString(2, line[PRODUCT_DESC_COLUMN]);
                pstmt.setDouble(3, convertCurrency.apply(line[PRODUCT_PRICE_COLUMN]));
                int affectedRows = pstmt.executeUpdate();
                // check the affected rows
                if (affectedRows > 0) PrintInTerminal.affectedRow(pstmt, line[PRODUCT_NAME_COLUMN]);
                totalRows++;
            }
            // prints out how many rows have incorrect values
            System.out.println("total: " + totalRows + " wrong: " + wrongInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if provided data types meets column definition in database
     * @param line single line of data in CSV
     * @return true if data types are correct
     */
    public static boolean checkData(String[] line) {
        if (line[PRODUCT_NAME_COLUMN].length() > MAX_PRODUCT_LENGTH) return false;
        else return Character.isDigit(line[PRODUCT_PRICE_COLUMN].charAt(1));
    }
}
