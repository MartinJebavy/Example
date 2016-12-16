using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using Realitni_Kancelar.DAL;
using Realitni_Kancelar.Models;

namespace Realitni_Kancelar.Controllers
{
    public class LoginController : Controller
    {
        private RealContext db = new RealContext();
         
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }


        public ActionResult Register()
        {
            return View();
        }

        public ActionResult Login()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Login([Bind(Include = "ID,Nick,Password")]Person osoba)
        {

                var prihlasitOsobu = from a in db.Persons
                                     where osoba.Nick == a.Nick
                                     select a;
                if (prihlasitOsobu.Count() != 0)
                {
                    if (osoba.Password == prihlasitOsobu.First().Password)
                    {
                    FormsAuthentication.SetAuthCookie(osoba.Nick, true);
                    

                   
                    return RedirectToAction("Index", "Advertisement");
                    }
                    else
                    {
                        ModelState.AddModelError("", "Nespravne heslo!");
                    }
                }
                else
                {
                    ModelState.AddModelError("", "Zadany nick neexistuje!");
                }
            
            return View();
        }

        [HttpPost]
        public ActionResult Register([Bind(Include = "ID,FirstName,LastName,Contact,Nick,Password")]Person osoba)
        {
            if (ModelState.IsValid)
            {
                var duplicitniNick = from a in db.Persons
                                     where osoba.Nick == a.Nick
                                     select a;
                if (duplicitniNick.Count() == 0)
                {
                    osoba.Role = TypeOfRole.User;
                    db.Persons.Add(osoba);
                    db.SaveChanges();
                    Roles.AddUserToRole(osoba.Nick, "Admin");
                    return RedirectToAction("Index","Person");
                }
                else
                {
                    ModelState.AddModelError("", "Nick je obsazeny, vyber jiny!");
                }

            }
            return View();
        }
    }
}