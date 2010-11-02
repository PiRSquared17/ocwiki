var apiPath;
var actionPath;
var uploadPath;
var templatePath;

function confirmDelete(name, str) {
	var checkboxes = document.getElementsByName(name);
	var count = 0;
	for (i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			count++;
		}
	}
	if (count > 0) {
		return confirm('Bạn có chắc muốn xoá ' + count + ' ' + str + ' không?');
	} else {
		alert('Bạn cần chọn ' + str + '.');
		return false;
	}
}

function filterNumericKey(el) {
	Event.observe($(el), window.opera ? 'keypress':'keydown', function(evt) {
		var theEvent = evt || window.event;
		var keycode = theEvent.keyCode || theEvent.which;
		var key = String.fromCharCode( keycode );
		var regex = /[0-9]|\./;
		if( keycode != 8 && keycode != 9 && keycode != 16 && keycode != 13 &&
				!(keycode >= 37 && keycode <= 40) && !regex.test(key) ) {
//			alert(keycode);
		  theEvent.returnValue = false;
		  theEvent.preventDefault();
		}
	});
}

function apiUrl(api) {
	return apiPath + '/' + api;
}

function actionUrl(action) {
	return actionPath + "/" + action;
}

/**
 * Editor class
 */

Editor = Class.create();

Editor.active = null;

Editor.edit = function(id) {
	if (Editor.active != null) {
		Editor.preview(Editor.active);
	}
	var element = $(id);
	var previewDiv = $(id + '-preview');
	if (previewDiv != null) {
		previewDiv.remove();
	}
	element.show();
	if (element.getElementsByTagName('textarea').length > 0) {
		var textarea = element.getElementsByTagName('textarea')[0];
		var tinymceEditor = tinymce.get(textarea.id);
		if (!tinymceEditor) {
			tinymceEditor = new tinymce.Editor(textarea.id, {});
			tinymceEditor.render();
		}
		tinymceEditor.focus(true);
	} else {
		var textbox = element.getElementsByTagName('input')[0];
		textbox.focus();
	}
	element.show();
	Editor.active = id;
};

Editor.preview = function(id) {
	var element = $(id);
	var textarea = element.getElementsByTagName('textarea')[0]; 
	var previewDiv = document.createElement('div');
	previewDiv.setAttribute('id', id + '-preview');
	previewDiv.innerHTML = textarea.value;
	previewDiv.observe('click', function(event) {
		var elementId = this.id;
		elementId = elementId.substring(0, elementId.length-8);
//		alert(elementId);
		Editor.edit(elementId);
	});
	element.hide();
	$(id).insert({after: previewDiv});
};
// Script cho inputextfield
Editor.ActiveTextField = null;

Editor.previewTextField = function(id){
	var element = $(id);
	var textfield = element.getElementsByTagName('input')[0];
	var textfieldId = textfield.id;
	var prevSpan = document.createElement('span');
	var content = '';
	prevSpan.setAttribute('id',id + '-preview');
	tinymceEditor = tinymce.get(textfield.id);
	if (tinymceEditor) {
		content = tinymceEditor.getContent();
	} else {
		content = textfield.value;
	}
	prevSpan.innerHTML = '(' + content + ' điểm): ';
	prevSpan.observe('click',function(event){
		var elementId = this.id;
		elementId = elementId.substring(0,elementId.length-8);
		Editor.EditTextField(elementId);
	});
	$(textfieldId).insert({after: prevSpan});
	textfield.hide();
};


Editor.EditTextField = function(id){
	var element = $(id);
	var content ='';
	if (!element) {
		return;
	}
	if (Editor.ActiveTextField) {
		Editor.previewTextField(Editor.ActiveTextField);
	}
	var previewSpan = $(id + '-preview');
	if (previewSpan) {
		content = previewSpan.textContent;
		previewSpan.remove();
	}
	var textfields = element.getElementsByTagName('input');
	if (textfields.length > 0) {
		var textfield = textfields[0];
		textfield.value = content.substring(1,content.length - 8);
	}
	textfield.show();
	textfield.select();
	Editor.active = id;
};

var ResourceService = Class.create( {
	retrieve: function(id, successCallback, failCallback) {
		new Ajax.Request(restPath + '/resources/' + resourceId,
				{
				  method:'get',
				  requestHeaders : {
				      Accept : 'application/json'
				  },
				  evalJSON : true,
				  onSuccess : function(transport) {
					  successCallback(transport.responseJSON.result);
				  },
				  onFailure: function(transport){ 
					  DefaultTemplate.onFailure(transport); 
					  if (failCallback) {
						  failCallback(transport);
					  }
			      }
			    });
	},
	
	update: function(value, successCallback, failCallback) {
		new Ajax.Request(restPath + '/resources/' + value.id,
				{
				  method:'put',
				  requestHeaders : {
				      Accept : 'application/json'
				  },
				  contentType: 'application/json',
				  postBody: Object.toJSON(value),
				  evalJSON : true,
				  onSuccess : function(transport) {
					  successCallback(transport.responseJSON.result);
				  },
				  onFailure: function(transport){ 
					  DefaultTemplate.onFailure(transport); 
					  if (failCallback) {
						  failCallback(transport);
					  }
			      }
			    });
	}
});