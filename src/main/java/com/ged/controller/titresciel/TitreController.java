package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ObligationDto;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/titres")
public class TitreController {
    private final TitreService titreService;
    private final TcnService tcnService;
    private final DroitService droitService;
    private final DatService datService;
    private final ObligationService obligationService;
    private final ActionService actionService;
    private final OpcService opcService;

    public TitreController(TitreService titreService, TcnService tcnService, DroitService droitService, DatService datService, ObligationService obligationService, ActionService actionService, OpcService opcService) {
        this.titreService = titreService;
        this.tcnService = tcnService;
        this.droitService = droitService;
        this.datService = datService;
        this.obligationService = obligationService;
        this.actionService = actionService;
        this.opcService = opcService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> afficherSelonId(@PathVariable Long id){
        return titreService.afficher(id);
    }

    @GetMapping("typetitre/{code}")
    public ResponseEntity<Object> afficherSelonTypeTitre(@PathVariable String code){
        return titreService.afficherSelonTypeTitre(code);
    }
    @PostMapping("/datatable-{qualite}/liste/{classname}")
    public ResponseEntity<Object> datatableList(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite,
            @PathVariable String classname) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> cls = Class.forName("com.ged.entity.titresciel." + classname);
//        Object clsInstance = cls.newInstance();
        return switch (qualite.toLowerCase()) {
            case "opc" -> opcService.afficherTous(params);
            case "tcn" -> tcnService.afficherTous(params);
            case "droits" -> droitService.afficherTous(params);
            case "dat" -> datService.afficherTous(params);
            case "obligations" -> obligationService.afficherTous(params);
            case "actions" -> actionService.afficherTous(params);
            case "autres" -> ResponseHandler.generateResponse(
                    "Liste des Autres titres par page datatable",HttpStatus.OK,null);
            default -> titreService.afficherTous(params);
        };
    }
    @GetMapping("/liste")
    public ResponseEntity<Object> afficherTous(){
        return titreService.afficherTous();
    }
    @GetMapping(value= "/columns-{qualite}/name")
    public List<String> tableColumnsName(@PathVariable String qualite) throws ClassNotFoundException, NoSuchMethodException {
        List<String> Columns = new ArrayList<>();
        List<Field> titreFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Titre").getDeclaredFields());
        switch (qualite.toLowerCase()) {
            case "opc" -> {
                List<Field> opcFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Opc").getDeclaredFields());
                List<Field> allFields = Stream.concat(titreFields.stream(), opcFields.stream()).toList();
                for (Field field : allFields) {
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
                    }
                }
            }
            case "tcn" -> {
                List<Field> tcnFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Tcn").getDeclaredFields());
                List<Field> allFields = Stream.concat(titreFields.stream(), tcnFields.stream()).toList();
                for (Field field : allFields) {
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
                    }
                }
            }
            case "droits" -> {
                List<Field> droitFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Droit").getDeclaredFields());
                List<Field> allFields = Stream.concat(titreFields.stream(), droitFields.stream()).toList();
                for (Field field : allFields) {
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
                    }
                }
            }
            case "dat" -> {
                List<Field> datFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Dat").getDeclaredFields());
                List<Field> allFields = Stream.concat(titreFields.stream(), datFields.stream()).toList();
                for (Field field : allFields) {
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
                    }
                }
            }
            case "obligations" -> {
//                List<Field> personFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Obligation").getSuperclass().getDeclaredFields());
                List<Field> obligationFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Obligation").getDeclaredFields());
                List<Field> allFields = Stream.concat(titreFields.stream(), obligationFields.stream()).toList();
                //librairieService.inspectEntityColumns("com.ged.entity.titresciel.Obligation");
                for (Field field : allFields) {
//                    Column col = field.getAnnotation(Column.class);
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
//                        System.out.println("Columns: "+col);
                    }
                }
            }
            case "actions" -> {
                List<Field> actionFields = Arrays.asList(Class.forName("com.ged.entity.titresciel.Action").getDeclaredFields());
                List<Field> allFields = Stream.concat(titreFields.stream(), actionFields.stream()).toList();
                for (Field field : allFields) {
//                    Column col = field.getAnnotation(Column.class);
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
//                        System.out.println("Columns: "+col);
                    }
                }
            }
            default -> {
                for (Field field : titreFields) {
                    if (!field.getName().equals("")) {
                        Columns.add(field.getName());
                    }
                }
            }
        };
        return Columns;
    }

    @PostMapping("/afficher-{qualite}/{id}")
    public ResponseEntity<Object> afficher(@PathVariable Long id, @PathVariable String qualite) {
        /*List<TableMetadata> metadata = tableMetadataRepository.findAll();
        TableMetadata metadata1 = tableMetadataRepository.findByTableName("T_Opc");*/
        return switch (qualite.toLowerCase()) {
            case "opc" -> opcService.afficher(id);
            case "tcn" -> tcnService.afficher(id);
            case "droits" -> droitService.afficher(id);
            case "dat" -> datService.afficher(id);
            case "obligations" -> obligationService.afficher(id);
            case "actions" -> actionService.afficher(id);
            default -> null;
        };
    }

    @PostMapping("/{symbole}")
    public ResponseEntity<Object> afficher(@PathVariable String symbole) {
        return titreService.findBySymbolTitre(symbole);
    }

    @PostMapping("/creer-{qualite}")
    public ResponseEntity<Object> creer(@RequestBody Object titreDto, @PathVariable String qualite) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ResponseEntity<Object> result = null;
        switch (qualite.toLowerCase()) {
            /*case "opc" -> result = opcService.creer((OpcDto)titreDto);
            case "tcn" -> result = tcnService.creer((TcnDto)titreDto);
            case "droits" -> result = droitService.creer((DroitDto)titreDto);
            case "dat" -> result  = datService.creer((DatDto)titreDto);*/
            case "obligations" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.ObligationDto");
                ObligationDto obligationDto = new ObligationDto();
                loadProperties(titreDto, obligationDto);
//                result = obligationService.creer((ObligationDto)titreDto);
            }
//            case "actions" -> result = actionService.creer((ActionDto)titreDto);
            default -> {
                
            }
        }
        return result;
    }

    /**
     * This methods transfer the attributes from one class to another class if it
     * has null values.
     *
     * @param fromClass from class
     * @param toClass   to class
     */
    private void loadProperties(Object fromClass, Object toClass) {
        if (Objects.isNull(fromClass) || Objects.isNull(toClass))
            return;

        Field[] fields = toClass.getClass().getDeclaredFields();
        Field[] fieldsSuperClass = toClass.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsFinal = new Field[fields.length + fieldsSuperClass.length];

        Arrays.setAll(fieldsFinal, i -> (i < fields.length ? fields[i] : fieldsSuperClass[i - fields.length]));

        for (Field field : fieldsFinal) {
            field.setAccessible(true);
            try {
                String propertyKey = field.getName();
                if (field.get(toClass) == null) {
                    /*ReflectionUtils.doWithFields(fromClass.getClass(), fromField -> {
                        System.out.println("Field name: " + fromField.getName());
                        fromField.setAccessible(true);
                        System.out.println("Field value: "+ fromField.get(fromClass));
                    });*/

                    Field defaultPropertyField = fromClass.getClass().getDeclaredField(propertyKey);
                    defaultPropertyField.setAccessible(true);
                    Object propertyValue = defaultPropertyField.get(fromClass);
                    if (propertyValue != null)
                        field.set(toClass, propertyValue);
                }
            } catch (IllegalAccessException e) {
                System.out.println("Error while loading properties from " + fromClass.getClass() +" and to " +toClass.getClass());
            } catch (NoSuchFieldException e) {
                System.out.println("Exception occurred while loading properties from " + fromClass.getClass()+" and to " +toClass.getClass());
            }
        }
    }

    @PutMapping("/modifier-{qualite}/{id}")
    public Object modifier(@RequestBody Object titreDto, @PathVariable Long id, @PathVariable String qualite) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ResponseEntity<Object> result;
        /*switch (qualite.toLowerCase()) {
            case "opc" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.OpcDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = opcService.modifier((OpcDto)titreDto);
            }
            case "tcn" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.TcnDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = tcnService.modifier((TcnDto)titreDto);
            }
            case "droits" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.DroitDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = droitService.modifier((DroitDto)titreDto);
            }
            case "dat" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.DatDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = datService.modifier((DatDto)titreDto);
            }
            case "obligations" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.ObligationDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = obligationService.modifier((ObligationDto)titreDto);
            }
            case "actions" -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.ActionDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = actionService.modifier((ActionDto)titreDto);
            }
            default -> {
                Class<?> c = Class.forName("com.ged.dto.titresciel.TitreDto");
                Method method = c.getMethod("setIdTitre", Long.class);
                method.invoke(titreDto,  id);
                result = titreService.modifier((TitreDto)titreDto);
            }
        };*/

        return titreDto;
    }
}
