package infrastructure;

import configuration.MyDBConnection;
import presentation.PrintInTerminal;

import java.sql.*;
import java.util.function.Consumer;

/**
 * Keeps methods fr reading quotation from database
 */
public class ReadQuote {
    /**
     * Read quotation by ID and prints the results in terminal
     * @param quoteId quote_id to read
     * @param myDB specify database connection
     */
     public static void readByID(int quoteId, MyDBConnection myDB) {
        String SQL = "SELECT q.quote_id, customer.customer_name, supplier.supplier_name, e.emp_name, jop.quantity, product.product_name, product.price_1" +
                " FROM quotation q" +
                " JOIN customer ON q.customer_id = customer.customer_id" +
                " JOIN supplier ON q.supplier_id = supplier.supplier_id" +
                " JOIN employee e ON q.employee_id = e.emp_id" +
                " JOIN JunctOfferProd jop ON jop.quote_id = q.quote_id" +
                " JOIN product ON jop.product_id = product.product_id" +
                " WHERE q.quote_id = ?;";

        try (
                Connection conn = myDB.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL);
        ) {

            pstmt.setInt(1, quoteId);
            ResultSet rs = pstmt.executeQuery(SQL);
            rs.next();
            PrintInTerminal.IntColumn("quote_id", rs);
            PrintInTerminal.StringColumn("customer_name", rs);
            PrintInTerminal.StringColumn("supplier_name", rs);
            PrintInTerminal.StringColumn("emp_name", rs);
            PrintInTerminal.StringColumn("product_name", rs);
            PrintInTerminal.IntColumn("quantity", rs);
            PrintInTerminal.DoubleColumn("price_1", rs);

            while (rs.next()) {
                PrintInTerminal.StringColumn("product_name", rs);
                PrintInTerminal.IntColumn("quantity", rs);
                PrintInTerminal.DoubleColumn("price_1", rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
