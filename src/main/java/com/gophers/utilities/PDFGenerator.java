package com.gophers.utilities;

import java.io.FileOutputStream;
import java.util.Map;
import com.gophers.interfaces.PDF;
import com.gophers.structures.AssignmentDetails;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFGenerator implements PDF {
    public final String TEMPLATE_PATH = "Template.pdf";

    public void generate(AssignmentDetails assignmentDetails) {
        try {
            PdfReader reader = new PdfReader(TEMPLATE_PATH);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("temp.pdf"));
            AcroFields form = stamper.getAcroFields();
            populateFields(assignmentDetails.toPDFData(), form);
            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateFields(Map<String, String> data, AcroFields form) {
        for (String key : data.keySet())
            try {
                form.setField(key, data.get(key));
                form.setField("Bonus", "5, 10, 10, 5");
                form.setField("Total", "100");
                form.setField("FeedBack", "Good Work");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
