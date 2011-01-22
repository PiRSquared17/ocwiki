<div style="position: fixed; right: 10px; bottom: 0px" >
	<div>
		<textarea rows="10" cols="40" id="draft-content" style="display: none;">
		</textarea>
	</div>
	<div class="bottom-toolbar">
		<span onclick = "ShowDraft()" style="margin-right: 0px">Nháp</span>
	</div>
</div>
<script type="text/javascript">
	var isHide = true;
	//var content = null;
	
	// Connect len server lay du lieu ve
	Event.observe(window,'load',function(){
		WebService.get("/draft/", {
		    onSuccess : function(transport) {
	  		   	var contentDraft = transport.responseJSON.result;
	  		   	content = contentDraft.text;
	  		  	$('draft-content').innerHTML = content;
		    },
		    onFailure: function(transport){ 
		    	template.onFailure(transport); 
			}
		});
	});
	
	function ShowDraft(){
		var tinyEditor = tinymce.get('draft-content');
		if (!tinyEditor){
			tinyEditor = new tinymce.Editor('draft-content',{});
			tinyEditor.render();
		}
		
		if (isHide) {
			tinyEditor.show();
			//$('draft-area').show();
			isHide = false;
		}
		else{
			tinyEditor.hide();
			$('draft-content').hide();
			//$('draft-area').hide();
			isHide = true;			
		}
	}

	function save(){
		var content = $F('draft-content');
		var text = {"text": content};
		WebService.post('/draft/',{
	  		postBody: Object.toJSON({
		  		"text": content
	  		}),
		    onSuccess : function(transport) {
	  		   	
		    },
		    onFailure: function(transport){ 
		    	//template.onFailure(transport); 
			}
		});
		return false;
	}
	
	function timeoutExc(){
		save();
		setTimeout(timeoutExc, 60 * 1000);
	}
	
	window.setTimeout(timeoutExc, 60 * 1000);
	Event.observe(window, 'unload', save);
	
</script>