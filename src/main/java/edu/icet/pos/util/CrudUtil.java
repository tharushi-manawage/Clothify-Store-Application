package edu.icet.pos.util;

import edu.icet.pos.db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pStm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pStm.setObject((i + 1), args[i]);
        }

        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) pStm.executeQuery();
        }
        return (T) (Boolean) (pStm.executeUpdate() > 0);
    }
}