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
    /// Dialog pro správu králíků.
    /// </summary>
    public partial class DialogKralici : Form
    {

        /// <summary>
        /// Kolekce králíků, kteří nebyli zařazeni do žádného turnaje.
        /// </summary>
        public Kolekce VolniKralici { get; set; }

        /// <summary>
        /// Kolekce turnajů a jejich učastníkami.
        /// </summary>
        public Kolekce Turnaje { get; set; }

        /// <summary>
        /// Event pro uchovávání změn.
        /// </summary>
        private event PocetZmenenEventHandler pocetZmenen;

        /// <summary>
        /// Inicializuje objekty typu Kolekce a inicializuje komponenty.
        /// </summary>
        public DialogKralici()
        {
            InitializeComponent();
            VolniKralici = new Kolekce();
            Turnaje = new Kolekce();
            if (radioButtonAno.Checked)
            {
                pocetZmenen += new PocetZmenenEventHandler(volniKralíci_PocetZmenen);
                listBoxLogovani.Items.Add("Logování zapnuto");
                listBoxLogovani.Items.Add("----------------");
            }


        }

        /// <summary>
        /// Vytvoří nového králika s jedinečným jménem v cele aplikaci. V případě duplicity je vyhozeno chybové okno.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonPridat_Click(object sender, EventArgs e)
        {
            if (textBoxJmeno.Text != "" && textBoxJmeno.Text.Length >= 4)
            {
                string jmeno = char.ToUpper(textBoxJmeno.Text[0]) + textBoxJmeno.Text.Substring(1);

                if (KontrolaDuplicity(jmeno) == 1)
                {
                    VolniKralici.Pridej(new Kralik(jmeno));
                    listBoxVolniKralici.Items.Add(jmeno);
                    listBoxVsichniKralíci.Items.Add(jmeno);
                    MessageBox.Show("Králík " + jmeno + " byl vytvořen a umístěn mezi volné.", "Králík Vytvořen",
                                    MessageBoxButtons.OK, MessageBoxIcon.Information);
                    OnPocetZmenen(new PocetEventArgs(listBoxVolniKralici.Items.Count - 1));
                }
            }
            else if (textBoxJmeno.Text.Length < 4)
            {
                MessageBox.Show("Minimální délka jména musí být 4 znaky!", "Chyba délky",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);

            }
        }

        /// <summary>
        /// Metoda nastavuje TextBox podle vybraného itemu v ListBoxu.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listBoxVolniKralici_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBoxVolniKralici.SelectedIndex != -1)
            {
                textBoxJmeno.Text = ((Kralik)VolniKralici.ZpristupniPrvek(listBoxVolniKralici.SelectedIndex)).Jmeno;
                buttonUpravit.Enabled = true;
                buttonSmazat.Enabled = true;
            }
        }

        /// <summary>
        /// Metoda upravuje jméno vybraného králíka.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonUpravit_Click(object sender, EventArgs e)
        {
            if (textBoxJmeno.Text != "" && textBoxJmeno.Text.Length >= 4)
            {
                string jmeno = char.ToUpper(textBoxJmeno.Text[0]) + textBoxJmeno.Text.Substring(1);

                if (KontrolaDuplicity(jmeno) == 1)
                {
                    string puvodniJmeno = ((Kralik)VolniKralici.ZpristupniPrvek(listBoxVolniKralici.SelectedIndex)).Jmeno;
                    ((Kralik)VolniKralici.ZpristupniPrvek(listBoxVolniKralici.SelectedIndex)).Jmeno = jmeno;
                    NastavListBoxKralici();

                    MessageBox.Show("Králík " + puvodniJmeno + " byl upraven na " + jmeno + ".", "Králík Vytvořen",
                                    MessageBoxButtons.OK, MessageBoxIcon.Information);
                }


            }
            else if (textBoxJmeno.Text.Length < 4)
            {
                MessageBox.Show("Minimální délka jména musí být 4 znaky!", "Chyba délky",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);

            }
        }

        /// <summary>
        /// Metoda smaže vybraného králíka.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonSmazat_Click(object sender, EventArgs e)
        {
            string puvodniJmeno = ((Kralik)VolniKralici.ZpristupniPrvek(listBoxVolniKralici.SelectedIndex)).Jmeno;
            VolniKralici.Vymaz(listBoxVolniKralici.SelectedIndex);
            NastavListBoxKralici();

            MessageBox.Show("Králík " + puvodniJmeno + " byl smazán.", "Králík Vymazán",
                            MessageBoxButtons.OK, MessageBoxIcon.Information);
            OnPocetZmenen(new PocetEventArgs(listBoxVolniKralici.Items.Count + 1));
        }

        /// <summary>
        /// Handler
        /// </summary>
        /// <param name="e"></param>
        protected virtual void OnPocetZmenen(PocetEventArgs e)
        {
            PocetZmenenEventHandler handler = pocetZmenen;
            if (handler != null) handler(this, e);
        }

        /// <summary>
        /// Metoda zobrazuje starý a nový počet změn.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void volniKralíci_PocetZmenen(object sender, PocetEventArgs e)
        {
            listBoxLogovani.Items.Add("Starý počet: " + e.Pocet);
            listBoxLogovani.Items.Add("Nový počet:  " + VolniKralici.PocetPrvku);
            listBoxLogovani.Items.Add("");
            listBoxLogovani.TopIndex = listBoxLogovani.Items.Count - 1;
        }

        /// <summary>
        /// Metoda zapíná a vypíná logování.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void radioButtonAno_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButtonAno.Checked)
            {
                pocetZmenen += new PocetZmenenEventHandler(volniKralíci_PocetZmenen);
                listBoxLogovani.Items.Add("Logování zapnuto");
                listBoxLogovani.Items.Add("----------------");
            }
            else
            {
                pocetZmenen -= new PocetZmenenEventHandler(volniKralíci_PocetZmenen);
                listBoxLogovani.Items.Add("----------------");
                listBoxLogovani.Items.Add("Logování vypnuto");
            }
            listBoxLogovani.TopIndex = listBoxLogovani.Items.Count - 1;
        }

        /// <summary>
        /// Metoda kontroluje duplicitu jména v aplikaci.
        /// </summary>
        /// <param name="jmeno">Kontrolované jméno</param>
        /// <returns>Vrací 0 pokud je nalezena duplicita, jinak vrací hodnotu 1</returns>
        private int KontrolaDuplicity(string jmeno)
        {
            for (int i = 0; i < Turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(i);
                for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                {
                    if (((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j)).Jmeno.CompareTo(jmeno) == 0)
                    {
                        MessageBox.Show("Králík se zadaným jménem již existuje v turnaji!", "Duplicita",
                            MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return 0;
                    }
                }
            }

            for (int i = 0; i < VolniKralici.PocetPrvku; i++)
            {
                if (((Kralik)VolniKralici.ZpristupniPrvek(i)).Jmeno.CompareTo(jmeno) == 0)
                {
                    MessageBox.Show("Králík se zadaným jménem již existuje v seznamu volných!", "Duplicita",
                            MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return 0;
                }
            }
            return 1;
        }

        /// <summary>
        /// Vypise kolekci volnych králiků do jednoho ListBoxu a všechny králíky do druhého.
        /// </summary>
        public void NastavListBoxKralici()
        {
            listBoxVsichniKralíci.Items.Clear();
            listBoxVolniKralici.Items.Clear();
            for (int i = 0; i < Turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(i);
                for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                {
                    listBoxVsichniKralíci.Items.Add(((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j)).Jmeno);
                }
            }
            for (int i = 0; i < VolniKralici.PocetPrvku; i++)
            {
                listBoxVolniKralici.Items.Add(((Kralik)VolniKralici.ZpristupniPrvek(i)).Jmeno);
                listBoxVsichniKralíci.Items.Add(((Kralik)VolniKralici.ZpristupniPrvek(i)).Jmeno);
            }
            buttonSmazat.Enabled = false;
            buttonUpravit.Enabled = false;
        }
    }

}
