/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahirusan
 */
public class ControleurTest {
    
    public ControleurTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of boutonChargePlan method, of class Controleur.
     */
    @Test
    public void testBoutonChargePlan() {
        System.out.println("boutonChargePlan");
        modele.GestionLivraison gestion = new modele.GestionLivraison();
        Controleur instance = new Controleur(gestion);
        
        String fichier = "fichier existant et bien formé";
        instance.boutonChargePlan(fichier);
        assertEquals(Controleur.ETAT_PLAN_CHARGE, Controleur.etatCourant);
        
        String fichier2 = "fichier non existant ou mal formé";
        instance.boutonChargePlan(fichier2);
        assertEquals(Controleur.ETAT_INIT, Controleur.etatCourant);
    }

    /**
     * Test of boutonChargeLivraisons method, of class Controleur.
     */
    @Test
    public void testBoutonChargeLivraisons() {
        System.out.println("boutonChargeLivraison");
        modele.GestionLivraison gestion = new modele.GestionLivraison();
        Controleur instance = new Controleur(gestion);
        
        String fichierPlan = "fichier existant et bien formé";
        instance.boutonChargePlan(fichierPlan);
        assertEquals(Controleur.ETAT_PLAN_CHARGE, Controleur.etatCourant);
        
        String fichier = "fichier existant et bien formé";
        instance.boutonChargeLivraisons(fichier);
        assertEquals(Controleur.ETAT_LIVRAISONS_CHARGE, Controleur.etatCourant);
        
        String fichier2 = "fichier non existant ou mal formé";
        instance.boutonChargeLivraisons(fichier2);
        assertEquals(Controleur.ETAT_PLAN_CHARGE, Controleur.etatCourant);
    }

    /**
     * Test of boutonCalculerTournees method, of class Controleur.
     */
    @Test
    public void testBoutonCalculerTournees() {
        System.out.println("boutonCalculerTournees");
        modele.GestionLivraison gestion = new modele.GestionLivraison();
        Controleur instance = new Controleur(gestion);
        
        String fichierPlan = "fichier existant et bien formé";
        instance.boutonChargePlan(fichierPlan);
        assertEquals(Controleur.ETAT_PLAN_CHARGE, Controleur.etatCourant);
        
        String fichierLivraison = "fichier existant et bien formé";
        instance.boutonChargeLivraisons(fichierLivraison);
        assertEquals(Controleur.ETAT_LIVRAISONS_CHARGE, Controleur.etatCourant);
        
        int nbLivreurs=3;
        instance.boutonCalculerTournees(nbLivreurs);
        assertEquals(Controleur.ETAT_CALCUL_TOURNEES, Controleur.etatCourant);
        
    }

    
    
}
