	//kiem tra inputbox empty
	function is_empty(element) {
		if (element.value=="" || element.value == null || element.value.length==0) {
			//bien bao loi
			element.style.background="#FF99AF";
			return true;
		} else {
			element.style.background="#FFFFFF";
			return false;
		}
	}

	//co phai e mail?
	function is_email(element){
		var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
		if(element.value.match(emailExp) && element.value!="" && element.value != null && element.value.lenth!=0){
			element.style.background="#FFFFFF";
		}else{
			//bien bao loi
			element.style.background="#FF99AF";
		}
	}

	//co phai e mail?
	function is_user(element){
		var userExp = /[a-zA-Z][a-zA-z0-9]/;
		if(element.value.match(userExp) && element.value!="" && element.value != null && element.value.lenth!=0){
			element.style.background="#FFFFFF";
		}else{
			//bien bao loi
			element.style.background="#FF99AF";
			
		}
	}
	
	function confirm_pass(pass1,pass2){
		if (pass1.value == pass2.value) {
			if (pass1.value=="" || pass1.value == null || pass1.value.length==0)
				pass1.style.background="#FF99AF";
			else {
				pass1.style.background="#FFFFFF";
				pass2.style.background="#FFFFFF";
			}
		} else {
			pass2.style.background="#FF99AF";
		}
	}