package org.example;

public class Evenements
{
    public int idReponse;
    public String premierNomFaction;
    public String premiereEffetAttribut;
    public int premiereEffet;
    public String deuxiemeNomFaction;
    public String deuxiemeEffetAttribut;
    public int deuxiemeEffet;

    //
    public Evenements(int idReponse,
                      String premierNomFaction,
                      String premiereEffetAttribut,
                      int premiereEffet,
                      String deuxiemeNomFaction,
                      String deuxiemeEffetAttribut,
                      int deuxiemeEffet)
    {
        this.idReponse = idReponse;
        this.premierNomFaction = premierNomFaction;
        this.premiereEffetAttribut = premiereEffetAttribut;
        this.premiereEffet = premiereEffet;
        this.deuxiemeNomFaction = deuxiemeNomFaction;
        this.deuxiemeEffetAttribut = deuxiemeEffetAttribut;
        this.deuxiemeEffet = deuxiemeEffet;
    }
    //
    public void actionEvenement()
    {

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

    public String getPremierNomFaction()
    {
        return premierNomFaction;
    }

    public void setPremierNomFaction(String premierNomFaction)
    {
        this.premierNomFaction = premierNomFaction;
    }

    public String getPremiereEffetAttribut()
    {
        return premiereEffetAttribut;
    }

    public void setPremiereEffetAttribut(String premiereEffetAttribut) { this.premiereEffetAttribut = premiereEffetAttribut; }

    public int getPremiereEffet()
    {
        return premiereEffet;
    }

    public void setPremiereEffet(int premiereEffet)
    {
        this.premiereEffet = premiereEffet;
    }

    public String getDeuxiemeNomFaction()
    {
        return deuxiemeNomFaction;
    }

    public void setDeuxiemeNomFaction(String deuxiemeNomFaction)
    {
        this.deuxiemeNomFaction = deuxiemeNomFaction;
    }

    public String getDeuxiemeEffetAttribut() { return deuxiemeEffetAttribut; }

    public void setDeuxiemeEffetAttribut(String deuxiemeEffetAttribut) { this.deuxiemeEffetAttribut = deuxiemeEffetAttribut; }

    public int getDeuxiemeEffet()
    {
        return deuxiemeEffet;
    }

    public void setDeuxiemeEffet(int deuxiemeEffet)
    {
        this.deuxiemeEffet = deuxiemeEffet;
    }
}