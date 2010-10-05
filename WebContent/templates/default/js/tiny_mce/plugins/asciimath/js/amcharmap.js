tinyMCEPopup.requireLangPack();
var waitforAMTcgiloc = true;

var AsciimathDialog = {
	init : function() {
		AMTcgiloc = tinyMCEPopup.getWindowArg('AMTcgiloc');
	},

	set : function(val) {
		tinyMCEPopup.restoreSelection();
		//xin lỗi vì tự ý sửa file này và file amcharmap.htm. viec de ky tu < trong htm day loi, sieu nhan check lai nhe.
		var val2 = val.replace("nhohon","<");
		// Insert the contents from the input into the document
		tinyMCEPopup.editor.execCommand('mceAsciimath', val);
		tinyMCEPopup.close();
	}
};

tinyMCEPopup.onInit.add(AsciimathDialog.init, AsciimathDialog);
