package Page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
    Connection connectionBudget23KF;
    Connection connectionBudget23UKT;
    Connection connectionBudget22KF;
    Connection connectionBudget22UKT;
    Connection connectionBudget21KF;
    Connection connectionBudget21UKT;
    Connection connectionedokf;

    public Connection getConnectionBudget23KF() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget23 = "jdbc:sqlserver://172.31.2.101:1433;database=budget23";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget23KF = DriverManager.getConnection(dbURLBudget23, "UKT_Riabtsev_AutoTest", "UKT_Riabtsev_AutoTest1");
        } catch (Exception ex23) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex23);
            System.exit(-1);
        }
        return connectionBudget23KF;
    }

    public Connection getConnectionBudget23UKT() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget23 = "jdbc:sqlserver://10.69.0.169:1433;database=grbs23";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget23UKT = DriverManager.getConnection(dbURLBudget23, "UKT_Riabtsev_AutoTest", "UKT_Riabtsev_AutoTest1");
        } catch (Exception ex23) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex23);
            System.exit(-1);
        }
        return connectionBudget23UKT;
    }

    public Connection getConnectionBudget22KF() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget22 = "jdbc:sqlserver://172.31.2.101:1433;database=budget22";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget22KF = DriverManager.getConnection(dbURLBudget22, "UKT_Riabtsev_AutoTest", "UKT_Riabtsev_AutoTest1");
        } catch (Exception ex22) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex22);
            System.exit(-1);
        }
        return connectionBudget22KF;
    }
    public Connection getConnectionBudget22UKT() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget22 = "jdbc:sqlserver://10.69.0.169:1433;database=budget22";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget22UKT = DriverManager.getConnection(dbURLBudget22, "UKT_Riabtsev_AutoTest", "UKT_Riabtsev_AutoTest1");
        } catch (Exception ex22) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex22);
            System.exit(-1);
        }
        return connectionBudget22UKT;
    }

    public Connection getConnectionBudget21KF() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget21 = "jdbc:sqlserver://172.31.2.101:1433;database=budget21";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget21KF = DriverManager.getConnection(dbURLBudget21, "UKT_Riabtsev_AutoTest", "UKT_Riabtsev_AutoTest1");
        } catch (Exception ex21) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex21);
            System.exit(-1);
        }
        return connectionBudget21KF;
    }

    public Connection getConnectionEDOKF() throws ClassNotFoundException, SQLException {
        try {
            String dbURLedokf ="jdbc:postgresql://172.31.1.150:55434/edokf";
            Class.forName("org.postgresql.Driver");
            connectionedokf = DriverManager.getConnection(dbURLedokf, "support", "Ztcnmhen!");
        } catch (Exception exEDO) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(exEDO);
            System.exit(-1);
        }
        return connectionedokf;
    }
    public void closeConnectionEDOKF() throws SQLException {
        if (connectionedokf != null && !connectionedokf.isClosed()) {
            connectionedokf.close();
        }
    }
    public void closeConnectBudget23KF() throws SQLException {
        if (connectionBudget23KF != null && !connectionBudget23KF.isClosed()) {
            connectionBudget23KF.close();
        }
    }
    public void closeConnectBudget23UKT() throws SQLException {
        if (connectionBudget23UKT != null && !connectionBudget23UKT.isClosed()) {
            connectionBudget23UKT.close();
        }
    }
    public void closeConnectBudget22KF() throws SQLException {
        if (connectionBudget22KF != null && !connectionBudget22KF.isClosed()) {
            connectionBudget22KF.close();
        }
    }
    public void closeConnectBudget22UKT() throws SQLException {
        if (connectionBudget22UKT != null && !connectionBudget22UKT.isClosed()) {
            connectionBudget22UKT.close();
        }
    }
    public void closeConnectBudget21KF() throws SQLException {
        if (connectionBudget21KF != null && !connectionBudget21KF.isClosed()) {
            connectionBudget21KF.close();
        }
    }
}



