$(document).ready(function () {

    readAll();
    document.getElementById("createWriter").addEventListener('click', createPost);
})

function readAll() {
    const tbody = document.getElementById("post-list");
    $.ajax({
        type: "GET",
        url: "/api/post",
        contentType: "application/json",
        success: function (response) {
            $("post-list").empty();
            [...response].forEach((data, num) => {
                drawTr(data, num + 1);
            })
        },
        complete: function () {
            let trs = tbody.children;
            [...trs].forEach((tr) => {
                tr.addEventListener('click', detail);
            })
        }
    })
}

function createPost() {
    let datas = document.getElementsByClassName('data');


    let empty = 0;
    [...datas].forEach((data) => {
        if (!!$(data).val() === false) {
            empty++;
        }
    })
    if (empty > 0) {
        alert("입력 되지 않은 항목이 있습니다.")
        return;
    }

    let data = {
        "writer": $(datas[0]).val(),
        "password": $(datas[1]).val(),
        "title": $(datas[2]).val(),
        "contents": $(datas[3]).val()
    }

    $.ajax({
        type: "post",
        url: "/api/createPost",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            location.reload();
        }
    })
}

function detail() {
    let id = this.children[0].innerText;
    location.href = `/detail/page/${id}`
}

function drawTr(data, num) {
    let html = `<tr>
                    <td style="display: none;">${data.id}</td>
                    <td width="5%">${num}</td>
                    <td width="55%">${data.title}</td>
                    <td width="20%">${data.writer}</td>
                    <td width="20%">${data.createAt}</td>
                </tr>
                `
    $('#post-list').append(html)
}


