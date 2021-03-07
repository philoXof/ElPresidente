package org.example;

public class EvenementsScenario
{
    public int idReponse;
    public String nomFaction;
    public String effetAttribut;
    public int effet;


    public EvenementsScenario(int idReponse,
                              String nomFaction,
                              String effetAttribut,
                              int effet)
    {
        this.idReponse = idReponse;
        this.nomFaction = nomFaction;
        this.effetAttribut = effetAttribut;
        this.effet = effet;
    }


    // getter et setter
    public int getIdReponse()
    {
        return idReponse;
    }

    public void setIdReponse(int idReponse)
    {
        this.idReponse = idReponse;
    }

    public String getNomFaction()
    {
        return nomFaction;
    }

    public void setNomFaction(String nomFaction)
    {
        this.nomFaction = nomFaction;
    }

    public String getEffetAttribut()
    {
        return effetAttribut;
    }

    public void setEffetAttribut(String effetAttribut) { this.effetAttribut = effetAttribut; }

    public int getEffet()
    {
        return effet;
    }

    public void setEffet(int effet)
    {
        this.effet = effet;
    }


}


