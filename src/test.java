import static java.lang.System.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TupleGenerator test = new TupleGeneratorImpl ();
        // TupleGenerator selectRange = new TupleGeneratorImpl();

        test.addRelSchema ("Student",
                "id name address status",
                "Integer String String String",
                "id",
                null);

        test.addRelSchema ("Professor",
                "id name deptId",
                "Integer String String",
                "id",
                null);

        test.addRelSchema ("Course",
                "crsCode deptId crsName descr",
                "String String String String",
                "crsCode",
                null);

        test.addRelSchema ("Teaching",
                "crsCode semester profId",
                "String String Integer",
                "crcCode semester",
                new String [][] {{ "profId", "Professor", "id" },
                        { "crsCode", "Course", "crsCode" }});

        test.addRelSchema ("Transcript",
                "studId crsCode semester grade",
                "Integer String String String",
                "studId crsCode semester",
                new String [][] {{ "studId", "Student", "id"},
                        { "crsCode", "Course", "crsCode" },
                        { "crsCode semester", "Teaching", "crsCode semester" }});
        try {  
			Connection conn = MySQLJDBCUtil.getConnection();
            Statement stmt  = conn.createStatement();
            stmt.execute("use University");
            conn.close();
        }
        catch(SQLException ex) {
        	System.out.println(ex.getMessage());
        	System.exit(1);
        }
        
        int[] size = new int[] {10000,1000,2000,5000,5000};

        Comparable[][][] resultTestSelect = test.generate(size);
        Table studentTable = new Table("Student_Table", "id name address status", "Integer String String String", "id");

        //    Table StudentTable100, StudentTable200, StudentTable500, StudentTable1000, StudentTable2000, StudentTable5000, StudentTable10000, StudentTable50000;
        try {
            Connection conn = MySQLJDBCUtil.getConnection();
            PreparedStatement stmt  = conn.prepareStatement("insert into student values(?,?,?,?)");
            PreparedStatement stmtProf  = conn.prepareStatement("insert into professor values(?,?,?)");
            PreparedStatement stmtCourse  = conn.prepareStatement("insert into course values(?,?,?,?)");
            PreparedStatement stmtTeaching  = conn.prepareStatement("insert into teaching values(?,?,?)");
            PreparedStatement stmtTranscript  = conn.prepareStatement("insert into transcript values(?,?,?,?)");
        
	        
	            for (int j = 0; j < resultTestSelect[0].length-1; j++) {
	                studentTable.insert(resultTestSelect[0][j]);
	                try {  
	        	
	                    stmt.setInt(1,(int) resultTestSelect[0][j][0]);
	                    stmt.setString(2, (String) resultTestSelect[0][j][1]);
	                    stmt.setString(3, (String) resultTestSelect[0][j][2]);
	                    stmt.setString(4, (String) resultTestSelect[0][j][3]);
	                    stmt.addBatch();
	            
	                }
	                catch(SQLException ex) {
	                	System.out.println(ex.getMessage());
	                	System.exit(1);
	                }
	            } // for
	        // for
	        
	        
	            for (int j = 0; j < size[1]; j++) {
	                studentTable.insert(resultTestSelect[0][j]);
	                try {  
	        	
	                    stmtProf.setInt(1,(int) resultTestSelect[1][j][0]);
	                    stmtProf.setString(2, (String) resultTestSelect[1][j][1]);
	                    stmtProf.setString(3, (String) resultTestSelect[1][j][2]);
	                    stmtProf.addBatch();
	            
	                }
	                catch(SQLException ex) {
	                	System.out.println(ex.getMessage());
	                	System.exit(1);
	                }
	            } // for
	         
	        
	        
	            for (int j = 0; j < size[2]; j++) {
	                studentTable.insert(resultTestSelect[0][j]);
	                try {  
	        	
	                    stmtCourse.setString(1,(String) resultTestSelect[2][j][0]);
	                    stmtCourse.setString(2, (String) resultTestSelect[2][j][1]);
	                    stmtCourse.setString(3, (String) resultTestSelect[2][j][2]);
	                    stmtCourse.setString(4, (String) resultTestSelect[2][j][3]);
	                    stmtCourse.addBatch();
	            
	                }
	                catch(SQLException ex) {
	                	System.out.println(ex.getMessage());
	                	System.exit(1);
	                }
	            } // for
	        
	        
	        
	            for (int j = 0; j < size[3]; j++) {
	                studentTable.insert(resultTestSelect[0][j]);
	                try {  
	        	
	                    stmtTeaching.setString(1,(String) resultTestSelect[3][j][0]);
	                    stmtTeaching.setString(2, (String) resultTestSelect[3][j][1]);
	                    stmtTeaching.setInt(3, (int) resultTestSelect[3][j][2]);
	                    stmtTeaching.addBatch();
	            
	                }
	                catch(SQLException ex) {
	                	System.out.println(ex.getMessage());
	                	System.exit(1);
	                }
	            } // for
	        
	       
	        
	        	for (int j = 0; j < size[4]; j++) {
		            studentTable.insert(resultTestSelect[0][j]);
		            try {  
		            	
		                stmtTranscript.setInt(1,(int) resultTestSelect[4][j][0]);
		                stmtTranscript.setString(2, (String) resultTestSelect[4][j][1]);
		                stmtTranscript.setString(3, (String) resultTestSelect[4][j][2]);
		                stmtTranscript.setString(4, (String) resultTestSelect[4][j][3]);
		                stmtTranscript.addBatch();
		        
		            }
		            catch(SQLException ex) {
		            	System.out.println(ex.getMessage());
		            	System.exit(1);
		            }
	        	} // for
	        
	        
	       
	       stmtProf.executeBatch();
	        //stmtProf.close();
	       stmtCourse.executeBatch();
	       //stmtCourse.close();
	        stmtTeaching.executeBatch();
	        //stmtTeaching.close();
	        stmtTranscript.executeBatch();
	       // stmtTranscript.close();
	        stmt.executeBatch();
	        conn.close();
	       // stmt.close();
	        
        } // try
        catch(SQLException ex) {
        	System.out.println(ex.getMessage());
        	System.exit(1);
        }
           

        }//for


	}

