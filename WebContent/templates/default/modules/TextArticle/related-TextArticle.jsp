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
		new Ajax.Request(restPath + '/texts/related/'+ resourceID,
				  {
				    method:'get',
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						var i;
						//alert("Cai nay la TA" + transport.responseText);
						relatedTextArticleList = transport.responseJSON.result;
						for(i = 0 ; i < relatedTextArticleList.length ; i++){
							var textArticle = relatedTextArticleList[i];
							$('relatedTextArticleContainer').insert(textArticleTemplate.evaluate(textArticle));
						}
					},
				    onFailure: function(transport) {
			            DefaultTemplate.onFailure(transport); 
			        }
				  });
	});

	function openInfoDialog(info) {
		Dialog.info(info + "<br> Thông báo tự động đóng sau 2 giây ...",
	               {width:300, height:100, className: "alphacube",showProgress: true});
	  	timeout=2;
	  	setTimeout(infoTimeout, 1000);
	}

	function infoTimeout() {
	  	timeout--;
	  	if (timeout >0) {
	    	Dialog.setInfoMessage("Thông báo tự động đóng sau " + timeout + "giây ...");
	    	setTimeout(infoTimeout, 1000);
	 	}
	 	else
	  		Dialog.closeInfo();
	}
</script>