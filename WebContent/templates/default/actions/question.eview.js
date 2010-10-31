function enableMode(mode) {
	// disable all
	$('mode-view-link').show();
	$('mode-view-link-enabled').hide();
	$('mode-view-container').hide();
	$('mode-exercise-link').show();
	$('mode-exercise-link-enabled').hide();
	$('mode-exercise-container').hide();
	// enable specified
	$('mode-' + mode + '-link').hide();
	$('mode-' + mode + '-link-enabled').show();
	$('mode-' + mode + '-container').show();
}

function viewMode() {
	enableMode('view');
}

function exerciseMode() {
	enableMode('exercise');
	
}

function configMenu() {
  this.closeDelayTime = 300;
}

var toolMenu = new Menu('tool-menu-root', 'toolMenu', configMenu);

function editAnswer(id) {
	// prepare editor
	var editor = $('answer-editor');
	editor.elements['id'] = id;
	new Ajax.Request(apiUrl('answer.edit') + '?id=' + id, {
		  method: 'get',
		  onSuccess: function(transport) {
			var json = transport.responseText.evalJSON();
			editor.elements['content'].value = json.answer.content
		  }
		});
	// replace
	var wrapper = $('wrapper' + id);
}