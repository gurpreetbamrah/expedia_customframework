package db;
import api.JsonHandler;
import runner.FrameWorkLauncher;

import java.sql.*;
import java.util.List;

public class DBUtils {
    public static String row_count;

    public String executeQuery(String sqlScript) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee","root","Welcome123");
            StringBuilder  stringBuilder = new StringBuilder();
            List list=JsonHandler.foundValues;
            for (int i=0;i<list.size();i++){

                stringBuilder.append("'"+list.get(i)+"'"+",");
            }

            String queryDataToCheck=  stringBuilder.substring(1,stringBuilder.length()-2);
            String executeScript=sqlScript.replaceAll("getjsonlist",queryDataToCheck);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(executeScript);
            while (rs.next())
                row_count= String.valueOf(rs.getInt(1));
        } catch (Exception e) {
        }
        if(row_count.equals(0)) {
            FrameWorkLauncher.result.add("executeQuery,Fail");
            System.out.println("executeQuery:Fail");
        }
        else {
            FrameWorkLauncher.result.add("executeQuery,Pass");
            System.out.println("executeQuery:Pass");
        }
        return row_count;
    }

    public void verifyresultCount(String count) throws Exception {
        int expectedcount = Integer.parseInt(String.valueOf(JsonHandler.foundValues.size()));
       if(expectedcount!=Integer.parseInt(row_count)){
           FrameWorkLauncher.result.add("verifyresultCount,Fail");
           System.out.println("verifyresultCount:Fail");
       }

       else {
           FrameWorkLauncher.result.add("verifyresultCount,Pass");
           System.out.println("verifyresultCount:Pass");
       }
    }}
//}
