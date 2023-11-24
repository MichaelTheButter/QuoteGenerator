package infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import configuration.MyDBConnection;
import core.Quote;
import presentation.PrintInTerminal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * Defines methods for inserting quotations
 */
public class CreateQuote {

    /**
     * Insert quotation from file into database
     * @param filePath path to file with quotation parameters
     * @param myDB specify database connection
     */
    public static void insertFromFile(String filePath, MyDBConnection myDB) {

        String SQL = "INSERT INTO quotation(cr_date, supplier_id, customer_id, employee_id) "
                + "VALUES(?,?,?,?)";
        String junctTable = "INSERT INTO JunctOfferProd(quote_id, product_id, quantity) "
                + "VALUES(?,?,?)";
        int quoteId = 0;
        try (
                Connection conn = myDB.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement jTpstmt = conn.prepareStatement(junctTable,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            //read quote from file
            Quote quote = readFile(filePath);
            // insert data to statement
            pstmt.setDate(1, java.sql.Date.valueOf(quote.getQuoteDate()));
            pstmt.setInt(2, quote.getSupplierId());
            pstmt.setInt(3, quote.getCustomerId());
            pstmt.setInt(4, quote.getEmployeeId());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) quoteId = PrintInTerminal.affectedRow(pstmt, quote, true);
            // insert products and quantities into junction table
            for (Integer key : quote.getProductList().keySet()){
                jTpstmt.setInt(1, quoteId);
                jTpstmt.setInt(2, key);
                jTpstmt.setInt(3, quote.getProductList().get(key));
                String junctionTableRow = "To quote: "+ quoteId + " add: " + key + " quantity: " + quote.getProductList().get(key);
                affectedRows = jTpstmt.executeUpdate();
                // check the affected rows
                if (affectedRows > 0) PrintInTerminal.affectedRow(jTpstmt, junctionTableRow);
            }
        } catch(SQLException | IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Read JSON file with quotation data
     * @param filePath path to file with quotation data
     * @return quote defined in JSON file
     * @throws IOException read data failed
     */
    private static Quote readFile(String filePath) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(jsonData);
        Function<String, Integer> convert = columnName -> rootNode.path(columnName).asInt();
        int supplierID = convert.apply("supplier_id");
        int customerID = convert.apply("customer_id");
        int employeeID = convert.apply("employee_id");
        Quote quote = new Quote(supplierID, customerID, employeeID);

        //get product Lists
        quote.setProductList(new LinkedHashMap<>());
        ArrayNode productList = (ArrayNode) rootNode.get("productList");
        for (JsonNode product : productList) {
            JsonNode product_id = product.path("product_id");
            JsonNode productQuantity = product.path("productQuantity");
            quote.getProductList().put(product_id.asInt(), productQuantity.asInt());
        }
        return quote;
    }
}
