using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Fei.Knihovna;

namespace ZavodyKraliku
{
    /// <summary>
    /// Třída pro uskutečnění turnajů.
    /// </summary>
    public class Zapasy
    {
        #region Vlastnosti

        private Kolekce turnaje;

        /// <summary>
        /// Zpřístupňuje a nastavuje turnaje.
        /// </summary>
        public Kolekce Turnaje
        {
            get { return turnaje; }
            set
            {
                for (int i = 0; i < value.PocetPrvku; i++)
                {
                    Turnaj zpristupnenyTurnaj = (Turnaj)value.ZpristupniPrvek(i);
                    turnaje.Pridej(new Turnaj(zpristupnenyTurnaj.Nazev));
                    for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                    {
                        Kralik zristupnenyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j);
                        ((Turnaj)turnaje.ZpristupniPrvek(i)).Ucastnici.Pridej(new Kralik(zristupnenyKralik.Jmeno));
                    }
                }
            }
        }

        /// <summary>
        /// Zpřístupňuje a nastavuje počet odehraných zápasů.
        /// </summary>
        public int PocetOdehranychZapasu { get; private set; }

        /// <summary>
        /// Zpřístupňuje a nastavuje počet odehraných turnajů.
        /// </summary>
        public int PocetOdehranychTurnaju { get; private set; }

        private Random generator;

        #endregion

        #region Konstruktor

        /// <summary>
        /// Inicializuje proměnnou generator a kolekci.
        /// </summary>
        public Zapasy()
        {
            generator = new Random(DateTime.Now.Ticks.GetHashCode());
            turnaje = new Kolekce();
        }
        #endregion

        #region Metody

        /// <summary>
        /// Metoda přiřadí učastníkům turnajů své soupeře.
        /// </summary>
        public void PripravitTurnaje()
        {
            for (int i = 0; i < turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)turnaje.ZpristupniPrvek(i);
                if (zpristupnenyTurnaj.Ucastnici.PocetPrvku > 1)
                {
                    for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                    {
                        Kralik zpristupnenyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j);
                        for (int k = 0; k < zpristupnenyTurnaj.Ucastnici.PocetPrvku; k++)
                        {
                            if (k != j)
                            {
                                zpristupnenyKralik.Souperi.Pridej(zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(k));
                            }
                        }
                    }
                }
            }
        }

        /// <summary>
        /// Metoda uskuteční všechny turnaje.
        /// </summary>
        public void OdehratTurnaje()
        {
            Kolekce vysledkyTurnaju = new Kolekce();
            PocetOdehranychZapasu = 0;
            PocetOdehranychTurnaju = 0;
            for (int i = 0; i < turnaje.PocetPrvku; i++)
            {
                Turnaj zpristupnenyTurnaj = (Turnaj)turnaje.ZpristupniPrvek(i);
                vysledkyTurnaju.Pridej(new Turnaj(zpristupnenyTurnaj.Nazev));

                if (zpristupnenyTurnaj.Ucastnici.PocetPrvku > 1)
                {
                    while (zpristupnenyTurnaj.Ucastnici.PocetPrvku != 1)
                    {
                        for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                        {
                            Kralik zpristupnenyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j);

                            for (int k = 0; k < zpristupnenyKralik.Souperi.PocetPrvku; k++)
                            {
                                double formaZprKralika = aktualniForma() + vyhodaDomacihoProstredi();
                                double formaSoupere = aktualniForma();

                                int aktualniRychlostZprKralika = (int)(zpristupnenyKralik.Rychlost * formaZprKralika);
                                int aktualniRychlostiSoupere = (int)(((Kralik)zpristupnenyKralik.Souperi.ZpristupniPrvek(k)).Rychlost * formaSoupere);

                                PocetOdehranychZapasu++;

                                if (aktualniRychlostZprKralika > aktualniRychlostiSoupere)
                                {
                                    zpristupnenyKralik.PocetVyherDoma++;
                                    ((Kralik)zpristupnenyKralik.Souperi.ZpristupniPrvek(k)).PocetProherVenku++;
                                }
                                else
                                {
                                    zpristupnenyKralik.PocetProherDoma++;
                                    ((Kralik)zpristupnenyKralik.Souperi.ZpristupniPrvek(k)).PocetVyherVenku++;
                                }
                            }
                        }
                        int indexPoslednihoKralika = posledniKralik(zpristupnenyTurnaj);
                        if (indexPoslednihoKralika != -1)
                        {
                            ((Turnaj)vysledkyTurnaju.ZpristupniPrvek(i)).Ucastnici.Pridej(zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(indexPoslednihoKralika));
                            zpristupnenyTurnaj.Ucastnici.Vymaz(indexPoslednihoKralika);
                            zpristupnenyTurnaj = upravTurnaj(zpristupnenyTurnaj);
                        }
                    }
                    ((Turnaj)vysledkyTurnaju.ZpristupniPrvek(i)).Ucastnici.Pridej(zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(0));
                    ((Turnaj)vysledkyTurnaju.ZpristupniPrvek(i)).Odehrano = true;
                    PocetOdehranychTurnaju++;
                }
            }
            turnaje = vysledkyTurnaju;
        }

        /// <summary>
        /// Metoda upraví v turnaji soupeře jednotlivých učastníkům po vyřazení nejhoršího králika.
        /// </summary>
        /// <param name="zpristupnenyTurnaj"></param>
        /// <returns>Upravený turnaj</returns>
        private Turnaj upravTurnaj(Turnaj zpristupnenyTurnaj)
        {
            if (zpristupnenyTurnaj.Ucastnici.PocetPrvku > 1)
            {
                for (int j = 0; j < zpristupnenyTurnaj.Ucastnici.PocetPrvku; j++)
                {
                    ((Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j)).Souperi.VymazVse();
                    Kralik zpristupnenyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(j);
                    for (int k = 0; k < zpristupnenyTurnaj.Ucastnici.PocetPrvku; k++)
                    {
                        if (k != j)
                        {
                            zpristupnenyKralik.Souperi.Pridej(zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(k));
                        }
                    }
                }
                return zpristupnenyTurnaj;
            }
            else
            {
                return zpristupnenyTurnaj;
            }
        }

        /// <summary>
        /// Metoda hledá nejhoršího králíka v turnaji.
        /// </summary>
        /// <param name="zpristupnenyTurnaj"></param>
        /// <returns>Pokud je nalezen nejhorší králík, vrací jeho index v kolekci, pokud ne, vrací -1</returns>
        private int posledniKralik(Turnaj zpristupnenyTurnaj)
        {
            int indexPoslednihoKralika = -1;
            int minVyher = int.MaxValue;
            int pocetPoslednichKraliku = 0;
            for (int i = 0; i < zpristupnenyTurnaj.Ucastnici.PocetPrvku; i++)
            {
                Kralik kontrolovanyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(i);
                if ((kontrolovanyKralik.PocetVyherDoma + kontrolovanyKralik.PocetVyherVenku) == minVyher)
                {
                    pocetPoslednichKraliku++;
                }
                else if ((kontrolovanyKralik.PocetVyherDoma + kontrolovanyKralik.PocetVyherVenku) < minVyher)
                {
                    indexPoslednihoKralika = i;
                    pocetPoslednichKraliku = 1;
                    minVyher = kontrolovanyKralik.PocetVyherDoma + kontrolovanyKralik.PocetVyherVenku;
                }
            }

            if (pocetPoslednichKraliku > 1)
            {
                int minVyherVenku = int.MaxValue;
                pocetPoslednichKraliku = 0;
                for (int i = 0; i < zpristupnenyTurnaj.Ucastnici.PocetPrvku; i++)
                {
                    Kralik kontrolovanyKralik = (Kralik)zpristupnenyTurnaj.Ucastnici.ZpristupniPrvek(i);
                    if ((kontrolovanyKralik.PocetVyherDoma + kontrolovanyKralik.PocetVyherVenku) == minVyher)
                    {
                        if (kontrolovanyKralik.PocetVyherVenku == minVyherVenku)
                        {
                            pocetPoslednichKraliku++;
                        }
                        else if (kontrolovanyKralik.PocetVyherVenku < minVyherVenku)
                        {
                            indexPoslednihoKralika = i;
                            pocetPoslednichKraliku = 1;
                            minVyherVenku = kontrolovanyKralik.PocetVyherVenku;
                        }

                    }
                }
                if (pocetPoslednichKraliku > 1)
                {
                    return -1;
                }
                else
                {
                    return indexPoslednihoKralika;
                }
            }
            return indexPoslednihoKralika;
        }

        /// <summary>
        /// Metoda generuje aktuální formu králíka.
        /// </summary>
        /// <returns>Vygenerovaná hodnota</returns>
        private double aktualniForma()
        {
            return ((double)generator.Next(70, 130)) / 100;

        }

        /// <summary>
        /// Metoda generuje vyhodu pro domacího králíka.
        /// </summary>
        /// <returns>Vygenerovaná hodnota</returns>
        private double vyhodaDomacihoProstredi()
        {
            return ((double)generator.Next(1, 10)) / 100;
        }

        #endregion
    }
}
