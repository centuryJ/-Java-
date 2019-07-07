package com.oracle.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConnUtil {
	private static DataSource ds;// ��������
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	static {
		try {
			InputStream is = ConnUtil.class.getResourceAsStream("jdbc.properties");
			Properties properties = new Properties();
			properties.load(is);
			// ͨ�����ص���Դ�ļ���������Դ����
			ConnUtil.ds = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���һ�����ݿ����Ӷ���
	 * 
	 * @return ���ݿ����Ӷ���
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		Connection conn = local.get();
		if (conn == null || conn.isClosed()) {
			conn = ds.getConnection();
			local.set(conn);
		}
		return conn;
	}

	/**
	 * �ر����ݿ����Ӷ���ķ���
	 */
	public static void closeConn() {
		try {
			Connection conn = local.get();
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			local.set(null);
		}
	}
}

