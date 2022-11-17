package connectingDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbConnection dbCon = new DbConnection();
        //1. Creating a connection to the database
        Connection conn = dbCon.dbConnect("<dbname>", "<user>", "password");
        //2. Creating table
        //dbCon.createTable(conn, "Engineers");
        //3. Inserting data into the table
        //dbCon.insertRow(conn, "Engineers", "Kinyua",  "Nairobi West");
        //4. Reading the data::
        dbCon.readData(conn, "Engineers");
    }
}