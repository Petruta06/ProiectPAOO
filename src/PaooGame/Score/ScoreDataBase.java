package PaooGame.Score;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ScoreDataBase {
    private static ScoreDataBase db = null;

    private static Connection c = null;
    private static Statement statement = null;
    private static PreparedStatement pstmt = null;


    /*! \fn public static ScoreDataBase getInstance()
    \brief Returns the instance of the ScoreDataBase Class if it exists, otherwise it creates one

 */
    public static ScoreDataBase getInstance() {
        if (db == null) {
            db = new ScoreDataBase();
        }
        return db;
    }

    private final String DB_NAME = "score_db.db";
    private final String TABLE_NAME = "SCORE";


    /*! \fn private ScoreDataBase()
    \brief Private constructor for implementing Singleton

 */
    private ScoreDataBase() {
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:score_db.db");
            System.out.println("Successully connected.");


        }catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
     //   createGameTable();
    }

    public void closeAll()  {
        try {
            pstmt.close();
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createGameTable() {
        try {

            String sql = "CREATE TABLE " + TABLE_NAME +
                    "(Entry   INT     NOT NULL," +
                    "Hour    INT     NOT NULL," +
                    "Minute  INT     NOT NULL," +
                    "Score   INT     NOT NULL)";

            pstmt = c.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /*! \fn public void createGameEntry(int points)
    \brief Makes a new entry for the current game

    \param points The points that are to be inserted in the table
 */
    public void createGameEntry(int points){

        String sql = "INSERT INTO "+TABLE_NAME+"(Entry, Hour, Minute, Score) VALUES (?,?,?,?)";
        try {
            pstmt = c.prepareStatement(sql);

            String sql2  = "SELECT COUNT(*) AS total FROM "+TABLE_NAME+";";
            PreparedStatement p1 = c.prepareStatement(sql2);
            ResultSet res = p1.executeQuery();
            int latestEntry = res.getInt("total") + 1;

            Calendar rightNow = Calendar.getInstance();
            int hour_rightnow = rightNow.get(Calendar.HOUR_OF_DAY);
            int minute_rightnow = rightNow.get(Calendar.MINUTE);
            pstmt.setInt(1, latestEntry);
            pstmt.setInt(2, hour_rightnow);
            pstmt.setInt(3, minute_rightnow);
            pstmt.setInt(4, points);

            pstmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    /*! \fn public int getScore(int entry)
    \brief Returns the score for a certain entry

    \param entry The log number in the table
 */
    public int getScore(int entry) {
        String sql = "SELECT Score FROM " + TABLE_NAME + " WHERE Entry = ?";
        try {
            pstmt = c.prepareStatement(sql);

            pstmt.setInt(1, entry);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return res.getInt("Score");
            }
            return -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -2;
        }
    }

    /*! \fn public int getHighScore()
    \brief Returns the highest score stored in the data base

 */
    public int getHighScore(){
        String sql = "SELECT Score FROM "+TABLE_NAME+" WHERE Score = (SELECT MAX(Score) FROM " + TABLE_NAME +");";
        try {
            pstmt = c.prepareStatement(sql);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return res.getInt("Score");
            }
            return -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -2;
        }
    }

    /*! \fn public void printToFile()
        \brief  Method for printing the data base information to a file

     */
    public void printToFile(){

        String sql = "SELECT * FROM " +TABLE_NAME+";";
        ArrayList output = new ArrayList<String>();
        try{
            pstmt = c.prepareStatement(sql);

            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                int entry = result.getInt("Entry");
                int hour = result.getInt("Hour");
                int minute = result.getInt("Minute");
                int score = result.getInt("Score");
                output.add(entry+" "+ hour+ ":" + minute+ " " + score);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writeToFile(output, "score.txt");

    }

    /*! \fn  public static void writeToFile(ArrayList<String> list, String path)
    \brief Helping method for printing info into a file

    \param list Contains the elements for printing
    \param path The path to the file in which we want to print
 */
    public static void writeToFile(ArrayList<String> list, String path){
        BufferedWriter out;
        try{
            File file = new File(path);
            out = new BufferedWriter(new FileWriter(file, true));
            //for(Object s : list){
            //  out.write((String)s);
            //out.newLine();
            //}
            out.write(list.get(list.size() - 1));
            out.newLine();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void print(){

        String sql = "SELECT * FROM " +TABLE_NAME+";";
        try{
            pstmt = c.prepareStatement(sql);

            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                int entry = result.getInt("Entry");
                int hour = result.getInt("Hour");
                int minute = result.getInt("Minute");
                int score = result.getInt("Score");
                System.out.print(entry + ",  " + hour + ", ");
                System.out.println(minute + ", " + score );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
