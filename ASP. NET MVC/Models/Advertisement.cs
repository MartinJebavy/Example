using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;


namespace Realitni_Kancelar.Models
{
    public enum TypeOfAdvertisement
    {
        Offer, Demand
    }

    public enum VariantOfAdvertisement
    {
        Sale, Lease, Auction
    }

    public class Advertisement
    {
        [Required]
        public int ID { get; set; } // id - primarní klíč
        [Required]
        public string Title { get; set; } // Titulek inzerátu
        [Required]
        public TypeOfAdvertisement TypeOfAd { get; set; } // Typ inzerátu
        [Required]
        public VariantOfAdvertisement VariantOfAd { get; set; } // Druh inzerátu
        [Required]
        public string PropertyType { get; set; } // Typ nemovitosti
        [Required]
        public string DispositionOfProperty { get; set; } // Dispozice nemovitosti
        [Required]
        public string PropertySize { get; set; } // Velikost nemovitosti
        [Required]
        public string ConditionOfProperty { get; set; } // Stav nemovitosti
        [Required]
        public double FinalPrice { get; set; } // Konečná cena
        [Required]
        public int PersonID { get; set; }// Kontakt

        public virtual Person Persons { get; set; } // Vlastník inzerátu


    }
}