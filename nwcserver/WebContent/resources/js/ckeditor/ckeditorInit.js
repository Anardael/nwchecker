function initializeCKEdior(textareaId, language) {
	var lang = 'en';
	if (language == 'ua') {
		lang = 'uk';
	}
	CKEDITOR.replace( textareaId,
			{
				filebrowserBrowseUrl: '../Filemanager/index.html',
				language: lang
			});
}