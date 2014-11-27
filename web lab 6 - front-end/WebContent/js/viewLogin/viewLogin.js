function viewLoginIndex(formID){
	$("#"+formID).attr("action", "viewLogin.do");
	$("#"+formID).submit();
}