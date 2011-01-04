<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<div>
	<div id="LikeCount" class="left"></div>
	<c:if test="${sessionScope.login}">
	   <div class="left">
		   <span style="display: none" id="likeLink"><a href="#" onclick="return like();">Thích</a> ·</span>   
		   <span style="display: none" id="unlikeLink"><a href="#" onclick="return unlike();">Không thích</a> ·</span>
	   </div>
	   <div class="jsmenu left"><ul class="level1 horizontal" id="mark-menu" style="display: none;">
	       <li class="level1"><a id="mark-status" href="javascript:void(0);"></a>
	           <ul class="level2 dropdown">
	               <li><a href="#" onclick="return toggleTodo();">Cần làm</a></li>
	               <li><a href="#" onclick="return toggleHard();">Khó</a></li>
	               <li><a href="#" onclick="return toggleEasy();">Dễ</a></li>
	           </ul>
	       </li>
	   </ul></div>
	</c:if>
</div>
<div class="clear"></div>

<script type="text/javascript">
	var resourceID  = ${action.resource.id};
	var resourceReport = null;
	var resourceCustomization = null;
	var login = ${sessionScope.login};
	var markMenu;

	Event.observe(window, 'load', function() {
		markMenu = new Menu('mark-menu', 'markMenu', function() {
	        this.closeDelayTime = 300;
	    });
		new Ajax.Request(restPath + '/resource_reports/' + resourceID,
		{
			method: 'get',
			requestHeader:{
				Accept: 'application/json'
			},
			evalJSON : true,
			onSuccess : function(transport) {
			    resourceReport = transport.responseJSON.result;
				resourceCustomization = {
			        'resource': { 'id': resourceID },
			        'user': { 'id': ${sessionScope.user.id} },
			        'like': resourceReport.like,
			        'todo': resourceReport.todo,
			        'level': resourceReport.level
				};
				updateLikeStatus();
				updateMarkStatus();
			},
			onFailure: function(transport) {
	            DefaultTemplate.onFailure(transport); 
	        }
		});
	});
	
	function updateLikeStatus() {
        if (resourceReport.likeCount > 0) {
            $('LikeCount').innerHTML = resourceReport.likeCount + ' người thích bài này ·&nbsp;';
        }
        setVisible('LikeCount', resourceReport.likeCount > 0);
        if (login) {
        	setVisible('unlikeLink', resourceCustomization.like == 'LIKE');
        	setVisible('likeLink', resourceCustomization.like != 'LIKE');
        }
	}
	
	function updateMarkStatus() {
		var markStatus = '';
        if (resourceCustomization.level == 1) {
            markStatus = 'Khó';
        } else if (resourceCustomization.level == -1) {
            markStatus = 'Dễ';
        }
        if (resourceCustomization.todo == 'TODO') {
            if (markStatus != '') {
                markStatus += ', ';
            }
            markStatus += ' cần làm';
        }
        if (markStatus == '') {
        	markStatus = 'Đánh dấu';
        }
        $('mark-status').innerHTML = markStatus;
        $('mark-menu').show();
	}

	function toggleTodo() {
		if (resourceCustomization.todo == 'TODO') {
			resourceCustomization.todo = 'NORMAL';
		} else {
			resourceCustomization.todo = 'TODO';
		}
		saveCustomization();
        return false;
	}
    
    function toggleHard() {
        if (resourceCustomization.level == 1) {
            resourceCustomization.level = 0;
        } else {
            resourceCustomization.level = 1;
        }
        saveCustomization();
        return false;
    }
    
    function toggleEasy() {
        if (resourceCustomization.level == -1) {
            resourceCustomization.level = 0;
        } else {
            resourceCustomization.level = -1;
        }
        saveCustomization();
        return false;
    }
    
    function like() {
    	resourceReport.likeCount++;
    	resourceCustomization.like = 'LIKE';
    	saveCustomization();
    	return false;
    }
    
    function unlike() {
        resourceReport.likeCount--;
    	resourceCustomization.like = 'NORMAL';
    	saveCustomization();
    	return false;
    }
	
	function saveCustomization() {
		new Ajax.Request(restPath + '/resource_customizations/' + resourceID,
        {
            method: 'post',
            requestHeader:{
                Accept: 'application/json'
            },
            contentType: 'application/json',
            postBody: Object.toJSON(resourceCustomization),
            evalJSON : true,
            onSuccess : function(transport) {
                resourceCustomization = transport.responseJSON.result;
                updateMarkStatus();
            },
            onFailure: function(transport) {
                DefaultTemplate.onFailure(transport); 
            }
        });
		updateLikeStatus();
		updateMarkStatus();
	}

</script>