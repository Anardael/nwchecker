/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
//	//Setting KCfinder as file manager for file upload
//	   config.filebrowserBrowseUrl = 'resources/js/gpFinder/finder.html';
//	   config.filebrowserImageBrowseUrl ='resources/js/gpFinder/finder.html';
//	   config.filebrowserFlashBrowseUrl = 'resources/js/ckeditor/kcfinder/browse.php?type=flash';
//	   config.filebrowserUploadUrl = 'resources/js/ckeditor/kcfinder/upload.php?type=files';
//	   config.filebrowserImageUploadUrl = 'resources/js/gpFinder/finder.html';
//	   config.filebrowserFlashUploadUrl = 'resources/js/ckeditor/kcfinder/upload.php?type=flash';


    // Temporary English only
    config.language = 'en';

    config.uiColor = '#1772A3';

    // Custom toolbar
    config.toolbar_Full =
        [
            {name: 'fontStyles', items: ['Font', 'FontSize']},
            {name: 'lists', items: ['NumberedList', 'BulletedList']},
            {name: 'basicStyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript']},
            {name: 'colors', items: ['TextColor', 'BGColor']},
            {name: 'paragraph', items: ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
            {name: 'other', items: ['Maximize']},
            {name: 'insert', items: ['Image']},
        ];
    config.toolbar = 'Full';
};