
<div class="item">
<table>
  
 	<td>
 	<div>
 		<g:if test = "${item.photo != null}">
 			<g:if test = "${item.photo.size()}">
 				<img src="${createLink(controller:'item', action:'image', id: item.id)}"/>
			</g:if>
			<g:else>
			<img src = "${resource(dir:'images',file:'nodisponible.jpg')}">
			</g:else>	
		</g:if>
		<g:else>
			<img src = "${resource(dir:'images',file:'nodisponible.jpg')}">
		</g:else>	
 	</div>
	</td>
 	<td>
 	<div >   
   			<g:link action="show" controller="item" id="${item.id}"><h3>${ item.name}</h3></g:link><br/>
    		<h4>${ item.summary}</h4><br/>
    		<div>${ item.details }</div><br/>
    		<div class = "deadLine">Vence: <g:formatDate format = "dd-MM-yyyy" date = "${item.deadLine}" /> </div><br/>
 	</div>   
    </td>
  
</table>
</div>
