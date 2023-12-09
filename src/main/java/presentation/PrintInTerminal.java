package presentation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Keeps methods for printing in terminal
 */
public class PrintInTerminal {
    private static String outputFormat = "%-30s%s";

    /**
     * Prints affected row while inserting into table
     * @param pstmt executed sql statement
     * @param object object which was inserted into database
     * @param <T> object of any type with toString method overwrite
     */
    public static <T> void affectedRow(PreparedStatement pstmt, T object){
        affectedRow(pstmt, object, false);
    }

    /**
     * Prints affected row while inserting into table and returns Id
     * @param pstmt executed sql statement
     * @param object object which was inserted into database
     * @param returnID if true returns ID, if false returns 0
     * @param <T> object of any type with toString method overwrite
     * @return id generated automatically by SQL
     */
    public static <T> int affectedRow(PreparedStatement pstmt, T object, boolean returnID){
        int id = 0;
        String table = null;

        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                id = rs.getInt(1);
                table = rs.getMetaData().getTableName(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.printf("Table: %s, added id: %s, %s%n", table, id, object.toString());
        if (returnID) return id;
        else return 0;
    }

    /**
     * Extracts and prints out String type column from results set
     * @param columnName column name to extract data
     * @param rs result set
     * @throws SQLException reading data failed
     */
    public static void StringColumn(String columnName, ResultSet rs) throws SQLException {
        String output = String.format(outputFormat, columnName, rs.getString(columnName));
        System.out.println(output);
    }

    /**
     * Extracts and prints out Integer type column from results set
     * @param columnName column name to extract data
     * @param rs result set
     * @throws SQLException reading data failed
     */
    public static void IntColumn(String columnName, ResultSet rs) throws SQLException {
        String output = String.format(outputFormat, columnName, rs.getInt(columnName));
        System.out.println(output);
    }

    /**
     * Extracts and prints out Integer type column from results set
     * @param columnName column name to extract data
     * @param rs result set
     * @throws SQLException reading data failed
     */
    public static void DoubleColumn(String columnName, ResultSet rs) throws SQLException {
        String output = String.format(outputFormat, columnName, rs.getDouble(columnName));
        System.out.println(output);
    }
}
