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
    /// Dialog pro správu turnajů.
    /// </summary>
    public partial class DialogTurnaje : Form
    {
        /// <summary>
        /// Kolekce králíků, kteří nebyli zařazeni do žádného turnaje.
        /// </summary>
        public Kolekce VolniKralici { get; set; }

        /// <summary>
        /// Kolekce turnajů s jejich učastníkami.
        /// </summary>
        public Kolekce Turnaje { get; set; }

        /// <summary>
        /// Inicializuje objekty typu Kolekce a inicializuje komponenty.
        /// </summary>
        public DialogTurnaje()
        {
            InitializeComponent();
            VolniKralici = new Kolekce();
            Turnaje = new Kolekce();
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
                Turnaj zpristupnenyTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(listBoxTurnaje.SelectedIndex);
                for (int i = 0; i < zpristupnenyTurnaj.Ucastnici.PocetPrvku; i++)
                {
                    listBoxKraliciTurnaj.Items.Add(((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(i)).Jmeno);
                }
                textBoxNazevTurnaje.Text = ((Turnaj)Turnaje.ZpristupniPrvek(listBoxTurnaje.SelectedIndex)).Nazev;
                buttonUpravitTurnaj.Enabled = true;
                buttonSmazatTurnaj.Enabled = true;
                buttonOdebratZTurnaje.Enabled = false;
                
            }
            if (listBoxVolniKralici.SelectedIndex != -1)
            {
                buttonPridatDoTurnaje.Enabled = true;
            }
        }

        /// <summary>
        /// Metoda vytvoří nový turnaj.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonVytvoritTurnaj_Click(object sender, EventArgs e)
        {
            if (textBoxNazevTurnaje.Text != "" && textBoxNazevTurnaje.Text.Length >= 4)
            {
                string nazev = char.ToUpper(textBoxNazevTurnaje.Text[0]) + textBoxNazevTurnaje.Text.Substring(1);

                if (KontrolaDuplicity(nazev) == 1)
                {
                    Turnaje.Pridej(new Turnaj(nazev));
                    listBoxTurnaje.Items.Add(nazev);
                    MessageBox.Show("Turnaj " + nazev + " byl vytvořen.", "Turnaj Vytvořen",
                                    MessageBoxButtons.OK, MessageBoxIcon.Information);
                    buttonVymazatVse.Enabled = true;
                    textBoxNazevTurnaje.Text = "";
                }
            }
            else if (textBoxNazevTurnaje.Text.Length < 4)
            {
                MessageBox.Show("Minimální délka názvu turnaje musí být 4 znaky!", "Chyba délky",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        /// <summary>
        /// Metoda upravuje vybraný turnaj.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonUpravitTurnaj_Click(object sender, EventArgs e)
        {
            if (textBoxNazevTurnaje.Text != "" && textBoxNazevTurnaje.Text.Length >= 4)
            {
                string nazev = char.ToUpper(textBoxNazevTurnaje.Text[0]) + textBoxNazevTurnaje.Text.Substring(1);

                if (KontrolaDuplicity(nazev) == 1)
                {
                    int vybranyPrvek = listBoxTurnaje.SelectedIndex;
                    string puvodniNazev = ((Turnaj)Turnaje.ZpristupniPrvek(vybranyPrvek)).Nazev;
                    ((Turnaj)Turnaje.ZpristupniPrvek(vybranyPrvek)).Nazev = nazev;
                    NastavListBoxTurnaje();
                    MessageBox.Show("Turnaj " + puvodniNazev + " byl upraven na " + nazev + ".", "Turnaj Upraven",
                                    MessageBoxButtons.OK, MessageBoxIcon.Information);
                    textBoxNazevTurnaje.Text = "";
                }
            }
            else if (textBoxNazevTurnaje.Text.Length < 4)
            {
                MessageBox.Show("Minimální délka názvu turnaje musí být 4 znaky!", "Chyba délky",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        /// <summary>
        /// Metoda smaže vybraný turnaj.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonSmazatTurnaj_Click(object sender, EventArgs e)
        {
            Turnaj puvodniTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(listBoxTurnaje.SelectedIndex);

            for (int i = 0; i < puvodniTurnaj.Ucastnici.PocetPrvku; i++)
            {
                VolniKralici.Pridej(puvodniTurnaj.Ucastnici.ZpristupniPrvek(i));
            }

            Turnaje.Vymaz(listBoxTurnaje.SelectedIndex);
            NastavListBoxTurnaje();

            MessageBox.Show("Turnaj " + puvodniTurnaj.Nazev + " byl smazán a králíci v turnaji zařezeni mezi volné.", "Králík Vymazán",
                            MessageBoxButtons.OK, MessageBoxIcon.Information);
            textBoxNazevTurnaje.Text = "";
        }

        /// <summary>
        /// Metoda odebere králíka z vybraného turnaje.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonOdebratZTurnaje_Click(object sender, EventArgs e)
        {

            Turnaj zpristupnenyTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(listBoxTurnaje.SelectedIndex);

            VolniKralici.Pridej(zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(listBoxKraliciTurnaj.SelectedIndex));
            listBoxVolniKralici.Items.Add(((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(listBoxKraliciTurnaj.SelectedIndex)).Jmeno);

            zpristupnenyTurnaj.Ucastnici.Vymaz(listBoxKraliciTurnaj.SelectedIndex);
            listBoxKraliciTurnaj.Items.Clear();

            for (int i = 0; i < zpristupnenyTurnaj.Ucastnici.PocetPrvku; i++)
            {
                listBoxKraliciTurnaj.Items.Add(((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(i)).Jmeno);
            }

            buttonOdebratZTurnaje.Enabled = false;
            buttonPridatDoTurnaje.Enabled = false;

        }

        /// <summary>
        /// Metoda kontroluje vybraný objekt v listBoxu králíků vybraný turnaj.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listBoxKraliciTurnaj_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBoxKraliciTurnaj.SelectedIndex != -1 && listBoxTurnaje.SelectedIndex != -1)
            {
                buttonOdebratZTurnaje.Enabled = true;
            }
        }

        /// <summary>
        /// Metoda přidá králíka do vybraného turnaje.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonPridatDoTurnaje_Click(object sender, EventArgs e)
        {
            Turnaj zpristupnenyTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(listBoxTurnaje.SelectedIndex);

            zpristupnenyTurnaj.Ucastnici.Pridej(VolniKralici.ZpristupniPrvek(listBoxVolniKralici.SelectedIndex));
            listBoxKraliciTurnaj.Items.Add(((Kralik)VolniKralici.ZpristupniPrvek(listBoxVolniKralici.SelectedIndex)).Jmeno);

            VolniKralici.Vymaz(listBoxVolniKralici.SelectedIndex);
            listBoxVolniKralici.Items.Clear();

            for (int i = 0; i < VolniKralici.PocetPrvku; i++)
            {
                listBoxVolniKralici.Items.Add(((Kralik)VolniKralici.ZpristupniPrvek(i)).Jmeno);
            }
    
            buttonPridatDoTurnaje.Enabled = false;
        }

        /// <summary>
        /// Metoda kontroluje vybraný objekt v listBoxu volných králíků.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listBoxVolniKralici_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBoxVolniKralici.SelectedIndex != -1 && listBoxTurnaje.SelectedIndex != -1)
            {
                buttonPridatDoTurnaje.Enabled = true;
            }
        }

        /// <summary>
        /// Metoda vymaže veškeré záznamy.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonVymazatVse_Click(object sender, EventArgs e)
        {
            Turnaje.VymazVse();
            VolniKralici.VymazVse();
            NastavListBoxTurnaje();
        }

        /// <summary>
        /// Metoda kontroluje duplicitu názvu v aplikaci.
        /// </summary>
        /// <param name="nazev">Kontrolovaný název</param>
        /// <returns>Vrací 0 pokud je nalezena duplicita, jinak vrací hodnotu 1</returns>
        private int KontrolaDuplicity(string nazev)
        {
            for (int i = 0; i < Turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)Turnaje.ZpristupniPrvek(i);

                if (zpristupnenyTurnaj.Nazev.CompareTo(nazev) == 0)
                {
                    MessageBox.Show("Turnaj se zadaným názvem již existuje!", "Duplicita",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return 0;
                }

            }
            return 1;
        }

        /// <summary>
        /// Metoda nastavuje jednotlivé ListBoxy (turnaje, hráči ve vybraném turnaji, seznam volných Králiků)
        /// </summary>
        public void NastavListBoxTurnaje()
        {
            listBoxTurnaje.Items.Clear();
            listBoxKraliciTurnaj.Items.Clear();
            listBoxVolniKralici.Items.Clear();

            for (int i = 0; i < Turnaje.PocetPrvku; i++)
            {
                listBoxTurnaje.Items.Add(((Turnaj)Turnaje.ZpristupniPrvek(i)).Nazev);
            }

            for (int i = 0; i < VolniKralici.PocetPrvku; i++)
            {
                listBoxVolniKralici.Items.Add(((Kralik)VolniKralici.ZpristupniPrvek(i)).Jmeno);
            }
            buttonSmazatTurnaj.Enabled = false;
            buttonUpravitTurnaj.Enabled = false;
            buttonOdebratZTurnaje.Enabled = false;
            buttonPridatDoTurnaje.Enabled = false;

            if (Turnaje.PocetPrvku != 0 || VolniKralici.PocetPrvku != 0)
            {
                buttonVymazatVse.Enabled = true;
            }
            else
            {
                buttonVymazatVse.Enabled = false;
            }

        }
    }
}
