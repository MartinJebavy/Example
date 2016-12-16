using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using Realitni_Kancelar.Models;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;

namespace Realitni_Kancelar.DAL
{
    public class RealContext : DbContext
    {
        public RealContext() : base("RealContext")
        {
        }
        public DbSet<Person> Persons { get; set; }
        public DbSet<Advertisement> Advertisements { get; set; }
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
        }
    }
}