<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedMultichoiceQuestionContainer">
</div>

<ocw:setJs var="baseQuestionTemplate">
		<li>
			<a href="${config.articlePath}/\#{resource.id}">\#{resource.name}</a>
		</li>
</ocw:setJs>

<script language="javascript">
	var baseQuestionTemplate = new Template('${baseQuestionTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedMultichoiceQuestionList;
	var timeout;
	Event.observe(window, 'load', function() {
		WebService.get('/questions/related/'+ resourceID,  {
					onSuccess : function(transport) {
						relatedMultichoiceQuestionList = transport.responseJSON.result;
						if (relatedMultichoiceQuestionList && relatedMultichoiceQuestionList.length > 0) {
							for(i = 0 ; i < relatedMultichoiceQuestionList.length ; i++){
								var baseQuestion = relatedMultichoiceQuestionList[i];
								$('relatedMultichoiceQuestionContainer').insert(baseQuestionTemplate.evaluate(baseQuestion));
							}
						} else {
							$('relatedMultichoiceQuestionContainer').insert('Không có');
						}
					},
				    onFailure: function(transport) {
			            template.onFailure(transport); 
			        }
				  });
	});

</script>