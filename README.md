For Running Framework:

mvn exec:java -Dexec.mainClass="runner.FrameWorkLauncher"   

For result:report.html
Cases are driven by : testsuite.xml



Query to find successful insertion:

mysql> select count(*) from FlightDetail where FLightFrom in ('Delhi','Bangalore','Lucknow');
+----------+
| count(*) |
+----------+
|        3 |
+----------+
1 row in set (0.00 sec)



After Insertion database:

mysql> select * from FlightDetail;
+----------+------------+----------+
| PersonID | FlightFrom | Name     |
+----------+------------+----------+
|        1 | Delhi      | Gurpreet |
|        2 | Bangalore  | Dilpreet |
|        3 | Mumbai     | Raja     |
|        4 | Lucknow    | Rishi    |
+----------+------------+----------+
4 rows in set (0.00 sec)






