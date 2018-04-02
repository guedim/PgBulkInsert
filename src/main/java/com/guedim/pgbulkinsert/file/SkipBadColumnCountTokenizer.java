package com.guedim.pgbulkinsert.file;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.Tokenizer;
import org.supercsv.prefs.CsvPreference;

//https://stackoverflow.com/questions/34958724/how-do-i-skip-white-space-only-lines-and-lines-having-variable-columns-using-sup
public class SkipBadColumnCountTokenizer extends Tokenizer {

  private final int expectedColumns;

  private final List<Integer> ignoredLines = new ArrayList<>();

  public SkipBadColumnCountTokenizer(Reader reader, 
          CsvPreference preferences, int expectedColumns) {
      super(reader, preferences);
      this.expectedColumns = expectedColumns;
  }

  @Override
  public boolean readColumns(List<String> columns) throws IOException {
      boolean moreInputExists;
      while ((moreInputExists = super.readColumns(columns)) && 
          columns.size() != this.expectedColumns){
          System.out.println(String.format("Ignoring line %s with %d columns: %s", getLineNumber(), columns.size(), getUntokenizedRow()));
          ignoredLines.add(getLineNumber());
      }

      return moreInputExists;

  }

  public List<Integer> getIgnoredLines(){
      return this.ignoredLines;
  }
}