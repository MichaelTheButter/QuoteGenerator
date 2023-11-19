package infrastructure;

import com.opencsv.CSVReader;
import configuration.MyDBConnection;
import presentation.PrintInTerminal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class LoadProductDirectly {
    private static int totalRows = 0;
    private static int wrongInput = 0;
    private static final int MAX_PRODUCT_LENGTH = 50;
    private static final int PRODUCT_NAME_COLUMN = 0;
    private static final int PRODUCT_DESC_COLUMN = 2;
    private static final int PRODUCT_PRICE_COLUMN = 4;
    private static final int SKIP_CURRENCY = 1;

    public static void insertProducts(Path filePath, MyDBConnection myDB) {
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
                if (!checkData(line)) {
                    totalRows++;
                    wrongInput++;
                    continue;
                }
                String productName = line[PRODUCT_NAME_COLUMN];
                String productDesc = line[PRODUCT_DESC_COLUMN];
                String priceString = line[PRODUCT_PRICE_COLUMN].substring(SKIP_CURRENCY);
                double productPrice = Double.parseDouble(priceString);
                totalRows++;

                pstmt.setString(1, productName);
                pstmt.setString(2, productDesc);
                pstmt.setDouble(3, productPrice);
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) PrintInTerminal.affectedRow(pstmt, productName);
            }
            System.out.println("total: " + totalRows + " wrong: " + wrongInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkData(String[] line) {
        if (totalRows == 0) return false;
        else if (line[PRODUCT_NAME_COLUMN].length() > MAX_PRODUCT_LENGTH) return false;
        else if (!Character.isDigit(line[PRODUCT_PRICE_COLUMN].charAt(1))) return false;
        else return true;
    }
}
