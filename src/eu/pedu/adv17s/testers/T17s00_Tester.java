/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s.testers;

import eu.pedu.adv17s_fw.game_txt.IGSMFactory;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ScenarioSummary;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ATestVisitor;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ASolutionTester;

import java.util.ArrayList;
import java.util.List;



/*******************************************************************************
 * Instance třídy {@code T17s00_Tester} testují hru bez modifikací.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public class T17s00_Tester
     extends ASolutionTester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Identifikátor testovaného zadání. */
    public static final String TEST_ID = "17s00";

    /** Popis požadované modifikace. */
    public static final String DESCRIPTION =
      "Hra podle původního zadání bez modifikací";

    /** Seznam výsledků provedených testů. */
    private static final List<ScenarioSummary> INFOS = new ArrayList<>();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří tester prověřující řešení základního zadání.
     */
    public T17s00_Tester()
    {
        super(TEST_ID, DESCRIPTION, Visitor16w00::new);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Instance třídy {@code Visitor} představují návštěvníky prověřující
     * splnění základního zadání semestrální práce.
     */
    private static class Visitor16w00
                 extends ATestVisitor
    {
    //\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==========
    //\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==========



    //##########################################################################
    //\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =====================
    //\CF== CLASS (STATIC) FACTORY METHODS =====================================
    //\CG== CLASS (STATIC) GETTERS AND SETTERS =================================
    //\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS =======================
    //\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS =======================



    //##########################################################################
    //\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===========
    //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===========



    //##########################################################################
    //\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===============================

        /***********************************************************************
         * Vytvoří návštěvníka pro prověření základní funkčnosti hry
         * poskytnuté zadaným továrním objektem.
         *
         * @param myTest  Zadavatel požadující vyřešení základního zadání
         * @param factory Tovární objekt poskytující základní objekty
         *                prověřované aplikace
         */
        Visitor16w00(ASolutionTester myTest, IGSMFactory factory)
        {
            super(myTest, factory);
        }



    //\IA== INSTANCE ABSTRACT METHODS ==========================================
    //\IG== INSTANCE GETTERS AND SETTERS =======================================

        /***********************************************************************
         * Vrátí sdružený tester, jehož zadání prověřuje.
         *
         * @return Sdružený tester
         */
        @Override
        public ASolutionTester getTester()
        {
            return myTest;
        }



    //\IM== INSTANCE REMAINING NON-PRIVATE METHODS =============================
    //\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =============================



    //##########################################################################
    //\NT== NESTED DATA TYPES ==================================================
    }



//##############################################################################
//\MM== MAIN METHOD ============================================================

    /***************************************************************************
     * Metoda spouštějící celou aplikaci.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        T17s00_Tester tester = new T17s00_Tester();
        tester.test(
//             AScenarioManager.getDefaultFactory()
//           new
//           //VašeTovárníTřída
//           eu.pedu.adv17s._#_####.login_prijmeni.TovárníTřída
            new eu.pedu.adv17s._4_1100.
                kouo00_koutecky.GSMFactoryKoutecky

            ()
        );
        System.exit(0);
    }
}
