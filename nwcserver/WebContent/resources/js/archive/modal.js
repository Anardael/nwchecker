$(document).ready(function() {
	$('#archiveTable').on('click-row.bs.table', function(e, row, $element) {
		location.href = 'getTaskDetails.do?id=' + row['id']
	});
});