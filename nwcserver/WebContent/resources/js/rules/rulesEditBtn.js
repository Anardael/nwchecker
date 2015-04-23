function editContent(contentId){
    document.getElementsByClassName("rule-content")[contentId-1].hidden = "hidden";
    document.getElementsByClassName("rule-content-area")[contentId-1].hidden = "";

    for(var i in document.getElementsByClassName("rule-edit-btn")){
        document.getElementsByClassName("rule-edit-btn")[i].hidden = "hidden";
        document.getElementsByClassName("rule-submit-btn")[i].hidden = "hidden";
    }
    document.getElementsByClassName("rule-submit-btn")[contentId-1].hidden = "";
}