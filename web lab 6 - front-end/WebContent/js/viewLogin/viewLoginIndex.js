function viewLoginIndex(formID){
	$("#"+formID).attr("action", "viewLoginIndex.do");
	$("#"+formID).submit();
}