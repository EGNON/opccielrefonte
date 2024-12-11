package com.ged.service.standard.impl;

import com.ged.dao.standard.DocumentDao;
import com.ged.dto.standard.DocumentDto;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.service.standard.FileService;
import com.itextpdf.commons.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Transactional
public class FileServiceImpl implements FileService {
//    @Value("${media.upload.dir}")
//    private final static String DIRECTORY = null;
    private final DocumentDao documentDao;
    private final DocumentMapper documentMapper;
    @Autowired
    private  CustomMultipartFile customMultipartFile;

    public FileServiceImpl(DocumentDao documentDao, DocumentMapper documentMapper) {
        this.documentDao = documentDao;
        this.documentMapper = documentMapper;
    }

    public <T> Set<T> uploadFile(String path, List<MultipartFile> files, Set<T> medias) throws Throwable {
        int i = 0;
        Set<T> finalMedias = new HashSet<>();
        for (T media: medias)
        {
            Integer id = null;
//            System.out.println("Média - {} " + media);
            //Récupérer le nom du fichier
            String filename = files.get(i).getOriginalFilename();
            //Récupérer l'id du document
            Class<Integer> resultType = Integer.class;
            if(media instanceof DocumentDto) {
//                MethodType mt = MethodType.methodType( resultType );
//                MethodHandle methodHandle = MethodHandles.lookup().findVirtual(media.getClass(), "getIdPersonne", mt );
//                id = resultType.cast( methodHandle.invoke(media));
                Method retobj = (Method)media.getClass().getDeclaredMethod("getIdPersonne").invoke(media);
//                id = retobj;
//                System.out.println("Cast - {} " + retobj.toString());
            }
            //Récupérer le chemin du fichier
            String filePath = path + File.separator + filename;
//            System.out.println("Path - {} " + filePath);
            //Créer l'objet File
            File f = new File(path);
            if(!f.exists())
            {
                f.mkdir();
            }
            if(media instanceof DocumentDto)
                media.getClass().getMethod("setChemin", String.class).invoke(media, filePath);
            finalMedias.add(media);
            //Copier le fichier dans le dossier path
            Files.copy(files.get(i).getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            i++;
        }

        return finalMedias;
    }
    public Set<DocumentDto> uploadMedia(String path,  Set<DocumentDto> documents) throws Throwable {
        int i = 0;
        List<DocumentDto> documentDtoList = new ArrayList<>(documents);
        documentDtoList.sort((o1, o2) -> Math.toIntExact(o1.getIdDoc() - o2.getIdDoc()));
        //System.out.println("taille="+documentDtoList.size());
        Set<DocumentDto> finalMedias = new HashSet<>();
        for (DocumentDto media: documentDtoList) {
//            System.out.println("I = " + i + " Old -> " +
//                    media.getNomDoc() + "." + media.getExtensionDoc() + " New -> " + files.get(i).getOriginalFilename());
            //Récupérer le nom du fichier
//            files.set(i,media.getfToByte())
//            String filename = files.get(i).getOriginalFilename();
            //Récupérer le chemin du fichier
            String newFilename="";
            if(media.getTypeDocument()!=null){
                newFilename = media.getTypeDocument().getLibelleTypeDoc() + "_" + media.getIdDoc().toString() + "." + media.getExtensionDoc();
            }
            else
                newFilename = media.getNomDoc() + "_" + media.getIdDoc().toString() + "." + media.getExtensionDoc();

            String filePath = path + media.getIdDoc().toString() + File.separator + newFilename;
            //Créer l'objet File
            File f = new File(path + File.separator + media.getIdDoc().toString() + File.separator);
            if(!f.exists())
            {
                boolean isCreated = f.mkdir();
                //System.out.println("Création du dossier - {} " + isCreated);
                //System.out.println("Chemin - {} " + path + File.separator + media.getIdDoc().toString() + File.separator + "\\");
            }
            media.setChemin(filePath);
            media.setIdDoc(media.getIdDoc());
            documentDao.save(documentMapper.deDocumentDto(media));
            //System.out.println("nomdoc("+i+")"+media.getNomDoc());
            finalMedias.add(media);
            try {
                File fileToCopy = new File(filePath);
                if(!fileToCopy.exists() && !fileToCopy.isDirectory())
                {
                    //Copier le fichier dans le dossier path
                    //System.out.println(filePath);
                    customMultipartFile.transferTo(filePath,media.getfToByte());
//                    Files.copy(
//                            files.get(i).getInputStream(),
//                            Paths.get(filePath),
//                            StandardCopyOption.REPLACE_EXISTING);
//                    System.out.println("C'est un nouveau fichier !!");
                }
            }
            catch (Exception ex)
            {
                System.out.println("Impossible de copier ce fichier - {} " + ex.getMessage());
            }
            i++;
        }

        return finalMedias;
    }

    public Set<DocumentDto> uploadMediaBlob(String path,  Set<DocumentDto> documents) throws Throwable {
        int i = 0;
        List<DocumentDto> documentDtoList = new ArrayList<>(documents);
        documentDtoList.sort((o1, o2) -> Math.toIntExact(o1.getIdDoc() - o2.getIdDoc()));
        //System.out.println("taille="+documentDtoList.size());
        Set<DocumentDto> finalMedias = new HashSet<>();
        for (DocumentDto media: documentDtoList) {
//            System.out.println("I = " + i + " Old -> " +
//                    media.getNomDoc() + "." + media.getExtensionDoc() + " New -> " + files.get(i).getOriginalFilename());
            //Récupérer le nom du fichier
//            files.set(i,media.getfToByte())
//            String filename = files.get(i).getOriginalFilename();
            //Récupérer le chemin du fichier
            String newFilename="";
            if(media.getTypeDocument()!=null){
                newFilename = media.getTypeDocument().getLibelleTypeDoc() + "_" + media.getIdDoc().toString() + "." + media.getExtensionDoc();
            }
            else
                newFilename = media.getNomDoc() + "_" + media.getIdDoc().toString() + "." + media.getExtensionDoc();

            String filePath = path + media.getIdDoc().toString() + File.separator + newFilename;
            //Créer l'objet File
            File f = new File(path + File.separator + media.getIdDoc().toString() + File.separator);
            if(!f.exists())
            {
                boolean isCreated = f.mkdir();
                //System.out.println("Création du dossier - {} " + isCreated);
                //System.out.println("Chemin - {} " + path + File.separator + media.getIdDoc().toString() + File.separator + "\\");
            }
            media.setChemin(filePath);
            media.setIdDoc(media.getIdDoc());
            documentDao.save(documentMapper.deDocumentDto(media));
            //System.out.println("nomdoc("+i+")"+media.getNomDoc());
            finalMedias.add(media);
            try {
                File fileToCopy = new File(filePath);
                if(!fileToCopy.exists() && !fileToCopy.isDirectory())
                {
                    customMultipartFile.transferTo(filePath,media.getfToByte());
//                    Files.copy(
//                            files.get(i).getInputStream(),
//                            Paths.get(filePath),
//                            StandardCopyOption.REPLACE_EXISTING);
//                    System.out.println("C'est un nouveau fichier !!");
                }
            }
            catch (Exception ex)
            {
                System.out.println("Impossible de copier ce fichier - {} " + ex.getMessage());
            }
            i++;
        }

        return finalMedias;
    }
    @Override
    public Set<DocumentDto> uploadMedia(String path, List<MultipartFile> files, Set<DocumentDto> documents) throws Throwable {
        int i = 0;
        List<DocumentDto> documentDtoList = new ArrayList<>(documents);
        documentDtoList.sort((o1, o2) -> Math.toIntExact(o1.getIdDoc() - o2.getIdDoc()));
        //System.out.println("taille="+documentDtoList.size());
        Set<DocumentDto> finalMedias = new HashSet<>();
        for (DocumentDto media: documentDtoList) {
//            System.out.println("I = " + i + " Old -> " +
//                    media.getNomDoc() + "." + media.getExtensionDoc() + " New -> " + files.get(i).getOriginalFilename());
            //Récupérer le nom du fichier
            String filename = files.get(i).getOriginalFilename();
            //Récupérer le chemin du fichier
            String newFilename = media.getTypeDocument().getLibelleTypeDoc() + "_" + media.getIdDoc().toString() + "." + media.getExtensionDoc();
            String filePath = path + media.getIdDoc().toString() + File.separator + newFilename;
            //Créer l'objet File
            File f = new File(path + File.separator + media.getIdDoc().toString() + File.separator);
            if(!f.exists())
            {
                boolean isCreated = f.mkdir();
                //System.out.println("Création du dossier - {} " + isCreated);
                //System.out.println("Chemin - {} " + path + File.separator + media.getIdDoc().toString() + File.separator + "\\");
            }
            media.setChemin(filePath);
            if(media.getCompteRendu()!=null)
                System.out.println("idcr1="+media.getCompteRendu().getIdCR());
            else
                System.out.println("pass");

            media = documentMapper.deDocument(documentDao.save(documentMapper.deDocumentDto(media)));
            //System.out.println("nomdoc("+i+")"+media.getNomDoc());
            if(media.getCompteRendu()!=null)
                System.out.println("idcr2="+media.getCompteRendu().getIdCR());
            else
                System.out.println("passez");

            finalMedias.add(media);
            try {
                File fileToCopy = new File(filePath);
                if(!fileToCopy.exists() && !fileToCopy.isDirectory())
                {
                    //Copier le fichier dans le dossier path
                    Files.copy(
                            files.get(i).getInputStream(),
                            Paths.get(filePath),
                            StandardCopyOption.REPLACE_EXISTING);
//                    System.out.println("C'est un nouveau fichier !!");
                }
            }
            catch (Exception ex)
            {
                System.out.println("Impossible de copier ce fichier - {} " + ex.getMessage());
            }
            i++;
        }

        return finalMedias;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        //Récupérer le chemin du fichier
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }

    @Override
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            //
        }
        //System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {
        }
        return outputStream.toByteArray();
    }
}
