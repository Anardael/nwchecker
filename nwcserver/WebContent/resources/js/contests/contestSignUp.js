function contestCheckType(indexFirstTask, indexContestType) {
    console.log(indexFirstTask + ", " + indexContestType);
    location.href = 'checkPassTaskType.do?id=' + indexFirstTask + '&typeId=' + indexContestType;
    console.log(location.href);
    /*location.href = 'passTask.do?id=' + indexFirstTask;*/
}
