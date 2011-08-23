<div class="item">
<table>
  
 	<td>
 	<div>
 		<img src="${createLink(controller:'item', action:'image', id: item.id)}"/>
	</div>
	</td>
 	<td>
 	<div>   
   			<h3>${ item.name}</h3><br/>
    		<h4>${ item.summary}</h4><br/>
    		<div>${ item.details }</div><br/>
    		<h4> Anunciante: <g:if test ="${item.user!=null}">${ item.user.username}</g:if></h4><br/>
    		<div class = "deadline">Vence: ${item.deadLine} </div><br/>
 	</div>   
    </td>
  
</table>
</div>