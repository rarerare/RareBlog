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
			
			$("#year").val(date.year);
			$("#month").val(date.month+1);
			$("#day").val(date.day);
		}
		$("#writerForm").submit();
	});
	
}