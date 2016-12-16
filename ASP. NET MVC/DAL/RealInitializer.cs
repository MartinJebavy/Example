using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using Realitni_Kancelar.Models;

namespace Realitni_Kancelar.DAL
{
    public class RealInitializer : System.Data.Entity.DropCreateDatabaseIfModelChanges<RealContext>
    {
        protected override void Seed(RealContext context)
        {

            var persons = new List<Person>
            {
new Person {FirstName = "Martin", LastName="Jebavý", Nick="Jeby", Password="12345",Role= TypeOfRole.Admin, Contact= "123 356 368" },
new Person {FirstName = "Jiří", LastName="Škornok", Nick="Jirka", Password="1234", Role= TypeOfRole.User, Contact= "356 123 496" },
new Person {FirstName = "Petr", LastName="Kopic", Nick="Petr", Password="123", Role= TypeOfRole.User, Contact= "695 365 146" }
            };
            persons.ForEach(s => context.Persons.Add(s));
            context.SaveChanges();

            var advertisements = new List<Advertisement>
            {
                new Advertisement {Title="Název1",TypeOfAd = TypeOfAdvertisement.Offer, VariantOfAd = VariantOfAdvertisement.Sale,
                PropertyType = "Chalupa", DispositionOfProperty="3+1",PropertySize="Obytná plocha", ConditionOfProperty="Nový",
                FinalPrice=1000000.50, PersonID=1},
                new Advertisement {Title="Název2",TypeOfAd = TypeOfAdvertisement.Demand, VariantOfAd = VariantOfAdvertisement.Lease,
                PropertyType = "Dům", DispositionOfProperty="5+2",PropertySize="Volná plocha", ConditionOfProperty="Rujna",
                FinalPrice=2654333.50, PersonID=1},
                new Advertisement {Title="Název3",TypeOfAd = TypeOfAdvertisement.Offer, VariantOfAd = VariantOfAdvertisement.Auction,
                PropertyType = "Byt", DispositionOfProperty="1+KK",PropertySize="Užitková plocha", ConditionOfProperty="Po rekonstrukci",
                FinalPrice=13365660.50, PersonID=2},
                new Advertisement {Title="Název4",TypeOfAd = TypeOfAdvertisement.Demand, VariantOfAd = VariantOfAdvertisement.Lease,
                PropertyType = "Zahrada", DispositionOfProperty="Neuvedeno",PropertySize="Obytná plocha", ConditionOfProperty="Rujna",
                FinalPrice=50000, PersonID=3},
                new Advertisement {Title="Název5",TypeOfAd = TypeOfAdvertisement.Demand, VariantOfAd = VariantOfAdvertisement.Sale,
                PropertyType = "Byt", DispositionOfProperty="1+1",PropertySize="Zastavěná plocha", ConditionOfProperty="Nový",
                FinalPrice=250000, PersonID=3}
            };
            advertisements.ForEach(s => context.Advertisements.Add(s));
            context.SaveChanges();
        }
    }
}