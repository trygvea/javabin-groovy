<html>
	<head>
		<meta name="layout" content="main">
		<title>Sudoko oppgaveløser</title>
		<r:require modules="jquery"/>
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:if test="${flash.error}">
			<div class="errors">
				<li>${flash.error}</li>
			</div>
		</g:if>
		<g:form>
			<div class="game">
				<g:each in="${(0..8)}" var="row">
					<div>
						<g:each in="${(0..8)}" var="col">
							<g:textField class="cell row${row} col${col}" name="cells" value="${cells[row*9+col]}"/>
						</g:each>
					</div>
				</g:each>
			<div>
			<g:actionSubmit value="Solve" action="solve" />
			<g:actionSubmit value="New" action="show" />
		</g:form>
	</body>
</html>
