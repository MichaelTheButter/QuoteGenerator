package infrastructure;

import configuration.MyDBConnection;
import core.Employee;
import core.Supplier;
import presentation.PrintInTerminal;

import java.sql.*;

public class InsertData {

    public static void insertEmployee(Employee employee, MyDBConnection myDB){
        String SQL = "INSERT INTO employee(emp_name,emp_lastName) "
                + "VALUES(?,?)";

        try (Connection conn = myDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) PrintInTerminal.affectedRow(pstmt, employee);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     public static void insertSupplier(Supplier supplier, MyDBConnection myDB){
         String SQL = "INSERT INTO supplier(supplier_name,supplier_address,tax_no) "
                 + "VALUES(?,?,?)";

         try (Connection conn = myDB.connect();
              PreparedStatement pstmt = conn.prepareStatement(SQL,
                      Statement.RETURN_GENERATED_KEYS)) {
             pstmt.setString(1, supplier.getSupplierName());
             pstmt.setString(2, supplier.getAddress());
             pstmt.setInt(3, supplier.getTaxNo());

             int affectedRows = pstmt.executeUpdate();
             // check the affected rows
             if (affectedRows > 0) PrintInTerminal.affectedRow(pstmt, supplier);
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }
}
