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
	private static String fileName = "Report_" + dateFormat.format(date);
	private static String csvFilePath = "Reports/" + fileName + ".csv";
	private static String htmlFilePath = "Reports/" + fileName + ".html";
	private static String htmlHeader = "<html>" + "<body>" + "<h1 align='center'>" + fileName + "</h1>"
			+ "<table border = '1' align='center'>" + "<tr>" + "<th>S/N</th>" + "<th>Test Summary</th>"
			+ "<th>Test Status</th>" + "</tr>";
	private static String htmlFooter;
	private static String htmlTableRows = "";
	private static String cellColorGreen = "#14dc50";
	private static String cellColorRed = "#dc143c";
	private static String cellColorGrey = "#d3d3d3";
	private static int nPassed = 0;
	private static int nFailed = 0;
	private static int nBlocked = 0;
	private static int tcCount = 0;

	public static void setTestCasePass(String testSummary) {
		new File("Reports").mkdirs();

		try {
			f = new FileWriter(csvFilePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tcCount++;
		file.format("%d,%s,PASS\n", tcCount, testSummary);
		htmlTableRows = htmlTableRows + "<tr><td>" + tcCount + "</td><td>" + testSummary + "</td><td bgcolor='"
				+ cellColorGreen + "'>PASS</td></tr>";
		nPassed++;
		file.close();
	}

	public static void setTestCaseFail(String testSummary) {
		new File("Reports").mkdirs();

		try {
			f = new FileWriter(csvFilePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tcCount++;
		file.format("%d,%s,FAIL\n", tcCount, testSummary);
		htmlTableRows = htmlTableRows + "<tr><td>" + tcCount + "</td><td>" + testSummary + "</td><td bgcolor='"
				+ cellColorRed + "'>FAIL</td></tr>";
		nFailed++;
		file.close();
	}

	public static void setTestCaseBlocked(String testSummary) {
		new File("Reports").mkdirs();

		try {
			f = new FileWriter(csvFilePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tcCount++;
		file.format("%d,%s,BLOCKED\n", tcCount, testSummary);
		htmlTableRows = htmlTableRows + "<tr><td>" + tcCount + "</td><td>" + testSummary + "</td><td bgcolor='"
				+ cellColorGrey + "'>BLOCKED</td></tr>";
		nBlocked++;
		file.close();
	}

	public static void validateCondition(boolean condition, String testSummary) {
		new File("Reports").mkdirs();
		try {
			f = new FileWriter(csvFilePath, true);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tcCount++;
		if (condition == true) {
			file.format("%d,%s,PASS\n", tcCount, testSummary);
			htmlTableRows = htmlTableRows + "<tr><td>" + tcCount + "</td><td>" + testSummary + "</td><td bgcolor='"
					+ cellColorGreen + "'>PASS</td></tr>";
			nPassed++;
		} else {
			file.format("%d,%s,FAIL\n", tcCount, testSummary);
			htmlTableRows = htmlTableRows + "<tr><td>" + tcCount + "</td><td>" + testSummary + "</td><td bgcolor='"
					+ cellColorRed + "'>FAIL</td></tr>";
			nFailed++;
		}
		file.close();

	}

	public static void generateHTMLReport() {
		htmlFooter = "</table>" + "<br><br><h2 align='center'>Summary</h2><center><b>Passed: " + nPassed
				+ "<br>Failed: " + nFailed + "<br>Blocked: " + nBlocked + "<b></center>"
				+ "<div id='piechart' align='center'></div>\n"
				+ "<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>\n"
				+ "<script type='text/javascript'>\n" + "google.charts.load('current', {'packages':['corechart']});\n"
				+ "google.charts.setOnLoadCallback(drawChart);\n" + "function drawChart() {\n"
				+ "  var data = google.visualization.arrayToDataTable([\n" + "  ['Status', 'PASS/FAIL/BLOCKED'],\n"
				+ "  ['PASS', " + nPassed + "],\n" + "  ['FAIL', " + nFailed + "],\n" + "  ['BLOCKED', " + nBlocked
				+ "]\n" + "]);\n" + "\n"
				+ "  var options = {'legend':{position: 'right', alignment: 'center', textStyle: {color: 'default', fontSize: 12}},\n"
				+ "  'width':650, \n" + "  'height':400, \n" + "  'colors': ['" + cellColorGreen + "', '" + cellColorRed
				+ "', '" + cellColorGrey + "']};\n" + "\n"
				+ "  var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n"
				+ "  chart.draw(data, options);\n" + "}\n" + "</script>" + "</body>" + "</html>";
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
