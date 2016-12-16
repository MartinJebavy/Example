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
using System.IO;

namespace ZavodyKraliku
{
    /// <summary>
    /// Aplikace závodění králíků.
    /// </summary>
    public partial class Aplikace : Form
    {
        /// <summary>
        /// Kolekce turnajů a jejich učastníkami.
        /// </summary>
        private Kolekce turnaje;

        /// <summary>
        /// Kolekce králíků, kteří nebyli zařazeni do žádného turnaje.
        /// </summary>
        private Kolekce volniKralici;

        /// <summary>
        /// Nastavuje a zpřístupňuje zápasy králíků.
        /// </summary>
        public Zapasy ZapasyKraliku { get; set; }

        /// <summary>
        /// Inicializuje objekty typu Kolekce, Zapasy a inicializuje komponenty.
        /// </summary>
        public Aplikace()
        {
            InitializeComponent();
            turnaje = new Kolekce();
            volniKralici = new Kolekce();
            ZapasyKraliku = new Zapasy();
        }

        /// <summary>
        /// Metoda otevírá nový dialog pro správu králíků.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuKralici_Click(object sender, EventArgs e)
        {
            using (DialogKralici dlg = new DialogKralici())
            {
                dlg.VolniKralici = volniKralici;
                dlg.Turnaje = turnaje;
                dlg.NastavListBoxKralici();

                dlg.ShowDialog();

                volniKralici = dlg.VolniKralici;

                NastavListBoxy();

            }
        }

        /// <summary>
        /// Metoda nastavuje listBox králíků pro daný turnaj.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listBoxTurnaje_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBoxTurnaje.SelectedIndex != -1)
            {
                listBoxKraliciTurnaj.Items.Clear();
                Turnaj zpristupnenyTurnaj = (Turnaj)turnaje.ZpristupniPrvek(listBoxTurnaje.SelectedIndex);
                for (int i = 0; i < zpristupnenyTurnaj.Ucastnici.PocetPrvku; i++)
                {
                    listBoxKraliciTurnaj.Items.Add(((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(i)).Jmeno);
                }
            }
        }

        /// <summary>
        /// Metoda ukončuje aplikaci.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuItemUkoncit_Click(object sender, EventArgs e)
        {
            Close();
        }

        /// <summary>
        /// Metoda otevírá nový dialog pro správu turnajů.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuTurnaje_Click(object sender, EventArgs e)
        {
            using (DialogTurnaje dlg = new DialogTurnaje())
            {
                dlg.VolniKralici = volniKralici;
                dlg.Turnaje = turnaje;
                dlg.NastavListBoxTurnaje();

                dlg.ShowDialog();

                volniKralici = dlg.VolniKralici;

                NastavListBoxy();

            }
        }

        /// <summary>
        /// Metoda odehraje všechny turnaje.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonOdehrat_Click(object sender, EventArgs e)
        {
            ZapasyKraliku.Turnaje = turnaje;
            ZapasyKraliku.PripravitTurnaje();
            ZapasyKraliku.OdehratTurnaje();
            toolStripMenuItemVysledky.Enabled = true;
            toolStripMenuItemRestartovatTurnaje.Enabled = true;
            buttonOdehrat.Enabled = false;
            MessageBox.Show("Turnaje byly odehrány", "Odehráno",
                                    MessageBoxButtons.OK, MessageBoxIcon.Information);

        }

        /// <summary>
        /// Metoda otevírá nový dialog s výsledkami jednotlivých králíků.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuItemVysledky_Click(object sender, EventArgs e)
        {
            using (DialogVysledky dlg = new DialogVysledky())
            {
                dlg.ZapasyKraliku = ZapasyKraliku;
                dlg.NastavPrehledTurnaju();
                dlg.ShowDialog();
                NastavListBoxy();

            }
        }

        /// <summary>
        /// Metoda uloží soupisku všech turnajů a králiků do souboru.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuUlozit_Click(object sender, EventArgs e)
        {
            zapisDoSouboru<Kolekce>("TurnajeSoupiska.bin", turnaje);
            zapisDoSouboru<Kolekce>("VolniKralici.bin", volniKralici);

            MessageBox.Show("Soupiska turnajů, králíku a volných králíků byla uložena.", "Uloženo",
                                    MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        /// <summary>
        /// Metoda načte soupisku všech turnajů a králiků ze souboru.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuNacist_Click(object sender, EventArgs e)
        {
            turnaje = cteniZeSouboru<Kolekce>("TurnajeSoupiska.bin");
            volniKralici = cteniZeSouboru<Kolekce>("VolniKralici.bin");
            NastavListBoxy();
            MessageBox.Show("Soupiska turnajů, králíku a volných králíků byla načtena.", "Načteno",
                                     MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        /// <summary>
        /// Metoda restartuje turnaje a zachová účastníky.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuItemZachovatUcastniky_Click(object sender, EventArgs e)
        {
            ZapasyKraliku = new Zapasy();
            toolStripMenuItemVysledky.Enabled = false;
            toolStripMenuItemRestartovatTurnaje.Enabled = false;
            buttonOdehrat.Enabled = true;

        }

        /// <summary>
        /// Metoda restartuje turnaje a vyřadí učastníky z turnajů mezi volné.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripMenuItemVymazatUcasniky_Click(object sender, EventArgs e)
        {
            for (int i = 0; i < turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)turnaje.ZpristupniPrvek(i);
                for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                {
                    Kralik zristupnenyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j);
                    volniKralici.Pridej(zristupnenyKralik);
                                     
                }
                zpristupnenyTurnaj.Ucastnici.VymazVse();
            }
            ZapasyKraliku = new Zapasy();
            NastavListBoxy();
            toolStripMenuItemVysledky.Enabled = false;
            toolStripMenuItemRestartovatTurnaje.Enabled = false;

        }

        /// <summary>
        /// Privátní metoda nastavuje jednotlivé ListBoxy (turnaje, hráči ve vybraném turnaji, seznam volných Králiků)
        /// </summary>
        private void NastavListBoxy()
        {
            listBoxTurnaje.Items.Clear();
            listBoxKraliciTurnaj.Items.Clear();
            listBoxVolniKralici.Items.Clear();

            buttonOdehrat.Enabled = false;

            for (int i = 0; i < turnaje.PocetPrvku; i++)
            {
                listBoxTurnaje.Items.Add(((Turnaj)turnaje.ZpristupniPrvek(i)).Nazev);
            }

            for (int i = 0; i < volniKralici.PocetPrvku; i++)
            {
                listBoxVolniKralici.Items.Add(((Kralik)volniKralici.ZpristupniPrvek(i)).Jmeno);
            }

            if (turnaje.PocetPrvku != 0)
            {
                if (!toolStripMenuItemVysledky.Enabled)
                {
                    buttonOdehrat.Enabled = true;
                }

            }
        }
       /// <summary>
       /// Privatní metoda pro uložení objektu.
       /// </summary>
       /// <typeparam name="T">Typ objektu</typeparam>
       /// <param name="filePath">Cilový soubor</param>
       /// <param name="objectToWrite">Zapisovaný objekt</param>
       /// <param name="append">Přidání nakonec</param>
        private static void zapisDoSouboru<T>(string filePath, T objectToWrite, bool append = false)
        {
            using (Stream stream = File.Open(filePath, append ? FileMode.Append : FileMode.Create))
            {
                var binaryFormatter = new System.Runtime.Serialization.Formatters.Binary.BinaryFormatter();
                binaryFormatter.Serialize(stream, objectToWrite);
            }
        }

        /// <summary>
        /// Privátní metoda pro načtení objektu.
        /// </summary>
        /// <typeparam name="T">Typ objektu</typeparam>
        /// <param name="filePath">Zdrojový soubor</param>
        /// <returns></returns>
        private static T cteniZeSouboru<T>(string filePath)
        {
            using (Stream stream = File.Open(filePath, FileMode.Open))
            {
                var binaryFormatter = new System.Runtime.Serialization.Formatters.Binary.BinaryFormatter();
                return (T)binaryFormatter.Deserialize(stream);
            }
        }
    }
}
