function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    //ajax方法调用响应
    $.ajax({
        type: "POST",
        //跳转的页面路径
        url: "/comment",
        //type
        contentType: 'application/json',
        //传入的JSON响应
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        //成功的命令行回复
        success: function (response) {
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=7ec7112452d3904f4082&rediect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}
