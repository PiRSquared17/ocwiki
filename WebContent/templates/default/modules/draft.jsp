<div style="position: fixed; right: 10px; bottom: 0px" >
	<div>
		<textarea rows="10" cols="40" id="draft-content">
		</textarea>
	</div>
	<div onclick="ShowDraft()">
		Nháp
	</div>
</div>
<script type="text/javascript">
	var isHide = true;
	$('draft-content').hide();
	function ShowDraft(){
		var tinyEditor = tinymce.get('draft-content');
		if (!tinyEditor){
			tinyEditor = new tinymce.Editor('draft-content',{});
			tinyEditor.render();
		}
		if (isHide) {
			tinyEditor.show();
			isHide = false;
		}
		else{
			tinyEditor.hide();
			isHide = true;			
		}
	}
	function Save(){
		alert("Closed");
	}
</script>