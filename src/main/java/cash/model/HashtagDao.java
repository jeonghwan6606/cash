package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import cash.vo.Hashtag;

public class HashtagDao {
	public int insertHashtag(Hashtag hashtag) {
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      int row = 0;
	      try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate)"
	                  + " VALUES(?,?,NOW(),NOW())";
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, hashtag.getCashbookNo());
	         stmt.setString(2, hashtag.getWord());
	         row = stmt.executeUpdate();
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	            conn.close();
	         }catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return row;
	   }
}
