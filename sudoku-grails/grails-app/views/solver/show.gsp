<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Javabin Sudoko oppgaveløser</title>
		<r:require modules="jquery"/>
	</head>
	<body>
		<section id="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${cells}">
				<ul class="errors" role="alert">
					<g:eachError bean="${cells}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
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
		</section>
	</body>
</html>
