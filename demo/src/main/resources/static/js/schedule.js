const privateScheduleSubmitButton = document.getElementById('private-schedule-submit-btn');

if(privateScheduleSubmitButton){

    privateScheduleSubmitButton.addEventListener('click', () => {
        function success(){
            alert('일정 등록 성공');
            location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
        }

        function fail(){
            alert('일정 등록 실패');
            location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
        }

        let body = JSON.stringify({
            groupId: document.getElementById('groupId').value,
            space: document.getElementById('principalEmail').value,
            startAt: document.getElementById('startAt').value,
            endAt: document.getElementById('endAt').value,
            content: document.getElementById('schedule-content').value,
            status: null

        });
        console.log(document.getElementById('principalEmail').value);
        console.log(body);
        httpRequest('POST', `/api/schedule`, body, success, fail);

    });
}

const groupScheduleSubmitButton = document.getElementById('group-schedule-submit-btn');

if(groupScheduleSubmitButton){
    groupScheduleSubmitButton.addEventListener('click', () => {

        function success(){
            alert('group schedule submit success');
            location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
        }

        function fail(){
            alert('group schedule submit fail');
            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('memberSpace').value);
        }

        let body = JSON.stringify({
            groupId: document.getElementById('groupId').value,
            space: "master",
            startAt: document.getElementById('startAt').value,
            endAt: document.getElementById('endAt').value,
            content: document.getElementById('schedule-content').value,
        })

        httpRequest('POST', `/api/schedule`, body, success, fail);
    });
}

const groupScheduleDeleteButtons =  document.getElementsByClassName('groupScheduleDelete-btn');

if(groupScheduleDeleteButtons){

    function success(){
        alert('스케줄 삭제 성공');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }

    function fail(){
        alert('스케줄 삭제 실패');
        location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('memberSpace').value);
    }

    let scheduleIds = Array.from(document.getElementsByClassName('groupScheduleIds'));

    Array.from(groupScheduleDeleteButtons).forEach((button, index) => {

        let body = JSON.stringify({
            scheduleId : scheduleIds[index].value
        });
        button.addEventListener('click', () => {
            httpRequest('DELETE', `/api/schedule`, body, success, fail);
        });
    });
}

const personalScheduleDeleteButtons = document.getElementsByClassName('personalScheduleDelete-btn');

if(personalScheduleDeleteButtons){

    let scheduleIds = Array.from(document.getElementsByClassName('personalScheduleIds'));

    function success(){
        alert('스케줄 삭제 성공');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }

    function fail(){
        alert('스케줄 삭제 실패');
        location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('memberSpace').value);
    }

    Array.from(personalScheduleDeleteButtons).forEach((button, index) => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId : scheduleIds[index].value
            });
            httpRequest('DELETE', `/api/schedule`, body, success, fail);
        });
    });
}

const scheduleDeleteButton = document.getElementsByClassName('deleteSchedule-btn');

if(scheduleDeleteButton){

    function success(){
        alert('스케줄 삭제 성공');
        location.replace('/home');
    }

    function fail(){
        alert('스케줄 삭제 실패');
        location.replace('/home');
    }

    let scheduleIds = Array.from(document.getElementsByClassName('scheduleIds'));

    Array.from(scheduleDeleteButton).forEach((button, index) => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId: scheduleIds[index].value
            });
            httpRequest('DELETE', `/api/schedule`, body, success, fail);
        });
    });
}

const updateScheduleButton = document.getElementById('updateSchedule-btn');

if(updateScheduleButton){

    updateScheduleButton.addEventListener('click', () => {
        let body = JSON.stringify({
                scheduleId: document.getElementById('scheduleId').value,
                startAt: document.getElementById('newStartAt').value,
                endAt: document.getElementById('newEndAt').value,
                content: document.getElementById('newContent').value
            });

            httpRequest('PUT', `/api/schedule`, body, success, fail);
    })
    function success(){
        alert('스케줄 수정 성공');
        location.replace('/home');
    }

    function fail(){
        alert('스케줄 수정 실패');
        location.replace('/home');
    }
}

const completeScheduleButtons = document.getElementsByClassName('completeSchedule-btn');

if(completeScheduleButtons){
    let scheduleIds = Array.from(document.getElementsByClassName('scheduleIds'));

    Array.from(completeScheduleButtons).forEach((button, index) => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId: scheduleIds[index].value
            });
            httpRequest('PUT', `/api/scheduleComplete`, body, success, fail);
        });
    })

    function success(){
        alert('스케줄 수정 성공');
        location.replace('/home');
    }

    function fail(){
        alert('스케줄 수정 실패');
        location.replace('/home');
    }
}

const updateScheduleButton2 = document.getElementById('updateSchedule-btn2');

if(updateScheduleButton2){
    updateScheduleButton2.addEventListener('click', () => {
        let body = JSON.stringify({
            scheduleId: document.getElementById('scheduleId').value,
            startAt: document.getElementById('startAt').value,
            endAt: document.getElementById('endAt').value,
            content: document.getElementById('schedule-content').value
        });

        httpRequest('PUT', `/api/schedule`, body, success, fail);
    })

    function success(){
        alert('스케줄 수정 성공');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }

    function fail(){
        alert('스케줄 수정 실패');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }
}

const completeGroupScheduleButton = document.getElementsByClassName('completeGroupSchedule-btn');

if(completeGroupScheduleButton){
    let scheduleIds = Array.from(document.getElementsByClassName('groupScheduleIds'));

    Array.from(completeGroupScheduleButton).forEach((button, index) => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId: scheduleIds[index].value
            });
            httpRequest('PUT', `/api/scheduleComplete`, body, success, fail);
        });
    });

    function success(){
        alert('스케줄 수정 성공');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }

    function fail(){
        alert('스케줄 수정 실패');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }
}

const completePersonalScheduleButton = document.getElementsByClassName('completePersonalSchedule-btn');

if(completePersonalScheduleButton){
    let scheduleIds = Array.from(document.getElementsByClassName('personalScheduleIds'));

    Array.from(completePersonalScheduleButton).forEach((button, index) => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId: scheduleIds[index].value
            });
            httpRequest('PUT', `/api/scheduleComplete`, body, success, fail);
        });
    });

    function success(){
        alert('스케줄 수정 성공');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }

    function fail(){
        alert('스케줄 수정 실패');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }
}



function httpRequest(method, url, body, success, fail){
    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: body
    }).then(response => {
        if(response.status === 200 || response.status ===201){
            return success();
        }
        else{
            return fail();
        }
    });
}