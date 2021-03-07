package org.example;

import java.util.ArrayList;
import java.util.List;

public class ListeEvenements {
    public List<EvenementsScenario> evenements;

    public ListeEvenements()
    {
        evenements = new ArrayList<>();
    }

    public void ajouterEvenement(EvenementsScenario evenement)
    {
        evenements.add(evenement);
    }

    public void supprimerEvenement(EvenementsScenario evenement)
    {
        evenements.remove(evenement);
    }

    public void toutSupprimerEvenement()
    {
        for (int i = 0; i < evenements.size(); i++)
            evenements.remove(evenements.get(i));
    }

    public void actionEvenementChoisi(int reponseJoueur, ListeFaction listeFaction, Republique rep)
    {
        for (int i = 0; i < evenements.size(); i++) {

            if (evenements.get(i).idReponse==reponseJoueur)
            {
                if (evenements.get(i).effetAttribut.equals("satisfaction"))
                {
                    listeFaction.changerPourcentageSatisfaction(evenements.get(i).effet,evenements.get(i).nomFaction);
                }

                if (evenements.get(i).effetAttribut.equals("partisans"))
                {
                    listeFaction.changerNombrePartisants(evenements.get(i).effet,evenements.get(i).nomFaction);
                }

                if (evenements.get(i).effetAttribut.equals("tresor"))
                {
                    rep.changerTresor(evenements.get(i).effet);
                }

                if (evenements.get(i).effetAttribut.equals("nourriture"))
                {
                    rep.changerUnitesNourriture(evenements.get(i).effet);
                }

                if (evenements.get(i).effetAttribut.equals("industrie"))
                {
                    if (evenements.get(i).effet<0) {
                        rep.reductionIndustrie(evenements.get(i).effet);
                    }else {
                        rep.augmentationIndustrie(evenements.get(i).effet);
                    }
                }

                if (evenements.get(i).effetAttribut.equals("agriculture"))
                {
                    if (evenements.get(i).effet<0) {
                        rep.reductionAgriculture(evenements.get(i).effet);
                    }else {
                        rep.augmentationAgriculture(evenements.get(i).effet);
                    }
                }
            }
        }
    }

    public void decrireAttribut(int id)
    {
        String temp="";
        for (int i = 0; i < evenements.size(); i++)
        {
            if (evenements.get(i).idReponse==id) {
                temp = "%";

                String chaineTotal = evenements.get(i).nomFaction +
                        " " +
                        evenements.get(i).effet + " " +
                        temp + " de " +
                        evenements.get(i).effetAttribut + " / " ;

                System.out.println(chaineTotal);
            }
        }
    }

}
