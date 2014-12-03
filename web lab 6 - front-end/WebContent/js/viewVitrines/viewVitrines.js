var mouse_is_inside = false;
$(document).ready(function(){
	
	// hover feel
	$(".img-toolbox, #new-vitrine-button").each(function(){
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
	
	//functions to hide edit div
	$('#edit-vitrine').hover(function(){ 
        mouse_is_inside=true; 
    }, function(){ 
        mouse_is_inside=false; 
    });
    $("body").mouseup(function(){ 
        if(!mouse_is_inside){
        	$('#edit-vitrine').hide();
        }
    });
	
	//bind create
	$("#new-vitrine-button").click(function(){
		$("#new-vitrine-form").submit();
	});
	
	//bind delete
	$(".supprimer-vitrine > img").click(function(){
		var idImg = $(this).attr("id");
		var idVitrine = idImg.substr(idImg.indexOf("-") + 1);
		console.log(idVitrine);
		$("#delete-vitrine-id").val(idVitrine);
		$("#delete-vitrine-form").submit();
	});
	
	//bind edit
	$(".modifier-vitrine > img").click(function(){
		var idImg = $(this).attr("id");
		var idVitrine = idImg.substr(idImg.indexOf("-") + 1);
		console.log(idVitrine);
		$("#edit-vitrine-id").val(idVitrine);
		$("#edit-vitrine").css("display","inline");
	});
	
	//bind view
	
});

function checkTitleLength(idFormElement){
	hideVitrinesErrors();
	var title = $("#"+idFormElement).val();
	if(isBlank(title)){
		$("#edit-failed-title-blank").css("display","block");
		return false;
	}
	return true;
}

function hideVitrinesErrors(){
	$("#delete-vitrine-success").css("display","none");
	$("#delete-vitrine-failed").css("display","none");
	$("#edit-vitrine-success").css("display","none");
	$("#edit-vitrine-failed").css("display","none");
	$("#edit-failed-title-blank").css("display","none");
	$("#create-vitrine-success").css("display","none");
	$("#create-vitrine-failed").css("display","none");
	return 0;
}