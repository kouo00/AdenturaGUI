/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s.testers;

import eu.pedu.adv17s_fw.game_txt.IGSMFactory;
import eu.pedu.adv17s_fw.game_txt.IItem;
import eu.pedu.adv17s_fw.game_txt.IWorld;
import eu.pedu.adv17s_fw.scenario.AScenarioManager;

import eu.pedu.adv17s_fw.scenario.Scenario;
import eu.pedu.adv17s_fw.scenario.ScenarioStep;
import eu.pedu.adv17s_fw.scenario.TypeOfStep;

import eu.pedu.adv17s_fw.test_util.common.TestException;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ScenarioSummary;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ATestVisitor;
import eu.pedu.adv17s_fw.test_util.game_txt_test.ASolutionTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/*******************************************************************************
 * Instance třídy {@code T17s03_Tester} testují správné zapracování<br>
 * modifikace s kódem <b>17s03</b>, požadující upravit odevzdaný program<br>
 * následovně:<br>
 * <br>
 * Na konec zprávy vypisované jako odpověď hry na zadání příkazu<br>
 * přidat řádek s textem:<br><br>
 * <b><tt>
 * §Not seen: [Name1, Name2, Name3, ..., NameN]
 * </tt></b><br>
 * <br>
 * Kde v hranatých závorkách bude abecedně seřazený seznam<br>
 * čárkami oddělených názvů h-objektů,<br>
 * které hráč během aktuální hry ještě <b>neviděl</b>.<br>
 * Názvy <b>Name1</b>, <b>Name2</b> atd. ve výše  uvedeném textu<br>
 * zastupují názvy h-objektů vyskytujících se v testované hře.<br>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public class T17s03_Tester
     extends ASolutionTester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Identifikátor testovaného zadání. */
    public static final String TEST_ID = "17s03";

    /** Požadovaný tvar začátku posledního řádku. */
    protected final static String START = "Not seen: [";

    /** Požadovaný oddělovač názvů. */
    protected final static String SEPARATOR = ", ";

    /** Požadovaný ukončovací řetězec. */
    protected final static String END = "]";

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
     * Vytvoří tester prověřující řešení zadání specifikovaného
     * v dokumentačním komentáři třídy.
     */
    public T17s03_Tester()
    {
        super(TEST_ID, "", Visitor17s03::new);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

////////////////////////////////////////////////////////////////////////////////
//\N1C /////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code Visitor17s03} představují návštěvníky prověřující
     * splnění zadání definovaného v dokumentačním komentáři vnější třídy.
     */
    private static class Visitor17s03
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

        /** Množina doposud nezahlédnutých h-objektů */
        public final List<String> notSeen = new ArrayList<>();



    //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===========



    //##########################################################################
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
        Visitor17s03(ASolutionTester myTest, IGSMFactory factory)
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

        /***********************************************************************
         * Akce, která se má provést po spuštění hry před testem prvního kroku:
         * zapamatuje si názvy všech h-objektů hry převedené na malá písmena.
         *
         * @param scenario  Scénář definující proces testování
         */
        @Override
        public void afterGameStart(Scenario scenario)
        {
            System.out.println("=== afterGameStart ===");
            notSeen.clear();
            IWorld world = game.getWorld();
            List<String> inPlaces =
                world.getAllPlaces().stream()
                     .flatMap(place -> place.getItems().stream())
                     .map(item -> item.getName())
                     .collect(Collectors.toList());
            System.out.println("inPlaces=" + inPlaces);
            AScenarioManager manager = gsmFactory.getScenarioManager();
            List<String> inScenarios = new ArrayList<>();
                manager.stream()
                       .flatMap(sc -> sc.stream())
                       .flatMap((ScenarioStep step) -> {
                            List<String> items = new ArrayList<>();
                            items.addAll(Arrays.asList(step.bag));
                            items.addAll(Arrays.asList(step.items));
                            return items.stream();
                        })
                       .collect(Collectors.toList());
                Stream.concat(inPlaces.stream(), inPlaces.stream())
                      .map(itemName -> itemName.toLowerCase())
                      .distinct()
                      .sorted()
                      .forEach(name -> notSeen.add(name));
        }


        /***********************************************************************
         * Akce, která se má provést po testu aktuálního kroku:
         * odebere z kolekce h-objektů v aktuálním prostoru a batohu
         * a zkontroluje poslední řádek zprávy.
         *
         * @param step      Aktuálně testovaný krok scénáře
         * @param message   Zpráva vrácená hrou v daném kroku
         */
        @Override
        public void afterStepTest(ScenarioStep step, String message)
        {
            Collection<? extends IItem> inBag   = game.getBag().getItems();
            Collection<? extends IItem> inPlace = game.getWorld()
                                                      .getCurrentPlace()
                                                      .getItems();
            List<IItem> seenItems = new ArrayList<>();
            seenItems.addAll(inBag);
            seenItems.addAll(inPlace);
            for (IItem seenItem : seenItems) {
                notSeen.remove(seenItem.getName().toLowerCase());
            }

            //Najde a zkontroluje poslední řádek
            String[] parts  = message.split("§");
            int      number = parts.length;
            if (number < 2) {
                throw new TestException(
                        "\nChybí požadovaný závěrečný řádek");
            }
            if (number > 2) {
                throw new TestException(
                        "\nNečekaný znak § v závěrečné zprávě");
            }
            String line = parts[1];
            if (! line.startsWith(START)) {
                throw new TestException(
                        "\nZávěrečný řádek nemá požadovaný začátek");
            }
            line = line.trim();
            if (! line.endsWith(END)) {
                throw new TestException(
                        "\nZávěrečný řádek nemá požadovaný konec");
            }
            if (step.typeOfStep == TypeOfStep.tsNOT_START) {
                return;
            }
            String answer = line.substring(START.length(),
                                           line.length() - END.length()).trim();
            String[] nameArr;
            if (answer.isEmpty()) {
                nameArr = new String[0];
            }
            else {
                nameArr = answer.split(SEPARATOR);
            }
            Set<String> names = new HashSet<>(Arrays.asList(nameArr));

            if (notSeen.size() != names.size()) {
                throw new TestException(
                    "\nNesouhlasí počet uvedených nezahlédnutých h-objektů");
            }
            for (Iterator<String> it = names.iterator(); it.hasNext();) {
                String name   = it.next();
                String tested = name.toLowerCase();
                if (notSeen.contains(tested)) {
                    it.remove();
                }
                else {
                    throw new TestException(
                        "\nChybně uvedený nezahlédnutý h-objekt: " + name);
                }
            }
        }


        /***********************************************************************
         * Akce, která se má provést po testu posledního kroku.
         *
         * @param summary Přepravka s kompletními informacemi o průběhu hry
         */
        @Override
        public void afterGameEnd(Throwable exception, String verboseMessage)
        {
            super.afterGameEnd(exception, verboseMessage);
        }



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
        T17s03_Tester tester = new T17s03_Tester();
        tester.test(
//            AScenarioManager.getDefaultFactory()
            //new VašeTovárníTřída()
            new eu.pedu.adv17s._4_1100.kouo00_koutecky.GSMFactoryKoutecky()
            
        );
        System.exit(0);
    }
}
