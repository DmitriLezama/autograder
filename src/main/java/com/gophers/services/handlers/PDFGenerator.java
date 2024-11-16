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

/**
 * The PDFGenerator class generates PDF reports for assignments based on the
 * provided assignment details.
 */
public class PDFGenerator implements PDF {
    /**
     * Generates a PDF report for the given assignment details.
     *
     * @param assignmentDetails the {@link AssignmentDetails} object containing data
     *                          for the PDF
     */
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

    /**
     * Populates the fields of the PDF form with the provided data.
     *
     * @param data a {@link Map} containing field names as keys and their
     *             corresponding values
     * @param form the {@link AcroFields} object representing the form fields in the
     *             PDF
     * @throws Exception if an error occurs while populating the fields
     */
    private void populateFields(Map<String, String> data, AcroFields form) throws Exception {
        for (String key : data.keySet()) {
            form.setField(key, data.get(key));
        }
    }
}
