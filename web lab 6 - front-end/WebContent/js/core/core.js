function isBlank(string){
	return !string || !/\S/.test(string);
}

function isEmpty(string){
	return !string || string.length === 0;
}