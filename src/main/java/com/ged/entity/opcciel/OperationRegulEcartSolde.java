package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationRegulEcartSolde", schema = "Operation")*/
public class OperationRegulEcartSolde extends Operation {
    private Double SoldeEspeceDepositaire;

    public OperationRegulEcartSolde() {
    }

    public Double getSoldeEspeceDepositaire() {
        return SoldeEspeceDepositaire;
    }

    public void setSoldeEspeceDepositaire(Double soldeEspeceDepositaire) {
        SoldeEspeceDepositaire = soldeEspeceDepositaire;
    }
}
