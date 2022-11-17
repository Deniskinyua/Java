package connectingDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
    //1. Creating a function to connect to the database:
    public Connection dbConnect(String dbname, String usr, String pas){
        //1.Create a connection object and initialize it with null
        Connection conn = null;
        //2.try---- catch to connect
        try{
            //3.Access class
            Class.forName("org.postgresql.Driver");
            //4.Initialize using Driver Manager
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, usr, pas);
            //5. Check connection:
            if(conn != null){System.out.println("Connection Successful!");}else{System.out.println("Connection failed");}
        }catch(Exception e){System.out.println(e);}
        return conn;
    }
    //2. Creating a table in database;
    public void createTable(Connection conn, String table_name){
        Statement statement;
        //try ------ catch
        System.out.println("Entering to create table");
        try{
            String query = "create table " + table_name + "(empID SERIAL, name varchar(200), address varchar(200), primary key(empID));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            //
            System.out.println("Table Created!!");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    //3. Adding data to created table Engineers;
    public void insertRow(Connection conn, String tableName, String name, String address){
        Statement statement;
        try{
            String query = String.format("insert into %s(name, address) values('%s', '%s');", tableName, name,address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            //
            System.out.println("Row inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //4. Reading data from the database:
    public void readData(Connection conn, String tableName){
        Statement statement;
        ResultSet rs = null;
        //try --- catch
        try{
            String query = String.format("select * from %s", tableName);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            //Iterating to check existence of values
            while(rs.next()){
                System.out.print(rs.getString("empID") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("address") + " ");
            }
        }catch(Exception e){System.out.println(e);}
    }
}
