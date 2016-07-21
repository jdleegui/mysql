package com.coweaver.test.test;

/**
 * Hello world!
 *
 */
public class App 
{
	final private static String host = "192.168.123.117";
	final private static String user = "root";
	final private static String pass = "root";
	
    public static void main( String[] args )
    {
        new JdbcTest(host, user, pass);
    }
}
