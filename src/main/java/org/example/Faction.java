package org.example;

public class Faction
{
    public float pourcentageSatisfaction;
    public String nom;
    public int nombrePartisans;

    public Faction(int nombrePartisants,String nom,float pourcentageSatisfaction)
    {
        this.nom = nom;
        this.nombrePartisans = nombrePartisants;
        this.pourcentageSatisfaction = pourcentageSatisfaction;
    }

    @Override
    public String toString() {
        return String.format("Faction { \n Nom : %s \n Pourcentage Satisfaction : %f \n Nombre de partisants : %d \n}",getNom(), pourcentageSatisfaction, nombrePartisans);
    }

    /*****Getters and Setters*****/
    Faction retourneFaction(){
        return this;
    }

    public float getPourcentageSatisfaction() {
        return pourcentageSatisfaction;
    }

    public void setPourcentageSatisfaction(int pourcentageSatisfaction) {
        this.pourcentageSatisfaction = pourcentageSatisfaction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombrePartisans() {
        return nombrePartisans;
    }

    public void setNombrePartisans(int nombrePartisans) {
        this.nombrePartisans = nombrePartisans;
    }
    /****************************/

    public void reductionNombrePartisants(int partisantsReduction)
    {
        if(nombrePartisans != 0)
        {
            int nombrePartisantSoustraction;
            nombrePartisantSoustraction = (nombrePartisans * partisantsReduction) / 100;
            this.nombrePartisans += nombrePartisantSoustraction;
        }
        if (nombrePartisans < 0)
        {
            this.nombrePartisans = 0;
        }
    }

    public void reductionPourcentageSatisfaction(int satisfactionReduction)
    {
        this.pourcentageSatisfaction += satisfactionReduction;

        if(pourcentageSatisfaction < 0)
        {
            this.pourcentageSatisfaction = 0;
        }

    }

    public void augmentationNombrePartisants(int PartisantsAugmentation)
    {
        if(nombrePartisans != 0)
        {
            int nombrePartisantAddition;
            nombrePartisantAddition = (nombrePartisans * PartisantsAugmentation) / 100;
            this.nombrePartisans += nombrePartisantAddition;

        }
    }

    public void augmentationPourcentageSatisfaction(int satisfactionAugmentation)
    {
        if(pourcentageSatisfaction != 0)
        {
            this.pourcentageSatisfaction += satisfactionAugmentation;

            if(pourcentageSatisfaction > 100)
            {
                this.pourcentageSatisfaction = 100;
            }
        }
    }
}