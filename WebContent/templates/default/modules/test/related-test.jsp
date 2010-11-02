<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<div id="relatedTestsContainer">
</div>

<ocw:setJs var="testTemplate">
		<li>
			<a href="${config.articlePath}/\#{resource.id}">\#{resource.name}</a>
		</li>
</ocw:setJs>

<script language="javascript">
	var testTemplate = new Template('${testTemplate}');
	var resourceID  = ${action.resource.id} ;
	var relatedTestList;
	var timeout;

	Event.observe(window, 'load', function() {
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