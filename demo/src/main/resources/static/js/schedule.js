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