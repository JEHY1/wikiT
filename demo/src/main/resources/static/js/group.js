const createGroupButton = document.getElementById('createGroup-btn')

if(createGroupButton){
    createGroupButton.addEventListener('click', () => {
        fetch(`/api/group`,{
            method: 'POST',
            headers:{
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                groupName: document.getElementById('groupName').value,
                constructor: document.getElementById('constructor').value
            })
        }).then(() => {
            alert('그룹 생성완료');
            location.replace('/home');
        });
    });
}

const inviteGroupButton = document.getElementById('invite-btn')

if(inviteGroupButton){
    inviteGroupButton.addEventListener('click', () => {


        function success() {
                alert('초대 완료되었습니다.');
                location.replace('/group/' + document.getElementById('groupId').value);
        }

        function fail() {
            alert('초대 실패(email을 확인하세요).');
            location.replace('/group/' + document.getElementById('groupId').value);
        }

        body = JSON.stringify({
            groupName: document.getElementById('groupName').value,
            groupMakerId: document.getElementById('groupId').value,
            email: document.getElementById('email').value
       });

        httpRequest('POST', `/api/invite`,body, success, fail);
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