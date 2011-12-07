<html>
	<head>
		<meta name="layout" content="main">
		<title>Sudoko oppgaveløser</title>
	</head>
	<body>
		<tmpl:renderMessages/>
		<g:form class="game">
			<g:each in="${(0..8)}" var="row">
				<div>
					<g:each in="${(0..8)}" var="col">
						<input type="text" class="cell row${row} col${col}" name="cells" value="${cells[row*9+col]}"/>
					</g:each>
				</div>
			</g:each>
			<g:actionSubmit value="Solve" action="solve" />
			<g:actionSubmit value="New" action="index" />
		</g:form>
	</body>
</html>
