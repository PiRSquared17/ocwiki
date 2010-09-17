<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedTestsContainer">
</div>

<ocw:setJs var="testTemplate">
		<li>
			<a href="${config.articlePath}/\#{resource.id}">\#{resource.article.name}</a>
		</li>
</ocw:setJs>

<script language="javascript">
	var testTemplate = new Template('${testTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedTestList;
	new Ajax.Request(restPath + '/tests/related/'+ resourceID,
			  {
			    method:'get',
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					//alert(transport.responseText);
					var i;
					relatedTestList = transport.responseJSON.result;
					for(i = 0 ; i < relatedTestList.length ; i++){
						var test = relatedTestList[i];
						$('relatedTestsContainer').insert(testTemplate.evaluate(test));
					}
				},
			    onFailure: function()
			    { 
					openInfoDialog("resourceID không chính xác!");
			    }
			  });
</script>