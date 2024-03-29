const createArticleButton = document.getElementById('createArticle-btn');

if(createArticleButton){
    createArticleButton.addEventListener('click', () => {

        function success(){
            alert('글 등록 성공');
            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('space').value);
        }

        function fail(){
            alert('권한이 없습니다.');
            location.replace('/home');
        }

        let body = JSON.stringify({
            title: document.getElementById('title').value,
            content: document.getElementById('content').value,
            groupId: document.getElementById('groupId').value,
            author: document.getElementById('author').value,
            space: document.getElementById('space').value
        })

        httpRequest('POST', `/api/article`, body, success, fail);



//        fetch(`/api/article`, {
//            method: 'POST',
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: JSON.stringify({
//                title: document.getElementById('title').value,
//                content: document.getElementById('content').value,
//                groupId: document.getElementById('groupId').value,
//                author: document.getElementById('author').value,
//                space: document.getElementById('space').value
//            })
//        }).then(() => {
//            alert('글 등록 성공');
//            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('space').value);
//        });
    });
}


const modifyArticleButton = document.getElementById('modifyArticle-btn');

if(modifyArticleButton){
    modifyArticleButton.addEventListener('click', () => {

        function success(){
            alert('글 수정 성공');
            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('space').value);
        }

        function fail(){
            alert('권한이 없습니다.');
            location.replace('/home');
        }

        let body = JSON.stringify({
            articleId: document.getElementById('articleId').value,
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        })

        httpRequest('PUT', `/api/article`, body, success, fail);


//        fetch(`/api/article`, {
//            method: 'PUT',
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: JSON.stringify({
//                articleId: document.getElementById('articleId').value,
//                title: document.getElementById('title').value,
//                content: document.getElementById('content').value
//            })
//        }).then(() => {
//            alert('글 수정 성공');
//            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('space').value);
//        });
    });
}

const deleteArticleButton = document.getElementById('deleteArticle-btn');

if(deleteArticleButton){
    deleteArticleButton.addEventListener('click', () => {

        function success(){
            alert('글 삭제 성공');
            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('space').value);
        }

        function fail(){
            alert('권한이 없습니다.');
            location.replace('/home');
        }

        let body = JSON.stringify({
            articleId: document.getElementById('articleId').value
        })

        httpRequest('DELETE', `/api/article`, body, success, fail);

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