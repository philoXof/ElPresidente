package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ListeFaction {
    public List<Faction> factions;

    public ListeFaction()
    {
        factions = new ArrayList<>();

        ajouterFaction(new Faction (15,"LOYALISTE",100));
        ajouterFaction(new Faction (15,"CAPITALISTE",50));
        ajouterFaction(new Faction (15,"COMMUNISTES",50));
        ajouterFaction(new Faction (15,"LIBEREAUX",50));
        ajouterFaction(new Faction (15,"RELIGIEUX",50));
        ajouterFaction(new Faction (15,"ECOLOGISTE",50));
        ajouterFaction(new Faction (15,"NATIONALISTE",50));
        ajouterFaction(new Faction (15,"MILITARISTE",50));
    }

    public void ajouterFaction(Faction faction) {
        factions.add(faction);
    }

    @Override
    public String toString() {
        return "ListeFaction{" +
                "factions=" + factions +
                '}';
    }

    /**
     * @return une faction au hasard
     */
    public Object selectionneFactionAleatoirement ()
    {
        int numeroSelectionAleatoire = (int) (Math.random() * factions.size()); // chiffre entre 0 et 7
        for(int i = 0; i < factions.size(); i++)
        {
            if (i == numeroSelectionAleatoire)
            {
                return factions.get(i).retourneFaction();
            }
        }
        return "!!! Erreur selectonneFactionAleatoirement() !!!";
    }

    /**
     * Affiche le noms des factions précédés de leurs indices
     * Permet d'éviter les fautes de frappes de l'utilisateur
     */
    public void afficheNomFactions ()
    {
        System.out.println("!!! Nom des factions !!!");
        for (int i = 1; i < factions.size(); i++)
        {
            System.out.println(i + " - " +  factions.get(i).nom);
        }
    }

    /**
     * @param nbPartisans
     * @param nombrePotDeVin
     * @return Montant du pot de vin à appliquer
     */
    public float calculMontantPotDeVin(int nbPartisans, int nombrePotDeVin)
    {
        return 15 * nbPartisans * nombrePotDeVin;
    }

    public int donneIdFactionASoudoyer ()
    {
        System.out.println("Id de la Faction à soudoyer : ");
        Scanner sc = new Scanner(System.in);
        int idFaction = sc.nextInt();

        while(idFaction < 1 || idFaction > 7)
        {
            System.out.println("l'Id de la Faction à soudoyer n'existe pas, essayez encore : ");
            idFaction = sc.nextInt();
        }
        return idFaction;
    }

    public int donneNombrePotDeVin ()
    {
        int nbPotDeVin;

        do {
            System.out.println("Combien de Pot De Vin souhaitez-vous appliquer ?\n Info : 10% de satisfaction en plus par Pot De Vin)");
            System.out.println("donneNombrePotDeVin()"); //Debug
            Scanner sc = new Scanner(System.in);
            nbPotDeVin = sc.nextInt();
        }while (nbPotDeVin <= 0);

        return nbPotDeVin;
    }

    /**
     * @param republique
     * @param idFaction
     * @param nbPotDeVin
     */
    public void potDeVin (Republique republique, int idFaction, int nbPotDeVin)
    {
        for(int i = 0; i < factions.size(); i++)
        {
            if (i == idFaction)
            {
                //s'assurer que le montant n'est pas supérieur au trésor
                while (calculMontantPotDeVin(factions.get(i).nombrePartisans, nbPotDeVin) > republique.tresor)
                {
                    nbPotDeVin = donneNombrePotDeVin();
                }
                republique.tresor -= calculMontantPotDeVin(factions.get(i).nombrePartisans, nbPotDeVin);  //Cette action coûte 15$ par partisan
                factions.get(i).pourcentageSatisfaction += (10 * nbPotDeVin); //une hausse de 10 % de la satisfaction de la faction concernée

                // engendre une perte de satisfaction des loyalistes à hauteur du montant divisé par 10
                factions.get(0).pourcentageSatisfaction -= (calculMontantPotDeVin(factions.get(0).nombrePartisans, nbPotDeVin) / 10);

                //S'assurer que le pourcentage ne soit pas négatif
                if (factions.get(0).pourcentageSatisfaction < 0)
                {
                    factions.get(0).pourcentageSatisfaction = 0;
                }
            }
        }
    }

    /**
     * @return La Satisfaction GLobal de la République à l'aide du calcul donné dans le sujet
     */
    public float calculSatisfactionGlobal()
    {
        float satisfactionGlobal = 0;

        for(Faction faction : factions)
        {
            satisfactionGlobal += ((faction.pourcentageSatisfaction * faction.nombrePartisans) / nombreTotalPartisans());
        }
        System.out.println("-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("Votre République est à " + satisfactionGlobal + "% de satisfaction globale");
        System.out.println("-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        return satisfactionGlobal;
    }

    public int nombreTotalPartisans()
    {
        int totalPartisant=0;
        for (Faction faction : factions) {
            totalPartisant+=faction.nombrePartisans;
        }
        return totalPartisant;
    }

    private void reductionSatisfactionApresElimination()
    {
        for (Faction faction : factions)
        {
            faction.pourcentageSatisfaction -= 2;
            if (faction.pourcentageSatisfaction < 0)
            {
                faction.pourcentageSatisfaction = 0;
            }
        }
    }

    public void eliminationPartisans()
    {
        System.out.println("! Elimination !");

        //éliminer des partisants aléatoirements jusqu'à ce que la production agricole soit suffisante pour les partisants restants
        Faction factionChoisiAleatoirement = (Faction) selectionneFactionAleatoirement();
        System.out.println(factionChoisiAleatoirement.toString()); //Debug

        while (factionChoisiAleatoirement.nombrePartisans == 0 && nombreTotalPartisans()!=0)
        {
            factionChoisiAleatoirement = (Faction) selectionneFactionAleatoirement();
        }
        factionChoisiAleatoirement.nombrePartisans -= 1;

        if(factionChoisiAleatoirement.nombrePartisans <= 0)
        {
            factionChoisiAleatoirement.nombrePartisans = 0;
        }
        // chaque partisans éliminé retire 2% de satisfation à toutes les factions
        reductionSatisfactionApresElimination();
    }

    public String naissancePartisans()
    {
        int pourcentageAugmentationPopulation = (int) (Math.random() * 10);
        int nombreNaissance = (nombreTotalPartisans() * pourcentageAugmentationPopulation)/100;

        for (int i = 0; i < nombreNaissance; i++)
        {
            //ajout des partisans un par un pour un melange plus homogène
            Faction factionChoisie = (Faction) selectionneFactionAleatoirement();
            factionChoisie.nombrePartisans += 1;
        }
        return String.format("Grace à votre excédants d'agriculture il y a eu %d naissance(s) cette année !",nombreNaissance);
    }

    public void changerNombrePartisants(int partisantsReduction,String nomFaction)
    {

        for (int i = 0; i < factions.size(); i++)
        {
            if (factions.get(i).nom.equals(nomFaction))
            {
                if(partisantsReduction<0)
                {
                    factions.get(i).reductionNombrePartisants(partisantsReduction);
                }else
                {
                    factions.get(i).augmentationNombrePartisants(partisantsReduction);
                }
            }
        }
    }

    public void changerPourcentageSatisfaction(int satisfactionReduction,String nomFaction)
    {

        for (int i = 0; i < factions.size(); i++)
        {
            if (factions.get(i).nom.equals(nomFaction))
            {
                if(satisfactionReduction<0)
                {
                    factions.get(i).reductionPourcentageSatisfaction(satisfactionReduction);
                }else
                {
                    factions.get(i).augmentationPourcentageSatisfaction(satisfactionReduction);
                }
            }
        }
    }
}
