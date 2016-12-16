using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Fei.Knihovna;

namespace ZavodyKraliku
{
    /// <summary>
    /// Třída, kde v jedné instanci soupeří proti sobě kolekce učastníků, patřící do této instance.
    /// </summary>

    [Serializable]
    public class Turnaj
    {
        #region Vlastnosti
        /// <summary>
        /// Nastavuje a zpřístupňuje název turnaje.
        /// </summary>
        public string Nazev { get; set; }

        /// <summary>
        /// Nastavuje a zpřístupňuje stav turnaje.
        /// </summary>
        public bool Odehrano { get; set; }

        /// <summary>
        /// Nastavuje a zpřístupňuje účastníky turnaje.
        /// </summary>
        public Kolekce Ucastnici { get; set; }
        #endregion

        #region Konstruktor
        /// <summary>
        /// Vytvoří turnaj s jedinečným názvem.
        /// </summary>
        /// <param name="nazevTurnaje">Název turnaje</param>
        public Turnaj(string nazevTurnaje)
        {
            Ucastnici = new Kolekce();
            Nazev = nazevTurnaje;
            Odehrano = false;
        }
        #endregion
    }
}
