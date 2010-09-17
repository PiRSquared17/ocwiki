<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedBaseQuestionContainer">
</div>

<ocw:setJs var="baseQuestionTemplate">
		<li>
			<a href="${config.articlePath}/\#{resource.id}">\#{resource.article.name}</a>
		</li>
</ocw:setJs>

<script language="javascript">
	var baseQuestionTemplate = new Template('${baseQuestionTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedBaseQuestionList;
	var timeout;
	new Ajax.Request(restPath + '/questions/related/'+ resourceID,
			  {
			    method:'get',
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					//alert("cai nay la BQ " + transport.responseText);
					var i;
					relatedBaseQuestionList = transport.responseJSON.result;
					for(i = 0 ; i < relatedBaseQuestionList.length ; i++){
						var baseQuestion = relatedBaseQuestionList[i];
						$('relatedBaseQuestionContainer').insert(baseQuestionTemplate.evaluate(baseQuestion));
					}
				},
			    onFailure: function()
			    { 
					openInfoDialog("resourceID không chính xác!");
			    }
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