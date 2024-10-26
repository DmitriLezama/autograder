package com.gophers.utilities;

import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.gophers.interfaces.PDF;
import com.gophers.structures.AssignmentGrade;
import com.itextpdf.text.pdf.AcroFields;

public class PDFGenerator implements PDF {
    public final String TEMPLATE_PATH = "Template.pdf";

    public void generate(AssignmentGrade assignmentGrade) {
        try {
            PdfReader reader = new PdfReader(TEMPLATE_PATH);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("temp.pdf"));
            AcroFields form = stamper.getAcroFields();
            form.setField("StudentName", "Bob Ross");
            form.setField("StudentID", "8160384591");
            form.setField("StudentPercentage", "100%");
            form.setField("ChatBotGenerator", "7");
            form.setField("ChatBot", "36");
            form.setField("ChatBotPlatform", "20");
            form.setField("ChatBotSimulation", "12");
            form.setField("Bonus", "5, 10, 10, 5");
            form.setField("Total", "100");
            form.setField("FeedBack", "Good Work");

            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
