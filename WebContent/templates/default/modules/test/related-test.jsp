<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedTestsContainer">
</div>

<ocw:setJs var="testTemplate">
	<div>
		<a href="${config.articlePath}/\#{resource.id}">\#{resource.name}</a>
	</div>
</ocw:setJs>

<script language="javascript">
	var testTemplate = new Template('${testTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedTestList;
	var timeout;

	Event.observe(window, 'load', function() {
		WebService.get('/tests/related/'+ resourceID,  {
					onSuccess : function(transport) {
						//alert(transport.responseText);
						var i;
						relatedTestList = transport.responseJSON.result;
						if (relatedTestList && relatedTestList.length > 0) {
							for(i = 0 ; i < relatedTestList.length ; i++){
								var test = relatedTestList[i];
								$('relatedTestsContainer').insert(testTemplate.evaluate(test));
							}
						} else {
							$('relatedTestsContainer').insert('Không có');
						}
					},
				    onFailure: function(transport) {
			            template.onFailure(transport); 
			        }
				  });
	});
</script>