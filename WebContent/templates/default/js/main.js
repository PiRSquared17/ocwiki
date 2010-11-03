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

var TextboxDefaultHandler = Class.create( {
	initialize: function (elementOrId, defaultValue) {
		this.text = $(elementOrId);
		this.defaultValue = defaultValue;
		this.appendDefaultValue();
		
		me = this;
		Event.observe(this.text, 'focus', function() {
			me.text.style.color = 'black';
			if (me.text.value == me.defaultValue) {
				me.text.value = '';
			}
		});
		Event.observe(this.text, 'blur', function() {
			me.appendDefaultValue();
		});
	},
	appendDefaultValue: function() {
		if (this.text.value == '' || this.text.value == this.defaultValue) {
			this.text.style.color = '#777777';
			this.text.value = this.defaultValue;
		}
	}
});

var AccordionHandler = Class.create({
	initialize: function(elementOrId) {
		this.container = $(elementOrId);
		this.title = '';
	}
});