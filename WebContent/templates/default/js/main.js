var DefaultTemplate = Class.create(OcwikiTemplate, {

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

});

template = new DefaultTemplate();

var TextboxDefaultHandler = Class.create( {
	initialize: function (elementOrId, defaultValue) {
		this.text = $(elementOrId);
		this.defaultValue = defaultValue;
		this.appendDefaultValue();
		
		var me = this;
		Event.observe(this.text, 'focus', function() {
			me.text.removeClassName('defaultValueTextbox');
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
			this.text.addClassName('defaultValueTextbox');
			this.text.value = this.defaultValue;
		}
	}
});

var AccordionHandler = Class.create({
	initialize: function(elementOrId) {
		this.container = $(elementOrId);
		this.header = this.container.select('.accor-header')[0];
		this.body = this.container.select('.accor-bodyWrapper')[0];
		var me = this;
		Event.observe(this.header, 'click', function(event) {
			if (me.body.visible()) {
				Effect.SlideUp(me.body, { duration: 0.5 });
				me.header.addClassName('accor-collapsed');
			} else {
				Effect.SlideDown(me.body, { duration: 0.5 });
				me.header.removeClassName('accor-collapsed');
			}
		});
	}
});

/**
 * Hàm của Thắng
 * @param info
 */

function openInfoDialog(info) {
	Dialog.info(info + "<br> Thông báo tự động đóng sau 2 giây ...",
               {width:300, height:100, className: "alphacube",showProgress: true});
  	timeout=2;
  	setTimeout(infoTimeout, 1000);
}

function infoTimeout() {
  	timeout--;
  	if (timeout >0) {
    	Dialog.setInfoMessage("Thông báo tự động đóng sau " + timeout + "giây ...");
    	setTimeout(infoTimeout, 1000);
 	}
 	else
  		Dialog.closeInfo();
}
