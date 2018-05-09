/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s.testers;


import eu.pedu.adv17s_fw.game_txt.IGSMFactory;
import eu.pedu.adv17s_fw.game_txt.IGame;
import eu.pedu.adv17s_fw.game_txt.IItem;
import eu.pedu.adv17s_fw.game_txt.IScenarioManager;

import eu.pedu.adv17s_fw.scenario.AScenarioManager;
import eu.pedu.adv17s_fw.scenario.Scenario;
import eu.pedu.adv17s_fw.scenario.ScenarioStep;
import eu.pedu.adv17s_fw.scenario.TypeOfScenario;
import eu.pedu.adv17s_fw.scenario.TypeOfStep;

import eu.pedu.adv17s_fw.test_util.game_txt_test.ScenarioSummary;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ASolutionTester;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ATestVisitor;
import eu.pedu.adv17s_fw.test_util.scenario_test.SMSummary;

import eu.pedu.adv17s_fw.utilities.FormatStrings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/*******************************************************************************
 * Instance třídy {@code T17s12_Tester} testují správné zapracování<br>
 * modifikace s kódem <b>17s01</b>, požadující upravit odevzdaný program<br>
 * následovně:<br>
 * <br>
 * Doplňte do hry příkaz nazvaný <b>_MAGIE_</b>
 * s následujícími vlastnostmi:<br>
 * <ul>
 *   <li>
 *     Při zadání příkazu a jeho parametrů nebude záležet<br>
 *     na velikosti písmen.<br>
 *     &nbsp;</li>
 *   <li>
 *     Při prvním zadání příkazu s parametrem <b>_KOUZLO_</b>
 *     se stane následující:
 *     <ul>
 *       <li>kapacita batohu se zvětší o 1,<br>
 *         &nbsp;</li>
 *       <li>v batohu se objeví předmět nazvaný <b>_KOUZLO_</b>,<br>
 *         &nbsp;</li>
 *       <li>hra po zadání tohoto příkazu odpoví:<br>
 *          <b>Získali jste kouzlo, které vám usnadní dosažení cíle.</b>
 *         &nbsp;</li>
 *     </ul>
 *     Nic jiného se na aktuálním stavu hry nezmění.<br>
 *     &nbsp;</li>
 *   <li>
 *     Zadá-li hráč příkaz <b>_MAGIE_</b> s jiným parametrem
 *                                                než <b>_KOUZLO_</b>,<br>
 *     nic se nestane a hra odpoví:<br>
 *     <b>Příkaz _MAGIE_ neznám.</b><br>
 *     &nbsp;</li>
 *   <li>
 *     Má-li již hráč v batohu předmět <b>_KOUZLO_</b> a zadá-li příkaz<br>
 *     <b>_MAGIE_ _KONEC_</b><br>
 *     přejde do cílového stavu, tj. do stavu shodného<br>
 *     se závěrečným krokem úspěšného scénáře.<br>
 *     S tímto krokem bude mít tedy nastaven
 *     <ul>
 *       <li>shodný prostor<br>
 *         &nbsp;</li>
 *       <li>se shodnými sousedy,<br>
 *         &nbsp;</li>
 *       <li>shodnými h-objekty v tomto prostoru,<br>
 *         &nbsp;</li>
 *       <li>shodnými h-objekty v batohu a<br>
 *         &nbsp;</li>
 *       <li>shodnou odpovědí hry na zadaný příkaz.</li>
 *     </ul>
 *     Jinými slovy:<br>
 *     závěrečné kroky testovacího a úspěšného scénáře budou shodné <br>
 *     s jediným rozdílem: závěrečným příkazem testovacího scénáře <br>
 *     bude příkaz<br>
 *     <b>_MAGIE_ _KONEC_</b>
 *     &nbsp;</li>
 * </ul>
 * Doplňte do správce scénářů obecný testovací scénář (tj. scénář typu <br>
 * {@link eu.pedu.adv17s_fw.scenario.TypeOfScenario#scGENERAL})
 *                          nazvaný <b>_T17s12_</b>, jenž bude obsahovat<br>
 * následující kroky:<br>
 * <ol>
 *   <li>
 *     Kkrok typu {@link TypeOfStep#tsSTART} sw startovacím krokem.<br>
 *     &nbsp;</li>
 *   <li>
 *     Krok typu {@link TypeOfStep#tsNON_STANDARD1} s příkazem<br>
 *     <b>_MAGIE_ _KONEC_</b><br>
 *     na nějž hra odpoví:<br>
 *     <b>Příkaz _MAGIE_ neznám.</b><br>
 *     (nebyl totiž ještě vyčarován předmět <b>KOUZLO</b>).<br>
 *     &nbsp;</li>
 *   <li>
 *     Krok typu {@link TypeOfStep#tsNON_STANDARD1} s příkazem<br>
 *     <b>_MAGIE_ _KOUZLO_</b><br>
 *     &nbsp;</li>
 *   <li>
 *     Kroky chybového scénáře počínaje krokem následujícím<br>
 *     za startovacím krokem a končící krokem typu
 *                                          {@link TypeOfStep#tsBAG_FULL},<br>
 *     při němž se hráč snaží přidat předmět do již zaplněného batohu.<br>
 *     Všechny kroky budou téměř stejné s odpovídajícími kroky chybového<br>
 *     scénáře s výjimkou toho, že v těchto krocích bude v batohu navíc<br>
 *     předmět <b>_KOUZLO_</b>.<br>
 *     &nbsp;</li>
 *   <li>
 *     Krok typu {@link TypeOfStep#tsNON_STANDARD1} s příkazem<br>
 *     <b>_MAGIE_ _KONEC_</b><br>
 *     po němž se hra převede do stavu shodného s posledním krokem<br>
 *     úspěšného scénáře (předmět <b>_KOUZLO_</b> již v batohu nebude).<br>
 *     &nbsp;</li>
 * </ol>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public class T17s12_Tester
     extends ASolutionTester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Identifikátor testovaného zadání. */
    public static final String TEST_ID = "17s12";

    /** Identifikátor testovaného zadání. */
    public static final String TEST_SCENARIO_NAME = "_T" + TEST_ID + "_";

    /** Seznam výsledků provedených testů. */
    private static final List<ScenarioSummary> INFOS = new ArrayList<>();

    /** Nově přidávaný příkaz. */
    public static final String ACTION = "_MAGIE_";

    /** Název klíčového parametru a současně h-objektu v batohu. */
    public static final String MAGIC_NAME = "_KOUZLO_";

    /** Druhý používaný parametr – ukončení hry. */
    public static final String ARG_END = "_KONEC_";

    /** Odpověď na špatně zadaný parametr. */
    public static final String ANSWER_WRONG_ARGUMENT =
           "Příkaz " + ACTION + " neznám.";

    /** Odpověď po úspěšném zadání příkazu. */
    public static final String ANSWER_MAGIC_REALIZED =
           "Získali jste kouzlo, které vám usnadní dosažení cíle.";

    /** Název předmětu v batohu. */
    public static final IItem MAGIC_ITEM =
        new IItem() {
            @Override public String getName()   { return MAGIC_NAME; }
            @Override public int    getWeight() { return 1; }
        };



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Poslední vytvořený tester. Nepředpokládá se paralelní použití,
     *  takže atribut a jeho získávací přístupová metody (getr)
     *  nejsou definovány jako vláknově bezpečné. */
    static T17s12_Tester lastTester;

    /** Tovární objekt schopný poskytnout klíčové třídy testované aplikace. */
    private static IGSMFactory orgFactory;

    /** Tovární objekt schopný poskytnout klíčové třídy testovací aplikace. */
    private static ADecoratingFactory
//                                      <T16w05_Manager,
//                                      T16w05_Game>
    newFactory;

    /** Testující, tj. dekorovaný správce scénářů. */
    private static ADecoratingScenarioManager newManager;
//
//    /** Přepravka s informacemi o světu hry získanými
//     *  na základě vyhodnocení scénářů a jejich kroků. */
//    private static SMSummary summary;

    /** Testovaná hra. */
    private static IGame newGame;

    /** Přidávaný scénář. */
    private static Scenario addedScenario;

    /** Poslední krok přidávaného scénáře. */
    private static ScenarioStep theLastStep;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí tovární objekt pomocné aplikace dekorující testovanou.
     *
     * @return Tovární objekt pomocné aplikace dekorující testovanou
     */
    public static
    ADecoratingFactory/*<T16w05_Manager,
                       T16w05_Game>*/ getNewFactory()
    {
        return newFactory;
    }


    /***************************************************************************
     * Vrátí tovární objekt testované aplikace.
     *
     * @return Tovární objekt testované aplikace
     */
    public static IGSMFactory getOrgFactory()
    {
        return orgFactory;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Definuje scénář, podle nějž je možno prověřit
     * splnění požadavků dané obhajoby.
     *
     * @param newManager Správce pro nějž se scénář vyrábí
     * @param oldManager  Správce úspěšného a chybového scénáře,
     *                          z nichž se vytvořený scénář odvodí
     * @return Vytvořený scénář
     */
    static Scenario createAddedScenarioFor(AScenarioManager newManager,
                                           AScenarioManager oldManager)
    {
        ScenarioStep[] steps  = prepareAddedScenarioStepsFrom(oldManager);
        Scenario       result = new Scenario(TEST_SCENARIO_NAME,
                                             TypeOfScenario.scGENERAL,
                                             newManager,
                                             steps);
        return result;
    }


    /***************************************************************************
     * Inicializuje atributy používané při testování.
     *
     * @param orgFactory Tovární objekt schopný poskytnout klíčové třídy
     */
    static void initializeFor(IGSMFactory orgFactory)
    {
        T17s12_Tester.orgFactory = orgFactory;
        T17s12_Tester.newFactory = new T16w05_Factory();

        T17s12_Tester.newManager = T16w05_Manager.getInstance();
        T17s12_Tester.newGame    = new T16w05_Game(orgFactory,
                                                            newFactory);
        checkAddedScenario();
    }


    /***************************************************************************
     * Připraví kroky doplňkového testovacího scénáře pro upravenou verzi hry
     * zadané prostřednictvím továrního objektu.
     *
     * @param manager Správce úspěšného a chybového scénáře,
     *                z nichž se vytvářené kroky odvodí
     * @return Kroky doplňujícího testovacího scénáře
     */
    public static
    ScenarioStep[] prepareAddedScenarioStepsFrom(AScenarioManager manager)
    {
        SMSummary        smSummary = manager.prepareSummary();
        ScenarioStep     startStep = new ScenarioStep(0, smSummary.startStep);
        ScenarioStep     endStep   = smSummary.endStep;

        List<ScenarioStep> steps = new ArrayList<>();
        steps.add(startStep);

        //Příkaz se špatným parametrem
        steps.add(new ScenarioStep(TypeOfStep.tsNON_STANDARD1,
            ACTION + " " + ARG_END,
            ANSWER_WRONG_ARGUMENT,
            startStep.place,
            startStep.neighbors,
            startStep.items,
            startStep.bag
        ));

        //Vyčarování (získání) předmětu _KOUZLO_
        steps.add(new ScenarioStep(TypeOfStep.tsNON_STANDARD1,
            ACTION + " " + MAGIC_NAME,
            ANSWER_MAGIC_REALIZED,
            startStep.place,
            startStep.neighbors,
            startStep.items,
            supplementBag(startStep.bag)
        ));

        addMistakeSteps(manager, steps);

        //Závěrečný krok ukončující daný běh hry
        steps.add(new ScenarioStep(TypeOfStep.tsNON_STANDARD1,
            ACTION + " " + ARG_END,
            endStep.message,
            endStep.place,
            endStep.neighbors,
            endStep.items,
            endStep.bag,
            true
        ));


        ScenarioStep[] result = steps.toArray(new ScenarioStep[steps.size()]);

        return result;
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

    /***************************************************************************
     * Přidá kroky z chybového scénáře zadaného správce scénářů
     * počínaje prvním krokem za startovacím krokem
     * a konče krokem, v němž se testuje pokus o přeplnění batohu,
     * tj. krokem typu {@link TypeOfStep#tsBAG_FULL}.
     *
     * @param manager Správce scénářů testované hry
     * @param steps   Doposud vytvořené kroky automaticky generovaného
     *                testovacího scénáře
     */
    static
    private void addMistakeSteps(AScenarioManager manager,
                                 List<ScenarioStep> steps)
    {
        Scenario mistakeScenario = manager.getMistakeScenario();
        Iterator<ScenarioStep>it = mistakeScenario.iterator();
        ScenarioStep scenarioStep;

        //Přeskoč úvodní nestartovací kroky a najdi startovací krok
        do {
            scenarioStep = it.next();
        } while (scenarioStep.typeOfStep != TypeOfStep.tsSTART);

        //Přidej kroky až do testu přeplnění batohu
        do {
            scenarioStep = it.next();
            ScenarioStep supplementedStep = new ScenarioStep(
                    scenarioStep.typeOfStep,
                    scenarioStep.command,
                    scenarioStep.message,
                    scenarioStep.place,
                    scenarioStep.neighbors,
                    scenarioStep.items,
                    supplementBag(scenarioStep.bag)
            );
            steps.add(supplementedStep);
        } while(scenarioStep.typeOfStep != TypeOfStep.tsBAG_FULL);
    }


    /***************************************************************************
     * Prověří, zda správce scénářů dodaný prověřovaným továrním objektem
     * definuje správný dodatečný scénář.
     * Metoda předpokládá, že jsou již nastaveny atributy
     * {@link #orgFactory} a {@link #newFactory}
     */
    private static
    void checkAddedScenario()
    {
        AScenarioManager orgManager  = orgFactory.getScenarioManager();
        Scenario         orgScenario = orgManager.getScenario(TEST_SCENARIO_NAME);
        Scenario         newScenario = newManager.getScenario(TEST_SCENARIO_NAME);
        Iterator<ScenarioStep> orgIt = orgScenario.iterator();
        Iterator<ScenarioStep> newIt = newScenario.iterator();

        System.out.println(FormatStrings.N_HASHES_N + FormatStrings.HASHES_N
                        + "Verifikace scénáře " + TEST_SCENARIO_NAME
                        + " pro test požadované modifikace"
                        +  FormatStrings.N_HASHES );
        for (;   newIt.hasNext();   ) {
            ScenarioStep orgStep = new ScenarioStep(0, orgIt.next());
            ScenarioStep newStep = new ScenarioStep(0, newIt.next());
            System.out.println("«" + orgStep.command + "»");
            if (!orgStep.equals(newStep)) {
                throw new RuntimeException(
                    "\nKrok přidaného scénáře neodpovídá požadavkům:\n"
                  + "\nPožadovaný:" + newStep
                  + "\nObdržený:"   + orgStep
                );
            }
            if (orgIt.hasNext() != newIt.hasNext()) {
                String msg = "\nPřidaný scénář má "
                           + (newIt.hasNext()  ?  "méně"  :  "více")
                           + " kroků, než je požadováno";
                throw new RuntimeException(msg);
            }
        }
        System.out.println(FormatStrings.HASHES_N
                        + "Scénář " + TEST_SCENARIO_NAME + " odpovídá požadavkům"
                        +  FormatStrings.N_HASHES_N + FormatStrings.HASHES_N);
    }


    /***************************************************************************
     * Doplní obsah pole s názvy h-objektů v batohu o název {@link #MAGIC_NAME}.
     *
     * @param bag Původní stav pole s názvy h-objektů v batohu
     * @return Pole s doplněným názvem
     */
    private static String[] supplementBag(String[] bag)
    {
        int BL = bag.length;
        String[] suplemented = new String[BL + 1];
        System.arraycopy(bag, 0, suplemented, 0, BL);
        suplemented[BL] = MAGIC_NAME;
        return suplemented;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří tester prověřující řešení zadání specifikovaného
     * v dokumentačním komentáři třídy.
     */
    public T17s12_Tester()
    {
        super(TEST_ID, "", Visitor16w05::new,
              IScenarioManager.HAPPY_SCENARIO_NAME,
              IScenarioManager.HAPPY_SCENARIO_NAME,
              IScenarioManager.MISTAKE_SCENARIO_NAME,
              TEST_SCENARIO_NAME, TEST_SCENARIO_NAME);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Otestuje práci zadanou prostřednictvím jejího továrního objektu
     * podle sady scénářů odpovídajících danému testu.
     * Odkaz na tovární objekt byl uložen ve statickém atributu
     * při inicializaci před vytvořením instance.
     */
    /*    @Override*/
    public void test()
    {
        createAddedScenarioFor(newManager, newManager);
        super.addScenarioName(TEST_SCENARIO_NAME);
        super.test(orgFactory);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

////////////////////////////////////////////////////////////////////////////////
//\N1C /////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
//%L+ CZ
     * Instance třídy {@code Nested_1_Class} představují ...
//%Lx EN
     * The {@code Nested_1_Class} class instances represent ...
//%L-
     *
     * @author  Rudolf PECINOVSKÝ
     * @version 0.00.0000 — 20yy-mm-dd
     */
    public static class T16w05_Factory
         extends ADecoratingFactory//<T16w05_Manager, T16w05_Game>
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
         */
        public T16w05_Factory()
        {
//            this(newFactory, T16w05_Manager  ::getInstance,
//                          T16w05_Game::getInstance);
            super(orgFactory, T16w05_Manager  ::getInstance,
                              T16w05_Game::getInstance);
        }



    //\IA== INSTANCE ABSTRACT METHODS ==========================================
    //\IG== INSTANCE GETTERS AND SETTERS =======================================
    //\IM== INSTANCE REMAINING NON-PRIVATE METHODS =============================
    //\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =============================



    //##########################################################################
    //\NT== NESTED DATA TYPES ==================================================

    }



////////////////////////////////////////////////////////////////////////////////
//\N1C /////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code T16w05_Manager} představují dekorující
     * správce scénářů používané interně v testeru {@link Tester_17s11}.
     * Tuto správce není možno definovat ja
     *
     * @author  Rudolf PECINOVSKÝ
     * @version 0.00.0000 — 20yy-mm-dd
     */
    public static class T16w05_Manager
         extends ADecoratingScenarioManager
    {
    //\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==========

        /** Jediná instance dané třídy. Není na počátku inicializovaná,
         *  bude ji inicializovat až metoda-tvůrce dekorujícího scénáře. */
        private final static T16w05_Manager SINGLETON =
                      new T16w05_Manager(getOrgFactory(), getNewFactory());



    //\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==========



    //##########################################################################
    //\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =====================
    //\CF== CLASS (STATIC) FACTORY METHODS =====================================

            /*******************************************************************
             *
             * @return
             */
            public static T16w05_Manager getInstance()
            {
                return SINGLETON;
            }



    //\CG== CLASS (STATIC) GETTERS AND SETTERS =================================
    //\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS =======================
    //\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS =======================



    //##########################################################################
    //\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===========
    //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===========



    //##########################################################################
    //\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===============================

            /*******************************************************************
             */
            private T16w05_Manager(IGSMFactory orgFactory,
                   ADecoratingFactory/*<T16w05_Manager,
                                      T16w05_Game> */decFactory)
            {
                super(orgFactory, decFactory);
                addBasicScenarios();
                T16w05_Manager me = this;
                Scenario scenario = createAddedScenarioFor(me, delegate);
                addScenario(scenario);
                seal();
    //            SINGLETON = this;
            }



    //\IA== INSTANCE ABSTRACT METHODS ==========================================
    //\IG== INSTANCE GETTERS AND SETTERS =======================================
    //\IM== INSTANCE REMAINING NON-PRIVATE METHODS =============================
    //\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =============================



    //##########################################################################
    //\NT== NESTED DATA TYPES ==================================================
    }



////////////////////////////////////////////////////////////////////////////////
//\N1C /////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
//%L+ CZ
     * Instance třídy {@code Nested_1_Class} představují ...
//%Lx EN
     * The {@code Nested_1_Class} class instances represent ...
//%L-
     *
     * @author  Rudolf PECINOVSKÝ
     * @version 0.00.0000 — 20yy-mm-dd
     */
    static class T16w05_Game
         extends ADecoratingGame
    {
    //\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==========
    //\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==========

        /** Jediná instance dané třídy. Není na počátku inicializovaná,
         *  bude ji inicializovat až metoda-tvůrce dekorujícího scénáře. */
        private static T16w05_Game SINGLETON;



    //##########################################################################
    //\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =====================
    //\CF== CLASS (STATIC) FACTORY METHODS =====================================

//%L+ CZ
        /***********************************************************************
         * Třídní tovární metoda nahrazující konstruktor -
         * vrátí odkaz na instanci jedináčka.
         *
         * @return Odkaz na instanci jedináčka
         */
//%Lx EN
        /***********************************************************************
         * The class newFactory method substituing a constructor -
 it returns the only instance of the class (its singleton).
         *
         * @return The only instance of the class (its singleton)
         */
//%L-
        public static T16w05_Game getInstance()
        {
            return SINGLETON;
        }



    //\CG== CLASS (STATIC) GETTERS AND SETTERS =================================
    //\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS =======================
    //\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS =======================



    //##########################################################################
    //\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===========
    //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===========



    //##########################################################################
    //\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===============================

        /***********************************************************************
         */
        T16w05_Game(IGSMFactory orgFactory,
               ADecoratingFactory/*<T16w05_Manager,
                                  T16w05_Game>*/ decFactory)
        {
            super(TEST_ID, orgFactory, decFactory);
            SINGLETON = this;
        }



    //\IA== INSTANCE ABSTRACT METHODS ==========================================
    //\IG== INSTANCE GETTERS AND SETTERS =======================================
    //\IM== INSTANCE REMAINING NON-PRIVATE METHODS =============================
    //\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =============================



    //##########################################################################
    //\NT== NESTED DATA TYPES ==================================================
    }



////////////////////////////////////////////////////////////////////////////////
//\N1C /////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code Visitor16w02} představují návštěvníky prověřující
     * splnění zadání definovaného v dokumentačním komentáři vnější třídy.
     */
    private static class Visitor16w05
                 extends ATestVisitor
    {
    //\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===============================

        /***********************************************************************
         * Vytvoří návštěvníka metod testujících fungování hry podle
         * modifikovaného zadání definovaného v konstantě {@link #DESCRIPTION}.
         *
         * @param myTest  Zadavatel požadující vyřešení zadání
         *                definovaného v konstantě {@link #DESCRIPTION}
         * @param factory Tovární objekt poskytující základní objekty
         *                prověřované aplikace
         */
        Visitor16w05(ASolutionTester myTest, IGSMFactory factory)
        {
            super(myTest, factory);
        }



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
        T17s12_Tester.initializeFor(
        new eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.GSMFactoryKoutecky()
            );
        T17s12_Tester tester = new T17s12_Tester();
        tester.test();
        System.exit(0);
    }
}