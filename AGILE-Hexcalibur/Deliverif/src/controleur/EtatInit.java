/*
 * Projet Deliverif
 *
 * Hexanome n° 41
 *
 * Projet développé dans le cadre du cours "Conception Orientée Objet
 * et développement logiciel AGILE".
 */
package controleur;

/** Etat dans lequel l'application se trouve à son ouverture
 *  Possibilité de charger un plan
 *
 * @author Hex'Calibur
 */

public class EtatInit extends EtatDefaut{
    
    /**
     * Constructeur EtatInit
     */
    public EtatInit (){}
    
    /**  Cette méthode délègue la chargement du plan au modèle
      *  Si le chargement s'est bien passé on passe dans 
      *  l'EtatPlanCharge 
      *  @param gestionLivraison
      *  @param fichier
     * @param fenetre
      *  @see modele.GestionLivraison
      *  @see EtatPlanCharge
     */
    @Override
    public void chargePlan (modele.outils.GestionLivraison gestionLivraison, String fichier, deliverif.Deliverif fenetre){
        int cre = gestionLivraison.chargerVille(fichier);
        if(cre==1){
            Controleur.etatCourant = Controleur.ETAT_PLAN_CHARGE;
        }
        fenetre.estPlanCharge(cre);
    }
}
