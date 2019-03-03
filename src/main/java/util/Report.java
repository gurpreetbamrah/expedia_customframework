package util;

import runner.FrameWorkLauncher;

import java.io.*;

public class Report {

    public void createReport() {
        try {
            StringBuilder htmlStringBuilder = new StringBuilder();
            htmlStringBuilder.append("<html><head><title>Automation</title><fieldset>\n" +
                    "    <h1><legend>Automation Result</legend></h1></head>");
            htmlStringBuilder.append("<body>");
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
            htmlStringBuilder.append("<tr><td><b>TestName</b></td><td><b>TestResult</b></td></tr>");
            for (int i=0;i<FrameWorkLauncher.result.size();i++) {
                String str= String.valueOf(FrameWorkLauncher.result.get(i));
                String arr[]=str.split(",");
                htmlStringBuilder.append("<tr><td>"+arr[0]+"</td><td>"+arr[1]+"</td></tr>");
            }

            htmlStringBuilder.append("</table></body></html>");
            WriteToFile(htmlStringBuilder.toString(), "report.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator + fileName;
        File file = new File(tempFile);
        if (file.exists()) {
            try {
                File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer = new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();

    }
}


