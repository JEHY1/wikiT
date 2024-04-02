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
            status: null
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
    let index = 0;

    Array.from(groupScheduleDeleteButtons).forEach(button => {

        let body = JSON.stringify({
            scheduleId : scheduleIds[index++].value
        });

        button.addEventListener('click', () => {
            httpRequest('DELETE', `/api/schedule`, body, success, fail);
        });
    });
}

const personalScheduleDeleteButtons = document.getElementsByClassName('personalScheduleDelete-btn');

if(personalScheduleDeleteButtons){
    let index = 0;
    let scheduleIds = Array.from(document.getElementsByClassName('personalScheduleIds'));

    function success(){
        alert('스케줄 삭제 성공');
        location.replace('/group/' + document.getElementById('groupId').value + "?memberEmail=" + document.getElementById('memberSpace').value);
    }

    function fail(){
        alert('스케줄 삭제 실패');
        location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('memberSpace').value);
    }

    Array.from(personalScheduleDeleteButtons).forEach(button => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId : scheduleIds[index++].value
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
    let index = 0;

    Array.from(scheduleDeleteButton).forEach(button => {
        button.addEventListener('click', () => {
            let body = JSON.stringify({
                scheduleId: scheduleIds[index++].value
            });

            httpRequest('DELETE', `/api/schedule`, body, success, fail);
        });
    });
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