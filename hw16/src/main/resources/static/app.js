
var stompClient = null;

const connect = () => {
    stompClient = Stomp.over(new SockJS('/ws'));
    stompClient.connect({}, frame => {
        console.log(`Connected: ${frame}`);
    });
};

    function sendUser() {

            var user = {
                'name': $("#name").val(),
                'age': $("#age").val(),
                'phone': $("#phone").val(),
                'address': $("#address").val(),
            };
            console.log(sendUser);

            stompClient.send("/app/addUser", {}, JSON.stringify(user));

            stompClient.subscribe('/topic/response/addUser', function(response) {
                       			var data = JSON.parse(response.body);
                       			console.log(data.message);
                       			alert(data.message);
            });
    }

    function viewUser(){
            stompClient.send("/app/viewUser", {},null);
            stompClient.subscribe('/topic/response/viewUser', function(response) {

            			var data = JSON.parse(response.body);
            			var list = '';
            			console.log(data);
            			var output="<ul>";
                        for (var i in data) {
                          output+="<li>" + data[i].name + "-" + data[i].age + "-" + data[i].phone+"-" + data[i].address+"</li>";
                        }
                        output+="</ul>";
                        document.getElementById("placeholder").innerHTML=output;
            });
    }


