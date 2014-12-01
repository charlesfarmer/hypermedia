function viewLoginIndex(formID){
	$("#"+formID).attr("action", "viewLogin.do");
	$("#"+formID).submit();
}

function checkFields(){
	$("#login-failed").css("display", "none");
	$("#register-failed").css("display", "none");
	$("#error-username-empty").css("display","none");
	$("#error-passwords").css("display","none");
	var username = $("#register-username").val();
	if(isBlank(username)){
		$("#error-username-empty").css("display","block");
		return false;
	}
	var password = $("#register-password").val();
	var password2= $("#register-password2").val();
	if(isEmpty(password) || isEmpty(password2) || password != password2){
		$("#error-passwords").css("display","block");
		return false;
	}
	return true;
}

function isBlank(string){
	return !string || !/\S/.test(string);
}

function isEmpty(string){
	return !string || string.length === 0;
}