/**
 * 
 */


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