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