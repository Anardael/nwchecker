$(document).ready(function() {
	$('#archiveTable').on('click-row.bs.table', function(e, row, $element) {
		$.getJSON('getTaskDetails.do?taskId=' + row['id'], function(data) {
			$('#pageHeader').html(data['title']);
			$('#taskBody').html(data['description']);
			$('#timeLimit').html(data['timeLimit']);
			$('#memoryLimit').html(data['memoryLimit']);
		});
		$('#taskDetailsModal').modal();
	});
});