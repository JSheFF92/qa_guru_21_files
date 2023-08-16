import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.InputStreamReader;


public class ZipZip {

    ClassLoader cl = JsonParseTest.class.getClassLoader();

    @Test
    @DisplayName("Csv из ZIP")
    void csvTest() throws Exception {
        try (ZipInputStream zis = openZipStream()) {
            verifyZipEntryContent(zis, "CsvTest.xlsx", inputStream -> {

                Reader reader = new InputStreamReader(inputStream);
                CSVReader csvReader = new CSVReader(reader);
                List<String[]> content = csvReader.readAll();

                Assertions.assertEquals(3, content.size());

                final String[] firstRow = content.get(0);
                final String[] secondRow = content.get(1);
                final String[] thirdRow = content.get(2);

                Assertions.assertArrayEquals(new String[]{"Teacher", "lesson"}, firstRow);
                Assertions.assertArrayEquals(new String[]{"Tuchs", "Files"}, secondRow);
                Assertions.assertArrayEquals(new String[]{"Vasenkov", "REST Assured"}, thirdRow);
            });
        }
    }

    @Test
    @DisplayName("PDF из ZIP")
    void testPdfTest() throws IOException, CsvException {
        try (ZipInputStream zis = openZipStream()) {
            verifyZipEntryContent(zis, "PdfTest.pdf", inputStream -> {
                PDF pdf = new PDF(inputStream);
                Assertions.assertTrue(pdf.text.contains("Пример pdf"));
            });
        }
    }

    @Test
    @DisplayName("Excel из ZIP")
    void testXlsxTest() throws Exception {
        try (ZipInputStream zis = openZipStream()) {
            verifyZipEntryContent(zis, "XlsxTest.xlsx", inputStream -> {
                XLS xls = new XLS(inputStream);
                String cellValue = xls.excel.getSheetAt(0)
                        .getRow(3)
                        .getCell(3)
                        .getStringCellValue();
                Assertions.assertTrue(cellValue.contains("Male"));
                assertThat(xls.excel.getSheetName(0), equalTo("Sheet1"));
            });
        }
    }

    private ZipInputStream openZipStream() {
        InputStream stream = cl.getResourceAsStream("arch.zip");
        return new ZipInputStream(stream);
    }

    private void verifyZipEntryContent(ZipInputStream zis, String entryName, ZipEntryVerifier verifier) throws IOException, CsvException {
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            final String name = entry.getName();
            if (name.contains(entryName)) {
                verifier.verifyEntry(zis);
            }
        }
    }

    interface ZipEntryVerifier {
        void verifyEntry(InputStream inputStream) throws IOException, CsvException;
    }

}