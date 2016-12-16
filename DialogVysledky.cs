using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Fei.Knihovna;

namespace ZavodyKraliku
{
    /// <summary>
    /// Dialog pro vypis vysledků turnajů
    /// </summary>
    public partial class DialogVysledky : Form
    {

        /// <summary>
        /// Zpřístupňuje a nastavuje výsledky turnajů.
        /// </summary>
        public Zapasy ZapasyKraliku { get; set; }

        /// <summary>
        /// Inicializuje objekty typu Zapasy a inicializuje komponenty.
        /// </summary>
        public DialogVysledky()
        {
            InitializeComponent();
            ZapasyKraliku = new Zapasy();
        }

        /// <summary>
        /// Metoda nastaví vysledky do listBoxu.
        /// </summary>
        public void NastavPrehledTurnaju()
        {
            groupBoxVysledky.Text = "Počet odehraných turnaju:  " + ZapasyKraliku.PocetOdehranychTurnaju +
                "     Počet odehraných zapasů:  " + ZapasyKraliku.PocetOdehranychZapasu;

            for (int i = 0; i < ZapasyKraliku.Turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)ZapasyKraliku.Turnaje.ZpristupniPrvek(i);
                string carky = new String('-', 61);
                string nazevTurnaje = carky + " " + zpristupnenyTurnaj.Nazev + " " + carky;
                listBoxVysledky.Items.Add(nazevTurnaje);

                if (zpristupnenyTurnaj.Odehrano)
                {
                    listBoxVysledky.Items.Add("");
                    listBoxVysledky.Items.Add("Pořadí\tJméno\t\tOdehrané zápasy\t   Výhry | Prohry\tVýhry doma\tVýhry venku\t" +
                      "Prohry doma\tProhry venku");
                    listBoxVysledky.Items.Add("");
                    int poradi = 1;

                    for (int j = zpristupnenyTurnaj.Ucastnici.PocetPrvku - 1; j >= 0; j--)
                    {
                        Kralik zpristupnenyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j);
                        string tabulator = "";
                        if (zpristupnenyKralik.Jmeno.Length <= 9)
                        {
                            tabulator = "\t";
                        }

                        int celkemOdehrano = zpristupnenyKralik.PocetVyherVenku + zpristupnenyKralik.PocetVyherDoma + zpristupnenyKralik.PocetProherVenku + zpristupnenyKralik.PocetProherDoma;
                        string mezera = new String(' ', 12);
                        string vysledekKralika = poradi + ". místo\t" + zpristupnenyKralik.Jmeno + tabulator + "\t" + mezera
                            + celkemOdehrano + "\t\t" + mezera + (zpristupnenyKralik.PocetVyherDoma + zpristupnenyKralik.PocetVyherVenku)
                            + " | " + (zpristupnenyKralik.PocetProherDoma + zpristupnenyKralik.PocetProherVenku)
                            + "\t" + mezera + zpristupnenyKralik.PocetVyherDoma + "\t\t" + mezera + zpristupnenyKralik.PocetVyherVenku
                            + "\t\t" + mezera + zpristupnenyKralik.PocetProherDoma + "\t\t" + mezera + zpristupnenyKralik.PocetProherVenku;
                        listBoxVysledky.Items.Add(vysledekKralika);
                        poradi++;
                    }
                }
                else
                {
                    string neodehranyTurnaj = "Turnaj nebyl odehrán pro nedostatek účastníků turnaje";
                    listBoxVysledky.Items.Add(neodehranyTurnaj);
                }

                listBoxVysledky.Items.Add("");
            }
        }
    }
}
