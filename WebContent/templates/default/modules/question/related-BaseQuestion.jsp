<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedBaseQuestionContainer">
</div>

<ocw:setJs var="baseQuestionTemplate">
		<li>
			<a href="${config.articlePath}/\#{resource.id}">\#{resource.name}</a>
		</li>
</ocw:setJs>

<script language="javascript">
	var baseQuestionTemplate = new Template('${baseQuestionTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedBaseQuestionList;
	var timeout;
	Event.observe(window, 'load', function() {
		WebService.get('/questions/related/'+ resourceID,  {
					onSuccess : function(transport) {
						relatedBaseQuestionList = transport.responseJSON.result;
						if (relatedBaseQuestionList && relatedBaseQuestionList.length > 0) {
							for(i = 0 ; i < relatedBaseQuestionList.length ; i++){
								var baseQuestion = relatedBaseQuestionList[i];
								$('relatedBaseQuestionContainer').insert(baseQuestionTemplate.evaluate(baseQuestion));
							}
						} else {
							$('relatedBaseQuestionContainer').insert('Không có');
						}
					},
				    onFailure: function(transport) {
			            template.onFailure(transport); 
			        }
				  });
	});

</script>