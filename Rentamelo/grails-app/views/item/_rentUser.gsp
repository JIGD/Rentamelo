<%@ page contentType="text/html;charset=ISO-8859-1" %>
<div class="item">
<h3>Rentaste el articulo ${rentU.itemRented} </h3>
<h3>Que es propiedad de ${rentU.itemOwner} </h3>
<h3>Lo rentaste el <g:formatDate date="${rentU.dateRented}" type="date" style="MEDIUM"/> y debes regresarlo el <g:formatDate date="${rentU.dateToReturn}" type="date" style="MEDIUM"/></h3>
<h3>El costo fue de ${rentU.totalCost}</h3><br/>

</div>