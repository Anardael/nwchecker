/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
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
                {name: 'other', items: ['Maximize']}
            ];
    config.toolbar = 'Full';
};