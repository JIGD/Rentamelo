
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
 	<div>   
   			<h3>${ item.name}</h3><br/>
    		<h4>${ item.summary}</h4><br/>
    		<div>${ item.details }</div><br/>
    		<h4> Anunciante: ${ item.user.username}
    		<!-- <g:if test ="${item.user!=null}">${ item.user.username}</g:if> -->
    		</h4><br/>
    		<div class = "deadline">Vence: ${item.deadLine} </div><br/>
 	</div>   
    </td>
  
</table>
</div>
