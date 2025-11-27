package com.ged.service.lab.impl;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.TransactionDto;
import com.ged.response.ResponseHandler;
import com.ged.service.lab.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@Transactional
public class Transaction2ServiceImpl implements TransactionService {
    private int page;
    private int size;
    @Autowired
    @Qualifier("refonteEntityManagerFactory")
    private EntityManager emOpcciel;
    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,long critere) {
        try {
//            System.out.println("pass");
            List<Object[]>  transaction;
            List<TransactionDto>  transactionDto=new ArrayList<>();

//            emOpcciel.unwrap(Session.class);
//            if (!emOpcciel.isOpen()) {
            emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();
//                System.out.println("pass");
//            }

            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_Transaction_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("IdCritereAlerte", Long.class, ParameterMode.IN);


            //Fournir les différents paramètres
            query.setParameter("IdCritereAlerte", critere);
//            System.out.println("passez");

            query.execute();
//            System.out.println("passons");
            transaction= query.getResultList();
//            System.out.println("passera");

            for(Object[] o:transaction)
            {
                TransactionDto transac=new TransactionDto();
                transac.setIdOperation(Long.valueOf(o[0].toString()));
                transac.setSigleOpcvm((o[1].toString()));
                transac.setDenominationOpcvm((o[2].toString()));
                transac.setDenomination((o[3].toString()));
                transac.setCodeNatureOperation((o[4].toString()));
                transac.setMontant(Double.valueOf(o[5].toString()));
                transac.setQtePart(Double.valueOf(o[6].toString()));
                transac.setNomPays((o[7].toString()));
                transactionDto.add(transac);
//                System.out.println(transac.getDenomination().toString()+" "+transac.getMontant());
            }
//            List<TransactionDto>  transactionDto;
////            emOpcciel.unwrap(Session.class);
////            if (!emOpcciel.isOpen()) {
//                emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();
////                System.out.println("pass");
////            }
//
//            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_Transaction2]");
//            //Déclarer les différents paramètres
//            query.registerStoredProcedureParameter("IdCritereAlerte", Long.class, ParameterMode.IN);
//
//
//            //Fournir les différents paramètres
//            query.setParameter("IdCritereAlerte", critere);
//
//
//            query.execute();
//            transactionDto= query.getResultList();
//            System.out.println(query.getResultList().size());
//            for(TransactionDto o:transactionDto)
//            {
//                System.out.println(o.getDenomination()+" "+o.getMontant());
//            }
            page= parameters.getStart();
            size=parameters.getLength();
            page+=1;
            int totalpages = transactionDto.size() / page;
            int max =0;
            if(page>=totalpages) {
                max = page + 9;//size*(page+1);//transactionDto.size();
                if(max>transactionDto.size())
                {
                    max=transactionDto.size();
                }
            }
            else
                if(totalpages>10) {
                    max = page + 9;//size*(page+1);
                    if(max>transactionDto.size())
                    {
                        max=transactionDto.size();
                    }
                }
                else
                    max=totalpages;
            int min =0;
            String nbreZero="1";
            String taille=String.valueOf(transactionDto.size());
            int diviseur=1;
            if(max==transactionDto.size())
            {
                for(int l=1;l<taille.length();l++)
                {
                    nbreZero+="0";
                }
            }
            diviseur=Integer.valueOf(nbreZero);
            int valeur=0;
            if(page >totalpages) {
                min = max - size;
                if(max==transactionDto.size())
                {
                    valeur=(max/diviseur);
                    min=valeur*diviseur;
                }
            }
            else
                if(totalpages>10) {
                    min = max - size;//size*page;
                    if(max==transactionDto.size())
                    {
                        valeur=(max/diviseur);
                        min=valeur*diviseur;
                    }
                }
                else
                    min=0;

            Sort sort=Sort.by(Sort.Direction.DESC,"denomination");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TransactionDto> transactionPage=new PageImpl<>(transactionDto.subList(min, max), pageable, transactionDto.size());

            List<TransactionDto> content =transactionPage.getContent().stream().collect(Collectors.toList());
            DataTablesResponse<TransactionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int) transactionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int) transactionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des alertes par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

        //return  null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        /*try {
            List<Object[]>  transaction;
            List<TransactionDto>  transactionDto=new List<TransactionDto>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<TransactionDto> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(TransactionDto transactionDto) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends TransactionDto> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends TransactionDto> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public TransactionDto get(int index) {
                    return null;
                }

                @Override
                public TransactionDto set(int index, TransactionDto element) {
                    return null;
                }

                @Override
                public void add(int index, TransactionDto element) {

                }

                @Override
                public TransactionDto remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<TransactionDto> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<TransactionDto> listIterator(int index) {
                    return null;
                }

                @Override
                public List<TransactionDto> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };
//            emOpcciel.unwrap(Session.class);
//            if (!emOpcciel.isOpen()) {
            emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();
//                System.out.println("pass");
//            }

            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_Transaction2]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("IdCritereAlerte", Long.class, ParameterMode.IN);


            //Fournir les différents paramètres
            query.setParameter("IdCritereAlerte", 10);

            query.execute();
            transaction= query.getResultList();

            for(Object[] o:transaction)
            {
                TransactionDto transac=new TransactionDto();
                transac.setIdOperation(Long.valueOf(o[0].toString()));
                transac.setSigleOpcvm((o[1].toString()));
                transac.setDenominationOpcvm((o[2].toString()));
                transac.setDenomination((o[3].toString()));
                transac.setCodeNatureOperation((o[4].toString()));
                transac.setMontant(Double.valueOf(o[5].toString()));
                transac.setQtePart(Double.valueOf(o[6].toString()));
                transac.setNomPays((o[7].toString()));
                transactionDto.add(transac);
//                System.out.println(transac.getDenomination().toString()+" "+transac.getMontant());
            }
            Sort sort=Sort.by(Sort.Direction.DESC,"denomination");

            return ResponseHandler.generateResponse(
                    "Liste des alertes par page datatable",
                    HttpStatus.OK,
                    transactionDto);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }*/

        return null;
    }
}
