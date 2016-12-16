using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Realitni_Kancelar.DAL;
using Realitni_Kancelar.Models;

namespace Realitni_Kancelar.Controllers
{
    public class AdvertisementController : Controller
    {
        private RealContext db = new RealContext();
        private static TypeOfAdvertisement vybranyTypeOfAD = TypeOfAdvertisement.Offer;
        private static VariantOfAdvertisement vybranyVarOfAD = VariantOfAdvertisement.Auction;
        private static bool vyber = false;

        // GET: Advertisement
        public ActionResult Index(string sortOrder)
        {

            //var advertisements = db.Advertisements.Include(a => a.Persons);
            //ViewBag.VANameSortParm = String.IsNullOrEmpty(sortOrder) ? "name_desc" : "";
            ViewBag.VANameSortParm = sortOrder == "VAName" ? "VAName_desc" : "VAName";
            ViewBag.FPNameSortParm = sortOrder == "FPName" ? "FPName_desc" : "FPName";
            ViewBag.TitleNameSortParm = sortOrder == "TitleName" ? "TitleName_desc" : "TitleName";
            ViewBag.TypeOfAdNameSortParm = sortOrder == "TypeOfAdName" ? "TypeOfAdName_desc" : "TypeOfAdName";
            ViewBag.PropertyTypeNameSortParm = sortOrder == "PropertyTypeName" ? "PropertyTypeName_desc" : "PropertyTypeName";
            ViewBag.ConditionOFPNameSortParm = sortOrder == "ConditionOFPName" ? "ConditionOFPName_desc" : "ConditionOFPName";

            var advertisements = from s in db.Advertisements select s;
            if (vyber)
            {
                advertisements = from a in db.Advertisements
                                 where a.TypeOfAd == vybranyTypeOfAD && a.VariantOfAd == vybranyVarOfAD
                                 select a;
            }



            switch (sortOrder)

            {
                case "ConditionOFPName_desc":

                    advertisements = advertisements.OrderByDescending(s => s.ConditionOfProperty);

                    break;
                case "ConditionOFPName":

                    advertisements = advertisements.OrderBy(s => s.ConditionOfProperty);

                    break;
                case "PropertyTypeName_desc":

                    advertisements = advertisements.OrderByDescending(s => s.PropertyType);

                    break;
                case "PropertyTypeName":

                    advertisements = advertisements.OrderBy(s => s.PropertyType);

                    break;
                case "TypeOfAdName_desc":

                    advertisements = advertisements.OrderByDescending(s => s.TypeOfAd);

                    break;
                case "TypeOfAdName":

                    advertisements = advertisements.OrderBy(s => s.TypeOfAd);

                    break;
                case "TitleName_desc":

                    advertisements = advertisements.OrderByDescending(s => s.Title);

                    break;
                case "TitleName":

                    advertisements = advertisements.OrderBy(s => s.Title);

                    break;
                case "VAName_desc":

                    advertisements = advertisements.OrderByDescending(s => s.VariantOfAd);

                    break;
                case "VAName":

                    advertisements = advertisements.OrderBy(s => s.VariantOfAd);

                    break;

                case "FPName_desc":

                    advertisements = advertisements.OrderByDescending(s => s.FinalPrice);

                    break;

                case "FPName":

                    advertisements = advertisements.OrderBy(s => s.FinalPrice);
                    break;
                default:
                    break;

            }
            vyber = false;
            return View(advertisements.ToList());
        }
        [HttpPost]
        public ActionResult Index(Advertisement advare)
        {
            var advertisements = from s in db.Advertisements where s.TypeOfAd == advare.TypeOfAd && s.VariantOfAd == advare.VariantOfAd select s;
            vybranyTypeOfAD = advare.TypeOfAd;
            vybranyVarOfAD = advare.VariantOfAd;
            vyber = true;
            return View(advertisements.ToList());
        }
        // GET: Advertisement/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Advertisement advertisement = db.Advertisements.Find(id);
            if (advertisement == null)
            {
                return HttpNotFound();
            }
            return View(advertisement);
        }

        // GET: Advertisement/Create
        public ActionResult Create()
        {
            ViewBag.PersonID = new SelectList(db.Persons, "ID", "LastName");
            return View();
        }

        // POST: Advertisement/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Title,TypeOfAd,VariantOfAd,PropertyType,DispositionOfProperty,PropertySize,ConditionOfProperty,FinalPrice,PersonID")] Advertisement advertisement)
        {
            if (ModelState.IsValid)
            {
                db.Advertisements.Add(advertisement);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.PersonID = new SelectList(db.Persons, "ID", "LastName", advertisement.PersonID);
            return View(advertisement);
        }

        // GET: Advertisement/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Advertisement advertisement = db.Advertisements.Find(id);
            if (advertisement == null)
            {
                return HttpNotFound();
            }
            ViewBag.PersonID = new SelectList(db.Persons, "ID", "LastName", advertisement.PersonID);
            return View(advertisement);
        }

        // POST: Advertisement/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Title,TypeOfAd,VariantOfAd,PropertyType,DispositionOfProperty,PropertySize,ConditionOfProperty,FinalPrice,PersonID")] Advertisement advertisement)
        {
            if (ModelState.IsValid)
            {
                db.Entry(advertisement).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.PersonID = new SelectList(db.Persons, "ID", "LastName", advertisement.PersonID);
            return View(advertisement);
        }

        // GET: Advertisement/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Advertisement advertisement = db.Advertisements.Find(id);
            if (advertisement == null)
            {
                return HttpNotFound();
            }
            return View(advertisement);
        }

        // POST: Advertisement/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Advertisement advertisement = db.Advertisements.Find(id);
            db.Advertisements.Remove(advertisement);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
