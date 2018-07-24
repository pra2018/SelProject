package com.aztec.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

	static String dbUrl="jdbc:sqlserver://otbsqlserver:1433;databaseName=BBNT_Staging";
	static String user="Development";
	static String password="jk";

	public static String getDbConnection(int leaseId) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rSet = null;
        
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conn=DriverManager.getConnection(dbUrl, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql="select Sum(amount) as SUM from Receivable_LeasePaymentHeader where LeaseID="+leaseId+"and isactive=1 and ReceivableheaderId in (\r\n" + 
				"(Select ReceivableheaderId from Receivable_LeasePaymentDetail where IncomeDetailId in (select IncomeDetailId from Lease_IncomeScheduleDetail \r\n" + 
				"where Incomedate >'9/30/2018')))";
		stmt=conn.createStatement();
		try {
			rSet=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (rSet.next())
			return rSet.getString(1); 

		else 
			return "0";
	}


	public static void main(String[] args) throws SQLException {

		// TODO Auto-generated method stub

		System.out.println(DBConnector.getDbConnection(12575));
	}

}
