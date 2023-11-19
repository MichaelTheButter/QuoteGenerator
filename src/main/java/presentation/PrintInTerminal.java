package presentation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintInTerminal {
    public static <T> void affectedRow(PreparedStatement pstmt, T object){
        long id = 0;
        String table = null;

        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                id = rs.getLong(1);
                table = rs.getMetaData().getTableName(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    System.out.printf("Table: %s, added id: %s, %s%n", table, id, object.toString());
    }
}
