package com.coweaver.test.test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final String host = "192.168.123.117"; 
    	final String user = "root"; 
    	final String pass = "root";
    	final Integer port = 3306;
    	
    	new JdbcTest(host, port, user, pass);
		JdbcConfig jcfg = new JdbcConfig(host, port, user, pass);
		if (jcfg != null)
		{
			CConfig cfg = new CConfig(user, jcfg);
			cfg.load();
			jcfg.Close();
		}   	
    }
}
