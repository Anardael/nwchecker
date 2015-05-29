function tryToShowStatistic(){
	/*$('#statisticTable').bootstrapTable('destroy');
	$.getJSON('TaskStatisticTable.do?taskId=' + TASK_ID, function(data){
		$('#statisticTable').bootstrapTable({
			data : data
		});
		
	})*/
	$('#taskStatistic').modal();
}