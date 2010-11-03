var DefaultTemplate = {

	promptLogin : function() {
		mess = '<p>Bạn chưa đăng nhập hoặc phiên làm việc của bạn đã hết hạn.</p>'
				+ '<p>Hãy <a href="' + actionPath + '/user.login' + '">đăng nhập</a>.</p>';
		Dialog.info(mess, {
			width : 300,
			height : 100,
			className : "alphacube"
		});
	},

	onFailure: function(transport) {
        if (transport.responseJSON.code == 'login required') {
            this.promptLogin();
        } else {
            alert('Có lỗi xảy ra, xin lỗi vì sự bất tiện!');
        }  
	}

};