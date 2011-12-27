<%@ page contentType="text/html;charset=ISO-8859-1" %>
<div class="item">
<h3>Rentaste el articulo "${rentO.itemRented}" </h3> <br/>
<h3>Fue el <g:formatDate date="${rentU.dateRented}" type="date" style="MEDIUM"/> y debe ser regresado el <g:formatDate date="${rentU.dateToReturn}" type="date" style="MEDIUM"/></h3>
<h3>Fue rentado por "${rentO.rentedByUser }</h3>
<h3>El costo fue de "${rentO.totalCost}"</h3> <br/>
</div>