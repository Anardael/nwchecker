package com.nwchecker.server.json;


public class EditorJson {
    private boolean isEdit;
    private String name;

    private EditorJson(){

    }

    public static EditorJson createEditorJson(boolean isEdit, String name){
        EditorJson editorJson = new EditorJson();
        editorJson.setEdit(isEdit);
        editorJson.setName(name);
        return editorJson;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
