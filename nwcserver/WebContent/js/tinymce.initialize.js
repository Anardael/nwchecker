tinymce.init({
	//language: "uk_UA",
	selector: "textarea#taskDescription",
	plugins: [
		"advlist autoresize autosave autolink link image lists charmap print preview paste hr anchor pagebreak spellchecker",
        "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
        "table contextmenu directionality emoticons template paste textcolor jbimages"
	],
	content_css: "css/content.css",
	toolbar: [ 
		"insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify",
		"| bullist numlist outdent indent | link image | print preview media | forecolor backcolor emoticons",
		" | fullscreen jbimages"
	],
	contextmenu: "cut copy paste | searchreplace | preview fullscreen",
	autoresize_min_width: 600,
	autoresize_min_height: 400,
	paste_as_text: true,
	save_enablewhendirty: true
});