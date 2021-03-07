package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Scanner;

public class XmlReaderScenario {

    private int countId = 0;
    private ListeEvenements listeEvenements=new ListeEvenements();

    public void lireXMLevenement(ListeFaction listeFaction,Republique rep)
    {
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            SAXParser saxParser = spfactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler()
            {
                boolean question = false;
                boolean reponse = false;
                boolean idEvenement = false;
                final String baliseReponse = "reponse";
                final String baliseQuestion = "question";
                int idReponse = 0;
                String nomFaction="";
                String attribut="";
                int effet=0;
                /*cette méthode est invoquée à chaque fois que parser rencontre une balise ouvrante '<' */
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
                {
                        int longueur = attributes.getLength();
                        for (int i = 0; i < longueur; i++)
                        {
                            String nomAttribut = attributes.getQName(i);
                            String valeurAttribut = attributes.getValue(i);
                            if(nomAttribut.equals("id"))
                            {
                                idEvenement = Integer.parseInt(valeurAttribut) == countId;
                                countId++;
                            }

                            if(nomAttribut.equals("idReponse"))
                            {
                                idReponse = Integer.parseInt(valeurAttribut);
                            }

                            if(idEvenement) {


                                if(nomAttribut.equals("NomFaction") || nomAttribut.equals("NomFaction2"))
                                {
                                    nomFaction=valeurAttribut;
                                }
                                if(nomAttribut.equals("attribut") || nomAttribut.equals("attribut2"))
                                {
                                    attribut=valeurAttribut;
                                }
                                if(nomAttribut.equals("effet") || nomAttribut.equals("effet2"))
                                {
                                    effet=Integer.parseInt(valeurAttribut);
                                    listeEvenements.ajouterEvenement(new EvenementsScenario(idReponse,nomFaction,attribut,effet));
                                }
                            }
                        }

                        if(idEvenement)
                        {
                            if (qName.equalsIgnoreCase(baliseQuestion)) { question = true; }
                            if (qName.equalsIgnoreCase(baliseReponse)) { reponse = true; }
                        }


                }


                public void endElement(String uri, String localName, String qName) throws SAXException
                {


                        if (qName.equalsIgnoreCase(baliseQuestion))
                        {
                            question = false;
                        }

                        if (qName.equalsIgnoreCase(baliseReponse))
                        {
                            reponse = false;
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

                            listeEvenements.decrireAttribut(idReponse);
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
        listeEvenements.actionEvenementChoisi(reponseJoueur,listeFaction,rep);
    }
}
