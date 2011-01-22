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
			tinymceEditor = new tinymce.Editor(textarea.id, tinymceOptions);
			tinymceEditor.render();
		}
		tinymceEditor.focus(true);
	} else {
		var textbox = element.getElementsByTagName('input')[0];
		textbox.focus();
	}
	element.show();
	Editor.active = id;
	//Editor.ActiveTextField = id;
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
	var prevSpans = element.getElementsByTagName('span');
	var content = '';
	var prevSpan = document.createElement('span');
	prevSpan.setAttribute('id',id + '-preview');
	tinymceEditor = tinymce.get(textfield.id);
	if (tinymceEditor) {
		content = tinymceEditor.getContent();
	} else {
		content = textfield.value;
	}
	prevSpan.innerHTML = '(' + content + ' điểm): ';
	prevSpan.observe('click',function(event){
		var elementPrev = this.id;
		var elementId = elementPrev.substring(0,elementPrev.length-8);
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
	if (Editor.ActiveTextField != null) {
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
	Editor.ActiveTextField = id;
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


function createQuestion() {
	resource = {
		articleType: 'oop.data.BaseQuestion',
		article: {
		    type: 'baseQuestionBean',
		    name: 'Câu hỏi mới',
		    namespace: {
		        id: 3
		    }
		} 
	};
	sendCreateRequest(resource);
}

function createSolution(questionId) {
    resource = {
            articleType: 'oop.data.Solution',
            article: {
                type: 'solutionBean',
                name: 'Bài giải mới',
                namespace: {
                    id: 0
                },
	            question: {
	                id: questionId
	            }
            } 
        };
        sendCreateRequest(resource);
}

function createTest() {
    resource = {
        articleType: 'oop.data.Test',
        article: {
            type: 'testBean',
            name: 'Đề thi mới',
            namespace: {
                id: 4
            }
        } 
    };
    sendCreateRequest(resource);
}

function createFile() {
    resource = {
        articleType: 'oop.data.File',
        article: {
            type: 'fileBean',
            name: 'Tập tin mới',
            namespace: {
                id: 0
            }
        } 
    };
    sendCreateRequest(resource);
}

function createTextArticle() {
    resource = {
        articleType: 'oop.data.TextArticle',
        article: {
            type: 'textArticleBean',
            name: 'Bài viết mới',
            namespace: {
                id: 0
            }
        } 
    };
    sendCreateRequest(resource);
}

function createTestStructure() {
    resource = {
        articleType: 'oop.data.TestStructure',
        article: {
            type: 'testStructureBean',
            name: 'Cấu trúc đề mới',
            namespace: {
                id: 5
            }
        } 
    };
    sendCreateRequest(resource);
}

function createTopic() {
    resource = {
        articleType: 'oop.data.Topic',
        article: {
            type: 'topicBean',
            name: 'Chủ đề mới',
            namespace: {
                id: 2
            }
        } 
    };
    sendCreateRequest(resource);
}

function getEditorContent(id) {
	tinymceEditor = tinymce.get(id);
	if (tinymceEditor) {
		return tinymceEditor.getContent;
	}
	return $F(id);
}

function sendCreateRequest(resource) {
    new Ajax.Request(restPath + '/resources',
    {
        method:'post',
        contentType: 'application/json',
        postBody: Object.toJSON(resource),
        requestHeaders : 
        {
            Accept : 'application/json'
        },
        evalJSON : true,
        onSuccess : function(transport) {
            var id = transport.responseJSON.result.id;
            location.href = actionPath + '/article.edit?id=' + id;
        },
        onFailure: function(transport) {
            DefaultTemplate.onFailure(transport); 
        }
    });
}

function setVisible(elementOrId, b) {
	var elem = $(elementOrId);
	if (b) {
		elem.show();
	} else {
		elem.hide();
	}
}

var WebService = Class.create();

WebService.get = function(url, options) {
	var requestOptions = Object.clone(options);
	requestOptions.method = 'get';
	WebService.request(url, requestOptions);
};

WebService.post = function(url, options) {
	var requestOptions = Object.clone(options);
	requestOptions.method = 'post';
	WebService.request(url, requestOptions);
}

WebService.request = function(url, options) {
	var ajaxOptions = {
	      requestHeaders : {
	          Accept : 'application/json'
	      },
	      contentType: 'application/json',
	      evalJSON: true
	};
	Object.extend(ajaxOptions, options);
	if (options.data) {
		delete ajaxOptions.data;
		ajaxOptions.postBody = Object.toJSON(options.data);
	}
	new Ajax.Request(restPath + url, ajaxOptions);
}

var OcwikiTemplate = Class.create({
	promptLogin: function() {
		mess = 'Bạn chưa đăng nhập hoặc phiên làm việc của bạn đã hết hạn.'
			+ 'Hãy đăng nhập.';
		alert(mess);
	},
	
	onFailure: function(transport) {
		var mess = 'Có lỗi xảy ra';
		if (transport.responseJSON.code) {
			mess += ': ' + transport.responseJSON.code;
		}
		mess += '. Xin lỗi vì sự bất tiện.';
		alert(mess);
	}
});

var template = new OcwikiTemplate();

Level_HARD = 1;
Level_EASY = -1;
Level_NORMAL = 0;