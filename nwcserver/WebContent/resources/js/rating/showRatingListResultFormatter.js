function dateTimeFormatter(value) {
	var date = new Date(value);	
    return date.toDateString() + " " + date.toLocaleTimeString();
}