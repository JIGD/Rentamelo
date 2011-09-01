
<div class="item">
<table>
  
 	<td>
 	<div>
 		<g:if test = "${item.hasPhoto}"> 			
 			<img src="${createLink(controller:'item', action:'image', id: item.id)}"/>	
		</g:if>
		<g:else>
			<img src = "${resource(dir:'images',file:'nodisponible.jpg')}">
		</g:else>	
		
	
	</div>
	</td>
 	<td>
 	<div>   
   			<h3>${ item.name}</h3><br/>
    		<h4>${ item.summary}</h4><br/>
    		<div>${ item.details }</div><br/>
    		<h4> Anunciante: ${ item.user.username}
    		</h4><br/>
 			<div class = "deadLine">Vence: <g:formatDate format = "dd-MMMM-yyyy" date = "${item.deadLine}" /> </div>
 	</div>   
    </td>
  
</table>
</div>
