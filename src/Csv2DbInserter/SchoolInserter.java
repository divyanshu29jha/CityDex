package Csv2DbInserter;

import java.io.*;
import java.sql.*;
import Console.ConsoleColors;
public class SchoolInserter {
    public static void main() throws SQLException, IOException  {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "Sm.963258741m";

        String csvFilePath = "C:\\Users\\swast\\Desktop\\Sem 3\\java projects\\CityDex-main\\tables\\schools.csv";

        try(Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            Statement stmt = conn.createStatement();
        ) {
            String sql1 = "DROP TABLE IF EXISTS schools";
            String sql = "CREATE TABLE IF NOT EXISTS schools " +
                    "( Name VARCHAR(100), " +
                    " Address VARCHAR(300)," +
                    " Monthly_Fee int, " +
                    " Name_of_Principle VARCHAR(300), " +
                    " Website VARCHAR(300), " +
                    " Rating DOUBLE, "+
                    " Location VARCHAR(300), " +
                    " City VARCHAR(100) )";
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Created Schools table in the database..."+ ConsoleColors.RESET);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int batchSize = 100;

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO schools (Name, Address, Monthly_Fee,Name_of_Principle,Website,Rating,Location,City ) VALUES (?, ?, ?, ?, ?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;

            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String Name = data[0];
                String Address = data[1];
                String Monthly_Fee = data[2];
                String Name_of_Principle = data[3];
                String Website = data[4];
                String Rating = data[5];
                String Location = data[6];
                String City = data[7];



                statement.setString(1, Name);
                statement.setString(2, Address);

                int fFee = Integer.parseInt(Monthly_Fee);
                statement.setInt(3, fFee);

                statement.setString(4,  Name_of_Principle);
                statement.setString(5, Website);

                double hRating = Double.parseDouble(Rating);
                statement.setDouble(6, hRating);

                statement.setString(7, Location);
                statement.setString(8, City);



                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }

            lineReader.close();
            statement.executeBatch();

            connection.commit();
            connection.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
//
//        Statement statement = conn.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from hospitals");
//        while(resultSet.next()){
//            System.out.println(resultSet.getString("Name"));
//        }

    }
}

