using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Fei.Knihovna;

namespace ZavodyKraliku
{
    /// <summary>
    /// Třída, kde se vytvoří instace Kralik s danými vlastnostmi
    /// </summary>
    [Serializable]
    public class Kralik
    {
        #region Vlastnosti
        /// <summary>
        /// Nastavuje a zpřístupňuje jmeno králíka.
        /// </summary>
        public string Jmeno { get; set; }

        /// <summary>
        /// Konstatni rychlost králíka
        /// </summary>
        private const int rychlost = 50;

        /// <summary>
        /// Zpřístupňuje rychlost králika;
        /// </summary>
        public int Rychlost { get { return rychlost; } }

        /// <summary>
        /// Nastavuje a zpřístupňuje počet výher doma králíka.
        /// </summary>
        public int PocetVyherDoma { get; set; }

        /// <summary>
        /// Nastavuje a zpřístupňuje počet výher venku králíka.
        /// </summary>
        public int PocetVyherVenku { get; set; }

        /// <summary>
        /// Nastavuje a zpřístupňuje počet proher doma králíka.
        /// </summary>
        public int PocetProherDoma { get; set; }

        /// <summary>
        /// Nastavuje a zpřístupňuje počet proher venku králíka.
        /// </summary>
        public int PocetProherVenku { get; set; }

        /// <summary>
        /// Nastavuje a zpřístupňuje kolekci soupeřů králíka.
        /// </summary>
        public Kolekce Souperi { get; set; }
        #endregion

        #region Konstruktor
        /// <summary>
        /// Vytvoří králíka s jedinečným jménem.
        /// </summary>
        /// <param name="jmeno">Jméno králika</param>
        public Kralik(string jmeno)
        {
            Jmeno = jmeno;
            Souperi = new Kolekce();
        }
        #endregion
    }
}
