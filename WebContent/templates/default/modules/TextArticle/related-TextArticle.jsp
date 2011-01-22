<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedTextArticleContainer">
</div>

<ocw:setJs var="textArticleTemplate">
		<li>
			<a href="${config.articlePath}/\#{resource.id}">\#{resource.article.name}</a>
		</li>
</ocw:setJs>

<script language="javascript">
	var textArticleTemplate = new Template('${textArticleTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedTextArticleList;
	var timeout;
	Event.observe(window, 'load', function() {
		WebService.get('/texts/related/'+ resourceID,  {
					onSuccess : function(transport) {
						var i;
						//alert("Cai nay la TA" + transport.responseText);
						relatedTextArticleList = transport.responseJSON.result;
						if (relatedTextArticleList && relatedTextArticleList.length > 0) {
							for(i = 0 ; i < relatedTextArticleList.length ; i++){
								var textArticle = relatedTextArticleList[i];
								$('relatedTextArticleContainer').insert(textArticleTemplate.evaluate(textArticle));
							}
						} else {
							$('relatedTextArticleContainer').insert('Không có');
						}
					},
				    onFailure: function(transport) {
			            template.onFailure(transport); 
			        }
				  });
	});

</script>