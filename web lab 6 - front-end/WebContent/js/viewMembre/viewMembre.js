function showNewProfil(){
	var o = $("#new-profil-membre").css("visibility");
	if(o === "visible"){
		$("#new-profil-membre").css("visibility", "hidden");
	} else {
		$("#new-profil-membre").css("visibility", "visible");
	}
}