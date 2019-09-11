import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Reporting {
	static DateFormat dateFormat = new SimpleDateFormat("hhmmss");
	static Date date = new Date();
	private static Formatter file;
	private static FileWriter f;
	private static String filePath = "Reports/Report_" + dateFormat.format(date)+".csv";
	private static String htmlFilePath = filePath+".html";
	private static String htmlHeader = "<html>" +
	           "<body>" +
	           "<table border = '1' align='center'>" +
	           "<tr>" +
	           "<th>Test Summary</th>" +
	           "<th>Test Status</th>" +
	           "</tr>";
	private static String htmlFooter = "</table>" +
	           "</body>" +
	           "</html>";
	private static String htmlTableRows = "";
	private static String cellColorGreen = "#32CD32";
	private static String cellColorRed = "#FF0000";

	public static void setTestCasePass(String testSummary) {
		new File("Reports").mkdirs();

		try {
			f = new FileWriter(filePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.format("\"%s\",PASS\n", testSummary);
		htmlTableRows = htmlTableRows + "<tr><td>"+testSummary+"</td><td bgcolor='"+cellColorGreen+"'>PASS</td></tr>";
		file.close();
	}

	public static void setTestCaseFail(String testSummary) {
		new File("Reports").mkdirs();

		try {
			f = new FileWriter(filePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.format("\"%s\",FAIL\n", testSummary);
		htmlTableRows = htmlTableRows + "<tr><td>"+testSummary+"</td><td bgcolor='"+cellColorRed+"'>FAIL</td></tr>";
		file.close();
	}

	public static void setTestCaseBlocked(String testSummary) {
		new File("Reports").mkdirs();

		try {
			f = new FileWriter(filePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.format("\"%s\",BLOCKED\n", testSummary);
		htmlTableRows = htmlTableRows + "<tr><td>"+testSummary+"</td><td>BLOCKED</td></tr>";
		file.close();
	}

	public static void validateCondition(boolean condition, String testSummary) {
		new File("Reports").mkdirs();
		try {
			f = new FileWriter(filePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (condition == true) {
			file.format("\"%s\",PASS\n", testSummary);
			htmlTableRows = htmlTableRows + "<tr><td>"+testSummary+"</td><td bgcolor='"+cellColorGreen+"'>PASS</td></tr>";
		}
		else {
			file.format("\"%s\",FAIL\n", testSummary);
			htmlTableRows = htmlTableRows + "<tr><td>"+testSummary+"</td><td bgcolor='"+cellColorRed+"'>FAIL</td></tr>";
		}
		file.close();

	}
	
	public static void generateHTMLReport() {
		try {
			f = new FileWriter(htmlFilePath, false);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.format("%s%s%s", htmlHeader, htmlTableRows, htmlFooter);
		file.close();

	}

}
