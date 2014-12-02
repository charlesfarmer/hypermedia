function showNewProfil(){
	var o = $("#new-profil-membre").css("visibility");
	if(o === "visible"){
		$("#new-profil-membre").css("visibility", "hidden");
	} else {
		$("#new-profil-membre").css("visibility", "visible");
	}
}

function checkFields(){
	$(".alert").css("display", "none");
	$("#error-passwords").css("display","none");
	var password = $("#profil-membre-new-password").val();
	var password2= $("#profil-membre-new-password2").val();
	if(!isEmpty(password)){
		if(password != password2){
			$("#error-passwords").css("display","block");
			return false;
		}
	}
	return true;
}