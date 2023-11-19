package infrastructure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.MyDBConnection;
import core.JSONCustomer;
import presentation.PrintInTerminal;


import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class LoadCustomerDirectly {

    public static void loadCustomers(List<JSONCustomer> customers, MyDBConnection myDB){
        String SQL = "INSERT INTO customer(customer_name, customer_address, tax_no) "
                + "VALUES(?,?,?)";
        long id = 0;
        String table = null;
        try (
                Connection conn = myDB.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            for (JSONCustomer customer : customers) {
                pstmt.setString(1, customer.getNazwaPodmiotu());
                pstmt.setString(2, customer.getMiejscowosc());
                pstmt.setInt(3, Integer.parseInt(customer.getNip()));

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) PrintInTerminal.affectedRow(pstmt, customer);
            }
        } catch(SQLException e){
                System.err.println(e.getMessage());
            }
    }

    public static void readJSON(){
            String filePath = "src/rawData/BusinessEntity.json";
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                //mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
                //mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
                List<JSONCustomer> customers = Arrays.asList(mapper.readValue(Paths.get(filePath).toFile(), JSONCustomer[].class));
                customers.forEach(System.out::println);



            }catch (Exception e) {
                e.printStackTrace();
            }
        }
}
