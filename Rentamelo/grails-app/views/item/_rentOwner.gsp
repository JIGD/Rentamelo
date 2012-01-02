<%@ page contentType="text/html;charset=ISO-8859-1" %>
<div class="item">
<h3>Te rentaron el articulo "${rentOwn.itemRented}" </h3>
<h3>Fue el <g:formatDate date="${rentOwn.dateRented}" type="date" style="MEDIUM"/> y debe ser regresado el <g:formatDate date="${rentOwn.dateToReturn}" type="date" style="MEDIUM"/></h3>
<h3>Fue rentado por "${rentOwn.rentedByUser }</h3>
<h3>El costo fue de "${rentOwn.totalCost}"</h3> <br/>
</div>
<g:form>
<g:hiddenField name="itemName" value="${rentOwn.itemRented}" />
                  <div class="buttons">
                    <span class="button"><g:actionSubmit value="Articulo regresado" action="itemReturned" onclick="return confirm('Estas seguro?');" /></span>
                </div>
                </g:form>