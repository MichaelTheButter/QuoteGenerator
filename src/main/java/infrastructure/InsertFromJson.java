package infrastructure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.MyDBConnection;
import core.JSONCustomer;
import presentation.PrintInTerminal;


import java.io.File;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Keeps methods for reading data from JSON and inserting directly into Database
 */
public class InsertFromJson {

    /**
     * Inserts Customers from list to database
     * @param customers List of customers
     * @param myDB specify database connection
     */
    public static void loadCustomers(List<JSONCustomer> customers, MyDBConnection myDB){
        String SQL = "INSERT INTO customer(customer_name, customer_address, tax_no) "
                + "VALUES(?,?,?)";
        try (
                Connection conn = myDB.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            for (JSONCustomer customer : customers) {
                pstmt.setString(1, customer.getNazwaPodmiotu());
                pstmt.setString(2, customer.getAddress());
                pstmt.setLong(3, Long.parseLong(customer.getNip()));

                int affectedRows = pstmt.executeUpdate();
                // check the affected rows
                if (affectedRows > 0) PrintInTerminal.affectedRow(pstmt, customer);
            }
        } catch(SQLException e){
                System.err.println(e.getMessage());
            }
    }

    /**
     * Converts JSON file with customers data into Customers List
     * @param filePath path to customers data file
     * @return List of customers
     */
    public static List<JSONCustomer> readJSON(String filePath){
        File file = Paths.get(filePath).toFile();
        List<JSONCustomer> customers = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            customers = Arrays.asList(mapper.readValue(file, JSONCustomer[].class));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
