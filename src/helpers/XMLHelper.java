package helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class XMLHelper {

	public static void createFile(ResultSet rs, String fileName) throws IOException, SQLException {
		Writer writer = new FileWriter(fileName);
		ResultSetMetaData metaData = rs.getMetaData();
		addPrologue(writer);
		openRoot(writer,metaData.getCatalogName(1));
		addChildren(writer,rs);
		closeRoot(writer,metaData.getCatalogName(1));
		writer.flush();
		writer.close();
	}


	private static void addChildren(Writer writer, ResultSet rs) throws SQLException, IOException {
		ResultSetMetaData metaData = rs.getMetaData();
		int nbColumn = metaData.getColumnCount();
		StringBuffer buffer = new StringBuffer();
		while(rs.next()){
			buffer.setLength(0);
			buffer.append("<").append(metaData.getTableName(1)).append(">").append("\n");
			for(int i=1 ; i<=nbColumn ; i++){
				buffer.append("<").append(metaData.getColumnName(i)).append(">");
				buffer.append(rs.getString(i));
				buffer.append("</").append(metaData.getColumnName(i)).append(">").append("\n");
			}
			buffer.append("</").append(metaData.getTableName(1)).append(">").append("\n");
			writer.write(buffer.toString());
		}
	}


	private static void closeRoot(Writer writer, String root) throws IOException {
		writer.write("</"+root+">\n");
	}


	private static void openRoot(Writer writer, String root) throws IOException {
		writer.write("<"+root+">\n");	
	}


	private static void addPrologue(Writer writer) throws IOException {
		writer.write("<?xml version='1.0' encoding='UTF-8'?>\n");
		
	}
}
