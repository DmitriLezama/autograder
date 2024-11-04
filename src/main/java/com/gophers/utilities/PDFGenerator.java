package com.gophers.utilities;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.gophers.interfaces.PDF;
import com.gophers.structures.AssignmentDetails;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFGenerator implements PDF {
    public final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    public final String OUTPUT_PATH = "src/main/resources/StudentSubmissions";

    public void generate(AssignmentDetails assignmentDetails) {
        try {
            var data = assignmentDetails.toPDFData();
            PdfReader reader = new PdfReader(TEMPLATE_PATH);
            String studentName = data.get("StudentName").replace(" ", "_");
            String studentID = data.get("StudentID");
            String path = studentName + "_" + studentID + "_A1";
            String outputPath = Paths.get(OUTPUT_PATH, path, path + ".pdf").toString();
            Files.createDirectories(Paths.get(OUTPUT_PATH, studentName + "_" + studentID + "_A1"));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPath));
            AcroFields form = stamper.getAcroFields();
            populateFields(data, form);
            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateFields(Map<String, String> data, AcroFields form) throws Exception {
        for (String key : data.keySet())
            form.setField(key, data.get(key));
    }
}
