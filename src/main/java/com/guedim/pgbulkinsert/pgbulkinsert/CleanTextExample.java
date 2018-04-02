package com.guedim.pgbulkinsert.pgbulkinsert;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CleanTextExample {

  public static void main(String[] args) throws IOException {
    File file =  new File("C:\\Users\\SONY\\Downloads\\dm-2018-04-03\\referencia_pago_1_2_wide_1.csv");
    Path path = Paths.get("C:\\Users\\SONY\\Downloads\\dm-2018-04-03\\referencia_pago_1_2_wide_1_fix.csv");
    
    String uncleanContent = readFileIntoString(file);
    //System.out.println(uncleanContent);
    String cleanContent = cleanTextContent(uncleanContent);
    //System.out.println(cleanContent);
    //Use try-with-resource to get auto-closeable writer instance
    try (BufferedWriter writer = Files.newBufferedWriter(path))
    {
        writer.write(cleanContent);
    }
  }

  private static String readFileIntoString(File file) {
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
      stream.forEach(s ->  { 
        contentBuilder.append(s).append("\n");
        });
    } catch (IOException e) {
      System.out.println("Error reading " + file.getAbsolutePath());
    }
    return contentBuilder.toString();
  }

  private static String cleanTextContent(String text) {
    // strips off all non-ASCII characters
    text = text.replaceAll("[^\\x00-\\x7F]", " ");
    text = text.replaceAll("\\u0000", " ");
    text = text.replace("\\u0000", ""); // removes backslash+u0000
    text = text.replace("\u0000", "null"); // removes NUL chars
    

    // erases all the ASCII control characters
    //text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

    // removes non-printable characters from Unicode
    //text = text.replaceAll("\\p{C}", "");
    
    //text = text.replaceAll("[^\\p{ASCII}]", "");

    //text = text.replaceAll( "([\\ud800-\\udbff\\udc00-\\udfff])", "");

    //text = text.replaceAll("[\\\\x00-\\\\x7F]|[\\\\xC0-\\\\xDF][\\\\x80-\\\\xBF]|[\\\\xE0-\\\\xEF][\\\\x80-\\\\xBF]{2}|[\\\\xF0-\\\\xF7][\\\\x80-\\\\xBF]{3}", "");

    // removes non-printable characters from Unicode
    text = text.replaceAll(",", "");
    
    return text.trim();
  }
}