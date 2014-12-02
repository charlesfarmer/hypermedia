$(document).ready(function(){
	$(".img-toolbox, #img-new-vitrines").each(function(){
		$(this).hover(
				function(){
					$(this).css({
						"background-color":"rgba(80,80,80,0.271)",
						"cursor":"pointer"
					});
				},
				function(){
					$(this).css({
						"background-color":"rgba(150,190,180,0.271)",
						"cursor":"pointer"
					});
				}
	    );
	});
});