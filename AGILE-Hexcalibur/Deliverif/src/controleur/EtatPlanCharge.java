/*
 * Projet Deliverif
 *
 * Hexanome n° 4102
 *
 * Projet développé dans le cadre du cours "Conception Orientée Objet
 * et développement logiciel AGILE".
 */
package controleur;

import deliverif.Deliverif;
import java.io.IOException;
import modele.GestionLivraison;
import org.xml.sax.SAXException;

/** Etat dans lequel se trouve l'application après le chargement du plan
 *  La seule action possible depuis cet état est le chargement d'une demande de 
 *  livraisons ou le chargement d'un autre plan
 * 
 * @author Hex'Calibur
 */

public class EtatPlanCharge extends EtatDefaut{

    /**
     * Constructeur EtatPlanCharge
     */
    public EtatPlanCharge() {
    }
    
    /**Cette méthode délègue la chargement des livraisons au modèle
     * Si le chargement s'est bien passé on passe dans l'EtatLivraisonsChargees   
     * @param controleur
     * @param gestionLivraison
     * @param fichier
     * @param fenetre
     * @see GestionLivraison
     * @see EtatLivraisonsChargees
     */
    @Override
    public void boutonChargeLivraisons (Controleur controleur, GestionLivraison gestionLivraison, String fichier, Deliverif fenetre) {
        try{
            gestionLivraison.chargerDemandeLivraison(fichier);
            controleur.setEtatCourant(Controleur.ETAT_LIVRAISONS_CHARGEES);
            fenetre.estDemandeLivraisonChargee("SUCCES");
        } catch (SAXException e){
            fenetre.estDemandeLivraisonChargee(e.getMessage());
        } catch (IOException e) {
            fenetre.estDemandeLivraisonChargee(e.getMessage());
        } catch (Exception e) {
            fenetre.estDemandeLivraisonChargee(e.getMessage());
        }
    }
    
    /**Cette méthode délègue la chargement du plan au modèle
      *Si le chargement s'est bien passé on passe dans l'EtatPlanCharge 
      *@param controleur
      *@param gestionLivraison
      *@param fichier
      *@param fenetre
      *@see GestionLivraison
      *@see EtatPlanCharge
     */
    @Override
    public void boutonChargePlan (Controleur controleur, GestionLivraison gestionLivraison, String fichier, Deliverif fenetre){
        try{
            gestionLivraison.chargerPlan(fichier);
            fenetre.estPlanCharge("SUCCES");
        } catch (SAXException e) {
            fenetre.estPlanCharge(e.getMessage());
            
        } catch (IOException e) {
            fenetre.estPlanCharge(e.getMessage());
            
        } catch (Exception e) {
            fenetre.estPlanCharge(e.getMessage());
        }
    }
    
    /**Pas de sélection livraison sur le plan possible dans cet état
     * @param controleur
     * @param gestionLivraison
     * @param fenetre
     * @param latitude
     * @param longitude 
     */
    @Override
    public void clicGauche(Controleur controleur, GestionLivraison gestionLivraison, Deliverif fenetre, double latitude, double longitude) {
    }
}
