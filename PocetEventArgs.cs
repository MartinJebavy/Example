using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ZavodyKraliku
{
    /// <summary>
    /// Třída pro uchování změn.
    /// </summary>
    public class PocetEventArgs : EventArgs
    {
        int pocet;
        
        /// <summary>
        /// Zpřístupňuje a nastavuje pocet.
        /// </summary>
        public int Pocet
        {
            get { return pocet; }
        }

        /// <summary>
        /// Nastavuje hodnotu počet.
        /// </summary>
        /// <param name="pocet"></param>
        public PocetEventArgs(int pocet)
        {
            this.pocet = pocet;
        }
    }

    /// <summary>
    /// Delegát handler.
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
    delegate void PocetZmenenEventHandler(object sender, PocetEventArgs e);
}
