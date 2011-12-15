
<div class="item">
<table>
  
 	<td>
 	<div>
 		<g:if test = "${item.picture!=0}"> 			
 			<img src="${createLink(controller:'item', action:'image', id: item.id)}"/>	
		</g:if>
		<g:else>
			<img src = "${resource(dir:'images',file:'nodisponible.jpg')}">
		</g:else>	
	</div>
	</td>
 	<td>
 	<div>   
   			<g:link action="show" controller="item" id="${item.id}"><h3>${ item.name}</h3></g:link><br/>
    		<h4>${ item.summary}</h4><br/>
    		<div>${ item.details }</div><br/>
    		<h4> Anunciante: <g:link action="show" controller="user" id="${item.user.id}">${ item.user.username}</g:link>
    		</h4><br/>
    		<g:if test = "${item.category!=null}"> 	
    		<h4>Categoria:<g:link action="listByCategory" controller="item" id="${item.category.id}"> ${ item.category.name} </g:link></h4></br>
    		</g:if>
 			<div class = "deadLine">Vence: <g:formatDate format = "dd-MM-yyyy" date = "${item.deadLine}" /> </div>
 	</div>   
    </td>
  
</table>
</div>
