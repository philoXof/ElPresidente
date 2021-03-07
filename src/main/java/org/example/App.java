package org.example;

import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {
        Republique rep = new Republique(200, 15, 0, 15);
        ListeFaction listeFaction = new ListeFaction();
        XmlReader xmlReader = new XmlReader();
        System.out.println(rep.toString() + "\n");
        System.out.println(listeFaction.toString() + "\n");

        float satisfactionGlobal = 0;
        boolean jouer;
        do {
            do {
                xmlReader.lireXMLevenement("hiver", listeFaction, rep);
                System.out.println(listeFaction.toString() + "\n");
                System.out.println(rep.toString() + "\n");

                xmlReader.lireXMLevenement("printemps", listeFaction, rep);
                System.out.println(listeFaction.toString() + "\n");
                System.out.println(rep.toString() + "\n");

                xmlReader.lireXMLevenement("ete", listeFaction, rep);
                System.out.println(listeFaction.toString() + "\n");
                System.out.println(rep.toString() + "\n");

                xmlReader.lireXMLevenement("automne", listeFaction, rep);
                System.out.println(listeFaction.toString() + "\n");
                System.out.println(rep.toString() + "\n");

                /***** Industrie *****/

                System.out.println("Calcul de l'Industrialisation de votre République...");
                rep.calculeIndustrialisation();
                System.out.println("Industrialisation : " + rep.industrie + "\n");

                /***** Unités de nourriture générées par l'Agriculture *****/

                System.out.println("----------Unités de nourriture mise à jour avec celles générées par l'Agriculture----------");
                System.out.println(rep.calculUniteNourriture() + "\n");

                /***** Pot De Vin *****/

                System.out.println("Voulez-vous soudoyer une faction ? (oui = 1 | non = 0 )");
                Scanner sc = new Scanner(System.in);
                int soudoyer = sc.nextInt();
                while (soudoyer < 0 || soudoyer > 1) {
                    System.out.println(soudoyer + " ne correspond pas aux chiffres demandé");
                    soudoyer = sc.nextInt();
                }
                if (soudoyer == 1)
                {
                    System.out.println("--- Tarif : 15$ par partisans | Effet : 10% de satisfaction en plus ---");
                    System.out.println("Trésor :" + rep.tresor);
                    listeFaction.afficheNomFactions();

                    int idFaction = listeFaction.donneIdFactionASoudoyer();
                    System.out.println("Trésor :" + rep.tresor);
                    int nbPotDeVin = listeFaction.donneNombrePotDeVin();
                    listeFaction.potDeVin(rep, idFaction, nbPotDeVin);

                    System.out.println("Trésor :" + rep.tresor);
                    System.out.println(listeFaction.toString());
                }

                /***** Marché Alimentaire *****/

                System.out.println("Nombre de partisans total : " + listeFaction.nombreTotalPartisans());
                rep.menuMarcheAlimentaire();
                int nombreUniteNourriturreAchetee = rep.donneNombreAchatUniteNourriturre();
                rep.marcheAlimentaire(nombreUniteNourriturreAchetee);
                listeFaction.calculSatisfactionGlobal();

                /***** Agriculture *****/

                rep.elegibiliteAgriculture(listeFaction);
                System.out.println(listeFaction.toString() + "\n");
                System.out.println(rep.toString() + "\n");

                satisfactionGlobal = listeFaction.calculSatisfactionGlobal();

            }while (satisfactionGlobal > 50);


            /***** Annonce du motif de défaite *****/
            if (satisfactionGlobal < 50)
            {
                System.out.println("Perdu ! Votre Pourcentage de satisfaction Global est trop faible");
            }
            if(listeFaction.nombreTotalPartisans() < 0)
            {
                System.out.println("Perdu ! Tous vos partisans ont été tué");
            }

                /****** Rejouer ? *****/
            System.out.println("Voulez-vous rejouer ? oui = 1 | non = 0");
            Scanner sc  = new Scanner(System.in);
            int reponseJoueur = sc.nextInt();
            while(reponseJoueur < 0 || reponseJoueur > 1)
            {
                System.out.println("Voulez-vous rejouer ? oui = 1 | non = 0");
                reponseJoueur = sc.nextInt();
            }
            if(reponseJoueur == 1)
            {
                jouer = true;
            }
            else
            {
                jouer = false;
            }

        }while (jouer);
    }
}
