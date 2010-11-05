<div style="position: fixed; right: 10px; bottom: 0px" >
	<div id="draft-area">
		<div>
			<textarea rows="10" cols="40" id="draft-content">
			</textarea>
		</div>
		<div>
			<button onclick="save()">Lưu</button>
		</div>
	</div>
	<div align="right">
		<span onclick = "ShowDraft()" style="margin-right: 0px">Nháp</span>
	</div>
</div>
<script type="text/javascript">
	var isHide = true;
	//var content = null;
	
	// Connect len server lay du lieu ve
	new Ajax.Request(restPath + "/draft/",{
		method: 'get',
		requestHeaders : {
	       Accept : 'application/json'
  		},
	    evalJSON : true,
	    onSuccess : function(transport) {
  		   	var contentDraft = transport.responseJSON.result;
  		   	content = contentDraft.text;
  		  	$('draft-content').innerHTML = content;
  		  	$('draft-content').hide();
	    },
	    onFailure: function(transport){ 
	    	DefaultTemplate.onFailure(transport); 
		}
	});
	
	function ShowDraft(){
		var tinyEditor = tinymce.get('draft-content');
		if (!tinyEditor){
			tinyEditor = new tinymce.Editor('draft-content',{});
			tinyEditor.render();
		}
		
		if (isHide) {
			//tinyEditor.show();
			$('draft-area').show();
			isHide = false;
		}
		else{
			//tinyEditor.hide();
			//$('draft-content').hide();
			$('draft-area').hide();
			isHide = true;			
		}
	}

	function save(){
		var content = null;
		var text;
		var tinyEditor = tinymce.get('draft-content');
		if (!tinyEditor){
			tinyEditor = new tinymce.Editor('draft-content',{});
			tinyEditor.render();
		}
		content = tinyEditor.getContent();
		text = {"text": content};
		new Ajax.Request(restPath + '/draft/',{
			method: 'post',
			requestHeaders : {
		       Accept : 'application/json'
	  		},
	  		contentType: 'application/json',
	  		postBody: Object.toJSON({
		  		"text": content
	  		}),
		    evalJSON : true,
		    onSuccess : function(transport) {
	  		   	
		    },
		    onFailure: function(transport){ 
		    	//DefaultTemplate.onFailure(transport); 
			}
		});
	}
	function timeoutExc(){
		save();
		setTimeout(timeoutExc, 10000);
	}
	window.setTimeout(timeoutExc, 10000);
	Event.observe(window,'unload',save);
</script>