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
            location.replace('/home')
        })
    })
}

const moveGroupButton = document.getElementById()
