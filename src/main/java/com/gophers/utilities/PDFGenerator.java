package com.gophers.utilities;

import java.io.FileOutputStream;
import java.util.Map;
import com.gophers.interfaces.PDF;
import com.gophers.structures.AssignmentDetails;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFGenerator implements PDF {
    public final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    public final String OUTPUT_PATH = "src/main/resources/Template.pdf";

    public void generate(AssignmentDetails assignmentDetails) {
        try {
            PdfReader reader = new PdfReader(TEMPLATE_PATH);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(OUTPUT_PATH));
            AcroFields form = stamper.getAcroFields();
            populateFields(assignmentDetails.toPDFData(), form);
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
