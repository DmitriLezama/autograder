package com.gophers.services.handlers;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.gophers.interfaces.PDF;
import com.gophers.structures.domain.AssignmentDetails;
import com.gophers.utilities.Constants;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFGenerator implements PDF {
    public void generate(AssignmentDetails assignmentDetails) {
        try {
            Map<String, String> data = assignmentDetails.toPDFData();
            PdfReader reader = new PdfReader(Constants.TEMPLATE_PATH);
            String studentName = data.get("StudentName").replace(" ", "_");
            String studentID = data.get("StudentID");
            String path = studentName + "_" + studentID + "_A1";
            String outputPath = Paths.get(Constants.OUTPUT_DIRECTORY, path, path + Constants.PDF_EXTENSION).toString();
            Files.createDirectories(Paths.get(Constants.OUTPUT_DIRECTORY, studentName + "_" + studentID + "_A1"));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPath));
            AcroFields form = stamper.getAcroFields();
            populateFields(data, form);
            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (Exception e) {
            System.err.println("PDF could not be generated");
        }
    }

    private void populateFields(Map<String, String> data, AcroFields form) throws Exception {
        for (String key : data.keySet())
            form.setField(key, data.get(key));
    }
}
