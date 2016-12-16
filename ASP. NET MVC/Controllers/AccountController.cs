using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace Realitni_Kancelar.Controllers
{
    public class AccountController : AuthorizeAttribute
    {
        protected override void HandleUnauthorizedRequest(AuthorizationContext filterContext)
        {
            if (filterContext.HttpContext.Request.IsAuthenticated)
                base.HandleUnauthorizedRequest(filterContext);
            else
            {
                filterContext.Result = new RedirectToRouteResult(new
                               RouteValueDictionary("Admin login route"));
            }

        }

    }
}
