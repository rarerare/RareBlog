window.onload=function(){
	var addedDate=false;
	$("#addDateButt").click(function(event){
		event.preventDefault();
		
		$("#spaceForAddDate").toggle();
		
		
		addedDate=!addedDate;
	});
	$("#submitButt").click(function(event){
		event.preventDefault();
		if(!addedDate){
			var date=new Date();
			
			$("#year").val(date.getFullYear());
			$("#month").val(date.getMonth()+1);
			$("#day").val(date.getDate());
		}
		$("#writerForm").submit();
	});
	
	checkLogin();
}
function checkLogin(){
	$.ajax({ type: 'POST',
			url: 'mainservlet?mAction=checkLogin',
			success: function(data){
				if(data=="stranger"){
					window.location="writerLogin.jsp";
				}
			},
			async: false
		
	});
}