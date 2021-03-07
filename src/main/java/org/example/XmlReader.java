package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Scanner;

public class XmlReader {

    private final Evenements reponse1 = new Evenements(0,"","",0,"","",0);
    private final Evenements reponse2 = new Evenements(0,"","",0,"","",0);
    private final Evenements reponse3 = new Evenements(0,"","",0,"","",0);
    private final Evenements reponse4 = new Evenements(0,"","",0,"","",0);


    public void lireXMLevenement(final String  saisonActuelle,ListeFaction listeFaction,Republique rep)
    {
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            SAXParser saxParser = spfactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler()
            {
                boolean question = false;
                boolean reponse = false;
                boolean saison = false;
                boolean idEvenement = false;
                final String baliseReponse = "reponse";
                final String baliseQuestion = "question";
                final String nomSaison = saisonActuelle;
                int nombreAleatoire = 0;
                int idReponse = 0;

                /*cette méthode est invoquée à chaque fois que parser rencontre une balise ouvrante '<' */
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
                {
                    if (qName.equalsIgnoreCase(nomSaison))
                    { saison = true; }

                    if (saison)
                    {

                        int longueur = attributes.getLength();
                        for (int i = 0; i < longueur; i++)
                        {
                            String nomAttribut = attributes.getQName(i);
                            String valeurAttribut = attributes.getValue(i);

                            if (nomAttribut.equals("nombreEvenement"))
                            // nombreEvenement dans le xml correspond au(x) nombre(s) d'évènement(s) dans la saison (attribut)
                            {
                                nombreAleatoire=(int) (Math.random() * Integer.parseInt(valeurAttribut))+1;
                            }

                            if(nomAttribut.equals("idEvenement"))
                            {
                                idEvenement = Integer.parseInt(valeurAttribut) == nombreAleatoire;
                            }

                            if(nomAttribut.equals("idReponse"))
                            {
                                idReponse = Integer.parseInt(valeurAttribut);
                            }
                            if(idEvenement) {
                                switch (idReponse) {
                                    case 1:
                                        MAJevenementEffet(reponse1, nomAttribut, valeurAttribut);
                                        break;
                                    case 2:
                                        MAJevenementEffet(reponse2, nomAttribut, valeurAttribut);
                                        break;
                                    case 3:
                                        MAJevenementEffet(reponse3, nomAttribut, valeurAttribut);
                                        break;
                                    case 4:
                                        MAJevenementEffet(reponse4, nomAttribut, valeurAttribut);
                                        break;
                                    default:
                                }
                            }
                        }

                        if(idEvenement)
                        {
                            if (qName.equalsIgnoreCase(baliseQuestion)) { question = true; }
                            if (qName.equalsIgnoreCase(baliseReponse)) { reponse = true; }
                        }
                    }
                }


                public void endElement(String uri, String localName, String qName) throws SAXException
                {
                    if (qName.equalsIgnoreCase(nomSaison))
                    {
                        saison =false;
                    }
                    if (saison) {
                        if (qName.equalsIgnoreCase(baliseQuestion))
                        {
                            question = false;
                        }

                        if (qName.equalsIgnoreCase(baliseReponse))
                        {
                            reponse = false;
                        }
                    }
                }


                public void characters(char[] contenu, int debut, int longueur) throws SAXException
                {
                    if (question)
                    {
                        System.out.println(
                                "Question : " +
                                        new String(contenu, debut, longueur)+ "\n"
                        );
                        question = false;
                    }

                    if (reponse)
                    {
                        System.out.println(
                                "Réponse " + idReponse +" : " +
                                        new String(contenu, debut, longueur));
                        switch (idReponse)
                        {
                            case 1:
                                decrireAttribut(reponse1);
                                break;
                            case 2:
                                decrireAttribut(reponse2);
                                break;
                            case 3 :
                                decrireAttribut(reponse3);
                                break;
                            case 4:
                                decrireAttribut(reponse4);
                                break;
                            default:
                        }

                        reponse = false;
                    }
                }


            };
            saxParser.parse("src/main/java/org/example/xml.xml", handler);

            choixReponse(listeFaction,rep);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void choixReponse(ListeFaction listeFaction,Republique rep)
    {
        Scanner sc = new Scanner(System.in);
        int reponseJoueur=0;
        while(reponseJoueur < 1 || reponseJoueur > 4)
        {
            System.out.println("Choisissez un évènement : ");
            reponseJoueur = sc.nextInt();
        }
        System.out.println(reponseJoueur);
        switch (reponseJoueur)
        {
            case 1:
                actionEvenementChoisi(reponse1,listeFaction,rep);
                break;
            case 2:
                actionEvenementChoisi(reponse2,listeFaction,rep);
                break;
            case 3 :
                actionEvenementChoisi(reponse3,listeFaction,rep);
                break;
            case 4:
                actionEvenementChoisi(reponse4,listeFaction,rep);
                break;
            default:
        }
    }


    private void actionEvenementChoisi(Evenements evenements, ListeFaction listeFaction, Republique rep)
    {

        if (evenements.premiereEffetAttribut.equals("satisfaction"))
        {
            listeFaction.changerPourcentageSatisfaction(evenements.premiereEffet,evenements.premierNomFaction);
        }
        if (evenements.deuxiemeEffetAttribut.equals("satisfaction"))
        {
            listeFaction.changerPourcentageSatisfaction(evenements.deuxiemeEffet,evenements.deuxiemeNomFaction);
        }

        if (evenements.premiereEffetAttribut.equals("partisans"))
        {
            listeFaction.changerNombrePartisants(evenements.premiereEffet,evenements.premierNomFaction);
        }
        if (evenements.deuxiemeEffetAttribut.equals("partisans"))
        {
            listeFaction.changerNombrePartisants(evenements.deuxiemeEffet,evenements.deuxiemeNomFaction);
        }

        if (evenements.premiereEffetAttribut.equals("tresor"))
        {
            rep.changerTresor(evenements.premiereEffet);
        }
        if (evenements.deuxiemeEffetAttribut.equals("tresor"))
        {
            rep.changerTresor(evenements.deuxiemeEffet);
        }

        if (evenements.premiereEffetAttribut.equals("nourriture"))
        {
            rep.changerUnitesNourriture(evenements.premiereEffet);
        }
        if (evenements.deuxiemeEffetAttribut.equals("nourriture"))
        {
            rep.changerUnitesNourriture(evenements.deuxiemeEffet);
        }

        if (evenements.premiereEffetAttribut.equals("industrie"))
        {
            if (evenements.premiereEffet<0) {
                rep.reductionIndustrie(evenements.premiereEffet);
            }else {
                rep.augmentationIndustrie(evenements.premiereEffet);
            }
        }
        if (evenements.deuxiemeEffetAttribut.equals("industrie"))
        {
            if (evenements.deuxiemeEffet<0) {
                rep.reductionIndustrie(evenements.deuxiemeEffet);
            }else {
                rep.augmentationIndustrie(evenements.deuxiemeEffet);
            }
        }

        if (evenements.premiereEffetAttribut.equals("agriculture"))
        {
            if (evenements.premiereEffet<0) {
                rep.reductionAgriculture(evenements.premiereEffet);
            }else {
                rep.augmentationAgriculture(evenements.premiereEffet);
            }
        }
        if (evenements.deuxiemeEffetAttribut.equals("agriculture"))
        {
            if (evenements.deuxiemeEffet<0) {
                rep.reductionAgriculture(evenements.deuxiemeEffet);
            }else {
                rep.augmentationAgriculture(evenements.deuxiemeEffet);
            }
        }
    }


    private void MAJevenementEffet(Evenements evenement,String nomAttribut,String valeurAttribut)
    {
        switch (nomAttribut)
        {
            case "idReponse":
                evenement.setIdReponse(Integer.parseInt(valeurAttribut));
                break;
            case "nomFaction":
                evenement.setPremierNomFaction(valeurAttribut);
                break;
            case "attribut":
                evenement.setPremiereEffetAttribut(valeurAttribut);
                break;
            case "effet":
                evenement.setPremiereEffet(Integer.parseInt(valeurAttribut));
                break;
            case "nomFaction2":
                evenement.setDeuxiemeNomFaction(valeurAttribut);
                break;
            case "effet2":
                evenement.setDeuxiemeEffet(Integer.parseInt(valeurAttribut));
                break;
            case "attribut2":
                evenement.setDeuxiemeEffetAttribut(valeurAttribut);
                break;
        }
    }

    private void decrireAttribut(Evenements evenement)
    {
        String temp="";

        if (!evenement.premierNomFaction.equals("REPUBLIQUE"))
        {
            temp="%";
        }else{
            temp="";
        }

        String chaineTotal= evenement.getPremierNomFaction() +
                " " +
                evenement.getPremiereEffet() + " " +
                temp + " de " +
                evenement.getPremiereEffetAttribut() + " / " +
                evenement.getDeuxiemeNomFaction() +
                " " +
                evenement.getDeuxiemeEffet() + " ";

        if (!evenement.deuxiemeNomFaction.equals("REPUBLIQUE"))
        {
            temp="%";
        }else{
            temp="";
        }

        chaineTotal+= temp + " de "+evenement.getDeuxiemeEffetAttribut() + "\n";

        System.out.println(chaineTotal);
    }

}
