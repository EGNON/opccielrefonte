package com.ged.reporting.services;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

@Service
public class PdfGeneratorService {
    @Value("${media.upload.dir}")
    public String PATH;
    public String htmlToPdf(String processedHtml) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFont);
            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);

            String fileCode = RandomStringUtils.randomAlphanumeric(8);
            String filePath = PATH + File.separator + fileCode + File.separator + "listVerifDepot.pdf";
            //Créer l'objet File
            File f = new File(PATH + File.separator + fileCode + File.separator);
            if(!f.exists())
            {
                boolean isCreated = f.mkdir();
                System.out.println("Le dossier " + fileCode + " est créé : " + isCreated);
            }
//            FileOutputStream fout = new FileOutputStream("C:/Users/gedbo/Desktop/Document/listVerifDepot.pdf");
            FileOutputStream fout = new FileOutputStream(filePath);
            byteArrayOutputStream.writeTo(fout);

            //Generate base64
            /*Deflater deflater = new Deflater();
            deflater.setLevel(Deflater.BEST_COMPRESSION);
            deflater.setInput(byteArrayOutputStream.toByteArray());
            deflater.finish();*/
            String encoded = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();

            return encoded;
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }
}
