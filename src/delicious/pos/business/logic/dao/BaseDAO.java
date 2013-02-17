package delicious.pos.business.logic.dao;

import java.sql.Connection;

public class BaseDAO 
{
	private String dbName;
	
	private Connection con;
	
	private String dbms;
	
	public BaseDAO(Connection con, String dbName, String dbms) 
	{
		setCon(con);
		setDbName(dbName);
		setDbms(dbms);
	}
	
	public String getDbName()
	{
		return dbName;
	}
	
	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}
	
	public Connection getCon()
	{
		return con;
	}
	
	public void setCon(Connection con)
	{
		this.con = con;
	}
	
	public String getDbms()
	{
		return dbms;
	}
	
	public void setDbms(String dbms)
	{
		this.dbms = dbms;
	}
}
