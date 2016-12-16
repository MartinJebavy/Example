using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Fei
{
    namespace Knihovna
    {
        /// <summary>
        /// Třída obsahuje delegát přijímající jeden parametr typu generického rozhraní knihovny
        /// </summary>
        public class Delegat
        {
            delegate void ProchazeniKolekce(Kolekce kolekce);
        }

        /// <summary>
        /// Třída slouží pro nastavení a práci s polem.
        /// </summary>
        public class Kolekce
        {
            private const int defaultniKapacita = 3;
            private object[] pole;

            /// <summary>
            /// Nastavuje a zpřistupňuje počet prvku v poli.
            /// </summary>
            public int PocetPrvku { get; private set; }

            /// <summary>
            /// Bezparametrický konstruktor, vytvářející nové pole a nastavující defaultní velikost pole na 3.
            /// </summary>
            public Kolekce()
            {
                pole = new object[defaultniKapacita];
            }

            /// <summary>
            /// Konstruktor s jedním parametrem, vytvářející nové pole s danou kapacitou.
            /// </summary>
            /// <param name="velikostPole">Velikost pole</param>
            public Kolekce(int velikostPole)
            {
                pole = new object[velikostPole];
            }

            /// <summary>
            /// Metoda přidá prvek na konec pole. Pokud již pole nemá kapacitu, zdvojnásobí velikost pole a prvek vloží na konec. 
            /// </summary>
            /// <param name="prvek">Vkládaný prvek</param>
            public void Pridej(object prvek)
            {
                if (pole == null || pole.Length == 0)
                {
                    pole = new object[defaultniKapacita];
                }
                if (pole.Length == PocetPrvku)
                {
                    object[] pomocnePole = new object[PocetPrvku * 2];
                    Array.Copy(pole, pomocnePole, PocetPrvku);
                    pole = pomocnePole;
                }
                pole[PocetPrvku++] = prvek;
            }

            /// <summary>
            /// Metoda vymaže prvek na zadaném indexu a setřídí pole tak, aby nullové hodnoty byly pouze na konci pole.
            /// </summary>
            /// <param name="index">Mazaný index</param>
            public void Vymaz(int index)
            {
                if (index >= 0 && index < PocetPrvku)
                {
                    for (int i = index; i < PocetPrvku - 1; i++)
                    {
                        pole[i] = pole[i + 1];
                    }
                    pole[--PocetPrvku] = null;
                }
            }

            /// <summary>
            /// Metoda projde pole, najde daný prvek, vymaže ho a setřídí pole tak, aby nullové hodnoty byly pouze na konci pole.
            /// </summary>
            /// <param name="prvek">Mazaný prvek</param>
            public void Vymaz(object prvek)
            {
                for (int i = 0; i < PocetPrvku; i++)
                {
                    if (prvek == pole[i])
                    {
                        Vymaz(i);
                        i = PocetPrvku;
                    }
                }
            }

            /// <summary>
            /// Metoda, která smaže všechny hodnoty v poli.
            /// </summary>
            public void VymazVse()
            {
                for (int i = 0; i < PocetPrvku; i++)
                {
                    pole[i] = null;
                }
            }
        }
    }
}