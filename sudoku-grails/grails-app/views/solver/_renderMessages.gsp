<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
</g:if>
<g:if test="${flash.error}">
	<div class="errors">
		<ul><li>${flash.error}</li></ul>
	</div>
</g:if>
