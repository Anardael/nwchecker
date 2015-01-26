/**
 * Created by ReaktorDTR on 26.01.2015.
 */

function getids() {
	var indexes = {};
	var index = 0;
	var table = $('#usersData');
	table.find('tbody tr.selected').each(function() {
		var userId = $(this).find('td:nth-child(2)').html();
		indexes[index] = userId;
		index++;
	});
	console.log(indexes);
}