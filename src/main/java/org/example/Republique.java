package org.example;

import java.util.Scanner;

public class Republique {
    public int tresor;
    public int agriculture;
    public int unitesNourriture;
    public int industrie;


    public Republique(int tresor, int agriculture ,int unitesNourriture, int industrie) {
        this.tresor = tresor;
        this.agriculture = agriculture;
        this.unitesNourriture = unitesNourriture;
        this.industrie = industrie;
    }

    @Override
    public String toString()
    {
        return String.format("République {\n Trésor : %d \n Agriculture : %d \n Unites de nouriture : %d \n Industrie : %d \n}",tresor, agriculture, unitesNourriture, industrie);
    }

    public void changerTresor(int ajoutTresor)
    {
        this.tresor += ajoutTresor;
        if(tresor<0) this.tresor = 0;
    }

    public void changerUnitesNourriture(int ajoutUnitesNourriture)
    {
        this.unitesNourriture += ajoutUnitesNourriture;
        if(unitesNourriture < 0) this.unitesNourriture = 0;
    }

    public void augmentationIndustrie(int augmentation)
    {
        if((industrie + agriculture) < 100)
        {
            if( (industrie + agriculture + augmentation) < 100 )
            {
                this.industrie += augmentation;
            }
            else
            {
                this.industrie = (100 - agriculture);
            }
        }
    }

    public void reductionIndustrie(int reduction)
    {
        this.industrie += reduction;
        if (industrie < 0)
        {
            this.industrie = 0;
        }
    }
    public void augmentationAgriculture(int augmentation)
    {
        if((industrie + agriculture) < 100)
        {
            if( (industrie + agriculture + augmentation) < 100 )
            {
                this.agriculture += augmentation;
            }
            else
            {
                this.agriculture = (100 - industrie);
            }
        }
    }
    public void reductionAgriculture(int reduction)
    {
        this.agriculture += reduction;
        if (agriculture < 0)
        {
            this.agriculture = 0;
        }
    }

    /****************************** Bilan de fin d'année ******************************/
    public void calculeIndustrialisation()
    {
        tresor += industrie * 10;
    }

    public int calculUniteNourriture ()
    {
        unitesNourriture = agriculture * 40;
        String.format("%d", unitesNourriture); //Debug
        return unitesNourriture;
    }
            /***** Marché Alimentaire *****/
    //affiche simplement des infos en rapport avec le marche alimentaire
    public void menuMarcheAlimentaire()
    {
        System.out.println("-_-_-_-_-_Bienvenue dans le Marché Alimentaire-_-_-_-_-_");
        System.out.println("Unités de Nourritures Avant : " + unitesNourriture);
        System.out.println("Votre Argent -> " + tresor);
        System.out.println("Tarif : 1 unités de nourriture = 8$ \n");
        System.out.println("Combien d'unités souhaitez-vous acheter ?");
    }

    /**
     * @return le nombre d'unité de nourriture que le joueur souhaite acheter
     */
    public int donneNombreAchatUniteNourriturre ()
    {
        Scanner sc = new Scanner(System.in);
        int nombreUniteNourriturreAchetee = sc.nextInt();

        while(tresor - (nombreUniteNourriturreAchetee * 8) < 0)
        {
            System.out.println("Vous n'avez pas assez d'argent !");
            System.out.println("Acheter moins ! : ");
            nombreUniteNourriturreAchetee = sc.nextInt();
        }
        return nombreUniteNourriturreAchetee;
    }

    public void marcheAlimentaire(int nombreUniteNourriturreAchetee)
    {
        unitesNourriture += nombreUniteNourriturreAchetee;
        tresor -= (nombreUniteNourriturreAchetee * 8);
        System.out.println("Récapitulatif d'achat : \n ");
        String.format("Récapitulatif d'achat : %d unités de nourriture pour un TOTAL de : %d $",nombreUniteNourriturreAchetee, nombreUniteNourriturreAchetee * 8);

        System.out.println("Votre Argent -> " + tresor);
        System.out.println("Unités de Nourritures après : " + unitesNourriture);
    }
            /***** Agriculture *****/
    public void elegibiliteAgriculture (ListeFaction listeFaction)
    {
        //un partisans consomme 4 unités de nourriture par an
        int consommationPartisans = listeFaction.nombreTotalPartisans() * 4;
        unitesNourriture -= consommationPartisans;

        if (unitesNourriture > 0)
        {
            System.out.println("Naissance");
            listeFaction.naissancePartisans();
        }
        else if(unitesNourriture < 0) //elimination
        {
            System.out.println("Elimination");
            for (int i = unitesNourriture; i < 0; i++) //parcourir l'écart entre les unités de nourriture et 0
            {
                listeFaction.eliminationPartisans();
            }
            String.format("La nourriture était insuffisante %d partisans ont été tué", (0 - unitesNourriture)); //nombre négatif
            unitesNourriture = 0;
        }
    }
    /*************************************************************************************************/

    /*****Getters and Setters*****/
    public int getTresor() {
        return tresor;
    }

    public void setTresor(int tresor) {
        this.tresor = tresor;
    }

    public int getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(int agriculture) {
        this.agriculture = agriculture;
    }

    public int getIndustrie() {
        return industrie;
    }

    public void setIndustrie(int industrie) {
        this.industrie = industrie;
    }

    public int getUnitesNourriture() {
        return unitesNourriture;
    }

    public void setUnitesNourriture(int unitesNourriture) {
        this.unitesNourriture = unitesNourriture;
    }
    /****************************/
}