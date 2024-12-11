package com.ged.service.standard.impl;

import com.ged.dao.standard.DocumentDao;
import com.ged.dao.standard.MailDao;
import com.ged.dto.standard.DocumentMailDto;
import com.ged.entity.standard.Document;
import com.ged.entity.standard.DocumentMail;
import com.ged.service.standard.DocumentMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class MailSenderServiceImpl {

    private final JavaMailSender mailSender;
    private final DocumentMailService documentMailService;
    private final MailDao mailDao;
    private final DocumentDao documentDao;
    @Value("${media.upload.dir}")
    public String path;

    @Autowired
    private  CustomMultipartFile customMultipartFile;
    public MailSenderServiceImpl(JavaMailSender mailSender, DocumentMailService documentMailService, MailDao mailDao, DocumentDao documentDao) {
        this.mailSender = mailSender;
        this.documentMailService = documentMailService;
        this.mailDao = mailDao;
        this.documentDao = documentDao;
    }

    //    @Override

    public boolean send(String subject, String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("POSTMASTER@saphiram.COM");
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    @Async
    public boolean sendMany(String subject, String[] to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("POSTMASTER@saphiram.COM");
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    @Async
    public boolean sendWithAttachement(String subject, String to, String email, String fileName[], String url[]) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "utf-8");
            //String url = "static/" + fileName;
            //String url = fileName;
            //ClassPathResource pdf = new ClassPathResource(url);
            helper.setText(email, true);
            helper.setTo(to);
            for(int i=0;i<fileName.length;i++)
            {
                helper.addAttachment(fileName[i], new File(url[i]));
            }
            helper.setSubject(subject);
            helper.setFrom("POSTMASTER@saphiram.COM");
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    public boolean sendManyWithAttachement(String subject, String[] to, String email,String[] fileName, byte[] fToByte[]) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);

            for(int i = 0; i< fToByte.length; i++)
            {
                String newFilename =fileName[i];
                String filePath = path  +"mail"+ File.separator + newFilename;
                //Créer l'objet File
                File f = new File(path + File.separator + "mail" + File.separator);
                if(!f.exists())
                {
                    boolean isCreated = f.mkdir();
                }
                try {
                    File fileToCopy = new File(filePath);
                    if(!fileToCopy.exists() && !fileToCopy.isDirectory())
                    {
                        customMultipartFile.transferTo(filePath,fToByte[i]);
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Impossible de copier ce fichier - {} " + ex.getMessage());
                }
                helper.addAttachment(newFilename, new File(filePath));
            }

            helper.setSubject(subject);
            helper.setFrom("POSTMASTER@saphiram.COM");
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public boolean sendManyWithAttachementBlob(String subject, String to, String email,String fileName, byte[] fToByte) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
                String newFilename =fileName;
                String filePath = path  +"mail"+ File.separator + newFilename;
                //Créer l'objet File
                File f = new File(path + File.separator + "mail" + File.separator);
                if(!f.exists())
                {
                    boolean isCreated = f.mkdir();
                }
                Random r=new Random();
                Integer rand=0;
                rand=r.nextInt();
                try {
                    File fileToCopy = new File(filePath);
                    if(!fileToCopy.exists() && !fileToCopy.isDirectory())
                    {
                        customMultipartFile.transferTo(filePath,fToByte);
                    }
                    else
                    {

                        filePath=filePath.replace(".pdf","")+rand+".pdf";
                        newFilename=newFilename.replace(".pdf","")+rand+".pdf";
                        customMultipartFile.transferTo(filePath,fToByte);
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Impossible de copier ce fichier - {} " + ex.getMessage());
                }
                helper.addAttachment(newFilename, new File(filePath));


            helper.setSubject(subject);
            helper.setFrom("POSTMASTER@saphiram.COM");
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public boolean sendManyWithAttachement2(String subject, String[] to, String email,String[] fileName, byte[] fToByte[]) {
        Boolean valeur=false;
        try {

            for(int i=0;i<to.length;i++) {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "utf-8");
                helper.setText(email, true);
                helper.setTo(to[i]);
                String newFilename = fileName[i];
                String filePath = path + "mail" + File.separator + newFilename;
                //Créer l'objet File
                File f = new File(path + File.separator + "mail" + File.separator);
                if (!f.exists()) {
                    boolean isCreated = f.mkdir();
                }
                try {
                    File fileToCopy = new File(filePath);
                    if (!fileToCopy.exists() && !fileToCopy.isDirectory()) {
                        customMultipartFile.transferTo(filePath, fToByte[i]);
                    }
                } catch (Exception ex) {
                    System.out.println("Impossible de copier ce fichier - {} " + ex.getMessage());
                }
                helper.addAttachment(newFilename, new File(filePath));

                helper.setSubject(subject);
                helper.setFrom("POSTMASTER@saphiram.COM");
                mailSender.send(mimeMessage);
                valeur=true;
            }

        } catch (MessagingException e) {
            return false;
        }
        return  valeur;
    }
}
