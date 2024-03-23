const createArticleButton = document.getElementById('createArticle-btn');

if(createArticleButton){
    createArticleButton.addEventListener('click', () => {
        fetch(`/api/article`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                groupId: document.getElementById('groupId').value,
                author: document.getElementById('author').value,
                space: document.getElementById('space').value
            })
        }).then(() => {
            alert('글 등록 성공');
            location.replace('/group/' + document.getElementById('groupId').value + '?memberEmail=' + document.getElementById('space').value);
        })
    })
}
