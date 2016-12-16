using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace Realitni_Kancelar.Models
{
    public enum TypeOfRole
    {
        Admin, User
    }

    public class Person
    {
        [Required]
        public int ID { get; set; } // Primární klíč
        [Required]
        public string Nick { get; set; } // Nick na příhlašení
        [Required]
        public string Password { get; set; } // heslo
        [Required]
        public string FirstName { get; set; } // Jméno
        [Required]
        public string LastName { get; set; } // Přijmení
        [Required]
        public TypeOfRole Role { get; set; } // Role
        [Required]
        public string Contact { get; set; } // Telefoní číslo

        public virtual ICollection<Advertisement> Advertisements { get; set; } // Kolekce svých inzerátu
    }
}